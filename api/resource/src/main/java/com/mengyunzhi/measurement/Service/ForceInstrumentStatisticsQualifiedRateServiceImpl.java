package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.CheckResult;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.InstrumentCheckInfo;
import com.mengyunzhi.measurement.output.BackQualifiedInstrument;
import com.mengyunzhi.measurement.repository.DepartmentRepository;

import com.mengyunzhi.measurement.repository.DistrictRepository;
import com.mengyunzhi.measurement.repository.InstrumentCheckInfoRepository;
import com.mengyunzhi.measurement.specs.InstrumentCheckInfoSpecs;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

/**
 * zhuchenshu
 */
@Service
public class ForceInstrumentStatisticsQualifiedRateServiceImpl implements ForceInstrumentStatisticsQualifiedRateService {
    private final static Logger logger = Logger.getLogger(OverdueCheckApplyServiceImpl.class.getName());
    @Autowired
    InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    DistrictService districtService;
    @Autowired
    CheckResultService checkResultService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Qualifier("DepartmentService")
    @Autowired
    DepartmentService departmentService;

    /**
     * zhuchenshu
     * 鉴定合格率查询通过年份与区域以及用途器具名称可选企业三个可选参数
     * @param years
     * @param districtId
     * @param purposeId
     * @param intrumentTypeId
     * @param generativeDepartmentId
     * @return
     */
    @Override
    public List<BackQualifiedInstrument> pageAllByYearAndDistrictAndParams(Long[] years, Long districtId, Long purposeId, Long intrumentTypeId, Long generativeDepartmentId) {
        logger.debug("格式化综合查询数据");
        Map<String, Object> map = new HashMap<>();

        map.put("purposeId", purposeId); // 用途
        map.put("intrumentTypeId", intrumentTypeId); // 器具名称id
        map.put("generativeDepartmentId", generativeDepartmentId); // 生产企业
        District district = districtRepository.findOne(districtId);// 当前区域
        List backQualifiedInstruments = new ArrayList();// 返回的对象集合
        if (!district.getDistrictType().getPinyin().equals("quxian")) { // 当区域为省市
            backQualifiedInstruments = this.findQualifiedInstrumentsShengShi(map, years, backQualifiedInstruments, districtId);
        } else  {
            backQualifiedInstruments = this.findQualifiedInstrumentsQuxian(map, years, backQualifiedInstruments, districtId);
        }
        return backQualifiedInstruments;
    }
    // 查找区域为省市级时的检定信息
    // 当查询单年时找到该区域的下级区域进行查询
    // 当前查询为多年时根据本级区域和年份查询
    public List<BackQualifiedInstrument> findQualifiedInstrumentsShengShi(Map map,Long[] years, List backQualifiedInstruments, Long districtId) {
        District district = districtRepository.findOne(districtId);// 当前区域
        map.put("department", null); // 当前方法查询不提供器具用户，设为空
        // 按单年分查找当前下级区域分类
        if (years.length == 1) {
            for (Long year : years) {
                map.put("year", year); // 年份
                List<District> sonDistricts = districtService.getClassOneSonListByDistrictId(districtId);// 找到下级区域
                for (District sonDistrict : sonDistricts) {
                    BackQualifiedInstrument backQualifiedInstrument = this.findQualifiedInstrumentByDistrict(sonDistrict, map, year);
                    backQualifiedInstruments.add(backQualifiedInstrument);
                }
            }
        } else { // 多年分查找当前区域
            for (Long year : years) {
                map.put("year", year); // 年份
                BackQualifiedInstrument backQualifiedInstrument = this.findQualifiedInstrumentByDistrict(district, map, year);
                backQualifiedInstruments.add(backQualifiedInstrument);
            }
        }
        return backQualifiedInstruments;
    }
    // 查找区域为区县级时的检定信息
    // 当查询单年时找到该区域的器具用户进行查询
    // 当前查询为多年时根据本级区域和年份查询
    public List<BackQualifiedInstrument> findQualifiedInstrumentsQuxian(Map map, Long[] years, List backQualifiedInstruments, Long districtId) {
        District district = districtRepository.findOne(districtId);// 当前区域
        if (years.length == 1) { // 如该区域类型为区县单年分找到该区县下的器具用户并进行查询
            List<Department> instrumentUser = departmentService.getAllInstrumentUserByDistrictId(districtId);
            for (Department department : instrumentUser) {
                map.put("department", department.getId()); // 器具用户
                for (Long year : years) {
                    map.put("year", year); // 年份
                    BackQualifiedInstrument backQualifiedInstrument = this.dataQuery(map, department, district, year);
                    backQualifiedInstruments.add(backQualifiedInstrument);
                }
            }
        } else { // 如该区域类型为区县多年查询，直接查找该区域
            for (Long year : years) {
                map.put("year", year); // 年份
                map.put("district", districtId);
                BackQualifiedInstrument backQualifiedInstrument = this.dataQuery(map, null, district, year);
                backQualifiedInstruments.add(backQualifiedInstrument);
            }
        }
        return backQualifiedInstruments;
    }

