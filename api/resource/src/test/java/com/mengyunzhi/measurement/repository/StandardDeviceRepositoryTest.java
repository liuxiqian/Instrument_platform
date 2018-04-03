package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.StandardDeviceService;
import com.mengyunzhi.measurement.Service.StandardDeviceServiceTest;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.StandardDevice;
import com.mengyunzhi.measurement.entity.StandardFile;
import com.mengyunzhi.measurement.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StandardDeviceRepositoryTest {
    static private Logger logger = Logger.getLogger(StandardDeviceServiceTest.class.getName());
    @Autowired
    private StandardDeviceRepository standardDeviceRepository;
    @Autowired
    private StandardFileRepository standardFileRepository;
    @Autowired
    private StandardDeviceService standardDeviceService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Value("${yunzhi.warn_before_day}")
    private int warnBeforeDay;    // 預警的间隔天数
    @Test
    public void save() {
        //获取一个standardFile
        StandardFile standardFile = new StandardFile();
        standardFileRepository.save(standardFile);
        //获取一个操作用户
        User user = new User();
        userRepository.save(user);
        //获取一个部门
        Department department = new Department();
        departmentRepository.save(department);

        StandardDevice standardDevice = new StandardDevice();
        //保存
        standardDeviceRepository.save(standardDevice);
        //断言
        assertThat(standardDevice.getId()).isNotNull();
    }

    @Test
    public void findAllBylastCheckDateBetweenAndDepartment() throws Exception {
        logger.info("持久化一个标准器实体，下次鉴定日期距今天在预警间隔天数以内");
        User user = userService.getOneSavedUser();

        StandardDevice standardDevice = standardDeviceService.getOneSavedStandardDevice();
        user.setDepartment(standardDevice.getDeviceSet().getDepartment());
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        // 计算预警日期
        calendar.add(Calendar.DAY_OF_YEAR, warnBeforeDay - 1);
        Date warnDay = new Date(calendar.getTimeInMillis());

        standardDevice.setNextCheckDate(warnDay);

        standardDeviceRepository.save(standardDevice);

        logger.info("新建一个 分页");
        PageRequest pageRequest = new PageRequest(0, 10);

        logger.info("查找当前预警记录");
        Page<StandardDevice> standardDeviceWarn = standardDeviceRepository.findAllByDepartmentAndNextCheckDateBetweenTodayAndNextDay(warnDay, user.getDepartment(), pageRequest);
        logger.info("断言是否成功");
        assertThat(standardDeviceWarn.getTotalPages()).isEqualTo(1);
        assertThat(standardDeviceWarn.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void findAllBylastCheckDateDepartment() throws Exception {
        logger.info("持久化一个标准器实体，下次鉴定日期早于今天");
        StandardDevice standardDevice = standardDeviceService.getOneSavedStandardDevice();
        logger.info("创建一个用户，将该用户的部门设置成当前标准器所述的部门");
        User user = userService.getOneSavedUser();
        user.setDepartment(standardDevice.getDeviceSet().getDepartment());
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        // 设置一个早于当前时间的时间
        calendar.add(Calendar.DAY_OF_YEAR, - 10);
        Date warnDay = new Date(calendar.getTimeInMillis());
        logger.info("设置该标准器的下次鉴定时间早于今天");
        standardDevice.setNextCheckDate(warnDay);

        standardDeviceRepository.save(standardDevice);

        logger.info("新建一个 分页");
        Pageable pageRequest = new PageRequest(0, 1);

        logger.info("查找当前预警记录");
        Page<StandardDevice> standardDeviceWarn = standardDeviceRepository.findAllDepartmentAndByNextCheckDateBeforeToday(user.getDepartment(), pageRequest);
        logger.info("断言是否成功");
        assertThat(standardDeviceWarn.getTotalPages()).isEqualTo(1);
        assertThat(standardDeviceWarn.getTotalElements()).isEqualTo(1);
    }
}