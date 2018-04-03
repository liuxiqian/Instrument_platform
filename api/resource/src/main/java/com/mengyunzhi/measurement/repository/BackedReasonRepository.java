package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.BackedReason;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;
/**
 * 退回理由实体仓库
 */
public interface BackedReasonRepository extends PagingAndSortingRepository<BackedReason, Long> {
    // 获取最进的10条理由
    List<BackedReason> findTop10ByOrderByIdDesc();

    BackedReason findBackedReasonByReason(String reason);
}
