package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by liming on 17-7-14.
 * 工作
 */
@Service("WorkService")
public class WorkServiceImpl implements WorkService {
    private Logger logger = Logger.getLogger(WorkServiceImpl.class.getName());
    @Autowired
    protected WorkRepository workRepository;
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("ApplyService")
    private ApplyService applyService;       // 申请
    @Autowired
    private UserService userService;         // 用户
    @Autowired
    private MandatoryInstrumentApplyService mandatoryInstrumentApplyService;        // 强制检定器具申请审核
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;      // 强制检定器具申请审核
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;        //强检器具
    @Autowired
    WorkflowNodeRepository workflowNodeRepository;   // 工作流结点
    @Autowired
    DistrictService districtService; // 区域
    @Autowired
    @Qualifier("DepartmentService")
    DepartmentService departmentService; // 部门
    @Autowired
    CurrentWorkService currentWorkService;   // 当前工作
    @Autowired
    CurrentWorkRepository currentWorkRepository;    // 当前工作
    @Autowired
    WorkflowNodeService workflowNodeService; // 工作流结点
    @Autowired
    @Qualifier("WorkService")
    WorkService workService;  // 工作
    @Autowired
    ApplyTypeService applyTypeService;   // 申请类型
    @Autowired
    DepartmentTypeService departmentTypeService; // 部门类型
    @Autowired
    DistrictTypeService districtTypeService; // 区域类型

    @Override
    public Work getOneUnSavedWork() {
        Work work = new Work();
        Department auditDepartment = departmentService.getOneSavedDepartment();
        work.setAuditDepartment(auditDepartment);
        work.setApply(applyService.getOneSavedApply());
        work.setOpinion("审核意见");
        return work;
    }

    @Override
    public Work getOneSavedWork() {
        Work work = this.getOneUnSavedWork();
        workRepository.save(work);
        return work;
    }

    @Override
    public Page<Apply> pageApplyByAuditDepartmentOfMandatoryInstrument(Department department, Pageable pageable) {
        Page<Apply> page = currentWorkRepository.pageApplyByWorkAuditDepartment(department, pageable);
        return page;
    }

    public Page<Work> pageMandatoryInstrumentWorkByAuditDepartment(Department department, Pageable pageable) {
        Page<Work> works = currentWorkService.pageByAuditDepartmentAndApplyClassName(department, MandatoryInstrumentApply.CLASS_NAME, pageable);
        return works;
    }

    @Override
    public Page<Work> pageUnHandleMandatoryInstrumentWorkByAuditDepartment(Department department, Pageable pageable) {

        Page<Work> works = currentWorkRepository.pageWorkByWorkAuditDepartmentAndStatus(department, Work.UN_HANDLE, pageable);
        return works;
    }

    @Override
    public Page<Work> pageHandlingMandatoryInstrumentWorkByAuditDepartment(Department department, Pageable pageable) {

        Page<Work> works = currentWorkRepository.pageWorkByWorkAuditDepartmentAndStatusAndWorkApplyClassName(department, Work.HANDLING, MandatoryInstrumentApply.CLASS_NAME, pageable);
        return works;
    }

    @Override
    public Page<Work> pageDoneMandatoryInstrumentWorkByAuditDepartment(Department department, Pageable pageable) {

        Page<Work> works = currentWorkRepository.pageWorkByWorkAuditDepartmentAndStatusAndWorkApplyClassName(department, Work.DONE, MandatoryInstrumentApply.CLASS_NAME, pageable);
        return works;
    }

    @Override
    public Page<Work> pageByDepartmentOfMandatoryInstrumentGroupByApplyOfNotDoing(Department department, Pageable pageable) {
        return this.pageUnHandleMandatoryInstrumentWorkByAuditDepartment(department, pageable);
    }

    /**
     * 获取某个部门的所有工作的分页数据
     * 1. 直接调用repository中的相应方法
     *
     * @param department     办理部门
     * @param applyClassName 申请类型名称
     * @param pageable
     * @return
     * @author panjie
     */
    @Override
    public Page<Work> pageByAuditDepartmentAndApplyClassName(Department department, String applyClassName, Pageable pageable) {
        return currentWorkRepository.pageWorkByWorkAuditDepartmentAndWorkApplyClassName(department, applyClassName, pageable);
    }

