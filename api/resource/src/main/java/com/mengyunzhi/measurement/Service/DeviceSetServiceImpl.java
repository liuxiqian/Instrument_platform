package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.AccuracyRepository;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import com.mengyunzhi.measurement.repository.MeasureScaleRepository;
import com.mengyunzhi.measurement.repository.DeviceSetRepository;
import com.mengyunzhi.measurement.repository.DeviceInstrumentRepository;
import com.mengyunzhi.measurement.repository.InstrumentTypeRepository;
import com.mengyunzhi.measurement.specs.DeviceSetSpecs;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by panjie on 17/7/6.
 * 计量标准装置
 */
@Service
public class DeviceSetServiceImpl implements DeviceSetService {
    static private Logger logger = Logger.getLogger(DeviceSetServiceImpl.class.getName());
    @Autowired
    protected UserService userService;

    @Autowired
    protected DistrictService districtService;
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository;
    @Autowired
    protected DeviceSetService deviceSetService;
    @Autowired private AccuracyRepository accuracyRepository;                   // 精度
    @Autowired private MeasureScaleRepository measureScaleRepository;           // 测量范围
    @Autowired private DepartmentRepository departmentRepository;               // 部门
    @Autowired private DeviceSetRepository deviceSetRepository;                 // 标准装置
    @Autowired private DeviceInstrumentRepository deviceInstrumentRepository;   // 授权检定项目
    @Autowired private InstrumentTypeRepository instrumentTypeRepository;       // 器具二级类别
    @Autowired private DeviceInstrumentService deviceInstrumentService;         // 装置授权检定项目

    @Override
    public DeviceSet save(DeviceSet deviceSet) {
        //获取当前登录用户
        User user = userService.getCurrentLoginUser();
        //获取当前登录用户的部门
        Department department = user.getDepartment();
        //为计量标准装置设置技术机构部门
        deviceSet.setDepartment(department);
        return deviceSetRepository.save(deviceSet);
    }

    @Override
    public void delete(DeviceSet deviceSet) {
        deviceSetRepository.delete(deviceSet);
        return;
    }

    @Override
    public DeviceSet updateDeviceInstrumentsById(Long id, DeviceSet deviceSet) {
        deviceSet.setId(id);
        return deviceSetRepository.save(deviceSet);
    }

    @Override
    public Page<DeviceSet> getAll(Pageable pageable) {
        Page<DeviceSet> page = deviceSetRepository.findAll(pageable);
        return page;
    }

    @Override
    public List<DeviceSet> getAllDeviceSetByTechnicalInstitutionId(Long technicallnstitutionId) {
        List<DeviceSet> deviceSets = deviceSetRepository.findAllByDepartmentId(technicallnstitutionId);
        return deviceSets;
    }

    @Override
    public Page<DeviceSet> pageAllOfCurrentUser(Pageable pageable) {
        //获取当前登陆用户
        User currentUser = userService.getCurrentLoginUser();
        //获取当前登陆用的部门
        Department department = currentUser.getDepartment();

        Page<DeviceSet> deviceSets = deviceSetRepository.findAllByDepartment(department, pageable);
//        String departmentTypeName = currentUser.getDepartment().getDepartmentType().getName();
//
//        if (departmentTypeName == "技术机构") {
//            logger.info("当前的登录用户为技术机构，获取次技术机构的计量标准装置");
//            Page<DeviceSet> deviceSets = deviceSetRepository.findAllByDepartment(department, pageable);
//            return deviceSets;
//        } else {
//            logger.info("当前登录用户为管理部门，获取管理部门内的所有技术机构的计量标准装置");
//            return deviceSetService.pageAllOfCurrentManagementDepartmentUser(currentUser, pageable);
//        }

        return deviceSets;

    }

    @Override
    public DeviceSet get(Long id) {
        DeviceSet deviceSet = deviceSetRepository.findOne(id);
        return deviceSet;
    }

    @Override
    public void delete(Long id) {
        //获取当前登录用户
        User user = userService.getCurrentLoginUser();
        DeviceSet deviceSet = deviceSetRepository.findOne(id);
        //判断当前登录用户是否和此标准器的用户相等
        if (user.getDepartment().getId() == deviceSet.getDepartment().getId()) {
            logger.info("有权限删除");
            deviceSetRepository.delete(deviceSet);
        } else {
            logger.info("没有权限删除");
            throw new ObjectNotFoundException(401, "您没有权限删除");
        }

        return;
    }

    @Override
    public void update(Long id, DeviceSet deviceSet) {
        DeviceSet deviceSet1 = deviceSetRepository.findOne(id);
        if (deviceSet1 == null) {
            throw new ObjectNotFoundException(404, "未找到ID为" + id.toString() + "的计量标准装置实体");
        } else {
            deviceSet.setId(id);
            deviceSetRepository.save(deviceSet);
        }
        return;
    }

