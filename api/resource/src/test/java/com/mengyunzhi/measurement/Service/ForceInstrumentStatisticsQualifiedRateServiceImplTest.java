package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.InstrumentCheckInfo;
import com.mengyunzhi.measurement.output.BackQualifiedInstrument;
import com.mengyunzhi.measurement.repository.InstrumentCheckInfoRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.transaction.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class ForceInstrumentStatisticsQualifiedRateServiceImplTest extends ServiceTest{
    private Logger logger = Logger.getLogger(MandatoryInstrumentServiceImpl.class.getName());
    @Autowired
    DistrictService districtService;
    @Autowired
    InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    @Qualifier("InstrumentCheckInfoService")
    @Autowired
    InstrumentCheckInfoService instrumentCheckInfoService;
    @Autowired
    ForceInstrumentStatisticsQualifiedRateService forceInstrumentStatisticsQualifiedRateService;
    @Qualifier("DepartmentService")
    @Autowired
    DepartmentService departmentService;
    @Test
    @Transactional
    public void pageAllByYearAndDistrictAndParams() {
        Calendar calendar = Calendar.getInstance();
        District district = districtService.getOneSavedDistrict();
        district.getDistrictType().setPinyin("shi");

        logger.debug("创建强检器具检定记录");
        InstrumentCheckInfo instrumentCheckInfo = instrumentCheckInfoService.getOneSavedInstrumentCheckInfo();
        instrumentCheckInfo.getCheckResult().setPinyin("hege");
        instrumentCheckInfo.getCheckResult().setParentCheckResult(null);
        instrumentCheckInfo.setCheckDate(new Date(calendar.getTimeInMillis()));
        instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getDistrictType().setPinyin("quxian");
        instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().setParentDistrict(district);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);

        calendar.add(Calendar.YEAR, -1);
        InstrumentCheckInfo instrumentCheckInfo1 = instrumentCheckInfoService.getOneSavedInstrumentCheckInfo();
        instrumentCheckInfo1.getCheckResult().setPinyin("hege");
        instrumentCheckInfo1.getCheckResult().setParentCheckResult(null);
        instrumentCheckInfo1.setCheckDate(new Date(calendar.getTimeInMillis()));
        instrumentCheckInfo1.getMandatoryInstrument().getDepartment().getDistrict().getDistrictType().setPinyin("quxian");
        instrumentCheckInfo1.getMandatoryInstrument().getDepartment().getDistrict().setParentDistrict(district);
        instrumentCheckInfoRepository.save(instrumentCheckInfo1);

        logger.debug("提取测试所需的查找条件,年份，区域，用途, 器具名称, 生产企业");
        Long[] year = {Long.valueOf(instrumentCheckInfo.getCheckYear()), Long.valueOf(instrumentCheckInfo1.getCheckYear())};
        Long districtId = instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getId();
        Long purposeId = null;
        Long intrumentTypeId = null;
        Long generativeDepartmentId = null;

        logger.debug("多年区县查询");
        List<BackQualifiedInstrument> instrumentCheckInfos = forceInstrumentStatisticsQualifiedRateService.pageAllByYearAndDistrictAndParams(year, districtId, purposeId, intrumentTypeId, generativeDepartmentId);
        logger.debug("断言查询结果");
        assertThat(instrumentCheckInfos.get(0).getSum()).isEqualTo(1);
        assertThat(instrumentCheckInfos.get(0).getQualifiedNumber()).isEqualTo(1);
    }

    @Test
    public void pageAllByYearAndDistrictAndDepartmentParams() {
        District district = districtService.getOneSavedDistrict();
        Department department = departmentService.getOneSavedDepartment();
        Calendar calendar = Calendar.getInstance();

        logger.debug("创建强检器具检定记录");
        InstrumentCheckInfo instrumentCheckInfo = instrumentCheckInfoService.getOneSavedInstrumentCheckInfo();
        instrumentCheckInfo.getCheckResult().setPinyin("hege"); // 检定结果合格
        instrumentCheckInfo.getCheckResult().setParentCheckResult(null);
        instrumentCheckInfo.setCheckDate(new Date(calendar.getTimeInMillis()));
        instrumentCheckInfo.getMandatoryInstrument().setDepartment(department);
        instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().setParentDistrict(district);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);

        InstrumentCheckInfo instrumentCheckInfo1 = instrumentCheckInfoService.getOneSavedInstrumentCheckInfo();
        instrumentCheckInfo1.getCheckResult().setPinyin("hege");
        instrumentCheckInfo1.getCheckResult().setParentCheckResult(null);
        calendar.add(Calendar.YEAR, -1);
        instrumentCheckInfo1.setCheckDate(new Date(calendar.getTimeInMillis()));
        instrumentCheckInfo1.getMandatoryInstrument().setDepartment(department);
        instrumentCheckInfo1.getMandatoryInstrument().getDepartment().getDistrict().setParentDistrict(district);
        instrumentCheckInfoRepository.save(instrumentCheckInfo1);

        logger.debug("提取测试所需的查找条件,年份，区域，用途, 器具名称, 生产企业");
        Long[] year = {Long.valueOf(instrumentCheckInfo.getCheckYear()), Long.valueOf(instrumentCheckInfo1.getCheckYear())};
        Long districtId = instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getId();
        Long departmentId = instrumentCheckInfo.getMandatoryInstrument().getDepartment().getId();
        Long purposeId = null;
        Long intrumentTypeId = null;
        Long generativeDepartmentId = null;

        logger.debug("根据条件进行查询");
        List<BackQualifiedInstrument> instrumentCheckInfos = forceInstrumentStatisticsQualifiedRateService.pageAllByYearAndDistrictAndDepartmentParams(year, districtId, departmentId, purposeId, intrumentTypeId, generativeDepartmentId);
        logger.debug("断言查询结果");
        assertThat(instrumentCheckInfos.size()).isEqualTo(2);
    }
}