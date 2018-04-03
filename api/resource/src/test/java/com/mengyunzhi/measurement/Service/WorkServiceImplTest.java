package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.hibernate.ObjectNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-7-15.
 * 工作
 */
public class WorkServiceImplTest extends ServiceTest{
    static private Logger logger = LoggerFactory.getLogger(WorkServiceImplTest.class.getName());
    @Autowired protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected WorkRepository workRepository;
    @Autowired @Qualifier("WorkService") protected WorkService workService;
    @Autowired private ApplyRepository applyRepository; // 申请
    @Autowired @Qualifier("ApplyService") private ApplyService applyService;       // 申请
    @Autowired private UserRepository userRepository;       // 用户
    @Autowired private UserService  userService;            // 用户
    @Autowired private WorkflowNodeRepository workflowNodeRepository;       // 工作流结点
    @Autowired WorkflowNodeService workflowNodeService; // 工作流节点
    @Autowired ApplyTypeService applyTypeService;   // 申请类型
    @Autowired CurrentWorkRepository currentWorkRepository; // 当前工作
    @Autowired CurrentWorkService currentWorkService;   // 当前工作
    @Autowired @Qualifier("DepartmentService") DepartmentService departmentService; // 部门
    @Autowired MandatoryInstrumentApplyService mandatoryInstrumentApplyService; // 强检申请
    private User user;
    @Before
    public void dataInit() {
        logger.info("登录用户");
        user = userService.loginWithOneUser();
    }

