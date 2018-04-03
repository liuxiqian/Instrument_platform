package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.StandardFile;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by panjie on 17/5/5.
 * 标准装置
 */
public interface StandardFileRepository extends PagingAndSortingRepository<StandardFile, Long> {
}
