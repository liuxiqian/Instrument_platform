package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author panjie on 2018/1/17
 * 当前工作
 */
public interface CurrentWorkRepository extends PagingAndSortingRepository<CurrentWork, Long>, JpaSpecificationExecutor {
    // 获取某部门在某个申请上的当前工作
    @Query("SELECT c FROM #{#entityName} c WHERE c.work.apply = :apply AND c.work.auditDepartment = :auditDepartment")
    CurrentWork findByApplyAndCheckDepartment(@Param("apply") Apply apply, @Param("auditDepartment") Department auditDepartment);

    CurrentWork findByWork(Work work);

    /**
     * 查询某个部门上某种申请类型的工作列表
     *
     * @param workAuditDepartment 审核部门
     * @param workApplyClassName  申请类型
     * @param pageable
     * @return
     * @Author panjie
     */
    @Query("SELECT c.work FROM #{#entityName} c WHERE c.work.auditDepartment = :workAuditDepartment AND c.work.apply.class = :workApplyClassName ORDER BY c.id DESC ")
    Page<Work> pageWorkByWorkAuditDepartmentAndWorkApplyClassName(@Param("workAuditDepartment") Department workAuditDepartment,
                                                                  @Param("workApplyClassName") String workApplyClassName,
                                                                  Pageable pageable);

    /**
     * 获取某个部门的审核列表
     *
     * @param auditDepartment 审核部门
     * @param pageable        分页
     * @return
     * @Author panjie
     */
    @Query("SELECT c.work.apply FROM #{#entityName} c WHERE c.work.auditDepartment = :auditDepartment")
    Page<Apply> pageApplyByWorkAuditDepartment(@Param("auditDepartment") Department auditDepartment, Pageable pageable);

    /**
     * 获取某部门的某个审核状态的所有数据
     *
     * @param auditDepartment 审核部门
     * @param status          状态
     * @return
     * @author panjie
     */
    @Query("SELECT c.work FROM #{#entityName} c WHERE c.work.auditDepartment = :auditDepartment AND c.work.status = :status")
    List<Work> findAllByWorkAuditDepartmentAndStatus(
            @Param("auditDepartment") Department auditDepartment, @Param("status") Byte status);

    /**
     * 获取某部门某个审核状态的分页数据
     * @param auditDepartment 审核部门
     * @param status 状态
     * @param pageable 分页数据
     * @return
     * @Author panjie
     */
    @Query("SELECT c.work FROM #{#entityName} c WHERE c.work.auditDepartment = :auditDepartment AND c.work.status = :status")
    Page<Work> pageWorkByWorkAuditDepartmentAndStatus(
            @Param("auditDepartment") Department auditDepartment, @Param("status") Byte status, Pageable pageable);

    /**
     * 获取分页数据
     * @param auditDepartment 审核部门
     * @param status 状态
     * @param workApplyClassName 申请类型
     * @param pageable 分页
     * @return
     * @Author
     */
    @Query("SELECT c.work FROM #{#entityName} c WHERE c.work.auditDepartment = :auditDepartment AND c.work.status = :status AND c.work.apply.class = :workApplyClassName")
    Page<Work> pageWorkByWorkAuditDepartmentAndStatusAndWorkApplyClassName(
            @Param("auditDepartment") Department auditDepartment,
            @Param("status") Byte status,
            @Param("workApplyClassName") String workApplyClassName,
            Pageable pageable);

    @Query("SELECT c FROM #{#entityName} c WHERE c.work.apply = :workApply")
    List<CurrentWork> findAllByApply(@Param("workApply") Apply workApply);

}
