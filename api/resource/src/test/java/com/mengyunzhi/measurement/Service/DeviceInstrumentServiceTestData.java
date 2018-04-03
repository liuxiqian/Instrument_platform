package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by panjie on 17/8/9.
 */
@Component
public class DeviceInstrumentServiceTestData {
    private final static Logger logger = Logger.getLogger(DeviceInstrumentServiceTestData.class.getName());
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
    @Autowired private DistrictRepository districtRepository;           // 区域
    @Autowired private MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository; // 强检器具类别

    public User pageByDeviceSetOfCurrentUser(DeviceSet deviceSet) {
        logger.info("新建一个用户登录");
        User user = userService.loginWithOneUser();

        logger.info("新建两个标准装置");
        logger.info("在每个标准装置上都增加这两个授权检定项目");
        deviceSet.setDepartment(user.getDepartment());
        deviceSetRepository.save(deviceSet);

        logger.info("新增两个授权检定项目");
        DeviceInstrument deviceInstrument = deviceInstrumentService.getOneUnSavedDeviceInstrument();
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrumentRepository.save(deviceInstrument);

        DeviceInstrument deviceInstrument1 = deviceInstrumentService.getOneUnSavedDeviceInstrument();
        deviceInstrument1.setDeviceSet(deviceSet);
        deviceInstrumentRepository.save(deviceInstrument1);

        logger.info("新增第二个标准装置");
        DeviceSet deviceSet1 = new DeviceSet();
        deviceSet1.setDepartment(user.getDepartment());
        deviceSetRepository.save(deviceSet1);

        logger.info("新增两个授权检定项目");
        DeviceInstrument deviceInstrument2 = deviceInstrumentService.getOneUnSavedDeviceInstrument();
        deviceInstrument2.setDeviceSet(deviceSet1);
        deviceInstrumentRepository.save(deviceInstrument2);

        DeviceInstrument deviceInstrument3 = deviceInstrumentService.getOneUnSavedDeviceInstrument();
        deviceInstrument3.setDeviceSet(deviceSet1);
        deviceInstrumentRepository.save(deviceInstrument3);

        return user;
    }

    public void pageAllByCurrentUserOfDeviceInstrument() {
        logger.info("pageAllByCurrentUserOfDeviceInstrument");
        logger.info("创建一个部门");
        Department department = new Department();
        DepartmentType departmentType = departmentTypeRepository.getByName("技术机构");
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);
        logger.info("设置当前登陆用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.clearCurrentTestLoginUser();
        userService.setCurrentLoginUser(user);

        logger.info("新建器具类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setName("123");
        instrumentType.setPinyin("123");
        instrumentTypeRepository.save(instrumentType);

        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("hahaha");
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("新建精度实体");
        Accuracy accuracy = new Accuracy();
        Accuracy accuracy1 = new Accuracy();
        accuracyRepository.save(accuracy);
        accuracyRepository.save(accuracy1);

        logger.info("测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScale.setInstrumentType(instrumentType);
        MeasureScale measureScale1 = new MeasureScale();
        measureScale1.setInstrumentType(instrumentType);
        measureScaleRepository.save(measureScale);
        measureScaleRepository.save(measureScale1);

        logger.info("新建授权鉴定项目实体");
        Set<DeviceInstrument> deviceInstruments = new HashSet<>();
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        DeviceInstrument deviceInstrument1 = new DeviceInstrument();

        deviceInstrument.setMinMeasureScale(measureScale);
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrument.setInstrumentType(instrumentType);

        deviceInstrument1.setDeviceSet(deviceSet);
        deviceInstrument1.setMinMeasureScale(measureScale1);
        deviceInstrument1.setAccuracy(accuracy1);
        logger.info("避免和上条数据的关键字重复，重新生成一个强检器具类别");
        MandatoryInstrumentType mandatoryInstrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        instrumentTypeRepository.save(mandatoryInstrumentType);
        deviceInstrument1.setInstrumentType(mandatoryInstrumentType);

        deviceInstrumentRepository.save(deviceInstrument);
        deviceInstrumentRepository.save(deviceInstrument1);
        deviceInstruments.add(deviceInstrument);
        deviceInstruments.add(deviceInstrument1);

        deviceSet.setDeviceInstruments(deviceInstruments);
        deviceSetRepository.save(deviceSet);
    }

    public void pageAllOfCurrentManageDepartmentBySpecification(String name, User user) {
        DeviceSet deviceSet = new DeviceSet();
        District district = new District();
        this.pageAllOfCurrentManageDepartmentBySpecification(deviceSet, name, user, district);
        return;
    }

    public void pageAllOfCurrentManageDepartmentBySpecification(DeviceSet deviceSet, String name, User user,  District district) {
        logger.info("新建两个区域");
        districtRepository.save(district);
        District district1 = new District();
        district1.setParentDistrict(district);
        districtRepository.save(district1);

        logger.info("使用用户登录");
        user.getDepartment().setDistrict(district);
        departmentRepository.save(user.getDepartment());

        logger.info("创建标准装置");
        deviceSet.setName(name);
        deviceSet.setDepartment(user.getDepartment());
        deviceSetRepository.save(deviceSet);

        logger.info("创建一个授权检定项目");
        InstrumentType instrumentType = InstrumentTypeService.getOneInstrumentType();
        instrumentTypeRepository.save(instrumentType);
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrument.setInstrumentType(instrumentType);
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("回写OneToMany使用数据统一");
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSetRepository.save(deviceSet);
    }

    public void deleteTest(DeviceInstrument deviceInstrument) {
        logger.info("实例化器具二级类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentTypeRepository.save(instrumentType);

        logger.info("实例化标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);

        logger.info("实例化装置授权检定项目");
        deviceInstrument.setInstrumentType(instrumentType);
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrumentRepository.save(deviceInstrument);
    }

    public void saveOrUpdate(DeviceInstrument deviceInstrument) {
        logger.info("新建器具二级类别");
        MandatoryInstrumentType mandatoryInstrumentType = new MandatoryInstrumentType();
        mandatoryInstrumentTypeRepository.save(mandatoryInstrumentType);

        logger.info("新建计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("hahaha");
        deviceSetRepository.save(deviceSet);

        logger.info("新建精度实体");
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);

        logger.info("测量范围");
        MeasureScale measureScale = new MeasureScale();
        measureScaleRepository.save(measureScale);

        logger.info("新建授权鉴定项目实体");
        deviceInstrument.setMinMeasureScale(measureScale);
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrument.setInstrumentType(mandatoryInstrumentType);
    }
}
