package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.SystemConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SystemConfigDataInitTest {
    @Autowired SystemConfigRepository systemConfigRepository;
    @Test
    public void countTest() {
        List<SystemConfig> systemConfigs = (List<SystemConfig>) systemConfigRepository.findAll();
        assertThat(systemConfigs.size()).isEqualTo(2);
    }
}