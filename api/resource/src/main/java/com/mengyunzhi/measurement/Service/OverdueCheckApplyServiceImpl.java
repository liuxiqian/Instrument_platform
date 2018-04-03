package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentRepository;
import com.mengyunzhi.measurement.repository.OverdueCheckApplyRepository;
import com.mengyunzhi.measurement.repository.WorkflowTypeRepository;
import com.mengyunzhi.measurement.repository.WorkRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

/**
 * @author panjie on 2017/12/12
 * 强检器具超期检定申请
 */
@Service("OverdueCheckApplyService")
public class OverdueCheckApplyServiceImpl extends ApplyServiceImpl implements OverdueCheckApplyService {
    private final static Logger logger = Logger.getLogger(OverdueCheckApplyServiceImpl.class.getName());
    @Autowired
    @Qualifier("ApplyService")
    ApplyService applyService;    // 申请
    @Autowired
    MandatoryInstrumentRepository mandatoryInstrumentRepository;    // 强检器具
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;   // 强检器具
    @Autowired
    UserService userService; // 用户
    @Autowired
    ApplyTypeRepository applyTypeRepository; // 申请类型
    @Autowired
    WorkflowTypeRepository workflowTypeRepository;   // 工作流类型
    @Autowired
    OverdueCheckApplyRepository overdueCheckApplyRepository; // 超期检定申请
    @Autowired
    WorkflowNodeService workflowNodeService; // 工作流节点
    @Autowired
    WorkRepository workRepository;   // 工作
    @Autowired
    @Qualifier("WorkService")
    WorkService workService; //  工作
    @Autowired CurrentWorkService currentWorkService;   // 当前工作

    /**
     * 由于先要保存申请信息
     * 然后再根据保存的申请信息，新建工作
     * 这期间可以会发生异常
     * 使用事务注解来保证异常发生的数据的正常会滚
     *
     * @param overdueCheckApply
     * @return panjie
     */
    @Transactional
    public OverdueCheckApply save(OverdueCheckApply overdueCheckApply) {
        logger.debug("获取基本信息");
        overdueCheckApply = this.cloneAndSetBaseInfo(overdueCheckApply);

        logger.debug("筛选出符合申请规则的器具");
        List<MandatoryInstrument> mandatoryInstrumentList = (List<MandatoryInstrument>) mandatoryInstrumentService.findAllByMandatoryInstrumentCollection(overdueCheckApply.getMandatoryInstrumentSet());
        Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>(mandatoryInstrumentList);
        mandatoryInstrumentSet = this.excludeMandatoryInstrumentByOverdueCheckApplyOfCurrentLoginUser(mandatoryInstrumentSet);
        overdueCheckApply.setMandatoryInstrumentSet(mandatoryInstrumentSet);

        logger.debug("保存申请");
        overdueCheckApplyRepository.save(overdueCheckApply);

        logger.debug("生成首个工作");
        Work firstWork = workService.addFirstWorkByApplyAndOpinion(overdueCheckApply, "发起申请");

        logger.debug("送下一默认审核部门");
        workService.sendToNextDefaultDepartmentByPreWork(firstWork);
        overdueCheckApplyRepository.save(overdueCheckApply);

        if (overdueCheckApply.getMandatoryInstrumentSet().size() == 0) {
            logger.warn("获取到的超期检定申请中没有符合要求的超期器具");
        }

        return overdueCheckApply;
    }

    /**
     * 复制并设置基本的信息
     * 复制信息：强检器具列表
     *
     * @param overdueCheckApply
     * @return panjie
     */
    protected OverdueCheckApply cloneAndSetBaseInfo(OverdueCheckApply overdueCheckApply) {
        User currentUser = userService.getCurrentLoginUser();
        OverdueCheckApply newOverdueCheckApply = new OverdueCheckApply();
        newOverdueCheckApply.setMandatoryInstrumentSet(overdueCheckApply.getMandatoryInstrumentSet());
        newOverdueCheckApply.setApplyTime(Calendar.getInstance());
        newOverdueCheckApply.setCreateUser(currentUser);
        newOverdueCheckApply.setDepartment(currentUser.getDepartment());
        newOverdueCheckApply.setName(currentUser.getDepartment().getName() + "_超期检定申请");
        newOverdueCheckApply.setReason(overdueCheckApply.getReason());
        return newOverdueCheckApply;
    }

