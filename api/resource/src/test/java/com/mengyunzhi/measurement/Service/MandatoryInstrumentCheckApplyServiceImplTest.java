package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentCheckApplyRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.AccessException;

import java.io.File;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author panjie on 2018/1/5
 * 检定申请
 */
public class MandatoryInstrumentCheckApplyServiceImplTest extends ServiceTest{
    private static Logger logger = Logger.getLogger(MandatoryInstrumentCheckApplyServiceImplTest.class.getName());

    @Autowired
    MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;                                    // 检定申请
    @Autowired
    MandatoryInstrumentRepository mandatoryInstrumentRepository;                  // 强检器具

    @Autowired
    private UserService userService;                                              // 用户
    @Autowired
    MandatoryInstrumentCheckApplyService mandatoryInstrumentCheckApplyService;                                          // 检定申请
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;                        // 强检器具
    @Autowired
    TechnicalInstitutionDepartmentService technicalInstitutionDepartmentService;  // 技术机构

    @Qualifier("DepartmentService")
    @Autowired
    DepartmentService departmentService;// 部门
    @Autowired
    DepartmentTypeRepository departmentTypeRepository;// 部门类型

    @Autowired
    CheckApplyServiceTestData checkApplyServiceTestData;                          // 检定申请测试数据
    @Autowired @Qualifier("WorkService") WorkService workService; // 工作
    @Autowired
    DepartmentRepository departmentRepository;   // 部门
    /**
     * 获取当前登录的技术机构的分页数据
     * panjie
     */
    @Test
    public void pageByCurrentTechnicalInstitutionDepartmentTest() {
        logger.info("使用一个新用户并登录");
        User user = userService.loginWithOneUser();

        logger.info("新建一个检定申请实体A, 并设置检定部门");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApplyA = new MandatoryInstrumentCheckApply();

        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApplyA);
        Work work = new Work();
        work.setApply(mandatoryInstrumentCheckApplyA);
        workService.saveWorkWithCurrentUserAudit(work);
        logger.info("分页并断言");
        final PageRequest pageRequest = new PageRequest(0, 1);
        Page<CurrentWork> currentWorkPage = mandatoryInstrumentCheckApplyService.pageOfCurrentUser(null, null, null, pageRequest);
        assertThat(currentWorkPage.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void getAllByCurrentLoginUserDepartment() throws Exception {
        logger.info("使用一个新用户并登录");
        User user = userService.loginWithOneUser();

        logger.info("新建一个检定申请实体A, 并设置申请发起部门");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApplyA = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApplyA.setDepartment(user.getDepartment());
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApplyA);

        Work work = new Work();
        work.setApply(mandatoryInstrumentCheckApplyA);
        workService.saveWorkWithCurrentUserAudit(work);

        logger.info("新建一个检定申请实体B,  并设置申请发起部门");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApplyB = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApplyB.setDepartment(user.getDepartment());
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApplyB);
        Work work1 = new Work();
        work1.setApply(mandatoryInstrumentCheckApplyB);
        workService.saveWorkWithCurrentUserAudit(work1);

