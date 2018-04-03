package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.ApplyType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-17.
 * 申请类型
 */
public interface ApplyTypeRepository extends CrudRepository<ApplyType, Long> {
    ApplyType findOneByName(String name);
}
