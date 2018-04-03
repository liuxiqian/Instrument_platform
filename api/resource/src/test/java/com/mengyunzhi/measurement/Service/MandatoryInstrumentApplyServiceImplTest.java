package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.exception.ValidationException;
import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.AccessException;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-7-15.
 */
public class MandatoryInstrumentApplyServiceImplTest extends ServiceTest {
    static private Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceImplTest.class.getName());
    @Autowired
    protected DepartmentRepository departmentRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected UserService userService;
    @Autowired
    protected WorkRepository workRepository;
    @Autowired
    protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;
    @Autowired
    protected MandatoryInstrumentApplyService mandatoryInstrumentApplyService;
    @Autowired
    private MandatoryInstrumentApplyServiceImplTestData mandatoryInstrumentApplyServiceTestData;
    @Autowired
    DepartmentTypeRepository departmentTypeRepository;      // 部门类型
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;   // 一级类别
    @Autowired
    InstrumentTypeRepository instrumentTypeRepository;                       // 二级类别
    @Autowired
    PurposeRepository purposeRepository;                                     // 用途
    @Autowired
    MandatoryInstrumentRepository mandatoryInstrumentRepository;             // 强检器具
    @Autowired @org.springframework.beans.factory.annotation.Qualifier("InstrumentTypeService") InstrumentTypeService instrumentTypeService; // 器具类别
    @Autowired AccuracyService accuracyService; // 精确度
    @Autowired MeasureScaleService measureScaleService; // 测量范围
    @Autowired CurrentWorkService currentWorkService;   // 当前工作
    @Autowired
    DistrictTypeRepository districtTypeRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired @Qualifier("WorkService") WorkService workService;

    @Test
    public void getPageOfCurrentDepartment() throws Exception {
        logger.info("设置部门");
        Department department = new Department();
        departmentRepository.save(department);
        logger.info("设置当前登录用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);
        logger.info("设置申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        logger.info("设置工作");
        Work work = new Work();
        work.setAuditDepartment(department);
        work.setApply(mandatoryInstrumentApply);
        workRepository.save(work);
        currentWorkService.addWork(work);

        logger.info("测试");
        PageRequest pageRequest = new PageRequest(0, 1);
        Page<Apply> applies = mandatoryInstrumentApplyService.getPageOfCurrentDepartment(pageRequest);
        logger.info("断言");
        assertThat(applies.getTotalPages()).isEqualTo(1);
    }
    @Test
    public void save() {
        logger.info("初始化测试变量，调用M层方法。新建申请，包括10个器具。");
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyServiceTestData.save();

        logger.info("调用保存方法");
        mandatoryInstrumentApply = mandatoryInstrumentApplyService.save(mandatoryInstrumentApply);

        logger.info("获取申请");
        MandatoryInstrumentApply newMandatoryInstrumentApply = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApply.getId());
        assertThat(mandatoryInstrumentApply.getMandatoryInstruments().size()).isEqualTo(10);

        logger.info("断言该申请对应着一个自己的新建工作，一个管理部门的新建工作");
        List<Work> works = workRepository.findAllByApplyId(newMandatoryInstrumentApply.getId());
        assertThat(works.size()).isEqualTo(2);

        logger.info("断言该工作的提交部门为当前部门");
        Work currentWork = works.get(0);
        assertThat(currentWork.getAuditDepartment().getId()).isEqualTo(mandatoryInstrumentApplyServiceTestData.getCurrentUser().getDepartment().getId());

        logger.info("断言审核部门为管理部门");
        Work currentWork1 = works.get(1);
        assertThat(currentWork1.getAuditDepartment().getId()).isEqualTo(mandatoryInstrumentApplyServiceTestData.getManageDepartment().getId());

    }



    /**
     * 生成PDF文档
     * @throws Exception
     * panjie
     */
    @Test
    public void generatePdfApplyById() throws Exception {
        logger.info("测试测试数据准备");
        User user = userService.loginWithOneUser();
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyServiceTestData.generatePdfApplyById(user);

        logger.info("生成文件，并断言");
        File file = mandatoryInstrumentApplyService.generateDoNotHaveCheckAbilityPdfReportByApplyId(mandatoryInstrumentApply.getId());

        assertThat(file).isFile();
    }

    /**
     * 通过申请ID及指定技术机构生成检定报告。
     *
     * @throws IOException
     * @throws AccessException
     * panjie
     */
    @Test
    public void generatePdfReportByApplyIdAndCheckDepartmentIdTest() throws IOException, AccessException {
        logger.info("测试测试数据准备");
        User user = userService.loginWithOneUser();
        HashSet<Long> checkDepartmentIds = new HashSet<>();
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyServiceTestData.generatePdfReportByApplyIdAndCheckDepartmentId(user, checkDepartmentIds);

        for(Long checkDepartmentId: checkDepartmentIds) {
            File file = mandatoryInstrumentApplyService.generatePdfReportByApplyIdAndCheckDepartmentId(mandatoryInstrumentApply.getId(), checkDepartmentId);
            assertThat(file).isFile();
        }
    }



    //  获取某个带有所有检定机构检定能力的强检申请
    @Test
    public void getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId() {
        User user = userService.loginWithOneUser();
        logger.info("数据准备");
        MandatoryInstrumentApply mandatoryInstrumentApply =
                mandatoryInstrumentApplyServiceTestData.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(user);

        logger.info("调用service方法");
        MandatoryInstrumentApply mandatoryInstrumentApply1 =
                mandatoryInstrumentApplyService.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(mandatoryInstrumentApply.getId());

        logger.info("对示例数据的检定值进行抓取");
        Integer quxianHasCheckAblity = 0;
        Integer shiHasCheckAblity = 0;
        for (MandatoryInstrument tempMandatoryInstrument: mandatoryInstrumentApply1.getMandatoryInstruments()) {
            for (Department tempDepartment: tempMandatoryInstrument.getCheckTechnicalInstitutionDepartments()) {
                if (tempDepartment.isCheckAbility() == true) {
                    if (tempDepartment.getDistrict().getDistrictType().getPinyin().equals("quxian")) {
                        quxianHasCheckAblity++;
                    } else {
                        shiHasCheckAblity++;
                    }
                }
            }
        }

        logger.info("断言区县能检定2个，市所能够全部检定");
        assertThat(quxianHasCheckAblity).isEqualTo(2);
        assertThat(shiHasCheckAblity).isEqualTo(5);
    }



    @Test
    public void batchPassByMandatoryInstruments() {
        User user = userService.loginWithOneUser();
        logger.info("数据准备");
        MandatoryInstrumentApply mandatoryInstrumentApply =
                mandatoryInstrumentApplyServiceTestData.batchPassByMandatoryInstruments(user);

        logger.info("创建当前工作");
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        work.setAuditDepartment(user.getDepartment());
        workRepository.save(work);

        logger.info("调用service方法");
        mandatoryInstrumentApplyService.batchPassByMandatoryInstrumentsAndMandatoryInstrumentApplyId(mandatoryInstrumentApply.getMandatoryInstruments(), mandatoryInstrumentApply.getId());

        logger.info("获取持久化数据");
        MandatoryInstrumentApply mandatoryInstrumentApply1 = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApply.getId());

        Integer count = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);

        for(MandatoryInstrument mandatoryInstrument: mandatoryInstrumentApply1.getMandatoryInstruments()) {
            Long time = mandatoryInstrument.getNextCheckDate().getTime();
            Calendar nextCheckDateCalendar = Calendar.getInstance();
            nextCheckDateCalendar.setTimeInMillis(time);
            Integer year = nextCheckDateCalendar.get(Calendar.YEAR);
            if (mandatoryInstrument.getStatus() == MandatoryInstrument.STATUS_NORMAL
                    && year.equals(calendar.get(Calendar.YEAR))) {
                count++;
            }
        }
        assertThat(count).isEqualTo(mandatoryInstrumentApply1.getMandatoryInstruments().size());
    }

    @Test
    public void batchBackByMandatoryInstrumentsAndReason() {
        User user = userService.loginWithOneUser();
        logger.info("数据准备");
        MandatoryInstrumentApply mandatoryInstrumentApply =
                mandatoryInstrumentApplyServiceTestData.batchBackByMandatoryInstrumentsAndReason(user);

        logger.info("创建申请对应的当前工作");
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        work.setAuditDepartment(user.getDepartment());
        workRepository.save(work);

        logger.info("调用service方法");
        mandatoryInstrumentApplyService.batchBackByMandatoryInstrumentsAndReasonAndApplyId(mandatoryInstrumentApply.getMandatoryInstruments(), "退回理由测试", mandatoryInstrumentApply.getId());

        logger.info("获取持久化数据");
        MandatoryInstrumentApply mandatoryInstrumentApply1 = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApply.getId());

        Integer count = 0;
        for(MandatoryInstrument mandatoryInstrument: mandatoryInstrumentApply1.getMandatoryInstruments()) {
            if (mandatoryInstrument.getStatus() == MandatoryInstrument.STATUS_BACKED &&
                    null == mandatoryInstrument.getNextCheckDate()) {
                count++;
            }
            assertThat(mandatoryInstrument.getManagementDepartmentBackedReason().getReason()).isEqualTo("退回理由测试");
        }
        assertThat(count).isEqualTo(mandatoryInstrumentApply1.getMandatoryInstruments().size());
    }

    @Test
    public void handleWhenApplyDoneByApplyId() throws IOException, AccessException {
        userService.loginWithOneUser();
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyServiceTestData.handleWhenApplyDoneByApplyId();
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        workService.saveWorkWithCurrentUserAudit(work);
        boolean catchException = false;
        try {
            mandatoryInstrumentApplyService.handleWhenApplyDoneByApplyId(mandatoryInstrumentApply.getId());
        }  catch (AccessException e) {
            catchException = true;
        }

        assertThat(catchException).isTrue();

        workService.updateToDoneByWork(work);
        mandatoryInstrumentApplyService.handleWhenApplyDoneByApplyId(mandatoryInstrumentApply.getId());

        MandatoryInstrumentApply mandatoryInstrumentApply1 = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApply.getId());
        int count = 0;
        for (MandatoryInstrument mandatoryInstrument: mandatoryInstrumentApply1.getMandatoryInstruments()) {
            if (mandatoryInstrument.getStatus() != MandatoryInstrument.STATUS_NEW) {
                count++;
            }
        }

        assertThat(count).isEqualTo(10);
    }

    @Test
    public void isCanExportPdfOfCurrentUser() {
        User user = userService.loginWithOneUser();
        logger.info("数据准备");
        MandatoryInstrumentApply mandatoryInstrumentApply =
                mandatoryInstrumentApplyServiceTestData.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(user);

        logger.info("断言");
        assertThat(mandatoryInstrumentApplyService.isCanExportPdfOfCurrentUser(mandatoryInstrumentApply)).isEqualTo(false);

    }

    @Test
    public void replyByIdAndReplyOpinions() {
        User currentUser = userService.loginWithOneUser();
        String opinion = "审核意见";
        logger.info("触发第一个异常");
        Boolean foundException = false;
        try {
            mandatoryInstrumentApplyService.replyByIdAndReplyOpinions(-1L, opinion);
        } catch (ValidationException e) {
            assertThat(e.getMessage().equals("未找到相关的申请实体"));
            foundException = true;
        }
        assertThat(foundException).isTrue();

        logger.info("触发第二个异常");
        foundException = false;
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyService.getOneSavedMandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        workService.saveWorkWithCurrentUserAudit(work);
        workService.updateToDoneByWork(work);
        try {
            mandatoryInstrumentApplyService.replyByIdAndReplyOpinions(mandatoryInstrumentApply.getId(), opinion);
        } catch (ValidationException e) {
            assertThat(e.getMessage().equals("当前申请已办结"));
            foundException = true;
        }
        assertThat(foundException).isTrue();

        logger.info("新建一个工作，并设置申请及审核部门");
        work.setStatus(Work.HANDLING);
        Department department = new Department();
        departmentRepository.save(department);
        work.setAuditDepartment(department);
        workRepository.save(work);

        try {
            mandatoryInstrumentApplyService.replyByIdAndReplyOpinions(mandatoryInstrumentApply.getId(), opinion);
        } catch (ValidationException e) {
            assertThat(e.getMessage().equals("当前申请的在办部门非当前登录部门"));
            foundException = true;
        }
        assertThat(foundException).isTrue();

        logger.info("排除异常，调用方法");
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        work.setAuditDepartment(currentUser.getDepartment());
        workRepository.save(work);

        logger.info("调用方法，并断言");
        mandatoryInstrumentApplyService.replyByIdAndReplyOpinions(mandatoryInstrumentApply.getId(), opinion);
        Work newWork = workRepository.findOne(work.getId());
        assertThat(newWork.getStatus().equals(Work.DONE));
        assertThat(newWork.getApply().getDone()).isTrue();
        MandatoryInstrumentApply mandatoryInstrumentApply1 = (MandatoryInstrumentApply) work.getApply();
        assertThat(mandatoryInstrumentApply1.getReplayOpinions()).isEqualTo(opinion);
    }

