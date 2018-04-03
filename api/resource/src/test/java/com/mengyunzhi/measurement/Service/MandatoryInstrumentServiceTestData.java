package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.mengyunzhi.measurement.entity.InstrumentEmploymentInfo.STATUS_NORMAL;

/**
 * Created by panjie on 17/8/1.
 */
@Component
public class MandatoryInstrumentServiceTestData {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentServiceTestData.class.getName());
    @Autowired
    DeviceSetService deviceSetService;                       // 标准装置
    @Autowired
    DeviceSetRepository deviceSetRepository;                 // 标准装置
    @Autowired
    DeviceInstrumentRepository deviceInstrumentRepository;       // 授权检定范围
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;         // 工作流结点
    @Autowired
    protected DepartmentRepository departmentRepository; // 部门
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强制检定申请
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;   // 强制检定
    @Autowired @Qualifier("DepartmentService") DepartmentService departmentService;
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;                // 强制检定器具
    @Autowired InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;   // 器具一级类别
    @Autowired InstrumentTypeRepository instrumentTypeRepository;   // 器具二级类别
    @Autowired DisciplineRepository disciplineRepository;   // 学科类别
    @Autowired UserService userService;                     // 用户
    @Autowired
    MeasureScaleService measureScaleService;                 // 测试范围
    @Autowired
    AccuracyService accuracyService;                         // 精确度
    @Autowired
    private AccuracyRepository accuracyRepository;
    @Autowired
    DistrictService districtService;
    @Autowired
    DistrictTypeRepository districtTypeRepository;
    @Autowired
    DistrictRepository districtRepository;       // 区域
    @Autowired
    DepartmentTypeRepository departmentTypeRepository;      // 部门类型
    @Autowired
    MeasureScaleRepository measureScaleRepository;          // 测量范围


    /**
     * 计算某个技术机构（部门）对器具（s）的检定能力
     * @param department    技术机构（部门）
     * @param jsonArray 回传的jsonArray数组
     * @param mandatoryInstruments 强检器具(s)
     */
    public void computerCheckAbilityByDepartmentIdOfMandatoryInstruments(
            Department department,
            JSONArray jsonArray,
            Set<MandatoryInstrument> mandatoryInstruments) {
        logger.info("新建精确度");
        Accuracy accuracy = AccuracyService.getOneAccuracy();
        accuracy.setWeight(10);
        accuracyRepository.save(accuracy);

        Accuracy accuracy1 = AccuracyService.getOneAccuracy();
        accuracy1.setWeight(20);
        accuracyRepository.save(accuracy1);

        Accuracy accuracy2 = AccuracyService.getOneAccuracy();
        accuracy2.setWeight(0);
        accuracyRepository.save(accuracy2);

        logger.info("新建最小测量范围");
        MeasureScale minMeasureScale = measureScaleService.getOneSavedMeasureScale();

        logger.info("新建最大测量范围");
        MeasureScale maxMeasureScale = measureScaleService.getOneSavedMeasureScale();

        logger.info("新建标准装置");
        DeviceSet deviceSet = DeviceSetService.getOneDeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);

        logger.info("实例化器具（二级）类别");
        InstrumentType instrumentType = InstrumentTypeService.getOneInstrumentType();
        instrumentType.addAccuracy(accuracy);
        instrumentType.addAccuracy(accuracy1);
        instrumentType.addAccuracy(accuracy2);
        instrumentType.addMeasureScale(minMeasureScale);
        instrumentType.addMeasureScale(maxMeasureScale);
        instrumentTypeRepository.save(instrumentType);

        logger.info("新建授权检定项目");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMinMeasureScale(minMeasureScale);
        deviceInstrument.setMaxMeasureScale(maxMeasureScale);
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrument.setInstrumentType(instrumentType);
        deviceInstrumentRepository.save(deviceInstrument);

        for (int i = 0; i < 11; i++) {
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneSavedMandatoryInstrument();
            mandatoryInstrument.setInstrumentType(instrumentType);
            if (i % 2 == 0) {
                logger.info("当为偶数个时，设置精确度(可被检定)");
                if (i % 4 == 0) {
                    logger.info("相同时可被检定");
                    mandatoryInstrument.setAccuracy(accuracy);
                } else {
                    logger.info("位于队列前方时，可被检定");
                    mandatoryInstrument.setAccuracy(accuracy2);
                }
            } else {
                logger.info("当为奇数时，设置另一个精确度（位于队列后方，不可被检定）");
                mandatoryInstrument.setAccuracy(accuracy1);
            }

            mandatoryInstrument.setMinMeasureScale(minMeasureScale);
            mandatoryInstrument.setMaxMeasureScale(maxMeasureScale);
            mandatoryInstrumentRepository.save(mandatoryInstrument);
            mandatoryInstruments.add(mandatoryInstrument);
            logger.info("设置jsonArray");
            mandatoryInstrument.setCreateTime(null);
            JSONObject jsonObject = JSONObject.fromObject(mandatoryInstrument);
            jsonArray.add(jsonObject);
        }
    }

    public void updateCheckDepartmentByMandatoryInstrumentsAndDepartmentIdTest(Department department, MandatoryInstrumentApply mandatoryInstrumentApply) {
        logger.info("创建一个用户，申请");
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("新建强制检定器具");
        Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneSavedMandatoryInstrument();
            mandatoryInstruments.add(mandatoryInstrument);
        }
        mandatoryInstrumentApply.setMandatoryInstruments(mandatoryInstruments);
        mandatoryInstrumentRepository.save(mandatoryInstruments);
    }

    public void pageAllOfCurrentUserBySpecification(String name, MandatoryInstrument mandatoryInstrument, InstrumentType instrumentType, InstrumentFirstLevelType instrumentFirstLevelType, Discipline discipline) {

        logger.info("设置区域");
        // 传入器具名称
        mandatoryInstrument.setName(name);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        // 传入器具名称及器具类别
        instrumentTypeRepository.save(instrumentType);
        mandatoryInstrument.setInstrumentType(instrumentType);
        mandatoryInstrumentRepository.save(mandatoryInstrument);


        // 传入器具名称、及一级学科类别 name
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        instrumentTypeRepository.save(instrumentType);

        // 只传入学科类别 name
        disciplineRepository.save(discipline);
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
    }

    public MandatoryInstrument setStatusToBackById(User user) {
        logger.info("新建一个申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setDepartment(user.getDepartment());
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        logger.info("新建一个强检器具");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        return mandatoryInstrument;
    }

    /**
     * 获取了七个强检器具集合,
     * 1.一个不符合条件(超期的、状态非正常的、不属于当前用户)
     * 2.一个不具有检定能力的
     * 3.五个具有检定能力的器具
     * @param user
     * @return
     * @author chuhang
     */
    public Set<MandatoryInstrument> getHasCheckAbilityMandatoryInstruments(User user) {
        logger.debug("新建一个区县区域及父级市区域");
        District shiDistrict = new District();
        shiDistrict.setDistrictType(districtTypeRepository.findOneShiDistrictType());
        districtRepository.save(shiDistrict);

        logger.debug("设置区县的市管理部门");
        District quxianDistrict = user.getDepartment().getDistrict();
        quxianDistrict.setParentDistrict(shiDistrict);
        districtRepository.save(quxianDistrict);

        logger.debug("新建一个市级管理部门");
        Department shiManagementDepartment = departmentService.getOneSavedDepartment();
        shiManagementDepartment.setDistrict(shiDistrict);
        shiManagementDepartment.setDepartmentType(departmentTypeRepository.findOneManagementDepartment());
        departmentRepository.save(shiManagementDepartment);

        logger.debug("在区县区域及市区域上分别建立一个技术机构");
        Department quxianTechnicalInstitution = new Department();
        quxianTechnicalInstitution.setDistrict(quxianDistrict);
        quxianTechnicalInstitution.setDepartmentType(departmentTypeRepository.findOneTechnicalInstitutionDepartment());
        departmentRepository.save(quxianTechnicalInstitution);

        Department shiTechnicalInstitution = new Department();
        shiTechnicalInstitution.setDistrict(shiDistrict);
        shiTechnicalInstitution.setDepartmentType(departmentTypeRepository.findOneTechnicalInstitutionDepartment());
        departmentRepository.save(shiTechnicalInstitution);

        logger.debug("新建学科类别并持久化");
        Discipline discipline = new Discipline();
        disciplineRepository.save(discipline);

        logger.debug("新建强检一级类别并持久化");
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.debug("新建强检二级类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);

        logger.debug("新建3个精确度，并按权重排序，同时，添加到强检二级类别上");

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

        logger.debug("新建3个测量范围，并设置权重，同时，添加到强检二级类别上");
        MeasureScale measureScale0 = new MeasureScale();
        measureScale0.setWeight(weight++);
        MeasureScale measureScale1 = new MeasureScale();
        measureScale1.setWeight(weight++);
        MeasureScale measureScale2 = new MeasureScale();
        measureScale2.setWeight(weight++);
        instrumentType.addMeasureScale(measureScale0);
        instrumentType.addMeasureScale(measureScale1);
        instrumentType.addMeasureScale(measureScale2);

        logger.debug("持久化二级类别");
        instrumentTypeRepository.save(instrumentType);

        logger.debug("为区县技术机构设置添加一个标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(quxianTechnicalInstitution);
        deviceSetRepository.save(deviceSet);

        logger.debug("设置为中等检定能力");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy1);
        deviceInstrument.setMinMeasureScale(measureScale0);
        deviceInstrument.setMaxMeasureScale(measureScale1);
        deviceInstrument.setInstrumentType(instrumentType);
        deviceInstrument.setDeviceSet(deviceSet);
        deviceInstrumentRepository.save(deviceInstrument);
        deviceSet.addDeviceInstrument(deviceInstrument);
        deviceSetRepository.save(deviceSet);

        logger.debug("为市技术机构添加一个标准装置，并设置为高等级检定能力");
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

        logger.debug("器具用户添加示例器具：");
        logger.debug("全部低等");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setDepartment(user.getDepartment());
        mandatoryInstrument.setAccuracy(accuracy0);
        mandatoryInstrument.setMinMeasureScale(measureScale0);
        mandatoryInstrument.setMaxMeasureScale(measureScale1);
        mandatoryInstrument.setInstrumentType(instrumentType);
        mandatoryInstrument.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument.setStatus(STATUS_NORMAL);

        logger.debug("全部中等");
        MandatoryInstrument mandatoryInstrument1 = new MandatoryInstrument();
        mandatoryInstrument1.setDepartment(user.getDepartment());
        mandatoryInstrument1.setAccuracy(accuracy1);
        mandatoryInstrument1.setMinMeasureScale(measureScale0);
        mandatoryInstrument1.setMaxMeasureScale(measureScale1);
        mandatoryInstrument1.setInstrumentType(instrumentType);
        mandatoryInstrument1.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument1.setStatus(STATUS_NORMAL);

        logger.debug("全部高等");
        MandatoryInstrument mandatoryInstrument2 = new MandatoryInstrument();
        mandatoryInstrument2.setDepartment(user.getDepartment());
        mandatoryInstrument2.setAccuracy(accuracy2);
        mandatoryInstrument2.setMinMeasureScale(measureScale0);
        mandatoryInstrument2.setMaxMeasureScale(measureScale2);
        mandatoryInstrument2.setInstrumentType(instrumentType);
        mandatoryInstrument2.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument2.setStatus(STATUS_NORMAL);

        logger.debug("精确度低等，测量范围高等；");
        MandatoryInstrument mandatoryInstrument3 = new MandatoryInstrument();
        mandatoryInstrument3.setDepartment(user.getDepartment());
        mandatoryInstrument3.setAccuracy(accuracy0);
        mandatoryInstrument3.setMinMeasureScale(measureScale0);
        mandatoryInstrument3.setMaxMeasureScale(measureScale2);
        mandatoryInstrument3.setInstrumentType(instrumentType);
        mandatoryInstrument3.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument3.setStatus(STATUS_NORMAL);

        logger.debug("测量范围低等，精确度高等");
        MandatoryInstrument mandatoryInstrument4 = new MandatoryInstrument();
        mandatoryInstrument4.setDepartment(user.getDepartment());
        mandatoryInstrument4.setAccuracy(accuracy2);
        mandatoryInstrument4.setMinMeasureScale(measureScale0);
        mandatoryInstrument4.setMaxMeasureScale(measureScale1);
        mandatoryInstrument4.setInstrumentType(instrumentType);
        mandatoryInstrument4.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument4.setStatus(STATUS_NORMAL);

        logger.debug("新建一个状态不正常的器具");
        MandatoryInstrument mandatoryInstrument5 = mandatoryInstrumentService.getOneSavedMandatoryInstrument();

        logger.debug("新建一个不超期的、状态正常的、属于当前用户的,且没有检定能力的器具");
        Accuracy accuracy3 = new Accuracy();
        accuracy3.setWeight(weight++);
        accuracyRepository.save(accuracy3);
        MeasureScale measureScale3 = new MeasureScale();
        measureScale3.setWeight(weight++);
        measureScaleRepository.save(measureScale3);

        MandatoryInstrument mandatoryInstrument6 = new MandatoryInstrument();
        mandatoryInstrument6.setStatus(STATUS_NORMAL);
        mandatoryInstrument6.setLastCheckDepartment(user.getDepartment());
        mandatoryInstrument6.setDepartment(user.getDepartment());
        mandatoryInstrument6.setAccuracy(accuracy3);
        mandatoryInstrument6.setMinMeasureScale(measureScale3);
        mandatoryInstrument6.setMaxMeasureScale(measureScale0);
        mandatoryInstrument6.setInstrumentType(instrumentType);

        logger.debug("将强检器具添加至集合中.共有7个强检器具.其中一个不符合条件的,一个没有检定能力的,5个具有检定能力的");
        Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();
        mandatoryInstruments.add(mandatoryInstrument);
        mandatoryInstruments.add(mandatoryInstrument1);
        mandatoryInstruments.add(mandatoryInstrument2);
        mandatoryInstruments.add(mandatoryInstrument3);
        mandatoryInstruments.add(mandatoryInstrument4);
        mandatoryInstruments.add(mandatoryInstrument5);
        mandatoryInstruments.add(mandatoryInstrument6);

        mandatoryInstrumentRepository.save(mandatoryInstruments);
        return mandatoryInstruments;
    }
}
