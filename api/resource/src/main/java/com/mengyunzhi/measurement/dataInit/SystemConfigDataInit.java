package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.EntityInitDataListener;
import com.mengyunzhi.measurement.entity.MandatoryInstrument;
import com.mengyunzhi.measurement.entity.MemoryCache;
import com.mengyunzhi.measurement.entity.SystemConfig;
import com.mengyunzhi.measurement.repository.SystemConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SystemConfigDataInit extends EntityInitDataListener {
    @Autowired
    SystemConfigRepository systemConfigRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<SystemConfig> systemConfigList = (List<SystemConfig>) systemConfigRepository.findAll();
        if (systemConfigList.size() > 0) {
            return;
        }

        List<SystemConfig> systemConfigs = new ArrayList<>();
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setK(MandatoryInstrument.MAX_ALLOW_BACK_DAY_KEY);
        systemConfig.setValue(MandatoryInstrument.MAX_ALLOW_BACK_DAY_VALUE);
        systemConfig.setDescription("最大允许退回的时间");
        systemConfigs.add(systemConfig);

        SystemConfig systemConfig1 = new SystemConfig();
        systemConfig1.setK(MemoryCache.EXPIRE_TIME_KEY);
        systemConfig1.setValue("1800");
        systemConfig1.setDescription("系统配置缓存过期时间(秒)，后台重启动生效");
        systemConfigs.add(systemConfig1);



        systemConfigRepository.save(systemConfigs);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 10;
    }
}
