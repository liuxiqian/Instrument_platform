package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.output.InstrumentCheckedRateView;
import com.mengyunzhi.measurement.repository.InstrumentCheckedRateRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentRepository;
import com.mengyunzhi.measurement.specs.InstrumentCheckedRateSpecs;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

/**
 * 器具受检率统计 service实现类
 *
 * @Author 朴世超
 */
@Service
public class InstrumentCheckedRateServiceImpl implements InstrumentCheckedRateService {
    private static final int onceDataNum = 1000;    // 最小分组数
    private static final Logger logger = Logger.getLogger(InstrumentCheckedRateServiceImpl.class);
    @Autowired
    private InstrumentCheckedRateRepository instrumentCheckedRateRepository;    // 器具受检率统计
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired
    private PurposeService purposeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private MandatoryInstrumentTypeService mandatoryInstrumentTypeService;
    @Autowired
    private ApplianceUserDepartmentService applianceUserDepartmentService;

    @Override
    public void clonePreYearForCurrentYear() {
        // 获取当前年份,减一为上一年份
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);  // 当前年份
        int preYear = currentYear - 1;                  // 上一年份

        Boolean isLastPage = false;     // 最后一页
        int page = 0;                   // 分组

        while (!isLastPage) {
            // 用来限定每组1000条数据
            PageRequest pageRequest = new PageRequest(page, onceDataNum);
            // 获取一组对象并加进数组中
            Page<InstrumentCheckedRate> preInstrumentCheckedRate = instrumentCheckedRateRepository.findAllByYear((short) preYear, pageRequest);

            // 当前页为最后一页
            if (preInstrumentCheckedRate.isLast()) {
                isLastPage = true;
            }
            page++;         // 组号自增,获取下一组

            // 创建一个数组,存放当前年份
            List<InstrumentCheckedRate> instrumentCheckedRateList = new ArrayList<>();

            // clone
            for (InstrumentCheckedRate instrumentCheckedRate : preInstrumentCheckedRate) {
                // 新建一个对象
                InstrumentCheckedRate newInstrumentCheckedRate = instrumentCheckedRate.clone();
                // 设置年份为当前年份
                newInstrumentCheckedRate.setYear((short) currentYear);
                // 设置受检总数为0
//                newInstrumentCheckedRate.setCheckedTotalCount(0);
                instrumentCheckedRateList.add(newInstrumentCheckedRate);
            }

            // 当前年份数组持久化
            instrumentCheckedRateRepository.save(instrumentCheckedRateList);

        }
    }

    /**
     * 数据查询思想  zhangxishuo
     * 目标：在InstrumentCheckedRate表中拼出所有的字段
     * 1.获取所有状态为正常的强检器具
     * 2.获取该强检器具关联的器具用户，器具用户属于某区域(省/市/县)
     * (虽然使用省/市/县为查询条件，但是与器具关联的基本单位是器具用户，有了器具用户这些数据就有了)
     * 3.递归获取省/市/县信息
     * 4.获取所有相关联的检定信息
     * (强检器具关联器具检定信息)
     * 5.循环从器具生产到当前年份，判断所关联的检定信息中合格的信息的创建时间是否为当前循环年份
     * (如果是，表示该器具该年检定过，反之则未检定)
     * 6.前台请求
     * 6.1后台根据条件返回符合条件的记录数(即：器具总数)
     * 6.2后台根据条件返回符合条件且受检状态为true的记录数(即：受检总数)
     * (年度/省/市/县/器具用户/用途/器具名称)都是前台查询时的参数
     * 无需从后台获取，直接在用户选择之后再前台循环显示即可
     */
    @Override
    public void generateInstrumentCheckedInfo() {

        logger.info("删除当年所有数据");
        int currentYear = this.getCurrentYear();
        List<InstrumentCheckedRate> primaryCheckedRateList = instrumentCheckedRateRepository.findAllByYear((short) currentYear);
        instrumentCheckedRateRepository.delete(primaryCheckedRateList);

        logger.info("生成的该年所有数据");
        this.generateCurrentYearAllData(currentYear);
    }

    /**
     * 获取当前年份
     * zhangxishuo
     */
    private int getCurrentYear() {

        logger.info("获取一个日历实例，并返回当前年份");
        Calendar currentCalendar = Calendar.getInstance();
        return currentCalendar.get(Calendar.YEAR);
    }

    /**
     * 生成该年的所有信息
     * @param currentYear  当前年份
     * zhangxishuo
     */
    private void generateCurrentYearAllData(int currentYear) {

        logger.info("初始化数组");
        List<InstrumentCheckedRate> instrumentCheckedRateList = new ArrayList<>();

        logger.info("初始化查询数据");
        boolean isLastPage = false;   // 是否为最后一页
        int page = 0;                 // 当前页

        logger.info("如果不是最后一页，循环");
        while (!isLastPage) {

            logger.info("新建分页信息，并查询数据");
            PageRequest pageRequest = new PageRequest(page, onceDataNum);
            Page<MandatoryInstrument> mandatoryInstrumentPage = mandatoryInstrumentRepository.findAllByStatus(MandatoryInstrument.STATUS_NORMAL, pageRequest);

            logger.info("如果为最后一页，设置为true");
            if (mandatoryInstrumentPage.isLast()) {
                isLastPage = true;
            }

            logger.info("页数自增");
            page ++;

            logger.info("获取分页内容");
            List<MandatoryInstrument> mandatoryInstrumentPageContent = mandatoryInstrumentPage.getContent();

            logger.info("循环添加数据");
            for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentPageContent) {

                logger.info("获取当前强检器具当前年份的检定率");
                InstrumentCheckedRate instrumentCheckedRate = this.generateAllInfo(currentYear, mandatoryInstrument);

                logger.info("将该数组添加到检定率数组中");
                instrumentCheckedRateList.add(instrumentCheckedRate);
            }

            logger.info("持久化数据");
            instrumentCheckedRateRepository.save(instrumentCheckedRateList);
        }
    }

    /**
     * 生成所有与该强检器具相关的检定率信息
     *
     * @param mandatoryInstrument 强检器具
     * @return 含有所有信息的强检器具检定率实体
     * zhangxishuo
     */
    private InstrumentCheckedRate generateAllInfo(int currentYear, MandatoryInstrument mandatoryInstrument) {

        logger.info("新建一个器具检定率对象");
        InstrumentCheckedRate instrumentCheckedRate = new InstrumentCheckedRate();

        logger.info("设置年份");
        instrumentCheckedRate.setYear((short) currentYear);

        logger.info("设置器具名称");
        instrumentCheckedRate.setInstrumentType(mandatoryInstrument.getInstrumentType());

        logger.info("设置用途");
        instrumentCheckedRate.setPurpose(mandatoryInstrument.getPurpose());

        logger.info("设置器具用户");
        instrumentCheckedRate.setInstrumentUserDepartment(mandatoryInstrument.getDepartment());

        logger.info("设置区域信息");
        this.setDistrictInfo(instrumentCheckedRate, mandatoryInstrument.getDepartment().getDistrict());

        logger.info("设置该年是否检定");
        this.setIsChecked(instrumentCheckedRate, mandatoryInstrument.getLastInstrumentCheckInfo());

        logger.info("返回");
        return instrumentCheckedRate;
    }

    /**
     * 根据强检器具设置器具检定率的区域信息(省/市/县)
     *
     * @param instrumentCheckedRate 待设置的器具检定率对象
     * @param district              器具用户所属的区域
     *                              zhangxishuo
     *                              注：这个方法的实现不好，不具有扩展性，如果又有了新的区域类型这个方法就不生效了
     */
    private void setDistrictInfo(InstrumentCheckedRate instrumentCheckedRate, District district) {
        if (district.getParentDistrict() == null) {

            logger.info("如果为省区域，设置省");
            instrumentCheckedRate.setProvinceDistrict(district);
        } else if (district.getParentDistrict().getParentDistrict() == null) {

            logger.info("如果为市区域，设置市、省");
            instrumentCheckedRate.setCityDistrict(district);
            instrumentCheckedRate.setProvinceDistrict(district.getParentDistrict());
        } else {

            logger.info("如果为区县区域，设置县、市、省");
            instrumentCheckedRate.setCountyDistrict(district);
            instrumentCheckedRate.setCityDistrict(district.getParentDistrict());
            instrumentCheckedRate.setProvinceDistrict(district.getParentDistrict().getParentDistrict());
        }
    }

    /**
     * 根据强检器具->最近一次检定记录   设置检定率该年是否受检信息
     *
     * @param instrumentCheckedRate 待设置的器具检定率对象
     * @param instrumentCheckInfo   强检器具最近检定记录
     *                              zhangxishuo
     */
    private void setIsChecked(InstrumentCheckedRate instrumentCheckedRate, InstrumentCheckInfo instrumentCheckInfo) {

        logger.info("如果该强检器具有最近检定记录");
        if (instrumentCheckInfo != null) {

            logger.info("获取最近一次检定日期");
            Date lastCheckDate = instrumentCheckInfo.getCheckDate();

            logger.info("将日期转换为日历");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastCheckDate);

            logger.info("获取最近检定年份");
            int lastCheckYear = calendar.get(Calendar.YEAR);

            logger.info("如果该器具最近一次检定年份为当前年份，则受检字段设置为true");
            if (lastCheckYear == instrumentCheckedRate.getYear()) {
                instrumentCheckedRate.setChecked(true);
            }
        }
    }

    /**
     * 多年根据对象获取检定率信息
     * @param years                    多年
     * @param province                 省
     * @param city                     市
     * @param county                   县
     * @param instrumentUserDepartment 器具用户
     * @param purpose                  用途
     * @param instrumentType           器具类别
     * @return List<InstrumentCheckedRateView>  强检器具检定率信息数组
     * zhangxishuo
     */
    @Override
    public List<InstrumentCheckedRateView> getAllCheckedRate(List<Short> years, District province, District city, District county, Department instrumentUserDepartment, Purpose purpose, InstrumentType instrumentType) {

        logger.info("初始化数组");
        List<InstrumentCheckedRateView> instrumentCheckedRateViewList = new ArrayList<>();

        logger.info("添加年份之外的查询条件");
        Map<String, Object> map = new HashMap<>();
        map.put("provinceDistrict", province);
        map.put("cityDistrict", city);
        map.put("countyDistrict", county);
        map.put("instrumentUserDepartment", instrumentUserDepartment);
        map.put("purpose", purpose);
        map.put("instrumentType", instrumentType);

        for (Short year : years) {

            logger.info("为map添加year条件");
            map.put("year", year);

            logger.info("查询检定率信息");
            InstrumentCheckedRateView instrumentCheckedRateView = this.getInstrumentCheckedViewByMap(map);

            instrumentCheckedRateViewList.add(instrumentCheckedRateView);
        }

        return instrumentCheckedRateViewList;
    }

    /**
     * 多年根据id查询检定率信息
     * @param years                      多年
     * @param districtId                 区域id
     * @param instrumentUserDepartmentId 器具用户id
     * @param purposeId                  用途id
     * @param instrumentTypeId           器具类别id
     * @return
     * zhangxishuo
     */
    @Override
    public List<InstrumentCheckedRateView> getAllCheckedRate(List<Short> years, Long districtId, Long instrumentUserDepartmentId, Long purposeId, Long instrumentTypeId) {

        logger.info("初始化通用查询条件");
        District province = null;
        District city = null;
        District county = null;
        Department instrumentUserDepartment = null;
        Purpose purpose = null;
        InstrumentType instrumentType = null;

        logger.info("设置区域/器具用户信息");
        District district = districtService.get(districtId);
        if (null != instrumentUserDepartmentId) {
            logger.info("传入了器具用户");
            instrumentUserDepartment = applianceUserDepartmentService.getOneOfDepartmentId(instrumentUserDepartmentId);
            county = instrumentUserDepartment.getDistrict();
            city = county.getParentDistrict();
            province = city.getParentDistrict();
        } else if (null == district.getParentDistrict()) {
            logger.info("传入地区为省");
            province = district;
        } else if (null == district.getParentDistrict().getParentDistrict()) {
            logger.info("传入地区为市");
            city = district;
            province = city.getParentDistrict();
        } else {
            logger.info("传入地区为区县");
            county = district;
            city = county.getParentDistrict();
            province = city.getParentDistrict();
        }

        logger.info("设置通用信息");
        if (null != purposeId) {
            purpose = purposeService.getPurposeById(purposeId);
        }
        if (null != instrumentTypeId) {
            instrumentType = mandatoryInstrumentTypeService.get(instrumentTypeId);
        }

        return this.getAllCheckedRate(years, province, city, county, instrumentUserDepartment, purpose, instrumentType);
    }

    /**
     * 单年 根据对象查询检定率数据
     * @param year                       单年
     * @param province                   省
     * @param city                       市
     * @param county                     区县
     * @param purpose                    用途
     * @param instrumentType             器具类别
     * @return
     * zhangxishuo
     */
    @Override
    public List<InstrumentCheckedRateView> getAllCheckedRate(Short year, District province, District city, District county, Purpose purpose, InstrumentType instrumentType) {

        List<InstrumentCheckedRateView> instrumentCheckedRateViewList = new ArrayList<>();

        logger.info("初始化查询条件");
        Map<String, Object> map = new HashMap<>();
        map.put("year", year);
        map.put("purpose", purpose);
        map.put("instrumentType", instrumentType);

        logger.info("设置区域查询条件");
        if (null != county) {
            map.put("provinceDistrict", province);
            map.put("cityDistrict", city);
            map.put("countyDistrict", county);
            // 查询所有器具用户
            for (Department instrumentUserDepartment : applianceUserDepartmentService.getAllInstrumentUserByDistrictId(county.getId())) {
                map.put("instrumentUserDepartment", instrumentUserDepartment);

                logger.info("查询数据");
                InstrumentCheckedRateView instrumentCheckedRateView = this.getInstrumentCheckedViewByMap(map);

                instrumentCheckedRateViewList.add(instrumentCheckedRateView);
            }
        } else if (null != city) {
            map.put("provinceDistrict", province);
            map.put("cityDistrict", city);
            // 查询所有区县
            for (District countyDistrict: districtService.getClassOneSonListByDistrict(city)) {
                map.put("countyDistrict", countyDistrict);

                logger.info("查询数据");
                InstrumentCheckedRateView instrumentCheckedRateView = this.getInstrumentCheckedViewByMap(map);

                instrumentCheckedRateViewList.add(instrumentCheckedRateView);
            }
        } else {
            map.put("provinceDistrict", province);
            // 查询所有市
            for (District cityDistrict: districtService.getClassOneSonListByDistrict(province)) {
                map.put("cityDistrict", cityDistrict);

                logger.info("查询数据");
                InstrumentCheckedRateView instrumentCheckedRateView = this.getInstrumentCheckedViewByMap(map);

                instrumentCheckedRateViewList.add(instrumentCheckedRateView);
            }
        }

        return instrumentCheckedRateViewList;
    }

    /**
     * 单年 根据id查询检定率信息
     * @param year                       年份
     * @param districtId                 区域id
     * @param purposeId                  用途id
     * @param instrumentTypeId           器具类别id
     * @return
     * zhangxishuo
     */
    @Override
    public List<InstrumentCheckedRateView> getAllCheckedRate(Short year, Long districtId, Long purposeId, Long instrumentTypeId) {

        logger.info("初始化查询条件");
        District province = null;
        District city = null;
        District county = null;
        Purpose purpose = null;
        InstrumentType instrumentType = null;

        logger.info("获取区域信息");
        District district = districtService.get(districtId);
        if (null == district.getParentDistrict()) {
            logger.info("父地区为省");
            province = district;
        } else if (null == district.getParentDistrict().getParentDistrict()) {
            logger.info("父地区为市");
            city = district;
            province = city.getParentDistrict();
        } else {
            logger.info("父地区为区县");
            county = district;
            city = county.getParentDistrict();
            province = city.getParentDistrict();
        }

        logger.info("设置通用信息");
        if (null != purposeId) {
            purpose = purposeService.getPurposeById(purposeId);
        }
        if (null != instrumentTypeId) {
            instrumentType = mandatoryInstrumentTypeService.get(instrumentTypeId);
        }

        return this.getAllCheckedRate(year, province, city, county, purpose, instrumentType);
    }

    /**
     * 根据条件获取器具检定信息
     * @param map    查询条件
     * @return       器具检定信息
     * zhangxishuo
     */
    private InstrumentCheckedRateView getInstrumentCheckedViewByMap(Map<String, Object> map) {

        logger.info("获取相关信息的器具总数和受检总数");
        int instrumentTotal = this.countByMapAndIsOnlyChecked(map, false);
        int checkedTotal = this.countByMapAndIsOnlyChecked(map, true);

        logger.info("将信息转换为InstrumentCheckedRateView对象");
        InstrumentCheckedRateView instrumentCheckedRateView = new InstrumentCheckedRateView(map, instrumentTotal, checkedTotal);

        return instrumentCheckedRateView;
    }

    /**
     * 根据查询条件与是否只返回已检定的数据返回数据条数
     * @param map            查询条件
     * @param isOnlyChecked  是否只返回已检定的数据条数
     * @return int           符合查询要求的数据条数
     * zhangxishuo
     */
    private int countByMapAndIsOnlyChecked(Map<String, Object> map, boolean isOnlyChecked) {

        logger.info("获取查询规格");
        Specification<InstrumentCheckedRate> specification = InstrumentCheckedRateSpecs.base(isOnlyChecked, map);;

        logger.info("根据条件查询数据");
        List<InstrumentCheckedRate> instrumentCheckedRateList = instrumentCheckedRateRepository.findAll(specification);
        return instrumentCheckedRateList.size();
    }
}
