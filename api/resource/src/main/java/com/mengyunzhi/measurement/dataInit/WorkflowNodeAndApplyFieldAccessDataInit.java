package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.EntityInitDataListener;
import com.mengyunzhi.measurement.repository.ApplyFieldRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;



@Component
public class WorkflowNodeAndApplyFieldAccessDataInit extends EntityInitDataListener {
    private final static Logger logger = Logger.getLogger(WorkflowNodeAndApplyFieldAccessDataInit.class.getName());
    @Autowired
    ApplyTypeDataInit applyTypeDataInit; // 申请类型
    @Autowired
    WorkFlowNodeDataInit workFlowNodeDataInit;   // 工作流结点
    @Autowired
    ApplyFieldRepository applyFieldRepository;   // 申请字段

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }

    @Override
    public int getOrder() {
        // 基于工作流结点及申请字段
        return applyTypeDataInit.getOrder() > workFlowNodeDataInit.getOrder() ?  applyTypeDataInit.getOrder() + 10 : workFlowNodeDataInit.getOrder() + 10;
    }
}
