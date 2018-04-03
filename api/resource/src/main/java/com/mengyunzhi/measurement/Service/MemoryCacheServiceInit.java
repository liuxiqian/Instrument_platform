package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.MemoryCache;
import com.mengyunzhi.measurement.entity.SystemConfig;
import com.mengyunzhi.measurement.dataInit.SystemConfigDataInit;
import com.mengyunzhi.measurement.repository.SystemConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 数据初始化，从数据表中取数，然后加入过期时间
 */
@Component
public class MemoryCacheServiceInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    @Autowired
    SystemConfigRepository systemConfigRepository;
    @Autowired
    SystemConfigDataInit systemConfigDataInit;  // 系统配置初始化
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        SystemConfig systemConfig = systemConfigRepository.findByK(MemoryCache.EXPIRE_TIME_KEY);
        if (systemConfig != null) {
            Integer expireTime = Integer.parseInt(systemConfig.getValue());
            MemoryCacheService.setExpireTime(expireTime);
        }
    }

    @Override
    public int getOrder() {
        return systemConfigDataInit.getOrder() + 10;
    }
}
