package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by chuhang on 17-5-23.
 * 强检器具
 * 实现了JpaSpecificationExecutor，则自动增加：
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#specifications
 */
public interface MandatoryInstrumentRepository extends PagingAndSortingRepository<MandatoryInstrument, Long>, JpaSpecificationExecutor {

    Page<MandatoryInstrument> findAllByLastCheckDepartment(Department lastCheckDepartment, Pageable pageable);

    Page<MandatoryInstrument> findAllByDepartment(Department department, Pageable pageable);

    @Query("select i from #{#entityName} i where i.lastCheckDepartment.district in :districts")
    Page<MandatoryInstrument> findAllByDistricts(@Param("districts") List<District> districts, Pageable pageable);

    /**
     * 查询某部门拥有的超期未检的分页数据
     *
     * @param status        器具状态
     * @param department    拥有的部门
     * @param nextCheckDate 下次检定日期
     * @param pageable      分页
     * @return
     * @author panjie
     */
    Page<MandatoryInstrument> findAllByStatusAndDepartmentAndNextCheckDateBefore(Byte status, Department department, Date nextCheckDate, Pageable pageable);

    /**
     * 查询某部门在起始日至结束期间检定的的分页数据
     * @param statusNormal
     * @param department
     * @param beginDate
     * @param endDate
     * @param pageable
     * @return
     */
    Page<MandatoryInstrument> findAllByStatusAndDepartmentAndNextCheckDateBetween(Byte statusNormal, Department department, Date beginDate, Date endDate, Pageable pageable);

    /**
     * 根据器具状态查询器具
     * @param status
     * zhangxishuo
     */
    List<MandatoryInstrument> findAllByStatus(Byte status);

    /**
     * 根据器具状态查询器具的分页信息
     * @param status
     * zhangxishuo
     */
    Page<MandatoryInstrument> findAllByStatus(Byte status, Pageable pageable);

    List<MandatoryInstrument> findAllByLastCheckDepartment(Department department);

    MandatoryInstrument findByName(String name);

    List<MandatoryInstrument> findAllByName(String name);
    // 获取所有的强检器具生产
    @Query("select i.generativeDepartment from #{#entityName} i")
    List<Department> findAllGenerativeDepartment();

    /**
     * 获取某个区域内的所有器具
     * @param district
     * @return
     * poshichao
     */
    @Query("select i from #{#entityName} i where i.department.district = :district")
    List<MandatoryInstrument> findAllByDistrict(@Param("district") District district);

    @Query("select count(i) from #{#entityName} i where i.accuracy = :accuracy and i.instrumentType = :instrumentType and i.department.district.id = :districtId")
    int countByAccuracyAndInstrumentTypeAndDistrict(@Param("accuracy") Accuracy accuracy, @Param("instrumentType") InstrumentType instrumentType, @Param("districtId") Long districtId);
}
