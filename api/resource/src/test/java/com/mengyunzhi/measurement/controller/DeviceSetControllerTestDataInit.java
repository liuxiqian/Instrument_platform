package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by panjie on 17/7/19.
 * 计量标准装置：授权检定项目 = N:N
 */
@Component
public class DeviceSetControllerTestDataInit {
    private Logger logger = Logger.getLogger(DeviceSetControllerTestDataInit.class.getName());
    @Autowired
    private DepartmentRepository departmentRepository;                          // 部门
    @Autowired private DeviceSetRepository deviceSetRepository;                 // 标准装置
    @Autowired private DeviceInstrumentRepository deviceInstrumentRepository;   // 授权检定项目
    @Autowired private MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository;   // 强制检定器具
    @Autowired private AccuracyRepository accuracyRepository;   // 精确度
    public void countByDeviceInstrumentAndDeviceSetDepartment(DeviceInstrument deviceInstrument, Department department) {
        logger.info("实例化一个部门");
        departmentRepository.save(department);

        logger.info("为这个部门实例化一个标准装置");
        DeviceSet deviceSet = DeviceSetService.getOneDeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("实例化一个精度");
        Accuracy accuracy = AccuracyService.getOneAccuracy();

        logger.info("实例化一个最小测试范围");
        MeasureScale minMeasureScale = MeasureScaleService.getOneMeasureScale();

        logger.info("实例化一个最大测试范围");
        MeasureScale maxMeasureScale = MeasureScaleService.getOneMeasureScale();

        logger.info("实例化一个 授权检定项目");
        deviceInstrument.setMinMeasureScale(minMeasureScale);
        deviceInstrument.setMaxMeasureScale(maxMeasureScale);
        deviceInstrument.setAccuracy(accuracy);

        logger.info("设置标准装置");
        deviceInstrument.setDeviceSet(deviceSet);

        logger.info("设置强检器具类别实体");
        MandatoryInstrumentType mandatoryInstrumentType = MandatoryInstrumentTypeService.getOneMandatoryInstrumentType();
        mandatoryInstrumentType.addMeasureScale(minMeasureScale);
        mandatoryInstrumentType.addMeasureScale(maxMeasureScale);
        minMeasureScale.setInstrumentType(mandatoryInstrumentType);
        maxMeasureScale.setInstrumentType(mandatoryInstrumentType);
        mandatoryInstrumentType.addAccuracy(accuracy);
        accuracy.setInstrumentType(mandatoryInstrumentType);
        mandatoryInstrumentTypeRepository.save(mandatoryInstrumentType);
        accuracyRepository.save(accuracy);
        deviceInstrument.setInstrumentType(mandatoryInstrumentType);

        logger.info("保存授权项目");
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("为标准装置添加一个授权检定项目");
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSetRepository.save(deviceSet);
    }
}
