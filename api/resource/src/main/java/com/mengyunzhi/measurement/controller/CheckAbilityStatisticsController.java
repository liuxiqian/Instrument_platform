package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.CheckAbilityStatisticsService;
import com.mengyunzhi.measurement.entity.CheckAbilityStatistics;
import com.mengyunzhi.measurement.output.CheckAbilityStatisticsView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.*;

/**
 * 器具检定能力统计
 *
 * @Author poshichao
 */
@Api(tags = "CheckAbilityStatistics 器具检定能力统计", description = "器具检定能力统计")
@RestController
@RequestMapping("/CheckAbilityStatistics")
public class CheckAbilityStatisticsController {
    private final CheckAbilityStatisticsService checkAbilityStatisticsService;    // 器具检定能力

    @Autowired
    public CheckAbilityStatisticsController(CheckAbilityStatisticsService checkAbilityStatisticsService) {
        this.checkAbilityStatisticsService = checkAbilityStatisticsService;
    }

    @ApiOperation(value = "getAllBySpecification 获取所有器具检定能力信息", notes = "获取所有器具检定能力信息", nickname = "CheckAbilityStatistics_getAllBySpecification")
    @GetMapping("/getAllBySpecification")
    public List<CheckAbilityStatistics> getAllBySpecification(
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "器具类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "测量范围下限权重") @RequestParam(name = "minMeasureScaleWeight", required = false) Integer minMeasureScaleWeight,
            @ApiParam(value = "测量范围上限权重") @RequestParam(name = "maxMeasureScaleWeight", required = false) Integer maxMeasureScaleWeight) {

        return checkAbilityStatisticsService.getAllBySpecification(districtId, disciplineId, instrumentTypeId, minMeasureScaleWeight, maxMeasureScaleWeight);
    }

    @ApiOperation(value = "dataInit 添加器具检定能力", notes = "添加器具检定能力(该接口只作为开发使用)", nickname = "CheckAbilityStatistics_dataInit")
    @GetMapping("/dataInit")
    public void dataInit() {
        checkAbilityStatisticsService.asyncDataInit();
    }

    @ApiOperation(value = "hasFinished 判断线程是否执行完毕", notes = "判断线程是否执行完毕", nickname = "CheckAbilityStatistics_hasFinished")
    @GetMapping("/hasFinished")
    public Boolean hasFinished() {
        // 变量为false, 说明函数执行完了,线程中没有正在执行的函数
        return checkAbilityStatisticsService.hasFinished();
    }

    @ApiOperation(value = "getAllByDistrictAndDiscipline 通过区域和学科类别获取所有的器具检定能力结果", notes = "通过区域和学科类别获取所有的器具检定能力结果", nickname = "CheckAbilityStatistics_getAllByDistrictAndDiscipline")
    @GetMapping("/getAllByDistrictAndDiscipline")
    public List<CheckAbilityStatisticsView> getAllByDistrictAndDiscipline(
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "学科类别Id") @RequestParam(name = "disciplineId", required = false) Long disciplineId) {
        List<CheckAbilityStatisticsView> checkAbilityStatisticsViewList = checkAbilityStatisticsService.getAllByDistrictAndDiscipline(districtId, disciplineId);
        return checkAbilityStatisticsViewList;
    }

    @ApiOperation(value = "getAllByDistrictAndInstrumentType 通过区域和器具类别获取所有的器具检定能力结果", notes = "通过区域和器具类别获取所有的器具检定能力结果", nickname = "CheckAbilityStatistics_getAllByDistrictAndInstrumentType")
    @GetMapping("/getAllByDistrictAndInstrumentType")
    public List<CheckAbilityStatisticsView> getAllByDistrictAndInstrumentType(
            @ApiParam(value = "区域Id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "instrumentId") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId) {
        List<CheckAbilityStatisticsView> checkAbilityStatisticsViewList = checkAbilityStatisticsService.getAllByDistrictAndInstrumentType(districtId, instrumentTypeId);
        return checkAbilityStatisticsViewList;
    }
}
