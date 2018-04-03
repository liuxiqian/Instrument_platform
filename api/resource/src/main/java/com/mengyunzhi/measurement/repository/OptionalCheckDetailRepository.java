package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.OptionalCheckDetail;
import com.mengyunzhi.measurement.entity.OptionalIntegrated;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by zhangjiahao on 2017/5/9.
 */
public interface OptionalCheckDetailRepository extends PagingAndSortingRepository<OptionalCheckDetail, Long> {
    List<OptionalCheckDetail> getAllByOptionalIntegrated(OptionalIntegrated optionalIntegrated);
}
