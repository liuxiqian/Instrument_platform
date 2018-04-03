package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 生产企业Service
 */
public interface ManufacturerDepartmentService extends DepartmentService{
    List<Department> getTop10ByNameContaining(String name);

    // 根据企业名称，获取一个保存的实体
    Department getOneSavedManagementDepartmentByName(String name);

    Department getOneSavedManufacturerDepartment();
}
