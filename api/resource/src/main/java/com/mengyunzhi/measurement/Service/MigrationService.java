package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Migration;

import java.util.List;

/**
 * 数据初始化版本控制
 * @author panjie
 */
public interface MigrationService {
    List<Migration> findAll();

    Migration findByBatch(String batch);
    Migration saveByBatch(String batch);
}
