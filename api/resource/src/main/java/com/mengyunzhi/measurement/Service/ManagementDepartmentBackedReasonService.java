package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.ManagementDepartmentBackedReason;

import java.util.List;

/**
 * Created By chuhang on 17-11-02
 * 管理部门退回原因service
 */

public interface ManagementDepartmentBackedReasonService {
    // 获取最新的10条退回理由
    List<ManagementDepartmentBackedReason> getTop10ManagementDepartmentBackedReasons();

    /**
     * 通过理由，获取一个持久化的对象
     * @param reason 理由
     * @return
     */
    ManagementDepartmentBackedReason getOnePersistenceManagementDepartmentBackedReasonByReason(String reason);
}
