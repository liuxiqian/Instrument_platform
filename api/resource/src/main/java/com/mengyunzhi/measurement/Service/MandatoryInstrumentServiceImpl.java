package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.MandatoryInstrumentSpecs;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

import static com.mengyunzhi.measurement.entity.InstrumentEmploymentInfo.*;

/**
 * Created by liming on 17-7-6.
 * 实现器具使用信息的InstrumentEmploymentInfo
 */
@Service
public class MandatoryInstrumentServiceImpl implements MandatoryInstrumentService {
    private Logger logger = Logger.getLogger(MandatoryInstrumentServiceImpl.class.getName());
    @Autowired
    private InstrumentProductionRepository instrumentProductionRepository;
    @Autowired          //用户Service
    private UserService userService;
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository; // 强检器具
    @Autowired          //部门实体仓库
    private DepartmentRepository departmentRepository;
    @Autowired
    private ApplyTypeRepository applyTypeRepository;     // 申请类型
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;  // 强检审核申请信息
    @Autowired
    private WorkRepository workRepository;       // 工作
    @Autowired
    private DeviceSetService deviceSetService;  // 标准装置
    @Autowired
    private MandatoryInstrumentService mandatoryInstrumentService;
    @Autowired
    private DistrictService districtService;        //区域
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;  // 部门类型
    @Autowired
    SystemConfigService systemConfigService; // 系统设置
    @Autowired
    MandatoryInstrumentApplyService mandatoryInstrumentApplyService;
    @Autowired
    ManagementDepartmentBackedReasonService managementDepartmentBackedReasonService;        // 退回理由
    @Autowired
    ToMessageService toMessageService;              // 站内消息
    @Autowired
    ManagementDepartmentService managementDepartmentService;    // 管理部门
    @Autowired
    TechnicalInstitutionBackedReasonService technicalInstitutionBackedReasonService;      // 技术机构退回原因
    @Autowired
    MandatoryInstrumentTypeService mandatoryInstrumentTypeService;  // 强检器具类型
    @Autowired @Qualifier("WorkService") WorkService workService;   // 工作


    @Override
    public MandatoryInstrument findById(Long id) {
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        return mandatoryInstrument;
    }

    @Override
    public MandatoryInstrument saveWithInstrumentProduction(MandatoryInstrument mandatoryInstrument) {
        if (null == mandatoryInstrument.getInstrumentProduction().getManufacturerDepartment().getId()) {
            logger.info("未传入部门, 则按名称进行查询");
            Department department = departmentRepository.findTopOneByName(mandatoryInstrument.getInstrumentProduction().getManufacturerDepartment().getName());
            if (department == null) {
                logger.info("未找到相关的部门，则新建一个");
                departmentRepository.save(mandatoryInstrument.getInstrumentProduction().getManufacturerDepartment());
            } else {
                logger.info("找到器具生产信息的相关部门信息");
                mandatoryInstrument.getInstrumentProduction().setManufacturerDepartment(department);
            }
        }

        logger.info("查询是否存在该器具生产信息");
        InstrumentProduction realInstrumentProduction = instrumentProductionRepository.findByInstrumentTypeAndAccuracyAndMeasureScaleAndSpecificationAndLicenseNumAndManufacturerDepartment(
                mandatoryInstrument.getInstrumentProduction().getInstrumentType(),
                mandatoryInstrument.getInstrumentProduction().getAccuracy(),
                mandatoryInstrument.getInstrumentProduction().getMeasureScale(),
                mandatoryInstrument.getInstrumentProduction().getSpecification(),
                mandatoryInstrument.getInstrumentProduction().getLicenseNum(),
                mandatoryInstrument.getInstrumentProduction().getManufacturerDepartment()
        );

        if (null == realInstrumentProduction) {
            logger.info("不存在器具生产信息，则持久化一个");
            instrumentProductionRepository.save(mandatoryInstrument.getInstrumentProduction());
        } else {
            logger.info("找到相关的生产企业器具的种类");
            mandatoryInstrument.setInstrumentProduction(realInstrumentProduction);
        }

        //器具当前操作用户并作为外建
        User currentUser = userService.getCurrentLoginUser();
        mandatoryInstrument.setCreateUser(currentUser);
        //获取当前操作用户对应的部门，做为器具使用信息的部门的外键
        Department generativeDepartment = currentUser.getDepartment();
        mandatoryInstrument.setGenerativeDepartment(generativeDepartment);
        //保存相应的器具使用信息
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        //返回相应的实体
        return mandatoryInstrument;
    }

    @Override
    public MandatoryInstrument save(MandatoryInstrument mandatoryInstrument) {
        logger.debug("添加创建用户以及器具所属部门");
        User user = userService.getCurrentLoginUser();
        mandatoryInstrument.setCreateUser(user);
        mandatoryInstrument.setDepartment(user.getDepartment());

        logger.debug("添加器具制造单位");
        this.addGenerativeDepartmentIfNotFound(mandatoryInstrument);

        if (null == mandatoryInstrument.getNextCheckDate()) {
            logger.debug("未设置下次检定日期，将下次检定日期设定为1年后");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 1);
            mandatoryInstrument.setNextCheckDate(new Date(calendar.getTimeInMillis()));
        }

