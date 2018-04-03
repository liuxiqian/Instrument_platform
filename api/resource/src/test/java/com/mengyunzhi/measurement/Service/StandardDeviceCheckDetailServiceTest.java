package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.DeviceSetRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceCheckDetailRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-14.
 * 测试标准器检定信息serviceTest
 */
public class StandardDeviceCheckDetailServiceTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(StandardDeviceCheckDetailServiceTest.class.getName());
    @Autowired
    private StandardDeviceCheckDetailService standardDeviceCheckDetailService;
    @Autowired
    private StandardDeviceService standardDeviceService;
    @Autowired
    protected StandardDeviceRepository standardDeviceRepository;
    @Autowired
    protected StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository;
    @Autowired
    UserService userService;
    @Autowired
    DeviceSetRepository deviceSetRepository;

    @Test
    public void save() throws Exception {
        //保存
        logger.info("设置当前登陆用户");
        User user = userService.getOneSavedUser();
        userService.setCurrentLoginUser(user);

        logger.info("测试同步修改标准器实体的最近检定记录");
        logger.info("创建新的标准器信息");
        StandardDevice standardDevice = new StandardDevice();
        Department department = user.getDepartment();
        logger.info("创建新的计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        logger.info("将当前用户部门保存到计量标准的部门");
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);
        logger.info("将当前计量标准装置保存为标准器的计量标准装置");
        standardDevice.setDeviceSet(deviceSet);
        standardDeviceRepository.save(standardDevice);
        logger.info("为标准器创建鉴定记录");
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        standardDeviceCheckDetail.setStandardDevice(standardDevice);
        standardDeviceCheckDetailService.save(standardDeviceCheckDetail);

        //断言
        assertThat(standardDeviceCheckDetail.getId()).isNotNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("创建一个对象,当做被更新对象");
        StandardDeviceCheckDetail oldStandardDeviceCheckDetail = new StandardDeviceCheckDetail();
        oldStandardDeviceCheckDetail.setInspectorQualifierCertificate("ceshi1");

        logger.info("持久化");
        standardDeviceCheckDetailRepository.save(oldStandardDeviceCheckDetail);
        Long id = oldStandardDeviceCheckDetail.getId();

        logger.info("断言InspectorQualifierCertificate为ceshi1");
        assertThat(standardDeviceCheckDetailRepository.findOne(id).getInspectorQualifierCertificate()).isEqualTo("ceshi1");

        logger.info("创建一个新对象,用于更新实体");
        StandardDeviceCheckDetail newStandardDeviceCheckDetail = new StandardDeviceCheckDetail();
        newStandardDeviceCheckDetail.setInspectorQualifierCertificate("ceshi2");

        logger.info("用新实体来更新来老实体");
        standardDeviceCheckDetailService.update(newStandardDeviceCheckDetail,id);
        assertThat(standardDeviceCheckDetailRepository.findOne(id).getInspectorQualifierCertificate()).isEqualTo("ceshi2");
    }

    @Test
    public void delete() throws Exception {
        // 新建
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        // 保存
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);
        // 删除
        standardDeviceCheckDetailService.delete(standardDeviceCheckDetail.getId());
        // 断言
        StandardDeviceCheckDetail standardDeviceCheckDetail1 = standardDeviceCheckDetailRepository.findOne(standardDeviceCheckDetail.getId());
        assertThat(standardDeviceCheckDetail1).isNull();
    }

    @Test
    public void getAllByStandardDeviceId() {
        List<StandardDeviceCheckDetail> list = new ArrayList<StandardDeviceCheckDetail>();
        //获取一个standarDevice
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceService.save(standardDevice);

        list = standardDeviceCheckDetailService.getAllByStandardDevice(standardDevice);
    }

    @Test
    public void pageAllByStandardDeviceId() {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceRepository.save(standardDevice);
        logger.info("新建一个标准器检定信息");
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        standardDeviceCheckDetail.setStandardDevice(standardDevice);
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);
        PageRequest pageRequest = new PageRequest(1, 1);
        logger.info("测试");
        Page<StandardDeviceCheckDetail> page = standardDeviceCheckDetailService.pageAllByStandardDeviceId(standardDevice.getId(), pageRequest);

        logger.info("断言");
        assertThat(page.getTotalPages()).isEqualTo(1);

        logger.info("删除数据");
        standardDeviceCheckDetailRepository.delete(standardDeviceCheckDetail);
        standardDeviceRepository.delete(standardDevice);
    }

    @Test
    public void findOneById() throws Exception {
        logger.info("新建一个标准器检定信息对象");
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        logger.info("持久化");
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);
        Long id = standardDeviceCheckDetail.getId();
        logger.info("调用service里的方法,获取这个对象");
        StandardDeviceCheckDetail newStandardDeviceCheckDetail = standardDeviceCheckDetailService.findOneById(id);
        logger.info("断言获取到的对象就是这个");
        assertThat(newStandardDeviceCheckDetail.getId()).isEqualTo(id);
    }
}