package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ManagementDepartmentBackedReasonService;
import com.mengyunzhi.measurement.entity.ManagementDepartmentBackedReason;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created By chuhang on 17-11-02
 * 管理部门退回原因控制器
 */

@RestController
@RequestMapping("/ManagementDepartmentBackedReason")
@Api(tags = "ManagementDepartmentBackedReason 管理部门退回原因", description = "管理部门退回原因")
public class ManagementDepartmentBackedReasonController {
    @Autowired
    ManagementDepartmentBackedReasonService managementDepartmentBackedReasonService;

    @ApiOperation(value = "getTop10ManagementDepartmentBackedReasons", notes = "获取最近10条退回理由", nickname = "ManagementDepartmentBackedReason_getTop10ManagementDepartmentBackedReasons")
    @GetMapping("/getTop10ManagementDepartmentBackedReasons")
    public List<ManagementDepartmentBackedReason> getTop10ManagementDepartmentBackedReasons() {
        List<ManagementDepartmentBackedReason> managementDepartmentBackedReasons = managementDepartmentBackedReasonService.getTop10ManagementDepartmentBackedReasons();
        return managementDepartmentBackedReasons;
    }
}
