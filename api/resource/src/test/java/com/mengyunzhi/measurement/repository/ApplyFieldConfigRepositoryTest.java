package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/10/9.
 * 申请字段配置
 */
public class ApplyFieldConfigRepositoryTest extends RepositoryTest{
    @Autowired
    private ApplyFieldConfigRepository applyFieldConfigRepository;

    @Test
    public void findAllTest() {
        assertThat(applyFieldConfigRepository.findAll()).isNotNull();
    }
}