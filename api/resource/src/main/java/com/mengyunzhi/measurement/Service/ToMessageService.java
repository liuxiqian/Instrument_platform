package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.ToMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created By chuhang on 17-10-17
 * 接受消息 service
 */
public interface ToMessageService {
    // 获取当前用户已发送信息的分页消息
    Page<ToMessage> pageAllOfCurrentUser(Pageable pageable);

    ToMessage get(Long id) throws EntityNotFoundException, SecurityException;

    // 将消息设置为已读
    void setMessageToRead(Long id);

    // 回复消息
    void reply(ToMessage toMessage);

    Page<ToMessage> pageReceiveUnReadToMessageOfCurrentUser(Pageable pageable);

    // 向当前用户所属市级管理部门发送消息
    ToMessage sendMessageToManagementDepartmentOfCurrentUser(ToMessage toMessage);

    // 批量删除消息
    void batchDelete(List<Integer> toMessageIds);

    // 向部门发送消息
    ToMessage sendMessageToDepartment(String content, String title, Department department);

    /**
     * 删除
     * @author chuhang
     */
    void delete(Long id);

}
