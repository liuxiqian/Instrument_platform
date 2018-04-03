package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.ApplyRepository;
import com.mengyunzhi.measurement.repository.CurrentWorkRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentApplyRepository;
import com.mengyunzhi.measurement.repository.WorkRepository;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author panjie on 2018/1/17
 * 当前工作
 */
public class CurrentWorkServiceImplTest extends ServiceTest {
    static final Logger logger = LoggerFactory.getLogger(CurrentWorkServiceImplTest.class.getName());
    @Qualifier("WorkService")
    @Autowired
    WorkService workService;
    @Autowired
    CurrentWorkService currentWorkService;   // 当前工作
    @Autowired
    WorkRepository workRepository;  // 工作
    @Autowired
    CurrentWorkRepository currentWorkRepository;    //当前工作
    @Autowired UserService userService; // 用户
    @Autowired MandatoryInstrumentApplyService mandatoryInstrumentApplyService; // 强检器具申请
    @Autowired
    MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;// 强检器具申请
    @Qualifier("ApplyService")
    @Autowired ApplyService applyService; // 申请
    @Autowired
    ApplyRepository applyRepository;// 申请
    @Autowired MandatoryInstrumentWorkService mandatoryInstrumentWorkService; // 强检器具备案工作

    @Test
    public void addWork() {
        logger.debug("添加首记录");
        Work work = workService.getOneSavedWork();
        currentWorkService.addWork(work);
        CurrentWork currentWork = currentWorkRepository.findByWork(work);
        assertThat(currentWork).isNotNull();

        logger.debug("重复添加相同申请，相同审核部门的记录");
        Work work1 = new Work();
        work1.setApply(work.getApply());
        work1.setAuditDepartment(work.getAuditDepartment());
        work1.setPreWork(work);
        workRepository.save(work1);
        currentWorkService.addWork(work1);

        logger.debug("断言第二次覆盖了第一次的记录");
        CurrentWork currentWork1 = currentWorkRepository.findByApplyAndCheckDepartment(work.getApply(), work.getAuditDepartment());
        assertThat(currentWork.getId()).isEqualTo(currentWork1.getId());

        logger.debug("添加相同申请，不同检定部门的记录");
        Work work2 = workService.getOneSavedWork();
        work2.setApply(work.getApply());
        workRepository.save(work2);
        currentWorkService.addWork(work2);

        logger.debug("断言该申请有两条工作记录");
        List<CurrentWork> currentWorkList = currentWorkRepository.findAllByApply(work.getApply());
        assertThat(currentWorkList.size()).isEqualTo(2);
    }

    @Test
    public void pageByAuditDepartmentAndApplyStartCalendarAndApplyEndCalendarAndApplyDepartmentAndApplyClassName() {

        logger.info("设置当前登陆用户");
        User user = userService.getOneSavedUser();
        userService.setCurrentLoginUser(user);
        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("设置日期");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(200000L);

        logger.info("新建并保存一个工作");
        Work work = workService.getOneUnSavedWork();
        work.setAuditDepartment(department);
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyService.getOneSavedMandatoryInstrumentApply();
        mandatoryInstrumentApply.setApplyTime(calendar);
        mandatoryInstrumentApply.setDepartment(department);
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        work.setApply(mandatoryInstrumentApply);
        workRepository.save(work);
        currentWorkService.addWork(work);

        logger.info("新建第二个工作");
        Work work1 = workService.getOneUnSavedWork();
        work1.setAuditDepartment(department);
        Apply apply = applyService.getOneSavedApply();
        apply.setApplyTime(calendar);
        apply.setDepartment(department);
        applyRepository.save(apply);
        work1.setApply(apply);
        applyRepository.save(apply);
        workRepository.save(work1);
        currentWorkService.addWork(work1);

        logger.info("查询");
        Pageable pageable = new PageRequest(0, 2);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTimeInMillis(100000L);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(300000L);
        Page<CurrentWork> currentWorks = currentWorkService.pageByAuditDepartmentAndApplyStartCalendarAndApplyEndCalendarAndApplyDepartmentAndApplyClass(
                work.getAuditDepartment(), startCalendar, endCalendar, null, MandatoryInstrumentApply.class, pageable);

        logger.info("断言获取的总数为1");
        assertThat(currentWorks.getTotalElements()).isEqualTo(1);

        currentWorks = currentWorkService.pageByAuditDepartmentAndApplyStartCalendarAndApplyEndCalendarAndApplyDepartmentAndApplyClass(
                work.getAuditDepartment(), startCalendar, endCalendar, department, MandatoryInstrumentApply.class, pageable);

        logger.info("断言获取的总数为1");
        assertThat(currentWorks.getTotalElements()).isEqualTo(1);
    }

}