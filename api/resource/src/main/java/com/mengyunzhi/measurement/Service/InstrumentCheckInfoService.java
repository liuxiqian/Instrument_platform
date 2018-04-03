package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.InstrumentCheckInfo;

/**
 * Created by chuhang on 17-8-1.
 * 器具检定信息的Service
 */
public interface InstrumentCheckInfoService {
    /**
     * 获取一个持久化的实体
     * @return
     * panjie
     */
    InstrumentCheckInfo getOneSavedInstrumentCheckInfo();

    /**
     * 获取一个未持久化的实体
     * @return
     * panjie
     */
    InstrumentCheckInfo getOneUnSaveInstrumentCheckInfo();
}
