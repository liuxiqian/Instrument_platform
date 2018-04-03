package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.ApplyType;

import java.util.List;

/**
 * Created by panjie on 17/7/6.
 * 申请类型M层
 */
public interface ApplyTypeService {
    ApplyType getOneSavedApplyType();
    ApplyType getOneByName(String name);
    ApplyType getById(Long id);

    ApplyType save(ApplyType applyType);
    void update(Long id, ApplyType applyType);
    void delete(Long id);
    List<ApplyType> getAll();

    ApplyType findOneByName(String className);

    ApplyType findByApply(Apply apply);
}
