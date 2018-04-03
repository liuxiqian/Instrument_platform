package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.FromMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created By chuhang on 17-10-17
 * 发送消息 实体仓库
 */
public interface FromMessageRepository extends PagingAndSortingRepository<FromMessage, Long> {
    // 通过发送部门获取发送消息
    Page<FromMessage> findAllByFromDepartment(Department department, Pageable pageable);
}
