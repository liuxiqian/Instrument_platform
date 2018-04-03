package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.Service.MigrationService;
import com.mengyunzhi.measurement.entity.WebAppMenu;
import com.mengyunzhi.measurement.repository.WebAppMenuRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/7/25.
 * 前台菜单数据初始化
 */
@Component
public class WebAppMenuDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private Logger logger = Logger.getLogger(WebAppMenuDataInit.class.getName());
    private final WebAppMenuRepository webAppMenuRepository; // 前台菜单
    // 前台菜单
    private List<WebAppMenu> webAppMenus;
    private MigrationService migrationService;
    // 权重
    protected int weight = HIGHEST_PRECEDENCE;

    private WebAppMenu mainMenu;

    @Autowired
    public WebAppMenuDataInit(WebAppMenuRepository webAppMenuRepository, MigrationService migrationService) {
        this.webAppMenuRepository = webAppMenuRepository;
        this.migrationService = migrationService;
        webAppMenus = new ArrayList<>();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<WebAppMenu> webAppMenusAll = webAppMenuRepository.findAll();
        Boolean isAdd = false;  // 是否执行添加记录操作
        if (webAppMenusAll.size() == 0) {
            isAdd = true;
            mainMenu = new WebAppMenu();
            mainMenu.setAbstract(true);
            mainMenu.setTemplateUrl("views/main.html");
            mainMenu.setShow(false);
            mainMenu.setName("用于仪表台及大数据平台继承");
            mainMenu.setDescription("请为所有用户勾选该选项");
            mainMenu.setRouteName("main");
            mainMenu.setWeight(weight++);
            webAppMenus.add(mainMenu);

            logger.info("主菜单");
            this.addWebAppMenuMain();

            logger.info("我的工作");
            this.addWebAppMenuMyWork();

            logger.info("强检器具备案");
            this.addForceInstrumentFiling();

            logger.info("强检器具超期检定备案");
            this.addTimeoutCheckApply();

            logger.info("强检器具检定管理");
            this.addWebAppMenuIntegrated();

            logger.info("标准装置管理");
            this.addWebAppMenuStandard();

            logger.info("用户（部门）管理");
            this.addDepartment();
            logger.info("人员资质");
            this.addWebAppMenusPersonnel();
            logger.info("我的消息");
            this.addWebAppMenuMyMessage();
            logger.info("系统设置");
            this.addWebAppMenuSystem();
            logger.info("个人中心");
            this.addWebAppMenuPersonalCenter();
        }

        // 查询是否添加过了以下的内容，如果添加过了，则跳过。未添加则执行
        String batch = "0daJM3rAqVOwPH8CZV2QgwVl1vUw0xn7";
        if (null == migrationService.findByBatch(batch)) {
            isAdd = true;   // 有新的数据需要更新

            logger.debug("维护版本信息");
            migrationService.saveByBatch(batch);

            logger.debug("添加菜单");
            // todo: 请按需求文档增加菜单。测试时，请将配置信息中的数据生成方式更改为create。
            // 每个一级菜单做为一个方法出现。先按现在菜单，检查哪些是需要填写的，哪些是不需要填写的。

            logger.info("---强检器具监督管理模块---");

            logger.info("强检器具统计管理");
            this.addWebAppMenuForceInstrumentStatistics();

            logger.info("报警管理");
            this.addWebAppMenuAlarmManagement();

            logger.info("---非强检器具管理模块---");

            logger.info("非强检器具检定申请管理");
            this.addWebAppMenuNonForceInstrumentCheckApplyManagement();

            logger.info("非强检器具检定信息综合查询");
            this.addWebAppMenuNonForceInstrumentCheckInfoQuery();

            logger.info("非强检器具校准申请管理");
            this.addWebAppMenuNonForceInstrumentCorrectApplyManagement();

            logger.info("非强检器具校准信息综合查询");
            this.addWebAppMenuNonForceInstrumentCorrectInfoQuery();

            logger.info("---定量包装模块---");

            logger.info("定量包装商品净含量监督管理");
            this.addWebAppMenuWareNetContentSupervision();

            logger.info("定量包装商品经营单位档案");
            this.addWebAppMenuWareBusinessFile();

            logger.info("定量包装商品生产单位档案");
            this.addWebAppMenuWareProductionFile();

            logger.info("定量包装商品监督抽查计划");
            this.addWebAppMenuWareSupervisoryCheckPlan();

            logger.info("定量包装商品监督抽查结果");
            this.addWebAppMenuWareSupervisoryCheckResult();

            logger.info("定量包装检验原始记录");
            this.addWebAppMenuCheckPrimaryRecord();

            logger.info("定量包装商品监督抽查通报");
            this.addWebAppMenuWareSupervisoryCheckBulletin();

            logger.info("注册信息");
            this.addWebAppMenuQuantitativePackageRegisterInfo();

            logger.info("---机构考核模块---");

            logger.info("技术机构监督考核模块");
            this.addWebAppMenuTechnicalInstitutionAssessment();

            logger.info("---计量标准考核（复查）模块---");

            logger.info("计量标准考核（复查）申请");
            this.addWebAppMenuMeasurementStandardAssessmentApply();

            logger.info("---能源计量管理模块---");

            logger.info("能源计量器具管理");
            this.addWebAppMenuEnergyMeasurementInstrumentsManagement();

            logger.info("能源计量器具档案");
            this.addWebAppMenuEnergyMeasurementInstrumentsFile();

            logger.info("用能系统管理");
            this.addWebAppMenuEnergyUseSystemManagement();

            logger.info("能源计量人员管理");
            this.addWebAppMenuEnergyMeteringPersonnelManagement();

            logger.info("用能管理");
            this.addWebAppMenuEnergyUseManagement();

            logger.info("重点用能企业信息查询");
            this.addWebAppMenuKeyEnergyEnterpriseInfoQuery();

            logger.info("重点用能企业数据分析");
            this.addWebAppMenuKeyEnergyEnterpriseDataAnalysis();

            logger.info("区域能耗数据分析");
            this.addWebAppMenuRegionalEnergyDataAnalysis();

            logger.info("---监督抽查模块---");

            logger.info("授权检定机构监督抽查");
            this.addWebAppMenuAuthorizedInspectionAgencySupervise();

            logger.info("计量标准监督抽查");
            this.addWebAppMenuMeasurementStandardInspection();

            logger.info("计量器具制造企业监督抽查");
            this.addWebAppMenuMeasurementInstrumentsManufacturingInspection();

            logger.info("定量包装商品净含量监督抽查");
            this.addWebAppMenuWareNetContentInspection();

            logger.info("重点计量器具监督抽查");
            this.addWebAppMenuKeyMeasurementInstrumentsInspection();

            logger.info("重点耗能企业计量器具监督抽查");
            this.addWebAppMenuKeyEnergyEnterpriseInstrumentsInspection();

            logger.info("能源标识使用单位监督抽查");
            this.addWebAppMenuEnergyLabelUseEnterpriseInspection();

            logger.info("法定计量单位监督抽查");
            this.addWebAppMenuLegalMeasurementEnterpriseInspection();
        }

        if (isAdd) {
            logger.debug("有新的记录，并执行更新保存操作");
            webAppMenuRepository.save(webAppMenus);
        }
    }

    // 用户，部门
    private void addDepartment() {
        WebAppMenu departmentMenu = new WebAppMenu();
        departmentMenu.setAbstract(true);
        departmentMenu.setRouteName("department");
        departmentMenu.setName("用户管理");
        departmentMenu.setDescription("对系统中所有的用户（器具用户，管理部门，技术机构）进行管理，适用于管理部门，非管理部门赋予该权限，前台将报错");
        departmentMenu.setWeight(weight++);
        webAppMenus.add(departmentMenu);

        WebAppMenu instrumentUserMenu = new WebAppMenu();
        instrumentUserMenu.setRouteName("instrumentUser");
        instrumentUserMenu.setName("器具用户");
        instrumentUserMenu.setDescription("器具用户管理");
        instrumentUserMenu.setParentRouteWebAppMenu(departmentMenu);
        instrumentUserMenu.setParentWebAppMenu(departmentMenu);
        instrumentUserMenu.setWeight(weight++);
        webAppMenus.add(instrumentUserMenu);

        WebAppMenu technicalInstitutionMenu = new WebAppMenu();
        technicalInstitutionMenu.setRouteName("technicalInstitution");
        technicalInstitutionMenu.setName("技术机构");
        technicalInstitutionMenu.setDescription("技术机构管理");
        technicalInstitutionMenu.setParentRouteWebAppMenu(departmentMenu);
        technicalInstitutionMenu.setParentWebAppMenu(departmentMenu);
        technicalInstitutionMenu.setWeight(weight++);
        webAppMenus.add(technicalInstitutionMenu);

        WebAppMenu enterpriseMenu = new WebAppMenu();
        enterpriseMenu.setRouteName("enterprise");
        enterpriseMenu.setName("生产企业");
        enterpriseMenu.setDescription("生产企业管理");
        enterpriseMenu.setParentRouteWebAppMenu(departmentMenu);
        enterpriseMenu.setParentWebAppMenu(departmentMenu);
        enterpriseMenu.setWeight(weight++);
        webAppMenus.add(enterpriseMenu);

        WebAppMenu managementMenu = new WebAppMenu();
        managementMenu.setRouteName("management");
        managementMenu.setName("管理部门");
        managementMenu.setDescription("管理部门");
        managementMenu.setParentRouteWebAppMenu(departmentMenu);
        managementMenu.setParentWebAppMenu(departmentMenu);
        managementMenu.setWeight(weight++);
        webAppMenus.add(managementMenu);
    }
    protected void addWebAppMenuMain() {
        logger.info("----- 添加前台菜单信息 -----");

        WebAppMenu dashboardMenu = new WebAppMenu();
        dashboardMenu.setName("仪表台");
        dashboardMenu.setPinyin("yibiaotai");
        dashboardMenu.setShowBadge(true);
        dashboardMenu.setBadgeStyle("success");
        dashboardMenu.setBadgeContent("V0.1");
        dashboardMenu.setDescription("首页，请为所有用户配置该菜单");
        dashboardMenu.setRouteName("dashboard");
        dashboardMenu.setWeight(weight++);
        dashboardMenu.setParentRouteWebAppMenu(mainMenu);
        webAppMenus.add(dashboardMenu);

        WebAppMenu analyticsMenu = new WebAppMenu();
        analyticsMenu.setName("大数据平台");
        analyticsMenu.setRouteName("analytics");
        analyticsMenu.setShowBadge(true);
        analyticsMenu.setBadgeStyle("warning");
        analyticsMenu.setBadgeContent("NEW");
        analyticsMenu.setDescription("尚未开发");
        analyticsMenu.setParentRouteWebAppMenu(mainMenu);
        analyticsMenu.setWeight(weight++);
        webAppMenus.add(analyticsMenu);

    }

    // 强检器具备案
    protected  void addForceInstrumentFiling() {
        logger.info("添加强检器具备案菜单");

        WebAppMenu instrumentForcedMenu = new WebAppMenu();
        instrumentForcedMenu.setAbstract(true);
        instrumentForcedMenu.setRouteName("InstrumentForced");
        instrumentForcedMenu.setName("强检器具备案管理");
        instrumentForcedMenu.setWeight(weight++);
        instrumentForcedMenu.setBadgeStyle("warning");
        instrumentForcedMenu.setBadgeContent("NEW");
        instrumentForcedMenu.setShowBadge(true);
        webAppMenus.add(instrumentForcedMenu);

        WebAppMenu mandatoryCalibrateRecordMenu = new WebAppMenu();
        mandatoryCalibrateRecordMenu.setName("备案申请");
        mandatoryCalibrateRecordMenu.setRouteName("InstrumentForcedApply");
        mandatoryCalibrateRecordMenu.setParentRouteWebAppMenu(instrumentForcedMenu);
        mandatoryCalibrateRecordMenu.setParentWebAppMenu(instrumentForcedMenu);
        mandatoryCalibrateRecordMenu.setDescription("适用于器具用户，对器具进行备案操作");
        mandatoryCalibrateRecordMenu.setWeight(weight++);
        webAppMenus.add(mandatoryCalibrateRecordMenu);

        WebAppMenu technologyDepartmentIntegratedQuery = new WebAppMenu();
        technologyDepartmentIntegratedQuery.setName("备案确认");
        technologyDepartmentIntegratedQuery.setDescription("适用于技术机构，用于查询管理部门指定给其的器具并进行确认");
        technologyDepartmentIntegratedQuery.setRouteName("InstrumentForcedTechnologyDepartmentIntegratedQuery");
        technologyDepartmentIntegratedQuery.setParentRouteWebAppMenu(instrumentForcedMenu);
        technologyDepartmentIntegratedQuery.setParentWebAppMenu(instrumentForcedMenu);
        technologyDepartmentIntegratedQuery.setWeight(weight++);
        technologyDepartmentIntegratedQuery.setShow(false);
        webAppMenus.add(technologyDepartmentIntegratedQuery);

        WebAppMenu instrumentMenu = new WebAppMenu();
        instrumentMenu.setRouteName("Instrument");
        instrumentMenu.setParentRouteWebAppMenu(instrumentForcedMenu);
        instrumentMenu.setParentWebAppMenu(instrumentForcedMenu);
        instrumentMenu.setTemplateUrl("views/mandatory/instrument/index.html");
        instrumentMenu.setName("综合查询");
        instrumentMenu.setDescription("适用于管理部门，综合查询所辖区域内的强检器具");
        instrumentMenu.setWeight(weight++);
        webAppMenus.add(instrumentMenu);

        WebAppMenu integratedMenu = new WebAppMenu();
        integratedMenu.setAbstract(false);
        integratedMenu.setRouteName("Integrated");
        integratedMenu.setTemplateUrl("views/mandatory/integrated/index.html");
        integratedMenu.setName("综合查询");
        integratedMenu.setWeight(weight++);
        integratedMenu.setDescription("适用于器具用户，查询器具用户拥有的器具");
        integratedMenu.setParentRouteWebAppMenu(instrumentForcedMenu);
        integratedMenu.setParentWebAppMenu(instrumentForcedMenu);
        webAppMenus.add(integratedMenu);

//        WebAppMenu appointCheckInstrumentMenu = new WebAppMenu();
//        appointCheckInstrumentMenu.setRouteName("appointCheckInstrument");
//        appointCheckInstrumentMenu.setParentRouteWebAppMenu(instrumentForcedMenu);
//        appointCheckInstrumentMenu.setParentWebAppMenu(instrumentForcedMenu);
//        appointCheckInstrumentMenu.setName("综合查询");
//        appointCheckInstrumentMenu.setWeight(weight++);
//        appointCheckInstrumentMenu.setDescription("适用于技术机构（老功能，废弃）");
//        webAppMenus.add(appointCheckInstrumentMenu);
    }

    protected void addTimeoutCheckApply() {
        logger.info("添加强检器具超期检定备案菜单");

        WebAppMenu timeoutCheckApplyParentMenu = new WebAppMenu();
        timeoutCheckApplyParentMenu.setAbstract(true);
        timeoutCheckApplyParentMenu.setRouteName("InstrumentForcedTimeOutCheckApplyParent");
        timeoutCheckApplyParentMenu.setName("强检器具超期检定备案管理");
        timeoutCheckApplyParentMenu.setWeight(weight++);
        webAppMenus.add(timeoutCheckApplyParentMenu);

        WebAppMenu timeoutCheckApplyMenu = new WebAppMenu();
        timeoutCheckApplyMenu.setName("检定申请");
        timeoutCheckApplyMenu.setRouteName("InstrumentForcedTimeOutCheckApplyIndex");
        timeoutCheckApplyMenu.setDescription("适用于器具用户、管理部门；查看申请工作的办理情况");
        timeoutCheckApplyMenu.setParentRouteWebAppMenu(timeoutCheckApplyParentMenu);
        timeoutCheckApplyMenu.setParentWebAppMenu(timeoutCheckApplyParentMenu);
        timeoutCheckApplyMenu.setWeight(weight++);
        webAppMenus.add(timeoutCheckApplyMenu);

        WebAppMenu integratedQueryApplyMenu = new WebAppMenu();
        integratedQueryApplyMenu.setName("综合查询");
        integratedQueryApplyMenu.setRouteName("InstrumentForcedTimeoutCheckApplyIntegratedQueryIndex");
        integratedQueryApplyMenu.setDescription("适用于器具用户, 对自己过期器具的综合查询");
        integratedQueryApplyMenu.setParentRouteWebAppMenu(timeoutCheckApplyParentMenu);
        integratedQueryApplyMenu.setParentWebAppMenu(timeoutCheckApplyParentMenu);
        integratedQueryApplyMenu.setWeight(weight++);
        webAppMenus.add(integratedQueryApplyMenu);

        WebAppMenu departmentQueryApplyMenu = new WebAppMenu();
        departmentQueryApplyMenu.setName("综合查询");
        departmentQueryApplyMenu.setRouteName("InstrumentForcedTimeoutCheckApplyDepartmentQueryIndex");
        departmentQueryApplyMenu.setDescription("适用于管理部门, 对自己所在区域以及子区域的过期器具的综合查询");
        departmentQueryApplyMenu.setParentRouteWebAppMenu(timeoutCheckApplyParentMenu);
        departmentQueryApplyMenu.setParentWebAppMenu(timeoutCheckApplyParentMenu);
        departmentQueryApplyMenu.setWeight(weight++);
        webAppMenus.add(departmentQueryApplyMenu);
    }

    // 强检菜单
    protected void addWebAppMenuIntegrated() {
        WebAppMenu mandatoryMenu = new WebAppMenu();
        mandatoryMenu.setAbstract(true);
        mandatoryMenu.setRouteName("mandatory");
        mandatoryMenu.setName("强检器具检定管理");
        mandatoryMenu.setWeight(weight++);
        webAppMenus.add(mandatoryMenu);




//        WebAppMenu numberCategoriesMenu = new WebAppMenu();
//        numberCategoriesMenu.setRouteName("NumberCategories");
//        numberCategoriesMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        numberCategoriesMenu.setParentWebAppMenu(mandatoryMenu);
//        numberCategoriesMenu.setName("分类统计");
//        numberCategoriesMenu.setWeight(weight++);
//        numberCategoriesMenu.setDescription("强制检定管理计量器具强制检定计量器具分类统计");
//        webAppMenus.add(numberCategoriesMenu);


//        WebAppMenu userapplication = new WebAppMenu();
//        userapplication.setRouteName("Userapplication");
//        userapplication.setParentRouteWebAppMenu(mandatoryMenu);
//        userapplication.setParentWebAppMenu(mandatoryMenu);
//        userapplication.setName("用户检定申请");
//        userapplication.setWeight(weight++);
//        userapplication.setDescription("强制检定管理计量器具用户检定申请");
//        webAppMenus.add(userapplication);

//        WebAppMenu replyMenu = new WebAppMenu();
//        replyMenu.setRouteName("Reply");
//        replyMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        replyMenu.setParentWebAppMenu(mandatoryMenu);
//        replyMenu.setName("机构申请回复");
//        replyMenu.setDescription("强制检定管理计量器具机构申请回复");
//        webAppMenus.add(replyMenu);

//        WebAppMenu checkDetailMenu = new WebAppMenu();
//        checkDetailMenu.setRouteName("CheckDetail");
//        checkDetailMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        checkDetailMenu.setParentWebAppMenu(mandatoryMenu);
//        checkDetailMenu.setName("检定信息");
//        checkDetailMenu.setDescription("强制检定管理计量器具检定信息");
//        webAppMenus.add(replyMenu);
        WebAppMenu integratedAuditMenu = new WebAppMenu();
        integratedAuditMenu.setAbstract(false);
        integratedAuditMenu.setRouteName("integratedAudit");
        integratedAuditMenu.setName("检定档案管理");
        integratedAuditMenu.setWeight(weight++);
        integratedAuditMenu.setDescription("器具用户。器具的检定数据管理");
        integratedAuditMenu.setParentRouteWebAppMenu(mandatoryMenu);
        integratedAuditMenu.setParentWebAppMenu(mandatoryMenu);
        webAppMenus.add(integratedAuditMenu);

        WebAppMenu integratedAdd = new WebAppMenu();
        integratedAdd.setRouteName("IntegratedAdd");
        integratedAdd.setParentRouteWebAppMenu(mandatoryMenu);
        integratedAdd.setParentWebAppMenu(mandatoryMenu);
        integratedAdd.setName("新增");
        integratedAdd.setDescription("强制检定管理计量器具用户强制检定计量器具档案 -- 新增(申请)");
        integratedAdd.setShow(false);
        webAppMenus.add(integratedAdd);

        WebAppMenu instrumentCheckInfoManageMenu = new WebAppMenu();
        instrumentCheckInfoManageMenu.setRouteName("instrumentCheckInfoManage");
        instrumentCheckInfoManageMenu.setParentRouteWebAppMenu(mandatoryMenu);
        instrumentCheckInfoManageMenu.setParentWebAppMenu(mandatoryMenu);
        instrumentCheckInfoManageMenu.setName("器具检定综合查询");
        instrumentCheckInfoManageMenu.setWeight(weight++);
        instrumentCheckInfoManageMenu.setDescription("管理部门。所辖器具用户的器具检定信息的综合查询");
        webAppMenus.add(instrumentCheckInfoManageMenu);

        WebAppMenu instrumentCheckInfoMenu = new WebAppMenu();
        instrumentCheckInfoMenu.setRouteName("instrumentCheckInfo");
        instrumentCheckInfoMenu.setParentRouteWebAppMenu(mandatoryMenu);
        instrumentCheckInfoMenu.setParentWebAppMenu(mandatoryMenu);
        instrumentCheckInfoMenu.setName("器具检定档案管理");
        instrumentCheckInfoMenu.setWeight(weight++);
        instrumentCheckInfoMenu.setDescription("技术机构。检定过的器具检定记录");
        webAppMenus.add(instrumentCheckInfoMenu);

        WebAppMenu checkApplyForInstrumentUserMenu = new WebAppMenu();
        checkApplyForInstrumentUserMenu.setRouteName("checkApplyForInstrumentUser");
        checkApplyForInstrumentUserMenu.setParentRouteWebAppMenu(mandatoryMenu);
        checkApplyForInstrumentUserMenu.setParentWebAppMenu(mandatoryMenu);
        checkApplyForInstrumentUserMenu.setName("检定申请");
        checkApplyForInstrumentUserMenu.setWeight(weight++);
        checkApplyForInstrumentUserMenu.setDescription("器具用户。检定申请");
        webAppMenus.add(checkApplyForInstrumentUserMenu);

        WebAppMenu checkApplyForTechnicalInstitutionMenu = new WebAppMenu();
        checkApplyForTechnicalInstitutionMenu.setRouteName("checkApplyForTechnicalInstitution");
        checkApplyForTechnicalInstitutionMenu.setParentRouteWebAppMenu(mandatoryMenu);
        checkApplyForTechnicalInstitutionMenu.setParentWebAppMenu(mandatoryMenu);
        checkApplyForTechnicalInstitutionMenu.setName("检定申请");
        checkApplyForTechnicalInstitutionMenu.setWeight(weight++);
        checkApplyForTechnicalInstitutionMenu.setDescription("技术机构。查看处理器具用户提交的检定申请");
        webAppMenus.add(checkApplyForTechnicalInstitutionMenu);


//        WebAppMenu instrumentCheckInfoStatisticsMenu = new WebAppMenu();
//        instrumentCheckInfoStatisticsMenu.setRouteName("instrumentCheckInfoStatistics");
//        instrumentCheckInfoStatisticsMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        instrumentCheckInfoStatisticsMenu.setParentWebAppMenu(mandatoryMenu);
//        instrumentCheckInfoStatisticsMenu.setName("检定统计");
//        instrumentCheckInfoStatisticsMenu.setWeight(weight++);
//        instrumentCheckInfoStatisticsMenu.setDescription("强制检定管理计量器具强制检定计量器具年检定量统计");
//        webAppMenus.add(instrumentCheckInfoStatisticsMenu);
//
//        WebAppMenu passRateMenu = new WebAppMenu();
//        passRateMenu.setRouteName("PassRate");
//        passRateMenu.setParentRouteWebAppMenu(mandatoryMenu);
//        passRateMenu.setParentWebAppMenu(mandatoryMenu);
//        passRateMenu.setTemplateUrl("views/mandatory/passrate/index.html");
//        passRateMenu.setName("合格率统计");
//        passRateMenu.setDescription("强制检定管理计量器具强制检定计量器具合格率统计");
//        webAppMenus.add(passRateMenu);


    }

    // 非强检菜单
    protected void addWebAppMenuOptions() {
        WebAppMenu optionalMenu = new WebAppMenu();
        optionalMenu.setAbstract(true);
        optionalMenu.setRouteName("optional");
        optionalMenu.setWeight(weight++);
        optionalMenu.setName("非强制检定管理");
        webAppMenus.add(optionalMenu);

        WebAppMenu numberSubjectsMenu = new WebAppMenu();
        numberSubjectsMenu.setRouteName("NumberSubjects");
        numberSubjectsMenu.setParentRouteWebAppMenu(optionalMenu);
        numberSubjectsMenu.setParentWebAppMenu(optionalMenu);
        numberSubjectsMenu.setWeight(weight++);
        numberSubjectsMenu.setName("非强制检定工作计量器具档案");
        numberSubjectsMenu.setDescription("非强制检定管理计量器具非强制检定工作计量器具档案");
        webAppMenus.add(numberSubjectsMenu);

        WebAppMenu optionalIntegratedMenu = new WebAppMenu();
        optionalIntegratedMenu.setRouteName("optionalIntegrated");
        optionalIntegratedMenu.setParentRouteWebAppMenu(optionalMenu);
        optionalIntegratedMenu.setParentWebAppMenu(optionalMenu);
        optionalIntegratedMenu.setTemplateUrl("views/optional/integrated/index.html");
        optionalIntegratedMenu.setName("非强制检定工作计量器具查询");
        optionalIntegratedMenu.setDescription("非强制检定管理非强制检定工作计量器具查询");

        WebAppMenu integratedAddMenu = new WebAppMenu();
        integratedAddMenu.setRouteName("optionalIntegratedAdd");
        integratedAddMenu.setParentRouteWebAppMenu(optionalMenu);
        integratedAddMenu.setParentWebAppMenu(optionalMenu);
        integratedAddMenu.setTemplateUrl("views/optional/integrated/add.html");
        integratedAddMenu.setName("添加");
        integratedAddMenu.setShow(false);
        integratedAddMenu.setDescription("非强制检定管理非强制检定工作计量器具查询--添加");
        webAppMenus.add(integratedAddMenu);

        WebAppMenu optionalCheckDetailMenu = new WebAppMenu();
        optionalCheckDetailMenu.setRouteName("CheckDetail");
        optionalCheckDetailMenu.setParentRouteWebAppMenu(optionalMenu);
        optionalCheckDetailMenu.setParentWebAppMenu(optionalMenu);
        optionalCheckDetailMenu.setTemplateUrl("views/optional/checkdetail/index.html");
        optionalCheckDetailMenu.setName("检定档案管理");
        optionalCheckDetailMenu.setDescription("非强制检定管理计量器具检定信息");
        webAppMenus.add(optionalCheckDetailMenu);

        WebAppMenu optionalUserapplicationMenu = new WebAppMenu();
        optionalUserapplicationMenu.setRouteName("Userapplication");
        optionalUserapplicationMenu.setParentRouteWebAppMenu(optionalMenu);
        optionalUserapplicationMenu.setParentWebAppMenu(optionalMenu);
        optionalUserapplicationMenu.setTemplateUrl("views/optional/userapplication/index.html");
        optionalUserapplicationMenu.setName("用户检定申请");
        optionalUserapplicationMenu.setDescription("非强制检定管理计量器具用户检定申请");
        webAppMenus.add(optionalUserapplicationMenu);

//        WebAppMenu optionalReplyMenu = new WebAppMenu();
//        optionalReplyMenu.setRouteName("Reply");
//        optionalReplyMenu.setParentRouteWebAppMenu(optionalMenu);
//        optionalReplyMenu.setParentWebAppMenu(optionalMenu);
//        optionalReplyMenu.setTemplateUrl("views/optional/reply/index.html");
//        optionalReplyMenu.setName("机构申请回复");
//        optionalReplyMenu.setDescription("非强制检定管理计量器具机构申请回复");
//        webAppMenus.add(optionalReplyMenu);
    }

    // 技术机构
    protected void addWebAppMenuStandard() {
        WebAppMenu standardMenu = new WebAppMenu();
        standardMenu.setAbstract(true);
        standardMenu.setRouteName("standard");
        standardMenu.setWeight(weight++);
        standardMenu.setName("标准装置管理");
        webAppMenus.add(standardMenu);

        WebAppMenu standardFileMenu = new WebAppMenu();
        standardFileMenu.setRouteName("File");
        standardFileMenu.setParentRouteWebAppMenu(standardMenu);
        standardFileMenu.setParentWebAppMenu(standardMenu);
        standardFileMenu.setName("综合查询");
        standardFileMenu.setDescription("技术机构。计量标准装置综合查询");
        standardFileMenu.setWeight(weight++);
        webAppMenus.add(standardFileMenu);

        WebAppMenu standardFileAddMenu = new WebAppMenu();
        standardFileAddMenu.setRouteName("FileAdd");
        standardFileAddMenu.setParentRouteWebAppMenu(standardMenu);
        standardFileAddMenu.setParentWebAppMenu(standardFileMenu);
        standardFileAddMenu.setName("新增");
        standardFileAddMenu.setDescription("技术机构。新增标准装置");
        standardFileAddMenu.setShow(false);
        standardFileAddMenu.setWeight(weight++);
        webAppMenus.add(standardFileAddMenu);

        WebAppMenu deviceSetManageMenu = new WebAppMenu();
        deviceSetManageMenu.setRouteName("deviceSetManage");
        deviceSetManageMenu.setParentRouteWebAppMenu(standardMenu);
        deviceSetManageMenu.setParentWebAppMenu(standardMenu);
        deviceSetManageMenu.setName("档案管理");
        deviceSetManageMenu.setDescription("技术机构。管理自己拥有的标准装置");
        deviceSetManageMenu.setWeight(weight++);
        webAppMenus.add(deviceSetManageMenu);

        WebAppMenu standardFixedassetsMenu = new WebAppMenu();
//        standardFixedassetsMenu.setRouteName("Fixedassets");
//        standardFixedassetsMenu.setParentRouteWebAppMenu(standardMenu);
//        standardFixedassetsMenu.setParentWebAppMenu(standardFileMenu);
//        standardFixedassetsMenu.setName("技术机构固定资产一览表");
//        standardFixedassetsMenu.setDescription("技术机构管理技术机构固定资产一览表");
//        webAppMenus.add(standardFixedassetsMenu);

        WebAppMenu standardAuthorizationMenu = new WebAppMenu();
        standardAuthorizationMenu.setRouteName("Authorization");
        standardAuthorizationMenu.setParentRouteWebAppMenu(standardMenu);
        standardAuthorizationMenu.setParentWebAppMenu(standardMenu);
        standardAuthorizationMenu.setName("授权检定项目综合查询");
        standardAuthorizationMenu.setWeight(weight++);
        standardAuthorizationMenu.setDescription("管理部门。所辖区域内的技术机构的授权项目综合管理");
        webAppMenus.add(standardAuthorizationMenu);


        WebAppMenu standardAuthorizationManageMenu = new WebAppMenu();
        standardAuthorizationManageMenu.setRouteName("FileDeviceInstrument");
        standardAuthorizationManageMenu.setParentRouteWebAppMenu(standardMenu);
        standardAuthorizationManageMenu.setParentWebAppMenu(standardMenu);
        standardAuthorizationManageMenu.setName("授权检定项目管理");
        standardAuthorizationManageMenu.setWeight(weight++);
        standardAuthorizationManageMenu.setDescription("技术机构。授权检定项目管理");
        webAppMenus.add(standardAuthorizationManageMenu);

        WebAppMenu standardAuthorizationAddMenu = new WebAppMenu();
        standardAuthorizationAddMenu.setRouteName("AuthorizationManageAdd");
        standardAuthorizationAddMenu.setParentRouteWebAppMenu(standardMenu);
        standardAuthorizationAddMenu.setParentWebAppMenu(standardAuthorizationManageMenu);
        standardAuthorizationAddMenu.setName("新增");
        standardAuthorizationAddMenu.setDescription("技术机构。新增授权检定项目");
        standardAuthorizationAddMenu.setShow(false);
        standardAuthorizationAddMenu.setWeight(weight++);
        webAppMenus.add(standardAuthorizationAddMenu);

//        WebAppMenu standardAbilityMenu = new WebAppMenu();
//        standardAbilityMenu.setRouteName("Ability");
//        standardAbilityMenu.setParentRouteWebAppMenu(standardMenu);
//        standardAbilityMenu.setParentWebAppMenu(standardMenu);
//        standardAbilityMenu.setWeight(weight++);
//        standardAbilityMenu.setName("技术机构能力建设申请、进度表");
//        standardAbilityMenu.setDescription("技术机构管理技术机构能力建设申请进度表");
//        webAppMenus.add(standardAbilityMenu);

//        WebAppMenu standardScheduleMenu = new WebAppMenu();
//        standardScheduleMenu.setRouteName("Schedule");
//        standardScheduleMenu.setParentRouteWebAppMenu(standardMenu);
//        standardScheduleMenu.setParentWebAppMenu(standardMenu);
//        standardScheduleMenu.setName("检定进度查询");
//        standardScheduleMenu.setDescription("技术机构管理检定进度查询");
//        webAppMenus.add(standardScheduleMenu);

//        WebAppMenu standardTechnologyMenu = new WebAppMenu();
//        standardTechnologyMenu.setRouteName("Technology");
//        standardTechnologyMenu.setParentRouteWebAppMenu(standardMenu);
//        standardTechnologyMenu.setParentWebAppMenu(standardMenu);
//        standardTechnologyMenu.setName("授权检定机构查询");
//        standardTechnologyMenu.setWeight(weight++);
//        standardTechnologyMenu.setDescription("技术机构管理计量器具授权检定机构查询");
//        webAppMenus.add(standardTechnologyMenu);
    }

    // 计量器具产品
    protected void addWebAppMenuMeasuringDevice() {

        WebAppMenu measuringdeviceMenu = new WebAppMenu();
        measuringdeviceMenu.setName("计量器具产品管理");
        measuringdeviceMenu.setAbstract(true);
        measuringdeviceMenu.setRouteName("measuringdevice");
        measuringdeviceMenu.setWeight(weight++);
        webAppMenus.add(measuringdeviceMenu);

        WebAppMenu measuringdeviceIntegratedMenu = new WebAppMenu();
        measuringdeviceIntegratedMenu.setRouteName("Integrated2");
        measuringdeviceIntegratedMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceIntegratedMenu.setParentWebAppMenu(measuringdeviceMenu);
        measuringdeviceIntegratedMenu.setName("计量器具产品档案");
        measuringdeviceIntegratedMenu.setDescription("计量器具产品管理计量器具产品档案");
        webAppMenus.add(measuringdeviceIntegratedMenu);

        WebAppMenu measuringdeviceApplianceArchivesMenu = new WebAppMenu();
        measuringdeviceApplianceArchivesMenu.setRouteName("ApplianceArchives");
        measuringdeviceApplianceArchivesMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceApplianceArchivesMenu.setParentWebAppMenu(measuringdeviceMenu);
        measuringdeviceApplianceArchivesMenu.setName("计量器具生产企业档案");
        measuringdeviceApplianceArchivesMenu.setDescription("计量器具产品管理计量器具生产企业档案");
        webAppMenus.add(measuringdeviceApplianceArchivesMenu);

        WebAppMenu measuringdeviceApplianceArchivesAddMenu = new WebAppMenu();
        measuringdeviceApplianceArchivesAddMenu.setRouteName("ApplianceArchives/add");
        measuringdeviceApplianceArchivesAddMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceApplianceArchivesAddMenu.setParentWebAppMenu(measuringdeviceApplianceArchivesMenu);
        measuringdeviceApplianceArchivesAddMenu.setName("计量器具生产企业档案--新增");
        measuringdeviceApplianceArchivesAddMenu.setDescription("计量器具产品管理计量器具生产企业档案--新增");
        measuringdeviceApplianceArchivesAddMenu.setShow(false);
        webAppMenus.add(measuringdeviceApplianceArchivesAddMenu);

        WebAppMenu measuringdeviceEnterpriseFileMenu = new WebAppMenu();
        measuringdeviceEnterpriseFileMenu.setRouteName("EnterpriseFile");
        measuringdeviceEnterpriseFileMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceEnterpriseFileMenu.setParentWebAppMenu(measuringdeviceMenu);
        measuringdeviceEnterpriseFileMenu.setName("获证产品目录");
        measuringdeviceEnterpriseFileMenu.setDescription("获证产品目录");
        webAppMenus.add(measuringdeviceEnterpriseFileMenu);

        WebAppMenu measuringdeviceEnterpriseFileAddMenu = new WebAppMenu();
        measuringdeviceEnterpriseFileAddMenu.setRouteName("EnterpriseFile/add");
        measuringdeviceEnterpriseFileAddMenu.setParentRouteWebAppMenu(measuringdeviceMenu);
        measuringdeviceEnterpriseFileAddMenu.setParentWebAppMenu(measuringdeviceEnterpriseFileMenu);
        measuringdeviceEnterpriseFileAddMenu.setName("获证产品目录--新增");
        measuringdeviceEnterpriseFileAddMenu.setDescription("获证产品目录--新增");
        measuringdeviceEnterpriseFileAddMenu.setShow(false);
        webAppMenus.add(measuringdeviceEnterpriseFileAddMenu);
    }

    // 重点用能企业
    protected void addWebAppMenuEnergyEnterprise() {
        WebAppMenu energyEnterpriseMenu = new WebAppMenu();
        energyEnterpriseMenu.setName("重点用能企业计量管理");
        energyEnterpriseMenu.setAbstract(true);
        energyEnterpriseMenu.setWeight(weight++);
        energyEnterpriseMenu.setRouteName("energyenterprise");
        webAppMenus.add(energyEnterpriseMenu);

        WebAppMenu energyEnterpriseFileMenu = new WebAppMenu();
        energyEnterpriseFileMenu.setRouteName("File");
        energyEnterpriseFileMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseFileMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseFileMenu.setName("能源计量器具档案");
        energyEnterpriseFileMenu.setDescription("重点用能企业计量管理能源计量器具档案");
        webAppMenus.add(energyEnterpriseFileMenu);

        WebAppMenu energyEnterpriseIntegratedMenu = new WebAppMenu();
        energyEnterpriseIntegratedMenu.setRouteName("Integrated");
        energyEnterpriseIntegratedMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseIntegratedMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseIntegratedMenu.setName("能源计量器具一览表");
        energyEnterpriseIntegratedMenu.setDescription("重点用能企业计量管理能源计量器具一览表");
        webAppMenus.add(energyEnterpriseIntegratedMenu);

        WebAppMenu energyEnterpriseMeasuringdeviceMenu = new WebAppMenu();
        energyEnterpriseMeasuringdeviceMenu.setRouteName("Measuringdevice");
        energyEnterpriseMeasuringdeviceMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMeasuringdeviceMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMeasuringdeviceMenu.setName("进出用能单位能源计量器具一览表");
        energyEnterpriseMeasuringdeviceMenu.setDescription("重点用能企业计量管理进出用能单位能源计量器具一览表");
        webAppMenus.add(energyEnterpriseMeasuringdeviceMenu);

        WebAppMenu energyEnterpriseSecondarydeviceMenu = new WebAppMenu();
        energyEnterpriseSecondarydeviceMenu.setRouteName("Secondarydevice");
        energyEnterpriseSecondarydeviceMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseSecondarydeviceMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseSecondarydeviceMenu.setName("进出主要次级用能单位能源计量器具一览表");
        energyEnterpriseSecondarydeviceMenu.setDescription("重点用能企业计量管理进出主要次级用能单位能源计量器具一览表");
        webAppMenus.add(energyEnterpriseSecondarydeviceMenu);

        WebAppMenu energyEnterpriseMaindeviceMenu = new WebAppMenu();
        energyEnterpriseMaindeviceMenu.setRouteName("Maindevice");
        energyEnterpriseMaindeviceMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMaindeviceMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMaindeviceMenu.setName("主要用能设备能源计量器具一览表");
        energyEnterpriseMaindeviceMenu.setDescription("重点用能企业计量管理主要用能设备能源计量器具一览表");
        webAppMenus.add(energyEnterpriseMaindeviceMenu);

        WebAppMenu energyEnterpriseOtherdeviceMenu = new WebAppMenu();
        energyEnterpriseOtherdeviceMenu.setRouteName("Otherdevice");
        energyEnterpriseOtherdeviceMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseOtherdeviceMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseOtherdeviceMenu.setTemplateUrl("views/energyenterprise/otherdevice/index.html");
        energyEnterpriseOtherdeviceMenu.setName("主要用能设备能源计量器具一览表");
        energyEnterpriseOtherdeviceMenu.setDescription("重点用能企业计量管理其他能源计量器具一览表");
        webAppMenus.add(energyEnterpriseMaindeviceMenu);

        WebAppMenu energyEnterpriseSummaryMenu = new WebAppMenu();
        energyEnterpriseSummaryMenu.setRouteName("Summary");
        energyEnterpriseSummaryMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseSummaryMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseSummaryMenu.setName("能源计量器具配备情况汇总表");
        energyEnterpriseSummaryMenu.setDescription("重点用能企业计量管理能源计量器具配备情况汇总表");
        webAppMenus.add(energyEnterpriseSummaryMenu);

        WebAppMenu energyEnterpriseStatisticalsummaryMenu = new WebAppMenu();
        energyEnterpriseStatisticalsummaryMenu.setRouteName("Statisticalsummary");
        energyEnterpriseStatisticalsummaryMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseStatisticalsummaryMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseStatisticalsummaryMenu.setName("能源计量器具配备情况统计汇总表");
        energyEnterpriseStatisticalsummaryMenu.setDescription("重点用能企业计量管理能源计量器具配备情况统计汇总表");
        webAppMenus.add(energyEnterpriseStatisticalsummaryMenu);

        WebAppMenu energyEnterpriseAccuracyMenu = new WebAppMenu();
        energyEnterpriseAccuracyMenu.setRouteName("Accuracy");
        energyEnterpriseAccuracyMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseAccuracyMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseAccuracyMenu.setName("能源计量器具准确度等级统计汇总表");
        energyEnterpriseAccuracyMenu.setDescription("重点用能企业计量管理能源计量器具准确度等级统计汇总表");
        webAppMenus.add(energyEnterpriseAccuracyMenu);

        WebAppMenu energyEnterpriseFlowgraphMenu = new WebAppMenu();
        energyEnterpriseFlowgraphMenu.setRouteName("Flowgraph");
        energyEnterpriseFlowgraphMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseFlowgraphMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseFlowgraphMenu.setName("重点用能单位能源流向图");
        energyEnterpriseFlowgraphMenu.setDescription("重点用能企业计量管理重点用能单位能源流向图");
        webAppMenus.add(energyEnterpriseFlowgraphMenu);

        WebAppMenu energyEnterpriseMapMenu = new WebAppMenu();
        energyEnterpriseMapMenu.setRouteName("Map");
        energyEnterpriseMapMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMapMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseMapMenu.setName("能源采集点网络图");
        energyEnterpriseMapMenu.setDescription("重点用能企业计量管理能源采集点网络图");
        webAppMenus.add(energyEnterpriseMapMenu);

        WebAppMenu energyEnterpriseEquipmentMenu = new WebAppMenu();
        energyEnterpriseEquipmentMenu.setRouteName("Equipment");
        energyEnterpriseEquipmentMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseEquipmentMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseEquipmentMenu.setName("主要用能设备一览表");
        energyEnterpriseEquipmentMenu.setDescription("重点用能企业计量管理主要用能设备一览表");
        webAppMenus.add(energyEnterpriseEquipmentMenu);

        WebAppMenu energyEnterpriseTrainingMenu = new WebAppMenu();
        energyEnterpriseTrainingMenu.setRouteName("Training");
        energyEnterpriseTrainingMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseTrainingMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseTrainingMenu.setTemplateUrl("views/energyenterprise/training/index.html");
        energyEnterpriseTrainingMenu.setName("能源计量人员培训管理");
        energyEnterpriseTrainingMenu.setDescription("重点用能企业计量管理能源计量人员培训管理");
        webAppMenus.add(energyEnterpriseTrainingMenu);

        WebAppMenu energyEnterpriseStatisticsMenu = new WebAppMenu();
        energyEnterpriseStatisticsMenu.setRouteName("Statistics");
        energyEnterpriseStatisticsMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseStatisticsMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseStatisticsMenu.setName("重点用能单位能源购进、消费、库存统计表");
        energyEnterpriseStatisticsMenu.setDescription("重点用能企业计量管理重点用能单位能源购进、消费、库存统计表");
        webAppMenus.add(energyEnterpriseStatisticsMenu);

        WebAppMenu energyEnterpriseEnergyStatisticsMenu = new WebAppMenu();
        energyEnterpriseEnergyStatisticsMenu.setRouteName("Energystatistics");
        energyEnterpriseEnergyStatisticsMenu.setParentRouteWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseEnergyStatisticsMenu.setParentWebAppMenu(energyEnterpriseMenu);
        energyEnterpriseEnergyStatisticsMenu.setName("能源消费动态统计");
        energyEnterpriseEnergyStatisticsMenu.setDescription("重点用能企业计量管理能源消费动态统计");
        webAppMenus.add(energyEnterpriseEnergyStatisticsMenu);
    }

    // 监督抽查
    protected void addWebAppMenuSupervise() {
        WebAppMenu superviseMenu = new WebAppMenu();
        superviseMenu.setName("监督抽查");
        superviseMenu.setAbstract(true);
        superviseMenu.setRouteName("supervise");
        superviseMenu.setWeight(weight++);
        webAppMenus.add(superviseMenu);

        WebAppMenu superviseOrganizationMenu = new WebAppMenu();
        superviseOrganizationMenu.setRouteName("Organization");
        superviseOrganizationMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseOrganizationMenu.setParentWebAppMenu(superviseMenu);
        superviseOrganizationMenu.setName("授权检定机构监督抽查");
        superviseOrganizationMenu.setDescription("监督抽查授权检定机构监督抽查");
        webAppMenus.add(superviseOrganizationMenu);

        WebAppMenu superviseStandardMenu = new WebAppMenu();
        superviseStandardMenu.setRouteName("Standard");
        superviseStandardMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseStandardMenu.setParentWebAppMenu(superviseMenu);
        superviseStandardMenu.setName("计量标准监督抽查");
        superviseStandardMenu.setDescription("监督抽查计量标准监督抽查");
        webAppMenus.add(superviseStandardMenu);

        WebAppMenu superviseManufacturingMenu = new WebAppMenu();
        superviseManufacturingMenu.setRouteName("Manufacturing");
        superviseManufacturingMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseManufacturingMenu.setParentWebAppMenu(superviseMenu);
        superviseManufacturingMenu.setName("计量器具制造企业监督抽查");
        superviseManufacturingMenu.setDescription("监督抽查计量器具制造企业监督抽查");
        webAppMenus.add(superviseManufacturingMenu);

        WebAppMenu superviseSuttleMenu = new WebAppMenu();
        superviseSuttleMenu.setRouteName("Suttle");
        superviseSuttleMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseSuttleMenu.setParentWebAppMenu(superviseMenu);
        superviseSuttleMenu.setName("定量包装商品净含量监督抽查");
        superviseSuttleMenu.setDescription("监督抽查定量包装商品净含量监督抽查");
        webAppMenus.add(superviseSuttleMenu);

        WebAppMenu superviseInstrumentMenu = new WebAppMenu();
        superviseInstrumentMenu.setRouteName("Instrument");
        superviseInstrumentMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseInstrumentMenu.setParentWebAppMenu(superviseMenu);
        superviseInstrumentMenu.setTemplateUrl("views/supervise/instrument/index.html");
        superviseInstrumentMenu.setName("重点计量器具监督抽查");
        superviseInstrumentMenu.setDescription("监督抽查重点计量器具监督抽查");
        webAppMenus.add(superviseInstrumentMenu);

        WebAppMenu superviseEnterpriseInstrumentMenu = new WebAppMenu();
        superviseEnterpriseInstrumentMenu.setRouteName("Enterpriseinstrument");
        superviseEnterpriseInstrumentMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseEnterpriseInstrumentMenu.setParentWebAppMenu(superviseMenu);
        superviseEnterpriseInstrumentMenu.setName("重点计量器具监督抽查");
        superviseEnterpriseInstrumentMenu.setDescription("监督抽查重点计量器具监督抽查");
        webAppMenus.add(superviseEnterpriseInstrumentMenu);

        WebAppMenu superviseDepartmentMenu = new WebAppMenu();
        superviseDepartmentMenu.setRouteName("Department");
        superviseDepartmentMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseDepartmentMenu.setParentWebAppMenu(superviseMenu);
        superviseDepartmentMenu.setName("能源标识使用单位监督抽查");
        superviseDepartmentMenu.setDescription("监督抽查能源标识使用单位监督抽查");
        webAppMenus.add(superviseDepartmentMenu);

        WebAppMenu superviseMeasureMenu = new WebAppMenu();
        superviseMeasureMenu.setRouteName("Measure");
        superviseMeasureMenu.setParentRouteWebAppMenu(superviseMenu);
        superviseMeasureMenu.setParentWebAppMenu(superviseMenu);
        superviseMeasureMenu.setName("法定计量单位监督抽查");
        superviseMeasureMenu.setDescription("监督抽查法定计量单位监督抽查");
        webAppMenus.add(superviseMeasureMenu);
    }

    // 行政许可
    protected void addWebAppMenuBusiness() {
        WebAppMenu businessMenu = new WebAppMenu();
        businessMenu.setName("行政许可");
        businessMenu.setAbstract(true);
        businessMenu.setRouteName("business");
        businessMenu.setWeight(weight++);
        webAppMenus.add(businessMenu);

        WebAppMenu businessMeasurementMenu = new WebAppMenu();
        businessMeasurementMenu.setRouteName("Measurement");
        businessMeasurementMenu.setParentRouteWebAppMenu(businessMenu);
        businessMeasurementMenu.setParentWebAppMenu(businessMenu);
        businessMeasurementMenu.setTemplateUrl("views/business/measurement/index.html");
        businessMeasurementMenu.setName("计量标准建标考核（复查）申请");
        businessMeasurementMenu.setDescription("计量标准建标考核（复查）申请");
        webAppMenus.add(businessMeasurementMenu);

        WebAppMenu businessMeasurementAddMenu = new WebAppMenu();
        businessMeasurementAddMenu.setRouteName("MeasurementAdd");
        businessMeasurementAddMenu.setParentRouteWebAppMenu(businessMenu);
        businessMeasurementAddMenu.setParentWebAppMenu(businessMeasurementMenu);
        businessMeasurementAddMenu.setTemplateUrl("views/business/measurement/add.html");
        businessMeasurementAddMenu.setName("计量标准建标考核（复查）申请--新增");
        businessMeasurementAddMenu.setDescription("计量标准建标考核（复查）申请--新增");
        businessMeasurementAddMenu.setShow(false);
        webAppMenus.add(businessMeasurementAddMenu);

        WebAppMenu businessTechnologyMenu = new WebAppMenu();
        businessTechnologyMenu.setRouteName("Technology");
        businessTechnologyMenu.setParentRouteWebAppMenu(businessMenu);
        businessTechnologyMenu.setParentWebAppMenu(businessMenu);
        businessTechnologyMenu.setTemplateUrl("views/business/technology/index.html");
        businessTechnologyMenu.setName("技术机构考核（复查）申请");
        businessTechnologyMenu.setDescription("技术机构考核（复查）申请");
        webAppMenus.add(businessTechnologyMenu);

        WebAppMenu businessTechnologyAddMenu = new WebAppMenu();
        businessTechnologyAddMenu.setRouteName("TechnologyAdd");
        businessTechnologyAddMenu.setParentRouteWebAppMenu(businessMenu);
        businessTechnologyAddMenu.setParentWebAppMenu(businessTechnologyMenu);
        businessTechnologyAddMenu.setTemplateUrl("views/business/technology/add.html");
        businessTechnologyAddMenu.setName("技术机构考核（复查）申请--新增");
        businessTechnologyAddMenu.setDescription("技术机构考核（复查）申请--新增");
        businessTechnologyAddMenu.setShow(false);
        webAppMenus.add(businessTechnologyAddMenu);

        WebAppMenu businessMeasureToolMenu = new WebAppMenu();
        businessMeasureToolMenu.setRouteName("MeasureTool");
        businessMeasureToolMenu.setParentRouteWebAppMenu(businessMenu);
        businessMeasureToolMenu.setParentWebAppMenu(businessMenu);
        businessMeasureToolMenu.setTemplateUrl("views/business/measuretool/index.html");
        businessMeasureToolMenu.setName("计量器具生产许可证(年检)申请");
        businessMeasureToolMenu.setDescription("计量器具生产许可证(年检)申请");
        businessMeasureToolMenu.setShow(true);
        webAppMenus.add(businessMeasureToolMenu);


        WebAppMenu businessMeasureToolAddMenu = new WebAppMenu();
        businessMeasureToolAddMenu.setRouteName("MeasureToolAdd");
        businessMeasureToolAddMenu.setParentRouteWebAppMenu(businessMenu);
        businessMeasureToolAddMenu.setParentWebAppMenu(businessMeasureToolMenu);
        businessMeasureToolAddMenu.setTemplateUrl("views/business/measuretool/add.html");
        businessMeasureToolAddMenu.setName("计量器具生产许可证(年检)申请--新增");
        businessMeasureToolAddMenu.setDescription("计量器具生产许可证(年检)申请--新增");
        businessMeasureToolAddMenu.setShow(false);
        webAppMenus.add(businessMeasureToolAddMenu);
    }

    // 人员资质
    protected void addWebAppMenusPersonnel() {
        WebAppMenu personnelMenu = new WebAppMenu();
        personnelMenu.setRouteName("personnel");
        personnelMenu.setWeight(weight++);
        personnelMenu.setName("人员资质");
        personnelMenu.setAbstract(true);
        webAppMenus.add(personnelMenu);

        WebAppMenu personnelQualificationManageMenu = new WebAppMenu();
        personnelQualificationManageMenu.setRouteName("personnelQualificationManage");
        personnelQualificationManageMenu.setParentRouteWebAppMenu(personnelMenu);
        personnelQualificationManageMenu.setParentWebAppMenu(personnelMenu);
        personnelQualificationManageMenu.setName("综合查询");
        personnelQualificationManageMenu.setWeight(weight++);
        personnelQualificationManageMenu.setDescription("人员资质管理综合查询");
        webAppMenus.add(personnelQualificationManageMenu);

        WebAppMenu personnelQualificationMenu = new WebAppMenu();
        personnelQualificationMenu.setRouteName("PersonnelFile");
        personnelQualificationMenu.setParentRouteWebAppMenu(personnelMenu);
        personnelQualificationMenu.setParentWebAppMenu(personnelMenu);
        personnelQualificationMenu.setName("档案管理");
        personnelQualificationMenu.setWeight(weight++);
        personnelQualificationMenu.setDescription("人员资质管理档案管理");
        webAppMenus.add(personnelQualificationMenu);

//        WebAppMenu personnelPersonnelFileMenu = new WebAppMenu();
//        personnelPersonnelFileMenu.setRouteName("PersonnelFile");
//        personnelPersonnelFileMenu.setParentRouteWebAppMenu(personnelMenu);
//        personnelPersonnelFileMenu.setParentWebAppMenu(personnelMenu);
//        personnelPersonnelFileMenu.setName("法定计量单位监督抽查");
//        personnelPersonnelFileMenu.setDescription("监督抽查法定计量单位监督抽查");
//        webAppMenus.add(personnelPersonnelFileMenu);

    }

    // 个人中心
    protected void addWebAppMenuPersonalCenter() {
        WebAppMenu personalCenterMenu = new WebAppMenu();
        personalCenterMenu.setName("个人中心");
        personalCenterMenu.setWeight(weight++);
        personalCenterMenu.setDescription("所有用户。个人中心");
        personalCenterMenu.setRouteName("Personal");
        personalCenterMenu.setAbstract(true);
        webAppMenus.add(personalCenterMenu);

        WebAppMenu  loginUserMessageMenu = new WebAppMenu();
        loginUserMessageMenu.setName("登录用户信息");
        loginUserMessageMenu.setWeight(weight++);
        loginUserMessageMenu.setDescription("所有用户。登录用户信息");
        loginUserMessageMenu.setRouteName("PersonalInfoManage");
        loginUserMessageMenu.setParentRouteWebAppMenu(personalCenterMenu);
        loginUserMessageMenu.setParentWebAppMenu(personalCenterMenu);
        webAppMenus.add(loginUserMessageMenu);

        WebAppMenu departmentMessageMenu = new WebAppMenu();
        departmentMessageMenu.setParentWebAppMenu(personalCenterMenu);
        departmentMessageMenu.setParentRouteWebAppMenu(personalCenterMenu);
        departmentMessageMenu.setWeight(weight++);
        departmentMessageMenu.setName("部门信息");
        departmentMessageMenu.setDescription("定制分配。管理本部门信息（比如注册人，注册地址等企业信息）");
        departmentMessageMenu.setRouteName("PersonalDepartmentManage");
        webAppMenus.add(departmentMessageMenu);
    }

    // 注册信息
    protected void addWebAppMenuRegister() {

        WebAppMenu registerMenu = new WebAppMenu();
        registerMenu.setName("注册信息");
        registerMenu.setAbstract(true);
        registerMenu.setWeight(weight++);
        registerMenu.setRouteName("register");
        webAppMenus.add(registerMenu);

        WebAppMenu registerMeasureDeviceMenu = new WebAppMenu();
        registerMeasureDeviceMenu.setRouteName("MeasureDevice");
        registerMeasureDeviceMenu.setParentRouteWebAppMenu(registerMenu);
        registerMeasureDeviceMenu.setParentWebAppMenu(registerMenu);
        registerMeasureDeviceMenu.setName("强制检定计量器具用户注册信息");
        registerMeasureDeviceMenu.setDescription("注册信息强制检定计量器具用户注册信息");
        webAppMenus.add(registerMeasureDeviceMenu);

        WebAppMenu registerMeasureDeviceAddMenu = new WebAppMenu();
        registerMeasureDeviceAddMenu.setRouteName("MeasureDeviceAdd");
        registerMeasureDeviceAddMenu.setParentRouteWebAppMenu(registerMenu);
        registerMeasureDeviceAddMenu.setParentWebAppMenu(registerMenu);
        registerMeasureDeviceAddMenu.setName("强制检定计量器具用户注册信息--新增");
        registerMeasureDeviceAddMenu.setDescription("注册信息强制检定计量器具用户注册信息--新增");
        webAppMenus.add(registerMeasureDeviceAddMenu);

        WebAppMenu registerTechnologyMenu = new WebAppMenu();
        registerTechnologyMenu.setRouteName("Technology");
        registerTechnologyMenu.setParentRouteWebAppMenu(registerMenu);
        registerTechnologyMenu.setParentWebAppMenu(registerMenu);
        registerTechnologyMenu.setName("技术机构注册信息");
        registerTechnologyMenu.setDescription("注册信息技术机构注册信息");
        webAppMenus.add(registerTechnologyMenu);

        WebAppMenu registerTechnologyAddMenu = new WebAppMenu();
        registerTechnologyAddMenu.setRouteName("TechnologyAdd");
        registerTechnologyAddMenu.setParentRouteWebAppMenu(registerMenu);
        registerTechnologyAddMenu.setParentWebAppMenu(registerMenu);
        registerTechnologyAddMenu.setName("技术机构注册信息--新增");
        registerTechnologyAddMenu.setDescription("注册信息技术机构注册信息--新增");
        registerMeasureDeviceMenu.setShow(false);
        webAppMenus.add(registerTechnologyAddMenu);

        WebAppMenu registerEnterpriseMenu = new WebAppMenu();
        registerEnterpriseMenu.setRouteName("Enterprise");
        registerEnterpriseMenu.setParentRouteWebAppMenu(registerMenu);
        registerEnterpriseMenu.setParentWebAppMenu(registerMenu);
        registerEnterpriseMenu.setName("定量包装商品生产、销售企业注册信息");
        registerEnterpriseMenu.setDescription("注册信息定量包装商品生产、销售企业注册信息");
        registerEnterpriseMenu.setShow(false);
        webAppMenus.add(registerEnterpriseMenu);

        WebAppMenu registerEnterpriseAddMenu = new WebAppMenu();
        registerEnterpriseAddMenu.setRouteName("EnterpriseAdd");
        registerEnterpriseAddMenu.setParentRouteWebAppMenu(registerMenu);
        registerEnterpriseAddMenu.setParentWebAppMenu(registerMenu);
        registerEnterpriseAddMenu.setName("定量包装商品生产、销售企业注册信息--新增");
        registerEnterpriseAddMenu.setDescription("注册信息定量包装商品生产、销售企业注册信息--新增");
        registerEnterpriseAddMenu.setShow(false);
        webAppMenus.add(registerEnterpriseAddMenu);

        WebAppMenu registerSocietyMenu = new WebAppMenu();
        registerSocietyMenu.setRouteName("Society");
        registerSocietyMenu.setParentRouteWebAppMenu(registerMenu);
        registerSocietyMenu.setParentWebAppMenu(registerMenu);
        registerSocietyMenu.setName("社会公众注册信息");
        registerSocietyMenu.setDescription("注册信息社会公众注册信息");
        webAppMenus.add(registerSocietyMenu);
    }

    // 我的工作
    protected void addWebAppMenuMyWork() {
        WebAppMenu myWorkMenu = new WebAppMenu();
        myWorkMenu.setName("我的工作");
        myWorkMenu.setAbstract(true);
        myWorkMenu.setRouteName("mywork");
        myWorkMenu.setWeight(weight++);
        myWorkMenu.setTemplateUrl("views/common/content.html");
        webAppMenus.add(myWorkMenu);

        WebAppMenu myWorkUnhandleMenu = new WebAppMenu();
        myWorkUnhandleMenu.setRouteName("Unhandle");
        myWorkUnhandleMenu.setParentRouteWebAppMenu(myWorkMenu);
        myWorkUnhandleMenu.setParentWebAppMenu(myWorkMenu);
        myWorkUnhandleMenu.setTemplateUrl("views/mywork/unhandle/index.html");
        myWorkUnhandleMenu.setName("待办工作");
        myWorkUnhandleMenu.setWeight(weight++);
        myWorkUnhandleMenu.setDescription("我的工作-待办工作");
        webAppMenus.add(myWorkUnhandleMenu);

//        WebAppMenu myWorkHoldOnMenu = new WebAppMenu();
//        myWorkHoldOnMenu.setRouteName("HoldOn");
//        myWorkHoldOnMenu.setParentRouteWebAppMenu(myWorkMenu);
//        myWorkHoldOnMenu.setParentWebAppMenu(myWorkMenu);
//        myWorkHoldOnMenu.setTemplateUrl("views/mywork/holdon/index.html");
//        myWorkHoldOnMenu.setName("搁置");
//        myWorkHoldOnMenu.setDescription("我的工作-搁置");
//        webAppMenus.add(myWorkHoldOnMenu);

        WebAppMenu myWorkHandlingMenu = new WebAppMenu();
        myWorkHandlingMenu.setRouteName("Handling");
        myWorkHandlingMenu.setParentRouteWebAppMenu(myWorkMenu);
        myWorkHandlingMenu.setParentWebAppMenu(myWorkMenu);
        myWorkHandlingMenu.setTemplateUrl("views/mywork/handling/index.html");
        myWorkHandlingMenu.setName("在办工作");
        myWorkHandlingMenu.setWeight(weight++);
        myWorkHandlingMenu.setDescription("我的工作-在办工作");
        webAppMenus.add(myWorkHandlingMenu);

        WebAppMenu myWorkHandledMenu = new WebAppMenu();
        myWorkHandledMenu.setRouteName("Handled");
        myWorkHandledMenu.setParentRouteWebAppMenu(myWorkMenu);
        myWorkHandledMenu.setParentWebAppMenu(myWorkMenu);
        myWorkHandledMenu.setTemplateUrl("views/mywork/handled/index.html");
        myWorkHandledMenu.setName("已办工作");
        myWorkHandledMenu.setWeight(weight++);
        myWorkHandledMenu.setDescription("我的工作-已办工作");
        webAppMenus.add(myWorkHandledMenu);

        WebAppMenu myWorkDoneMenu = new WebAppMenu();
        myWorkDoneMenu.setRouteName("Done");
        myWorkDoneMenu.setParentRouteWebAppMenu(myWorkMenu);
        myWorkDoneMenu.setParentWebAppMenu(myWorkMenu);
        myWorkDoneMenu.setTemplateUrl("views/mywork/done/index.html");
        myWorkDoneMenu.setName("办结工作");
        myWorkDoneMenu.setWeight(weight++);
        myWorkDoneMenu.setDescription("我的工作-办结工作");
        webAppMenus.add(myWorkDoneMenu);
    }

    // 其它功能
    protected void addWebAppMenuOther() {
        WebAppMenu othersMenu = new WebAppMenu();
        othersMenu.setName("其他功能");
        othersMenu.setAbstract(true);
        othersMenu.setRouteName("others");
        othersMenu.setWeight(weight++);
        webAppMenus.add(othersMenu);

        WebAppMenu othersIntegrityListMenu = new WebAppMenu();
        othersIntegrityListMenu.setRouteName("Integritylist");
        othersIntegrityListMenu.setParentRouteWebAppMenu(othersMenu);
        othersIntegrityListMenu.setParentWebAppMenu(othersMenu);
        othersIntegrityListMenu.setTemplateUrl("views/others/integritylist/index.html");
        othersIntegrityListMenu.setName("诚信体系红黑榜");
        othersIntegrityListMenu.setDescription("其他功能诚信体系红黑榜");
        webAppMenus.add(othersIntegrityListMenu);

        WebAppMenu othersPlatformMenu = new WebAppMenu();
        othersPlatformMenu.setRouteName("Integritylist");
        othersPlatformMenu.setParentRouteWebAppMenu(othersMenu);
        othersPlatformMenu.setParentWebAppMenu(othersMenu);
        othersPlatformMenu.setTemplateUrl("views/others/integritylist/index.html");
        othersPlatformMenu.setName("技术监督质量信息平台链接");
        othersIntegrityListMenu.setDescription("其他功能技术监督质量信息平台链接");
        webAppMenus.add(othersIntegrityListMenu);
    }

    // 计量科技园地
    protected void addWebAppMenuTechnology() {
        WebAppMenu technologyMenu = new WebAppMenu();
        technologyMenu.setName("计量科技园地");
        technologyMenu.setWeight(weight++);
        technologyMenu.setAbstract(true);
        technologyMenu.setRouteName("technology");
        webAppMenus.add(technologyMenu);

        WebAppMenu technologyDynamicMenu = new WebAppMenu();
        technologyDynamicMenu.setName("计量科技园地");
        technologyDynamicMenu.setDescription("新闻动态");
        technologyDynamicMenu.setRouteName("Dynamic");
        technologyDynamicMenu.setParentRouteWebAppMenu(technologyMenu);
        technologyDynamicMenu.setParentWebAppMenu(technologyMenu);
        webAppMenus.add(technologyDynamicMenu);

        WebAppMenu technologyNewRegulationsMenu = new WebAppMenu();
        technologyNewRegulationsMenu.setName("新法规宣讲");
        technologyNewRegulationsMenu.setDescription("新法规宣讲");
        technologyNewRegulationsMenu.setRouteName("Newregulations");
        technologyNewRegulationsMenu.setParentRouteWebAppMenu(technologyMenu);
        technologyNewRegulationsMenu.setParentWebAppMenu(technologyMenu);
        webAppMenus.add(technologyNewRegulationsMenu);

        WebAppMenu technologyLawMenu = new WebAppMenu();
        technologyLawMenu.setName("法律法规");
        technologyLawMenu.setDescription("法律法规");
        technologyLawMenu.setRouteName("Law");
        technologyLawMenu.setParentRouteWebAppMenu(technologyMenu);
        technologyLawMenu.setParentWebAppMenu(technologyMenu);
        webAppMenus.add(technologyLawMenu);

        WebAppMenu technologyInformationMenu = new WebAppMenu();
        technologyInformationMenu.setName("新闻资讯");
        technologyInformationMenu.setDescription("新闻资讯");
        technologyInformationMenu.setRouteName("Information");
        technologyInformationMenu.setParentRouteWebAppMenu(technologyMenu);
        technologyInformationMenu.setParentWebAppMenu(technologyMenu);
        technologyInformationMenu.setTemplateUrl("views/technology/information/index.html");
        webAppMenus.add(technologyInformationMenu);
    }

    // 企业行业最高计量标准管理
    protected void addWebAppMenuHighestStandard() {
        WebAppMenu higheststandardMenu = new WebAppMenu();
        higheststandardMenu.setAbstract(true);
        higheststandardMenu.setWeight(weight++);
        higheststandardMenu.setRouteName("higheststandard");
        higheststandardMenu.setTemplateUrl("views/common/content.html");
        higheststandardMenu.setName("企业行业最高计量标准管理");
        webAppMenus.add(higheststandardMenu);

        WebAppMenu higheststandardFileMenu = new WebAppMenu();
        higheststandardFileMenu.setRouteName("Reply");
        higheststandardFileMenu.setParentRouteWebAppMenu(higheststandardMenu);
        higheststandardFileMenu.setParentWebAppMenu(higheststandardMenu);
        higheststandardFileMenu.setTemplateUrl("views/higheststandard/file/index.html");
        higheststandardFileMenu.setName("企业行业最高计量标准档案");
        higheststandardFileMenu.setDescription("企业行业最高计量标准管理企业行业最高计量标准档案");
        webAppMenus.add(higheststandardFileMenu);
    }

    // 系统设置
    protected void addWebAppMenuSystem() {
        WebAppMenu systemConfigMenu = new WebAppMenu();
        systemConfigMenu.setName("系统设置");
        systemConfigMenu.setDescription("系统管理员。对系统进行统一的设置");
        systemConfigMenu.setAbstract(true);
        systemConfigMenu.setRouteName("system");
        systemConfigMenu.setWeight(weight++);
        webAppMenus.add(systemConfigMenu);

        WebAppMenu viewSpecialCharMenu = new WebAppMenu();
        viewSpecialCharMenu.setName("查看特殊字符");
        viewSpecialCharMenu.setRouteName("viewSpecialChar");
        viewSpecialCharMenu.setParentRouteWebAppMenu(systemConfigMenu);
        viewSpecialCharMenu.setParentWebAppMenu(systemConfigMenu);
        viewSpecialCharMenu.setWeight(weight++);
        webAppMenus.add(viewSpecialCharMenu);

        WebAppMenu userMenu = new WebAppMenu();
        userMenu.setName("用户管理");
        userMenu.setRouteName("Userfile");
        userMenu.setParentRouteWebAppMenu(systemConfigMenu);
        userMenu.setParentWebAppMenu(systemConfigMenu);
        userMenu.setWeight(weight++);
        userMenu.setShow(false);

        webAppMenus.add(userMenu);

        WebAppMenu userAddMenu = new WebAppMenu();
        userAddMenu.setName("用户管理 -- 增加");
        userAddMenu.setRouteName("UserfileAdd");
        userAddMenu.setShow(false);
        userAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        userAddMenu.setParentWebAppMenu(userMenu);
        webAppMenus.add(userAddMenu);

        WebAppMenu userEditMenu = new WebAppMenu();
        userEditMenu.setName("用户管理 -- 编辑");
        userEditMenu.setRouteName("UserfileEdit");
        userAddMenu.setShow(false);
        userAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        userAddMenu.setParentWebAppMenu(userMenu);
        webAppMenus.add(userAddMenu);

        WebAppMenu userDetailMenu = new WebAppMenu();
        userDetailMenu.setName("用户管理 -- 查看详情");
        userDetailMenu.setRouteName("UserfileDetail");
        userDetailMenu.setShow(false);
        userDetailMenu.setParentRouteWebAppMenu(systemConfigMenu);
        userDetailMenu.setParentWebAppMenu(systemConfigMenu);
        webAppMenus.add(userDetailMenu);

        WebAppMenu roleMenu = new WebAppMenu();
        roleMenu.setName("角色管理");
        roleMenu.setRouteName("role");
        roleMenu.setWeight(weight++);
        roleMenu.setParentWebAppMenu(systemConfigMenu);
        roleMenu.setParentRouteWebAppMenu(systemConfigMenu);
        webAppMenus.add(roleMenu);

        WebAppMenu roleAddMenu = new WebAppMenu();
        roleAddMenu.setName("角色管理 -- 新增");
        roleAddMenu.setRouteName("RolefileAdd");
        roleAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        roleAddMenu.setParentWebAppMenu(roleMenu);
        roleAddMenu.setController("RoleRolefileAddCtrl");
        roleAddMenu.setShow(false);
        webAppMenus.add(roleAddMenu);

        WebAppMenu roleEditMenu = new WebAppMenu();
        roleEditMenu.setName("角色管理 -- 编辑");
        roleEditMenu.setRouteName("RolefileEdit");
        roleEditMenu.setParentRouteWebAppMenu(systemConfigMenu);
        roleEditMenu.setParentWebAppMenu(roleMenu);
        roleEditMenu.setShow(false);
        webAppMenus.add(roleEditMenu);

        WebAppMenu roleDeleteMenu = new WebAppMenu();
        roleDeleteMenu.setName("角色管理 -- 删除");
        roleDeleteMenu.setRouteName("RolefileDelete");
        roleDeleteMenu.setParentRouteWebAppMenu(systemConfigMenu);
        roleDeleteMenu.setParentWebAppMenu(roleMenu);
        roleDeleteMenu.setShow(false);
        webAppMenus.add(roleDeleteMenu);

        WebAppMenu roleDetailMenu = new WebAppMenu();
        roleDetailMenu.setName("角色管理 -- 详情");
        roleDetailMenu.setRouteName("RolefileDetail");
        roleDetailMenu.setParentRouteWebAppMenu(systemConfigMenu);
        roleDetailMenu.setParentWebAppMenu(roleMenu);
        roleDetailMenu.setShow(false);
        webAppMenus.add(roleDetailMenu);

        WebAppMenu systemMenuMenu = new WebAppMenu();
        systemMenuMenu.setName("菜单管理");
        systemMenuMenu.setDescription("计量系统菜单管理");
        systemMenuMenu.setRouteName("Menu");
        systemMenuMenu.setWeight(weight++);
        systemMenuMenu.setParentRouteWebAppMenu(systemConfigMenu);
        systemMenuMenu.setParentWebAppMenu(systemConfigMenu);
        webAppMenus.add(systemMenuMenu);

        WebAppMenu systemMenuAddMenu = new WebAppMenu();
        systemMenuAddMenu.setName("菜单管理-新增菜单");
        systemMenuAddMenu.setDescription("计量系统菜单管理");
        systemMenuAddMenu.setRouteName("MenuAdd");
        systemMenuAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        systemMenuAddMenu.setParentWebAppMenu(systemMenuMenu);
        systemMenuAddMenu.setShow(false);
        webAppMenus.add(systemMenuAddMenu);

        logger.info("增加强检器具类别路由");
        WebAppMenu instrumentTypeMenu = new WebAppMenu();
        instrumentTypeMenu.setName("强检器具类别管理");
        instrumentTypeMenu.setDescription("强检器具类别管理，每种器具都应该是器具类别的一种");
        instrumentTypeMenu.setRouteName("instrumentType");
        instrumentTypeMenu.setWeight(weight++);
        instrumentTypeMenu.setParentWebAppMenu(systemConfigMenu);
        instrumentTypeMenu.setParentRouteWebAppMenu(systemConfigMenu);
        webAppMenus.add(instrumentTypeMenu);

        WebAppMenu instrumentTypeAddMenu = new WebAppMenu();
        instrumentTypeAddMenu.setName("强检器具类别管理 -- 新增");
        instrumentTypeAddMenu.setRouteName("instrumentTypeAdd");
        instrumentTypeAddMenu.setShow(false);
        instrumentTypeAddMenu.setWeight(weight++);
        instrumentTypeAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
        instrumentTypeAddMenu.setParentWebAppMenu(instrumentTypeMenu);
        webAppMenus.add(instrumentTypeAddMenu);

        WebAppMenu instrumentTypeEditMenu = new WebAppMenu();
        instrumentTypeEditMenu.setName("强检器具类别管理 -- 编辑");
        instrumentTypeEditMenu.setRouteName("instrumentTypeEdit");
        instrumentTypeEditMenu.setShow(false);
        instrumentTypeEditMenu.setParentRouteWebAppMenu(systemConfigMenu);
        instrumentTypeEditMenu.setParentWebAppMenu(instrumentTypeMenu);
        webAppMenus.add(instrumentTypeEditMenu);

        WebAppMenu applyTypeMenu = new WebAppMenu();
        applyTypeMenu.setName("申请类型管理");
        applyTypeMenu.setDescription("申请类型管理，每种申请对应一个申请类别");
        applyTypeMenu.setRouteName("applyType");
        applyTypeMenu.setWeight(weight++);
        applyTypeMenu.setParentWebAppMenu(systemConfigMenu);
        applyTypeMenu.setParentRouteWebAppMenu(systemConfigMenu);
        webAppMenus.add(applyTypeMenu);

        WebAppMenu systemAuthorityMenu = new WebAppMenu();
        systemAuthorityMenu.setName("系统设置-权限设置");
        systemAuthorityMenu.setDescription("计量系统权限设置");
        systemAuthorityMenu.setRouteName("Authority");
        systemAuthorityMenu.setWeight(weight++);
        systemAuthorityMenu.setParentRouteWebAppMenu(systemConfigMenu);
        systemAuthorityMenu.setParentWebAppMenu(systemMenuMenu);
        webAppMenus.add(systemAuthorityMenu);









//        WebAppMenu qualifierCertificateTypeMenu = new WebAppMenu();
//        qualifierCertificateTypeMenu.setName("资格证类别管理");
//        qualifierCertificateTypeMenu.setDescription("每个资格证类别对应检定的学科类别");
//        qualifierCertificateTypeMenu.setRouteName("qualifierCertificateType");
//        qualifierCertificateTypeMenu.setWeight(weight++);
//        qualifierCertificateTypeMenu.setParentWebAppMenu(systemConfigMenu);
//        qualifierCertificateTypeMenu.setParentRouteWebAppMenu(systemConfigMenu);
//        webAppMenus.add(qualifierCertificateTypeMenu);

//
//        WebAppMenu workflowtypeWorkFlowTypeManageMenu = new WebAppMenu();
//        workflowtypeWorkFlowTypeManageMenu.setRouteName("WorkFlowTypeManage");
//        workflowtypeWorkFlowTypeManageMenu.setParentRouteWebAppMenu(systemConfigMenu);
//        workflowtypeWorkFlowTypeManageMenu.setParentWebAppMenu(systemConfigMenu);
//        workflowtypeWorkFlowTypeManageMenu.setName("工作流管理");
//        workflowtypeWorkFlowTypeManageMenu.setDescription("计量系统工作流管理");
//        workflowtypeWorkFlowTypeManageMenu.setWeight(weight++);
//        webAppMenus.add(workflowtypeWorkFlowTypeManageMenu);

//        WebAppMenu workflowtypeWorkFlowTypeManageAddMenu = new WebAppMenu();
//        workflowtypeWorkFlowTypeManageAddMenu.setRouteName("WorkFlowTypeManageAdd");
//        workflowtypeWorkFlowTypeManageAddMenu.setParentRouteWebAppMenu(systemConfigMenu);
//        workflowtypeWorkFlowTypeManageAddMenu.setParentWebAppMenu(workflowtypeWorkFlowTypeManageMenu);
//        workflowtypeWorkFlowTypeManageAddMenu.setName("工作流管理-新增页面");
//        workflowtypeWorkFlowTypeManageAddMenu.setDescription("计量系统工作流管理");
//        workflowtypeWorkFlowTypeManageAddMenu.setShow(false);
//        webAppMenus.add(workflowtypeWorkFlowTypeManageAddMenu);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    // 我的消息
    protected void addWebAppMenuMyMessage() {
        WebAppMenu myWorkMenu = new WebAppMenu();
        myWorkMenu.setName("我的消息");
        myWorkMenu.setAbstract(true);
        myWorkMenu.setRouteName("myMessage");
        myWorkMenu.setWeight(weight++);
        myWorkMenu.setTemplateUrl("views/common/content.html");
        webAppMenus.add(myWorkMenu);

        logger.info("收件箱");
        WebAppMenu myMessageInboxMenu = new WebAppMenu();
        myMessageInboxMenu.setRouteName("inbox");
        myMessageInboxMenu.setParentRouteWebAppMenu(myWorkMenu);
        myMessageInboxMenu.setParentWebAppMenu(myWorkMenu);
        myMessageInboxMenu.setTemplateUrl("views/myMessage/inbox/index.html");
        myMessageInboxMenu.setName("收件箱");
        myMessageInboxMenu.setDescription("我的消息-收件箱");
        webAppMenus.add(myMessageInboxMenu);

        logger.info("已发送");
        WebAppMenu myMessageSentMenu = new WebAppMenu();
        myMessageSentMenu.setRouteName("sent");
        myMessageSentMenu.setParentRouteWebAppMenu(myWorkMenu);
        myMessageSentMenu.setParentWebAppMenu(myWorkMenu);
        myMessageSentMenu.setTemplateUrl("views/myMessage/sent/index.html");
        myMessageSentMenu.setName("已发送");
        myMessageSentMenu.setDescription("我的消息-已发送");
        webAppMenus.add(myMessageSentMenu);

        logger.info("未读消息");
        WebAppMenu myMessageUnReadMessageMenu = new WebAppMenu();
        myMessageUnReadMessageMenu.setRouteName("unReadMessage");
        myMessageUnReadMessageMenu.setParentRouteWebAppMenu(myWorkMenu);
        myMessageUnReadMessageMenu.setParentWebAppMenu(myWorkMenu);
        myMessageUnReadMessageMenu.setTemplateUrl("views/myMessage/unReadMessage/index.html");
        myMessageUnReadMessageMenu.setName("未读消息");
        myMessageUnReadMessageMenu.setDescription("我的消息-未读消息");
        webAppMenus.add(myMessageUnReadMessageMenu);
    }

    /**
     * 以下方法为2018年3月8日所建立的新的目录菜单
     */

    /**
     * 以下方法为强检器具监督管理
     */
    // 强检器具统计管理
    protected void addWebAppMenuForceInstrumentStatistics() {
        logger.info("强检器具统计管理");
        WebAppMenu forceInstrumentStatisticsMenu = new WebAppMenu();
        forceInstrumentStatisticsMenu.setName("强检器具统计管理");
        forceInstrumentStatisticsMenu.setRouteName("forceInstrumentStatistics");
        forceInstrumentStatisticsMenu.setAbstract(true);
        forceInstrumentStatisticsMenu.setWeight(weight++);
        webAppMenus.add(forceInstrumentStatisticsMenu);

        logger.info("强检器具受检率统计");
        WebAppMenu forceInstrumentCheckRate = new WebAppMenu();
        forceInstrumentCheckRate.setName("强检器具受检率统计");
        forceInstrumentCheckRate.setDescription("强检器具统计管理-强检器具受检率统计");
        forceInstrumentCheckRate.setRouteName("checkRate");
        forceInstrumentCheckRate.setParentWebAppMenu(forceInstrumentStatisticsMenu);
        forceInstrumentCheckRate.setParentRouteWebAppMenu(forceInstrumentStatisticsMenu);
        forceInstrumentCheckRate.setTemplateUrl("views/forceInstrumentStatistics/checkRate/index.html");
        webAppMenus.add(forceInstrumentCheckRate);

        logger.info("一次检定合格率统计");
        WebAppMenu onceCheckQualifiedRate = new WebAppMenu();
        onceCheckQualifiedRate.setName("一次检定合格率统计");
        onceCheckQualifiedRate.setDescription("强检器具统计管理-一次检定合格率统计");
        onceCheckQualifiedRate.setRouteName("qualifiedRate");
        onceCheckQualifiedRate.setParentWebAppMenu(forceInstrumentStatisticsMenu);
        onceCheckQualifiedRate.setParentRouteWebAppMenu(forceInstrumentStatisticsMenu);
        onceCheckQualifiedRate.setTemplateUrl("views/forceInstrumentStatistics/qualifiedRate/index.html");
        webAppMenus.add(onceCheckQualifiedRate);

        logger.info("检定能力统计");
        WebAppMenu checkAbility = new WebAppMenu();
        checkAbility.setName("检定能力统计");
        checkAbility.setDescription("强检器具统计管理-检定能力统计");
        checkAbility.setRouteName("checkAbility");
        checkAbility.setParentWebAppMenu(forceInstrumentStatisticsMenu);
        checkAbility.setParentRouteWebAppMenu(forceInstrumentStatisticsMenu);
        checkAbility.setTemplateUrl("views/forceInstrumentStatistics/checkAbility/index.html");
        webAppMenus.add(checkAbility);

        logger.info("检定能力覆盖率统计");
        WebAppMenu checkAbilityCoverageRate = new WebAppMenu();
        checkAbilityCoverageRate.setName("检定能力覆盖率统计");
        checkAbilityCoverageRate.setDescription("强检器具统计管理-检定能力覆盖率统计");
        checkAbilityCoverageRate.setRouteName("coverageRate");
        checkAbilityCoverageRate.setParentWebAppMenu(forceInstrumentStatisticsMenu);
        checkAbilityCoverageRate.setParentRouteWebAppMenu(forceInstrumentStatisticsMenu);
        checkAbilityCoverageRate.setTemplateUrl("views/forceInstrumentStatistics/coverageRate/index.html");
        webAppMenus.add(checkAbilityCoverageRate);

        logger.info("违规出证率统计");
        WebAppMenu violationRate = new WebAppMenu();
        violationRate.setName("违规出证率统计");
        violationRate.setDescription("强检器具统计管理-违规出证率统计");
        violationRate.setRouteName("violationRate");
        violationRate.setParentWebAppMenu(forceInstrumentStatisticsMenu);
        violationRate.setParentRouteWebAppMenu(forceInstrumentStatisticsMenu);
        violationRate.setTemplateUrl("views/forceInstrumentStatistics/violationRate/index.html");
        webAppMenus.add(violationRate);

        logger.info("出证及时率统计");
        WebAppMenu evidenceTimelyRate = new WebAppMenu();
        evidenceTimelyRate.setName("出证及时率统计");
        evidenceTimelyRate.setDescription("强检器具统计管理-出证及时率统计");
        evidenceTimelyRate.setRouteName("timelyRate");
        evidenceTimelyRate.setParentWebAppMenu(forceInstrumentStatisticsMenu);
        evidenceTimelyRate.setParentRouteWebAppMenu(forceInstrumentStatisticsMenu);
        evidenceTimelyRate.setTemplateUrl("views/forceInstrumentStatistics/timelyRate/index.html");
        webAppMenus.add(evidenceTimelyRate);
    }

    // 报警管理
    protected void addWebAppMenuAlarmManagement() {
        logger.info("报警管理");
        WebAppMenu alarmManagement = new WebAppMenu();
        alarmManagement.setName("报警管理");
        alarmManagement.setRouteName("alarmManagement");
        alarmManagement.setAbstract(true);
        alarmManagement.setWeight(weight++);
        webAppMenus.add(alarmManagement);

        logger.info("证书超期查询");
        WebAppMenu certificateOverdueQuery = new WebAppMenu();
        certificateOverdueQuery.setName("证书超期查询");
        certificateOverdueQuery.setDescription("报警管理-证书超期查询");
        certificateOverdueQuery.setRouteName("certificateOverdue");
        certificateOverdueQuery.setParentWebAppMenu(alarmManagement);
        certificateOverdueQuery.setParentRouteWebAppMenu(alarmManagement);
        certificateOverdueQuery.setTemplateUrl("views/alarmManagement/certificateOverdue/index.html");
        webAppMenus.add(certificateOverdueQuery);

        logger.info("超期出证查询");
        WebAppMenu evidenceOverdueQuery = new WebAppMenu();
        evidenceOverdueQuery.setName("超期出证查询");
        evidenceOverdueQuery.setDescription("报警管理-超期出证查询");
        evidenceOverdueQuery.setRouteName("evidenceOverdue");
        evidenceOverdueQuery.setParentWebAppMenu(alarmManagement);
        evidenceOverdueQuery.setParentRouteWebAppMenu(alarmManagement);
        evidenceOverdueQuery.setTemplateUrl("views/alarmManagement/evidenceOverdue/index.html");
        webAppMenus.add(evidenceOverdueQuery);

        logger.info("超范围出证查询");
        WebAppMenu evidenceOverRangeQuery = new WebAppMenu();
        evidenceOverRangeQuery.setName("超范围出证查询");
        evidenceOverRangeQuery.setDescription("报警管理-超范围出证查询");
        evidenceOverRangeQuery.setRouteName("evidenceOverRange");
        evidenceOverRangeQuery.setParentWebAppMenu(alarmManagement);
        evidenceOverRangeQuery.setParentRouteWebAppMenu(alarmManagement);
        evidenceOverRangeQuery.setTemplateUrl("views/alarmManagement/evidenceOverRange/index.html");
        webAppMenus.add(evidenceOverRangeQuery);
    }

    /**
     * 以下方法为非强检器具检定申请管理
     */
    // 非强检器具检定申请管理
    protected void addWebAppMenuNonForceInstrumentCheckApplyManagement() {
        logger.info("非强检器具检定申请管理");
        WebAppMenu nonForceInstrumentCheckApplyManagement = new WebAppMenu();
        nonForceInstrumentCheckApplyManagement.setName("非强检器具检定申请管理");
        nonForceInstrumentCheckApplyManagement.setRouteName("nonForceInstrumentCheckApplyManagement");
        nonForceInstrumentCheckApplyManagement.setAbstract(true);
        nonForceInstrumentCheckApplyManagement.setWeight(weight++);
        webAppMenus.add(nonForceInstrumentCheckApplyManagement);

        logger.info("检定申请");
        WebAppMenu nonForceInstrumentCheckApply = new WebAppMenu();
        nonForceInstrumentCheckApply.setName("检定申请");
        nonForceInstrumentCheckApply.setDescription("非强检器具检定申请管理-检定申请");
        nonForceInstrumentCheckApply.setRouteName("checkApply");
        nonForceInstrumentCheckApply.setParentWebAppMenu(nonForceInstrumentCheckApplyManagement);
        nonForceInstrumentCheckApply.setParentRouteWebAppMenu(nonForceInstrumentCheckApplyManagement);
        nonForceInstrumentCheckApply.setTemplateUrl("views/nonForceInstrumentCheckApplyManagement/checkApply/index.html");
        webAppMenus.add(nonForceInstrumentCheckApply);

        logger.info("综合查询");
        WebAppMenu nonForceInstrumentCheckApplyIntegratedQuery = new WebAppMenu();
        nonForceInstrumentCheckApplyIntegratedQuery.setName("综合查询");
        nonForceInstrumentCheckApplyIntegratedQuery.setDescription("非强检器具检定申请管理-综合查询");
        nonForceInstrumentCheckApplyIntegratedQuery.setRouteName("integratedQuery");
        nonForceInstrumentCheckApplyIntegratedQuery.setParentWebAppMenu(nonForceInstrumentCheckApplyManagement);
        nonForceInstrumentCheckApplyIntegratedQuery.setParentRouteWebAppMenu(nonForceInstrumentCheckApplyManagement);
        webAppMenus.add(nonForceInstrumentCheckApplyIntegratedQuery);
    }

    // 非强检器具检定信息综合查询
    protected void addWebAppMenuNonForceInstrumentCheckInfoQuery() {
        logger.info("非强检器具检定信息综合查询");
        WebAppMenu nonForceInstrumentCheckInfoQuery = new WebAppMenu();
        nonForceInstrumentCheckInfoQuery.setName("非强检器具检定信息综合查询");
        nonForceInstrumentCheckInfoQuery.setRouteName("nonForceInstrumentCheckInfoQuery");
        nonForceInstrumentCheckInfoQuery.setAbstract(true);
        nonForceInstrumentCheckInfoQuery.setWeight(weight++);
        webAppMenus.add(nonForceInstrumentCheckInfoQuery);

        logger.info("综合查询");
        WebAppMenu nonForceInstrumentCheckInfoIntegratedQuery = new WebAppMenu();
        nonForceInstrumentCheckInfoIntegratedQuery.setName("综合查询");
        nonForceInstrumentCheckInfoIntegratedQuery.setDescription("非强检器具检定信息综合查询-综合查询");
        nonForceInstrumentCheckInfoIntegratedQuery.setRouteName("infoIntegratedQuery");
        nonForceInstrumentCheckInfoIntegratedQuery.setParentWebAppMenu(nonForceInstrumentCheckInfoQuery);
        nonForceInstrumentCheckInfoIntegratedQuery.setParentRouteWebAppMenu(nonForceInstrumentCheckInfoQuery);
        webAppMenus.add(nonForceInstrumentCheckInfoIntegratedQuery);
    }

    // 非强检器具校准申请管理
    protected void addWebAppMenuNonForceInstrumentCorrectApplyManagement() {
        logger.info("非强检器具校准申请管理");
        WebAppMenu nonForceInstrumentCorrectApplyManagement = new WebAppMenu();
        nonForceInstrumentCorrectApplyManagement.setName("非强检器具校准申请管理");
        nonForceInstrumentCorrectApplyManagement.setRouteName("nonForceInstrumentCorrectApplyManagement");
        nonForceInstrumentCorrectApplyManagement.setAbstract(true);
        nonForceInstrumentCorrectApplyManagement.setWeight(weight++);
        webAppMenus.add(nonForceInstrumentCorrectApplyManagement);

        logger.info("校准申请");
        WebAppMenu correctApply = new WebAppMenu();
        correctApply.setName("校准申请");
        correctApply.setDescription("非强检器具校准申请管理-校准申请");
        correctApply.setRouteName("correctApply");
        correctApply.setParentWebAppMenu(nonForceInstrumentCorrectApplyManagement);
        correctApply.setParentRouteWebAppMenu(nonForceInstrumentCorrectApplyManagement);
        correctApply.setTemplateUrl("views/nonForceInstrumentCorrectApplyManagement/correctApply/index.html");
        webAppMenus.add(correctApply);
    }

    // 非强检器具校准信息综合查询
    protected void addWebAppMenuNonForceInstrumentCorrectInfoQuery() {
        logger.info("非强检器具校准信息综合查询");
        WebAppMenu nonForceInstrumentCorrectInfoQuery = new WebAppMenu();
        nonForceInstrumentCorrectInfoQuery.setName("非强检器具校准信息综合查询");
        nonForceInstrumentCorrectInfoQuery.setRouteName("nonForceInstrumentCorrectInfoQuery");
        nonForceInstrumentCorrectInfoQuery.setAbstract(true);
        nonForceInstrumentCorrectInfoQuery.setWeight(weight++);
        webAppMenus.add(nonForceInstrumentCorrectInfoQuery);

        logger.info("校准信息综合查询");
        WebAppMenu correctInfoQuery = new WebAppMenu();
        correctInfoQuery.setName("校准信息综合查询");
        correctInfoQuery.setDescription("非强检器具校准信息综合查询-校准信息综合查询");
        correctInfoQuery.setRouteName("correctInfoQuery");
        correctInfoQuery.setParentWebAppMenu(nonForceInstrumentCorrectInfoQuery);
        correctInfoQuery.setParentRouteWebAppMenu(nonForceInstrumentCorrectInfoQuery);
        webAppMenus.add(correctInfoQuery);
    }

    /**
     * 以下方法为定量包装管理
     */
    // 定量包装商品净含量监督管理
    protected void addWebAppMenuWareNetContentSupervision() {
        logger.info("定量包装商品净含量监督管理");
        WebAppMenu wareNetContentSupervision = new WebAppMenu();
        wareNetContentSupervision.setName("定量包装商品净含量监督管理");
        wareNetContentSupervision.setRouteName("wareNetContentSupervision");
        wareNetContentSupervision.setAbstract(true);
        wareNetContentSupervision.setWeight(weight++);
        webAppMenus.add(wareNetContentSupervision);
    }

    // 定量包装商品经营单位档案
    protected void addWebAppMenuWareBusinessFile() {
        logger.info("定量包装商品经营单位档案");
        WebAppMenu wareBusinessFile = new WebAppMenu();
        wareBusinessFile.setName("定量包装商品经营单位档案");
        wareBusinessFile.setRouteName("wareBusinessFile");
        wareBusinessFile.setAbstract(true);
        wareBusinessFile.setWeight(weight++);
        webAppMenus.add(wareBusinessFile);
    }

    // 定量包装商品生产单位档案
    protected void addWebAppMenuWareProductionFile() {
        logger.info("定量包装商品生产单位档案");
        WebAppMenu wareProductionFile = new WebAppMenu();
        wareProductionFile.setName("定量包装商品生产单位档案");
        wareProductionFile.setRouteName("wareProductionFile");
        wareProductionFile.setAbstract(true);
        wareProductionFile.setWeight(weight++);
        webAppMenus.add(wareProductionFile);
    }

    // 定量包装商品监督抽查计划
    protected void addWebAppMenuWareSupervisoryCheckPlan() {
        logger.info("定量包装商品监督抽查计划");
        WebAppMenu wareSupervisoryCheckPlan = new WebAppMenu();
        wareSupervisoryCheckPlan.setName("定量包装商品监督抽查计划");
        wareSupervisoryCheckPlan.setRouteName("wareSupervisoryCheckPlan");
        wareSupervisoryCheckPlan.setAbstract(true);
        wareSupervisoryCheckPlan.setWeight(weight++);
        webAppMenus.add(wareSupervisoryCheckPlan);
    }

    // 定量包装商品监督抽查结果
    protected void addWebAppMenuWareSupervisoryCheckResult() {
        logger.info("定量包装商品监督抽查结果");
        WebAppMenu wareSupervisoryCheckResult = new WebAppMenu();
        wareSupervisoryCheckResult.setName("定量包装商品监督抽查结果");
        wareSupervisoryCheckResult.setRouteName("wareSupervisoryCheckResult");
        wareSupervisoryCheckResult.setAbstract(true);
        wareSupervisoryCheckResult.setWeight(weight++);
        webAppMenus.add(wareSupervisoryCheckResult);
    }

    // 定量包装检验原始记录
    protected void addWebAppMenuCheckPrimaryRecord() {
        logger.info("定量包装检验原始记录");
        WebAppMenu checkPrimaryRecord = new WebAppMenu();
        checkPrimaryRecord.setName("定量包装检验原始记录");
        checkPrimaryRecord.setRouteName("checkPrimaryRecord");
        checkPrimaryRecord.setAbstract(true);
        checkPrimaryRecord.setWeight(weight++);
        webAppMenus.add(checkPrimaryRecord);
    }

    // 定量包装商品监督抽查通报
    protected void addWebAppMenuWareSupervisoryCheckBulletin() {
        logger.info("定量包装商品监督抽查通报");
        WebAppMenu wareSupervisoryCheckBulletin = new WebAppMenu();
        wareSupervisoryCheckBulletin.setName("定量包装商品监督抽查通报");
        wareSupervisoryCheckBulletin.setRouteName("wareSupervisoryCheckBulletin");
        wareSupervisoryCheckBulletin.setAbstract(true);
        wareSupervisoryCheckBulletin.setWeight(weight++);
        webAppMenus.add(wareSupervisoryCheckBulletin);
    }

    // 注册信息
    protected void addWebAppMenuQuantitativePackageRegisterInfo() {
        logger.info("注册信息");
        WebAppMenu quantitativePackageRegisterInfo = new WebAppMenu();
        quantitativePackageRegisterInfo.setName("注册信息");
        quantitativePackageRegisterInfo.setRouteName("quantitativePackageRegisterInfo");
        quantitativePackageRegisterInfo.setAbstract(true);
        quantitativePackageRegisterInfo.setWeight(weight++);
        webAppMenus.add(quantitativePackageRegisterInfo);
    }

    /**
     * 以下方法为机构考核模块
     */
    // 技术机构监督考核模块
    protected void addWebAppMenuTechnicalInstitutionAssessment() {
        logger.info("技术机构监督考核模块");
        WebAppMenu technicalInstitutionAssessment = new WebAppMenu();
        technicalInstitutionAssessment.setName("技术机构监督考核模块");
        technicalInstitutionAssessment.setRouteName("technicalInstitutionAssessment");
        technicalInstitutionAssessment.setAbstract(true);
        technicalInstitutionAssessment.setWeight(weight++);
        webAppMenus.add(technicalInstitutionAssessment);

        logger.info("考核申请");
        WebAppMenu assessmentApply = new WebAppMenu();
        assessmentApply.setName("考核申请");
        assessmentApply.setDescription("技术机构监督考核模块-考核申请");
        assessmentApply.setRouteName("assessmentApply");
        assessmentApply.setParentWebAppMenu(technicalInstitutionAssessment);
        assessmentApply.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        assessmentApply.setTemplateUrl("views/technicalInstitutionAssessment/assessmentApply/index.html");
        webAppMenus.add(assessmentApply);

        logger.info("考评员档案");
        WebAppMenu examinerFile = new WebAppMenu();
        examinerFile.setName("考评员档案");
        examinerFile.setDescription("技术机构监督考核模块-考评员档案");
        examinerFile.setRouteName("examinerFile");
        examinerFile.setParentWebAppMenu(technicalInstitutionAssessment);
        examinerFile.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        examinerFile.setTemplateUrl("views/technicalInstitutionAssessment/examinerFile/index.html");
        webAppMenus.add(examinerFile);

        logger.info("考评员初审");
        WebAppMenu examinerFirstTrial = new WebAppMenu();
        examinerFirstTrial.setName("考评员初审");
        examinerFirstTrial.setDescription("技术机构监督考核模块-考评员初审");
        examinerFirstTrial.setRouteName("examinerFirstTrial");
        examinerFirstTrial.setParentWebAppMenu(technicalInstitutionAssessment);
        examinerFirstTrial.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        examinerFirstTrial.setTemplateUrl("views/technicalInstitutionAssessment/examinerFirstTrial/index.html");
        webAppMenus.add(examinerFirstTrial);

        logger.info("现场考核计划");
        WebAppMenu fieldAssessmentPlan = new WebAppMenu();
        fieldAssessmentPlan.setName("现场考核计划");
        fieldAssessmentPlan.setDescription("技术机构监督考核模块-现场考核计划");
        fieldAssessmentPlan.setRouteName("fieldAssessmentPlan");
        fieldAssessmentPlan.setParentWebAppMenu(technicalInstitutionAssessment);
        fieldAssessmentPlan.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        fieldAssessmentPlan.setTemplateUrl("views/technicalInstitutionAssessment/fieldAssessmentPlan/index.html");
        webAppMenus.add(fieldAssessmentPlan);

        logger.info("首次会议");
        WebAppMenu firstMeeting = new WebAppMenu();
        firstMeeting.setName("首次会议");
        firstMeeting.setDescription("技术机构监督考核模块-首次会议");
        firstMeeting.setRouteName("firstMeeting");
        firstMeeting.setParentWebAppMenu(technicalInstitutionAssessment);
        firstMeeting.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        firstMeeting.setTemplateUrl("views/technicalInstitutionAssessment/firstMeeting/index.html");
        webAppMenus.add(firstMeeting);

        logger.info("现场考核记录");
        WebAppMenu fieldAssessmentRecord = new WebAppMenu();
        fieldAssessmentRecord.setName("现场考核记录");
        fieldAssessmentRecord.setDescription("技术机构监督考核模块-现场考核记录");
        fieldAssessmentRecord.setRouteName("fieldAssessmentRecord");
        fieldAssessmentRecord.setParentWebAppMenu(technicalInstitutionAssessment);
        fieldAssessmentRecord.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        fieldAssessmentRecord.setTemplateUrl("views/technicalInstitutionAssessment/fieldAssessmentRecord/index.html");
        webAppMenus.add(fieldAssessmentRecord);

        logger.info("考评报告");
        WebAppMenu assessmentReport = new WebAppMenu();
        assessmentReport.setName("考评报告");
        assessmentReport.setDescription("技术机构监督考核模块-考评报告");
        assessmentReport.setRouteName("assessmentReport");
        assessmentReport.setParentWebAppMenu(technicalInstitutionAssessment);
        assessmentReport.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        assessmentReport.setTemplateUrl("views/technicalInstitutionAssessment/assessmentReport/index.html");
        webAppMenus.add(assessmentReport);

        logger.info("能力比对");
        WebAppMenu abilityComparison = new WebAppMenu();
        abilityComparison.setName("能力比对");
        abilityComparison.setDescription("技术机构监督考核模块-能力比对");
        abilityComparison.setRouteName("abilityComparison");
        abilityComparison.setParentWebAppMenu(technicalInstitutionAssessment);
        abilityComparison.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        abilityComparison.setTemplateUrl("views/technicalInstitutionAssessment/abilityComparison/index.html");
        webAppMenus.add(abilityComparison);

        logger.info("整改措施");
        WebAppMenu correctiveMeasures = new WebAppMenu();
        correctiveMeasures.setName("整改措施");
        correctiveMeasures.setDescription("技术机构监督考核模块-整改措施");
        correctiveMeasures.setRouteName("correctiveMeasures");
        correctiveMeasures.setParentWebAppMenu(technicalInstitutionAssessment);
        correctiveMeasures.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        correctiveMeasures.setTemplateUrl("views/technicalInstitutionAssessment/correctiveMeasures/index.html");
        webAppMenus.add(correctiveMeasures);

        logger.info("整改报告");
        WebAppMenu correctiveReport = new WebAppMenu();
        correctiveReport.setName("整改报告");
        correctiveReport.setDescription("技术机构监督考核模块-整改报告");
        correctiveReport.setRouteName("correctiveReport");
        correctiveReport.setParentWebAppMenu(technicalInstitutionAssessment);
        correctiveReport.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        correctiveReport.setTemplateUrl("views/technicalInstitutionAssessment/correctiveReport/index.html");
        webAppMenus.add(correctiveReport);

        logger.info("跟踪报告");
        WebAppMenu trackingReport = new WebAppMenu();
        trackingReport.setName("跟踪报告");
        trackingReport.setDescription("技术机构监督考核模块-跟踪报告");
        trackingReport.setRouteName("trackingReport");
        trackingReport.setParentWebAppMenu(technicalInstitutionAssessment);
        trackingReport.setParentRouteWebAppMenu(technicalInstitutionAssessment);
        trackingReport.setTemplateUrl("views/technicalInstitutionAssessment/trackingReport/index.html");
        webAppMenus.add(trackingReport);
    }

    /**
     * 以下方法为计量标准考核（复查）模块
     */
    // 计量标准考核（复查）申请
    protected void addWebAppMenuMeasurementStandardAssessmentApply() {
        logger.info("计量标准考核（复查）申请");
        WebAppMenu measurementStandardAssessmentApply = new WebAppMenu();
        measurementStandardAssessmentApply.setName("计量标准考核（复查）申请");
        measurementStandardAssessmentApply.setRouteName("measurementStandardAssessmentApply");
        measurementStandardAssessmentApply.setAbstract(true);
        measurementStandardAssessmentApply.setWeight(weight++);
        webAppMenus.add(measurementStandardAssessmentApply);

        logger.info("查询功能");
        WebAppMenu queryFunction = new WebAppMenu();
        queryFunction.setName("查询功能");
        queryFunction.setDescription("计量标准考核（复查）申请-查询功能");
        queryFunction.setRouteName("queryFunction");
        queryFunction.setParentWebAppMenu(measurementStandardAssessmentApply);
        queryFunction.setParentRouteWebAppMenu(measurementStandardAssessmentApply);
        queryFunction.setTemplateUrl("views/measurementStandardAssessmentApply/queryFunction/index.html");
        webAppMenus.add(queryFunction);

        logger.info("新建功能");
        WebAppMenu newBuiltFunction = new WebAppMenu();
        newBuiltFunction.setName("新建功能");
        newBuiltFunction.setDescription("计量标准考核（复查）申请-新建功能");
        newBuiltFunction.setRouteName("newBuiltFunction");
        newBuiltFunction.setParentWebAppMenu(measurementStandardAssessmentApply);
        newBuiltFunction.setParentRouteWebAppMenu(measurementStandardAssessmentApply);
        newBuiltFunction.setTemplateUrl("views/measurementStandardAssessmentApply/newBuiltFunction/index.html");
        webAppMenus.add(newBuiltFunction);

        logger.info("上传功能");
        WebAppMenu uploadFunction = new WebAppMenu();
        uploadFunction.setName("上传功能");
        uploadFunction.setDescription("计量标准考核（复查）申请-上传功能");
        uploadFunction.setRouteName("uploadFunction");
        uploadFunction.setParentWebAppMenu(measurementStandardAssessmentApply);
        uploadFunction.setParentRouteWebAppMenu(measurementStandardAssessmentApply);
        uploadFunction.setTemplateUrl("views/measurementStandardAssessmentApply/uploadFunction/index.html");
        webAppMenus.add(uploadFunction);

        logger.info("初审");
        WebAppMenu firstTrial = new WebAppMenu();
        firstTrial.setName("初审");
        firstTrial.setDescription("计量标准考核（复查）申请-初审");
        firstTrial.setRouteName("firstTrial");
        firstTrial.setParentWebAppMenu(measurementStandardAssessmentApply);
        firstTrial.setParentRouteWebAppMenu(measurementStandardAssessmentApply);
        firstTrial.setTemplateUrl("views/measurementStandardAssessmentApply/firstTrial/index.html");
        webAppMenus.add(firstTrial);

        logger.info("组织考评单位/考评组");
        WebAppMenu organizationEvaluationUnit = new WebAppMenu();
        organizationEvaluationUnit.setName("组织考评单位/考评组");
        organizationEvaluationUnit.setDescription("计量标准考核（复查）申请-组织考评单位/考评组");
        organizationEvaluationUnit.setRouteName("organizationEvaluationUnit");
        organizationEvaluationUnit.setParentWebAppMenu(measurementStandardAssessmentApply);
        organizationEvaluationUnit.setParentRouteWebAppMenu(measurementStandardAssessmentApply);
        organizationEvaluationUnit.setTemplateUrl("views/measurementStandardAssessmentApply/organizationEvaluationUnit/index.html");
        webAppMenus.add(organizationEvaluationUnit);

        logger.info("设定考核报告");
        WebAppMenu settingAssessmentReport = new WebAppMenu();
        settingAssessmentReport.setName("设定考核报告");
        settingAssessmentReport.setDescription("计量标准考核（复查）申请-设定考核报告");
        settingAssessmentReport.setRouteName("settingAssessmentReport");
        settingAssessmentReport.setParentWebAppMenu(measurementStandardAssessmentApply);
        settingAssessmentReport.setParentRouteWebAppMenu(measurementStandardAssessmentApply);
        settingAssessmentReport.setTemplateUrl("views/measurementStandardAssessmentApply/settingAssessmentReport/index.html");
        webAppMenus.add(settingAssessmentReport);

        logger.info("审核结果评定");
        WebAppMenu auditResultsEvaluation = new WebAppMenu();
        auditResultsEvaluation.setName("审核结果评定");
        auditResultsEvaluation.setDescription("计量标准考核（复查）申请-审核结果评定");
        auditResultsEvaluation.setRouteName("auditResultsEvaluation");
        auditResultsEvaluation.setParentWebAppMenu(measurementStandardAssessmentApply);
        auditResultsEvaluation.setParentRouteWebAppMenu(measurementStandardAssessmentApply);
        auditResultsEvaluation.setTemplateUrl("views/measurementStandardAssessmentApply/auditResultsEvaluation/index.html");
        webAppMenus.add(auditResultsEvaluation);
    }

    /**
     * 以下方法为能源计量管理模块
     */
    // 能源计量器具管理
    protected void addWebAppMenuEnergyMeasurementInstrumentsManagement() {
        logger.info("能源计量器具管理");
        WebAppMenu energyMeasurementInstrumentsManagement = new WebAppMenu();
        energyMeasurementInstrumentsManagement.setName("能源计量器具管理");
        energyMeasurementInstrumentsManagement.setRouteName("energyMeasurementInstrumentsManagement");
        energyMeasurementInstrumentsManagement.setAbstract(true);
        energyMeasurementInstrumentsManagement.setWeight(weight++);
        webAppMenus.add(energyMeasurementInstrumentsManagement);
    }

    // 能源计量器具档案
    protected void addWebAppMenuEnergyMeasurementInstrumentsFile() {
        logger.info("能源计量器具档案");
        WebAppMenu energyMeasurementInstrumentsFile = new WebAppMenu();
        energyMeasurementInstrumentsFile.setName("能源计量器具档案");
        energyMeasurementInstrumentsFile.setRouteName("energyMeasurementInstrumentsFile");
        energyMeasurementInstrumentsFile.setAbstract(true);
        energyMeasurementInstrumentsFile.setWeight(weight++);
        webAppMenus.add(energyMeasurementInstrumentsFile);
    }

    // 用能系统管理
    protected void addWebAppMenuEnergyUseSystemManagement() {
        logger.info("用能系统管理");
        WebAppMenu energyUseSystemManagement = new WebAppMenu();
        energyUseSystemManagement.setName("用能系统管理");
        energyUseSystemManagement.setRouteName("energyUseSystemManagement");
        energyUseSystemManagement.setAbstract(true);
        energyUseSystemManagement.setWeight(weight++);
        webAppMenus.add(energyUseSystemManagement);
    }

    // 能源计量人员管理
    protected void addWebAppMenuEnergyMeteringPersonnelManagement() {
        logger.info("能源计量人员管理");
        WebAppMenu energyMeteringPersonnelManagement = new WebAppMenu();
        energyMeteringPersonnelManagement.setName("能源计量人员管理");
        energyMeteringPersonnelManagement.setRouteName("energyMeteringPersonnelManagement");
        energyMeteringPersonnelManagement.setAbstract(true);
        energyMeteringPersonnelManagement.setWeight(weight++);
        webAppMenus.add(energyMeteringPersonnelManagement);
    }

    // 用能管理
    protected void addWebAppMenuEnergyUseManagement() {
        logger.info("用能管理");
        WebAppMenu energyUseManagement = new WebAppMenu();
        energyUseManagement.setName("用能管理");
        energyUseManagement.setRouteName("energyUseManagement");
        energyUseManagement.setAbstract(true);
        energyUseManagement.setWeight(weight++);
        webAppMenus.add(energyUseManagement);
    }

    // 重点用能企业信息查询
    protected void addWebAppMenuKeyEnergyEnterpriseInfoQuery() {
        logger.info("重点用能企业信息查询");
        WebAppMenu keyEnergyEnterpriseInfoQuery = new WebAppMenu();
        keyEnergyEnterpriseInfoQuery.setName("重点用能企业信息查询");
        keyEnergyEnterpriseInfoQuery.setRouteName("keyEnergyEnterpriseInfoQuery");
        keyEnergyEnterpriseInfoQuery.setAbstract(true);
        keyEnergyEnterpriseInfoQuery.setWeight(weight++);
        webAppMenus.add(keyEnergyEnterpriseInfoQuery);
    }

    // 重点用能企业数据分析
    protected void addWebAppMenuKeyEnergyEnterpriseDataAnalysis() {
        logger.info("重点用能企业数据分析");
        WebAppMenu keyEnergyEnterpriseDataAnalysis = new WebAppMenu();
        keyEnergyEnterpriseDataAnalysis.setName("重点用能企业数据分析");
        keyEnergyEnterpriseDataAnalysis.setRouteName("keyEnergyEnterpriseDataAnalysis");
        keyEnergyEnterpriseDataAnalysis.setAbstract(true);
        keyEnergyEnterpriseDataAnalysis.setWeight(weight++);
        webAppMenus.add(keyEnergyEnterpriseDataAnalysis);
    }

    // 区域能耗数据分析
    protected void addWebAppMenuRegionalEnergyDataAnalysis() {
        logger.info("区域能耗数据分析");
        WebAppMenu regionalEnergyDataAnalysis = new WebAppMenu();
        regionalEnergyDataAnalysis.setName("区域能耗数据分析");
        regionalEnergyDataAnalysis.setRouteName("regionalEnergyDataAnalysis");
        regionalEnergyDataAnalysis.setAbstract(true);
        regionalEnergyDataAnalysis.setWeight(weight++);
        webAppMenus.add(regionalEnergyDataAnalysis);
    }

    /**
     * 以下方法为监督抽查模块
     */
    // 授权检定机构监督抽查
    protected void addWebAppMenuAuthorizedInspectionAgencySupervise() {
        logger.info("授权检定机构监督抽查");
        WebAppMenu authorizedInspectionAgencySupervise = new WebAppMenu();
        authorizedInspectionAgencySupervise.setName("授权检定机构监督抽查");
        authorizedInspectionAgencySupervise.setRouteName("authorizedInspectionAgencySupervise");
        authorizedInspectionAgencySupervise.setAbstract(true);
        authorizedInspectionAgencySupervise.setWeight(weight++);
        webAppMenus.add(authorizedInspectionAgencySupervise);
    }

    // 计量标准监督抽查
    protected void addWebAppMenuMeasurementStandardInspection() {
        logger.info("计量标准监督抽查");
        WebAppMenu measurementStandardInspection = new WebAppMenu();
        measurementStandardInspection.setName("计量标准监督抽查");
        measurementStandardInspection.setRouteName("measurementStandardInspection");
        measurementStandardInspection.setAbstract(true);
        measurementStandardInspection.setWeight(weight++);
        webAppMenus.add(measurementStandardInspection);
    }

    // 计量器具制造企业监督抽查
    protected void addWebAppMenuMeasurementInstrumentsManufacturingInspection() {
        logger.info("计量器具制造企业监督抽查");
        WebAppMenu measurementInstrumentsManufacturingInspection = new WebAppMenu();
        measurementInstrumentsManufacturingInspection.setName("计量器具制造企业监督抽查");
        measurementInstrumentsManufacturingInspection.setRouteName("measurementInstrumentsManufacturingInspection");
        measurementInstrumentsManufacturingInspection.setAbstract(true);
        measurementInstrumentsManufacturingInspection.setWeight(weight++);
        webAppMenus.add(measurementInstrumentsManufacturingInspection);
    }

    // 定量包装商品净含量监督抽查
    protected void addWebAppMenuWareNetContentInspection() {
        logger.info("定量包装商品净含量监督抽查");
        WebAppMenu wareNetContentInspection = new WebAppMenu();
        wareNetContentInspection.setName("定量包装商品净含量监督抽查");
        wareNetContentInspection.setRouteName("wareNetContentInspection");
        wareNetContentInspection.setAbstract(true);
        wareNetContentInspection.setWeight(weight++);
        webAppMenus.add(wareNetContentInspection);
    }

    // 重点计量器具监督抽查
    protected void addWebAppMenuKeyMeasurementInstrumentsInspection() {
        logger.info("重点计量器具监督抽查");
        WebAppMenu keyMeasurementInstrumentsInspection = new WebAppMenu();
        keyMeasurementInstrumentsInspection.setName("重点计量器具监督抽查");
        keyMeasurementInstrumentsInspection.setRouteName("keyMeasurementInstrumentsInspection");
        keyMeasurementInstrumentsInspection.setAbstract(true);
        keyMeasurementInstrumentsInspection.setWeight(weight++);
        webAppMenus.add(keyMeasurementInstrumentsInspection);
    }

    // 重点耗能企业计量器具监督抽查
    protected void addWebAppMenuKeyEnergyEnterpriseInstrumentsInspection() {
        logger.info("重点耗能企业计量器具监督抽查");
        WebAppMenu keyEnergyEnterpriseInstrumentsInspection = new WebAppMenu();
        keyEnergyEnterpriseInstrumentsInspection.setName("重点耗能企业计量器具监督抽查");
        keyEnergyEnterpriseInstrumentsInspection.setRouteName("keyEnergyEnterpriseInstrumentsInspection");
        keyEnergyEnterpriseInstrumentsInspection.setAbstract(true);
        keyEnergyEnterpriseInstrumentsInspection.setWeight(weight++);
        webAppMenus.add(keyEnergyEnterpriseInstrumentsInspection);
    }

    // 能源标识使用单位监督抽查
    protected void addWebAppMenuEnergyLabelUseEnterpriseInspection() {
        logger.info("能源标识使用单位监督抽查");
        WebAppMenu energyLabelUseEnterpriseInspection = new WebAppMenu();
        energyLabelUseEnterpriseInspection.setName("能源标识使用单位监督抽查");
        energyLabelUseEnterpriseInspection.setRouteName("energyLabelUseEnterpriseInspection");
        energyLabelUseEnterpriseInspection.setAbstract(true);
        energyLabelUseEnterpriseInspection.setWeight(weight++);
        webAppMenus.add(energyLabelUseEnterpriseInspection);
    }

    // 法定计量单位监督抽查
    protected void addWebAppMenuLegalMeasurementEnterpriseInspection() {
        logger.info("法定计量单位监督抽查");
        WebAppMenu legalMeasurementEnterpriseInspection = new WebAppMenu();
        legalMeasurementEnterpriseInspection.setName("法定计量单位监督抽查");
        legalMeasurementEnterpriseInspection.setRouteName("legalMeasurementEnterpriseInspection");
        legalMeasurementEnterpriseInspection.setAbstract(true);
        legalMeasurementEnterpriseInspection.setWeight(weight++);
        webAppMenus.add(legalMeasurementEnterpriseInspection);
    }
}
