package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author panjie on 2018/1/17
 */
public class CurrentWorkRepositoryTest extends RepositoryTest {
    private final static Logger logger = LoggerFactory.getLogger(CurrentWorkRepositoryTest.class.getName());
    @Autowired
    CurrentWorkRepository currentWorkRepository; // 当前工作
    @Autowired
    DepartmentRepository departmentRepository;   // 部门
    @Autowired
    ApplyRepository applyRepository; // 申请
    @Autowired
    WorkRepository workRepository;   // 工作
    @Autowired
    MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;   // 强检申请

    @Test
    public void findAddTest() {
        currentWorkRepository.findAll();
    }

    @Test
    public void CurrentWorkRepository() {
        Apply apply = new Apply();
        applyRepository.save(apply);

        Department department = new Department();
        departmentRepository.save(department);

        Work work = new Work();
        work.setApply(apply);
        work.setAuditDepartment(department);
        workRepository.save(work);

        CurrentWork currentWork = new CurrentWork();
        currentWork.setWork(work);
        currentWorkRepository.save(currentWork);

        CurrentWork currentWork1 = currentWorkRepository.findByApplyAndCheckDepartment(currentWork.getWork().getApply(), currentWork.getWork().getAuditDepartment());
        assertThat(currentWork1.getId()).isEqualTo(currentWork.getId());
    }

    @Test
    public void findByApply() {
        Apply apply = new Apply();
        applyRepository.save(apply);

        Work work1 = new Work();
        work1.setApply(apply);
        workRepository.save(work1);

        Work work2 = new Work();
        work2.setApply(apply);
        workRepository.save(work2);

        CurrentWork currentWork = new CurrentWork();
        currentWork.setWork(work1);
        currentWorkRepository.save(currentWork);

        CurrentWork currentWork1 = new CurrentWork();
        currentWork1.setWork(work2);
        currentWorkRepository.save(currentWork1);

        List<CurrentWork> currentWorkList = (List<CurrentWork>) currentWorkRepository.findAllByApply(apply);
        assertThat(currentWorkList.size()).isEqualTo(2);

    }

    @Test
    public void pageByAuditDepartmentAndApplyClassName() {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        Department department = new Department();
        departmentRepository.save(department);

        Work work = new Work();
        work.setAuditDepartment(department);
        work.setApply(mandatoryInstrumentApply);
        workRepository.save(work);

        CurrentWork currentWork = new CurrentWork();
        currentWork.setWork(work);
        currentWorkRepository.save(currentWork);

        Pageable pageable = new PageRequest(0, 1);
        Page<Work> workPage = currentWorkRepository.pageWorkByWorkAuditDepartmentAndWorkApplyClassName(department, MandatoryInstrumentApply.CLASS_NAME, pageable);
        assertThat(workPage.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void pageApplyByWorkAuditDepartment() {
        logger.debug("实例化一个当前工作");
        Apply apply = new Apply();
        applyRepository.save(apply);

        Department department = new Department();
        departmentRepository.save(department);

        Work work = new Work();
        work.setAuditDepartment(department);
        work.setApply(apply);
        workRepository.save(work);

        CurrentWork currentWork = new CurrentWork();
        currentWork.setWork(work);
        currentWorkRepository.save(currentWork);

        logger.info("分页查询");
        Pageable pageable = new PageRequest(0, 1);
        Page<Apply> applyPage = currentWorkRepository.pageApplyByWorkAuditDepartment(department, pageable);
        assertThat(applyPage.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void findAllByWorkAuditDepartmentAndStatus() {
        Apply apply = new Apply();
        applyRepository.save(apply);

        Department department = new Department();
        departmentRepository.save(department);

        Work work = new Work();
        work.setAuditDepartment(department);
        work.setApply(apply);
        work.setStatus(Work.UN_HANDLE);
        workRepository.save(work);

        CurrentWork currentWork = new CurrentWork();
        currentWork.setWork(work);

        currentWorkRepository.save(currentWork);


        List<Work> workList = currentWorkRepository.findAllByWorkAuditDepartmentAndStatus(department, Work.UN_HANDLE);
        assertThat(workList.size()).isEqualTo(1);

        work.setStatus(Work.HANDLED);
        workRepository.save(work);
        workList = currentWorkRepository.findAllByWorkAuditDepartmentAndStatus(department, Work.HANDLED);
        assertThat(workList.size()).isEqualTo(1);
    }
}