    @Override
    public void updateToHandlingByWork(Work work) {
        if (null != work && work.getStatus().equals(Work.UN_HANDLE)) {
            work.setStatus(Work.HANDLING);
            workRepository.save(work);
        }
    }

    @Override
    public void updateToHandlingByWorkId(Long id) throws ObjectNotFoundException {
        Work work = workRepository.findOne(id);
        if (null == work) {
            throw new ObjectNotFoundException(404, "id为" + id.toString() + "的Work实体未找到");
        }
        this.updateToHandlingByWork(work);
    }

    @Override
    public void updateToDoneByWork(Work work) {
        logger.debug("更新审核类型 ，审核意见 ，审核用户");
        this.updateAuditTypeAndOpinionAndAuditUserByWorkIdOfCurrentUser(work.getAuditType(), work.getOpinion(), work.getId());

        logger.debug("办结所有工作");
        List<Work> workList = workRepository.findAllByApplyId(work.getApply().getId());
        for (Work work1 : workList) {
            work1.setStatus(Work.DONE);
        }
        workRepository.save(workList);
    }

    /**
     * 更新审核类型、申请意见、审核用户
     *
     * @param auditType
     * @param opinion
     * @param workId
     * @return
     * @author panjie
     */
    private Work updateAuditTypeAndOpinionAndAuditUserByWorkIdOfCurrentUser(String auditType, String opinion, Long workId) {
        Work work = workRepository.findOne(workId);
        work.setAuditType(auditType);
        work.setOpinion(opinion);
        User user = userService.getCurrentLoginUser();
        work.setAuditUser(user);
        workRepository.save(work);
        return work;
    }

    @Override
    public void updateToDoneByWorkId(Long id) throws ObjectNotFoundException {
        Work work = workRepository.findOne(id);
        if (null == work) {
            throw new ObjectNotFoundException(404, "id为" + id.toString() + "的Work实体未找到");
        }
        this.updateToDoneByWork(work);
    }

    @Override
    public void updateToHandledByWork(Work work) {
        if (null != work && !work.getStatus().equals(Work.DONE)) {
            work.setStatus(Work.HANDLED);
            workRepository.save(work);
        }
    }

    /**
     * 退回申请人，谁当年申请给我的，我退回给谁
     *
     * @param work
     * @author panjie
     */
    @Override
    public void backToApplyDepartment(Work work) {
        logger.info("办结本工作");
        this.updateToDoneByWork(work);
        if (work.getAliasWork() == null) {
            logger.info("上一申请人即是上一提交人，直接调用退回提交人的方法");
            this.backToPreDepartment(work);
        } else {
            logger.info("获取源工作节点");
            Work originWork = this.getOriginWorkByWork(work);
            Work nextWork = new Work();
            this.cloneWork(nextWork, originWork.getPreWork());
            nextWork.setPreWork(work);
            workRepository.save(nextWork);

            logger.info("办结本工作");
            work.setNextWork(nextWork);
            this.updateToDoneByWork(work);
        }

        logger.info("给下一个部门的所有用户发送短信提醒");
        departmentService.sentMessageToAllUserInDepartment(work.getAuditDepartment(), this.getRemindMessage(work));
    }

    /**
     * 获取起始工作流
     *
     * @param work
     * @return work
     * @author panjie
     */
    public Work getOriginWorkByWork(Work work) {
        if (work.getAliasWork() == null) {
            return work;
        } else {
            return this.getOriginWorkByWork(work.getAliasWork());
        }
    }

    @Override
    public Work getOriginWorkByWorkId(Long id) throws ObjectNotFoundException {
        Work work = workRepository.findOne(id);
        if (null == work) {
            throw new ObjectNotFoundException(404, "未找到ID为" + id.toString() + "的工作(Work)实体");
        }

        return this.getOriginWorkByWork(work);
    }

    /**
     * 退回提交人
     *
     * @param work
     */
    @Override
    public void backToPreDepartment(Work work) {
        logger.info("新建工作");
        Work nextWork = new Work();
        this.cloneWork(nextWork, work.getPreWork());
        nextWork.setPreWork(work);
        workRepository.save(nextWork);

        logger.info("办结本工作");
        work.setNextWork(nextWork);
        this.updateToDoneByWork(work);

        logger.info("给下一个部门的所有用户发送短信提醒");
        departmentService.sentMessageToAllUserInDepartment(work.getAuditDepartment(), this.getRemindMessage(work));
    }

