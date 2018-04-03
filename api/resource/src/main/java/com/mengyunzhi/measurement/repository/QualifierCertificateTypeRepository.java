package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.QualifierCertificateType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liming on 17-5-18.
 */
public interface QualifierCertificateTypeRepository extends PagingAndSortingRepository<QualifierCertificateType, Long> {
    Page<QualifierCertificateType> findAllByDisciplineId(Long disciplineId, Pageable pageble);
}
