package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.Qualifier;
import com.mengyunzhi.measurement.entity.User;
import com.mengyunzhi.measurement.repository.QualifierRepository;
import com.mengyunzhi.measurement.specs.QualifierSpecs;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangjiahao on 2017/7/18.
 * 人员 M层实现
 */
@Service
public class QualifierServiceImpl implements QualifierService {
    private static Logger logger = Logger.getLogger(QualifierServiceImpl.class.getName());
    @Autowired
    private QualifierRepository qualifierRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DistrictService districtService;
    @Override
    public Page<Qualifier> getAllByDepartmentId(Long departmentId, Pageable pageable) {
        Page<Qualifier> qualifiers = qualifierRepository.findAllByDepartmentId(departmentId, pageable);
        return qualifiers;
    }

    @Override
    public Qualifier addQualifierOfCurrentLoginUserDepartment(Qualifier qualifier) {
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        qualifier.setDepartment(department);
        qualifierRepository.save(qualifier);
        return qualifier;
    }

    @Override
    public Qualifier updateQualifierOfCurrentLoginUserDepartment(Long id, Qualifier qualifier) throws ObjectNotFoundException, SecurityException{
        logger.info("查找要更新的实体是否存在");
        Qualifier qualifier1 = qualifierRepository.findOne(id);
        if (qualifier1 == null)
        {
            throw new ObjectNotFoundException(404, "要修改的实体不存在");
        }

        User user = userService.getCurrentLoginUser();
        if (user.getDepartment().getId() == qualifier1.getDepartment().getId())
        {
            qualifier.setId(id);
            qualifierRepository.save(qualifier);
            return qualifier;
        }
        else
        {
            throw new SecurityException("您无此操作权限");
        }
    }

    @Override
    public void delete(Long id) throws SecurityException{
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        Qualifier qualifier = qualifierRepository.findOne(id);
        //判断部门要删除的人员对应的部门id与当前登录用户的部门id是否相等
        if (department.getId() == qualifier.getDepartment().getId())
        {
            qualifierRepository.delete(id);
        }
        else{
            throw new SecurityException("很抱歉您没有删除该人员的权限");
        }
        return;
    }

    @Override
    public List<Qualifier> getAllByCurrentLoginUserDepartment() {
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        List<Qualifier> qualifiers = qualifierRepository.findAllByDepartment(department);
        return qualifiers;
    }

    @Override
    public Page<Qualifier> getAllByCurrentLoginUser(Pageable pageable) {
        User user = userService.getCurrentLoginUser();
        Department department = user.getDepartment();
        Page<Qualifier> qualifiers = qualifierRepository.findAllByDepartment(department, pageable);
        return qualifiers;
    }

    @Override
    public Page<Qualifier> pageAllOfCurrentUserBySpecification(Long districtId, String name, String departmentName, Pageable pageable) {
        logger.debug("获取当前用户");
        User currentUser = userService.getCurrentLoginUser();

        List<District> districts;
        if (districtId != null) {
            logger.debug("获取要查询的区域集");
            districts = districtService.getSonsListByRootDistrictAndSonDistrictId(currentUser.getDepartment().getDistrict(), districtId);
        } else {
            logger.debug("获取用户所在区域的区域集");
            districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("districts", districts);
        map.put("departmentName", departmentName);
        Specification specification = QualifierSpecs.base(map);
        Page<Qualifier> qualifiers = qualifierRepository.findAll(specification, pageable);
        return qualifiers;
    }
}
