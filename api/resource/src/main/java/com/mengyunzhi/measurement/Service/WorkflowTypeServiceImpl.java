package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.WorkflowType;
import com.mengyunzhi.measurement.repository.WorkflowTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by panjie on 17/7/11.
 * 工作流类型
 */
@Service
public class WorkflowTypeServiceImpl implements WorkflowTypeService {
    @Autowired
    WorkflowTypeRepository workflowTypeRepository;

    @Override
    public WorkflowType getOneWorkflowType() {
        WorkflowType workflowType = new WorkflowType();
        workflowType.setName("测试工作流类型" + CommonService.getRandomStringByLength(10));
        return workflowType;
    }

    @Override
    public WorkflowType getOneSavedWorkflowType() {
        WorkflowType workflowType = this.getOneWorkflowType();
        workflowTypeRepository.save(workflowType);
        return workflowType;
    }
}
