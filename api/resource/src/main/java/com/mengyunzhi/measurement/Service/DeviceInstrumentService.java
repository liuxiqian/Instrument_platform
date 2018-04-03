package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目
 */
public interface DeviceInstrumentService {
    DeviceInstrument findById(Long id);
    /**
     * 查找实体
     * @param deviceSetId 标准装置ID
     * @param instrumentTypeId 器具二级类别ID
     * @return 实
     */
    DeviceInstrument findByDeviceSetIdAndInstrumentTypeId(Long deviceSetId, Long instrumentTypeId);

    Page<DeviceInstrument> pageAllByCurrentUserOfDeviceInstrument(Pageable pageable);

    Page<?> pageAllOfCurrentUserOfManagement(Pageable pageable);
    // 综合查询授权检定项目信息
    Page<DeviceInstrument> pageByDeviceSetOfCurrentUser(DeviceSet deviceSet, Pageable pageable);
    Page<DeviceInstrument> pageByDeviceSetIdOfCurrentUser(Long deviceSetId, Pageable pageable);

    // 获取当前用户的所有检定信息
    Page<DeviceInstrument> pageOfCurrentUser(Pageable pageable);

    //综合查询条件授权检定项目 name, deviceSetId, districtId, departmentId, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId
    Page<DeviceInstrument> pageAllOfCurrentManageDepartmentBySpecification(Long deviceSetId, Long districtId, Long departmentId, Long disciplineId, Long instrumentTypeFirstLevelId, Long instrumentTypeId, String name, Pageable pageable);
    DeviceInstrument getOneUnSavedDeviceInstrument();

    // 获取某部门对某个器具是否拥有检定能定
    Boolean getCheckAbilityByMandatoryInstrumentAndDepartment(MandatoryInstrument mandatoryInstrument, Department department);
    // 获取某部门是否有相关的（）检定能力

    /**
     * 获取某部门是否有相关（精确度等级，最小测量范围，最大测量范围，器具二级类别）的检定能力
     * @param accuracy 精确度
     * @param minMeasureScale 最小测量范围
     * @param maxMeasureScale 最大测量范围
     * @param instrumentType 器具类别
     * @param department 部门
     * @return
     */
    Boolean getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(
            Accuracy accuracy,
            MeasureScale minMeasureScale,
            MeasureScale maxMeasureScale,
            InstrumentType instrumentType,
            Department department);

    Boolean getCheckAbility(Accuracy accuracy, MeasureScale minMeasureScale, MeasureScale maxMeasureScale, List<DeviceInstrument> deviceInstrumentSet);

    void delete(Long id);
    void save(DeviceInstrument deviceInstrument) throws NullPointerException ;
    DeviceInstrument getOneSavedDeviceInstrument();

    /**
     * 获取对强检器具有检定能力的技术机构
     * 如果本区域未找到，则找父区域 如果找到根区域仍未找到 返回null
     * @param district 区域
     * @param mandatoryInstrument 强检器具
     * @return 具有检定能力的技术机构
     * panjie
     */
    Department getCheckAbilityDepartmentByDistrictAndMandatoryInstrument(
            District district,
            MandatoryInstrument mandatoryInstrument
    );
}