    /**
     * 送下一审核部门
     *
     * @param work
     * @author panjie
     */
    @Override
    public void sendToNextDepartment(Work persistenceWork, Work work) {
        logger.info("新建下一工作, 并传入上一工作，工作流结点，审核部门");
        work.getNextWork().setPreWork(persistenceWork);
        work.getNextWork().setApply(persistenceWork.getApply());
        workRepository.save(work.getNextWork());

        logger.info("设置下一工作");
        persistenceWork.setNextWork(work.getNextWork());
        workRepository.save(persistenceWork);

        logger.info("办结本工作");
        this.updateToDoneByWork(persistenceWork);


        logger.info("给下一个部门的所有用户发送短信提醒");
        departmentService.sentMessageToAllUserInDepartment(persistenceWork.getAuditDepartment(), this.getRemindMessage(persistenceWork));
    }

    /**
     * 分送下次默认部门
     *
     * @param preWork 上一工作
     * @return panjie
     */
    @Override
    @Transactional
    public Work sendToNextDefaultDepartmentByPreWork(Work preWork) {
        User currentUser = userService.getCurrentLoginUser();
        logger.debug("通过工作，获取下一工作流结点，如果为多个，则抛出异常");
        List<WorkflowNode> workflowNodes = workflowNodeRepository.findAllByPreWorkflowNodeId(preWork.getWorkflowNode().getId());
        if (workflowNodes.size() == 0) {
            throw new RuntimeException("未获取到下一工作流结点");
        }

        if (workflowNodes.size() > 1) {
            throw new RuntimeException("获取到的下一工作流结点不唯一");
        }

        WorkflowNode workflowNode = workflowNodes.get(0);
        logger.debug("通过工作流节点，获取区域类型及部门类型，并由当前用户所在区域开始依次向上查找");

        List<Department> auditDepartments = this.getAuditDepartmentListByWorkflowNodeAndCurrentDepartment(workflowNode, currentUser.getDepartment());
        if (auditDepartments.size() == 0) {
            throw new RuntimeException("未找到符合条件的审核部门");
        }

        if (auditDepartments.size() > 1) {
            throw new RuntimeException("找到了多个符合条件的审核部门");
        }

        return this.addNewWorkByPreWorkAndAuditDepartmentAndWorkflowNodeAndOpinion(preWork, auditDepartments.get(0), workflowNode, "");
    }

    /**
     * 添加一个新工作
     *
     * @param preWork         上一工作
     * @param auditDepartment 审核部门
     * @param workflowNode    工作流结点
     * @param opinion         审核意见
     * @return
     * @Author panjie
     */
    protected Work addNewWorkByPreWorkAndAuditDepartmentAndWorkflowNodeAndOpinion(Work preWork, Department auditDepartment, WorkflowNode workflowNode, String opinion) {
        logger.debug("设置原工作的审核类型，新建新工作");
        preWork.setAuditType(Work.AUDIT_TYPE_SEND_TO_NEXT_DEPARTMENT);

        Work work = new Work();
        work.setWorkflowNode(workflowNode);
        work.setClickTime(Calendar.getInstance());
        work.setAuditDepartment(auditDepartment);
        work.setPreWork(preWork);
        work.setApply(preWork.getApply());
        work.setOpinion(opinion);
        workRepository.save(work);

        preWork.setNextWork(work);
        workRepository.save(preWork);

        logger.debug("设置当前工作");
        currentWorkService.addWork(work);

        return work;
    }

    /**
     * 获取审核部门列表
     *
     * @param workflowNode      工作流结点
     * @param currentDepartment 要发起申请的当前部门
     * @return
     * @Author panjie
     */
    protected List<Department> getAuditDepartmentListByWorkflowNodeAndCurrentDepartment(WorkflowNode workflowNode, Department currentDepartment) {
        List<District> districts = districtService.getParentListByDistrict(currentDepartment.getDistrict());
        List<Department> auditDepartments = new ArrayList<>();
        for (District district : districts) {
            if (district.getDistrictType().getId() == workflowNode.getDistrictType().getId()) {
                auditDepartments = departmentService.getAllByDistrictAndDepartmentType(district, workflowNode.getDepartmentType());
            }
        }
        return auditDepartments;
    }

