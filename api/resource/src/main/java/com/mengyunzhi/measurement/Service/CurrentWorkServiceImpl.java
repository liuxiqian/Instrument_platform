package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.Work;
import com.mengyunzhi.measurement.exception.AccessDeniedException;
import com.mengyunzhi.measurement.repository.ApplyRepository;
import com.mengyunzhi.measurement.repository.CurrentWorkRepository;
import com.mengyunzhi.measurement.repository.WorkRepository;
import com.mengyunzhi.measurement.specs.CurrentWorkSpecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author panjie on 2018/1/17
 * 当前工作
 */
@Service
public class CurrentWorkServiceImpl implements CurrentWorkService {
    static final Logger logger = LoggerFactory.getLogger(CurrentWorkServiceImpl.class.getName());
    @Autowired
    CurrentWorkRepository currentWorkRepository;    // 当前工作
    @Qualifier("WorkService")
    @Autowired
    WorkService workService; // 工作
    @Autowired
    WorkRepository workRepository;  // 工作
    @Autowired
    ApplyRepository applyRepository;    // 申请

    @Autowired @Qualifier("ApplyService") ApplyService applyService;        // 申请

    @Override
    public CurrentWork getOneSavedCurrentWork() {
        CurrentWork currentWork = this.getOneUnSavedCurrentWork();
        currentWorkRepository.save(currentWork);
        return currentWork;
    }

    @Override
    public CurrentWork getOneUnSavedCurrentWork() {
        Work work = workService.getOneSavedWork();
        CurrentWork currentWork = new CurrentWork();
        currentWork.setWork(work);
        return currentWork;
    }

    /**
     * 新增加工作
     * 按审核部门及申请进行查找
     * 1. 如果查看到历史记录，则更新历史记录
     * 2. 如果为首次申请，则添加新记录
     *
     * @param work
     * @Author panjie
     */
    @Override
    public void addWork(Work work) {
        if (work.getApply().getDone()) {
            throw new AccessDeniedException("当前申请已办结");
        }

        logger.debug("查找是否存在同一申请，同一审核部门的记录，存在，则更新状态.不存在，则新建");
        CurrentWork currentWork = currentWorkRepository.findByApplyAndCheckDepartment(work.getApply(), work.getAuditDepartment());
        if (currentWork == null) {
            logger.debug("未找到同一部门、同一申请的记录。将同一申请的其它记录状态设置为已办");
            List<Work> workList = workRepository.findAllByApplyId(work.getApply().getId());
            for (Work work1 : workList) {
                logger.debug("除自身以外，其它的全部设置为已办");
                if (!work1.getId().equals(work.getId())) {
                    work1.setStatus(Work.HANDLED);
                }
            }
            workRepository.save(workList);
            currentWork = new CurrentWork();
        }

        currentWork.setWork(work);
        currentWorkRepository.save(currentWork);

        logger.info("设置当前工作");
        applyService.updateCurrentWorkByApplyId(work.getApply().getId(), currentWork);

        return;
    }

    @Override
    public Page<Work> pageByAuditDepartmentAndApplyClassName(Department auditDepartment, String className, Pageable pageable) {
        return currentWorkRepository.pageWorkByWorkAuditDepartmentAndWorkApplyClassName(auditDepartment, className, pageable);
    }

    @Override
    public Page<CurrentWork> pageByAuditDepartmentAndApplyStartCalendarAndApplyEndCalendarAndApplyDepartmentAndApplyClass(
            Department auditDepartment, Calendar applyStartCalendar, Calendar applyEndCalendar, Department applyDepartment, Class<?> applyClass, Pageable pageable) {
        logger.debug("格式化综合查询数据");
        Map<String, Object> map = new HashMap<>();
        map.put("auditDepartmentId", auditDepartment.getId());                        // 工作的检定部门
        map.put("applyStartCalendar", applyStartCalendar);                          // 起始申请日期
        map.put("applyEndCalendar", applyEndCalendar);                              // 终止申请日期
        map.put("applyDepartment", applyDepartment);                        // 工作的申请部门
        map.put("applyClass", applyClass);                                    // 申请类型

        logger.debug("综合查询");
        Specification specification = CurrentWorkSpecs.base(map);
        Page<CurrentWork> works = currentWorkRepository.findAll(specification, pageable);

        return works;
    }

    @Override
    public Page<Work> pageByAuditDepartmentAndStatus(Department auditDepartment, Byte status, Pageable pageable) {
        return currentWorkRepository.pageWorkByWorkAuditDepartmentAndStatus(auditDepartment, status, pageable);
    }
}
