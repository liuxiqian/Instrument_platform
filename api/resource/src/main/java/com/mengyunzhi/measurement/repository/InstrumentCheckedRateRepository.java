package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.InstrumentCheckedRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 器具受检率统计 仓库
 * @Author 朴世超
 */
public interface InstrumentCheckedRateRepository extends CrudRepository<InstrumentCheckedRate, Long>, JpaSpecificationExecutor {
    /**
     * 获取某年所有记录
     * @param year 年份
     * @return
     */
    List<InstrumentCheckedRate> findAllByYear(Short year);

    /**
     * 根据年度与是否检定获取信息
     * @param year    年度
     * @param checked 是否检定
     * zhangxishuo
     */
    List<InstrumentCheckedRate> findAllByYearAndChecked(short year, boolean checked);

    /**
     * 获取某年总记录数
     * @param year 年份
     * @return
     */
    Long countByYear(Short year);

    /**
     * 获取某年分页数据
     * @param year          年份
     * @param pageRequest   分页
     * @return
     */
    Page<InstrumentCheckedRate> findAllByYear(Short year, org.springframework.data.domain.Pageable pageRequest);
}