package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/10/9.
 * 文本字段
 */
public class TextFieldRepositoryTest extends RepositoryTest{
    @Autowired
    private TextFieldRepository textFieldRepository;
    @Test
    public void findTest() {
        assertThat(textFieldRepository.findAll()).isNotNull();
    }
}