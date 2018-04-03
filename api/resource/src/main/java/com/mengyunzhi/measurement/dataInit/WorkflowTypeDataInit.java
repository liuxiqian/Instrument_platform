package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.EntityInitDataListener;
import com.mengyunzhi.measurement.entity.WorkflowType;
import com.mengyunzhi.measurement.repository.WorkflowTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.log4j.Logger.*;

/**
 * Created by panjie on 17/7/6.
 * 工作流类型数据初始化
 */
@Component
public class WorkflowTypeDataInit extends EntityInitDataListener {
    private static final Logger logger;

    static {
        logger = getLogger(WorkflowTypeDataInit.class.getName());
    }

    private final EntityInitDataListener entityInitDataListener;   // 基础数据初始化
    protected final WorkflowTypeRepository workflowTypeRepository; // 工作流类型

    @Autowired
    public WorkflowTypeDataInit(EntityInitDataListener entityInitDataListener, WorkflowTypeRepository workflowTypeRepository) {
        this.entityInitDataListener = entityInitDataListener;
        this.workflowTypeRepository = workflowTypeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.debug("工作流类型数据初始化");
        List<WorkflowType> workflowTypes = (List<WorkflowType>) workflowTypeRepository.findAll();
        if (workflowTypes.size() < 1) {
            WorkflowType workflowType = new WorkflowType();
            workflowType.setName("适用于器具用户新强检器具审批");
            workflowType.setDescription("1.向上级管理部门提出申请；2.管理部门可以转给同区域或子区域的技术机构。3.技术机构可办结可返回给管理部门");
            workflowTypes.add(workflowType);
        }

        if (workflowTypes.size() < 2) {
            WorkflowType workflowType = new WorkflowType();
            workflowType.setName("器具用户送技术机构或市管理部门");
            workflowType.setDescription("适用于强检器具检定申请：1. 器具用户发起申请。 2.1 分送至市管理部门 2.2 分送至区县技术机构 2.3 分送至市技术机构");
            workflowTypes.add(workflowType);
        }

        workflowTypeRepository.save(workflowTypes);
    }

    @Override
    public int getOrder() {
        return entityInitDataListener.getOrder() + 10;
    }
}
