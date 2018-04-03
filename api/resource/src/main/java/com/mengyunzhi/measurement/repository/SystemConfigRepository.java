package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.SystemConfig;
import org.springframework.data.repository.CrudRepository;

public interface SystemConfigRepository extends CrudRepository<SystemConfig, Long>{
    SystemConfig findByK(String key);
}
