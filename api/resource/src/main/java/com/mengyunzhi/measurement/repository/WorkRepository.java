package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.Work;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by liming on 17-5-22.
 * 工作
 */
public interface WorkRepository extends PagingAndSortingRepository<Work, Long>, JpaSpecificationExecutor {

//    /**
//     * 获取指定部门指定工作流类型名称的按申请进行分组的分页数据
//     * 取出申请类型为 applyTypeName 的；
//     * 审核部门是 department
//     * 同一申请中id最靠大的
//     * 按id进行逆序排序
//     * @param department
//     * @param pageable
//     * @return
//     * @author panjie
//     */
//    @Query("SELECT work FROM #{#entityName} work WHERE work.id = (SELECT MAX(work1.id) FROM #{#entityName} work1 WHERE work.apply = work1.apply AND work1.auditDepartment =:department) AND work.apply.applyType.flag =:applyTypeFlag ORDER BY work.id DESC")
//    Page<Work> pageByDepartmentAndApplyTypeFlagGroupByApply(@Param("department") Department department,
//                                                            @Param("applyTypeFlag") String applyTypeFlag,
//                                                            Pageable pageable);

    Work findOneByPreWork(Work work);

    List<Work> findAllByApplyId(Long applyId);

    /**
     * 获取某个申请对应的首工作结点
     * @param apply 申请
     * @return
     * panjie
     */
    Work findTopByApplyOrderByIdDesc(Apply apply);
}
