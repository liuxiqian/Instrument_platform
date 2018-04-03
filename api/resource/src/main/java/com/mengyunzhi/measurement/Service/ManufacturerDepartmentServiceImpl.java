package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.DepartmentType;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 生产企业service实现
 */
@Service
public class ManufacturerDepartmentServiceImpl extends DepartmentServiceImpl implements ManufacturerDepartmentService {
    final static Logger logger = LoggerFactory.getLogger(ManufacturerDepartmentServiceImpl.class.getName());

    private final DepartmentTypeRepository departmentTypeRepository;    // 部门类型
    private final DepartmentRepository departmentRepository;            // 部门

    @Autowired
    public ManufacturerDepartmentServiceImpl(DepartmentTypeRepository departmentTypeRepository, DepartmentRepository departmentRepository) {
        this.departmentTypeRepository = departmentTypeRepository;
        this.departmentRepository = departmentRepository;
    }

    /**
     * @param name
     * @return array
     * 通过部门的名字模糊查询出前10条数据
     */
    @Override
    public List<Department> getTop10ByNameContaining(String name) {
        //取出器具用户对应的部门类型ID
        DepartmentType departmentType = departmentTypeRepository.findOneByName("生产企业");
        List<Department> departments = this.getTop10ByDepartmentTypeIdAndNameContaining(departmentType.getId(), name);
        return departments;
    }

    /**
     * 根据企业名称，获取一个保存的实体
     * @param name String 企业名称
     * @return Department 部门
     * @author panjie
     */
    @Override
    public Department getOneSavedManagementDepartmentByName(String name) {
        logger.debug("格式化名称，查找当前名称的生产企业是否存在");
        name = name.trim();
        DepartmentType departmentType = departmentTypeService.findOneByName("生产企业");
        Department managementDepartment = departmentRepository.findOneByNameAndDepartmentType(name, departmentType);

        if (managementDepartment == null) {
            logger.info("不存在，则持久化一个");
            managementDepartment = new Department();
            managementDepartment.setDepartmentType(departmentType);
            managementDepartment.setName(name);
            departmentRepository.save(managementDepartment);
        }
        return managementDepartment;
    }

    /**
     * 获取一个供测试使用的持化的生产企业
     * @return Department
     * @author panjie
     */
    @Override
    public Department getOneSavedManufacturerDepartment() {
        Department department = new Department();
        department.setName("测试生产企业" + CommonService.getRandomStringByLength(10));
        DepartmentType departmentType = departmentTypeService.findOneByName("生产企业");
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);
        return department;
    }
}
