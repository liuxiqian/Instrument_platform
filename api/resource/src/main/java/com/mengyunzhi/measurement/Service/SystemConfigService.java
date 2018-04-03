package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.SystemConfig;
import org.hibernate.ObjectNotFoundException;

import java.util.*;

/**
 * 系统设置
 */
public interface SystemConfigService {
    HashMap<String, SystemConfig> SYSTEM_CONFIG_HASH_MAP = new HashMap<>();
    Date LAST_UPDATE_TIME = null;
    SystemConfig findByKey(String key) throws ObjectNotFoundException;
    SystemConfig getOneUnSavedSystemConfig();
    SystemConfig getOneSavedSystemConfig();
    List<SystemConfig> findAll();
}
