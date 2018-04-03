package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.AccuracyDisplayName;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 使用@RepositoryRestRescource注解，解决跨域问题
 */
public interface AccuracyDisplayNameRepository extends PagingAndSortingRepository<AccuracyDisplayName, Long> {
}
