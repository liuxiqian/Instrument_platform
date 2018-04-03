package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.DeviceInstrumentSpecs;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目Service实现层
 */
@Service
public class DeviceInstrumentServiceImpl implements DeviceInstrumentService {
    static private Logger logger = Logger.getLogger(DeviceInstrumentServiceImpl.class.getName());
    @Autowired
    protected DeviceSetService deviceSetService;                     // 标准器
    @Autowired
    protected UserService userService;                               // 用户
    @Autowired
    protected DistrictService districtService;                       // 区域
    @Autowired
    protected DeviceInstrumentService deviceInstrumentService;       // 授权检定项目
    @Autowired
    private DepartmentRepository departmentRepository;               // 部门
    @Autowired
    private DeviceSetRepository deviceSetRepository;                 // 标准装置
    @Autowired
    private DeviceInstrumentRepository deviceInstrumentRepository;   // 授权检定项目
    @Autowired @org.springframework.beans.factory.annotation.Qualifier("MandatoryInstrumentTypeService") InstrumentTypeService instrumentTypeService; // 器具类别
    @Autowired InstrumentTypeRepository instrumentTypeRepository;
    @Autowired
    AccuracyRepository accuracyRepository;       // 精确度
    @Autowired
    MeasureScaleRepository measureScaleRepository;   // 测量范围
    @Autowired TechnicalInstitutionDepartmentService technicalInstitutionDepartmentService; // 技术机构
    @Override
    public DeviceInstrument findById(Long id) {
        DeviceInstrument deviceInstrument = deviceInstrumentRepository.findOne(id);
        return deviceInstrument;
    }

    @Override
    public DeviceInstrument findByDeviceSetIdAndInstrumentTypeId(Long deviceSetId, Long instrumentTypeId) {
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setId(deviceSetId);
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setId(instrumentTypeId);

        return deviceInstrumentRepository.findOneByDeviceSetAndInstrumentType(deviceSet, instrumentType);
    }

    @Override
    public Page<DeviceInstrument> pageAllByCurrentUserOfDeviceInstrument(Pageable pageable) {
        //获取当前登录用户
        User currentUser = userService.getCurrentLoginUser();
        String departmentTypeName = currentUser.getDepartment().getDepartmentType().getName();
        String realDepartementTypeName = "技术机构";
        if (departmentTypeName.equals(realDepartementTypeName)) {
            //返回分页信息
            return (Page<DeviceInstrument>) deviceInstrumentRepository.findAllByDepartmentOfDeviceInstrument(currentUser.getDepartment(), pageable);
        } else {
            //如果是管理部门
            return (Page<DeviceInstrument>) deviceInstrumentService.pageAllOfCurrentUserOfManagement(pageable);
        }
    }

