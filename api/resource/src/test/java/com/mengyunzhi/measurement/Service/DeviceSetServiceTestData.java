package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liming on 17-8-13.
 */
@Component
public class DeviceSetServiceTestData {
    private static final Logger logger = Logger.getLogger(DeviceSetServiceTestData.class.getName());
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

    public void pageAllOfCurrentUserBySpecification(String name, String code, Department department, District district, DeviceSet deviceSet, District district1, User user) {
        //设置当前登录用户
        user.setName("123");

        //只传入标准装置名称
        deviceSet.setName(name);
        deviceSetRepository.save(deviceSet);

        //只传入代码,标准装置名层
        deviceSet.setCode(code);
        deviceSetRepository.save(deviceSet);

        //只传入部门,代码,标准装置名称
        departmentRepository.save(department);
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        //传入部门，区域，器具名称，代码
        districtRepository.save(district);
        //设置父区域
        district1.setParentDistrict(district);
        districtRepository.save(district1);
        department.setDistrict(district);
        departmentRepository.save(department);
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);
    }

    public DeviceSet updateDeviceInstrumentsById(String name, DeviceInstrument deviceInstrument) {
        logger.info("新建二级强检器具类别");
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

        deviceInstrumentRepository.save(deviceInstrument);

        Set<DeviceInstrument> deviceInstruments = new HashSet<>();
        deviceSet.setDeviceInstruments(deviceInstruments);
        deviceSet.setName(name);
        DeviceSet newDeviceSet = deviceSetService.updateDeviceInstrumentsById(deviceSet.getId(), deviceSet);
        return newDeviceSet;
    }
}
