package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by panjie on 17/7/6.
 * 计量标准装置
 */
public interface DeviceSetService {
    DeviceSet save(DeviceSet deviceSet);

    void delete(DeviceSet deviceSet);

    DeviceSet updateDeviceInstrumentsById(Long id, DeviceSet deviceSet);

    Page<DeviceSet> getAll(Pageable pageable);

    //根据技术机构ID获取对应的所有的计量标准装置
    List<DeviceSet> getAllDeviceSetByTechnicalInstitutionId(Long technicalInstitutionId);

    //根据当前登陆用户获取它的标准装置列表
    Page<DeviceSet> pageAllOfCurrentUser(Pageable pageable);

    static DeviceSet getOneDeviceSet() {
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setName("测试计量标准装置" + CommonService.getRandomStringByLength(10));
        return deviceSet;
    }

    //根据计量标准装置id获取数组（部门电话、地址、精度实体、测量范围实体）
    DeviceSet get(Long id);

    void delete(Long id);

    //更新计量标准装置实体
    void update(Long id, DeviceSet deviceSet);

    //根据当前登录的管理部门获取它所在区域及子区域下的所有技术机构中的计量标准装置
    Page<DeviceSet> pageAllOfCurrentManagementDepartmentUser(User currentUser, Pageable pageable);

    //组合条件查询 (区域id, 技术机构id, 标准装置id, 代码)
    Page<DeviceSet> pageAllOfCurrentUserBySpecification(Long districtId, Long departmentId, String name, String code, Pageable pageable);
    // 判断某个部门是否对器具二级类别、精确度，测量范围拥有检定能
    Boolean getCheckAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId(Long accuracyId, Long minMeasureScaleId, Long maxMeasureScaleId, Long instrumentTypeId, Long departmentId) throws Exception;
    Boolean getCheckAbilityByAccuracyAndMinMeasureScaleAndMaxMeasureScaleAndInstrumentTypeAndDepartment(Accuracy accuracy, MeasureScale minMeasureScale, MeasureScale maxMeasureScale, InstrumentType instrumentType, Department department);

    // 获取一个持久化的实体
    DeviceSet getOneSavedDeviceSet();

}
