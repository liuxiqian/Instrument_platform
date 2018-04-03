package com.mengyunzhi.measurement.Service;


import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import com.mengyunzhi.measurement.repository.WebAppMenuRepository;
import com.mengyunzhi.measurement.repository.WorkflowNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/7/11.
 * 工作流结点
 */
@Service
public class WorkflowNodeServiceImpl implements WorkflowNodeService {
    /**
     * 通过菜单id获取当前用户符合的所有列表信息
     * 先通过当前菜单获取工作流类型，再根据工作流类型及当前登录用户获取工作流节点
     *
     * @param webAppMenuId
     * @return
     */
    @Autowired
    protected WebAppMenuRepository webAppMenuRepository;
    @Autowired
    protected ApplyTypeRepository applyTypeRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;
    @Autowired WorkflowNodeService workflowNodeService;
    @Autowired ApplyTypeService applyTypeService;

    /**
     * 通过前一工作流节点获取到下一工作流节点的全部信息
     * 先通过当前工作流节点获取到前一工作流节点，再根据前一个工作流节点获取到它的所有的下一个工作流节点的全部信息
     *
     * @param preWorkflowNodeId
     * @return list
     */
    public List<WorkflowNode> getAllByPreWorkflowNodeId(Long preWorkflowNodeId) {
        List<WorkflowNode> list = new ArrayList<WorkflowNode>();
        list = (List<WorkflowNode>) workflowNodeRepository.findAllByPreWorkflowNodeId(preWorkflowNodeId);
        return list;
    }

    @Override
    public WorkflowNode getOneWorkflowNode() {
        WorkflowNode workflowNode = new WorkflowNode();
        return workflowNode;
    }

    @Override
    public WorkflowNode getOneSavedWorkflowNode() {
        WorkflowNode workflowNode = this.getOneWorkflowNode();
        workflowNodeRepository.save(workflowNode);
        return workflowNode;
    }

    @Override
    public WorkflowNode getTopOneByApplyTypeIdOfCurrentUser(Long applyTypeId) {
        ApplyType applyType = applyTypeRepository.findOne(applyTypeId);
        return this.getTopOneByWorkflowTypeOfCurrentUser(applyType.getWorkflowType());
    }

    @Override
    public WorkflowNode getTopOneByWorkflowTypeOfCurrentUser(WorkflowType workflowType) {
        User user = userService.getCurrentLoginUser();
        DepartmentType departmentType = user.getDepartment().getDepartmentType();
        DistrictType districtType = user.getDepartment().getDistrict().getDistrictType();
        return workflowNodeRepository.findTopOneByDepartmentTypeAndDistrictTypeAndWorkflowTypeAndPreWorkflowNodeIsNull(
                departmentType, districtType, workflowType
        );
    }

    @Override
    public WorkflowNode getTopOneByApplyOfCurrentUser(Apply apply) {
        ApplyType applyType = applyTypeService.findOneByName(apply.getClassName());
        return this.getTopOneByWorkflowTypeOfCurrentUser(applyType.getWorkflowType());
    }

    @Override
    public WorkflowNode getOneCompleteWorkflowNode() {
        WorkflowNode workflowNode = workflowNodeService.getOneWorkflowNode();
        workflowNodeRepository.save(workflowNode);
        return workflowNode;
    }

    @Override
    public WorkflowNode getOneByPreWorkflowNodeAndDepartmentTypeAndDistrictType(WorkflowNode preWorkflowNode, DepartmentType departmentType, DistrictType departmentDistrictType) {
        return workflowNodeRepository.findAllByPreWorkflowNodeAndDepartmentTypeAndDistrictType(preWorkflowNode, departmentType, departmentDistrictType);
    }
}
