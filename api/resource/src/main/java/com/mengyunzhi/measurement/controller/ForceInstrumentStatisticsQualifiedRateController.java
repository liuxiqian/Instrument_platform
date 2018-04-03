package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.ForceInstrumentStatisticsQualifiedRateService;
import com.mengyunzhi.measurement.jsonView.BackQualifiedInstrumentJsonView;
import com.mengyunzhi.measurement.output.BackQualifiedInstrument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * zhuchenshu
 * 强检器具检定合格率统计
 */
@RequestMapping("/ForceInstrumentStatisticsQualifiedRate")
@Api(tags = "ForceInstrumentStatisticsQualifiedRate 强检器具检定合格率统计", description = "强检器具检定合格率统计")
@RestController
public class ForceInstrumentStatisticsQualifiedRateController {
    @Autowired
    ForceInstrumentStatisticsQualifiedRateService forceInstrumentStatisticsQualifiedRateService;

    // 查询选择年度及1个区域主体的数据
    @GetMapping("/years/{year}/district/{district}")
    @JsonView(BackQualifiedInstrumentJsonView.class)
    @ApiOperation(value = "pageAllInstrumentByYearAndDistrict (查询选择年度及1个区域主体的数据)", notes = "查询选择年度及1个区域主体的数据", nickname = "pageAllInstrumentByYearAndDistrict")
    public List<BackQualifiedInstrument> pageAllInstrumentByYearAndDistrict(
            @ApiParam(value = "年份") @PathVariable Long[] year,
            @ApiParam(value = "区域") @PathVariable Long district,
            @ApiParam("用途") @RequestParam(value = "purpose", required = false) Long purposeId,
            @ApiParam("器具名称（类别）") @RequestParam(value = "intrumentType", required = false) Long intrumentTypeId,
            @ApiParam("生产企业") @RequestParam(value = "generativeDepartment", required = false) Long generativeDepartmentId) {
        return forceInstrumentStatisticsQualifiedRateService.pageAllByYearAndDistrictAndParams(year, district, purposeId, intrumentTypeId, generativeDepartmentId);
    }

    // 查询选择年度及1个区域主体和器具用户的数据
    @GetMapping("/years/{year}/district/{district}/department/{dapartmentId}")
    @JsonView(BackQualifiedInstrumentJsonView.class)
    @ApiOperation(value = "pageAllInstrumentByYearAndDistrictAndDepartment (查询选择年度及1个区域主体和器具用户的数据)", notes = "查询选择年度及1个区域主体和器具用户的数据", nickname = "pageAllInstrumentByYearAndDistrictAndDepartment")
    public List<BackQualifiedInstrument> pageAllInstrumentByYearAndDistrictAndDepartment(
            @ApiParam(value = "年份") @PathVariable Long[] year,
            @ApiParam(value = "区域") @PathVariable Long district,
            @ApiParam(value = "器具用户") @PathVariable Long dapartmentId,
            @ApiParam("用途") @RequestParam(value = "purpose", required = false) Long purposeId,
            @ApiParam("器具名称（类别）") @RequestParam(value = "intrumentType", required = false) Long intrumentTypeId,
            @ApiParam("生产企业") @RequestParam(value = "generativeDepartment", required = false) Long generativeDepartmentId) {
        return forceInstrumentStatisticsQualifiedRateService.pageAllByYearAndDistrictAndDepartmentParams(year, district, dapartmentId, purposeId, intrumentTypeId, generativeDepartmentId);
    }
}
