package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.WorkflowNodeAndApplyFieldAccessService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * panjie
 */
@RestController
@RequestMapping("/WorkflowNodeAndApplyFieldAccess")
@Api(tags = "WorkflowNodeAndApplyFieldAccess  工作流结点-申请字段权限", description = "工作流结点-申请字段权限")
public class WorkflowNodeAndApplyFieldAccessController {
    @Autowired
    WorkflowNodeAndApplyFieldAccessService workflowNodeAndApplyFieldAccessService;

//    @ApiOperation(value = "/getByWorkflowNodeIdAndApplyFieldId",
//            notes = "通过工作流结点ID及申请字段ID，获取实体",
//            nickname = "getByWorkflowNodeIdAndApplyFieldId_getByWorkflowNodeIdAndApplyFieldId")
//    @GetMapping("/getByWorkflowNodeIdAndApplyFieldId/workflowNodeId/{workflowNodeId}/applyFieldId/{applyFieldId}")
//    @JsonView(WorkflowNodeAndApplyFieldAccessJsonView.get.class)
//    public WorkflowNodeAndApplyFieldAccess getByWorkflowNodeIdAndApplyFieldId(
//            @ApiParam("工作流结点ID") @PathVariable("workflowNodeId") Long workflowNodeId,
//            @ApiParam("申请字段ID") @PathVariable("applyFieldId") Long applyFieldId) {
//        return workflowNodeAndApplyFieldAccessService.getByWorkflowNodeIdAndApplyFieldId(workflowNodeId, applyFieldId);
//    }
}
