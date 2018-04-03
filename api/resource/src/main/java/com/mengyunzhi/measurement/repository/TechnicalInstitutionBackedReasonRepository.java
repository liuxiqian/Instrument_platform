package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.TechnicalInstitutionBackedReason;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by chuhang on 17-11-9
 * 技术机构退回原因 仓库
 */
public interface TechnicalInstitutionBackedReasonRepository extends CrudRepository<TechnicalInstitutionBackedReason, Long> {
    TechnicalInstitutionBackedReason findTechnicalInstitutionBackedReasonByReason(String reason);
    List<TechnicalInstitutionBackedReason> findTop10ByOrderByIdDesc();
}
