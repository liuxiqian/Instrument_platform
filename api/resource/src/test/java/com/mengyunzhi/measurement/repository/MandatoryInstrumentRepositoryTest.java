package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.DepartmentService;
import com.mengyunzhi.measurement.Service.InstrumentCheckInfoService;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentService;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.entity.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;


public class MandatoryInstrumentRepositoryTest extends RepositoryTest {
    final static Logger logger = Logger.getLogger(MandatoryInstrumentRepositoryTest.class.getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired
    UserService userService;    // 登录用户
    @Qualifier("DepartmentService")
    @Autowired
    DepartmentService departmentService;
    @Qualifier("InstrumentCheckInfoService")
    @Autowired
    InstrumentCheckInfoService instrumentCheckInfoService;
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;
    @Autowired
    AccuracyRepository accuracyRepository;              // 精确度等级
    @Autowired
    DistrictRepository districtRepository;              // 区域
    @Autowired
    InstrumentTypeRepository instrumentTypeRepository;  // 器具类别
    @Autowired
    DepartmentRepository departmentRepository;          // 部门

    @Test
    public void findAllByStatusAndDepartmentAndNextCheckDateBetween() throws Exception {
        logger.debug("获取一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        MandatoryInstrument mandatoryInstrument1 = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument1);

        logger.debug("设置鉴定时间在30天内，和状态,和用户");
        mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 5);
        mandatoryInstrument.setNextCheckDate(new Date(calendar.getTimeInMillis()));
        User user = userService.getOneSavedUser();
        mandatoryInstrument.setCreateUser(user);
        mandatoryInstrument.setDepartment(user.getDepartment());

        logger.debug("新建一个 分页");
        PageRequest pageRequest = new PageRequest(1, 1);

        logger.debug("设置鉴定时间超过30天，和状态,和用户");
        mandatoryInstrument1.setStatus(MandatoryInstrument.STATUS_NORMAL);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 35);
        mandatoryInstrument1.setNextCheckDate(new Date(calendar1.getTimeInMillis()));
        mandatoryInstrument1.setCreateUser(user);
        mandatoryInstrument1.setDepartment(user.getDepartment());

        logger.debug("保存");
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mandatoryInstrumentRepository.save(mandatoryInstrument1);

        logger.debug("查找数据并断言");
        calendar = Calendar.getInstance();
        Date nowDate = new Date(calendar.getTimeInMillis()); // 当前时间

        calendar.add(Calendar.DAY_OF_YEAR, MandatoryInstrument.WARN_BEFORE_DAYS);
        Date warnDate = new Date(calendar.getTimeInMillis());

        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.
                findAllByStatusAndDepartmentAndNextCheckDateBetween(MandatoryInstrument.STATUS_NORMAL, user.getDepartment(), nowDate, warnDate, pageRequest);
        assertThat(mandatoryInstruments.getTotalPages()).isEqualTo(1);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);
    }


    @Test
    public void findAllByStatusAndDepartmentAndNextCheckDateBefore() throws Exception {
        logger.debug("获取一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        MandatoryInstrument mandatoryInstrument1 = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument1);

        logger.debug("设置鉴定时间在当前日期之前，和状态,和用户");
        mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -5);
        mandatoryInstrument.setNextCheckDate(new Date(calendar.getTimeInMillis()));
        User user = userService.getOneSavedUser();
        mandatoryInstrument.setCreateUser(user);
        mandatoryInstrument.setDepartment(user.getDepartment());
        Date nowDate = new Date(Calendar.getInstance().getTime().getTime()); // 当前时间

        logger.debug("新建一个 分页");
        PageRequest pageRequest = new PageRequest(0, 1);

        logger.debug("设置鉴定时间在当前日期之前，和状态,和用户");
        mandatoryInstrument1.setStatus(MandatoryInstrument.STATUS_NORMAL);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, 5);
        mandatoryInstrument1.setNextCheckDate(new Date(calendar1.getTimeInMillis()));
        mandatoryInstrument1.setCreateUser(user);
        mandatoryInstrument1.setDepartment(user.getDepartment());

        logger.debug("保存");
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mandatoryInstrumentRepository.save(mandatoryInstrument1);

        logger.debug("查找数据并断言");
        Page<MandatoryInstrument> mandatoryInstruments
                = mandatoryInstrumentRepository.findAllByStatusAndDepartmentAndNextCheckDateBefore(
                MandatoryInstrument.STATUS_NORMAL, user.getDepartment(), nowDate, pageRequest);
        assertThat(mandatoryInstruments.getTotalPages()).isEqualTo(1);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void findAllGenerativeDepartment() {
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneSavedMandatoryInstrument();
        Department department = departmentService.getOneSavedDepartment();
        mandatoryInstrument.setGenerativeDepartment(department);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        List<Department> generativeDepartments = mandatoryInstrumentRepository.findAllGenerativeDepartment();

        assertThat(generativeDepartments).isNotEmpty();
    }

    @Test
    public void countByAccuracyAndInstrumentTypeAndDistrict() throws Exception {
        logger.info("新建一个准确度等级");
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);
        logger.info("新建一个器具类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentTypeRepository.save(instrumentType);
        logger.info("新建一个区域");
        District district = new District();
        districtRepository.save(district);
        Long districtId = district.getId();
        logger.info("新建一个部门");
        Department department = new Department();
        department.setDistrict(district);
        departmentRepository.save(department);
        logger.info("新建一个器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setAccuracy(accuracy);
        mandatoryInstrument.setInstrumentType(instrumentType);
        mandatoryInstrument.setDepartment(department);
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        logger.info("调用查询");
        int num = mandatoryInstrumentRepository.countByAccuracyAndInstrumentTypeAndDistrict(accuracy, instrumentType, districtId);
        logger.info("断言查询到的结果不为0");
        assertThat(num).isNotZero();
    }
}