package com.mengyunzhi.measurement.controller;

/**
 * zhuchenshu
 */

import com.mengyunzhi.measurement.Service.DistrictService;
import com.mengyunzhi.measurement.Service.InstrumentCheckInfoService;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentServiceImpl;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.InstrumentCheckInfo;
import com.mengyunzhi.measurement.repository.InstrumentCheckInfoRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ForceInstrumentStatisticsQualifiedRateControllerTest extends ControllerTest  {
    private Logger logger = Logger.getLogger(MandatoryInstrumentServiceImpl.class.getName());
    @Autowired
    InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    @Qualifier("InstrumentCheckInfoService")
    @Autowired
    InstrumentCheckInfoService instrumentCheckInfoService;
    @Autowired
    DistrictService districtService;
    @Test
    public void pageAllInstrumentByYearAndDistrict() throws Exception {
        logger.debug("创建查询参数");
        logger.debug("创建一个强检器具检定记录");
        District district = districtService.getOneSavedDistrict();
        district.getDistrictType().setPinyin("shi");

        InstrumentCheckInfo instrumentCheckInfo = instrumentCheckInfoService.getOneSavedInstrumentCheckInfo();
        instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getDistrictType().setPinyin("quxian");
        instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().setParentDistrict(district);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
        logger.debug("提取测试所需的查找条件,年份，区域，用途, 器具名称, 生产企业");
        String years = Long.valueOf(instrumentCheckInfo.getCheckYear()).toString();
        Long districtId = instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getId();
        Long purposeId = instrumentCheckInfo.getMandatoryInstrument().getPurpose().getId();
        Long intrumentTypeId = instrumentCheckInfo.getMandatoryInstrument().getId();
        Long generativeDepartmentId = instrumentCheckInfo.getMandatoryInstrument().getGenerativeDepartment().getId();

        this.mockMvc.perform(get("/ForceInstrumentStatisticsQualifiedRate/years/" + years +"/district/" + districtId)
                        .param("purposeId", purposeId.toString())
                        .param("intrumentTypeId", intrumentTypeId.toString())
                        .param("generativeDepartmentId", generativeDepartmentId.toString())
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void pageAllInstrumentByYearAndDistrictAndDepartment() throws Exception {
        logger.debug("创建查询参数");
        logger.debug("创建一个强检器具检定记录");
        InstrumentCheckInfo instrumentCheckInfo = instrumentCheckInfoService.getOneSavedInstrumentCheckInfo();
        instrumentCheckInfo.getCheckResult().setPinyin("hege");
        instrumentCheckInfo.getCheckResult().setParentCheckResult(null);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
        logger.debug("提取测试所需的查找条件,年份，区域，用途, 器具名称, 生产企业");
        String years = Long.valueOf(instrumentCheckInfo.getCheckYear()).toString() + "," + Long.valueOf(instrumentCheckInfo.getCheckYear()).toString() ;
        Long districtId = instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getId();
        Long departmentId = instrumentCheckInfo.getMandatoryInstrument().getDepartment().getId();
        Long purposeId = instrumentCheckInfo.getMandatoryInstrument().getPurpose().getId();
        Long intrumentTypeId = instrumentCheckInfo.getMandatoryInstrument().getId();
        Long generativeDepartmentId = instrumentCheckInfo.getMandatoryInstrument().getGenerativeDepartment().getId();

        this.mockMvc.perform(get("/ForceInstrumentStatisticsQualifiedRate/years/" + years +"/district/" + districtId + "/department/" + departmentId)
                .param("purposeId", purposeId.toString())
                .param("intrumentTypeId", intrumentTypeId.toString())
                .param("generativeDepartmentId", generativeDepartmentId.toString())
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}