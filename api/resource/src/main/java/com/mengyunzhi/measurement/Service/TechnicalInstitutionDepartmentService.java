package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.District;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 技术机构Service
 */
public interface TechnicalInstitutionDepartmentService extends DepartmentService {
    List<Department> getTop10ByNameContaining(String name);
    List<Department> findAllByDistrict(District district);
    // 找到某个部门所在区域及其父区域上的所有的技术机构
    List<Department> findDistrictAndParentDistrictAllByDepartment(Department department);
    // 为某个区域设置技术机构
    District setTechnicalInstitutionDepartmentsForDistrict(District district);
    Department getOneSavedTechnicalInstitutionDepartment();
}
