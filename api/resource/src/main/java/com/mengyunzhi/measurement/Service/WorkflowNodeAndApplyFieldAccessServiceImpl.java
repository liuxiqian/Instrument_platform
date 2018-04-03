package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.WorkflowNodeAndApplyFieldAccess;
import com.mengyunzhi.measurement.repository.WorkflowNodeAndApplyFieldAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowNodeAndApplyFieldAccessServiceImpl implements WorkflowNodeAndApplyFieldAccessService {
    @Autowired
    WorkflowNodeAndApplyFieldAccessRepository workflowNodeAndApplyFieldAccessRepository;
    @Autowired ApplyFieldService applyFieldService;     // 申请字段
    @Autowired WorkflowNodeService workflowNodeService; // 工作流结点

    @Override
    public WorkflowNodeAndApplyFieldAccess getOneEntity() {
        WorkflowNodeAndApplyFieldAccess workflowNodeAndApplyFieldAccess = new WorkflowNodeAndApplyFieldAccess();
        workflowNodeAndApplyFieldAccess.setApplyField(applyFieldService.getOneSavedApplyField());
        return workflowNodeAndApplyFieldAccess;
    }

    @Override
    public WorkflowNodeAndApplyFieldAccess getOneSavedEntity() {
        WorkflowNodeAndApplyFieldAccess workflowNodeAndApplyFieldAccess = this.getOneEntity();
        workflowNodeAndApplyFieldAccessRepository.save(workflowNodeAndApplyFieldAccess);
        return workflowNodeAndApplyFieldAccess;
    }

//    @Override
//    public WorkflowNodeAndApplyFieldAccess getByWorkflowNodeIdAndApplyFieldId(Long workflowNodeId, Long applyFieldId) {
//        WorkflowNode workflowNode = new WorkflowNode();
//        workflowNode.setId(workflowNodeId);
//
//        ApplyField applyField = new ApplyField();
//        applyField.setId(applyFieldId);
//
//        WorkflowNodeAndApplyFieldAccess workflowNodeAndApplyFieldAccess = this.getByWorkflowNodeAndApplyField(workflowNode, applyField);
//        return workflowNodeAndApplyFieldAccess;
//    }
//
}
