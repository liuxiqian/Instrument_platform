package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.EntityInitDataListener;
import com.mengyunzhi.measurement.entity.DepartmentType;
import com.mengyunzhi.measurement.entity.DistrictType;
import com.mengyunzhi.measurement.entity.WorkflowNode;
import com.mengyunzhi.measurement.entity.WorkflowType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import com.mengyunzhi.measurement.repository.DistrictTypeRepository;
import com.mengyunzhi.measurement.repository.WorkflowNodeRepository;
import com.mengyunzhi.measurement.repository.WorkflowTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by panjie on 17/7/6.
 * 工作流结点初始化数据
 */
@Component
public class WorkFlowNodeDataInit extends EntityInitDataListener {
    private Logger logger = Logger.getLogger(WorkFlowNodeDataInit.class.getName());
    @Autowired
    protected WorkflowTypeDataInit workflowTypeDataInit;             // 工作流类型数据初始化
    @Autowired
    protected WorkflowTypeRepository workflowTypeRepository;         // 工作流类型
    @Autowired
    protected DistrictTypeRepository districtTypeRepository;         // 区域类型
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository;     // 部门类型
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;         // 工作流结点

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.debug("添加工流流结点初始化数据");
        List<WorkflowNode> workflowNodes = (List<WorkflowNode>) workflowNodeRepository.findAll();
        DistrictType countyDistrictType = districtTypeRepository.findOneByName("区\\县");
        logger.debug("获取基本的依赖对象");
        DistrictType cityDistrictType = districtTypeRepository.findOneByName("市");
//            DistrictType provinceDistrictType = districtTypeRepository.findOneByName("省");
        DepartmentType adminDepartmentType = departmentTypeRepository.findOneByName("管理部门");
        DepartmentType technicalInstitutionDepartmentType = departmentTypeRepository.findOneByName("技术机构");
        DepartmentType ApplianceUserDepartmentType = departmentTypeRepository.findOneByName("器具用户");

        if (workflowNodes.size() < 1) {
            logger.debug("添加市级器具用户审核流程");
            WorkflowType workflowType = workflowTypeRepository.findTopOneByName("适用于器具用户新强检器具审批");

            logger.debug("添加市属器具用户结点");
            WorkflowNode cityInstrumentDepartmentWorkflowNode = new WorkflowNode();
            cityInstrumentDepartmentWorkflowNode.setDistrictType(cityDistrictType);
            cityInstrumentDepartmentWorkflowNode.setWorkflowType(workflowType);
            cityInstrumentDepartmentWorkflowNode.setDepartmentType(ApplianceUserDepartmentType);
            cityInstrumentDepartmentWorkflowNode.setName("市属器具用户");
            workflowNodeRepository.save(cityInstrumentDepartmentWorkflowNode);

            logger.debug("添加市管理部门结点");
            WorkflowNode cityInstrumentDepartmentWorkflowNode0 = new WorkflowNode();
            cityInstrumentDepartmentWorkflowNode0.setDistrictType(cityDistrictType);
            cityInstrumentDepartmentWorkflowNode0.setWorkflowType(workflowType);
            cityInstrumentDepartmentWorkflowNode0.setDepartmentType(adminDepartmentType);
            cityInstrumentDepartmentWorkflowNode0.setPreWorkflowNode(cityInstrumentDepartmentWorkflowNode);
            cityInstrumentDepartmentWorkflowNode0.setName("市级管理部门");
            workflowNodeRepository.save(cityInstrumentDepartmentWorkflowNode0);

            logger.debug("添加区县级器具用户审核流程");
            WorkflowNode workflowNode = new WorkflowNode();
            workflowNode.setDistrictType(countyDistrictType);
            workflowNode.setWorkflowType(workflowType);
            workflowNode.setDepartmentType(ApplianceUserDepartmentType);
            workflowNode.setName("区县级器具用户");
            workflowNodeRepository.save(workflowNode);

            WorkflowNode workflowNode0 = new WorkflowNode();
            workflowNode0.setDistrictType(countyDistrictType);
            workflowNode0.setWorkflowType(workflowType);
            workflowNode0.setDepartmentType(adminDepartmentType);
            workflowNode0.setName("区县级管理部门");
            workflowNode0.setPreWorkflowNode(workflowNode);
            workflowNodeRepository.save(workflowNode0);
        }

        if (workflowNodes.size() < 8) {
            logger.debug("添加市级器具用户审核流程. 1 区县级器具用户。 2.1 市管理部门。 2.2 市技术机构 2.3 区县技术机构");

            WorkflowType workflowType = workflowTypeRepository.findTopOneByName("器具用户送技术机构或市管理部门");
            logger.debug("添加区县级器具用户审核流程");
            WorkflowNode workflowNode = new WorkflowNode();
            workflowNode.setDistrictType(countyDistrictType);
            workflowNode.setWorkflowType(workflowType);
            workflowNode.setDepartmentType(ApplianceUserDepartmentType);
            workflowNode.setName("区县级器具用户");
            workflowNodeRepository.save(workflowNode);

            logger.debug("添加市管理部门结点");
            WorkflowNode cityInstrumentDepartmentWorkflowNode0 = new WorkflowNode();
            cityInstrumentDepartmentWorkflowNode0.setDistrictType(cityDistrictType);
            cityInstrumentDepartmentWorkflowNode0.setWorkflowType(workflowType);
            cityInstrumentDepartmentWorkflowNode0.setDepartmentType(adminDepartmentType);
            cityInstrumentDepartmentWorkflowNode0.setPreWorkflowNode(workflowNode);
            cityInstrumentDepartmentWorkflowNode0.setName("市级管理部门");
            workflowNodeRepository.save(cityInstrumentDepartmentWorkflowNode0);

            logger.debug("添加区县技术机构");
            WorkflowNode workflowNode0 = new WorkflowNode();
            workflowNode0.setDistrictType(countyDistrictType);
            workflowNode0.setWorkflowType(workflowType);
            workflowNode0.setDepartmentType(technicalInstitutionDepartmentType);
            workflowNode0.setName("区县技术机构");
            workflowNode0.setPreWorkflowNode(workflowNode);
            workflowNodeRepository.save(workflowNode0);

            logger.debug("添加市技术机构");
            WorkflowNode cityTechnologyWorkflowNode = new WorkflowNode();
            cityTechnologyWorkflowNode.setDistrictType(cityDistrictType);
            cityTechnologyWorkflowNode.setWorkflowType(workflowType);
            cityTechnologyWorkflowNode.setDepartmentType(technicalInstitutionDepartmentType);
            cityTechnologyWorkflowNode.setName("市技术机构");
            cityTechnologyWorkflowNode.setPreWorkflowNode(workflowNode);
            workflowNodeRepository.save(cityTechnologyWorkflowNode);
        }
    }

    @Override
    public int getOrder() {
        // 基于工作流类型
        return workflowTypeDataInit.getOrder() + 10;
    }
}
