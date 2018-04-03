package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Migration;
import org.springframework.data.repository.CrudRepository;

/**
 * 数据迁移
 * 用来更新版本时进行数据的初始化工作
 * @author panjie
 */
public interface MigrationRepository extends CrudRepository<Migration, Long> {
    Migration findByBatch(String batch);
}
