package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by panjie on 17/7/13.
 * 工作
 */
public interface WorkService {
    Work getOneUnSavedWork();

    Work getOneSavedWork();

    /**
     * 获取强检器具备案的审核列表
     *
     * @param auditDepartment 审核部门
     * @param pageable
     * @return panjie
     */
    Page<Apply> pageApplyByAuditDepartmentOfMandatoryInstrument(Department auditDepartment, Pageable pageable);

    /**
     * 获取某个部门的强制检定器具申请工作列表
     *
     * @param department
     * @param pageable
     * @return
     * @author panjie
     */
    Page<Work> pageMandatoryInstrumentWorkByAuditDepartment(Department department, Pageable pageable);

    /**
     * 　获取强检备案的待办工作
     *
     * @param auditDepartment 审核部门
     * @param pageable
     * @return
     * @Author panjie
     */
    Page<Work> pageUnHandleMandatoryInstrumentWorkByAuditDepartment(Department auditDepartment, Pageable pageable);

    /**
     * 获取在办工作
     *
     * @param department
     * @param pageable
     * @return
     */
    Page<Work> pageHandlingMandatoryInstrumentWorkByAuditDepartment(Department department, Pageable pageable);

    /**
     * 获取办结工作
     *
     * @param department
     * @param pageable
     * @return
     */
    Page<Work> pageDoneMandatoryInstrumentWorkByAuditDepartment(Department department, Pageable pageable);

    /**
     * 获取已办工作
     *
     * @param department
     * @param pageable
     * @return
     */
    Page<Work> pageByDepartmentOfMandatoryInstrumentGroupByApplyOfNotDoing(Department department, Pageable pageable);

    /**
     * 获取某个部门的所有工作的分页数据
     *
     * @param department    办理部门
     * @param applyTypeFlag 申请类型名称
     * @param pageable
     * @return
     */
    Page<Work> pageByAuditDepartmentAndApplyClassName(Department department, String applyTypeFlag, Pageable pageable);

    /**
     * 更新工作状态到 在办
     *
     * @param work
     * @author panjie
     */
    void updateToHandlingByWork(Work work);

    void updateToHandlingByWorkId(Long id) throws ObjectNotFoundException;

    /**
     * 将某项工作设置为办结
     *
     * @param work
     * @author panjie
     */
    void updateToDoneByWork(Work work);

    void updateToDoneByWorkId(Long id) throws ObjectNotFoundException;

    void updateToHandledByWork(Work work);

    /**
     * 退回申请人(即上一结点)
     *
     * @param work
     * @author panjie
     */
    void backToApplyDepartment(Work work);

    /**
     * 退回提交人 谁点给我的，我就回点给他
     *
     * @param work
     * @author
     */
    void backToPreDepartment(Work work);

    /**
     * 获取起始工作
     *
     * @param work
     * @return work
     * @author panjie
     */
    Work getOriginWorkByWork(Work work);

    Work getOriginWorkByWorkId(Long id) throws ObjectNotFoundException;

    /**
     * 分送下一审核部门
     *
     * @param work
     * @author panjie
     */
    void sendToNextDepartment(Work persistenceWork, Work work);

    /**
     * 分送至默认的下一部门
     * 如果有多个符合条件的部门，则抛出异常
     *
     * @param preWork
     * @return panjie
     */
    Work sendToNextDefaultDepartmentByPreWork(Work preWork);

    /**
     * 审核工作
     *
     * @param work 工作
     */
    void audit(Work work);

    List<Work> getAllByApplyId(Long applyId);

    // 当前用户做为审核用户，所在部门做为审核部门，保存一个新工作
    Work saveWorkWithCurrentUserAudit(Work work);

    Work saveWork(Work work);

    Work getById(Long id);

    /**
     * 按申请类型标识获取当前登录用户的所有工作分页列表
     *
     * @param applyTypeFlag
     * @param pageable
     * @return
     */
    Page<Work> pageOfCurrentUserByApplyTypeFlag(String applyTypeFlag, Pageable pageable);

    /**
     * 获取当前登录用户的待办工作
     *
     * @return
     */
    List<Work> getAllUnHandleWorkOfCurrentUser();

    /**
     * 获取当前登录用户的在办工作
     *
     * @return
     */
    List<Work> getAllCurrentUserWorkOfDoing();

    /**
     * 获取申请对应的最后一个工作结点
     *
     * @param apply
     * @return
     */
    Work findLastWorkByApply(Apply apply);

    Work findNextWorkByWorkId(Long workId);

    Work findNextWorkByWork(Work work);

    // 待办
    Page<Work> pageUnHandleWorkOfCurrentUser(Pageable pageable);

    // 在办
    Page<Work> pageHandlingWorkOfCurrentUser(Pageable pageable);

    // 已办
    Page<Work> pageHandledWorkOfCurrentUser(Pageable pageable);

    // 办结
    Page<Work> pageDoneWorkOfCurrentUser(Pageable pageable);

    /**
     * 新建首个工作记录
     *
     * @param apply   申请
     * @param opinion 申请意见
     * @return
     */
    Work addFirstWorkByApplyAndOpinion(Apply apply, String opinion);

    /**
     * 分送至下一个默认的部门
     * @param preWork 上一个工作
     * @param nextAuditDepartment 下一个工作的审核部门
     * @return
     * @Author panjie
     */
    Work sendDepartmentByPreWorkAndNextAuditDepartment(Work preWork, Department nextAuditDepartment);

    /**
     * 分送至下一个默认的部门
     * @param perWork 上一个工作
     * @param nextAuditDepartmentTypeName 下一审核部门的部门类型
     * @param nextAuditDepartmentDistrictTypeName 下一审核部门的区域类型
     * @return
     * @Author panjie
     */
    Work sendToNextDefaultDepartmentByPreWorkAndNextAuditDepartmentTypeNameAndNextAuditDepartmentDistrictTypeName(Work perWork, String nextAuditDepartmentTypeName, String nextAuditDepartmentDistrictTypeName);

    /**
     * 分送至下一个默认的部门
     * @param preWork 上一个工作
     * @param nextAuditDepartmentType 审核部门类型
     * @param nextAuditDepartmentDistrictType 审核区域类型
     * @return
     * @Author panjie
     */
    Work sendToNextDefaultDepartmentByPreWorkAndNextAuditDepartmentTypeAndNextAuditDepartmentDistrictType(Work preWork, DepartmentType nextAuditDepartmentType, DistrictType nextAuditDepartmentDistrictType);


    //    // 设置手机短信内容
//    String getSMSContent(Work work);
}