package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.MandatoryInstrumentCheckApply;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * created by zhangjiahao
 */
public interface MandatoryInstrumentCheckApplyRepository extends PagingAndSortingRepository<MandatoryInstrumentCheckApply, Long>, JpaSpecificationExecutor {
}
