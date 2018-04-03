package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangjiahao on 2017/7/6.
 * 部门
 */
public interface DepartmentService {
    List<Department> getTop10ByDepartmentTypeIdAndNameContaining(Long DepartmentTypeId, String name);
    // 取出某区域某类型下的所有 部门列表（包含子部门）
    List<Department> getAllByDistrictAndDepartmentTypeIncludeSons(District district, DepartmentType departmentType);
    //取出某个区域某个部门类型所有部门类型信息
    List<Department> getAllByDistrictAndDepartmentType(District district, DepartmentType departmentType);

    Department getOneDepartment();


    /**
     * 获取一个持久化的部门
     * @return
     */
    Department getOneSavedDepartment();
    // 获取一个技术机构
    Department getOneTechnicalInstitutionDepartment();

    // 获取一个区县级的器具用户
    Department getOneSavedCountryInstrumentUserDepartment();

    // 获取一个完整的部门
    Department getOneCompleteDepartment();
    List<Department> getAllByWorkflowNodeIdOfCurrentLoginUser(Long workflowNodeId)  throws ObjectNotFoundException;

    List<Department> getAllByWorkflowNodeAndUser(WorkflowNode workflowNode, User user);

    List<Department> getAllByWorkflowNodeOfCurrentLoginUser(WorkflowNode workflowNode);
    // 获取符合当前工作流的所有部门信息，同时，添加是否有传入器具的检定能力
    List<Department> getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser(Long workflowNodeId, Long mandatoryInstrumentId) throws ObjectNotFoundException;
    List<Department> getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(WorkflowNode workflowNode, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException;

    // 获取某个区域下的所有技术机构
    List<Department> getAllTechnicalInstitutionsByDistrictId(Long districtId);

    // 获取当前用户所辖区域内 某部门类型拼音 的所有部门
    Page<Department> pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(String departmentTypePinyin, Pageable pageable) throws SecurityException, AccessDeniedException;
    //删除功能实现
    void delete(Long id);

    // 获取某个区域下的所有的器具用户
    List<Department> getAllInstrumentUserByDistrictId(Long districtId);

    //更新部门信息
    void update(Long id, Department department) throws AccessDeniedException;
    List<Department> findAllByDistrictAndDepartmentTypeName(District district, String name);

    //根据部门id获取部门信息
    Department getOneOfDepartmentId(Long id);

    // 获取部门所有登录用户的手机号
    Set<String> getAllUserMobileOfDepartment(Department department);

    /**
     * 向某部门的所有用户发送短信
     *
     * @param department
     * @param message
     */
    void sentMessageToAllUserInDepartment(Department department, String message);

    /**
     * 获取当前用户的市级管理部门
     * @param district
     * @author chuhang
     */
    Department getShiManagementDepartmentByDistrict(District district);
    // 获取一个保存过的管理部门
    Department getOneSavedManagementDepartment();

    /**
     * 获取一个保存的实体
     * @param name 名称
     * @param departmentType 部门类型
     * @return
     * @author panjie
     */
    Department getOneSavedDepartmentByNameAndDepartmentType(String name, DepartmentType departmentType);
}
