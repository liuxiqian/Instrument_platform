package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.AccessException;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-8-2.
 * 强检器具检定信息ServiceTest
 */
public class MandatoryInstrumentCheckInfoServiceImplTest extends ServiceTest {
    static private Logger logger = Logger.getLogger(MandatoryInstrumentCheckInfoServiceImplTest.class.getName());
    @Autowired protected MandatoryInstrumentCheckInfoServiceTestData mandatoryInstrumentCheckInfoServiceTestData;
    @Autowired protected MandatoryInstrumentCheckInfoService mandatoryInstrumentCheckInfoService;
    @Autowired protected UserRepository userRepository;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired protected InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    @Autowired MandatoryInstrumentService mandatoryInstrumentService;   // 强检器具
    @Autowired
    MandatoryInstrumentCheckApplyService mandatoryInstrumentCheckApplyService; // 检定申请
    @Autowired UserService userService; // 用户
    @Autowired
    MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;// 检定申请
    @Autowired DepartmentTypeRepository departmentTypeRepository;   // 部门类型
    @Autowired DepartmentTypeService departmentTypeService;
    @Autowired @Qualifier("WorkService") WorkService workService;   // 工作
    @Autowired @Qualifier("DepartmentService") DepartmentService departmentService; // 部门
    @Test
    public void pageAllOfCurrentUser() throws Exception {
        logger.info("准备测试数据");
        User user = userRepository.findOneByUsername("user3");
        Department department = departmentRepository.findByName("测试区县技术机构");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        InstrumentCheckInfo instrumentCheckInfo = mandatoryInstrumentCheckInfoService.getOneUnSaveInstrumentCheckInfo();

        mandatoryInstrumentCheckInfoServiceTestData.getOneMandatoryInstrumentCheckInfo(department, user ,mandatoryInstrument, instrumentCheckInfo);

        logger.info("测试");
        PageRequest pageRequest = new PageRequest(1,1);
        Page<InstrumentCheckInfo> instrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllOfCurrentUser(pageRequest);

        logger.info("断言");
        assertThat(instrumentCheckInfos.getTotalPages()).isEqualTo(1);
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void update() {
        logger.info("新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfo = mandatoryInstrumentCheckInfoService.getOneSavedInstrumentCheckInfo();
        logger.info("更新");
        instrumentCheckInfo.setCertificateNum("123456789");

        logger.info("测试");
        mandatoryInstrumentCheckInfoService.update(instrumentCheckInfo.getId(), instrumentCheckInfo);

        logger.info("断言");
        InstrumentCheckInfo instrumentCheckInfo1 = instrumentCheckInfoRepository.findOne(instrumentCheckInfo.getId());
        assertThat(instrumentCheckInfo1.getCertificateNum()).isEqualTo("123456789");

        logger.info("删除数据");
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
    }

    @Test
    public void pageAllByMandatoryInstrumentId() {
        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        logger.info("新建一个检定信息");
        InstrumentCheckInfo instrumentCheckInfo = mandatoryInstrumentCheckInfoService.getOneUnSaveInstrumentCheckInfo();
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);

        logger.info("测试");
        PageRequest pageRequest = new PageRequest(0,1);
        Page<InstrumentCheckInfo> instrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllByMandatoryInstrumentId(mandatoryInstrument.getId(), pageRequest);

        logger.info("断言");
        assertThat(instrumentCheckInfos.getTotalPages()).isEqualTo(1);

        logger.info("删除数据");
        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void saveViaTechnologyDepartmentTest() throws AccessException {
        logger.debug("新建一个器具");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneSavedMandatoryInstrument();

        logger.debug("新建一个申请");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = mandatoryInstrumentCheckApplyService.getOneSavedCheckApply();

        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        instrumentCheckInfo.setMandatoryInstrumentCheckApply(mandatoryInstrumentCheckApply);
        instrumentCheckInfo.setCheckDate(new Date(Calendar.getInstance().getTimeInMillis()));
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);

        logger.debug("调用保存功能, 断言获取到异常");
        String exceptionMessage = "";
        try {
            mandatoryInstrumentCheckInfoService.saveViaTechnologyDepartment(instrumentCheckInfo);
        } catch (AccessException e) {
            exceptionMessage = e.getMessage();
        }
        assertThat(exceptionMessage).isEqualTo("传入的器具ID与检定申请不匹配");

        logger.debug("这个器具加入申请后，保存.");
        mandatoryInstrumentCheckApply.getMandatoryInstrumentSet().add(mandatoryInstrument);
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);

        logger.info("设置登录用户，将当前登录用户设置为指定的检定机构");
        User currentUser = userService.loginWithOneUser();

        logger.info("创建非当前登录用户审核的工作");
        Work work = new Work();
        work.setApply(mandatoryInstrumentCheckApply);
        Department department = new Department();
        departmentRepository.save(department);
        work.setAuditDepartment(department);
        workService.saveWork(work);

        exceptionMessage = "";
        try {
            mandatoryInstrumentCheckInfoService.saveViaTechnologyDepartment(instrumentCheckInfo);
        } catch (AccessException e) {
            exceptionMessage = e.getMessage();
        }
        assertThat(exceptionMessage).isEqualTo("您不是指定的检定机构，无权限上传检定结果");

        logger.info("设置工作的审核部门为当前部门");
        work.setAuditDepartment(currentUser.getDepartment());
        workService.saveWork(work);

        logger.debug("再次调用，断言保存成功");
        mandatoryInstrumentCheckInfoService.saveViaTechnologyDepartment(instrumentCheckInfo);
        assertThat(instrumentCheckInfo.getId()).isNotNull();
        assertThat(instrumentCheckInfo.getMandatoryInstrument().getLastInstrumentCheckInfo().getId()).isEqualTo(instrumentCheckInfo.getId());
        assertThat(instrumentCheckInfo.getMandatoryInstrument().getLastCheckDate()).isEqualTo(instrumentCheckInfo.getCheckDate());
    }

