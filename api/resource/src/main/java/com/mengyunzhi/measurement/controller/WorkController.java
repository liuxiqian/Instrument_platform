package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.WorkService;
import com.mengyunzhi.measurement.entity.Work;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentWorkJsonView;
import com.mengyunzhi.measurement.jsonView.WorkJsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by panjie on 17/7/18.
 * 工作
 */
@RestController
@RequestMapping("/Work")
@Api(tags = "Work 工作", description = "用户提交申请后，生成工作记录。 author:panjie")
public class WorkController {
    @Autowired
    @Qualifier("WorkService")
    private WorkService workService;

    @GetMapping("/{id}")
    @ApiOperation(value = "/", notes = "method: get", nickname = "Work_getById")
    @JsonView(WorkJsonView.getById.class)
    public Work getById(@ApiParam("工作id") @PathVariable("id") Long id) {

        return workService.getById(id);
    }

    @PatchMapping("/updateToDoingById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "updateToDoingById",
            notes = "更新某个ID记录的WORK为在办",
            nickname = "Work_updateToDoingById")
    public void updateToDoingById(@ApiParam("工作id") @PathVariable("id") Long id) {
        workService.updateToHandlingByWorkId(id);
    }

    @GetMapping("/getOriginWorkByWorkId/{id}")
    @ApiOperation(value = "findFirstWorkById 获取首工作",
            notes = "获取某个工作对应的起点工作(即上一申请人提交的工作)",
            nickname = "Work_getOriginWorkByWorkId")
    @JsonView(WorkJsonView.getById.class)
    public Work getOriginWorkByWorkId(@ApiParam("工作ID") @PathVariable("id") Long id) {
        return workService.getOriginWorkByWorkId(id);
    }

    @GetMapping("/getNextWorkById/{id}")
    @ApiOperation(value = "getNextWorkById 获取下个工作",
            notes = "获取当前传入工作的下一个工作",
            nickname = "Work_getNextWorkById")
    @JsonView(WorkJsonView.getById.class)
    public Work getNextWorkById(@ApiParam("工作ID") @PathVariable("id") Long id) {
        return workService.findNextWorkByWorkId(id);
    }

    @GetMapping("/getAllByApplyId/{applyId}")
    @ApiOperation(value = "getAllByApplyId 获取某个申请的所有记录",
            notes = "获取某个申请的所有记录 ID从小到大自然排序",
            nickname = "Work_getAllByApplyId")
    @JsonView(WorkJsonView.getAllByApplyId.class)
    public List<Work> getAllByApplyId(@ApiParam("申请ID") @PathVariable("applyId") Long applyId) {
        return workService.getAllByApplyId(applyId);
    }

    @PatchMapping("/audit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "audit 审核某个工作",
            notes = "审核某个工作（权限限制：当前工作的审核部门为当前登录用户所在部门）",
            nickname = "Work_audit")
    public void audit(@ApiParam("工作") @RequestBody Work work) {
        workService.audit(work);
        return;
    }

    @GetMapping("/getPageOfCurrentUserByApplyTypeFlag/{applyTypeFlag}")
    @ApiOperation(value = "getPageOfCurrentUserByApplyTypeName 按申请类型名称获取当前登录用户对应的工作分页列表",
            notes = "按申请类型获取当前登录用户对应的工作分页列表",
            nickname = "Work_getPageOfCurrentUserByApplyTypeFlag")
    @JsonView(WorkJsonView.getById.class)
    public Page<Work> getPageOfCurrentUserByApplyTypeFlag(
            @ApiParam("申请类型标识") @PathVariable("applyTypeFlag") String applyTypeFlag,
            Pageable pageable) {
        return workService.pageOfCurrentUserByApplyTypeFlag(applyTypeFlag, pageable);
    }

    @GetMapping("/getAllCurrentUserWorkOfToDo/")
    @ApiOperation(value = "getAllUnHandleWorkOfCurrentUser 获取当前登录用户的所有待办工作",
            notes = "获取当前登录用户的所有待办工作",
            nickname = "Work_getAllCurrentUserWorkOfToDo")
    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    public List<Work> getAllCurrentUserWorkOfToDo() {
        return workService.getAllUnHandleWorkOfCurrentUser();
    }

    @GetMapping("/getAllCurrentUserWorkOfDoing/")
    @ApiOperation(value = "getAllCurrentUserWorkOfDoing 获取当前登录用户的所有待办工作",
            notes = "获取当前登录用户的所有待办工作",
            nickname = "Work_getAllCurrentUserWorkOfDoing")
    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    public List<Work> getAllCurrentUserWorkOfDoing() {
        return workService.getAllCurrentUserWorkOfDoing();
    }

    @GetMapping("/pageUnHandleWorkOfCurrentUser")
    @ApiOperation(value = "pageUnHandleWorkOfCurrentUser",
            notes = "获取当前登录用户的待办工作",
            nickname = "Work_pageUnHandleWorkOfCurrentUser")
    @JsonView(WorkJsonView.getById.class)
    public Page<Work> pageUnHandleWorkOfCurrentUser(Pageable pageable) {
        return workService.pageUnHandleWorkOfCurrentUser(pageable);
    }

    @GetMapping("/pageHandlingWorkOfCurrentUser")
    @ApiOperation(value = "pageHandlingWorkOfCurrentUser",
            notes = "获取当前登录用户的在办工作",
            nickname = "Work_pageHandlingWorkOfCurrentUser")
    @JsonView(WorkJsonView.getById.class)
    public Page<Work> pageHandlingWorkOfCurrentUser(Pageable pageable) {
        return workService.pageHandlingWorkOfCurrentUser(pageable);

    }

    @GetMapping("/pageHandledWorkOfCurrentUser")
    @ApiOperation(value = "pageHandledWorkOfCurrentUser",
            notes = "获取当前登录用户的已办工作",
            nickname = "Work_pageHandledWorkOfCurrentUser")
    @JsonView(WorkJsonView.getById.class)
    public Page<Work> pageHandledWorkOfCurrentUser(Pageable pageable) {
        return workService.pageHandledWorkOfCurrentUser(pageable);

    }

    @GetMapping("/pageDoneWorkOfCurrentUser")
    @ApiOperation(value = "pageDoneWorkOfCurrentUser",
            notes = "获取当前登录用户的办结工作",
            nickname = "Work_pageDoneWorkOfCurrentUser")
    @JsonView(WorkJsonView.getById.class)
    public Page<Work> pageDoneWorkOfCurrentUser(Pageable pageable) {
        return workService.pageDoneWorkOfCurrentUser(pageable);
    }
}