    /**
     * zhuchenshu
     * 鉴定合格率查询通过年份与区域和器具用户以及用途器具名称可选企业三个可选参数
     * @param years
     * @param districtId
     * @param departmentId
     * @param purposeId
     * @param intrumentTypeId
     * @param generativeDepartmentId
     * @return
     */
    @Override
    public List<BackQualifiedInstrument> pageAllByYearAndDistrictAndDepartmentParams(Long[] years, Long districtId, Long departmentId, Long purposeId, Long intrumentTypeId, Long generativeDepartmentId) {
        logger.debug("格式化综合查询数据");
        Map<String, Object> map = new HashMap<>();
        map.put("purposeId", purposeId); // 用途
        map.put("intrumentTypeId", intrumentTypeId); // 器具名称id
        map.put("generativeDepartmentId", generativeDepartmentId); // 生产企业
        map.put("department", departmentId); // 器具用户
        map.put("district", districtId); // 区域
        // 返回的对象集合
        List backQualifiedInstruments = new ArrayList();
        District district = districtRepository.findOne(districtId);
        // 按年分分类
        for (Long year : years) {
            map.put("year", year); // 年份
            Department department = departmentRepository.findOne(departmentId);

            BackQualifiedInstrument backQualifiedInstrument = this.dataQuery(map, department, district, year);

            backQualifiedInstruments.add(backQualifiedInstrument);
        }
        return backQualifiedInstruments;
    }

    /**
     * zhuchenshu
     * 将查询参数传入，返回查询结果
     * @param map
     * @param department
     * @param district
     * @return
     */
    public BackQualifiedInstrument dataQuery(Map map, Department department, District district, Long year) {
        logger.debug("综合查询");
        Specification specification = InstrumentCheckInfoSpecs.base(map);
        // 获得器具总数
        int sumInstrumentNumber = (int) instrumentCheckInfoRepository.count(specification);
        List<InstrumentCheckInfo> all = instrumentCheckInfoRepository.findAll(specification);
        // 获得合格数(找到跟节点，判断跟节点是否为合格)
        int qualifiedInstrumentNumber = 0;
        for (InstrumentCheckInfo instrumentCheckInfo : all) {
            CheckResult checkResult = instrumentCheckInfo.getCheckResult();
            CheckResult rootCheckResult = checkResultService.findRootCheckResult(checkResult);
            if (rootCheckResult.getPinyin().equals("hege")) {
                qualifiedInstrumentNumber++;
            }
        }
        BackQualifiedInstrument backQualifiedInstrument = new BackQualifiedInstrument(district, department, Math.toIntExact(year), sumInstrumentNumber, qualifiedInstrumentNumber);

        return backQualifiedInstrument;
    }
    // 获取相应区域的器具总数与合格数
    public BackQualifiedInstrument findQualifiedInstrumentByDistrict(District district, Map map, Long year) {
        // 判断当区域为区县直接按当前区域查询
        if (district.getDistrictType().getPinyin().equals("quxian")) {
            map.put("district", district.getId());// 区域
            BackQualifiedInstrument backQualifiedInstrument = this.dataQuery(map, null, district, year);
            return backQualifiedInstrument;
        } else if (district.getDistrictType().getPinyin().equals("shi")){ // 区域为市，查找该市的所有县的检定数和合格数
            List<District> xianDistricts = districtService.getClassOneSonListByDistrictId(district.getId());
            int sum = 0, qualified = 0;
            // 找到该市的检定器具总数和合格数
            Map<String, Integer> map1 = this.getShiQualified(xianDistricts, map);
            sum = map1.get("sum");
            qualified = map1.get("qualified");
            BackQualifiedInstrument backQualifiedInstrument = new BackQualifiedInstrument(district, null, Math.toIntExact(year), sum, qualified);
            return backQualifiedInstrument;
        } else { // 如果该区域为省，找到该省的所有县
            // 市级
            int shiSum = 0;
            int shiQualified = 0;
            int shengSum = 0;
            int shengQualified = 0;
            List<District> shiDistricts = districtService.getClassOneSonListByDistrictId(district.getId());
            // 获取该省的检定总数与和合格数
            for (District shiDistrict : shiDistricts) {
                // 找到该市的检定器具总数和合格数
                List<District> xianDistricts = districtService.getClassOneSonListByDistrictId(shiDistrict.getId());
                Map<String, Integer> map1 = this.getShiQualified(xianDistricts, map);
                shiSum = map1.get("sum");
                shiQualified = map1.get("qualified");
                shengSum += shiSum;
                shengQualified += shiQualified;
            }
            BackQualifiedInstrument backQualifiedInstrument = new BackQualifiedInstrument(district, null, Math.toIntExact(year), shengSum, shengQualified);
            return backQualifiedInstrument;
        }

    }
    // 获取市级的检定总数和合格数
    public Map getShiQualified(List<District> xianDistricts, Map map) {
        Map<String, Integer> map1 = new HashMap<>();
        int shiSum = 0;
        int shiQualified = 0;
        for (District xianDistrict : xianDistricts) {
            map.put("district", xianDistrict.getId());// 区域
            Specification specification = InstrumentCheckInfoSpecs.base(map);
            int xianSum = (int) instrumentCheckInfoRepository.count(specification);
            List<InstrumentCheckInfo> all = instrumentCheckInfoRepository.findAll(specification);
            int xianQualified = 0;
            for (InstrumentCheckInfo instrumentCheckInfo : all) {
                CheckResult checkResult = instrumentCheckInfo.getCheckResult();
                CheckResult rootCheckResult = checkResultService.findRootCheckResult(checkResult);
                if (rootCheckResult.getPinyin().equals("hege")) {
                    xianQualified++;
                }
            }
            shiSum += xianSum;
            shiQualified += xianQualified;
        }
        map1.put("sum", shiSum);
        map1.put("qualified", shiQualified);
        return map1;
    }
}