    /**
     * 验证是否有当前工作的编辑权限
     *
     * @param work
     * @return panjie
     */
    private Work checkIsWorkEditAccess(Work work) {
        Work tempWork = workRepository.findOne(work.getId());
        if (null == tempWork) {
            logger.info("查找的work工作实体不存在");
            throw new ObjectNotFoundException(404, "查找的work工作实体不存在: " + work.getId().toString());
        }

        if (tempWork.getApply().getDone()) {
            logger.info("该工作已办结，无法更改状态");
            throw new SecurityException("该工作已办结，无法更改状态");
        }

        User user = userService.getCurrentLoginUser();
        if (user.getDepartment().getId() != tempWork.getAuditDepartment().getId()) {
            logger.info("您并不属于该工作的当前待/在办部门，无此操作权限");
            throw new SecurityException("您并不属于该工作的当前待/在办部门，无此操作权限");
        }

        return tempWork;
    }

//    /**
//     * 更新申请信息
//     *
//     * @param persistenceWork 数据表中已持久化的工作
//     * @param newWork         由前台传入的新工作
//     * @author: panjie
//     * 更新申请中的附加表单字段信息。
//     * 1. 遍历传入的字段记录
//     * 2. 查找权限表。如果该字段记录在当前工作的当前结点有write权限，则执行更新操作
//     * 3. 在执行更新操作时，先删除原数据，再添加新的数据。这样同时，可以满足数据的新增
//     */
//    private void updateApplyFieldRecords(Work persistenceWork, Work newWork) {
//        logger.info("遍历传入的新申请中的申请字段记录, 有写入权限的，进行数据的新增或更新");
//        for (ApplyFieldRecord applyFieldRecord : newWork.getApply().getApplyFieldRecords()) {
//            logger.info("遍历权限表");
//            for (WorkflowNodeAndApplyFieldAccess workflowNodeAndApplyFieldAccess : persistenceWork.getWorkflowNode().getWorkflowNodeAndApplyFieldAccesses()) {
//                if (workflowNodeAndApplyFieldAccess.getApplyField().getId() == applyFieldRecord.getApplyField().getId()) {
//                    logger.info("有权限记录");
//                    if (workflowNodeAndApplyFieldAccess.getWrite()) {
//                        logger.info("有写的权限, 查找出原记录，并删除。");
//                        for (ApplyFieldRecord applyFieldRecord1 : persistenceWork.getApply().getApplyFieldRecords()) {
//                            if (applyFieldRecord1.getApplyField().getId() == applyFieldRecord.getApplyField().getId()) {
//                                logger.info("找到申请字段对应的记录，进行数据删除");
//                                persistenceWork.getApply().getApplyFieldRecords().remove(applyFieldRecord1);
//                                break;
//                            }
//                        }
//
//                        logger.info("新的设置除value以外的两个属性，并添加到申请字段记录中");
//                        applyFieldRecord.setApplyField(workflowNodeAndApplyFieldAccess.getApplyField());
//                        applyFieldRecord.setApply(persistenceWork.getApply());
//                        persistenceWork.getApply().getApplyFieldRecords().add(applyFieldRecord);
//                    }
//                    break;
//                }
//            }
//        }
//    }

    @Override
    @Transient
    public void audit(Work newWork) {
        Work persistenceWork;
        try {
            logger.info("进行权限审核");
            persistenceWork = this.checkIsWorkEditAccess(newWork);
        } catch (Exception e) {
            throw e;
        }

        logger.info("设置审核意见及审核类型");
        persistenceWork.setOpinion(newWork.getOpinion());
        persistenceWork.setAuditType(newWork.getAuditType());

        switch (newWork.getAuditType()) {
            case Work.AUDIT_TYPE_SEND_TO_NEXT_DEPARTMENT:
                this.sendToNextDepartment(persistenceWork, newWork);
                break;

            case Work.AUDIT_TYPE_BACK_TO_APPLY_DEPARTMENT:
                this.backToApplyDepartment(persistenceWork);
                break;

            case Work.AUDIT_TYPE_BACK_TO_PRE_DEPARTMENT:
                this.backToPreDepartment(persistenceWork);
                break;

            default:
                this.updateToDoneByWork(persistenceWork);
        }
    }

