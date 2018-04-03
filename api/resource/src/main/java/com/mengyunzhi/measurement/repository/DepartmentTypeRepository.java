package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.DepartmentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-17.
 */
public interface DepartmentTypeRepository extends CrudRepository<DepartmentType, Long> {
    DepartmentType getByName(String name);
    DepartmentType findOneByName(String name);
    DepartmentType findOneByPinyin(String pinyin);
    // 获取器具用户类型
    @Query("select d from #{#entityName} d where name = '器具用户'")
    DepartmentType findOneApplianceUserDepartment();

    // 获取管理部门类型
    @Query("select d from #{#entityName} d where name = '管理部门'")
    DepartmentType findOneManagementDepartment();

    //  获取生产企业类型
    @Query("select d from #{#entityName} d where name = '生产企业'")
    DepartmentType findOneManufacturerDepartment();

    //  获取技术机构类型
    @Query("select d from #{#entityName} d where name = '技术机构'")
    DepartmentType findOneTechnicalInstitutionDepartment();
}