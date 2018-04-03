package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.DepartmentType;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-7-8.
 * 技术机构Service实现
 */
@Service
public class TechnicalInstitutionDepartmentServiceImpl extends DepartmentServiceImpl implements TechnicalInstitutionDepartmentService {
    static final Logger logger = Logger.getLogger(TechnicalInstitutionDepartmentServiceImpl.class.getName());
    static final String DB_Type = "技术机构";
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;
    @Autowired private DistrictService districtService; // 区域
    @Autowired private DepartmentRepository departmentRepository;   // 部门
    /**
     * @param name
     * @return array
     * 通过部门的名字模糊查询出前10条数据
     */
    @Override
    public List<Department> getTop10ByNameContaining(String name) {
        //取出器具用户对应的部门类型ID
        DepartmentType departmentType = departmentTypeRepository.findOneByName(this.DB_Type);
        List<Department> departments = this.getTop10ByDepartmentTypeIdAndNameContaining(departmentType.getId(), name);
        return departments;
    }

    @Override
    public List<Department> findAllByDistrict(District district) {
        List<Department> departments = this.findAllByDistrictAndDepartmentTypeName(district, this.DB_Type);
        return departments;
    }

    @Override
    public List<Department> findDistrictAndParentDistrictAllByDepartment(Department department) {
        logger.info("获取这个部门对应的区域及上级区域");
        List<District> districts = districtService.getParentListByDistrict(department.getDistrict());

        logger.info("获取所有区域的技术机构");
        List<Department> technicalInstitutionDepartments = new ArrayList<>();
        for(District district: districts) {
            technicalInstitutionDepartments.addAll(this.findAllByDistrict(district));
        }
        return technicalInstitutionDepartments;
    }

    @Override
    public District setTechnicalInstitutionDepartmentsForDistrict(District district) {
        district.setTechnicalInstitutionDepartments(this.findAllByDistrict(district));
        return district;
    }

    @Override
    public Department getOneSavedTechnicalInstitutionDepartment() {
       return  super.getOneTechnicalInstitutionDepartment();
    }
}
