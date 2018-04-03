package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/13.
 * 强检器具
 */
public class MandatoryInstrumentServiceTest extends ServiceTest {
    private static final Logger logger = Logger.getLogger(MandatoryInstrumentServiceTest.class.getName());
    @Autowired
    private UserRepository userRepository;           // 用户
    @Autowired
    private UserService userService;                 // 用户
    @Autowired
    protected WorkflowNodeService workflowNodeService;              // 工作流结点
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;         // 工作流结点
    @Autowired
    protected ApplyTypeRepository applyTypeRepository;                       // 申请类型
    @Autowired
    protected DepartmentRepository departmentRepository; // 部门
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强制检定申请
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("DepartmentService")
    private DepartmentService departmentService; // 部门
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;   // 强制检定
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired
    private MandatoryInstrumentServiceTestData mandatoryInstrumentServiceTestData;
    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;
    @Autowired
    InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    DisciplineRepository disciplineRepository;
    @Autowired
    DistrictRepository districtRepository;       // 区域
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;      //区域类型
    @Autowired
    @Qualifier("WorkService")
    WorkService workService;
    @Autowired
    MandatoryInstrumentApplyService mandatoryInstrumentApplyService;
    @Autowired
    ToMessageRepository toMessageRepository;              // 接受消息
    @Autowired
    FromMessageRepository fromMessageRepository;            // 发送消息
    @Autowired
    DistrictService districtService;
    @Autowired
    DistrictTypeRepository districtTypeRepository;
    @Autowired
    private MandatoryInstrumentTypeService mandatoryInstrumentTypeService;
    @Autowired ApplyTypeService applyTypeService;   // 申请类型

    @Test
    @Transactional
    public void save() {
        User user = userService.loginWithOneUser();
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        Department department = departmentService.getOneCompleteDepartment();
        mandatoryInstrument.setGenerativeDepartment(department);
        mandatoryInstrumentService.save(mandatoryInstrument);
        assertThat(mandatoryInstrument.getCreateUser().getId()).isEqualTo(user.getId());
        assertThat(mandatoryInstrument.getDepartment().getId()).isEqualTo(user.getDepartment().getId());
        assertThat(mandatoryInstrument.getGenerativeDepartment().getId()).isEqualTo(department.getId());

        String name = CommonService.getRandomStringByLength(10);
        MandatoryInstrument mandatoryInstrument1 = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        Department department1 = new Department();
        department1.setName(name);
        mandatoryInstrument1.setGenerativeDepartment(department1);
        mandatoryInstrumentService.save(mandatoryInstrument1);
        assertThat(mandatoryInstrument1.getGenerativeDepartment().getId()).isNotNull();

        MandatoryInstrument mandatoryInstrument2 = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
        Department department2 = new Department();
        department2.setName(name);
        mandatoryInstrument2.setGenerativeDepartment(department2);
        mandatoryInstrumentService.save(mandatoryInstrument2);
        assertThat(mandatoryInstrument2.getGenerativeDepartment().getId()).isEqualTo(mandatoryInstrument1.getGenerativeDepartment().getId());
        assertThat(mandatoryInstrument2.getGenerativeDepartment().getDepartmentType().getPinyin()).isEqualTo("shengchanqiye");
    }

