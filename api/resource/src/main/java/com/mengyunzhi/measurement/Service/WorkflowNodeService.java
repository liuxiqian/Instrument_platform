package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;

import java.util.List;

/**
 * Created by panjie on 17/7/11.
 * 工作流节点
 */
public interface WorkflowNodeService {
    List<WorkflowNode> getAllByPreWorkflowNodeId(Long preWorkflowNodeId);
    WorkflowNode getOneWorkflowNode();
    WorkflowNode getOneSavedWorkflowNode();
    // 获取符合当前登录用户的对应当前申请类型的首条工作流结点
    WorkflowNode getTopOneByApplyTypeIdOfCurrentUser(Long applyTypeId);
    WorkflowNode getTopOneByWorkflowTypeOfCurrentUser(WorkflowType workflowType);
    WorkflowNode getTopOneByApplyOfCurrentUser(Apply apply);
    WorkflowNode getOneCompleteWorkflowNode();

    // 获取符合条件的下一个工作流结点
    WorkflowNode getOneByPreWorkflowNodeAndDepartmentTypeAndDistrictType(WorkflowNode preWorkflowNode, DepartmentType nextAuditDepartmentType, DistrictType nextAuditDepartmentDistrictType);
}
