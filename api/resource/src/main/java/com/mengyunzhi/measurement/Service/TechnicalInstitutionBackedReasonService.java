package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.TechnicalInstitutionBackedReason;

import java.util.List;

/**
 * Created by chuhang on 17-11-10
 * 技术机构退回原因 service
 */
public interface TechnicalInstitutionBackedReasonService {
    // 获取最新的10条退回理由
    List<TechnicalInstitutionBackedReason> getTop10TechnicalInstitutionBackedReasons();

    /**
     * 通过理由，获取一个持久化的对象
     * @param reason 理由
     * @return
     */
    TechnicalInstitutionBackedReason getOnePersistenceTechnicalInstitutionBackedReasonByReason(String reason);
}