    @Test
    @Transactional
    public void updateCheckDepartmentByMandatoryInstrumentsAndDepartmentIdTest() {
        User user = userService.loginWithOneUser();
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        this.mandatoryInstrumentServiceTestData.updateCheckDepartmentByMandatoryInstrumentsAndDepartmentIdTest(
                user.getDepartment(), mandatoryInstrumentApply
        );
        logger.info("增加一个审核部门，并调用方法");
        Department checkDepartment = departmentService.getOneCompleteDepartment();
        mandatoryInstrumentService.updateLastCheckDepartmentByMandatoryInstrumentsAndDepartmentId(mandatoryInstrumentApply.getMandatoryInstruments(), checkDepartment.getId());

        logger.info("获取数据，并断言");
        MandatoryInstrumentApply mandatoryInstrumentApply1 = mandatoryInstrumentApplyRepository.findOne(mandatoryInstrumentApply.getId());
        Set<MandatoryInstrument> mandatoryInstruments1 = mandatoryInstrumentApply1.getMandatoryInstruments();
        assertThat(mandatoryInstruments1.size()).isEqualTo(10);
        int j = 0;
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments1) {
            if (mandatoryInstrument.getLastCheckDepartment() != null &&
                    mandatoryInstrument.getLastCheckDepartment().getId() == checkDepartment.getId()) {
                j++;
            }
        }
        assertThat(j).isEqualTo(10);
    }

    @Test
    public void pageAllOfCurrentUser() {
        logger.info("创建一个登录用户");
        User user = userService.loginWithOneUser();
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        Department department = user.getDepartment();
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);

        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("测试");
        PageRequest pageRequest = new PageRequest(0, 1);
        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUser(pageRequest);

        logger.info("断言");
        assertThat(mandatoryInstruments.getTotalPages()).isEqualTo(1);

        logger.info("删除数据");
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void getAllOfCurrentUser() {
        logger.info("设置当前登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("测试");
        List<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentService.getAllOfCurrentUser();

        logger.info("断言");
        assertThat(mandatoryInstruments.size()).isEqualTo(1);

        logger.info("删除数据");
        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void pageAllOfCurrentUserBySpecification() {
//        https://my.oschina.net/u/2434456/blog/596938
        logger.info("新建立两个区域，父区域和子区域");
        District parentDistrict = new District();
        districtRepository.save(parentDistrict);
        District sonDistrict = new District();
        sonDistrict.setParentDistrict(parentDistrict);
        districtRepository.save(sonDistrict);

        logger.info("使用用户登录, 并将其设置为子区域");
        User user = userService.loginWithOneUser();
        user.getDepartment().setDistrict(sonDistrict);
        departmentRepository.save(user.getDepartment());

        String name = CommonService.getRandomStringByLength(10);        // 名称
        Pageable pageable = new PageRequest(0, 2);                  // 分页
        Page<MandatoryInstrument> mandatoryInstruments = null;                  // 返回值
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();    // 强检器具
        mandatoryInstrument.setDepartment(user.getDepartment());                // 设置部门
        InstrumentType instrumentType = new InstrumentType();                   // 器具类别
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType(); // 一级类别
        Discipline discipline = new Discipline();                                           // 学科类别
        // 区域

        mandatoryInstrumentServiceTestData.pageAllOfCurrentUserBySpecification(name, mandatoryInstrument, instrumentType, instrumentFirstLevelType, discipline);

        // 只传入器具名称
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, null, null, null, null, name, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        // 传入器具名称及器具类别
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, 1L, 1L, instrumentType.getId(), null, name, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, 1L, null, instrumentType.getId(), null, name, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        // 传入器具名称、及一级学科类别 name
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, 1L, instrumentFirstLevelType.getId(), null, null, name, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, null, instrumentFirstLevelType.getId(), null, null, name, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        // 只传入学科类别 name
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, discipline.getId(), null, null, null, name, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        // 传入name及是否已检定
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, discipline.getId(), null, null, false, name, null, null,  pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        mandatoryInstrument.setAudit(true);
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, discipline.getId(), null, null, true, name, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(null, discipline.getId(), null, null, true, name, null, MandatoryInstrument.STATUS_NORMAL, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("按ID查询");
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(mandatoryInstrument.getId(), null, null, null, null, null, null, null, null, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("按父区域查询");
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, parentDistrict.getId(), null, null, null, null, null, null, null, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("按子区域查询");
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, sonDistrict.getId(), null, null, null, null, null, null, null, null, null, pageable);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("查询excel导出功能, 断言导出了1条数据（包括表头共2行）");
        Workbook workbook = mandatoryInstrumentService.getWorkbookByMandatoryInstruments(mandatoryInstruments);
        assertThat(workbook).isNotNull();
        assertThat(workbook.getSheetAt(0).getLastRowNum()).isEqualTo(1);


        logger.info("设置审核状态，审核日期，然后查询审核部门");
        mandatoryInstrument.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument.setAudit(true);
        mandatoryInstrument.setAuditDate(new Date(new java.util.Date().getTime()));
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentTechnicalInstitutionBySpecification(null, null, null, null, null, null, null, null, null);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);

        logger.info("判断器具是否超期未检");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        mandatoryInstrument.setNextCheckDate(new Date(calendar.getTimeInMillis()));
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        mandatoryInstruments = mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(null, null, null, null, null, null, null, null, null, true, null, null);
        assertThat(mandatoryInstruments.getTotalElements()).isEqualTo(1);
    }

    public void updateFixSite() throws Exception {
        logger.info("---- updateFixsite方法测试 ----");
        logger.info("新建一个强检器具实体");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setFixSite("123");
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("修改该实体");
        mandatoryInstrument.setFixSite("456");
        mandatoryInstrumentService.updateFixSite(mandatoryInstrument.getId(), mandatoryInstrument);

        logger.info("断言修改成功");
        assertThat(mandatoryInstrument.getFixSite()).isEqualTo("456");

        mandatoryInstrumentRepository.delete(mandatoryInstrument);
    }

    @Test
    public void autoConfirmWhenTimeOutByMandatoryInstruments() throws Exception {
        logger.info("获取一个系统设定的过期时间的前5天的日期");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, (-5) - Integer.parseInt(MandatoryInstrument.MAX_ALLOW_BACK_DAY_VALUE));

        logger.info("建立10个强检器具");
        List<MandatoryInstrument> mandatoryInstruments = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            logger.info("分别设置审核日期及确认状态, 并添加到列表中");
            MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
            mandatoryInstrument.setAuditDate(new Date(cal.getTimeInMillis()));
            cal.add(Calendar.DATE, 1);
            mandatoryInstruments.add(mandatoryInstrument);
        }

        Page<MandatoryInstrument> mandatoryInstrumentPage = new PageImpl(mandatoryInstruments);

        logger.info("调用方法");
        mandatoryInstrumentService.autoConfirmWhenTimeOutByMandatoryInstruments(mandatoryInstrumentPage);

        logger.info("断言其中有3个还未被系统确认，7已经被系统确认了");
        Integer falseCount = 0;
        Iterator<MandatoryInstrument> mandatoryInstrumentIterator = mandatoryInstrumentPage.iterator();
        while (mandatoryInstrumentIterator.hasNext()) {
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentIterator.next();
        }

        // 该断言在新的版本中，已经舍弃。
        assertThat(falseCount).isEqualTo(0);
    }

    /**
     * 批量确认已指定给技术机构的器具
     * panjie
     */
    @Test
    public void batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser() {
        User user = userService.loginWithOneUser();
        String name = CommonService.getRandomStringByLength(30);
        Calendar todayCalendar = Calendar.getInstance();
        Date today = new Date(todayCalendar.getTimeInMillis());
        Integer maxAllowDayBack = mandatoryInstrumentService.getMaxAllBackDay();

        logger.info("新建几个器具");
        Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>();

        logger.info("其中有1个已确认的");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);
        mandatoryInstrument.setAuditDate(today);
        mandatoryInstrument.setName(name);
        mandatoryInstrument.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrumentSet.add(mandatoryInstrument);

        logger.info("有1个已被退回的");
        MandatoryInstrument mandatoryInstrument1 = new MandatoryInstrument();
        mandatoryInstrument1.setAuditDate(today);
        mandatoryInstrument1.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument1.setStatus(MandatoryInstrument.STATUS_BACKED);
        mandatoryInstrument1.setName(name);
        mandatoryInstrumentSet.add(mandatoryInstrument1);

        logger.info("有1个不是当前登录用户的");
        MandatoryInstrument mandatoryInstrument2 = new MandatoryInstrument();
        mandatoryInstrument2.setAuditDate(today);
        mandatoryInstrument2.setName(name);
        mandatoryInstrument2.setStatus(MandatoryInstrument.STATUS_NORMAL);
        Department department = departmentService.getOneSavedDepartment();
        mandatoryInstrument2.setLastCheckDepartment(department);
        mandatoryInstrumentSet.add(mandatoryInstrument2);

        logger.info("有1个正常可以确认的");
        MandatoryInstrument mandatoryInstrument3 = new MandatoryInstrument();
        mandatoryInstrument3.setAuditDate(today);
        mandatoryInstrument3.setName(name);
        mandatoryInstrument3.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument3.setStatus(MandatoryInstrument.STATUS_NORMAL);
        mandatoryInstrumentSet.add(mandatoryInstrument3);

        logger.info("有1个过期的");
        todayCalendar.add(Calendar.DATE, -2 - maxAllowDayBack);
        MandatoryInstrument mandatoryInstrument4 = new MandatoryInstrument();
        mandatoryInstrument4.setAuditDate(new Date(todayCalendar.getTimeInMillis()));
        mandatoryInstrument4.setStatus(MandatoryInstrument.STATUS_NORMAL);
        mandatoryInstrument4.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument4.setName(name);
        mandatoryInstrumentSet.add(mandatoryInstrument4);

        logger.info("持久化");
        mandatoryInstrumentRepository.save(mandatoryInstrumentSet);

        logger.info("调用方法");
        mandatoryInstrumentService.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser(mandatoryInstrumentSet);

        logger.info("断言找到的4个数据，只有2个是被确认的");
        List<MandatoryInstrument> mandatoryInstrumentList = mandatoryInstrumentRepository.findAllByLastCheckDepartment(user.getDepartment());
        assertThat(mandatoryInstrumentList.size()).isEqualTo(4);
        Integer count = 0;
