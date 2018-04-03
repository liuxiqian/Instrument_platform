package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by panjie on 17/6/21.
 * 部门类型
 */
@Service
public class DepartmentTypeServiceImpl implements DepartmentTypeService {
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository; // 部门类型

    /**
     * 获取所有数据
     * @return List
     * @author panjie
     */
    @Override
    @Cacheable("DepartmentTypeServiceImpl_findAll")
    public List<DepartmentType> findAll() {
        return (List<DepartmentType>) departmentTypeRepository.findAll();
    }

    @Override
    public DepartmentType save(DepartmentType departmentType) {
        departmentTypeRepository.save(departmentType);
        return departmentType;
    }

    @Override
    public DepartmentType getOneSavedDepartmentType() {
        DepartmentType departmentType = DepartmentTypeService.getOneDepartmentType();
        departmentTypeRepository.save(departmentType);
        return departmentType;
    }

    @Override
    @Cacheable("DepartmentTypeServiceImpl_getTechnicalInstitutionDepartmentType")
    public DepartmentType getTechnicalInstitutionDepartmentType() {
        return departmentTypeRepository.findOneByPinyin("jishujigou");
    }

    @Override
    @Cacheable("DepartmentTypeServiceImpl_findOneByName")
    public DepartmentType findOneByName(String name) {
        return departmentTypeRepository.findOneByName(name);
    }
}
