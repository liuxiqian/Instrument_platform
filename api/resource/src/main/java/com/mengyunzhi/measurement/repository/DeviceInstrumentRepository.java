package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by 安强 on 2017/5/31.
 * 装置授权检定项目 实体仓库
 */
public interface DeviceInstrumentRepository extends CrudRepository<DeviceInstrument, Long>, JpaSpecificationExecutor<DeviceInstrument> {

    // 左连接查处所有的数据
    @Query("select deviceInstrument, deviceSet, instrumentType from DeviceSet deviceSet left JOIN deviceSet.deviceInstruments as deviceInstrument left join deviceInstrument.instrumentType as instrumentType  where deviceSet.department=:department and deviceInstrument is not null")
    Page<?> findAllByDepartmentOfDeviceInstrument(@Param("department") Department department, Pageable pageable);

    //获取管理部门所管辖的技术机构的授权检定项目
    @Query("select deviceInstrument, deviceSet, instrumentType from DeviceSet deviceSet left JOIN deviceSet.deviceInstruments as deviceInstrument left join deviceInstrument.instrumentType as instrumentType where deviceSet.department.departmentType.name='技术机构' and deviceSet.department.district in :districts and deviceInstrument is not null order by deviceSet.department")
    Page<?> findAllByDistricts(@Param("districts") List<District> districts, Pageable pageable);

    //左连接查处所有的数据
    @Query("SELECT deviceInstrument FROM #{#entityName} deviceInstrument left JOIN deviceInstrument.deviceSet deviceSet WHERE deviceSet.department = :department AND (deviceSet.id = :deviceSetId OR :deviceSetId = NULL)")
    Page<DeviceInstrument> findAllByDeviceSetDepartmentAndDeviceSetId(@Param("department") Department department, @Param("deviceSetId") Long deviceSetId, Pageable pageable);

    //左连接查处所有的数据
    @Query("SELECT deviceInstrument FROM #{#entityName} deviceInstrument left JOIN deviceInstrument.deviceSet deviceSet WHERE deviceSet.department = :department")
    Page<DeviceInstrument> findAllByDeviceSetDepartment(@Param("department") Department department, Pageable pageable);

    // 获取某个部门关于某二级器具类别的 实体
    @Query("SELECT deviceInstrument FROM #{#entityName} deviceInstrument WHERE deviceInstrument.instrumentType = :instrumentType AND deviceInstrument.deviceSet.department = :department")
    Iterable<DeviceInstrument> findAllByInstrumentTypeAndDeviceSetDepartment(@Param("instrumentType") InstrumentType instrumentType, @Param("department") Department department);

    DeviceInstrument findOneByDeviceSetAndInstrumentType(DeviceSet deviceSet, InstrumentType instrumentType);

    /**
     * 获取某个区域内的所有授权检定项目
     * @param district
     * @return
     * poshichao
     */
    @Query("SELECT deviceInstrument FROM #{#entityName} deviceInstrument WHERE deviceInstrument.deviceSet.department.district = :district")
    List<DeviceInstrument> findAllByDistrict(@Param("district") District district);
}
