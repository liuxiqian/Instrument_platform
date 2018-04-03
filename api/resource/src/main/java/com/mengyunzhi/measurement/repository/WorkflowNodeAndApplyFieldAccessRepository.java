package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.WorkflowNodeAndApplyFieldAccess;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by panjie on 17/10/9.
 * 工作流结点-申请字段 权限
 */
public interface WorkflowNodeAndApplyFieldAccessRepository extends CrudRepository<WorkflowNodeAndApplyFieldAccess, Long> {
//    WorkflowNodeAndApplyFieldAccess findByWorkflowNodeAndApplyField(WorkflowNode workflowNode, ApplyField applyField);
}
