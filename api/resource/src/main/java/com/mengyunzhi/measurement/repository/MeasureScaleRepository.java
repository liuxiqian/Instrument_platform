package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.InstrumentType;
import com.mengyunzhi.measurement.entity.MeasureScale;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-21.
 */
public interface MeasureScaleRepository extends CrudRepository<MeasureScale, Long> {
    // 获取某个器具类别下的所有测量范围 panjie
    Iterable<MeasureScale> findAllByInstrumentType(InstrumentType instrumentType);
}
