package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.entity.ApplyType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by panjie on 17/10/9.
 */
public interface ApplyFieldRepository extends CrudRepository<ApplyField, Long>{
    ApplyField findByApplyTypeAndName(ApplyType applyType, String name);
    List<ApplyField> findAllByApplyTypeId(Long applyTypeId);
}
