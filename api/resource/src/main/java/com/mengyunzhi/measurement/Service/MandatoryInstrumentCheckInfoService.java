package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.InstrumentCheckInfo;
import com.mengyunzhi.measurement.entity.User;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.AccessException;

import java.util.List;

/**
 * Created by chuhang on 17-8-2.
 * 强制器具检定信息的service
 */
public interface MandatoryInstrumentCheckInfoService extends InstrumentCheckInfoService {
    // 检定机构发起的新增检定记录
    void saveViaTechnologyDepartment(InstrumentCheckInfo instrumentCheckInfo) throws AccessException;
    // 申请器具用户发起的新增检定记录
    void saveViaApplyInstrumentUserDepartment(InstrumentCheckInfo instrumentCheckInfo) throws AccessException;

    Page<InstrumentCheckInfo> pageAllOfCurrentUser(Pageable pageable);

    void delete(Long id) throws ObjectNotFoundException, SecurityException, AccessException;

    void update(Long id, InstrumentCheckInfo instrumentCheckInfo);

    //获取一个强检器具的所有检定信息
    Page<InstrumentCheckInfo> pageAllByMandatoryInstrumentId(Long mandatoryInstrumentId, Pageable pageable);

    //获取管理部门旗下的所有器具用户的检定信息
    Page<InstrumentCheckInfo> pageAllOfManagementUser(User currentUser, Pageable pageable);

    /**
     * 获取分页数据
     * @param year 年度
     * @param districts 区域
     * @param applyDepartmentId 申请部门ID
     * @param checkDepartmentId 审核部门ID
     * @param disciplineId 学科类别id
     * @param instrumentFirstLevelTypeId 一级类别
     * @param instrumentTypeId （二级）类别
     * @param certificateNum 证书编号
     * @param checkResultId 检定结果
     * @param mandatoryInstrumentId 强检器具
     * @param name 器具名称
     * @param accuracyId 精确度
     * @param minMeasuringScaleWeight 最小测量范围权重
     * @param maxMeasuringScaleWeight 最大测量范围权重
     * @param pageable 分页信息
     * @return
     * @panjie
     */
    Page<InstrumentCheckInfo> pageAllBySpecification(Integer year,
                                                     List<District> districts,
                                                     Long applyDepartmentId,
                                                     Long checkDepartmentId,
                                                     Long disciplineId,
                                                     Long instrumentFirstLevelTypeId,
                                                     Long instrumentTypeId,
                                                     String certificateNum,
                                                     Long checkResultId,
                                                     Long mandatoryInstrumentId,
                                                     String name,
                                                     Long accuracyId,
                                                     Integer minMeasuringScaleWeight,
                                                     Integer maxMeasuringScaleWeight,
                                                     Pageable pageable);
    /**
     * 根据查询条件获取管理部门的强检器具鉴定信息
     * @param year  年度
     * @param districtId 区域
     * @param departmentId 器具用户
     * @param checkDepartmentId 检定部门
     * @param disciplineId 学科类别
     * @param instrumentFirstLevelTypeId 一级类别
     * @param instrumentTypeId 二级类别
     * @param certificateNum 证书编号
     * @param checkResultId 检定结果
     * @param mandatoryInstrumentId 强检器具ID
     * @param name 器具名称
     * @param accuracyId 精确度
     * @param minMeasuringScaleWeight 最小测量范围
     * @param maxMeasuringScaleWeight 最大测量范围
     * @param pageable 分页
     * @return
     * @author panjie
     * @throws SecurityException
     */
    Page<InstrumentCheckInfo> pageAllOfManagementDepartmentBySpecification(Integer year,
                                                                           Long districtId,
                                                                           Long departmentId,
                                                                           Long checkDepartmentId,
                                                                           Long disciplineId,
                                                                           Long instrumentFirstLevelTypeId,
                                                                           Long instrumentTypeId,
                                                                           String certificateNum,
                                                                           Long checkResultId,
                                                                           Long mandatoryInstrumentId,
                                                                           String name,
                                                                           Long accuracyId,
                                                                           Integer minMeasuringScaleWeight,
                                                                           Integer maxMeasuringScaleWeight,
                                                                           Pageable pageable) throws SecurityException;

    // 根据查询条件获取器具用户的强检器具鉴定信息
    Page<InstrumentCheckInfo> pageAllOfMeasureUserBySpecification(Integer year, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Long checkResultId, Long mandatoryInstrumentId, String name, Pageable pageable);

    // 根据查询条件获取技术机构的强检器具鉴定信息
    Page<InstrumentCheckInfo> pageAllOfTechnicalInstitutionDepartmentBySpecification(Integer year, Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String certificateNum, Long checkResultId, Long accuracyId, Long mandatoryInstrumentId, String name, Pageable pageable);
}
