package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.DepartmentType;

import java.util.List;

/**
 * Created by panjie on 17/6/21.
 * 部门类型
 */
public interface DepartmentTypeService {
    List<DepartmentType> findAll();
    DepartmentType save(DepartmentType departmentType);
    static DepartmentType getOneDepartmentType() {
        DepartmentType departmentType = new DepartmentType();
        departmentType.setName("测试部门类型" + CommonService.getRandomStringByLength(10));
        return departmentType;
    }

    DepartmentType getOneSavedDepartmentType();

    /**
     * 获取一个技术机构的类型
     * @return
     */
    DepartmentType getTechnicalInstitutionDepartmentType();

    DepartmentType findOneByName(String name);

}
