package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Accuracy;
import com.mengyunzhi.measurement.entity.InstrumentType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by administrator on 2017/5/24.
 */
public interface AccuracyRepository extends PagingAndSortingRepository<Accuracy,Long> {
    // 获取某个器具类别下的所有实体
    Iterable<Accuracy> findAllByInstrumentType(InstrumentType instrumentType);
}
