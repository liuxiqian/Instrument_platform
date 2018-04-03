package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.FromMessage;
import com.mengyunzhi.measurement.repository.FromMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created By chuhang on 17-10-17
 * 收件消息 接口实现
 */
@Service
public class FromMessageServiceImpl implements FromMessageService {
    static private Logger logger = Logger.getLogger(FromMessageServiceImpl.class.getName());

    @Autowired
    private FromMessageRepository fromMessageRepository;
    @Autowired
    private UserService userService;

    @Override
    public Page<FromMessage> pageAllOfCurrentUser(Pageable pageable) {
        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("通过发送部门获取发送消息");
        Page<FromMessage> fromMessages = fromMessageRepository.findAllByFromDepartment(department, pageable);
        return fromMessages;
    }

    @Override
    public FromMessage get(Long id) throws EntityNotFoundException, SecurityException {
        FromMessage fromMessage = fromMessageRepository.findOne(id);

        logger.info("判断实体是否存在");
        if (null == fromMessage) {
            throw new EntityNotFoundException();
        }

        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("判断当前用户是否为消息的发送部门");
        if (fromMessage.getFromDepartment().getId() != department.getId()) {
            throw new SecurityException("您无此操作权限");
        }

        return fromMessage;
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, SecurityException {
        FromMessage fromMessage = fromMessageRepository.findOne(id);

        logger.info("判断实体是否存在");
        if (null == fromMessage) {
            throw new EntityNotFoundException();
        }

        logger.info("获取当前用户所在部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("判断当前用户是否为消息的发送部门");
        if (fromMessage.getFromDepartment().getId() != department.getId()) {
            throw new SecurityException("您无此操作权限");
        }

        logger.info("删除实体");
        fromMessageRepository.delete(id);

        return;
    }

    @Override
    public void batchDelete(List<Integer> fromMessageIds) {
        logger.info("删除接受消息");
        fromMessageIds.forEach(list -> {
            fromMessageRepository.delete(Long.valueOf(list));
        });

        return;
    }
}
