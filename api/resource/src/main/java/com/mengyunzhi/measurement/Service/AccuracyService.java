package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Accuracy;

/**
 * Created by panjie on 17/6/28.
 * 精度
 */
public interface AccuracyService {
    // 保存
    Accuracy save(Accuracy accuracy);

    Accuracy getOneSavedAccuracy();
    static Accuracy getOneAccuracy() {
        Accuracy accuracy = new Accuracy();
        return accuracy;
    }
    void deleteById(Long id);

    /**
     * 获取某个器具类别下的所有精确度
     * @param instrumentTypeId 器具类别id
     * @return
     * panjie
     */
    Iterable<Accuracy> getAllByInstrumentTypeId(Long instrumentTypeId);

}
