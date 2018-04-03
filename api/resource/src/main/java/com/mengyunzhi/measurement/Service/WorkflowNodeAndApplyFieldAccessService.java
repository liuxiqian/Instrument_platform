package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.WorkflowNodeAndApplyFieldAccess;

/**
 * 工作流结点 - 申请字段权限
 */
public interface WorkflowNodeAndApplyFieldAccessService {
    WorkflowNodeAndApplyFieldAccess getOneEntity();
    WorkflowNodeAndApplyFieldAccess getOneSavedEntity();
//    WorkflowNodeAndApplyFieldAccess getByWorkflowNodeIdAndApplyFieldId(Long workflowNodeId, Long applyFieldId);
//    WorkflowNodeAndApplyFieldAccess getByWorkflowNodeAndApplyField(WorkflowNode workflowNode, ApplyField applyField);
}
