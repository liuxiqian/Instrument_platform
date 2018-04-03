package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author panjie on 2017/12/13
 **/
@Component
public class OverdueCheckApplyServiceImplTestData {
    private final static Logger logger = Logger.getLogger(OverdueCheckApplyServiceImplTestData.class.getName());
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
    @Autowired UserRepository userRepository;   // 用户
    @Autowired @Qualifier("WorkService") WorkService workService; // 工作
    @Autowired private OverdueCheckApplyService overdueCheckApplyService;
    private User currentUser;
    private Department manageDepartment;


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Department getManageDepartment() {
        return manageDepartment;
    }

    public void setManageDepartment(Department manageDepartment) {
        this.manageDepartment = manageDepartment;
    }

    /**
     * 新建数据准备
     * 10个器具
     * 3， 6， 9是超期器具
     * 4, 8不属于当前用户
     * 5, 10的状态不正常
     * panjie
     */
    public OverdueCheckApply save() {
        logger.info("获取一个未保存超期检定申请");
        OverdueCheckApply overdueCheckApply = overdueCheckApplyService.getOneUnsavedOverdueCheckApply();

        logger.info("获取登录用户");
        currentUser = userService.loginWithOneUser();

        logger.info("设置这个用户的部门所在区域类型为区县， 设置用户部门类型为器具用户");
        DistrictType districtType = districtTypeRepository.findOneQuXianDistrictType();
        currentUser.getDepartment().getDistrict().setDistrictType(districtType);
        DepartmentType departmentType = departmentTypeRepository.findOneByPinyin("qijuyonghu");
        currentUser.getDepartment().setDepartmentType(departmentType);
        districtRepository.save(currentUser.getDepartment().getDistrict());

        logger.info("为这个用户的所在区域增加一个管理部门");
        manageDepartment = departmentService.getOneSavedDepartment();
        DepartmentType departmentType1 = departmentTypeRepository.findOneByPinyin("guanlibumen");
        manageDepartment.setDepartmentType(departmentType1);
        manageDepartment.setDistrict(currentUser.getDepartment().getDistrict());
        departmentRepository.save(manageDepartment);

        logger.info("新增10个(1 - 10)器具");
        Calendar calendar = Calendar.getInstance();
        Date notOverDate = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date overDate = new Date(calendar.getTimeInMillis());
        User antherUser = userService.getOneSavedUser();

        Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
            logger.info("其中有3， 6， 9是超期器具");
            if (i % 3 == 0) {
                mandatoryInstrument.setNextCheckDate(overDate);
            } else {
                mandatoryInstrument.setNextCheckDate(notOverDate);
            }

            logger.info("4, 8不属于当前用户");
            if (i % 4 == 0) {
                mandatoryInstrument.setDepartment(antherUser.getDepartment());
            } else {
                mandatoryInstrument.setDepartment(currentUser.getDepartment());
            }

            logger.info("5, 10的状态不正常");
            if (i % 5 == 0) {
                mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NEW);
            } else {
                mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);
            }

            mandatoryInstruments.add(mandatoryInstrument);
        }

        logger.info("保存强检器具");
        mandatoryInstrumentRepository.save(mandatoryInstruments);

        logger.info("设置强检器具");
        overdueCheckApply.setMandatoryInstrumentSet(mandatoryInstruments);
        return overdueCheckApply;
    }

    public Work reply() {
        logger.info("保存一个申请");
        OverdueCheckApply overdueCheckApply = this.save();
        OverdueCheckApply overdueCheckApply1 = overdueCheckApplyService.save(overdueCheckApply);

        logger.info("获取一个登录用户，并设置当前登录用户的部门为申请的审核部门");
        currentUser = userService.getOneSavedUser();
        currentUser.setDepartment(this.getManageDepartment());
        userRepository.save(currentUser);
        userService.setCurrentLoginUser(currentUser);

        logger.info("获取这个申请对应在办工作");
        Work work = workService.findLastWorkByApply(overdueCheckApply1);

        logger.info("设置工作信息");
        work.setOpinion("同意");
        OverdueCheckApply overdueCheckApply2 = (OverdueCheckApply) work.getApply();

        logger.info("设置最迟检定日期");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        overdueCheckApply2.setLatestCheckDate(new Date(calendar.getTimeInMillis()));
        work.setApply(overdueCheckApply2);
        return work;
    }
}
