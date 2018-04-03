package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Migration;
import com.mengyunzhi.measurement.repository.MigrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版本控制
 * @author panjie
 */
@Service
public class MigrationServiceImpl implements MigrationService {
    private MigrationRepository migrationRepository;    // 数据初始化版本控制

    @Autowired
    public MigrationServiceImpl(MigrationRepository migrationRepository) {
        this.migrationRepository = migrationRepository;
    }

    @Cacheable("MigrationServiceImpl_findAll")
    @Override
    public List<Migration> findAll() {
        return (List<Migration>) migrationRepository.findAll();
    }

    /**
     * 按批次进行查询
     * @param batch 批次
     * @return Migration
     * @author panjie
     */
    @Override
    public Migration findByBatch(String batch) {
        List<Migration> migrations = this.findAll();
        for (Migration migration : migrations) {
            if (migration.getBatch().equals(batch)) {
                return migration;
            }
        }

        return null;
    }

    /**
     * 操作某个批次的实体
     * @param batch 批次
     * @return Migration
     * @author panjie
     */
    @Override
    public Migration saveByBatch(String batch) {
        Migration migration = new Migration();
        migration.setBatch(batch);
        migrationRepository.save(migration);
        return migration;
    }
}
