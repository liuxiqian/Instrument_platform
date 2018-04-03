package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据迁移
 * @author panjie
 */
public class MigrationRepositoryTest extends RepositoryTest {
    @Autowired
    MigrationRepository migrationRepository;    // 仓库
    @Test
    public void findAllTest() {
        migrationRepository.findAll();
    }
}