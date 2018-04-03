package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.MandatoryInstrumentApply;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by panjie on 17/7/14.
 * 强检器具审核
 */
public interface MandatoryInstrumentApplyRepository extends CrudRepository<MandatoryInstrumentApply, Long>{
    Set<MandatoryInstrumentApply> findAllByDepartmentId(Long id);
}
