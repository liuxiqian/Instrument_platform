package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.EntityInitDataListener;
import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by 安强 on 2017/6/16.
 * 申请类型
 */
@Component
public class ApplyTypeDataInit extends EntityInitDataListener {
    private Logger logger = Logger.getLogger(ApplyTypeDataInit.class.getName());

    @Autowired
    private ApplyTypeRepository applyTypeRepository;
    @Autowired
    protected WorkflowTypeDataInit workflowTypeDataInit;
    @Autowired
    DepartmentTypeRepository departmentTypeRepository;   // 部门类型
    @Autowired
    DistrictTypeRepository districtTypeRepository;       // 区域类型
    @Autowired
    WebAppMenuRepository webAppMenuRepository;           // 前台菜单
    @Autowired WorkflowTypeRepository workflowTypeRepository;       // 工作流类型

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.debug("----- 添加适用于器具用户申请添加强检器具的申请类型 -----");
        List<ApplyType> applyTypeList = (List<ApplyType>) applyTypeRepository.findAll();
        WorkflowType workflowType = null;
        if (applyTypeList.size() < 2) {
            logger.info("获取对应的工作流类型");
            workflowType = workflowTypeRepository.findTopOneByName("适用于器具用户新强检器具审批");

            ApplyType applyType = new ApplyType();
            applyType.setName(MandatoryInstrumentApply.CLASS_NAME);
            applyType.setDescription("强制检定器具备案申请");
            applyType.setWorkflowType(workflowType);
            applyTypeList.add(applyType);

            ApplyType applyType1 = new ApplyType();
            applyType1.setName(OverdueCheckApply.CLASS_NAME);
            applyType1.setDescription("超期未检申请");
            applyType1.setWorkflowType(workflowType);
            applyTypeList.add(applyType1);
        }

        if (applyTypeList.size() < 4) {
            ApplyType applyType2 = new ApplyType();
            applyType2.setName(MandatoryInstrumentCheckApply.CLASS_NAME);
            applyType2.setDescription("强检器具检定申请");
            WorkflowType workflowType1 = workflowTypeRepository.findTopOneByName("器具用户送技术机构或市管理部门");
            applyType2.setWorkflowType(workflowType1);
            applyTypeList.add(applyType2);

            ApplyType applyType3 = new ApplyType();
            applyType3.setName(Apply.CLASS_NAME);
            applyType3.setDescription("申请（用于被继承与单元测试)");
            workflowType = workflowTypeRepository.findTopOneByName("适用于器具用户新强检器具审批");
            applyType3.setWorkflowType(workflowType);
            applyTypeList.add(applyType3);
        }

        applyTypeRepository.save(applyTypeList);
    }

    /**
     * 申请类型数据独立
     * @return
     */
    @Override
    public int getOrder() {
        // 基于工作流类型
        return workflowTypeDataInit.getOrder() + 10;
    }
}
