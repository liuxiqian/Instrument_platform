package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.CurrentWorkRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentApplyRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by panjie on 17/7/16.
 * 强制检定器具申请
 */
@Service
public class MandatoryInstrumentWorkServiceImpl extends WorkServiceImpl implements MandatoryInstrumentWorkService {
    private Logger logger = Logger.getLogger(MandatoryInstrumentWorkServiceImpl.class.getName());
    @Autowired
    protected UserService userService;                  // 用户
    @Autowired protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强检器具申请
    @Autowired protected MandatoryInstrumentRepository mandatoryInstrumentRepository;           // 强检器具
    @Autowired protected CurrentWorkRepository currentWorkRepository;   // 当前工作

    @Override
    public Page<Work> pageOfCurrentLoginUser(Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        return this.pageMandatoryInstrumentWorkByAuditDepartment(department, pageable);
    }

    /**
     * 当前用户的查询信息

     * @author chuhang
     */
    @Override
    public Page<CurrentWork> pageAllOfCurrentLoginUserBySpecification(Long departmentId, Date applyStartDate, Date applyEndDate, Pageable pageable) {
        logger.debug("初始化审核部门及申请部门");
        Department auditDepartment = userService.getCurrentLoginUser().getDepartment();
        Department applyDepartment = new Department();
        applyDepartment.setId(departmentId);

        Calendar applyStartCalendar = CommonService.sqlDateToCalendar(applyStartDate);
        Calendar applyEndCalendar = CommonService.sqlDateToCalendar(applyEndDate);

        logger.debug("调用当前工作方法");
        return currentWorkService.pageByAuditDepartmentAndApplyStartCalendarAndApplyEndCalendarAndApplyDepartmentAndApplyClass(
                auditDepartment, applyStartCalendar, applyEndCalendar, applyDepartment, MandatoryInstrumentApply.class, pageable
        );
    }

    /**
     * 获得的待办工作
     * @param pageable
     * @return
     */
    @Override
    public Page<Work> pageOfCurrentLoginUserOfToDo(Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        return this.pageUnHandleMandatoryInstrumentWorkByAuditDepartment(department, pageable);
    }

    /**
     * 获取在办工作
     * @param pageable
     * @return
     */
    @Override
    public Page<Work> pageOfCurrentLoginUserOfDoing(Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        return this.pageHandlingMandatoryInstrumentWorkByAuditDepartment(department, pageable);
    }

    /**
     * 获取办结工作
     * @param pageable
     * @return
     */
    @Override
    public Page<Work> pageOfCurrentLoginUserOfDone(Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        return this.pageDoneMandatoryInstrumentWorkByAuditDepartment(department, pageable);
    }

    /**
     * 获得已办工作
     * @param pageable
     * @return
     */
    @Override
    public Page<Work> pageOfCurrentLoginUserOfNotToDo(Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        return this.pageByDepartmentOfMandatoryInstrumentGroupByApplyOfNotDoing(department, pageable);
    }

    @Override
    public void updateToDoneByWork(Work work) {
        logger.info("获取对应的强检申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(work.getApply().getId());
        logger.info("将本工作对应的申请中的所有未审核的器具设置为退回");
        for(MandatoryInstrument mandatoryInstrument: mandatoryInstrumentApply.getMandatoryInstruments()) {
            if (mandatoryInstrument.getStatus() == MandatoryInstrument.STATUS_NEW) {
                mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_BACKED);
            }
        }
        mandatoryInstrumentRepository.save(mandatoryInstrumentApply.getMandatoryInstruments());
        this.updateToDoneByWork(work);
        return;
    }
}