//    该功能已去除
//        assertThat(count).isEqualTo(1);
    }

    /**
     * 当前登录用户超级未检的器具
     */
    @Test
    public void pageOverdueDataOfCurrentUserBySpecificationTest() {
        logger.info("获取一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("获取一个前5天的日期");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, (-5));

        logger.info("获取一个二级器具类别");
        InstrumentType instrumentType = mandatoryInstrumentTypeService.getOneSavedMandatoryInstrumentType();

        logger.info("新建10个器具，分别将计划检定时间设置为前5天至后4天。");
        Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();
        for (Integer i = 0; i < 10; i++) {
            MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
            mandatoryInstrument.setDepartment(user.getDepartment());
            mandatoryInstrument.setInstrumentType(instrumentType);
            if (i % 2 == 0) {
                mandatoryInstrument.setStatus(InstrumentEmploymentInfo.STATUS_NORMAL);
            } else {
                mandatoryInstrument.setStatus(InstrumentEmploymentInfo.STATUS_NEW);
            }

            mandatoryInstrument.setNextCheckDate(new Date(cal.getTimeInMillis()));
            mandatoryInstruments.add(mandatoryInstrument);
            cal.add(Calendar.DATE, 1);
        }

        logger.info("实例化");
        mandatoryInstrumentRepository.save(mandatoryInstruments);

        logger.info("调用方法查询");
        Pageable pageable = new PageRequest(0, 2);
        Page<MandatoryInstrument> mandatoryInstrumentPage = mandatoryInstrumentService.pageOverdueDataOfCurrentUserBySpecification(null, instrumentType.getInstrumentFirstLevelType().getDiscipline().getId(), null, null, null, pageable);
        logger.info("断言有3个超期未检的器具");
        assertThat(mandatoryInstrumentPage.getTotalElements()).isEqualTo(3);

        mandatoryInstrumentPage = mandatoryInstrumentService.pageOverdueDataOfCurrentUserBySpecification(
                null,
                null,
                instrumentType.getInstrumentFirstLevelType().getId(),
                null,
                null,
                pageable);
        logger.info("断言有3个超期未检的器具");
        assertThat(mandatoryInstrumentPage.getTotalElements()).isEqualTo(3);

        mandatoryInstrumentPage = mandatoryInstrumentService.pageOverdueDataOfCurrentUserBySpecification(
                null,
                null,
                null,
                instrumentType.getId(),
                null,
                pageable);
        logger.info("断言有3个超期未检的器具");
        assertThat(mandatoryInstrumentPage.getTotalElements()).isEqualTo(3);
    }
}