//    @Test
//    public void classificationOfMandatoryInstrument() throws Exception {
//        logger.info("新建一个强检器具申请实体");
//        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
//        mandatoryInstrumentApplyRepository.saveWorkWithCurrentUserAudit(mandatoryInstrumentApply);
//
//        logger.info("新建一个区县级技术机构检定的强检器具");
//        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
//        District district = new District();
//        district.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
//        logger.info("新建一个部门A");
//        Department departmentA = new Department();
//        departmentRepository.saveWorkWithCurrentUserAudit(departmentA);
//        districtRepository.saveWorkWithCurrentUserAudit(district);
//        departmentA.setDistrict(district);
//        mandatoryInstrument.setLastCheckDepartment(departmentA);
//        mandatoryInstrumentRepository.saveWorkWithCurrentUserAudit(mandatoryInstrument);
//
//        logger.info("新建一个市级技术机构检定的强检器具");
//        MandatoryInstrument mandatoryInstrumentB = new MandatoryInstrument();
//        District districtB = new District();
//        districtB.setDistrictType(districtTypeRepository.findOneByName("市"));
//        logger.info("新建一个部门");
//        Department departmentB = new Department();
//        departmentRepository.saveWorkWithCurrentUserAudit(departmentB);
//        districtRepository.saveWorkWithCurrentUserAudit(districtB);
//        departmentB.setDistrict(districtB);
//        mandatoryInstrumentB.setLastCheckDepartment(departmentB);
//        mandatoryInstrumentRepository.saveWorkWithCurrentUserAudit(mandatoryInstrumentB);
//
//        logger.info("新建一个省级技术机构检定的强检器具");
//        MandatoryInstrument mandatoryInstrumentC = new MandatoryInstrument();
//        District districtC = new District();
//        districtC.setDistrictType(districtTypeRepository.findOneByName("省"));
//        logger.info("新建一个部门");
//        Department departmentC = new Department();
//        departmentRepository.saveWorkWithCurrentUserAudit(departmentC);
//        districtRepository.saveWorkWithCurrentUserAudit(districtC);
//        departmentC.setDistrict(districtC);
//        mandatoryInstrumentC.setLastCheckDepartment(departmentC);
//        mandatoryInstrumentRepository.saveWorkWithCurrentUserAudit(mandatoryInstrumentC);
//
//        logger.info("新建一个区县级技术机构检定的且被退回的强检器具");
//        MandatoryInstrument mandatoryInstrumentD = new MandatoryInstrument();
//        mandatoryInstrumentD.setLastCheckDepartment(departmentA);
//        mandatoryInstrumentD.setStatus(InstrumentEmploymentInfo.STATUS_BACKED);
//        mandatoryInstrumentRepository.saveWorkWithCurrentUserAudit(mandatoryInstrumentD);
//
//        logger.info("断言");
//        Map<String, List<MandatoryInstrument>> map = mandatoryInstrumentApplyService.classificationOfMandatoryInstrument(mandatoryInstrumentApply);
//        assertThat(map.get(MandatoryInstrumentApplyServiceImpl.Quxian).size()).isEqualTo(1);
//        assertThat(map.get(MandatoryInstrumentApplyServiceImpl.Shi).size()).isEqualTo(1);
//        assertThat(map.get(MandatoryInstrumentApplyServiceImpl.Sheng).size()).isEqualTo(1);
//        assertThat(map.get(MandatoryInstrumentApplyServiceImpl.BACKED).size()).isEqualTo(1);
//
//    }

}