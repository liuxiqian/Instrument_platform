package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/7/6.
 * 计量标准装置
 */
public class DeviceSetServiceImplTest extends ServiceTest {
    private Logger logger = Logger.getLogger(DeviceSetServiceImplTest.class.getName());
    @Autowired protected DeviceSetService deviceSetService;       // 计量标准装置
    @Autowired protected DeviceSetRepository deviceSetRepository;
    @Autowired protected DeviceInstrumentRepository deviceInstrumentRepository;
    @Autowired protected AccuracyRepository accuracyRepository;
    @Autowired protected MeasureScaleRepository measureScaleRepository;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected UserService userService;
    @Autowired protected UserRepository userRepository;
    @Autowired protected DeviceSetServiceTestData deviceSetServiceTestData;
    @Autowired protected DistrictRepository districtRepository;
    @Autowired private MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository; // 强检器具二级类别

    @Test
    public void save() throws Exception {
        logger.info("一并测试getOneDeviceSet saveWorkWithCurrentUserAudit delete方法");
        DeviceSet deviceSet = DeviceSetService.getOneDeviceSet();
        User user = userRepository.findOneByUsername("user3");
        userService.setCurrentLoginUser(user);
        deviceSetService.save(deviceSet);
        assertThat(deviceSetRepository.findOne(deviceSet.getId())).isNotNull();
        deviceSetService.delete(deviceSet);
        assertThat(deviceSetRepository.findOne(deviceSet.getId())).isNull();
    }


    @Test
    public void updateDeviceInstrumentsById() {
        String name = CommonService.getRandomStringByLength(10);
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        DeviceSet deviceSet = deviceSetServiceTestData.updateDeviceInstrumentsById(name, deviceInstrument);

        logger.info("断言");
        assertThat(deviceSet.getName()).isEqualTo(name);
        logger.info("断言中间表删除成功");
        assertThat(deviceSet.getDeviceInstruments().size()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void getAll() throws Exception {
        logger.info("新建一个计量标注装置实体并保存");
        DeviceSet deviceSet = DeviceSetService.getOneDeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个计量标准装置实体并保存");
        DeviceSet deviceSet1 = new DeviceSet();
        deviceSet1.setName("name1");
        deviceSet1.setCertificateNum("number2");
        deviceSetRepository.save(deviceSet1);

        logger.info("设置分页大小以及显示的页码的起始位置");
        final PageRequest pageRequest = new PageRequest(1,1);

        logger.info("调用在deviceRepository中定义的分页方法，并将参数pageRequest传入");
        Page<DeviceSet> page = deviceSetRepository.findAll(pageRequest);

        logger.info("断言分页总数与数据表中数据总数相同");
        assertThat(page.getTotalPages()).isEqualTo((int)page.getTotalElements());
        logger.info("断言分页大小为1");
        assertThat(page.getContent()).size().isEqualTo(1);

        logger.info("删除刚才建立的实体");
        deviceSetRepository.delete(deviceSet);
        deviceSetRepository.delete(deviceSet1);
        return;
    }

    @Test
    public void getAllDeviceSetByTechnicalInstitutionId() {
        logger.info("创建一个部门");
        Department department = new Department();
        departmentRepository.save(department);
        Set<DeviceSet> deviceSets = new HashSet<>();
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSets.add(deviceSet);
        DeviceSet deviceSet1 = new DeviceSet();
        deviceSet1.setDepartment(department);
        deviceSets.add(deviceSet1);
        deviceSetRepository.save(deviceSets);

        logger.info("测试");
        List<DeviceSet> list = deviceSetService.getAllDeviceSetByTechnicalInstitutionId(department.getId());
        assertThat(list.size()).isEqualTo(2);

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSets);
        departmentRepository.delete(department);
    }

    @Test
    public void pageAllByCurrentUser() {
        logger.info("新建一个登录用户");
        User user = userService.loginWithOneUser();

        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(user.getDepartment());
        deviceSetRepository.save(deviceSet);

        logger.info("断言");
        final PageRequest pageRequest = new PageRequest(0, 1);
        Page<DeviceSet> page = deviceSetService.pageAllOfCurrentUser(pageRequest);
        assertThat(page.getTotalElements()).isEqualTo(1);

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void update() {
        logger.info("创建一个计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("name");
        deviceSetRepository.save(deviceSet);
        logger.info("修改更新数据");
        deviceSet.setName("haha");
        logger.info("测试");
        deviceSetService.update(deviceSet.getId(), deviceSet);
        logger.info("断言");
        assertThat(deviceSetRepository.findOne(deviceSet.getId()).getName()).isEqualTo("haha");
        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void delete() {
        logger.info("创建一个部门");
        Department department = new Department();
        departmentRepository.save(department);
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);
        logger.info("设置当前登陆用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);

        logger.info("断言");
        deviceSetService.delete(deviceSet.getId());
        assertThat(deviceSetRepository.findOne(deviceSet.getId())).isNull();

        logger.info("删除数据");
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void pageAllOfCurrentUserBySpecification() {
        String code = CommonService.getRandomStringByLength(10);
        String name = CommonService.getRandomStringByLength(10);
        Pageable pageable = new PageRequest(0, 2);
        Page<DeviceSet> deviceSets = null;
        DeviceSet deviceSet = new DeviceSet();
        Department department = new Department();
        District district = new District();
        District district1 = new District();
        User user = new User();

        deviceSetServiceTestData.pageAllOfCurrentUserBySpecification(name, code, department, district, deviceSet, district1, user);

        // 只传入name
        deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(null, null,"", "", pageable);
        assertThat(deviceSets.getTotalElements()).isEqualTo(1);

        // 传入name和代码
        deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(null, null,name, code, pageable);
        assertThat(deviceSets.getTotalElements()).isEqualTo(1);

        //传入部门ID，name和代码
        deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(null, department.getId(), name, code, pageable);
        assertThat(deviceSets.getTotalElements()).isEqualTo(1);

        //传入部门ID，name和代码,区域id
        deviceSets = deviceSetService.pageAllOfCurrentUserBySpecification(district1.getId(), null, "", "", pageable);
        assertThat(deviceSets.getTotalElements()).isEqualTo(0);

        //删除数据
        deviceSetRepository.delete(deviceSet);
        departmentRepository.delete(department);
        districtRepository.delete(district);
    }

    // @author panjie
    @Test
    public void getOneSavedDeviceSet() {
        logger.debug("获取标准装置，并断言其加入的部门与创建用户信息");
        DeviceSet deviceSet = deviceSetService.getOneSavedDeviceSet();
        assertThat(deviceSet.getId()).isNotNull();
        assertThat(deviceSet.getDepartment()).isNotNull();
        assertThat(deviceSet.getCreateUser()).isNotNull();
    }
}