        mandatoryInstrumentRepository.save(mandatoryInstrument);
        return mandatoryInstrument;
    }

    /**
     * 如果生产企业的实体不存在，那么就添加一个
     *
     * @param mandatoryInstrument
     */
    protected void addGenerativeDepartmentIfNotFound(MandatoryInstrument mandatoryInstrument) {
        if (null != mandatoryInstrument.getGenerativeDepartment()) {
            if (null == mandatoryInstrument.getGenerativeDepartment().getId()) {
                logger.debug("未传入部门, 则按名称进行查询");
                String name = mandatoryInstrument.getGenerativeDepartment().getName();
                DepartmentType departmentType = departmentTypeRepository.findOneByPinyin("shengchanqiye");

                logger.debug("根据名称获取一个保存的");
                Department department = managementDepartmentService.getOneSavedDepartmentByNameAndDepartmentType(name, departmentType);
                mandatoryInstrument.setGenerativeDepartment(department);
            }
        }
    }

    @Override
    public MandatoryInstrument getOneSavedMandatoryInstrument() {
        return mandatoryInstrumentRepository.save(this.getOneUnsavedMandatoryInstrument());
    }

    @Autowired
    MeasureScaleService measureScaleService; // 测量范围
    @Autowired
    MeasureScaleRepository measureScaleRepository;

    @Autowired
    AccuracyService accuracyService; // 精度
    @Autowired AccuracyRepository accuracyRepository;
    @Autowired PurposeService purposeService;
    @Qualifier("DepartmentService")
    @Autowired DepartmentService departmentService;

    @Override
    public MandatoryInstrument getOneUnsavedMandatoryInstrument() {
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrument.setName("测试器具名称" + CommonService.getRandomStringByLength(10));
        mandatoryInstrument.setSerialNum("出厂编号");
        mandatoryInstrument.setFixSite("安装地点");

        logger.debug("获取一个器具类别");
        MandatoryInstrumentType mandatoryInstrumentType = mandatoryInstrumentTypeService.getOneSavedMandatoryInstrumentType();
        mandatoryInstrument.setInstrumentType(mandatoryInstrumentType);

        logger.debug("获取两个测量范围");
        MeasureScale minMeasureScale = measureScaleService.getOneSavedMeasureScale();
        minMeasureScale.setInstrumentType(mandatoryInstrumentType);
        minMeasureScale.setWeight(2);
        measureScaleRepository.save(minMeasureScale);
        mandatoryInstrument.setMinMeasureScale(minMeasureScale);

        MeasureScale maxMeasureScale = measureScaleService.getOneSavedMeasureScale();
        maxMeasureScale.setInstrumentType(mandatoryInstrumentType);
        maxMeasureScale.setWeight(20);
        measureScaleRepository.save(maxMeasureScale);
        mandatoryInstrument.setMaxMeasureScale(maxMeasureScale);

        logger.debug("获取一个精确度");
        Accuracy accuracy = accuracyService.getOneSavedAccuracy();
        accuracy.setInstrumentType(mandatoryInstrumentType);
        accuracyRepository.save(accuracy);
        mandatoryInstrument.setAccuracy(accuracy);



        logger.debug("获取一个用户");
        User user = userService.getOneSavedUser();
        mandatoryInstrument.setCreateUser(user);
        mandatoryInstrument.setDepartment(user.getDepartment());

        logger.debug("获取一个用途");
        Purpose purpose = purposeService.getOneSavedPurpose();
        mandatoryInstrument.setPurpose(purpose);

        logger.debug("获取一个生产部门");
        Department department = departmentService.getOneSavedDepartment();
        mandatoryInstrument.setGenerativeDepartment(department);

        return mandatoryInstrument;
    }

    @Override
    public List<MandatoryInstrument> computerCheckAbilityByDepartmentIdOfMandatoryInstruments(Long departmentId, Collection<MandatoryInstrument> mandatoryInstruments) throws Exception {
        List<MandatoryInstrument> mandatoryInstrumentList = (List<MandatoryInstrument>) this.findAllByMandatoryInstrumentCollection(mandatoryInstruments);
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {
            logger.info("获取是否具有检定能力");
            Boolean checkAbility = deviceSetService.getCheckAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId(
                    mandatoryInstrument.getAccuracy().getId(),
                    mandatoryInstrument.getMinMeasureScale().getId(),
                    mandatoryInstrument.getMaxMeasureScale().getId(),
                    mandatoryInstrument.getInstrumentType().getId(),
                    departmentId);
        }

        return new ArrayList<>(mandatoryInstruments);
    }

    @Override
    public void updateLastCheckDepartmentByMandatoryInstrumentsAndDepartmentId(Collection<MandatoryInstrument> mandatoryInstruments, Long departmentId) {
        Department department = departmentRepository.findOne(departmentId);
        Set<Long> ids = new HashSet<>();
        for(MandatoryInstrument mandatoryInstrument : mandatoryInstruments) {
            ids.add(mandatoryInstrument.getId());
        }
        List<MandatoryInstrument> mandatoryInstrumentList = (List<MandatoryInstrument>) mandatoryInstrumentRepository.findAll(ids);
        if (null == department) {
            throw new ObjectNotFoundException(404, "未找到相关的部门实体");
        }
        User user = userService.getCurrentLoginUser();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {
                mandatoryInstrument.setLastCheckDepartment(department);
        }
        logger.info("统一更新符合要求的强检器具");
        mandatoryInstrumentRepository.save(mandatoryInstrumentList);
    }

    @Override
    public void updateCheckCycleAndFirstCheckDate(Long id, MandatoryInstrument mandatoryInstrument) {
        logger.info("取出实体");
        MandatoryInstrument updateMandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        if (updateMandatoryInstrument == null) {
            logger.info("实体不存在");
            throw new ObjectNotFoundException(404, "未找到强检器具使用信息");
        }
        if (userService.getCurrentLoginUser().getDepartment().getId() != updateMandatoryInstrument.getLastCheckDepartment().getId()) {
            logger.info("被指定的技术机构才有更新的权限");
            throw new SecurityException("没有权限");
        }

        mandatoryInstrumentRepository.save(updateMandatoryInstrument);
        return;
    }

    @Override
    public void delete(Long id) throws ObjectNotFoundException, SecurityException {
        logger.info("取出实体");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        if (mandatoryInstrument == null) {
            logger.info("实体不存在");
            throw new ObjectNotFoundException(404, "未找到强检器具使用信息");
        }

        if (!this.canBeDeleteOrUpdate(mandatoryInstrument)) {
            throw new SecurityException("您无权删除该器具");
        }

        mandatoryInstrumentRepository.delete(mandatoryInstrument);
        return;
    }

    @Override
    public Page<MandatoryInstrument> pageAllOfCurrentUser(Pageable pageable) {
        User currentUser = userService.getCurrentLoginUser();
        String departmentTypeName = currentUser.getDepartment().getDepartmentType().getName();
//        String applianceUser = "器具用户";
//        String technicalInstitution = "技术机构";
//        String management = "管理部门";
        Page<MandatoryInstrument> mandatoryInstruments;

        switch (departmentTypeName) {
            case "技术机构":
                mandatoryInstruments = mandatoryInstrumentRepository.findAllByLastCheckDepartment(currentUser.getDepartment(), pageable);
                break;
            case "器具用户":
                mandatoryInstruments = mandatoryInstrumentRepository.findAllByDepartment(currentUser.getDepartment(), pageable);
                break;
            case "管理部门":
                mandatoryInstruments = mandatoryInstrumentService.pageAllOfManagementUser(currentUser, pageable);
                break;
            default:
                mandatoryInstruments = null;
                break;
        }

        return mandatoryInstruments;
    }

    /**
     * 多条件查询
     *
     * @param id
     * @param disciplineId               学科类别
     * @param instrumentFirstLevelTypeId 一级分类
     * @param instrumentTypeId           二级分类
     * @param audit                      是否已审核
     * @param name                       器具名称
     * @param overdue                    是否超期未检
     * @param status                     状态
     * @param pageable                   分页信息
     * @return
     * @author panjie
     */
    @Override
    public Page<MandatoryInstrument> pageAllOfCurrentUserBySpecification(Long id, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Boolean overdue, Byte status, Pageable pageable) {
        Long departmentId = userService.getCurrentLoginUser().getDepartment().getId();
        return this.pageAllOfCurrentManageDepartmentBySpecification(id, null, departmentId, null, disciplineId, instrumentFirstLevelTypeId, instrumentTypeId, audit, name, overdue, status, pageable);
    }

    /**
     * 当前登录用户超级未检的器具
     *
     * @param id                         器具ID
     * @param disciplineId               学科类别
     * @param instrumentTypeFirstLevelId 一级分类
     * @param instrumentTypeId           二级分类
     * @param name                       器具名称
     * @param pageable                   分页信息
     * @return
     * @author panjie
     */
    @Override
    public Page<MandatoryInstrument> pageOverdueDataOfCurrentUserBySpecification(Long id, Long disciplineId, Long instrumentTypeFirstLevelId, Long instrumentTypeId, String name, Pageable pageable) {
        Long departmentId = userService.getCurrentLoginUser().getDepartment().getId();
        Boolean overdue = true;
        Byte status = InstrumentEmploymentInfo.STATUS_NORMAL;
        return this.pageAllOfCurrentManageDepartmentBySpecification(id, null, departmentId, null, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, null, name, overdue, status, pageable);
    }

    /**
     * 更新
     *
     * @param id
     * @param mandatoryInstrument 强检器具
     * @author panjie
     */
    @Override
    public void update(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException {
        MandatoryInstrument oldMandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        if (oldMandatoryInstrument == null) {
            logger.info("实体不存在");
            throw new ObjectNotFoundException(404, "未找到强检器具使用信息");
        }

        if (!this.canBeDeleteOrUpdate(oldMandatoryInstrument)) {
            throw new SecurityException("器具状态非正在审核，或该器具并不属于您");
        }

        User user = userService.getCurrentLoginUser();
        logger.info("设置添加用户及所属部门");
        mandatoryInstrument.setId(id);
        oldMandatoryInstrument.setName(mandatoryInstrument.getName());  // 名称
        oldMandatoryInstrument.setInstrumentType(mandatoryInstrument.getInstrumentType());  // 器具类别
        oldMandatoryInstrument.setGenerativeDepartment(mandatoryInstrument.getGenerativeDepartment()); // 制造单位
        oldMandatoryInstrument.setSerialNum(mandatoryInstrument.getSerialNum()); // 许可证号
        oldMandatoryInstrument.setOutOfFactoryName(mandatoryInstrument.getOutOfFactoryName()); // 出厂名称
        oldMandatoryInstrument.setLicenseNum(mandatoryInstrument.getLicenseNum());  // 出厂编号
        oldMandatoryInstrument.setMinMeasureScale(mandatoryInstrument.getMinMeasureScale());  // 测量范围
        oldMandatoryInstrument.setAccuracy(mandatoryInstrument.getAccuracy());  // 精确度等级
        oldMandatoryInstrument.setSpecificationName(mandatoryInstrument.getSpecificationName()); // 规格型号名称
        oldMandatoryInstrument.setSpecification(mandatoryInstrument.getSpecification());    // 适用规格型号
        oldMandatoryInstrument.setPurpose(mandatoryInstrument.getPurpose());    // 用途
        oldMandatoryInstrument.setFixSite(mandatoryInstrument.getFixSite());    // 安装地点
        mandatoryInstrumentRepository.save(oldMandatoryInstrument);
        return;
    }

    /**
     * 检测是否可以被删除或是被全部更新
     *
     * @param mandatoryInstrument 强检器具
     * @return
     * @author panjie
     */
    public boolean canBeDeleteOrUpdate(MandatoryInstrument mandatoryInstrument) {
        logger.info("判断是否为退回");
        if (mandatoryInstrument.getStatus() != STATUS_BACKED
                && mandatoryInstrument.getStatus() != STATUS_NEW) {
            return false;
        }

        logger.info("判断强检器具的所在部门是否为用户所在的部门");
        if (userService.getCurrentLoginUser().getDepartment().getId() != mandatoryInstrument.getDepartment().getId()) {
            return false;
        }
        return true;
    }

    @Override
    public Page<MandatoryInstrument> pageAllOfManagementUser(User currentUser, Pageable pageable) {
        logger.info("获取当前登录用户的所有区域");
        List<District> districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());

        logger.info("获取所有的指定强检器具");
        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAllByDistricts(districts, pageable);
        return mandatoryInstruments;
    }

    @Override
    public List<MandatoryInstrument> getAllOfCurrentUser() {
        logger.info("取出当前登录用户");
        User currentUser = userService.getCurrentLoginUser();
        logger.info("获取所有的数据");
        List<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAllByLastCheckDepartment(currentUser.getDepartment());
        return mandatoryInstruments;
    }

    @Override
    public Page<MandatoryInstrument> pageByCheckDepartmentOfCurrentDepartment(Pageable pageable) {
        Department checkDepartment = userService.getCurrentLoginUser().getDepartment();
        return mandatoryInstrumentRepository.findAllByLastCheckDepartment(checkDepartment, pageable);
    }

    @Override
    public void updateFixSite(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException {
        MandatoryInstrument mandatoryInstrument1 = mandatoryInstrumentRepository.findOne(id);
        if (mandatoryInstrument1 == null) {
            throw new ObjectNotFoundException(404, "未找到强检器具使用信息");
        }
        mandatoryInstrument1.setFixSite(mandatoryInstrument.getFixSite());
        mandatoryInstrumentRepository.save(mandatoryInstrument1);
        return;
    }

    @Override
    public Page<MandatoryInstrument> pageAllOfCurrentManageDepartmentBySpecification(Long id, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Boolean overdue, Byte status, Pageable pageable) throws SecurityException {
        logger.info("获取查询的区域范围");
        List<District> districts = this.getQueryDistrictsOfCurrentUserByDepartmentIdAndDistrictId(departmentId, districtId);
        // 格式化数据
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);                                                  // id
        map.put("districts", districts);                                    // 区域
        map.put("departmentId", departmentId);                              // 所属部门
        map.put("name", name);                                              // 器具名称
        map.put("disciplineId", disciplineId);                              // 学科类别
        map.put("instrumentFirstLevelTypeId", instrumentFirstLevelTypeId);  // 一级类别
        map.put("instrumentTypeId", instrumentTypeId);                      // 二级类别
        map.put("audit", audit);                                            // 是否通过审核
        map.put("checkDepartmentId", checkDepartmentId);                    // 检定机构
        map.put("status", status);
        map.put("overdue", overdue);                    // 是否超期未检

        org.springframework.data.jpa.domain.Specification specification = MandatoryInstrumentSpecs.base(map);
        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAll(specification, pageable);

        return mandatoryInstruments;
    }

    /**
     * 根据指定的区域ID、部门ID来获取当前管理部门所查询的区域范围
     *
     * @param departmentId
     * @param districtId
     * @return panjie
     */
    private List<District> getQueryDistrictsOfCurrentUserByDepartmentIdAndDistrictId(Long departmentId, Long districtId) {
        User user = userService.getCurrentLoginUser();

        if (null != departmentId) {
            logger.info("传入部门ID，则将区域ID设置为当前部门所在ID");
            Department department = departmentRepository.findOne(departmentId);
            if (null == department) {
                throw new ObjectNotFoundException(404, "部门实体未找到");
            }
            districtId = department.getDistrict().getId();
        }

        // 获取所在的区域列表
        List<District> districts = this.getRightDistrictsByRootDistrictAndSonDistrictId(user.getDepartment().getDistrict(), districtId);
        return districts;
    }

    /**
     * 根据综合参数
     * 1. 查询数据
     * 2. 写入表头，数据列
     * 3. 生成文件名
     * 4. 写入文件
     * 5. 返回写入成功的文件
     *
     * @param id
     * @param districtId
     * @param departmentId
     * @param checkDepartmentId
     * @param disciplineId
     * @param instrumentFirstLevelTypeId
     * @param instrumentTypeId
     * @param audit
     * @param name
     * @param overdue
     * @param status
     * @return
     */
    @Override
    public File exportExcelOfCurrentManageDepartmentBySpecification(Long id, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Boolean overdue, Byte status) throws IOException {
        logger.info("查询传入条件下的所有数据");
        Page<MandatoryInstrument> mandatoryInstruments = this.pageAllOfCurrentManageDepartmentBySpecification(
                id,
                districtId,
                departmentId,
                checkDepartmentId,
                disciplineId,
                instrumentFirstLevelTypeId,
                instrumentTypeId,
                audit,
                name,
                overdue,
                status,
                null);

        logger.info("生成excel文档数据");
        Workbook workbook = this.getWorkbookByMandatoryInstruments(mandatoryInstruments);

        logger.info("生成文件名");
        User user = userService.getCurrentLoginUser();
        Calendar calendar = Calendar.getInstance();
        String filename = user.getDepartment().getName() +
                "强检器具备案数据导出" +
                +calendar.get(Calendar.YEAR)
                + calendar.get(Calendar.MONTH)
                + calendar.get(Calendar.DAY_OF_MONTH)
                + "_" + calendar.getTimeInMillis()
                + ".xlsx";

        logger.info("将excel文档数据写入文件");
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        logger.info("返回生成的文件");
        File file = new File(filename);
        return file;
    }

    @Override
    public File exportExcelOfCurrentUserBySpecification(Long id, Long disciplineId,
                                                        Long instrumentFirstLevelTypeId, Long instrumentTypeId,
                                                        Boolean audit, String name, Boolean overdue,
                                                        Pageable pageable) throws IOException {

        logger.info("查询传入条件下的所有数据");
        Page<MandatoryInstrument> mandatoryInstruments = this.pageAllOfCurrentUserBySpecification(id, disciplineId, instrumentFirstLevelTypeId, instrumentTypeId, audit, name, overdue, null, null);
        logger.info("生成excel文档数据");
        Workbook workbook = this.getWorkbookByMandatoryInstruments(mandatoryInstruments);

        logger.info("生成文件名");
        User user = userService.getCurrentLoginUser();
        Calendar calendar = Calendar.getInstance();
        String filename = user.getDepartment().getName() +
                "强检器具备案数据导出" +
                +calendar.get(Calendar.YEAR)
                + calendar.get(Calendar.MONTH)
                + calendar.get(Calendar.DAY_OF_MONTH)
                + "_" + calendar.getTimeInMillis()
                + ".xlsx";

        logger.info("将excel文档数据写入文件");
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        logger.info("返回生成的文件");
        File file = new File(filename);
        return file;
    }

    @Override
    public File exportExcelOfCurrentTechnicalInstitutionWithTokenBySpecification(Long id,
                                                                                 Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId,
                                                                                 Long instrumentTypeId, String name, Boolean isConfirmedByTechnologyDepartment,
                                                                                 Pageable pageable) throws IOException {
        logger.info("查询传入条件下的所有数据");
        Page<MandatoryInstrument> mandatoryInstruments = this.pageAllOfCurrentTechnicalInstitutionBySpecification(id, districtId, departmentId, disciplineId, instrumentFirstLevelTypeId, instrumentTypeId, name, isConfirmedByTechnologyDepartment, null);
        logger.info("生成excel文档数据");
        Workbook workbook = this.getWorkbookByMandatoryInstruments(mandatoryInstruments);

        logger.info("生成文件名");
        User user = userService.getCurrentLoginUser();
        Calendar calendar = Calendar.getInstance();
        String filename = user.getDepartment().getName() +
                "强检器具备案数据导出" +
                +calendar.get(Calendar.YEAR)
                + calendar.get(Calendar.MONTH)
                + calendar.get(Calendar.DAY_OF_MONTH)
                + "_" + calendar.getTimeInMillis()
                + ".xlsx";

        logger.info("将excel文档数据写入文件");
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        workbook.write(fileOutputStream);
        fileOutputStream.close();

        logger.info("返回生成的文件");
        File file = new File(filename);
        return file;
    }

    @Override
    public Workbook getWorkbookByMandatoryInstruments(Page<MandatoryInstrument> mandatoryInstruments) {
        logger.info("实例化excel");
        logger.info("参考：https://poi.apache.org/spreadsheet/quick-guide.html#NewWorkbook");
        Workbook workbook = new XSSFWorkbook();

        logger.info("实例化一个sheet0");
        Sheet sheet = workbook.createSheet("sheet0");

        logger.info("实例化一行，并设置为表头");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("系统编号");
        row.createCell(2).setCellValue("器具用户");
        row.createCell(3).setCellValue("器具名称");
        row.createCell(4).setCellValue("出厂名称");
        row.createCell(5).setCellValue("学科类别");
        row.createCell(6).setCellValue("一级类别");
        row.createCell(7).setCellValue("二级类别");
        row.createCell(8).setCellValue("用途");
        row.createCell(9).setCellValue("测量范围");
        row.createCell(10).setCellValue("准确度等级");
        row.createCell(11).setCellValue("规格型号");
        row.createCell(12).setCellValue("制造单位");
        row.createCell(13).setCellValue("出厂编号");
        row.createCell(14).setCellValue("安装地点");
        row.createCell(15).setCellValue("许可证号");
        row.createCell(16).setCellValue("状态");

        logger.info("循环设置每一行的内容");
        Integer i = 1;
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments.getContent()) {
            logger.info("新建一下行，并为这行填充数据");
            Row row1 = sheet.createRow(i);
            row1.createCell(0).setCellValue(i);
            row1.createCell(1).setCellValue(mandatoryInstrument.getId());
            row1.createCell(2).setCellValue(mandatoryInstrument.getDepartment().getName());
            row1.createCell(3).setCellValue(mandatoryInstrument.getName());
            row1.createCell(4).setCellValue(mandatoryInstrument.getOutOfFactoryName());
            row1.createCell(5).setCellValue(mandatoryInstrument.getInstrumentType().getInstrumentFirstLevelType().getDiscipline().getName());
            row1.createCell(6).setCellValue(mandatoryInstrument.getInstrumentType().getInstrumentFirstLevelType().getName());
            row1.createCell(7).setCellValue(mandatoryInstrument.getInstrumentType().getName());
            if (mandatoryInstrument.getPurpose() != null) {
                row1.createCell(8).setCellValue(mandatoryInstrument.getPurpose().getName());
            }
            if (mandatoryInstrument.getMeasureScale() != null) {
                row1.createCell(9).setCellValue(mandatoryInstrument.getMeasureScale());
            }
            if (mandatoryInstrument.getAccuracy() != null) {
                row1.createCell(10).setCellValue(mandatoryInstrument.getAccuracy().getValue());
            }
            row1.createCell(11).setCellValue(mandatoryInstrument.getSpecificationName());
            if (mandatoryInstrument.getGenerativeDepartment() != null) {
                row1.createCell(12).setCellValue(mandatoryInstrument.getGenerativeDepartment().getName());
            }
            row1.createCell(13).setCellValue(mandatoryInstrument.getSerialNum());
            row1.createCell(14).setCellValue(mandatoryInstrument.getFixSite());
            row1.createCell(15).setCellValue(mandatoryInstrument.getLicenseNum());
            row1.createCell(16).setCellValue(InstrumentEmploymentInfo.getStatusName(mandatoryInstrument.getStatus()));
            i++;
        }
        return workbook;
    }

    /**
     * 当到达系统规定的最长确认时间后，自动为技术机构确认器具
     * 1. 取出当前日期的天数
     * 2. 取出器具的审核日期
     * 3. 两者相减，如果时间大于最长的确认时间，则触发确认操作
     *
     * @param mandatoryInstruments 强检器具(分页数据)
     * @author panjie
     */
    @Override
    public void autoConfirmWhenTimeOutByMandatoryInstruments(Page<MandatoryInstrument> mandatoryInstruments) {
        logger.info("获取强检器具迭代器，同时，初始化要处理的强检器具及当前日期");
        Iterator<MandatoryInstrument> mandatoryInstrumentIterator = mandatoryInstruments.iterator();
        Set<MandatoryInstrument> handleMandatoryInstruments = new HashSet<>();


        logger.info("迭代处理");
        while (mandatoryInstrumentIterator.hasNext()) {
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentIterator.next();
            if (this.validateIsOverdueForTechnologyConfirm(mandatoryInstrument)) {
                handleMandatoryInstruments.add(mandatoryInstrument);
            }
        }

        if (handleMandatoryInstruments.size() > 0) {
            logger.info("当筛选出符合条件的器具时，触发保存操作");
            mandatoryInstrumentRepository.save(mandatoryInstruments);
        }

        return;
    }

    /**
     * 当前器具，是否为超期未确认（不允许手动被技术机构确认或是退回）
     * 1. 也确认的排除掉
     * 2. 未确认的未超期的排除掉
     * 涉及到系统配置的缓存技术.
     *
     * @return
     */
    public Boolean validateIsOverdueForTechnologyConfirm(MandatoryInstrument mandatoryInstrument) {
        logger.info("获取允许最大天数");
        Integer maxAllowBackDay = this.getMaxAllBackDay();
        if (maxAllowBackDay == 0) {
            logger.info("设置的过期天数为0，或为空");
            return false;
        }

        return false;
    }

    /**
     * 获取允许退回器具的最大天数
     *
     * @return panjie
     */
    @Override
    public Integer getMaxAllBackDay() {
        Integer maxAllowBackDay = 0;
        SystemConfig maxAllowBackDaySystemConfig = systemConfigService.findByKey(MandatoryInstrument.MAX_ALLOW_BACK_DAY_KEY);
        if (maxAllowBackDaySystemConfig == null) {
            logger.info("未设置最大过期天数");
            maxAllowBackDay = Integer.parseInt(MandatoryInstrument.MAX_ALLOW_BACK_DAY_VALUE);

        } else {
            maxAllowBackDay = Integer.parseInt(maxAllowBackDaySystemConfig.getValue());
        }

        return maxAllowBackDay;
    }


    @Override
    public Page<MandatoryInstrument> pageAllOfCurrentTechnicalInstitutionBySpecification(Long id, Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String name, Boolean isConfirmedByTechnologyDepartment, Pageable pageable) {
        User user = userService.getCurrentLoginUser();
        Department lastCheckDepartment = user.getDepartment();

        logger.info("获取所在的区域列表");
        List<District> districts = this.getRightDistrictsByRootDistrictAndSonDistrictId(user.getDepartment().getDistrict(), districtId);

        logger.info("格式化综合查询数据");
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);                                                  // id
        map.put("districts", districts);                                    // 区域
        map.put("departmentId", departmentId);                              // 所属部门
        map.put("name", name);                                              // 器具名称
        map.put("disciplineId", disciplineId);                              // 学科类别
        map.put("instrumentFirstLevelTypeId", instrumentFirstLevelTypeId);  // 一级类别
        map.put("instrumentTypeId", instrumentTypeId);                      // 二级类别
        map.put("lastCheckDepartmentId", lastCheckDepartment.getId());              // 审核部门
        map.put("isConfirmedByTechnologyDepartment", isConfirmedByTechnologyDepartment);              // 是否已被技术机构确认

        logger.info("构造查询条件，进行分页查询");
        org.springframework.data.jpa.domain.Specification specification = MandatoryInstrumentSpecs.base(map);
        Page<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAll(specification, pageable);

        return mandatoryInstruments;
    }

    /**
     * 获取当前部门拥有权限查看的区域列表
     *
     * @param rootDistrict  根区域
     * @param sonDistrictId 传入的子区域id
     * @return
     */
    @Override
    public List<District> getRightDistrictsByRootDistrictAndSonDistrictId(District rootDistrict, Long sonDistrictId) {
        logger.info("对区域进行权限判定");
        List<District> districts = null;
        districts = districtService.getSonsListByDistrict(rootDistrict);
        if (null != sonDistrictId) {
            Boolean found = false;
            for (int i = 0; i < districts.size() && !found; i++) {
                if (districts.get(i).getId().equals(sonDistrictId)) {
                    found = true;
                }
            }
            logger.info("传入的区域ID在父区域中，则取子区域包含的所有区域");
            if (true == found) {
                districts = districtService.getSonsListByDistrictId(sonDistrictId);
            }
        }

        return districts;
    }

    @Override
    public void setStatusToBackById(Long id) throws ObjectNotFoundException, SecurityException {
        logger.info("查看当前器具状态");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(id);
        if (null == mandatoryInstrument) {
            throw new ObjectNotFoundException(404, "传入的强检器具实体不存在");
        }

        if (mandatoryInstrument.getStatus() != STATUS_NEW) {
            throw new SecurityException("该器具当前状态不允许退回");
        }
        logger.info("取当前部门");
        Department currentDepartment = userService.getCurrentLoginUser().getDepartment();

        logger.info("设置状态");
        mandatoryInstrument.setStatus(STATUS_BACKED);

        logger.info("设置指定检定部门为null");
        mandatoryInstrument.setLastCheckDepartment(null);

        mandatoryInstrumentRepository.save(mandatoryInstrument);
        return;
    }

    /**
     * 排除掉不能够被退回的器具
     * 1. 排除掉不能被当前用户确认的器具
     * 2. 排除排超期未确认的器具
     *
     * @param mandatoryInstrumentList
     */
    private void removeCanNotBackToAssignedInstrumentByMandatoryInstrumentListOfCurrentUser(List<MandatoryInstrument> mandatoryInstrumentList) {
        this.removeCanNotConfirmMandatoryInstrumentByMandatoryInstrumentListOfCurrentUser(mandatoryInstrumentList);
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {
            if (this.validateIsOverdueForTechnologyConfirm(mandatoryInstrument)) {
                logger.info("排除超期未进行确认的");
                mandatoryInstrumentList.remove(mandatoryInstrument);
                continue;
            }
        }
    }

    /**
     * 排除当前用户没有权限或是不能够进行确认或是退回的器具
     * 1. 获取当前登录部门
     * 2 排除指定的审核部门非当前登录用户的
     * 3 排除超期未进行确认的（在一定的时间内，技术机构没有进行确认，也没有进行退回的，为超期未确认器具）
     * 4 排除状态为非正常的
     *
     * @param mandatoryInstrumentList panjie
     */
    private void removeCanNotConfirmMandatoryInstrumentByMandatoryInstrumentListOfCurrentUser(List<MandatoryInstrument> mandatoryInstrumentList) {
        logger.info("获取当前用户所在的部门");
        Department currentDepartment = userService.getCurrentLoginUser().getDepartment();

        logger.info("遍历只留下符合条件的强检器具");
        Iterator<MandatoryInstrument> iterator = mandatoryInstrumentList.iterator();
        while (iterator.hasNext()) {
            MandatoryInstrument mandatoryInstrument = iterator.next();
            logger.info("排除指定的审核部门非当前登录用户的");
            if ((mandatoryInstrument.getLastCheckDepartment() == null)
                    ||
                    (mandatoryInstrument.getLastCheckDepartment().getId() != currentDepartment.getId())) {
                logger.info("当前用户所在部门不为审核部门");
                iterator.remove();
                continue;
            }

            logger.info("排除状态为非正常的");
            if (mandatoryInstrument.getStatus() != STATUS_NORMAL) {
                logger.info("状态为非正常的器具");
                iterator.remove();
                continue;
            }
        }
        return;
    }

    /**
     * 批量确认当前用户被指定的强检器具
     * 1. 排除不符合确认要求的
     * 2. 循环设置确认状态
     * 3. 持久化
     *
     * @param mandatoryInstruments 强检器具
     */
    @Override
    public void batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser(Set<MandatoryInstrument> mandatoryInstruments) {
        logger.info("获取数据表中的持久化数据");
        List<MandatoryInstrument> mandatoryInstrumentList = (List<MandatoryInstrument>) this.findAllByMandatoryInstrumentCollection(mandatoryInstruments);

        logger.info("排队不符合确认要求的");
        this.removeCanNotConfirmMandatoryInstrumentByMandatoryInstrumentListOfCurrentUser(mandatoryInstrumentList);

        logger.info("循环设置确认状态");
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {
            mandatoryInstrument.setStatus(InstrumentEmploymentInfo.STATUS_NORMAL);
        }

        mandatoryInstrumentRepository.save(mandatoryInstrumentList);

        return;
    }


    @Override
    public Iterable<MandatoryInstrument> findAllByMandatoryInstrumentCollection(Collection<MandatoryInstrument> mandatoryInstrumentCollection) {
        logger.info("由数据库中查找传入的强检器具");
        List<Long> ids = new ArrayList<>();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentCollection) {
            ids.add(mandatoryInstrument.getId());
        }
        return mandatoryInstrumentRepository.findAll(ids);
    }

    @Override
    public Map<Department, List<MandatoryInstrument>> classificationByDepartment(List<MandatoryInstrument> mandatoryInstruments) {
        Map<Department, List<MandatoryInstrument>> map = new HashMap<>();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstruments) {
            if (!map.containsKey(mandatoryInstrument.getDepartment())) {
                logger.info("如果map的key中不包含该强检器具的拥有部门，新建一个arrayList");
                List<MandatoryInstrument> mandatoryInstruments1 = new ArrayList<>();
                mandatoryInstruments1.add(mandatoryInstrument);
                map.put(mandatoryInstrument.getDepartment(), mandatoryInstruments1);
            } else {
                logger.info("如果map的key中包含该强检器具的拥有部门，将器具添加至原list数组中");
                List<MandatoryInstrument> mandatoryInstruments1 = map.get(mandatoryInstrument.getDepartment());
                mandatoryInstruments1.add(mandatoryInstrument);
                map.put(mandatoryInstrument.getDepartment(), mandatoryInstruments1);
            }
        }
        return map;
    }

    @Override
    public void sendMessageToMeasurementUser(Department department, List<MandatoryInstrument> mandatoryInstruments) {
        logger.info("设置站内消息标题");
        String title = "器具退回通知";

        logger.info("设置站内消息内容");
        String content = "您被退回了" + mandatoryInstruments.size() + "个器具，您可以在强检器具备案管理中查看详情。";

        logger.info("发送站内消息");
        toMessageService.sendMessageToDepartment(content, title, department);
        return;
    }

    @Override
    public void sendMessageToManagementDepartment(Department department, List<MandatoryInstrument> mandatoryInstruments) {
        logger.info("设置站内消息的标题");
        String title = "器具退回通知";

        logger.info("设置站内消息内容");
        String content = mandatoryInstruments.get(0).getName() + "送审的器具被退回了" + mandatoryInstruments.size() + "个，您可以在强检器具备案管理中查看详情。";

        logger.info("发送站内消息");
        toMessageService.sendMessageToDepartment(content, title, department);
        return;
    }

    @Override
    public Page<MandatoryInstrument> getAllCurrentUserWorkOfOverTimeWarn(Pageable pageable) {
        logger.info("获取当前用户，今天以及30天以内的");
        User user = userService.getCurrentLoginUser();
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTimeInMillis());

        calendar.add(Calendar.DAY_OF_YEAR, MandatoryInstrument.WARN_BEFORE_DAYS);
        Date warnDaday = new Date(calendar.getTimeInMillis());

        return mandatoryInstrumentRepository.findAllByStatusAndDepartmentAndNextCheckDateBetween(MandatoryInstrument.STATUS_NORMAL, user.getDepartment(), today, warnDaday, pageable);
    }

    @Override
    public Page<MandatoryInstrument> getAllCurrentUserWorkOfOverTime(Pageable pageable) {
        User user = userService.getCurrentLoginUser();
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTimeInMillis()); // 当前时间
        return mandatoryInstrumentRepository.findAllByStatusAndDepartmentAndNextCheckDateBefore(MandatoryInstrument.STATUS_NORMAL, user.getDepartment(), today, pageable);
    }

    @Override
    public List<Department> getAllGenerativeDepartment() {
        return mandatoryInstrumentRepository.findAllGenerativeDepartment();
    }
}
