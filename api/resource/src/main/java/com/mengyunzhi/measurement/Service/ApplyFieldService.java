package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.ApplyField;

import java.util.List;

/**
 * Created by panjie on 17/10/12.
 * 申请字段
 */
public interface ApplyFieldService {
    ApplyField getOneApplyField();

    ApplyField getOneSavedApplyField();

    ApplyField findById(Long id);

    ApplyField save(ApplyField applyField);

    void delete(Long id);

    List<ApplyField> getAll();

    void update(Long id, ApplyField applyField);

    List<ApplyField> getAllByApplyTypeId(Long applyTypeId);
}