    @Test
    public void saveViaApplyInstrumentUserTest() throws AccessException {
        logger.debug("新建一个器具");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneSavedMandatoryInstrument();

        logger.debug("新建一个申请");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = mandatoryInstrumentCheckApplyService.getOneSavedCheckApply();

        logger.debug("建立申请对应的当前工作");
        Work work = new Work();
        work.setApply(mandatoryInstrumentCheckApply);
        Department technicalInstitutionDepartment = departmentService.getOneTechnicalInstitutionDepartment();
        work.setAuditDepartment(technicalInstitutionDepartment);
        workService.saveWork(work);

        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        instrumentCheckInfo.setMandatoryInstrumentCheckApply(mandatoryInstrumentCheckApply);
        Department department = new Department();
        String departmentName = "XDa2HD6YFVqM5GRE92TC";
        department.setName(departmentName);
        instrumentCheckInfo.setCheckDepartment(department);
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);

        logger.info("设置登录用户，将当前登录用户设置为指定的检定机构");
        User currentUser = userService.loginWithOneUser();

        logger.debug("调用保存功能, 断言获取到异常");
        String exceptionMessage = "";
        try {
            mandatoryInstrumentCheckInfoService.saveViaApplyInstrumentUserDepartment(instrumentCheckInfo);
        } catch (AccessException e) {
            exceptionMessage = e.getMessage();
        }
        assertThat(exceptionMessage).isEqualTo("存在检定机构的检定申请，只有相关检定机构有权限进行上传，您无权上传相关检定记录");

        Department department1 = departmentService.getOneSavedCountryInstrumentUserDepartment();
        work.setAuditDepartment(department1);
        workService.saveWork(work);

        logger.debug("调用保存功能, 断言获取到异常");
        exceptionMessage = "";
        try {
            mandatoryInstrumentCheckInfoService.saveViaApplyInstrumentUserDepartment(instrumentCheckInfo);
        } catch (AccessException e) {
            exceptionMessage = e.getMessage();
        }
        assertThat(exceptionMessage).isEqualTo("传入的器具ID与检定申请不匹配");

