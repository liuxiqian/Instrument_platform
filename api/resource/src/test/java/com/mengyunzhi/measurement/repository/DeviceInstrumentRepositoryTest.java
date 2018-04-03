package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by 安强 on 2017/5/31.
 * 装置授权检定项目 实体仓库测试
 */
public class DeviceInstrumentRepositoryTest extends RepositoryTest {
    private final static Logger logger = Logger.getLogger(DeviceInstrumentRepositoryTest.class.getName());
    @Autowired
    private DeviceSetRepository deviceSetRepository;
    @Autowired
    private AccuracyRepository accuracyRepository;
    @Autowired
    private MeasureScaleRepository measureScaleRepository;
    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    private DeviceInstrumentRepository deviceInstrumentRepository;
    @Autowired private MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository; // 强检器具类别
    @Autowired private DepartmentRepository departmentRepository;   // 部门

    @Test
    public void save() {

        //存精度
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);

        //存测量范围
        MeasureScale measureScale = new MeasureScale();
        measureScaleRepository.save(measureScale);

        //取id
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMinMeasureScale(measureScale);
        deviceInstrumentRepository.save(deviceInstrument);

        //删除实体
        deviceInstrumentRepository.delete(deviceInstrument);
    }

    // 通过器具二级类别及标准装置所属部门查找实体
    @Test
    public void findOneByInstrumentTypeAndDeviceSetDepartment() {
        logger.info("新建强检器具二级类别实体");
        MandatoryInstrumentType mandatoryInstrumentType = new MandatoryInstrumentType();
        mandatoryInstrumentTypeRepository.save(mandatoryInstrumentType);

        logger.info("新建部门");
        Department department = new Department();
        departmentRepository.save(department);

        logger.info("新建标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("新建授权检定项目");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrument.setInstrumentType(mandatoryInstrumentType);

        logger.info("保存实体");
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("调用查询并断言查询实体成功");
        List<DeviceInstrument> deviceInstrument1 = (List<DeviceInstrument>) deviceInstrumentRepository.findAllByInstrumentTypeAndDeviceSetDepartment(mandatoryInstrumentType, department);
        assertThat(deviceInstrument1.size()).isNotZero();
    }

}