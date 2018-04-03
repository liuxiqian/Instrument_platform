package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.SystemConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class SystemConfigRepositoryTest extends RepositoryTest {
    @Autowired SystemConfigRepository systemConfigRepository;
    @Test
    public void findAllTest() {
        List<SystemConfig> systemConfigs = (List<SystemConfig>) systemConfigRepository.findAll();
        assertThat(systemConfigs.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void findByKTest() {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setK("Z2QuCR0mSbpLDm4NQ3rW");
        systemConfigRepository.save(systemConfig);

        SystemConfig systemConfig1 = systemConfigRepository.findByK(systemConfig.getK());
        assertThat(systemConfig1.getId()).isEqualTo(systemConfig1.getId());
    }
}