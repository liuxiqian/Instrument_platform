package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.output.BackQualifiedInstrument;

import java.util.List;

/**
 * zhuchenshu
 */
public interface ForceInstrumentStatisticsQualifiedRateService {
    // 鉴定合格率查询（年份和区域）
    List<BackQualifiedInstrument> pageAllByYearAndDistrictAndParams(Long[] year, Long district, Long purposeId, Long intrumentTypeId, Long generativeDepartmentId);
    // 鉴定合格率查询（年份区域和用户）
    List<BackQualifiedInstrument> pageAllByYearAndDistrictAndDepartmentParams(Long[] year, Long district, Long departmentId, Long purposeId, Long intrumentTypeId, Long generativeDepartmentId);
}
