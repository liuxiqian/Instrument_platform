package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author panjie on 2017/12/12
 * 超期器具检定申请
 */
public class OverdueMandatoryInstrumentCheckApplyServiceImplTest extends ServiceTest {

    private final static Logger logger = Logger.getLogger(OverdueMandatoryInstrumentCheckApplyServiceImplTest.class.getName());
    @Autowired
    OverdueCheckApplyService overdueCheckApplyService; // 超期检定
    @Autowired
    UserService userService; // 用户
    @Autowired
    DistrictTypeService districtTypeService;     // 区域类型
    @Autowired
    DistrictTypeRepository districtTypeRepository;  // 区域类型
    @Autowired
    DistrictRepository districtRepository;   // 区域
    @Autowired
    DepartmentRepository departmentRepository;   // 部门
    @Autowired
    @Qualifier("DepartmentService")
    DepartmentService departmentService; // 部门
    @Autowired
    DepartmentTypeService departmentTypeService;
    @Autowired
    DepartmentTypeRepository departmentTypeRepository;   // 部门类型
    @Autowired
    OverdueCheckApplyRepository overdueCheckApplyRepository; // 超期检定申请
    @Autowired
    WorkRepository workRepository;   // 工作
    @Autowired
    MandatoryInstrumentRepository mandatoryInstrumentRepository;// 强检器具
    @Autowired
    OverdueCheckApplyServiceImplTestData overdueCheckApplyServiceImplTestData;   // 测试数据
    @Qualifier("WorkService")
    @Autowired
    WorkService workService;

    @Test
    public void save() throws Exception {
        logger.info("初始化测试变量，调用M层方法。 10个器具。");
        OverdueCheckApply overdueCheckApply = overdueCheckApplyServiceImplTestData.save();

        logger.info("调用保存方法");
        overdueCheckApply = overdueCheckApplyService.save(overdueCheckApply);

        logger.info("获取申请");
        OverdueCheckApply newOverdueCheckApply = overdueCheckApplyRepository.findOne(overdueCheckApply.getId());

        logger.info("断言申请中正常加入了3个器具（1，2，7）");
        assertThat(newOverdueCheckApply.getMandatoryInstrumentSet().size()).isEqualTo(3);

        logger.info("断言该申请对应着一个自己的新建工作，一个管理部门的新建工作");
        List<Work> works = workRepository.findAllByApplyId(newOverdueCheckApply.getId());
        assertThat(works.size()).isEqualTo(2);

        logger.info("断言该工作的提交部门为当前部门");
        Work currentWork = works.get(0);
        assertThat(currentWork.getAuditDepartment().getId()).isEqualTo(overdueCheckApplyServiceImplTestData.getCurrentUser().getDepartment().getId());

        logger.info("断言审核部门为管理部门");
        Work currentWork1 = works.get(1);
        assertThat(currentWork1.getAuditDepartment().getId()).isEqualTo(overdueCheckApplyServiceImplTestData.getManageDepartment().getId());
    }

    /**
     * 回复
     *
     * @throws Exception
     */
    @Test
    public void replyTest() throws Exception {
        logger.info("初始化测试变量，调用M层方法。 10个器具。并提交申请");
        Work work = overdueCheckApplyServiceImplTestData.reply();

        logger.info("准备数据");
        OverdueCheckApply overdueCheckApply = (OverdueCheckApply) work.getApply();

        logger.info("设置为同意");
        overdueCheckApply.setAgree(true);

        logger.info("调用方法");
        overdueCheckApplyService.reply(work);

        logger.info("断言申请办结，工作办结，状态为未同意，所有的器具都设置了下次检定日期，且与申请中的最迟检定日期相同");
        work = workRepository.findOne(work.getId());
        assertThat(work.getApply().getDone()).isTrue();
        OverdueCheckApply overdueCheckApply1 = (OverdueCheckApply) work.getApply();
        assertThat(overdueCheckApply1.getAgree()).isTrue();
        for (MandatoryInstrument mandatoryInstrument : overdueCheckApply1.getMandatoryInstrumentSet()) {
            assertThat(mandatoryInstrument.getNextCheckDate().getTime()).isEqualTo(overdueCheckApply.getLatestCheckDate().getTime());
        }

        logger.info("初始化测试变量，调用M层方法。 10个器具。并提交申请");
        work = overdueCheckApplyServiceImplTestData.reply();

        logger.info("准备数据");
        overdueCheckApply = (OverdueCheckApply) work.getApply();

        logger.info("设置为同意");
        overdueCheckApply.setAgree(false);

        logger.info("调用方法");
        overdueCheckApplyService.reply(work);

        logger.info("断言申请办结，工作办结，状态为未同意，未为检定设置最迟检定日期");
        assertThat(work.getApply().getDone()).isTrue();
        overdueCheckApply1 = (OverdueCheckApply) work.getApply();
        assertThat(overdueCheckApply1.getAgree()).isFalse();
        assertThat(overdueCheckApply1.getLatestCheckDate()).isNull();
    }

    @Test
    public void pageOfCurrentUser() {
        User currentUser = userService.loginWithOneUser();

        logger.debug("新建申请");
        OverdueCheckApply overdueCheckApply = new OverdueCheckApply();
        overdueCheckApply.setDepartment(currentUser.getDepartment());
        Calendar calendar = Calendar.getInstance();
        overdueCheckApply.setApplyTime(calendar);
        overdueCheckApplyRepository.save(overdueCheckApply);

        logger.info("新建工作");
        Work work = new Work();
        work.setAuditDepartment(currentUser.getDepartment());
        work.setApply(overdueCheckApply);
        workService.saveWork(work);

        logger.info("调用方法");
//        Page<CurrentWork> currentWorks = overdueCheckApplyService.pageOfCurrentUser(
//                calendar.getTimeInMillis(),
//                calendar.getTimeInMillis(),
//                currentUser.getDepartment().getId(),
//                new PageRequest(0, 1));
//        assertThat(currentWorks.getTotalElements()).isEqualTo(1);

        logger.info("调用方法");
        Page<CurrentWork> currentWorks = overdueCheckApplyService.pageOfCurrentUser(
                null,
                null,
                null,
                new PageRequest(0, 1));
        assertThat(currentWorks.getTotalElements()).isEqualTo(1);
    }
}