    @Override
    public Page<?> pageAllOfCurrentUserOfManagement(Pageable pageable) {
        //获取当强登录用户
        User currentUser = userService.getCurrentLoginUser();
        //获取当前登录用户的所有区域
        List<District> districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());
        //返回分页信息
        Page<?> page = deviceInstrumentRepository.findAllByDistricts(districts, pageable);
        return page;
    }

    @Override
    public Page<DeviceInstrument> pageByDeviceSetOfCurrentUser(DeviceSet deviceSet, Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        Long id = deviceSet.getId();
        if (id == null) {
            return this.pageOfCurrentUser(pageable);
        }

        Page<DeviceInstrument> deviceInstruments = deviceInstrumentRepository.findAllByDeviceSetDepartmentAndDeviceSetId(department, id, pageable);
        return deviceInstruments;
    }

    @Override
    public Page<DeviceInstrument> pageByDeviceSetIdOfCurrentUser(Long deviceSetId, Pageable pageable) {
        DeviceSet deviceSet = new DeviceSet();
        if (deviceSetId != null) {
            deviceSet.setId(deviceSetId);
        }
        return this.pageByDeviceSetOfCurrentUser(deviceSet, pageable);
    }

    @Override
    public Page<DeviceInstrument> pageOfCurrentUser(Pageable pageable) {
        Department department = userService.getCurrentLoginUser().getDepartment();
        Page<DeviceInstrument> deviceInstruments = deviceInstrumentRepository.findAllByDeviceSetDepartment(department, pageable);
        return deviceInstruments;
    }

    /**
     * 多条件查询
     *
     * @param deviceSetId                //标准器ID
     * @param districtId                 //区域ID
     * @param departmentId               //技术机构ID
     * @param disciplineId               //学科ID
     * @param instrumentTypeFirstLevelId //一级类别
     * @param instrumentTypeId           //二级类别
     * @param name                       //标准装置名称
     * @return
     */
    @Override
    public Page<DeviceInstrument> pageAllOfCurrentManageDepartmentBySpecification(Long deviceSetId, Long districtId, Long departmentId, Long disciplineId, Long instrumentTypeFirstLevelId, Long instrumentTypeId, String name, Pageable pageable) {
        logger.info("获取当前登录用户");
        User currentUser = userService.getCurrentLoginUser();

        if (null != departmentId) {
            logger.info("传入部门ID,将区域ID设置为当前部门所在ID");
            Department department = departmentRepository.findOne(departmentId);
            if (null == department) {
                throw new ObjectNotFoundException(404, "没有找到部门实体");
            }
            districtId = department.getDistrict().getId();
        }

        logger.info("对区域的权限进行判断");
        List<District> districts = null;
        districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());
        if (null != districtId) {
            Boolean found = false;
            for (int i = 0; i < districts.size() && !found; i++) {
                if (districts.get(i).getId() == districtId) {
                    found = true;
                }
            }
            if (false == found) {
                throw new SecurityException("对不起,您只能查看本区域的信息");
            }
            districts = districtService.getSonsListByDistrictId(districtId);
        }

        //格式化数据
        Map<String, Object> map = new HashMap<>();
        map.put("deviceSetId", deviceSetId);                                    //标准装置ID
        map.put("districts", districts);                                        //区域
        map.put("departmentId", departmentId);                                  //所属部门
        map.put("disciplineId", disciplineId);                                  //学科ID
        map.put("instrumentTypeFirstLevelId", instrumentTypeFirstLevelId);      //一级器具类别
        map.put("instrumentTypeId", instrumentTypeId);                          //二级类别
        map.put("name", name);                                                  //标准装名称

        org.springframework.data.jpa.domain.Specification specification = DeviceInstrumentSpecs.base(map);
        Page<DeviceInstrument> deviceInstruments = deviceInstrumentRepository.findAll(specification, pageable);
        return deviceInstruments;
    }

    @Autowired
    private MandatoryInstrumentTypeRepository mandatoryInstrumentTypeRepository;  // 强检器具类别

    @Override
    public DeviceInstrument getOneUnSavedDeviceInstrument() {
        logger.info("实例化器具类别");
        InstrumentType instrumentType = InstrumentTypeService.getOneInstrumentType();
        instrumentTypeRepository.save(instrumentType);

        logger.info("实例化精确度");
        Accuracy accuracy = AccuracyService.getOneAccuracy();
        accuracy.setInstrumentType(instrumentType);
        accuracyRepository.save(accuracy);

        logger.info("实例化最小测量范围");
        MeasureScale minMeasureScale = MeasureScaleService.getOneMeasureScale();
        minMeasureScale.setInstrumentType(instrumentType);
        measureScaleRepository.save(minMeasureScale);

        logger.info("实例化最大测量范围");
        MeasureScale maxMeasureScale = MeasureScaleService.getOneMeasureScale();
        maxMeasureScale.setInstrumentType(instrumentType);
        measureScaleRepository.save(maxMeasureScale);

        logger.info("实例化标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(userService.getCurrentLoginUser().getDepartment());
        deviceSetRepository.save(deviceSet);

        logger.info("实例化授权检定项目");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMinMeasureScale(minMeasureScale);
        deviceInstrument.setMaxMeasureScale(maxMeasureScale);
        deviceInstrument.setInstrumentType(instrumentType);
        deviceInstrument.setDeviceSet(deviceSet);
        return deviceInstrument;
    }

    /**
     * 获取某个部门对某个器具的检定能力
     * @param mandatoryInstrument
     * @param department
     * @return
     * panjie
     */
    @Override
    public Boolean getCheckAbilityByMandatoryInstrumentAndDepartment(
            MandatoryInstrument mandatoryInstrument,
            Department department) {
        return this.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(
                mandatoryInstrument.getAccuracy(),
                mandatoryInstrument.getMinMeasureScale(),
                mandatoryInstrument.getMaxMeasureScale(),
                mandatoryInstrument.getInstrumentType(),
                department);
    }

    /**
     * 获取某部门是否有相关（精确度等级，最小测量范围，最大测量范围，器具二级类别）的检定能力
     * 1. 取出该部门检定该器具类别的授权检定项目
     * 2. 依次比较检定项目中的精度与最小最大测量范围
     * 3. 只要有一个检定项目可以检定，则回true
     * 4. 全部无检定能力，返回false
     * @param accuracy 精确度
     * @param minMeasureScale 最小测量范围
     * @param maxMeasureScale 最大测量范围
     * @param instrumentType 器具类别
     * @param department 部门
     * @return
     */
    @Override
    public Boolean getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(
            Accuracy accuracy,
            MeasureScale minMeasureScale,
            MeasureScale maxMeasureScale,
            InstrumentType instrumentType,
            Department department) {
        logger.info("取出当前部门对应传入器具二级类别的授权检定项目信息");
        List<DeviceInstrument> deviceInstrumentSet = (List<DeviceInstrument>) deviceInstrumentRepository.findAllByInstrumentTypeAndDeviceSetDepartment(instrumentType, department);

        return getCheckAbility(accuracy, minMeasureScale, maxMeasureScale, deviceInstrumentSet);
    }

    @Override
    public Boolean getCheckAbility(Accuracy accuracy, MeasureScale minMeasureScale, MeasureScale maxMeasureScale, List<DeviceInstrument> deviceInstrumentSet) {
        if (minMeasureScale.getWeight() > maxMeasureScale.getWeight()) {
            logger.info("交换最大最小测试范围（防止用户选错）");
            MeasureScale temp = minMeasureScale;
            minMeasureScale = temp;
            maxMeasureScale = minMeasureScale;
        }
        for (DeviceInstrument deviceInstrument: deviceInstrumentSet) {
            logger.info("判断是否拥有检定能力");
            if (deviceInstrument.getAccuracy().getWeight() >= accuracy.getWeight()) {
                logger.info("拥有检定精度的能力");

                MeasureScale deviceInstrumentMinMeasureScale;
                MeasureScale deviceInstrumentMaxMeasureScale;
                if (deviceInstrument.getMinMeasureScale().getWeight() > deviceInstrument.getMaxMeasureScale().getWeight()) {
                    logger.info("交换授权检定的最大小最小测量范围(防止用户在添加数据时有错误)");
                    deviceInstrumentMinMeasureScale = deviceInstrument.getMaxMeasureScale();
                    deviceInstrumentMaxMeasureScale = deviceInstrument.getMinMeasureScale();
                } else {
                    logger.info("赋值");
                    deviceInstrumentMinMeasureScale = deviceInstrument.getMinMeasureScale();
                    deviceInstrumentMaxMeasureScale = deviceInstrument.getMaxMeasureScale();
                }

                logger.info("判断是否拥有测量范围的检定能力");
                if (deviceInstrumentMinMeasureScale.getWeight() <= minMeasureScale.getWeight()) {
                    logger.info("授权检定项目的最小测试范围符合小于等于器具的最小测量范围");
                    if (deviceInstrumentMaxMeasureScale.getWeight() >= maxMeasureScale.getWeight()) {
                        logger.info("同时，授权检定项目的最大测量范围大于等于器具的最大测量范围");
                        return true;
                    }
                }
            }
        }

        logger.info("未找到有检定能力的检定项目");
        return false;
    }


    @Override
    public void delete(Long id) {
        deviceInstrumentRepository.delete(id);
        return;
    }

    @Override
    public void save(DeviceInstrument deviceInstrument) throws NullPointerException {
        if (deviceInstrument.getAccuracy() == null ) {
            throw new NullPointerException("未接收到精确度实体");
        }

        if (deviceInstrument.getDeviceSet() == null) {
            throw new NullPointerException("未接收到标准装置实体");
        }

        if (deviceInstrument.getInstrumentType() == null) {
            throw new NullPointerException("未接收到器具类别实体");
        }

        if (deviceInstrument.getMinMeasureScale() == null) {
            throw new NullPointerException("未接收到测量范围实体");
        }

        logger.info("获取标准装置的部门信息, 用于进行权限判断");
        deviceInstrument.setDeviceSet(deviceSetRepository.findOne(deviceInstrument.getDeviceSet().getId()));
        if (deviceInstrument.getDeviceSet().getDepartment().getId() != userService.getCurrentLoginUser().getDepartment().getId()) {
                throw new SecurityException("接收到的标准装置所属于部门当前登录用户所在部门");
        }
        deviceInstrumentRepository.save(deviceInstrument);
        return;
    }



    @Override
    public DeviceInstrument getOneSavedDeviceInstrument() {
        DeviceInstrument deviceInstrument = this.getOneUnSavedDeviceInstrument();
        deviceInstrumentService.save(deviceInstrument);
        return deviceInstrument;
    }

    /**
     * 获取对强检器具有检定能力的技术机构
     * 如果本区域未找到，则找父区域 如果找到根区域仍未找到 返回null
     * @param district 区域
     * @param mandatoryInstrument 强检器具
     * @return 具有检定能力的技术机构
     * panjie
     */
    @Override
    public Department getCheckAbilityDepartmentByDistrictAndMandatoryInstrument(District district, MandatoryInstrument mandatoryInstrument) {
        do {
            logger.debug("查找当前区域上的技术机构");
            technicalInstitutionDepartmentService.setTechnicalInstitutionDepartmentsForDistrict(district);

            logger.debug("判断技术机构是否有检定能力");
            for (Department technicalInstitution : district.getTechnicalInstitutionDepartments()) {
                logger.debug("判断当前技术机构是否具有检定能力");
                if (deviceInstrumentService.getCheckAbilityByMandatoryInstrumentAndDepartment(mandatoryInstrument, technicalInstitution) == true) {

                    logger.info("有技术机构, 则返回");
                    return technicalInstitution;
                }
            }

            logger.debug("取父区域");
            district = district.getParentDistrict();
        } while (district != null);

        return null;
    }
}
