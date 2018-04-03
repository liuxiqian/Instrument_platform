package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.output.CheckAbilityStatisticsView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

/**
 * 检定能力（覆盖率）统计
 * @author panjie
 */
public interface CheckAbilityStatisticsService {
    // 数据初始化
    void dataInit();

    void computerAbilityAndDataInit(HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap, List<District> districtList);

    void addDeviceInstrumentListOfDistrictList(HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap, List<District> districtList);

    void computerCheckAbility(District district,
                              HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap,
                              MandatoryInstrument mandatoryInstrument,
                              CheckAbilityStatistics checkAbilityStatistics);

    /**
     * 获取所有的器具检定能力统计信息
     * @param districtId
     * @param disciplineId
     * @param instrumentTypeId
     * @param minMeasureScaleWeight
     * @param maxMeasureScaleWeight
     * @return
     * @Author poshichao
     */
    List<CheckAbilityStatistics> getAllBySpecification(Long districtId, Long disciplineId, Long instrumentTypeId, Integer minMeasureScaleWeight, Integer maxMeasureScaleWeight);

    /**
     * 根据区域和学科类别获取所有数据
     * @param districtId
     * @param disciplineId
     * @return
     * poshichao
     */
    List<CheckAbilityStatisticsView> getAllByDistrictAndDiscipline(Long districtId, Long disciplineId);

    /**
     * 根据区域和器具类别获取所有数据
     * @param districtId
     * @param instrumentTypeId
     * @return
     * poshichao
     */
    List<CheckAbilityStatisticsView> getAllByDistrictAndInstrumentType(Long districtId  , Long instrumentTypeId);

    /**
     * 判断函数是否执行完成
     * @return
     */
    Boolean hasFinished();

    /**
     * 异步调用数据写入函数
     */
    void asyncDataInit();
}
