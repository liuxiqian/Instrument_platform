package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.MemoryCache;
import com.mengyunzhi.measurement.entity.SystemConfig;
import com.mengyunzhi.measurement.repository.SystemConfigRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * 内存cache初始化测试
 */
public class MemoryCacheServiceInitTest extends ServiceTest{
    @Autowired
    SystemConfigRepository systemConfigRepository;
    @Test
    public void onApplicationEvent() throws Exception {
        SystemConfig systemConfig = systemConfigRepository.findByK(MemoryCache.EXPIRE_TIME_KEY);
        if (systemConfig != null) {
            assertThat(Integer.parseInt(systemConfig.getValue())).isEqualTo(MemoryCacheService.getExpireTime());
        }
    }

}