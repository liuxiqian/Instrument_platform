package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Calendar;

/**
 * @author panjie on 2018/1/17
 * 当前工作
 */
public interface CurrentWorkService {
    CurrentWork getOneSavedCurrentWork();

    CurrentWork getOneUnSavedCurrentWork();

    void addWork(Work work);

    /**
     * 获取某个部门的某种申请类型的列表数据
     *
     * @param auditDepartment 审核部门
     * @param className       申请类型
     * @param pageable        分页
     * @return
     * @Author panjie
     */
    Page<Work> pageByAuditDepartmentAndApplyClassName(Department auditDepartment, String className, Pageable pageable);

    /**
     * 获取分页数据
     *
     * @param auditDepartment     申请部门
     * @param applyStartCalendar 申请开始时间
     * @param applyEndCalendar   申请结束时间
     * @param applyDepartment     申请部门
     * @param applyClass           申请类型
     * @param pageable
     * @return
     * @Author panjie
     */
    Page<CurrentWork> pageByAuditDepartmentAndApplyStartCalendarAndApplyEndCalendarAndApplyDepartmentAndApplyClass(
            Department auditDepartment, Calendar applyStartCalendar, Calendar applyEndCalendar, Department applyDepartment, Class<?> applyClass, Pageable pageable);

    /**
     * 获取分页数据
     * @param auditDepartment 审核部门
     * @param status 状态
     * @return
     */
    Page<Work> pageByAuditDepartmentAndStatus(Department auditDepartment, Byte status, Pageable pageable);
}
