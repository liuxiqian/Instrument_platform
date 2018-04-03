package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentWorkService;
import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.Work;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentWorkJsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;


/**
 * Created by panjie on 17/7/16.
 * 强制检定器具申请 工作
 */
@RestController
@RequestMapping("/MandatoryInstrumentWork")
@Api(tags = "MandatoryInstrumentWorkSpecs 强制检定器具申请工作", description = "强制检定器具申请工作")
public class MandatoryInstrumentWorkController {
    @Autowired
    protected MandatoryInstrumentWorkService mandatoryInstrumentWorkService; // 强制检定工作

    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    @GetMapping("/pageOfCurrentLoginUser")
    @ApiOperation(value = "pageOfCurrentLoginUser 分页信息", nickname = "MandatoryInstrumentWork_pageOfCurrentLoginUser")
    public Page<Work> pageOfCurrentLoginUser(
            @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<Work> works = mandatoryInstrumentWorkService.pageOfCurrentLoginUser(pageable);
        return works;
    }

    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    @GetMapping("/pageAllOfCurrentLoginUserBySpecification")
    @ApiOperation(value = "pageAllOfCurrentLoginUserBySpecification 当前用户的查询信息", nickname = "MandatoryInstrumentWork_pageAllOfCurrentLoginUserBySpecification")
    public Page<CurrentWork> pageAllOfCurrentLoginUserBySpecification(
            @ApiParam(value = "器具用户id") @RequestParam(name = "departmentId", required = false) Long departmentId,
            @ApiParam(value = "起始申请日期") @RequestParam(name = "startApplyDate", required = false) Date applyStartDate,
            @ApiParam(value = "终止申请日期") @RequestParam(name = "endApplyDate", required = false)  Date applyEndCalDate,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentWorkService.pageAllOfCurrentLoginUserBySpecification(departmentId, applyStartDate, applyEndCalDate, pageable);
    }


    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    @GetMapping("/pageOfCurrentLoginUserOfToDo")
    @ApiOperation(value = "pageOfCurrentLoginUserOfToDo 分页信息", nickname = "MandatoryInstrumentWork_pageOfCurrentLoginUserOfToDo")
    public Page<Work> pageOfCurrentLoginUserOfToDo(@SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<Work> works = mandatoryInstrumentWorkService.pageOfCurrentLoginUserOfToDo(pageable);
        return works;
    }

    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    @GetMapping("/pageOfCurrentLoginUserOfDoing")
    @ApiOperation(value = "pageOfCurrentLoginUserOfDoing 分页信息", nickname = "MandatoryInstrumentWork_pageOfCurrentLoginUserOfDoing")
    public Page<Work> pageOfCurrentLoginUserOfDoing(@SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<Work> works = mandatoryInstrumentWorkService.pageOfCurrentLoginUserOfDoing(pageable);
        return works;
    }

    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    @GetMapping("/pageOfCurrentLoginUserOfDone")
    @ApiOperation(value = "pageOfCurrentLoginUserOfDone 分页信息", nickname = "MandatoryInstrumentWork_pageOfCurrentLoginUserOfDone")
    public Page<Work> pageOfCurrentLoginUserOfDone(@SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<Work> works = mandatoryInstrumentWorkService.pageOfCurrentLoginUserOfDone(pageable);
        return works;
    }

    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    @GetMapping("/pageOfCurrentLoginUserOfNotToDo")
    @ApiOperation(value = "pageOfCurrentLoginUserOfNotToDo 分页信息", nickname = "MandatoryInstrumentWork_pageOfCurrentLoginUserOfNotToDo")
    public Page<Work> pageOfCurrentLoginUserOfNotToDo(@SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<Work> works = mandatoryInstrumentWorkService.pageOfCurrentLoginUserOfNotToDo(pageable);
        return works;
    }

}
