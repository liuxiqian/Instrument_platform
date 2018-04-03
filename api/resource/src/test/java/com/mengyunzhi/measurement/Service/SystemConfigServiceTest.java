package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.SystemConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class SystemConfigServiceTest extends ServiceTest{
    @Autowired SystemConfigService systemConfigService;
    @Test
    public void findByKeyTest() {
        SystemConfig systemConfig =systemConfigService.getOneSavedSystemConfig();
        SystemConfig systemConfig1 = systemConfigService.findByKey(systemConfig.getK());
        assertThat(systemConfig.getId()).isEqualTo(systemConfig1.getId());
    }
}