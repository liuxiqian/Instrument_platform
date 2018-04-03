package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashSet;

/**
 * Created by panjie on 17/7/27.
 */
@Component
public class MandatoryInstrumentApplyServiceImplTestData {
    static final Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceImplTestData.class.getName());
    @Autowired
    DisciplineRepository disciplineRepository;   // 学科类别
    @Autowired
    DeviceInstrumentRepository deviceInstrumentRepository;// 授权检定范围
    @Autowired
    DeviceSetRepository deviceSetRepository; // 标准装置
    @Autowired DistrictTypeRepository districtTypeRepository;   // 区域类型
    @Autowired DistrictRepository districtRepository;       // 区域
    @Autowired DepartmentTypeRepository departmentTypeRepository;   // 部门类型
    @Autowired DepartmentRepository departmentRepository;   // 部门
    @Autowired InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository; // 一级器具类别
    @Autowired InstrumentTypeRepository instrumentTypeRepository;// 器具类别
    @Autowired MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;// 强检器具申请
    @Autowired MandatoryInstrumentRepository mandatoryInstrumentRepository; // 强检器具
    @Autowired @Qualifier("WorkService") WorkService workService;
    @Autowired MandatoryInstrumentApplyService mandatoryInstrumentApplyService; // 强检器具申请
    @Autowired
    PurposeRepository purposeRepository;
    @Autowired @org.springframework.beans.factory.annotation.Qualifier("InstrumentTypeService") InstrumentTypeService instrumentTypeService; // 器具类别
    @Autowired AccuracyService accuracyService;
    @Autowired MeasureScaleService measureScaleService;
    @Autowired
    protected UserService userService;
    @Autowired MandatoryInstrumentService mandatoryInstrumentService;  // 强检器具
    @Autowired @Qualifier("DepartmentService") DepartmentService departmentService;    // 部门

    private User currentUser;                // 当前用户
    private Department manageDepartment;    // 管理部门

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

    public MandatoryInstrumentApply getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(User user) {
        logger.info("新建一个区县区域及父级市区域");
        District shiDistrict = new District();
        shiDistrict.setDistrictType(districtTypeRepository.findOneShiDistrictType());
        districtRepository.save(shiDistrict);

        District quxianDistrict = new District();
        quxianDistrict.setParentDistrict(shiDistrict);
        quxianDistrict.setDistrictType(districtTypeRepository.findOneQuXianDistrictType());
        districtRepository.save(quxianDistrict);

        logger.info("获取一个器具用户，其区域对应区县区域");
        Department department = new Department();
        department.setDistrict(quxianDistrict);
        department.setDepartmentType(departmentTypeRepository.findOneApplianceUserDepartment());
        departmentRepository.save(department);

        logger.info("在区县区域及市区域上分别建立一个技术机构");
        Department quxianTechnicalInstitution = new Department();
        quxianTechnicalInstitution.setDistrict(quxianDistrict);
        quxianTechnicalInstitution.setDepartmentType(departmentTypeRepository.findOneTechnicalInstitutionDepartment());
        departmentRepository.save(quxianTechnicalInstitution);

        logger.info("当前登录用户送入区县管理部门");
        user.getDepartment().setDistrict(quxianDistrict);
        user.getDepartment().setDepartmentType(departmentTypeRepository.findOneManagementDepartment());
        departmentRepository.save(user.getDepartment());

        Department shiTechnicalInstitution = new Department();
        shiTechnicalInstitution.setDistrict(shiDistrict);
        shiTechnicalInstitution.setDepartmentType(departmentTypeRepository.findOneTechnicalInstitutionDepartment());
        departmentRepository.save(shiTechnicalInstitution);

        logger.info("新建学科类别并持久化");
        Discipline discipline = new Discipline();
        disciplineRepository.save(discipline);

        logger.info("新建强检一级类别并持久化");
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("新建强检二级类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);

        logger.info("新建3个精确度，并按权重排序，同时，添加到强检二级类别上");

        Integer weight = 0;
        Accuracy accuracy0 = new Accuracy();
        accuracy0.setWeight(weight++);
        Accuracy accuracy1 = new Accuracy();
        accuracy1.setWeight(weight++);
        Accuracy accuracy2 = new Accuracy();
        accuracy2.setWeight(weight++);
        instrumentType.addAccuracy(accuracy0);
        instrumentType.addAccuracy(accuracy1);
        instrumentType.addAccuracy(accuracy2);

        logger.info("新建3个测量范围，并设置权重，同时，添加到强检二级类别上");
        MeasureScale measureScale0 = new MeasureScale();
        measureScale0.setWeight(weight++);
        MeasureScale measureScale1 = new MeasureScale();
        measureScale1.setWeight(weight++);
        MeasureScale measureScale2 = new MeasureScale();
        measureScale2.setWeight(weight++);
        instrumentType.addMeasureScale(measureScale0);
        instrumentType.addMeasureScale(measureScale1);
        instrumentType.addMeasureScale(measureScale2);

        logger.info("持久化二级类别");
        instrumentTypeRepository.save(instrumentType);

        logger.info("为区县技术机构设置添加一个标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(quxianTechnicalInstitution);
        deviceSetRepository.save(deviceSet);

        logger.info("设置为中等检定能力");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy1);
        deviceInstrument.setMinMeasureScale(measureScale0);
        deviceInstrument.setMaxMeasureScale(measureScale1);
        deviceInstrument.setInstrumentType(instrumentType);
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrumentRepository.save(deviceInstrument);
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSetRepository.save(deviceSet);

        logger.info("为市技术机构添加一个标准装置，并设置为高等级检定能力");
        DeviceSet deviceSet1 = new DeviceSet();
        deviceSet1.setDepartment(shiTechnicalInstitution);
        deviceSetRepository.save(deviceSet1);

        DeviceInstrument deviceInstrument1 = new DeviceInstrument();
        deviceInstrument1.setAccuracy(accuracy2);
        deviceInstrument1.setMinMeasureScale(measureScale0);
        deviceInstrument1.setMaxMeasureScale(measureScale2);
        deviceInstrument1.setInstrumentType(instrumentType);
        deviceInstrument1.setDeviceSet(deviceSet1);
        deviceInstrumentRepository.save(deviceInstrument1);
        deviceSet1.addDeviceInstrument(deviceInstrument1);
        deviceSetRepository.save(deviceSet1);

        logger.info("器具用户新建一个申请,并设置审核部门为当前登录用户审核部门");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setDepartment(department);
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("器具用户添加示例器具：");
        logger.info("全部低等");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setDepartment(department);
        mandatoryInstrument.setAccuracy(accuracy0);
        mandatoryInstrument.setMinMeasureScale(measureScale0);
        mandatoryInstrument.setMaxMeasureScale(measureScale1);
        mandatoryInstrument.setInstrumentType(instrumentType);
        mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("全部中等");
        MandatoryInstrument mandatoryInstrument1 = new MandatoryInstrument();
        mandatoryInstrument1.setDepartment(department);
        mandatoryInstrument1.setAccuracy(accuracy1);
        mandatoryInstrument1.setMinMeasureScale(measureScale0);
        mandatoryInstrument1.setMaxMeasureScale(measureScale1);
        mandatoryInstrument1.setInstrumentType(instrumentType);
        mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument1);
        mandatoryInstrumentRepository.save(mandatoryInstrument1);

        logger.info("全部高等");
        MandatoryInstrument mandatoryInstrument2 = new MandatoryInstrument();
        mandatoryInstrument2.setDepartment(department);
        mandatoryInstrument2.setAccuracy(accuracy2);
        mandatoryInstrument2.setMinMeasureScale(measureScale0);
        mandatoryInstrument2.setMaxMeasureScale(measureScale2);
        mandatoryInstrument2.setInstrumentType(instrumentType);
        mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument2);
        mandatoryInstrumentRepository.save(mandatoryInstrument2);

        logger.info("精确度低等，测量范围高等；");
        MandatoryInstrument mandatoryInstrument3 = new MandatoryInstrument();
        mandatoryInstrument3.setDepartment(department);
        mandatoryInstrument3.setAccuracy(accuracy0);
        mandatoryInstrument3.setMinMeasureScale(measureScale0);
        mandatoryInstrument3.setMaxMeasureScale(measureScale2);
        mandatoryInstrument3.setInstrumentType(instrumentType);
        mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument3);
        mandatoryInstrumentRepository.save(mandatoryInstrument3);

        logger.info("测量范围低等，精确度高等");
        MandatoryInstrument mandatoryInstrument4 = new MandatoryInstrument();
        mandatoryInstrument4.setDepartment(department);
        mandatoryInstrument4.setAccuracy(accuracy2);
        mandatoryInstrument4.setMinMeasureScale(measureScale0);
        mandatoryInstrument4.setMaxMeasureScale(measureScale1);
        mandatoryInstrument4.setInstrumentType(instrumentType);
        mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument4);
        mandatoryInstrumentRepository.save(mandatoryInstrument4);

        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        return mandatoryInstrumentApply;
    }

    public MandatoryInstrumentApply assignToTechnicalInstitutionByMandatoryInstruments(User user) {
        return this.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(user);
    }

    public MandatoryInstrumentApply batchPassByMandatoryInstruments(User user) {
        return this.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(user);
    }

    public MandatoryInstrumentApply batchBackByMandatoryInstrumentsAndReason (User user) {
        return this.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(user);
    }

    public MandatoryInstrumentApply handleWhenApplyDoneByApplyId() {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();

        int i = 0;

        do {
            MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
            if (i%2 == 0) {
                mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NEW);
            } else {
                mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);
            }
            i++;
            mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument);
        } while (i < 10);

        mandatoryInstrumentRepository.save(mandatoryInstrumentApply.getMandatoryInstruments());
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        return mandatoryInstrumentApply;
    }

    /**
     * 生成PDF申请文档
     * @return
     */
    public MandatoryInstrumentApply generatePdfApplyById(User user) {

        logger.info("创建一个生产企业");
        Department department0 = new Department();
        department0.setName("制造单位名称");
        departmentRepository.save(department0);

        logger.info("创建两个技术机构");
        District district = new District();
        district.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
        districtRepository.save(district);
        DepartmentType departmentType = departmentTypeRepository.findOneByName("技术机构");
        Department department1 = new Department();
        department1.setName("区县技术机构一");
        department1.setDistrict(district);
        department1.setDepartmentType(departmentType);
        departmentRepository.save(department1);

        District district1 = new District();
        district1.setDistrictType(districtTypeRepository.findOneByName("市"));
        districtRepository.save(district1);
        Department department2 = new Department();
        department2.setDistrict(district1);
        department2.setName("技术机构二");
        department2.setDepartmentType(departmentType);
        departmentRepository.save(department2);

        logger.info("创建一个申请, 并设置基本信息");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setDepartment(user.getDepartment());
        mandatoryInstrumentApply.setApplyTime(Calendar.getInstance());
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("创建一个二级类别");
        InstrumentType instrumentType = instrumentTypeService.getOneSavedInstrumentType();

        logger.info("创建一个用途");
        Purpose purpose = new Purpose();
        purpose.setName("用途");
        purposeRepository.save(purpose);

        Accuracy accuracy = accuracyService.getOneSavedAccuracy();
        MeasureScale measureScale = measureScaleService.getOneSavedMeasureScale();

        logger.info("创建10个通过审核的强检器具,其中4个给一个技术机构审核，3个给第二个技术机构审核，另外3个不指定技术机构");
        for (Integer i = 0; i < 10; i++) {
            MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
            mandatoryInstrument.setDepartment(user.getDepartment());
            mandatoryInstrument.setPurpose(purpose);
            mandatoryInstrument.setInstrumentType(instrumentType);
            mandatoryInstrument.setGenerativeDepartment(department0);
            mandatoryInstrument.setAccuracy(accuracy);
            mandatoryInstrument.setMinMeasureScale(measureScale);
            mandatoryInstrument.setMaxMeasureScale(measureScale);
            mandatoryInstrument.setStatus(MandatoryInstrument.STATUS_NORMAL);

            if (i % 3 == 0) {
                mandatoryInstrument.setLastCheckDepartment(department1);
            } else if (i % 3 == 1) {
                mandatoryInstrument.setLastCheckDepartment(department2);
            }

            mandatoryInstrument.setName("器具名称" + i.toString());
            mandatoryInstrument.setSpecificationName("规格型号");
            mandatoryInstrument.setSerialNum("出厂编号");
            mandatoryInstrument.setFixSite("安装/使用地点");

            mandatoryInstrumentApply.addMandatoryInstrument(mandatoryInstrument);
        }

        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("新建对应的工作，并办结工作");
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        workService.saveWorkWithCurrentUserAudit(work);
        workService.updateToDoneByWork(work);

        return mandatoryInstrumentApply;
    }

    public MandatoryInstrumentApply generatePdfReportByApplyIdAndCheckDepartmentId(User user, HashSet<Long> checkDepartmentIds) {
        MandatoryInstrumentApply mandatoryInstrumentApply = this.generatePdfApplyById(user);

        for (MandatoryInstrument mandatoryInstrument: mandatoryInstrumentApply.getMandatoryInstruments()) {
            if (mandatoryInstrument.getLastCheckDepartment() != null && mandatoryInstrument.getStatus() == MandatoryInstrument.STATUS_NORMAL) {
                checkDepartmentIds.add(mandatoryInstrument.getLastCheckDepartment().getId());
            }
        }
        return mandatoryInstrumentApply;
    }

    public MandatoryInstrumentApply save() {
        logger.info("获取一个未保存超期检定申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        Department generateDepartment = departmentService.getOneSavedDepartment();
        String departmentName = CommonService.getRandomStringByLength(10);
        Department newGenerateDepartment = new Department();
        newGenerateDepartment.setName(departmentName);

        Integer i = 0;
        while(i < 10) {
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneUnsavedMandatoryInstrument();
            if (i % 2 == 0) {
                mandatoryInstrument.setGenerativeDepartment(generateDepartment);
            } else {
                mandatoryInstrument.setGenerativeDepartment(newGenerateDepartment);
            }

            mandatoryInstrumentApply.getMandatoryInstruments().add(mandatoryInstrument);
            i++;
        }

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

        return mandatoryInstrumentApply;
    }

}