    @After
    public void deleteData () {
        userRepository.delete(user);
    }
    @Test
    public void pageApplyByAuditDepartmentOfMandatoryInstrument() throws Exception {
        logger.info("新建一个部门");
        Department department = new Department();
        departmentRepository.save(department);
        logger.info("新建一个强检申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        logger.info("新建一个工作");
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        work.setAuditDepartment(department);
        workRepository.save(work);

        logger.info("将工作存入当前工作列表");
        currentWorkService.addWork(work);

        logger.info("测试数据");
        final PageRequest pageRequest = new PageRequest(1,1);
        Page<Apply> page = workService.pageApplyByAuditDepartmentOfMandatoryInstrument(department, pageRequest);
        logger.info("断言");
        assertThat(page.getTotalPages()).isEqualTo(1);
    }

    @Test
    public void updateToDoingByWorkId() {
        logger.info("新建一个工作，先保存，然后设置为在办");
        Work work = workService.getOneUnSavedWork();
        workRepository.save(work);

        logger.debug("将工作添加到工作列表");
        currentWorkService.addWork(work);

        workService.updateToHandlingByWork(work);
        CurrentWork currentWork = currentWorkRepository.findByWork(work);
        assertThat(currentWork.getWork().getStatus()).isEqualTo(Work.HANDLING);

        workService.updateToHandlingByWorkId(work.getId());
        currentWork = currentWorkRepository.findByWork(work);
        assertThat(currentWork.getWork().getStatus()).isEqualTo(Work.HANDLING);

        logger.info("查找不存在的，断言发生异常");
        Long id = -1L;
        Boolean getException = false;
        try {
            workService.updateToHandlingByWorkId(id);
        } catch (ObjectNotFoundException e) {
            getException = true;
        }

        assertThat(getException).isTrue();
        workRepository.delete(work);
    }

    @Test
    public void updateToDoneByWorkId() {
        logger.info("新建一个工作，并设置为完成，然后断言");
        Work work = workService.getOneUnSavedWork();
        workRepository.save(work);
        currentWorkService.addWork(work);
        workService.updateToDoneByWorkId(work.getId());
        CurrentWork currentWork = currentWorkRepository.findByWork(work);
        assertThat(currentWork.getWork().getStatus()).isEqualTo(Work.DONE);
    }

    @Test
    public void doneByWork() {
        userService.loginWithOneUser();

        logger.info("新建工作及其对应的申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = MandatoryInstrumentApplyService.getOneMandatoryInstrumentApply();
        mandatoryInstrumentApply.setCreateUser(userService.getOneSavedUser());
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        Work work = workService.getOneUnSavedWork();
        work.setApply(mandatoryInstrumentApply);
        workService.saveWork(work);

        logger.info("设置为办结");
        workService.updateToDoneByWork(work);

        Work work1 = workRepository.findOne(work.getId());
        assertThat(work1.getApply().getDone()).isTrue();
    }

    /**
     * 退回提交人
     */
    @Test
    public void backToPreDepartment() {
        logger.info("新建部门");
        Department department = departmentService.getOneDepartment();
        departmentRepository.save(department);

        logger.info("新建申请");
        Apply apply = applyService.getOneSavedApply();
        applyRepository.save(apply);

        logger.info("新建两个工作");
        Work work = workService.getOneUnSavedWork();
        work.setApply(apply);
        work.setAuditDepartment(department);
        workRepository.save(work);

        Work work1 = workService.getOneUnSavedWork();
        work1.setApply(apply);
        work1.setPreWork(work);
        work1.setAuditDepartment(department);
        workRepository.save(work1);

        logger.info("新建用户，并设置为当前用户");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);

        logger.info("调用退回上一审核部门方法");
        workService.backToPreDepartment(work1);

        logger.info("获取并断言数据");
        Work work2 = workRepository.findOneByPreWork(work1);
        assertThat(work2.getAliasWork().getId()).isEqualTo(work.getId());
        assertThat(work2.getAuditDepartment().getId()).isEqualTo(department.getId());
    }

    // 送下一审核部门
    @Test
    public void sendToNextDepartment() {
        logger.info("新建一一个申请");
        Apply apply = applyService.getOneSavedApply();
        applyRepository.save(apply);
        logger.info("新建3个部门");
        Department department = departmentService.getOneDepartment();
        departmentRepository.save(department);
        Department department1 = departmentService.getOneDepartment();
        departmentRepository.save(department1);
        Department department2 = departmentService.getOneDepartment();
        departmentRepository.save(department2);

        logger.info("新建一个工作流结点");
        WorkflowNode workflowNode = workflowNodeService.getOneWorkflowNode();
        workflowNodeRepository.save(workflowNode);

        Work work = workService.getOneUnSavedWork();
        work.setAuditDepartment(department);
        work.setApply(apply);
        workRepository.save(work);

        logger.info("新建下一工作，及审核信息");
        Work nextWork = workService.getOneUnSavedWork();
        nextWork.setAuditDepartment(department1);
        nextWork.setWorkflowNode(workflowNode);
        work.setNextWork(nextWork);

        logger.info("登录用户");
        User user = userService.loginWithOneUser();

        logger.info("审核当前工作，并送下一部门");
        workService.sendToNextDepartment(workRepository.findOne(work.getId()), work);
        Work work1 = workRepository.findOneByPreWork(work);
        assertThat(work1.getAuditDepartment().getId()).isEqualTo(department1.getId());
        assertThat(work1.getPreWork().getId()).isEqualTo(work.getId());
        assertThat(work1.getWorkflowNode().getId()).isEqualTo(workflowNode.getId());


        logger.info("新建下一工作，及审核信息");
        Work nextWork1 = workService.getOneUnSavedWork();
        nextWork1.setAuditDepartment(department2);
        nextWork1.setWorkflowNode(workflowNode);
        work1.setNextWork(nextWork1);

        logger.info("再送下一部门");
        workService.sendToNextDepartment(workRepository.findOne(work1.getId()), work1);
        Work work2 = workRepository.findOneByPreWork(work1);
        assertThat(work2.getAuditDepartment().getId()).isEqualTo(department2.getId());

        logger.info("退回给上一部门");
        workService.backToPreDepartment(work2);
        Work work3 = workRepository.findOneByPreWork(work2);
        assertThat(work3.getAuditDepartment().getId()).isEqualTo(department1.getId());

        logger.info("退回上一申请部门");
        workService.backToApplyDepartment(work3);
        Work work4 = workRepository.findOneByPreWork(work3);
        assertThat(work4.getAuditDepartment().getId()).isEqualTo(department.getId());


        workRepository.delete(work4);
        workRepository.delete(work3);
        workRepository.delete(work2);
        workRepository.delete(work1);
        workRepository.delete(work);
        userRepository.delete(user);
        workflowNodeRepository.delete(workflowNode);
        departmentRepository.delete(department);

    }

    @Test
    public void saveNewWork() {
        Work work = workService.getOneUnSavedWork();
        User user = userService.loginWithOneUser();
        workService.saveWorkWithCurrentUserAudit(work);

        Work work1 = workRepository.findOne(work.getId());
        assertThat(work1.getAuditDepartment().getId()).isEqualTo(user.getDepartment().getId());

        CurrentWork currentWork = currentWorkRepository.findByWork(work1);
        assertThat(currentWork.getWork().getStatus()).isEqualTo(Work.UN_HANDLE);

    }

    @Test
    public void getById() {
        Work work = workService.getOneUnSavedWork();
        workRepository.save(work);
        Work work1 = workService.getById(work.getId());
        assertThat(work1.getId()).isEqualTo(work.getId());
    }


    /**
     * 获取指定部门指指定申请类型名称并以申请分组的分页数据
     * panjie
     */
    @Test
    public void pageByDepartmentAndApplyTypeFlagGroupByApply() {
        logger.info("获取一个申请");
        Apply apply = mandatoryInstrumentApplyService.getOneSavedMandatoryInstrumentApply();

        logger.info("获取一个部门");
        Department department = departmentService.getOneSavedDepartment();

        logger.info("在这个申请上添加3个工作，其中第1，2个工作是的审核部门为当前部门");
        for (Integer i = 0; i < 3; i++) {
            Work work = workService.getOneUnSavedWork();
            work.setApply(apply);
            work.setOpinion(i.toString());
            if (i < 2) {
                work.setAuditDepartment(department);
            }

            logger.info("持久化");
            workRepository.save(work);
            currentWorkService.addWork(work);
        }

        logger.info("查询，断言查到了第2个工作");
        Page<Work> workPage = workService.pageByAuditDepartmentAndApplyClassName(department, MandatoryInstrumentApply.CLASS_NAME, new PageRequest(0, 2));
        assertThat(workPage.getTotalElements()).isEqualTo(1);
        Work work = workPage.getContent().get(0);
        assertThat(work.getOpinion()).isEqualTo("1");
    }

}