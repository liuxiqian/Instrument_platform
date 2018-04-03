package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.Message;
import com.mengyunzhi.measurement.entity.ToMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created By chuhang on 17-10-17
 * 接收消息 实体仓库
 */
public interface ToMessageRepository extends PagingAndSortingRepository<ToMessage, Long> {
    // 通过接收部门获取接收消息
    Page<ToMessage> findAllByToDepartment(Department department, Pageable pageable);

    void save(Message message);

    // 获取收件部门的未读消息
    Page<ToMessage> findAllByToDepartmentAndIsRead(Department department, Boolean isRead, Pageable pageable);
}
