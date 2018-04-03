package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.MandatoryInstrument;
import com.mengyunzhi.measurement.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liming on 17-7-6.
 * 强检器具
 */
public interface MandatoryInstrumentService {
    MandatoryInstrument findById(Long id);

    // 保存的同时，保存器具生产信息
    MandatoryInstrument saveWithInstrumentProduction(MandatoryInstrument mandatoryInstrument);

    MandatoryInstrument save(MandatoryInstrument mandatoryInstrument);

    // 获取一个强检器具
    MandatoryInstrument getOneSavedMandatoryInstrument();

    MandatoryInstrument getOneUnsavedMandatoryInstrument();

    // 计算某个部门对于强制检定数组中的强制检定实体是否具有检定能力
    List<MandatoryInstrument> computerCheckAbilityByDepartmentIdOfMandatoryInstruments(Long departmentId, Collection<MandatoryInstrument> mandatoryInstruments) throws Exception;

    /**
     * 更新最后一次的检定部门
     *
     * @param mandatoryInstruments 强检器具
     * @param departmentId         部门ID
     * @Author panjie
     */
    void updateLastCheckDepartmentByMandatoryInstrumentsAndDepartmentId(Collection<MandatoryInstrument> mandatoryInstruments, Long departmentId);

    //更新强检器具的检定周期和首次检定日期
    void updateCheckCycleAndFirstCheckDate(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException;

    //删除
    void delete(Long id) throws ObjectNotFoundException, SecurityException;

    //获取当前登录用户下的所有指定强检器具信息
    Page<MandatoryInstrument> pageAllOfCurrentUser(Pageable pageable);

    /**
     * 组合条件查询当前登录用户器具
     *
     * @param id
     * @param disciplineId               学科类别
     * @param instrumentFirstLevelTypeId 一级分类
     * @param instrumentTypeId           二级分类
     * @param audit                      是否已审核
     * @param name                       器具名称
     * @param overdue                    是否超期未检
     * @param status                     器具状态
     * @param pageable                   分页信息
     * @return
     * @authro panjie
     */
    Page<MandatoryInstrument> pageAllOfCurrentUserBySpecification(Long id, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Boolean overdue, Byte status, Pageable pageable);

    /**
     * 组合条件查询当前登录用户的超期未检器具（器具状态为正常)
     *
     * @param id
     * @param disciplineId               学科类别
     * @param instrumentTypeFirstLevelId 一级分类
     * @param instrumentTypeId           二级分类
     * @param name                       器具名称
     * @param pageable                   分页信息
     * @return
     */
    Page<MandatoryInstrument> pageOverdueDataOfCurrentUserBySpecification(Long id, Long disciplineId, Long instrumentTypeFirstLevelId, Long instrumentTypeId, String name, Pageable pageable);

    void update(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException;

    // 是否可以删除或是更新基本信息
    boolean canBeDeleteOrUpdate(MandatoryInstrument mandatoryInstrument);

    // 获取当前管理部门下的所有指定强检器具
    Page<MandatoryInstrument> pageAllOfManagementUser(User currentUser, Pageable pageable);

    //获取当前技术机构的所有强检器具使用信息
    List<MandatoryInstrument> getAllOfCurrentUser();

    // 获取当前部门中被管理部门指定检定的器具信息
    Page<MandatoryInstrument> pageByCheckDepartmentOfCurrentDepartment(Pageable pageable);

    void updateFixSite(Long id, MandatoryInstrument mandatoryInstrument) throws ObjectNotFoundException, SecurityException;

    /**
     * 综合查询当前登录的管理部门所辖区域内的强检器具信息
     *
     * @param id                         器具ID
     * @param districtId                 区域ID
     * @param departmentId               器具用户（部门）ID
     * @param checkDepartmentId          检定机构id
     * @param disciplineId               学科类别id
     * @param instrumentFirstLevelTypeId 一级类别ID
     * @param instrumentTypeId           二级类别ID
     * @param audit                      是否审核ID
     * @param name                       器具名称
     * @param overdue                    是否超期
     * @param status                     状态
     * @param pageable                   分页信息
     * @return
     * @Author panjie@yunzhiclub.com
     */
    Page<MandatoryInstrument> pageAllOfCurrentManageDepartmentBySpecification(Long id, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Boolean overdue, Byte status, Pageable pageable) throws SecurityException;

    /**
     * 根据传入的参数来导出EXECL表
     *
     * @param id
     * @param districtId
     * @param departmentId
     * @param checkDepartmentId
     * @param disciplineId
     * @param instrumentFirstLevelTypeId
     * @param instrumentTypeId
     * @param audit
     * @param name
     * @param overdue
     * @param status
     * @return panjie
     */
    File exportExcelOfCurrentManageDepartmentBySpecification(Long id, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Boolean overdue, Byte status) throws IOException;

    /**
     * 按查询条件导出当前登录的器具用户的强检器具
     *
     * @param id
     * @param disciplineId
     * @param instrumentFirstLevelTypeId
     * @param instrumentTypeId
     * @param audit
     * @param name
     * @param overdue
     * @param pageable
     * @return
     * @author chuhang
     */
    File exportExcelOfCurrentUserBySpecification(Long id, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Boolean audit, String name, Boolean overdue, Pageable pageable) throws IOException;

    /**
     * 按查询条件导出当前登录的技术机构的强检器具
     *
     * @param id
     * @param districtId
     * @param departmentId
     * @param disciplineId
     * @param instrumentFirstLevelTypeId
     * @param instrumentTypeId
     * @param name
     * @param isConfirmedByTechnologyDepartment
     * @param pageable
     * @return
     * @author chuhang
     */
    File exportExcelOfCurrentTechnicalInstitutionWithTokenBySpecification(Long id, Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String name, Boolean isConfirmedByTechnologyDepartment, Pageable pageable) throws IOException;

    Workbook getWorkbookByMandatoryInstruments(Page<MandatoryInstrument> mandatoryInstruments);

    /**
     * 当到达系统规定的最长确认时间后，自动为技术机构确认器具
     *
     * @param mandatoryInstruments
     */
    void autoConfirmWhenTimeOutByMandatoryInstruments(Page<MandatoryInstrument> mandatoryInstruments);

    /**
     * 当前器具，是否已经不允许手动被技术机构确认或是退回
     *
     * @param mandatoryInstrument
     * @return
     */
    Boolean validateIsOverdueForTechnologyConfirm(MandatoryInstrument mandatoryInstrument);

    /**
     * 获取允许退回器具的最大天数
     *
     * @return panjie
     */
    Integer getMaxAllBackDay();

    /**
     * 综合查询当前登录的技术机构被指定的强检器具信息。
     * 如果存在超期未确认的，自动触发确认操作
     *
     * @param id                         器具ID
     * @param districtId                 区域ID
     * @param departmentId               部门ID （器具用户）
     * @param disciplineId               学科类虽
     * @param instrumentFirstLevelTypeId 一级类别
     * @param instrumentTypeId           二级类别
     * @param name                       器具名称
     * @param pageable
     * @return
     */
    Page<MandatoryInstrument> pageAllOfCurrentTechnicalInstitutionBySpecification(Long id, Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String name, Boolean isConfirmedByTechnologyDepartment, Pageable pageable);

    List<District> getRightDistrictsByRootDistrictAndSonDistrictId(District rootDistrict, Long sonDistrictId);

    /**
     * 设置状态为 被退回
     *
     * @param id 强检器具ID
     */
    void setStatusToBackById(Long id);

    /**
     * 批量确认已指定给该技术机构的强检器具
     *
     * @param mandatoryInstruments 强检器具
     *                             panjie
     */
    void batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser(Set<MandatoryInstrument> mandatoryInstruments);

    /**
     * 根据SET集合查找出持久化的数据
     *
     * @param mandatoryInstrumentCollection 强检器具集
     * @return
     */
    Iterable<MandatoryInstrument> findAllByMandatoryInstrumentCollection(Collection<MandatoryInstrument> mandatoryInstrumentCollection);

    // 将强检器具按部门进行分类
    Map<Department, List<MandatoryInstrument>> classificationByDepartment(List<MandatoryInstrument> mandatoryInstruments);

    // 当退回强检器具向器具用户发送站内消息
    void sendMessageToMeasurementUser(Department department, List<MandatoryInstrument> mandatoryInstruments);

    // 当退回强检器具向管理部门发送站内消息
    void sendMessageToManagementDepartment(Department department, List<MandatoryInstrument> mandatoryInstruments);

    /**
     * 获取当前登录用户的超期预警工作
     *
     * @return
     */
    Page<MandatoryInstrument> getAllCurrentUserWorkOfOverTimeWarn(Pageable pageable);

    /**
     * 获取当前登录用户的超期工作
     *
     * @return
     */
    Page<MandatoryInstrument> getAllCurrentUserWorkOfOverTime(Pageable pageable);

    // 获取所有的生产部们
    List<Department> getAllGenerativeDepartment();
}