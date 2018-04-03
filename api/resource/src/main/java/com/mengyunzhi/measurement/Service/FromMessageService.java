package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.FromMessage;
import com.mengyunzhi.measurement.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created By chuhang on 17-10-17
 * 发送消息 service
 */
public interface FromMessageService {
    // 获取当前用户已发送信息的分页消息
    Page<FromMessage> pageAllOfCurrentUser(Pageable pageable);

    Message get(Long id) throws EntityNotFoundException, SecurityException;

    void delete(Long id) throws EntityNotFoundException, SecurityException;

    // 批量删除消息
    void batchDelete(List<Integer> fromMessageIds);
}
