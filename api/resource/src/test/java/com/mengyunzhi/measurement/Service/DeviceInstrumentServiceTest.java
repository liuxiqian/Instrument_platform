package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目
 */
public class DeviceInstrumentServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(DeviceInstrumentServiceTest.class.getName());

    @Autowired
    protected DeviceInstrumentService deviceInstrumentService;
    @Autowired
    protected DeviceSetService deviceSetService;       // 计量标准装置
    @Autowired
    protected DeviceSetRepository deviceSetRepository;
    @Autowired
    protected DeviceInstrumentRepository deviceInstrumentRepository;
    @Autowired
    protected AccuracyRepository accuracyRepository;
    @Autowired
    protected MeasureScaleRepository measureScaleRepository;
    @Autowired
    protected DepartmentRepository departmentRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    private DeviceInstrumentServiceTestData deviceInstrumentServiceTestData;    // 数据准备
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private AccuracyService accuracyService;
    @Autowired
    private MeasureScaleService measureScaleService;
    @Autowired private MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository; // 强检器具类别
    @Autowired @Qualifier("DepartmentService") DepartmentService departmentService;  // 部门
    @Test
    public void pageAllByCurrentUserOfDeviceInstrument() {
        deviceInstrumentServiceTestData.pageAllByCurrentUserOfDeviceInstrument();

        PageRequest pageRequest = new PageRequest(1, 1);
        Page<DeviceInstrument> page = deviceInstrumentService.pageAllByCurrentUserOfDeviceInstrument(pageRequest);
        assertThat(page.getTotalElements()).isEqualTo(2);
    }

    @Test
    public void pageByDeviceSetOfCurrentUser() {
        DeviceSet deviceSet = new DeviceSet();
        deviceInstrumentServiceTestData.pageByDeviceSetOfCurrentUser(deviceSet);

        Pageable pageable = new PageRequest(1,2);
        Page<DeviceInstrument> deviceInstruments = deviceInstrumentService.pageByDeviceSetOfCurrentUser(deviceSet, pageable);
        logger.info("模拟登录获取第一个标准装置的");
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(2);

        logger.info("模拟登录获取当前部门所有标准装置的");
        DeviceSet deviceSet2 = new DeviceSet();
        deviceInstruments = deviceInstrumentService.pageByDeviceSetOfCurrentUser(deviceSet2, pageable);
        logger.info("模拟登录获取第一个标准装置的");
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(4);

    }

    @Test
    public void pageAllOfCurrentManageDepartmentBySpecification() {
        // 数据初始化
        District district = new District();
        User user = userService.loginWithOneUser();
        String name = CommonService.getRandomStringByLength(10);        // 名称
        DeviceSet deviceSet = new DeviceSet();

        // 调用数据准备
        deviceInstrumentServiceTestData.pageAllOfCurrentManageDepartmentBySpecification(deviceSet, name, user, district);

        // 获取信息
        Pageable pageable = new PageRequest(0, 2);
        Page<DeviceInstrument> deviceInstruments = null;

        logger.info("测试标准装置名ID");
        deviceInstruments = deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(deviceSet.getId(), null, null, null, null, null, null, pageable);
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(1);

        logger.info("测试标准装置名称");
        deviceInstruments = deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, null,null, null, null, null, name, pageable);
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(1);

        logger.info("测试部门, 标准装置名称");
        deviceInstruments = deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, null, user.getDepartment().getId(), null, null, null, null, pageable);
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(1);

        logger.info("测试部门,区域,标准装置姓名");
        deviceInstruments = deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, district.getId(), user.getDepartment().getId(), null, null, null, null, pageable);
        assertThat(deviceInstruments.getTotalElements()).isEqualTo(1);

    }



    // 判断是否拥有检定能力
    @Test
    public void getCheckAbilityByAccuracyAndMeasureScaleAndInstrumentTypeAndDepartment() {
        logger.info("实例化二级器具类别");
        MandatoryInstrumentType mandatoryInstrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        mandatoryInstrumentTypeRepository.save(mandatoryInstrumentType);

        logger.info("实例化精度");
        Accuracy accuracy = AccuracyService.getOneAccuracy();
        accuracy.setWeight(5);
        accuracyRepository.save(accuracy);

        logger.info("实例化最小测量范围");
        MeasureScale minMeasureScale = MeasureScaleService.getOneMeasureScale();
        minMeasureScale.setWeight(5);
        measureScaleRepository.save(minMeasureScale);

        logger.info("实例化最大测量范围");
        MeasureScale maxMeasureScale = MeasureScaleService.getOneMeasureScale();
        maxMeasureScale.setWeight(10);
        measureScaleRepository.save(maxMeasureScale);

        logger.info("实例化部门");
        Department department = departmentService.getOneDepartment();
        departmentRepository.save(department);

        logger.info("实例化计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("调用方法，并断言");
        Boolean ability = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, mandatoryInstrumentType, department);
        assertThat(ability).isFalse();

        logger.info("----------------实例化一个授权检定项目----------------");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setInstrumentType(mandatoryInstrumentType);

        logger.info("实例化精度");
        Accuracy accuracy1 = AccuracyService.getOneAccuracy();
        accuracy1.setWeight(10);
        accuracyRepository.save(accuracy1);

        logger.info("实例化测量范围");
        MeasureScale minMeasureScale1 = MeasureScaleService.getOneMeasureScale();
        minMeasureScale1.setWeight(2);
        measureScaleRepository.save(minMeasureScale1);

        logger.info("实例化测量范围");
        MeasureScale maxMeasureScale1 = MeasureScaleService.getOneMeasureScale();
        maxMeasureScale1.setWeight(20);
        measureScaleRepository.save(maxMeasureScale1);

        logger.info("为实体加属性，并保存");
        deviceInstrument.setAccuracy(accuracy1);
        deviceInstrument.setMinMeasureScale(minMeasureScale1);
        deviceInstrument.setMaxMeasureScale(maxMeasureScale1);
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("调用方法并断言");
        Boolean ability1 = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, mandatoryInstrumentType, department);
        assertThat(ability1).isTrue();

        logger.info("重新设置部门并断言");
        Department department1 = new Department();
        departmentRepository.save(department1);
        deviceSet.setDepartment(department1);
        deviceSetRepository.save(deviceSet);
        logger.info("调用方法并断言");
        Boolean ability6 = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, mandatoryInstrumentType, department);
        assertThat(ability6).isFalse();

        logger.info("恢复部门");
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("重新设置精度权重");
        accuracy1.setWeight(1);
        accuracyRepository.save(accuracy1);

        logger.info("调用方法并断言");
        Boolean ability2 = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, mandatoryInstrumentType, department);
        assertThat(ability2).isFalse();

        logger.info("重新设置测量范围权重并恢复精度");
        accuracy1.setWeight(10);
        accuracyRepository.save(accuracy1);
        minMeasureScale1.setWeight(8);
        measureScaleRepository.save(minMeasureScale1);

        logger.info("调用方法并断言");
        Boolean ability3 = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, mandatoryInstrumentType, department);
        assertThat(ability3).isFalse();

        logger.info("重新设置精度与测量范围权重");
        accuracy1.setWeight(1);
        accuracyRepository.save(accuracy1);
        logger.info("调用方法并断言");
        Boolean ability4 = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, mandatoryInstrumentType, department);
        assertThat(ability4).isFalse();

        minMeasureScale1.setWeight(5);
        measureScaleRepository.save(minMeasureScale1);
        maxMeasureScale1.setWeight(8);
        measureScaleRepository.save(maxMeasureScale1);
        Boolean ability5 = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, mandatoryInstrumentType, department);
        assertThat(ability5).isFalse();
    }

    @Test
    public void deleteTest() {
        logger.info("实例化器具二级类别");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrumentServiceTestData.deleteTest(deviceInstrument);
        logger.info("查找并断言");
        assertThat(deviceInstrumentRepository.findOne(deviceInstrument.getId())).isNotNull();

        logger.info("删除");
        deviceInstrumentService.delete(deviceInstrument.getId());

        logger.info("查找并断言为null");
        assertThat(deviceInstrumentRepository.findOne(deviceInstrument.getId())).isNull();
    }

    @Test
    public void findByIdTest() {
        logger.info("测试数据");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("获取并断言");
        DeviceInstrument deviceInstrument1 = deviceInstrumentService.findById(deviceInstrument.getId());
        assertThat(deviceInstrument1).isNotNull();
    }
}