        logger.info("分页并断言");
        final PageRequest pageRequest = new PageRequest(0, 1);
        Page<Work> workPage = mandatoryInstrumentCheckApplyService.pageOfCurrentUser(pageRequest);
        assertThat(workPage.getTotalElements()).isEqualTo(2);

    }

    @Test
    public void save () throws Exception {
        logger.info("获取一个区县级的器具用户并登录");
        User user = userService.getOneSavedUser();
        Department department = departmentService.getOneSavedCountryInstrumentUserDepartment();
        user.setDepartment(department);
        userService.save(user);
        userService.setCurrentLoginUser(user);

        logger.debug("获取检定申请");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = checkApplyServiceTestData.save(user);
        Set<MandatoryInstrumentCheckApply> savedCheckApplies = mandatoryInstrumentCheckApplyService.save(mandatoryInstrumentCheckApply);

        int i = 0;
        for (MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply1 : savedCheckApplies) {
            if (mandatoryInstrumentCheckApply1.getCurrentWork().getWork().getAuditDepartment().getDepartmentType().getName().equals("管理部门")) {
                logger.debug("断言向市级管理部门发送了一个申请,且只有一个器具");
                assertThat(mandatoryInstrumentCheckApply1.getMandatoryInstrumentSet().size()).isEqualTo(1);
                assertThat(mandatoryInstrumentCheckApply1.getCurrentWork().getWork().getAuditDepartment().getDistrict().getDistrictType().getPinyin().equals("shi"));
            } else {
                i += mandatoryInstrumentCheckApply1.getMandatoryInstrumentSet().size();
            }
        }
        logger.debug("断言向技术机构发送了的申请中总共包含5个器具,且其中一个不符合条件的器具被删除了");
        assertThat(i).isEqualTo(5);
    }

    @Test
    public void replyById() throws AccessException {
        logger.info("数据准备");
        String userPhone = "12132324";
        User user = userService.loginWithOneUser();
        Long id = checkApplyServiceTestData.reply(user);

        logger.info("更新数据");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApplyService.auditById(id, mandatoryInstrumentCheckApply);

        logger.info("查询并断言");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply1 = mandatoryInstrumentCheckApplyRepository.findOne(id);
    }

    /**
     * 当检定申请有审核部门时生成pdf的测试
     * @throws AccessException
     * @author zhangxishuo
     */
    @Test
    public void generatePdfApplyByIdWhenHaveAcceptDepartment() throws AccessException {
        User currentUser = userService.loginWithOneUser();
        Department department = departmentService.getOneTechnicalInstitutionDepartment();
        currentUser.setDepartment(department);
        userService.save(currentUser);

        logger.info("获取一个检定申请数据");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = checkApplyServiceTestData.generateCheckApplyData(currentUser);
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);

        Work work = new Work();
        logger.info("为该检定申请添加审核部门并保存");


        work.setAuditDepartment(department);
        work.setApply(mandatoryInstrumentCheckApply);
        workService.saveWorkWithCurrentUserAudit(work);

        logger.info("生成检定申请文档, 并断言");
        File file = mandatoryInstrumentCheckApplyService.generatePdfApplyById(mandatoryInstrumentCheckApply.getId());
        assertThat(file).isFile();
    }

    /**
     * 当检定申请没有审核部门时生成pdf的测试
     * @throws AccessException
     * @author zhangxishuo
     */
    @Test
    public void generatePdfApplyByIdWhenNoAcceptDepartment() throws AccessException {
        User currentUser = userService.loginWithOneUser();
        logger.info("获取一个检定申请数据");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = checkApplyServiceTestData.generateCheckApplyData(currentUser);
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);

        Work work = new Work();
        work.setApply(mandatoryInstrumentCheckApply);
        workService.saveWorkWithCurrentUserAudit(work);

        DepartmentType guanLiBuMenDepartment = departmentTypeRepository.findOneManagementDepartment();
        mandatoryInstrumentCheckApply.getCurrentWork().getWork().getAuditDepartment().setDepartmentType(guanLiBuMenDepartment);
        departmentRepository.save(mandatoryInstrumentCheckApply.getCurrentWork().getWork().getAuditDepartment());

        logger.info("生成检定申请文档, 并断言");
        File file = mandatoryInstrumentCheckApplyService.generatePdfApplyById(mandatoryInstrumentCheckApply.getId());
        assertThat(file).isFile();
    }

    /**
     * 当用户无下载权限时生成pdf的测试
     * @throws AccessException
     * @author zhangxishuo
     */
    @Test
    public void generatePdfApplyWhenNoAccess() throws AccessException {

        logger.info("用户登录");
        userService.loginWithOneUser();

        logger.info("获取检定申请并保存, 当前用户无权限");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = mandatoryInstrumentCheckApplyService.getOneSavedCheckApply();

        logger.info("获取文档, 无权限, 期待抛出权限异常");
        boolean catchException = false;
        try {
            mandatoryInstrumentCheckApplyService.generatePdfApplyById(mandatoryInstrumentCheckApply.getId());
        } catch (AccessException e) {
            assertThat(e.getMessage()).isEqualTo("您没有下载的权限");
            catchException = true;
        }
        assertThat(catchException).isTrue();
    }
}