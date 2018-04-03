package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.DeviceSetRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetailRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;


public class StandardDeviceServiceImplTest extends ServiceTest {
    static private Logger logger = Logger.getLogger(StandardDeviceServiceTest.class.getName());
    @Autowired
    StandardDeviceService standardDeviceService;
    @Autowired
    StandardDeviceRepository standardDeviceRepository;
    @Autowired
    StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository;
    @Qualifier("DepartmentService")
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    UserService userService;
    @Autowired
    DeviceSetRepository deviceSetRepository;

    @Test
    public void updateLastCheckDetail() throws Exception {
        logger.info("设置当前登陆用户");
        User user = userService.getOneSavedUser();
        userService.setCurrentLoginUser(user);

        logger.info("测试同步修改标准器实体的最近检定记录");
        logger.info("创建新的标准器信息");
        StandardDevice standardDevice = new StandardDevice();
        Department department = user.getDepartment();
        logger.info("创建新的计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        logger.info("将当前用户部门保存到计量标准的部门");
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);
        logger.info("将当前计量标准装置保存为标准器的计量标准装置");
        standardDevice.setDeviceSet(deviceSet);
        standardDeviceRepository.save(standardDevice);
        logger.info("为标准器创建鉴定记录");
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        standardDeviceCheckDetail.setStandardDevice(standardDevice);
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);

        logger.info("同步修改标准器实体的最近检定记录");
        standardDeviceService.updateLastCheckDetailByStandardDeviceCheckDetail(standardDeviceCheckDetail);
        logger.info("断言标准器实体的最近检定记录是否更新");
        StandardDevice newStandardDevice = standardDeviceRepository.findOne(standardDeviceCheckDetail.getStandardDevice().getId());
        assertThat(newStandardDevice.getLastCheckDetail().getId()).isEqualTo(standardDeviceCheckDetail.getId());
    }

    // @author panjie
    @Test
    public void getOneSavedStandardDevice() {
        StandardDevice standardDevice =  standardDeviceService.getOneSavedStandardDevice();
        assertThat(standardDevice.getId()).isNotNull();
        assertThat(standardDevice.getDeviceSet()).isNotNull();
    }

    @Test
    public void saveTest() {
        logger.info("获取一个未持久化的实体, 并复制生产企业的名称");
        StandardDevice standardDevice = standardDeviceService.getOneUnSavedStandardDevice();
        Long manufactureDepartmentId = new Long(standardDevice.getManufacturerDepartment().getId());
        Department manufactureDepartment = new Department();
        manufactureDepartment.setName(standardDevice.getManufacturerDepartment().getName());
        standardDevice.setManufacturerDepartment(manufactureDepartment);

        logger.info("调用保存操作");
        standardDeviceService.save(standardDevice);

        logger.info("断言保存的生产企业ID正确");
        StandardDevice standardDevice1 = standardDeviceRepository.findOne(standardDevice.getId());
        assertThat(standardDevice1.getManufacturerDepartment().getId().equals(manufactureDepartmentId));

        logger.info("断言下次检定日期为一年后");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date date = new Date(calendar.getTimeInMillis());
        assertThat(standardDevice.getNextCheckDate().toLocalDate()).isEqualTo(date.toLocalDate());
    }

    @Test
    public void saveTest1() {
        logger.info("获取一个未持久化的实体, 并设置一个生产企业名称");
        StandardDevice standardDevice = standardDeviceService.getOneUnSavedStandardDevice();
        String departmentName = CommonService.getRandomStringByLength(30);
        Department department = standardDevice.getManufacturerDepartment().clone();
        department.setId(null);
        department.setName(departmentName);
        standardDevice.setManufacturerDepartment(department);

        logger.info("调用保存操作");
        standardDeviceService.save(standardDevice);

        logger.info("断言保存的生产企业名称及类型正确");
        StandardDevice standardDevice1 = standardDeviceRepository.findOne(standardDevice.getId());
        assertThat(standardDevice1.getManufacturerDepartment().getName()).isEqualTo(departmentName);
        assertThat(standardDevice1.getManufacturerDepartment().getDepartmentType().getName()).isEqualTo("生产企业");
    }
}