package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangjiahao on 2017/7/6.
 * 部门
 */
@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {
    private Logger logger = Logger.getLogger(DepartmentServiceImpl.class.getName());
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DistrictService districtService;
    @Autowired
    DepartmentTypeService departmentTypeService; // 部门类型
    @Autowired
    private UserService userService;
    @Autowired
    private WorkflowNodeRepository workflowNodeRepository;   // 工作流结点
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository; // 强检器具
    @Autowired
    private DeviceSetRepository deviceSetRepository;             // 检定装置
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;                       // 部门类型
    @Autowired
    private DeviceInstrumentService deviceInstrumentService;                         // 授权检定项目
    @Autowired private SMSService smsService;   // 短信服务
    @Autowired
    private SMSServiceImplC123 smsServiceImplC123;  // 短信服务c123

    @Override
    public List<Department> getTop10ByDepartmentTypeIdAndNameContaining(Long DepartmentTyTypeId, String name) {
        List<Department> departments = departmentRepository.findTop10ByDepartmentTypeIdAndNameContaining(DepartmentTyTypeId, name);
        return departments;
    }

    @Override
    public List<Department> getAllByDistrictAndDepartmentTypeIncludeSons(District district, DepartmentType departmentType) {
        // 获取区域及子区域信息
        List<District> districts = districtService.getSonsListByDistrict(district);
        //获取部门类型
        List<Department> departments = new ArrayList<>();
        for (District newDistrict : districts) {
            List<Department> newDepartments = departmentRepository.findAllByDistrictAndDepartmentType(newDistrict, departmentType);
            departments.addAll(newDepartments);
        }
        return departments;
    }

    @Override
    public List<Department> getAllByDistrictAndDepartmentType(District district, DepartmentType departmentType) {
        List<Department> departments = departmentRepository.findAllByDistrictAndDepartmentType(district, departmentType);
        return departments;
    }


    @Override
    public Department getOneDepartment() {
        Department department = new Department();
        DepartmentType departmentType = departmentTypeService.getOneSavedDepartmentType();
        department.setDepartmentType(departmentType);
        District district = districtService.getOneSavedDistrict();
        department.setDistrict(district);
        department.setDepartmentType(departmentTypeService.getOneSavedDepartmentType());
        department.setName("测试部门信息" + CommonService.getRandomStringByLength(10));
        department.setCode("信息代码" + CommonService.getRandomStringByLength(10));
        department.setAddress("地址：" + CommonService.getRandomStringByLength(10));
        return department;
    }

    /**
     * 获取一个持久化的部门
     *
     * @return
     * @author panjie
     */
    @Override
    public Department getOneSavedDepartment() {
        Department department = this.getOneDepartment();
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Department getOneCompleteDepartment() {
        Department department = this.getOneDepartment();
        department.setName("测试部门" + CommonService.getRandomStringByLength(10));
        departmentRepository.save(department);
        return department;
    }

    @Override
    public List<Department> getAllByWorkflowNodeIdOfCurrentLoginUser(Long workflowNodeId) throws ObjectNotFoundException {
        WorkflowNode workflowNode = workflowNodeRepository.findOne(workflowNodeId);
        if (null == workflowNode) {
            throw new ObjectNotFoundException("404", "WorkflowNode");
        }
        return this.getAllByWorkflowNodeOfCurrentLoginUser(workflowNode);
    }

    @Override
    public List<Department> getAllByWorkflowNodeAndUser(WorkflowNode workflowNode, User user) {
        logger.info("查看当前用户的区域是否与结点区域相同");
        District district = this.getDistrictByWorkflowNodeAndDistrict(workflowNode, user.getDepartment().getDistrict());
        if (null == district) {
            logger.info("当前用户所在区域是工作流节点上设置的父区域，则以当前用户所有区域为准");
            district = user.getDepartment().getDistrict();
        }
        List<Department> departments = new ArrayList<>();
        if (workflowNode.isContainSonDistrict()) {
            logger.info("包含子区域，则取出自已以及所有子区域中符合要求的部门");
            departments = this.getAllByDistrictAndDepartmentTypeIncludeSons(district, workflowNode.getDepartmentType());
        } else {
            logger.info("不包含子区域，则只取出本区域符合要求的部门");
            departments = this.getAllByDistrictAndDepartmentType(district, workflowNode.getDepartmentType());
        }
        return departments;
    }

    public District getDistrictByWorkflowNodeAndDistrict(WorkflowNode workflowNode, District district) {
        logger.info("判断当前用户区域类型是否符合要求");
        if (null == district) {
            return district;
        } else if (district.getDistrictType().getId() == workflowNode.getDistrictType().getId()) {
            return district;
        } else {
            return this.getDistrictByWorkflowNodeAndDistrict(workflowNode, district.getParentDistrict());
        }
    }

    @Override
    public List<Department> getAllByWorkflowNodeOfCurrentLoginUser(WorkflowNode workflowNode) {
        User user = userService.getCurrentLoginUser();
        return this.getAllByWorkflowNodeAndUser(workflowNode, user);
    }

    @Override
    public List<Department> getAllWithCheckAbilityByWorkflowNodeIdAndMandatoryInstrumentIdOfCurrentLoginUser(Long workflowNodeId, Long mandatoryInstrumentId) throws ObjectNotFoundException {
        WorkflowNode workflowNode = workflowNodeRepository.findOne(workflowNodeId);
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(mandatoryInstrumentId);
        if (workflowNode == null) {
            throw new ObjectNotFoundException(404, "id为" + workflowNodeId.toString() + "的工作流实体未找到");
        } else if (mandatoryInstrument == null) {
            throw new ObjectNotFoundException(404, "id为" + mandatoryInstrumentId.toString() + "的实体未找到");
        }
        return this.getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(workflowNode, mandatoryInstrument);
    }

    @Override
    public List<Department> getAllWithCheckAbilityByWorkflowNodeAndMandatoryInstrumentOfCurrentLoginUser(WorkflowNode workflowNode, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException {
        logger.info("获取所有的符合申请流程的部门列表");
        List<Department> departments = this.getAllByWorkflowNodeOfCurrentLoginUser(workflowNode);


        for (Department department : departments) {
            logger.info("判断是否拥有检定能力");
            boolean ability = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(
                    mandatoryInstrument.getAccuracy(),
                    mandatoryInstrument.getMinMeasureScale(),
                    mandatoryInstrument.getMaxMeasureScale(),
                    mandatoryInstrument.getInstrumentType(),
                    department);
            department.setCheckAbility(ability);
        }
        return departments;
    }

    @Override
    public List<Department> getAllTechnicalInstitutionsByDistrictId(Long districtId) {
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        return departmentRepository.findAllByDistrictIdAndDepartmentType(districtId, departmentType);
    }

    @Override
    public Department getOneTechnicalInstitutionDepartment() {
        Department department = this.getOneCompleteDepartment();
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        department.setDepartmentType(departmentType);
        return departmentRepository.save(department);
    }

    @Override
    public Department getOneSavedCountryInstrumentUserDepartment() {
        Department department = this.getOneCompleteDepartment();
        DepartmentType departmentType = departmentTypeRepository.findOneByName("器具用户");
        department.setDepartmentType(departmentType);

        District district = districtService.getOneSavedCountryDistrict();
        department.setDistrict(district);
        departmentRepository.save(department);
        return department;
    }

    @Override
    public Page<Department> pageByDepartmentTypePinyinOfCurrentLoginUserManageDistricts(String departmentTypePinyin, Pageable pageable) throws AccessDeniedException {
        logger.info("获取当前用户，并检定是否是管理部门");
        User user = userService.getCurrentLoginUser();
        DepartmentType departmentType1 = departmentTypeRepository.findOneByName("管理部门");
        if (user.getDepartment().getDepartmentType().getId() != departmentType1.getId()) {
            throw new AccessDeniedException("您不是管理部门，没有查看的权限");
        }

        logger.info("按拼音取出部门类型");
        DepartmentType departmentType = departmentTypeRepository.findOneByPinyin(departmentTypePinyin);
        if (departmentType == null) {
            throw new ObjectNotFoundException(404, "传入的部门类型拼间未找到对应的实体");
        }
        logger.info("取出当前用户所在部门的所辖区域");
        List<District> districts = districtService.getSonsListByDistrict(user.getDepartment().getDistrict());

        logger.info("调用实体仓库方法，返回值");
        Page<Department> departments = departmentRepository.findAllByDistrictsAndDepartmentType(districts, departmentType, pageable);
        return departments;
    }

    @Override
    public void delete(Long id) {
        logger.info("删除实体");
        departmentRepository.delete(id);
        return;
    }

    @Override
    public List<Department> getAllInstrumentUserByDistrictId(Long districtId) {
        DepartmentType departmentType = departmentTypeRepository.findOneByName("器具用户");
        return departmentRepository.findAllByDistrictIdAndDepartmentType(districtId, departmentType);
    }

    @Override
    public void update(Long id, Department department) throws AccessDeniedException, ObjectNotFoundException {
        logger.info("获取当前登录用户");
        User currentUser = userService.getCurrentLoginUser();
        logger.info("后台获取相关的数据");
        Department department1 = departmentRepository.findOne(id);

        if (!currentUser.getDepartment().getDepartmentType().getName().equals("管理部门")) {

            if (currentUser.getDepartment().getId() == department1.getId()) {
                logger.info("此部门需要更新自己部门的信息");
                department1.setName(department.getName());
                department1.setCode(department.getCode());
                department1.setPostalCode(department.getPostalCode());
                department1.setAddress(department.getAddress());
                department1.setLegalName(department.getLegalName());
                department1.setLegalPhone(department.getLegalPhone());
                department1.setRegistrantName(department.getRegistrantName());
                department1.setRegistrantPhone(department.getRegistrantPhone());
                department1.setRegistrantTel(department.getRegistrantTel());
                department1.setRegistrantMail(department.getRegistrantMail());
                department1.setPinyin(department.getPinyin());
                department1.setDepartmentType(department.getDepartmentType());
                department1.setDistrict(department.getDistrict());

                logger.info("更新实体信息");
                departmentRepository.save(department1);

            } else {
                logger.info("不是管理部门用户没有编辑用户的权限");
                throw new AccessDeniedException("the login user's departmentType is not management. 不是管理部门用户没有编辑用户的权限");
            }

        } else if (!districtService.preOneIsParentNextOne(currentUser.getDepartment().getDistrict(), department.getDistrict())) {
            throw new AccessDeniedException("this update department's district is over management. 要修改为的区域不在管辖区域之内");

        } else if (null == department1) {
            logger.info("没有找到相关实体 ");
            throw new ObjectNotFoundException(404, "未找到ID为" + id + "的部门信息");

        } else if (!districtService.preOneIsParentNextOne(currentUser.getDepartment().getDistrict(), department1.getDistrict())) {
            throw new AccessDeniedException("the target update department district is over management power. 要改的区域内没有在管辖区域之内");

        } else {
            logger.info("管理部门修改子区域内的部门信息");
            department1.setName(department.getName());
            department1.setCode(department.getCode());
            department1.setPostalCode(department.getPostalCode());
            department1.setAddress(department.getAddress());
            department1.setLegalName(department.getLegalName());
            department1.setLegalPhone(department.getLegalPhone());
            department1.setRegistrantName(department.getRegistrantName());
            department1.setRegistrantPhone(department.getRegistrantPhone());
            department1.setRegistrantTel(department.getRegistrantTel());
            department1.setRegistrantMail(department.getRegistrantMail());
            department1.setPinyin(department.getPinyin());
            department1.setDepartmentType(department.getDepartmentType());
            department1.setDistrict(department.getDistrict());

            logger.info("更新实体信息");
            departmentRepository.save(department1);
        }

        return;
    }

    @Override
    public List<Department> findAllByDistrictAndDepartmentTypeName(District district, String name) {
        return departmentRepository.findAllDistrictAndDepartmentTypeName(district, name);
    }

    @Override
    public Department getOneOfDepartmentId(Long id) throws EntityNotFoundException {
        Department department = departmentRepository.findOne(id);

        if (department == null)
            throw new ObjectNotFoundException(404, "未找到ID为" + id + "的部门信息");

        return department;
    }

    @Override
    public Set<String> getAllUserMobileOfDepartment(Department department) {
        Set<User> users = department.getUsers();
        logger.info("获取所有用户的手机号");
        Set<String> mobiles = null;
        for (User user : users) {
            mobiles.add(user.getMobile());
        }

        return mobiles;
    }

    /**
     * 给本部门中所有的用户都发送一遍短信
     * @param department
     * @param message
     * chuhang panjie
     */
    @Override
    public void sentMessageToAllUserInDepartment(Department department, String message) {
        Set<User> users = department.getUsers();

        logger.info("获取短信数组");
        Set<String> mobiles = new HashSet<>();
        for (User user : users) {
            mobiles.add(user.getMobile());
        }

        logger.info("调用短信接口");
        try {
            smsServiceImplC123.sentMessage(mobiles, message);
        } catch (IOException e) {
            logger.info("发送短信失败");
        }

        return;
    }

    @Override
    public Department getShiManagementDepartmentByDistrict(District district) throws IllegalStateException {
        if (district.getDistrictType().getPinyin().equals("sheng")) {
            logger.debug("传入区域的类型为省");
            throw new IllegalArgumentException("传入区域的类型应该为市或市以下");
        }

        if (district.getDistrictType().getPinyin().equals("shi")) {
            logger.debug("传入区域的类型为市");
            DepartmentType departmentType = departmentTypeRepository.findOneByName("管理部门");
            Department department = departmentRepository.findTopOneByDistrictAndDepartmentType(district, departmentType);
            return department;
        } else {
            logger.debug("获取区域为父区域");
            District parentDistrict = district.getParentDistrict();
            return this.getShiManagementDepartmentByDistrict(parentDistrict);
        }
    }

    @Override
    public Department getOneSavedManagementDepartment() {
        Department department = new Department();
        department.setName("测试管理部门" + CommonService.getRandomStringByLength(30));
        DepartmentType departmentType = departmentTypeService.findOneByName("管理部门");
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);
        return department;
    }

    /**
     * 获取一个持久化的部门
     * @param name 名称
     * @param departmentType 部门类型
     * @return
     * @author panjie
     */
    @Override
    public Department getOneSavedDepartmentByNameAndDepartmentType(String name, DepartmentType departmentType) {
        name = name.trim();
        Department department = departmentRepository.findOneByNameAndDepartmentType(name, departmentType);
        if (department == null) {
            logger.debug("未找到相关的部门，则新建一个");
            department = new Department();
            department.setDepartmentType(departmentType);
            department.setName(name);
            departmentRepository.save(department);
        }

        return department;
    }

}
