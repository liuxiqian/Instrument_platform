package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.ManagementDepartmentBackedReason;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by chuhang on 17-11-9
 * 管理部门退回原因 仓库
 */
public interface ManagementDepartmentBackedReasonRepository extends CrudRepository<ManagementDepartmentBackedReason, Long> {
    List<ManagementDepartmentBackedReason> findTop10ByOrderByIdDesc();
    ManagementDepartmentBackedReason findManagementDepartmentBackedReasonByReason(String reason);
}