        logger.debug("这个器具加入申请后，保存.");
        mandatoryInstrumentCheckApply.getMandatoryInstrumentSet().add(mandatoryInstrument);
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);
        exceptionMessage = "";
        try {
            mandatoryInstrumentCheckInfoService.saveViaApplyInstrumentUserDepartment(instrumentCheckInfo);
        } catch (AccessException e) {
            exceptionMessage = e.getMessage();
        }
        assertThat(exceptionMessage).isEqualTo("当前检定申请的发起者不是您所在的部门");

        instrumentCheckInfo.getMandatoryInstrumentCheckApply().setDepartment(currentUser.getDepartment());
        mandatoryInstrumentCheckApplyRepository.save(instrumentCheckInfo.getMandatoryInstrumentCheckApply());

        logger.debug("再次调用，断言保存成功");
        mandatoryInstrumentCheckInfoService.saveViaApplyInstrumentUserDepartment(instrumentCheckInfo);
        assertThat(instrumentCheckInfo.getId()).isNotNull();

        logger.info("重新将相同名字的检定部门传进来，看是否复用了检定的部门信息");
        InstrumentCheckInfo instrumentCheckInfo1 = new InstrumentCheckInfo();
        instrumentCheckInfo1.setMandatoryInstrumentCheckApply(mandatoryInstrumentCheckApply);
        instrumentCheckInfo1.setCheckDepartment(department);
        instrumentCheckInfo1.setMandatoryInstrument(mandatoryInstrument);
        mandatoryInstrumentCheckApply.getMandatoryInstrumentSet().add(mandatoryInstrument);
        mandatoryInstrumentCheckInfoService.saveViaApplyInstrumentUserDepartment(instrumentCheckInfo1);
        assertThat(instrumentCheckInfo1.getCheckDepartment().getId()).isEqualTo(instrumentCheckInfo.getCheckDepartment().getId());
        assertThat(instrumentCheckInfo1.getCheckDepartment().getName()).isEqualTo(department.getName());
        List<InstrumentCheckInfo> instrumentCheckInfoList = (List<InstrumentCheckInfo>) instrumentCheckInfoRepository.findAllByMandatoryInstrumentAndMandatoryInstrumentCheckApply(
                instrumentCheckInfo.getMandatoryInstrument(), instrumentCheckInfo.getMandatoryInstrumentCheckApply()
        );
        assertThat(instrumentCheckInfoList.size()).isEqualTo(1);

    }

    @Test
    public void  pageAllOfManagementDepartmentBySpecification() {
        InstrumentCheckInfo instrumentCheckInfo = mandatoryInstrumentCheckInfoService.getOneSavedInstrumentCheckInfo();
        Pageable pageable = new PageRequest(0, 2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(instrumentCheckInfo.getCheckDate().getTime());
        User user = instrumentCheckInfo.getMandatoryInstrument().getCreateUser();
        userService.setCurrentLoginUser(user);

        logger.debug("分别传入不同的参数，测试");

        Page<InstrumentCheckInfo> mandatoryInstrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllOfManagementDepartmentBySpecification(
                calendar.get(Calendar.YEAR),
                instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getDepartment().getId(),
                instrumentCheckInfo.getCheckDepartment().getId(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                pageable
        );

        assertThat(mandatoryInstrumentCheckInfos.getTotalElements()).isEqualTo(1);

        mandatoryInstrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllOfManagementDepartmentBySpecification(
                calendar.get(Calendar.YEAR),
                instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getId(),
                user.getDepartment().getId(),
                instrumentCheckInfo.getCheckDepartment().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getInstrumentType().getInstrumentFirstLevelType().getDiscipline().getId(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                pageable
        );

        assertThat(mandatoryInstrumentCheckInfos.getTotalElements()).isEqualTo(1);

        mandatoryInstrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllOfManagementDepartmentBySpecification(
                calendar.get(Calendar.YEAR),
                instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getId(),
                user.getDepartment().getId(),
                instrumentCheckInfo.getCheckDepartment().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getInstrumentType().getInstrumentFirstLevelType().getDiscipline().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getInstrumentType().getInstrumentFirstLevelType().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getInstrumentType().getId(),
                instrumentCheckInfo.getCertificateNum(),
                instrumentCheckInfo.getCheckResult().getId(),
                null,
                null,
                null,
                null,
                null,
                pageable
        );

        assertThat(mandatoryInstrumentCheckInfos.getTotalElements()).isEqualTo(1);


        mandatoryInstrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllOfManagementDepartmentBySpecification(
                calendar.get(Calendar.YEAR),
                instrumentCheckInfo.getMandatoryInstrument().getDepartment().getDistrict().getId(),
                user.getDepartment().getId(),
                instrumentCheckInfo.getCheckDepartment().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getInstrumentType().getInstrumentFirstLevelType().getDiscipline().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getInstrumentType().getInstrumentFirstLevelType().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getInstrumentType().getId(),
                instrumentCheckInfo.getCertificateNum(),
                instrumentCheckInfo.getCheckResult().getId(),
                null,
                instrumentCheckInfo.getMandatoryInstrument().getName(),
                instrumentCheckInfo.getMandatoryInstrument().getAccuracy().getId(),
                instrumentCheckInfo.getMandatoryInstrument().getMinMeasureScale().getWeight(),
                instrumentCheckInfo.getMandatoryInstrument().getMaxMeasureScale().getWeight(),
                pageable
        );

        assertThat(mandatoryInstrumentCheckInfos.getTotalElements()).isEqualTo(1);
    }
}