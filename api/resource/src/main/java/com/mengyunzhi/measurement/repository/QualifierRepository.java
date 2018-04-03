package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chuhang on 17-5-24.
 */
public interface QualifierRepository extends PagingAndSortingRepository<Qualifier, Long>, JpaSpecificationExecutor {
    Page<Qualifier> findAllByDepartmentId(Long departmentId, Pageable pageable);
    List<Qualifier> findAllByDepartment(Department department);
    Page<Qualifier> findAllByDepartment(Department department, Pageable pageable);
}
