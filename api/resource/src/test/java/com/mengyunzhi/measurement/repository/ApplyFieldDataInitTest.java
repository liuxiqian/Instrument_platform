package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.ApplyField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/10/12.
 * 申请类型字段初始化
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplyFieldDataInitTest {
    @Autowired ApplyFieldRepository applyFieldRepository;   // 申请字段
    @Test
    public void lengthTest() {
        Iterable<ApplyField> applyFields = applyFieldRepository.findAll();

        assertThat(applyFields.spliterator().getExactSizeIfKnown()).isGreaterThanOrEqualTo(6);
    }
}