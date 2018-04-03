package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/10/9.
 */
public class ApplyFieldRepositoryTest extends RepositoryTest{
    @Autowired
    private ApplyFieldRepository applyFieldRepository;
    @Test
    public void findAllTest() {
        assertThat(applyFieldRepository.findAll()).isNotNull();
    }
}