package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.ApplyType;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import com.mengyunzhi.measurement.repository.WorkflowTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panjie on 17/7/6.
 * 申请类型
 */
@Service
public class ApplyTypeServiceImpl implements ApplyTypeService {
    @Autowired
    DistrictTypeService districtTypeService;     // 区域类型
    @Autowired
    DepartmentTypeService departmentTypeService; // 部门类型
    @Autowired
    WebAppMenuService webAppMenuService;         // 前台菜单
    @Autowired
    WorkflowTypeService workflowTypeService;                   // 工作流类型
    @Autowired
    ApplyTypeRepository applyTypeRepository;     // 申请类型
    @Autowired
    WorkflowTypeRepository workflowTypeRepository;

    @Override
    public ApplyType getOneSavedApplyType() {
        ApplyType applyType = new ApplyType();
        applyType.setName(CommonService.getRandomStringByLength(20));
        applyType.setWorkflowType(workflowTypeService.getOneSavedWorkflowType());

        applyTypeRepository.save(applyType);
        return applyType;
    }

    @Override
    @Cacheable("ApplyTypeServiceImpl_getOneByName")
    public ApplyType getOneByName(String name) {
        ApplyType applyType = applyTypeRepository.findOneByName(name);
        return applyType;
    }

    @Override
    public ApplyType getById(Long id) {
        ApplyType applyType = applyTypeRepository.findOne(id);
        return applyType;
    }

    // 保存方法的实现
    @Override
    public ApplyType save(ApplyType applyType) {
        applyTypeRepository.save(applyType);
        return applyType;
    }

    // 更新方法的实现
    @Override
    public void update(Long id, ApplyType applyType) {
        applyType.setId(id);
        applyTypeRepository.save(applyType);
        return;
    }

    // 删除方法的实现
    @Override
    public void delete(Long id) {
        applyTypeRepository.delete(id);
        return;
    }

    // 获取全部的申请类型方法的实现
    @Override
    public List<ApplyType> getAll() {
        List<ApplyType> applyTypes = (List<ApplyType>) applyTypeRepository.findAll();
        return applyTypes;
    }

    @Override
    public ApplyType findOneByName(String name) {
        return this.getOneByName(name);
    }

    @Override
    public ApplyType findByApply(Apply apply) {
        return this.findOneByName(apply.CLASS_NAME);
    }
}