    @Override
    public Page<DeviceSet> pageAllOfCurrentManagementDepartmentUser(User currentUser, Pageable pageable) {
        logger.info("获取当前登录用户所属于的区域");
        District district = currentUser.getDepartment().getDistrict();
        logger.info("获取这个区域及所有子区域");
        List<District> districts = districtService.getSonsListByDistrict(district);
        logger.info("获取所有的计量标准装置");
        Page<DeviceSet> deviceSets = deviceSetRepository.findAllByDisctricts(districts, pageable);
        return deviceSets;
    }

    @Override
    public Page<DeviceSet> pageAllOfCurrentUserBySpecification(Long districtId, Long departmentId, String name, String code, Pageable pageable) {
        Page<DeviceSet> deviceSets = null;

        logger.info("获取当前登录用户");
        User currentUser = userService.getCurrentLoginUser();

        if (null != departmentId) {
            logger.info("传入部门ID, 将区域ID设置为当前部门所在ID");
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
                throw new SecurityException("对不起, 您只能查看本区域的信息");
            }
            districts = districtService.getSonsListByDistrictId(districtId);
        }

        //格式化数据
        Map<String, Object> map = new HashMap<>();
        map.put("districts", districts);                                        //区域
        map.put("code", code);
        map.put("districtId", districtId);
        map.put("departmentId", departmentId);
        map.put("name", name);

        org.springframework.data.jpa.domain.Specification specification = DeviceSetSpecs.base(map);
        deviceSets = deviceSetRepository.findAll(specification, pageable);


        return deviceSets;
    }

    @Override
    public Boolean getCheckAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId(Long accuracyId, Long minMeasureScaleId, Long maxMeasureScaleId, Long instrumentTypeId, Long departmentId) throws Exception {
        Accuracy accuracy = accuracyRepository.findOne(accuracyId);
        if (null == accuracy) {
            throw new ObjectNotFoundException(404, "未找到ID为" + accuracyId.toString() + "的Accuracy精度实体");
        }

        MeasureScale minMeasureScale = measureScaleRepository.findOne(minMeasureScaleId);
        if (null == minMeasureScale) {
            throw new ObjectNotFoundException(404, "未找到ID为" + minMeasureScaleId.toString() + "的MeasureScale测量范围实体");
        }

        MeasureScale maxMeasureScale = measureScaleRepository.findOne(maxMeasureScaleId);
        if (null == maxMeasureScale) {
            throw new ObjectNotFoundException(404, "未找到ID为" + maxMeasureScaleId.toString() + "的MeasureScale测量范围实体");
        }

        Department department = departmentRepository.findOne(departmentId);
        if (null == department) {
            throw new ObjectNotFoundException(404, "未找到ID为" + departmentId.toString() + "的Department部门实体");
        }

        InstrumentType instrumentType = instrumentTypeRepository.findOne(instrumentTypeId);
        if (null == instrumentType) {
            throw new ObjectNotFoundException(404, "未找到ID为" + departmentId.toString() + "的instrumentType器具二级类别实体");
        }

        logger.info("判断传入参数是否为当前器具类别上拥有的参数，如果不拥有，则抛出异常");
        logger.info("判断精度度");
        Boolean found = false;
        Iterator<Accuracy> accuracyIterator = instrumentType.getAccuracies().iterator();
        while(!found && accuracyIterator.hasNext()) {
            if (accuracyIterator.next().getId().equals(accuracyId)) {
                found = true;
            }
        }

        if (!found) {
            throw new Exception("传入的精确度与传入的器具类别不对应");
        }

        logger.info("判断测量范围是否属于当前器具类别");
        boolean foundMin = false;
        boolean foundMax = false;
        Iterator<MeasureScale> measureScaleIterator = instrumentType.getMeasureScales().iterator();
        while (measureScaleIterator.hasNext()) {
            MeasureScale measureScale = measureScaleIterator.next();
            if (measureScale.getId().equals(minMeasureScaleId)) {
                foundMin = true;
            }

            if (measureScale.getId().equals(maxMeasureScaleId)) {
                foundMax = true;
            }
        }

        if (!foundMin) {
            throw new Exception("传入的最小测量范围与传入的器具类别不对应");
        }

        if (!foundMax) {
            throw new Exception("传入的最大测量范围与传入的器具类别不对应");
        }

        Boolean checkAbility = this.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, instrumentType, department);
        return checkAbility;
    }

    @Override
    public Boolean getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(Accuracy accuracy, MeasureScale minMeasureScale, MeasureScale maxMeasureScale, InstrumentType instrumentType, Department department) {
        Boolean checkAbility = deviceInstrumentService.getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(accuracy, minMeasureScale, maxMeasureScale, instrumentType, department);
        return checkAbility;
    }

    /**
     * 获取一个持久化的实体
     * @return
     * @author panjie
     */
    @Override
    public DeviceSet getOneSavedDeviceSet() {
        User user = userService.getOneSavedUser();
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(user.getDepartment());
        deviceSet.setCreateUser(user);
        deviceSetRepository.save(deviceSet);
        return deviceSet;
    }
}
