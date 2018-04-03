package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.StandardDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by liming on 17-5-9.
 * 标准器实体仓库
 */
public interface StandardDeviceRepository extends CrudRepository<StandardDevice, Long> {
    //通过标准装置id获取相应的标准器
    List<StandardDevice> findAllByDeviceSetId(Long deviceSetId);

    //通过标准装置id获取相应的标准器分页功能
    Page<StandardDevice> findAllByDeviceSetId(Long deviceSetId, Pageable pageable);

    /**
     * 查询某部门在起始日至结束期间标准器的的分页数据
     *
     * @param nextDay
     * @param department
     * @param pageable
     * @return
     * zhuchenshu
     */
    @Query("select i from #{#entityName} i" +
            " where i.deviceSet.department = :department " +
            " and i.nextCheckDate between current_date() and :nextDay ")
    Page<StandardDevice> findAllByDepartmentAndNextCheckDateBetweenTodayAndNextDay(@Param("nextDay") Date nextDay, @Param("department") Department department, Pageable pageable);
    /**
     * 查询某部门标准器的的报警数据分页数据
     * @param department
     * @param pageable
     * @return
     * zhuchenshu
     */
    @Query("select i from #{#entityName} i " +
            "where i.nextCheckDate < current_date() and i.deviceSet.department = :department")
    Page<StandardDevice> findAllDepartmentAndByNextCheckDateBeforeToday(@Param("department") Department department, Pageable pageable);
}
