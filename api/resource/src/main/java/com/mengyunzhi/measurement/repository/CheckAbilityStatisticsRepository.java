package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.CheckAbilityStatistics;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * 器具检定能力统计
 * @Author poshichao
 */
public interface CheckAbilityStatisticsRepository extends CrudRepository<CheckAbilityStatistics, Long> {
    List<CheckAbilityStatistics> findAll(Specification<CheckAbilityStatistics> Specification);
}
