package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.ApplyFieldRecord;

/**
 * 申请字段记录
 */
public interface ApplyFieldRecordService {
    /**
     * 获取某条指定的记录
     * @param apply 申请
     * @param applyFieldName 申请字段
     * @return
     */
    ApplyFieldRecord getByApplyAndApplyFieldName(Apply apply, String applyFieldName);

    String getValueByApplyAndApplyFieldName(Apply apply, String applyFieldName);

    ApplyFieldRecord getOneUnSaveApplyFieldRecord();
    ApplyFieldRecord getOneSavedApplyFieldRecord();
}
