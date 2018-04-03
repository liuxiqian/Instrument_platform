package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Migration;
import com.mengyunzhi.measurement.repository.MigrationRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 数据版本控制
 */
public class MigrationServiceImplTest extends ServiceTest {
    private final Logger logger = Logger.getLogger(MigrationServiceImplTest.class.getName());
    @Autowired
    MigrationService migrationService;
    @Autowired
    MigrationRepository migrationRepository;
    @Test
    public void findAll() {
       List<Migration> migrations = migrationService.findAll();
       assertNotNull(migrations);
    }

    @Test
    public void findByBatch() {
        logger.info("获取随机字符串，并发起查询");
        String batch = CommonService.getRandomStringByLength(32);
        Migration migration0 = migrationService.findByBatch(batch);
        assertNull(migration0);

        logger.debug("实例化一个实体");
        Migration migration = new Migration();
        migration.setBatch(batch);
        migrationRepository.save(migration);

        logger.info("调用方法断方获取了这个实体");
        Migration migration1 = migrationService.findByBatch(batch);
        assertEquals(migration.getId(), migration1.getId());
    }

    @Test
    public void saveByBatch() {
        String batch = CommonService.getRandomStringByLength(32);
        migrationService.saveByBatch(batch);
        Migration migration = migrationRepository.findByBatch(batch);
        assertNotNull(migration);
    }
}