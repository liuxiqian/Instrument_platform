package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.SystemConfig;
import com.mengyunzhi.measurement.repository.SystemConfigRepository;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {
    static Logger logger = Logger.getLogger(SystemConfigServiceImpl.class.getName());

    @Autowired
    SystemConfigRepository systemConfigRepository;

    @Override
    public SystemConfig findByKey(String key) throws ObjectNotFoundException {
        SystemConfig systemConfig = (SystemConfig)MemoryCacheService.get(key);
        if (systemConfig == null) {
            systemConfig = systemConfigRepository.findByK(key);
            if (systemConfig == null) {
                throw new ObjectNotFoundException(404, "未找到关键字为:" + key + "的信息");
            }
            MemoryCacheService.put(key, systemConfig);
        }

        return systemConfig;
    }

    @Override
    public SystemConfig getOneUnSavedSystemConfig() {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setK(CommonService.getRandomStringByLength(20));
        systemConfig.setValue(CommonService.getRandomStringByLength(50));
        systemConfig.setDescription(CommonService.getRandomStringByLength(100));
        return systemConfig;
    }

    @Override
    public SystemConfig getOneSavedSystemConfig() {
        SystemConfig systemConfig = this.getOneUnSavedSystemConfig();
        systemConfigRepository.save(systemConfig);
        return systemConfig;
    }

    @Override
    public List<SystemConfig> findAll() {
        return (List<SystemConfig>) systemConfigRepository.findAll();
    }
}
