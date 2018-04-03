package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.User;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * 内存缓存测试
 */
public class MemoryCacheServiceTest extends ServiceTest {
    Logger logger = Logger.getLogger(MemoryCacheServiceTest.class.getName());
    @Test
    public void remove() throws Exception {
        logger.info("加个缓存");
        User user = new User();
        MemoryCacheService.put("user", user);
        user = (User) MemoryCacheService.get("user");
        assertThat(user).isNotNull();

        logger.info("移出两次，再获取，断言值均为null");
        MemoryCacheService.remove("user");
        user = (User) MemoryCacheService.get("user");
        assertThat(user).isNull();

        MemoryCacheService.remove("user");
        user = (User) MemoryCacheService.get("user");
        assertThat(user).isNull();
    }

    @Test
    public void get() throws Exception {
        logger.info("加个3秒的缓存");
        User user = new User();
        MemoryCacheService.put("user", user, 3);
        user = (User) MemoryCacheService.get("user");
        assertThat(user).isNotNull();

        logger.info("断言3秒后，再获取的时候。返回null");
        Thread.sleep(3001L);
        user = (User) MemoryCacheService.get("user");
        assertThat(user).isNull();
    }

    @Test
    public void put() throws Exception {

    }

}