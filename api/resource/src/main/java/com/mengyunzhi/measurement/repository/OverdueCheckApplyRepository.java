package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.OverdueCheckApply;
import org.springframework.data.repository.CrudRepository;

/**
 * @author panjie on 2017/12/12
 * 超期检定申请
 */
public interface OverdueCheckApplyRepository extends CrudRepository<OverdueCheckApply, Long> {
}
