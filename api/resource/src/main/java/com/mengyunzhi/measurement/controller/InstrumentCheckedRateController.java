package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.jsonView.InstrumentCheckedRateJsonView;
import com.mengyunzhi.measurement.output.InstrumentCheckedRateView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxishuo on 2018/3/15
 */
@Api(tags = "InstrumentCheckedRate 强检器具受检率",description = "强检器具受检率")
@RestController
@RequestMapping("/InstrumentCheckedRate")
public class InstrumentCheckedRateController {

    @Autowired
    private InstrumentCheckedRateService instrumentCheckedRateService;      // 检定率

    /**
     * 可以绑定一个数组在url中
     * url中数组以,分隔开
     * 参考：https://www.jianshu.com/p/7bf18b6d68d4
     */
    @JsonView(InstrumentCheckedRateJsonView.getAllCheckedRateByYears.class)
    @ApiOperation(value = "getAllCheckedRate (查询多个年度的所有数据)", notes = "查询多个年度的所有数据", nickname = "getAllCheckedRate")
    @GetMapping("/getAllCheckedRate/years/{years}/districtId/{districtId}")
    public List<InstrumentCheckedRateView> getAllCheckedRate(@ApiParam(value = "年度") @PathVariable List<Short> years,
                                                                 @ApiParam(value = "区域id") @PathVariable Long districtId,
                                                                 @ApiParam(value = "器具用户id") @RequestParam(required = false) Long instrumentUserDepartmentId,
                                                                 @ApiParam(value = "用途id") @RequestParam(required = false) Long purposeId,
                                                                 @ApiParam(value = "器具名称id") @RequestParam(required = false) Long instrumentTypeId) {

        return instrumentCheckedRateService.getAllCheckedRate(years, districtId, instrumentUserDepartmentId, purposeId, instrumentTypeId);
    }

    @JsonView(InstrumentCheckedRateJsonView.getAllCheckedRateByYear.class)
    @ApiOperation(value = "getAllCheckedRate (查询单个年度的所有数据)", notes = "查询单个年度的所有数据", nickname = "getAllCheckedRate")
    @GetMapping("/getAllCheckedRate/year/{year}/districtId/{districtId}")
    public List<InstrumentCheckedRateView> getAllCheckedRate(@ApiParam(value = "单年度") @PathVariable Short year,
                                                                 @ApiParam(value = "区域id") @PathVariable Long districtId,
                                                                 @ApiParam(value = "用途id") @RequestParam(required = false) Long purposeId,
                                                                 @ApiParam(value = "器具名称id") @RequestParam(required = false) Long instrumentTypeId) {
        return instrumentCheckedRateService.getAllCheckedRate(year, districtId, purposeId, instrumentTypeId);
    }
}
