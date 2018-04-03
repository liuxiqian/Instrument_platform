package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.DeviceInstrument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import  static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/10/13.
 * 授权检定范围
 */
public class DeviceInstrumentServiceImplTest extends ServiceTest{
    @Autowired
    DeviceInstrumentService deviceInstrumentService;    // 授权检定项目
    @Autowired UserService userService;
    @Test
    public void saveTest() {
        userService.loginWithOneUser();
        DeviceInstrument deviceInstrument = deviceInstrumentService.getOneUnSavedDeviceInstrument();
        deviceInstrumentService.save(deviceInstrument);
        assertThat(deviceInstrument.getId()).isNotNull();
    }
}