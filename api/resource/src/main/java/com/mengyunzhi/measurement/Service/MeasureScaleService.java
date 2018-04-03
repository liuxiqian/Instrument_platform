package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.MeasureScale;

/**
 * Created by panjie on 17/7/19.
 * 测量范围
 */
public interface MeasureScaleService {
    MeasureScale getOneSavedMeasureScale();
    static MeasureScale getOneMeasureScale() {
        MeasureScale measureScale = new MeasureScale();
        return measureScale;
    }

    /**
     * 获取某个类别下的所有测量范围值
     * @param instrumentTypeId  器具类别
     * @return
     * panjie
     */
    Iterable<MeasureScale> getAllByInstrumentTypeId(Long instrumentTypeId);
}
