package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.StandardFile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by panjie on 17/5/5.
 * 标准装置
 */
public class StandardFileRepositoryTest extends RepositoryTest {
    @Autowired
    private StandardFileRepository standardFileRepository;

    @Test
    public void save() {
        StandardFile standardFile = new StandardFile();
        standardFileRepository.save(standardFile);
        assertThat(standardFile.getId()).isNotNull();
    }


}