    /**
     * 当前部门（用户、各级技术机构和管理部门）有待办工作需要处理时，系统给当前部门上的所有登录用户（工作人员）发送短信提醒
     *
     * @param work
     * @return
     */
    private String getRemindMessage(Work work) {
        logger.info("获取当前部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("获取短信内容");
        String message = "您有一个" + department.getName() +
                "分送的" + applyTypeService.findByApply(work.getApply()).getName() +
                "申请（申请类型）需要处理";

        return message;
    }

    @Override
    public List<Work> getAllByApplyId(Long applyId) {
        return workRepository.findAllByApplyId(applyId);
    }

    @Override
    public Work saveWorkWithCurrentUserAudit(Work work) {
        User currentUser = userService.getCurrentLoginUser();
        work.setAuditDepartment(currentUser.getDepartment());
        work.setClickTime(Calendar.getInstance());
        work.setAuditUser(currentUser);
        return this.saveWork(work);
    }

    @Override
    public Work saveWork(Work work) {
        boolean isNew = true;
        if (work.getId() != null) {
            isNew = false;
        }
        workRepository.save(work);

        if (isNew) {
            logger.debug("新建工作，则设置当前工作");
            currentWorkService.addWork(work);
        }

        return work;
    }


    @Autowired
    ApplyFieldRecordService applyFieldRecordService; //申请字段记录

    @Override
    public Work getById(Long id) {
        Work work = workRepository.findOne(id);

        logger.info("如果当前工作为待办，则设置为在办");
        if (work.getStatus().equals(Work.UN_HANDLE)) {
            workService.updateToHandlingByWork(work);
        }

        return work;
    }

    /**
     * 按申请类型名称获取当前登录用户的所有工作分页列表
     * 1. 获取当前登录用户
     * 2. 调用 获取某个部门的工作分页列表
     *
     * @param applyTypeFlag
     * @param pageable
     * @return
     * @author panjie
     */
    @Override
    public Page<Work> pageOfCurrentUserByApplyTypeFlag(String applyTypeFlag, Pageable pageable) {
        User user = userService.getCurrentLoginUser();
        return this.pageByAuditDepartmentAndApplyClassName(user.getDepartment(), applyTypeFlag, pageable);
    }

    @Override
    public List<Work> getAllUnHandleWorkOfCurrentUser() {
        Department department = userService.getCurrentLoginUser().getDepartment();
        return currentWorkRepository.findAllByWorkAuditDepartmentAndStatus(department, Work.UN_HANDLE);
    }

    @Override
    public List<Work> getAllCurrentUserWorkOfDoing() {
        Department department = userService.getCurrentLoginUser().getDepartment();
        return currentWorkRepository.findAllByWorkAuditDepartmentAndStatus(department, Work.HANDLING);
    }

    /**
     * 获取某个申请对应的最后一个工作
     *
     * @param apply 申请
     * @return panjie
     */
    @Override
    public Work findLastWorkByApply(Apply apply) {
        return workRepository.findTopByApplyOrderByIdDesc(apply);
    }

    @Override
    public Work findNextWorkByWorkId(Long workId) {
        Work work = new Work();
        work.setId(workId);
        return this.findNextWorkByWork(work);
    }

    @Override
    public Work findNextWorkByWork(Work work) {
        return workRepository.findOneByPreWork(work);
    }

    @Override
    public Page<Work> pageUnHandleWorkOfCurrentUser(Pageable pageable) {
        Department auditDepartment = userService.getCurrentLoginUser().getDepartment();
        return currentWorkService.pageByAuditDepartmentAndStatus(auditDepartment, Work.UN_HANDLE, pageable);
    }

    @Override
    public Page<Work> pageHandlingWorkOfCurrentUser(Pageable pageable) {
        Department auditDepartment = userService.getCurrentLoginUser().getDepartment();
        return currentWorkService.pageByAuditDepartmentAndStatus(auditDepartment, Work.HANDLING, pageable);
    }

    @Override
    public Page<Work> pageHandledWorkOfCurrentUser(Pageable pageable) {
        Department auditDepartment = userService.getCurrentLoginUser().getDepartment();
        return currentWorkService.pageByAuditDepartmentAndStatus(auditDepartment, Work.HANDLED, pageable);
    }

    @Override
    public Page<Work> pageDoneWorkOfCurrentUser(Pageable pageable) {
        Department auditDepartment = userService.getCurrentLoginUser().getDepartment();
        return currentWorkService.pageByAuditDepartmentAndStatus(auditDepartment, Work.DONE, pageable);
    }

    @Override
    public Work addFirstWorkByApplyAndOpinion(Apply apply, String opinion) {
        logger.debug("获取当前用户对应的工作流的首结点");
        WorkflowNode workflowNode = workflowNodeService.getTopOneByApplyOfCurrentUser(apply);
        if (workflowNode == null) {
            logger.error("未找到符合当前用户的审核流首结点");
            throw new RuntimeException("未找到符合当前用户的审核流首结点");
        }

        logger.debug("新建工作，并设置对应的申请及工作流结点。");
        Work work = new Work();
        work.setOpinion(opinion);
        work.setApply(apply);
        work.setWorkflowNode(workflowNode);
        this.saveWorkWithCurrentUserAudit(work);

        currentWorkService.addWork(work);
        return work;
    }

    @Override
    public Work sendDepartmentByPreWorkAndNextAuditDepartment(Work preWork, Department nextAuditDepartment) {
        logger.info("查找当前工作对应工作结点的所有下个工作结点");
        WorkflowNode nextWorkFlowNode = workflowNodeService.getOneByPreWorkflowNodeAndDepartmentTypeAndDistrictType(preWork.getWorkflowNode(), nextAuditDepartment.getDepartmentType(), nextAuditDepartment.getDistrict().getDistrictType());

        if (nextWorkFlowNode == null) {
            throw new RuntimeException("未找到适用的工作流结点。上一工作流ID：" + preWork.getWorkflowNode().getId().toString()
                    + ". 区域类型：" + nextAuditDepartment.getDistrict().getDistrictType().getId().toString()
                    + ". 部门类型：" + nextAuditDepartment.getDepartmentType().getId().toString());
        }

        return this.addNewWorkByPreWorkAndAuditDepartmentAndWorkflowNodeAndOpinion(preWork, nextAuditDepartment, nextWorkFlowNode, "");
    }

    @Override
    public Work sendToNextDefaultDepartmentByPreWorkAndNextAuditDepartmentTypeNameAndNextAuditDepartmentDistrictTypeName(Work perWork, String nextAuditDepartmentTypeName, String nextAuditDepartmentDistrictTypeName) {
        DepartmentType nextAuditDepartmentType = departmentTypeService.findOneByName(nextAuditDepartmentTypeName);
        if (null == nextAuditDepartmentType) {
            throw new RuntimeException("can found department type with name : " + nextAuditDepartmentTypeName);
        }

        DistrictType nextAuditDepartmentDistrictType = districtTypeService.findOneByName(nextAuditDepartmentDistrictTypeName);
        if (null == nextAuditDepartmentDistrictType) {
            throw new RuntimeException("can found district type with name : " + nextAuditDepartmentDistrictTypeName);
        }

        return this.sendToNextDefaultDepartmentByPreWorkAndNextAuditDepartmentTypeAndNextAuditDepartmentDistrictType(
                perWork, nextAuditDepartmentType, nextAuditDepartmentDistrictType
        );
    }

    @Override
    public Work sendToNextDefaultDepartmentByPreWorkAndNextAuditDepartmentTypeAndNextAuditDepartmentDistrictType(Work preWork, DepartmentType nextAuditDepartmentType, DistrictType nextAuditDepartmentDistrictType) {
        logger.info("查找当前工作对应工作结点的所有下个工作结点");
        WorkflowNode nextWorkFlowNode = workflowNodeService.getOneByPreWorkflowNodeAndDepartmentTypeAndDistrictType(preWork.getWorkflowNode(), nextAuditDepartmentType, nextAuditDepartmentDistrictType);

        if (nextWorkFlowNode == null) {
            throw new RuntimeException("未找到适用的工作流结点。上一工作流ID：" + preWork.getWorkflowNode().getId().toString()
                    + ". 区域类型：" + nextAuditDepartmentDistrictType.getId().toString()
                    + ". 部门类型：" + nextAuditDepartmentType.getId().toString());
        }

        logger.info("获取当前用户对应的部门类型及区域类型的唯一审核部门");
        User currentUser = userService.getCurrentLoginUser();
        List<Department> departmentList = this.getAuditDepartmentListByWorkflowNodeAndCurrentDepartment(nextWorkFlowNode, currentUser.getDepartment());

        if (departmentList.size() == 0) {
            throw new RuntimeException("can not found next audit department. 未找到符合要求的下一审核部门");
        }

        if (departmentList.size() > 1) {
            throw new RuntimeException("found more than one audit department. 找到了多个审核部门");
        }

        return this.addNewWorkByPreWorkAndAuditDepartmentAndWorkflowNodeAndOpinion(preWork, departmentList.get(0), nextWorkFlowNode, "");
    }


    protected void cloneWork(Work targetWork, Work originWork) {
        logger.info("设置别名，设置上一工作，设置申请部门，设置申请类型，设置审核部门, 设置待办, 设置工作流结点");
        targetWork.setAliasWork(originWork);
        targetWork.setAuditDepartment(originWork.getAuditDepartment());
        targetWork.setApply(originWork.getApply());
        targetWork.setWorkflowNode(originWork.getWorkflowNode());
    }
//
//    @Override
//    public String getSMSContent(Work work) {
//        logger.info("获取强检器具送审实体");
//        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(work.getApply().getId());
//        logger.info("对强检器具进行分类");
//        Map<String, List<MandatoryInstrument>> map = mandatoryInstrumentApplyService.classificationOfMandatoryInstrument(mandatoryInstrumentApply);
//
//        logger.info("设置发送短信的内容");
//        String sMSContent = "您送审的" +
//                mandatoryInstrumentRepository.findAllByMandatoryInstrumentApplyId(work.getApply().getId()).size()
//                + "个器具已完成审核：";
//
//        if (map.get(MandatoryInstrumentApplyServiceImpl.Quxian).size() != 0) {
//            logger.info("检定机构为区县级的数量大于零");
//            sMSContent = sMSContent.concat("其中" +
//                    map.get(MandatoryInstrumentApplyServiceImpl.Quxian).size() +
//                    "个指定" +
//                    map.get(MandatoryInstrumentApplyServiceImpl.Quxian).get(0).getLastCheckDepartment().getName() +
//                    "检定，");
//        }
//
//        if (map.get(MandatoryInstrumentApplyServiceImpl.Shi).size() != 0) {
//            logger.info("检定机构为市级的数量大于零");
//            sMSContent = sMSContent.concat(map.get(MandatoryInstrumentApplyServiceImpl.Shi).size() +
//                    "个指定" +
//                    map.get(MandatoryInstrumentApplyServiceImpl.Shi).get(0).getLastCheckDepartment().getName() +
//                    "检定，");
//        }
//
//        if (map.get(MandatoryInstrumentApplyServiceImpl.BACKED).size() != 0) {
//            logger.info("被退回的强检器具大于零");
//            sMSContent = sMSContent.concat(map.get(MandatoryInstrumentApplyServiceImpl.BACKED).size() +
//                    "个不符合相关条件并退回，");
//        }
//
//        if (map.get(MandatoryInstrumentApplyServiceImpl.Sheng).size() != 0) {
//            logger.info("检定机构为省级的数量大于零");
//            sMSContent = sMSContent.concat(map.get(MandatoryInstrumentApplyServiceImpl.Sheng).size() +
//                    "个需要您进入系统导出word文档，打印盖章，并至" +
//                    map.get(MandatoryInstrumentApplyServiceImpl.Sheng).get(0).getLastCheckDepartment().getName() +
//                    "盖章，持此向自治区管理部门申请检定。");
//        }
//
//        logger.info("确保最后一个字符是“，”");
//        sMSContent = sMSContent.substring(0, sMSContent.length() - 1).concat("。");
//        System.out.println(sMSContent);
//        return sMSContent;
//    }

}
