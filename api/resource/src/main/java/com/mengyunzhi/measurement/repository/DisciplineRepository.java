package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Discipline;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liming on 17-5-18.
 */
public interface DisciplineRepository extends PagingAndSortingRepository<Discipline, Long> {
}
