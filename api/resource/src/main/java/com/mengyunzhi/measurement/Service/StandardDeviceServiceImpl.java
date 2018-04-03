package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetailRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by liming on 17-5-9.
 * 标准器
 */
@Service
public class StandardDeviceServiceImpl implements StandardDeviceService {
    private final static Logger logger = LoggerFactory.getLogger(StandardDeviceServiceImpl.class.getName());
    private final StandardDeviceRepository standardDeviceRepository;
    private final StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository;
    private final UserService userService;
    final ManufacturerDepartmentService manufacturerDepartmentService;  // 生产企业

    final DeviceSetService deviceSetService;   // 标准装置

    @Value("${yunzhi.warn_before_day}")
    private int warnBeforeDay;    // 預警的间隔天数

    @Autowired
    public StandardDeviceServiceImpl(StandardDeviceRepository standardDeviceRepository, StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository, UserService userService, DeviceSetService deviceSetService, ManufacturerDepartmentService manufacturerDepartmentService) {
        this.standardDeviceRepository = standardDeviceRepository;
        this.standardDeviceCheckDetailRepository = standardDeviceCheckDetailRepository;
        this.userService = userService;
        this.deviceSetService = deviceSetService;
        this.manufacturerDepartmentService = manufacturerDepartmentService;
    }

    @Override
    public StandardDevice save(StandardDevice standardDevice) {
        logger.debug("获取当前生产企业名称对应的相关信息");
        this.setManufactureDepartment(standardDevice);

        logger.debug("获取当前年月日");

        logger.debug("将下次检定日期设置为一年后");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        standardDevice.setNextCheckDate(new Date(calendar.getTimeInMillis()));

        logger.debug("持久化");
        return standardDeviceRepository.save(standardDevice);
    }

    /**
     * 设置生产企业信息
     * 根据传入的生产企业的名称，查找生产企业信息
     * 如果数据表中没有当前生产企业，则持久化一个。
     * @param standardDevice
     * @author panjie
     */
    private void setManufactureDepartment(StandardDevice standardDevice) {
        if (null != standardDevice.getManufacturerDepartment()) {
            standardDevice.setManufacturerDepartment(
                    manufacturerDepartmentService.getOneSavedManagementDepartmentByName(
                            standardDevice.getManufacturerDepartment().getName()));
        }
    }

    @Override
    public StandardDevice findOne(Long id) {
        return standardDeviceRepository.findOne(id);
    }

    /**
     * 获取一个持久化的实体
     * @return
     * @Author panjie
     */
    @Override
    public StandardDevice getOneSavedStandardDevice() {
        StandardDevice standardDevice = this.getOneUnSavedStandardDevice();
        standardDeviceRepository.save(standardDevice);
        return standardDevice;
    }

    /**
     * 获取一个没有持久化的实体
     * @return
     */
    @Override
    public StandardDevice getOneUnSavedStandardDevice() {
        DeviceSet deviceSet = deviceSetService.getOneSavedDeviceSet();
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setName("测试标准器" + CommonService.getRandomStringByLength(10));
        standardDevice.setManufacturerDepartment(manufacturerDepartmentService.getOneSavedManufacturerDepartment());
        standardDevice.setDeviceSet(deviceSet);
        return standardDevice;
    }

    @Override
    public List<StandardDevice> getAllByDeviceSetId(Long deviceSetId) {
        List<StandardDevice> standardDevices = standardDeviceRepository.findAllByDeviceSetId(deviceSetId);
        return standardDevices;
    }

    @Override
    public StandardDevice update(Long id, StandardDevice standardDevice) {
        standardDevice.setId(id);
        return standardDeviceRepository.save(standardDevice);
    }


    @Override
    public void delete(Long id) {
        standardDeviceRepository.delete(id);
        return;
    }

    @Override
    public Page<StandardDevice> pageAllByDeviceSetId(Long deviceSetId, Pageable pageable) {
        return standardDeviceRepository.findAllByDeviceSetId(deviceSetId, pageable);
    }

    @Override
    public void updateLastCheckDetailByStandardDeviceCheckDetail(StandardDeviceCheckDetail standardDeviceCheckDetail) {
        // 获取检定信息中的标准器信息的标准器ID
        Long standardDeviceId = standardDeviceCheckDetail.getStandardDevice().getId();
        // 查看该标准器
        StandardDevice standardDevice = standardDeviceRepository.findOne(standardDeviceId);
        // 增加权限判定
        // 获取当前用户所在部门
        Department userDepartment = userService.getCurrentLoginUser().getDepartment();
        // 获取标准器所在部门
        Department standardDeviceDepartment = standardDevice.getDeviceSet().getDepartment();
        // 判断登录用户部门ID与标准器所在部门ID是否相同
        if (userDepartment.getId().equals(standardDeviceDepartment.getId())) {
            // 设置标准器的最后一次检定记录
            standardDevice.setLastCheckDetail(standardDeviceCheckDetail);
            // 保存标准器
            standardDeviceRepository.save(standardDevice);
        } else {
            throw new SecurityException("您无此操作权限");
        }
    }

    /**
     * 分页查找标准器预警信息
     * @param pageable
     * @return
     * zhuchenshu
     */
    @Override
    public Page<StandardDevice> pageAllWarnStandardDevice(Pageable pageable) {
        User user = userService.getCurrentLoginUser(); // 当前用户
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        // 计算预警日期
        calendar.add(Calendar.DAY_OF_YEAR, warnBeforeDay);
        Date warnDay = new Date(calendar.getTimeInMillis());
        // 查找下次鉴定日期在当前日期与鉴定日期之间的标准器
        return standardDeviceRepository.findAllByDepartmentAndNextCheckDateBetweenTodayAndNextDay(warnDay, user.getDepartment(), pageable);
    }

    /**
     * 分页查找标准器报警信息
     * @param pageable
     * @return
     * zhuchenshu
     */
    @Override
    public Page<StandardDevice> pageAllAlarmStandardDevice(Pageable pageable) {
        User user = userService.getCurrentLoginUser(); // 当前用户
        // 查找检定日期在当前日期之后的标准器
        return standardDeviceRepository.findAllDepartmentAndByNextCheckDateBeforeToday(user.getDepartment(), pageable);
    }
}
