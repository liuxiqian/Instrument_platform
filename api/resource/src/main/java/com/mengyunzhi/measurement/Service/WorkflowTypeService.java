package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.WorkflowType;

/**
 * Created by panjie on 17/7/11.
 * 工作流类型
 */
public interface WorkflowTypeService {
    WorkflowType getOneWorkflowType();

    WorkflowType getOneSavedWorkflowType();
}