    /**
     * 排除不符合超期送检条件的
     * 1. 器具非当前部门的
     * 2. 状态不正常的
     * 3. 不是超期未检器具的
     *
     * @param mandatoryInstrumentCollection panjie
     */
    protected Set<MandatoryInstrument> excludeMandatoryInstrumentByOverdueCheckApplyOfCurrentLoginUser(Collection<MandatoryInstrument> mandatoryInstrumentCollection) {
        User currentUser = userService.getCurrentLoginUser();
        Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentCollection) {
            if (mandatoryInstrument.getDepartment().getId() == currentUser.getDepartment().getId()) {
                logger.debug("属于当前用户所在部门的");
                if (mandatoryInstrument.getStatus() == MandatoryInstrument.STATUS_NORMAL) {
                    logger.debug("状态正常的");
                    if (mandatoryInstrument.getOverdue()) {
                        logger.debug("是超期器具的");
                        mandatoryInstrumentSet.add(mandatoryInstrument);
                    }
                }
            }
        }
        return mandatoryInstrumentSet;
    }

    @Override
    public OverdueCheckApply getOneUnsavedOverdueCheckApply() {
        OverdueCheckApply overdueCheckApply = new OverdueCheckApply();
        overdueCheckApply.setCreateUser(userService.getOneSavedUser());
        overdueCheckApply.setName("测试申请" + CommonService.getRandomStringByLength(10));
        return overdueCheckApply;
    }

    /**
     * 回复
     *
     * @param work 工作
     *             panjie
     */
    @Override
    public void reply(Work work) {
        logger.debug("获取基础信息");
        work = this.cloneBaseInfoByWork(work);

        logger.debug("合法性较验");
        if (!this.isCanReplyOfCurrentUserByWork(work)) {
            String info = "您无此权限，或该流程已审核完毕";
            logger.warn(info);
            throw new AccessDeniedException(info);
        }

        OverdueCheckApply overdueCheckApply = (OverdueCheckApply) work.getApply();
        if (work.getApply().getAgree()) {
            logger.debug("同意， 设置各个器具的下次检定时间");
            for(MandatoryInstrument mandatoryInstrument: overdueCheckApply.getMandatoryInstrumentSet()) {
                mandatoryInstrument.setNextCheckDate(overdueCheckApply.getLatestCheckDate());
            }
        } else {
            logger.debug("不同意，至空最迟检定时间");
            overdueCheckApply.setLatestCheckDate(null);
        }

        logger.debug("办结工作");
        workService.updateToDoneByWork(work);
    }

    @Override
    public Page<CurrentWork> pageOfCurrentUser(Date startDate, Date endDate, Long applyDepartmentId, Pageable pageable) {
        logger.debug("日期转换");
        Calendar startCalendar = Calendar.getInstance();
        if (startDate != null) {
            startCalendar.setTimeInMillis(startDate.getTime());
        } else {
            startCalendar = null;
        }

        Calendar endCalendar = Calendar.getInstance();
        if (endDate != null) {
            endCalendar.setTimeInMillis(endDate.getTime());
            endCalendar.add(Calendar.DAY_OF_YEAR, 1);   // 做加1处理, 前台查询更容易理解
        } else {
            endCalendar = null;
        }

        logger.info("获取当前登录用户，设置申请部门信息");
        User currentUser = userService.getCurrentLoginUser();
        Department applyDepartment = new Department();
        applyDepartment.setId(applyDepartmentId);

        logger.info("调用查询");
        return currentWorkService.pageByAuditDepartmentAndApplyStartCalendarAndApplyEndCalendarAndApplyDepartmentAndApplyClass(
                currentUser.getDepartment(), startCalendar, endCalendar, applyDepartment, OverdueCheckApply.class, pageable
        );
    }

    /**
     * 复制基础的可回复的信息
     * @param work
     * @return
     * panjie
     */
    protected Work cloneBaseInfoByWork(Work work) {
        logger.info("复制基础信息: 是否同意；最后检定日期；处理意见");
        Work oldWork = workRepository.findOne(work.getId());
        OverdueCheckApply oldOverdueCheckApply = (OverdueCheckApply) oldWork.getApply();
        OverdueCheckApply newOverdueCheckApply = (OverdueCheckApply) work.getApply();
        oldOverdueCheckApply.setAgree(newOverdueCheckApply.getAgree());
        oldOverdueCheckApply.setLatestCheckDate(newOverdueCheckApply.getLatestCheckDate());
        oldOverdueCheckApply.setOpinion(newOverdueCheckApply.getOpinion());

        oldWork.setOpinion(work.getOpinion());
        return oldWork;
    }

    /**
     * 是否可被当前用户回复
     * 1. 较难审核部门
     * 2. 较验工作状态
     * @param work
     * @return
     */
    protected Boolean isCanReplyOfCurrentUserByWork(Work work) {
        User currentUser = userService.getCurrentLoginUser();
        if (currentUser.getDepartment().getId() != work.getAuditDepartment().getId()) {
            logger.debug("当前工作的审核部门非当前用户所在部门");
            return false;
        }

        if (work.getApply().getDone()) {
            logger.debug("当前工作已办结");
            return false;
        }

        return true;
    }
}
