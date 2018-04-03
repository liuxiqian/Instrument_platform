package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import com.mengyunzhi.measurement.repository.FromMessageRepository;
import com.mengyunzhi.measurement.repository.ToMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created By chuhang on 17-10-17
 * 接收消息 接口实现
 */
@Service
public class ToMessageServiceImpl implements ToMessageService {
    static private Logger logger = Logger.getLogger(ToMessageServiceImpl.class.getName());

    @Autowired
    private ToMessageRepository toMessageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FromMessageRepository fromMessageRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;

    @Override
    public Page<ToMessage> pageAllOfCurrentUser(Pageable pageable) {
        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("通过接受部门获取接受消息");
        Page<ToMessage> toMessages = toMessageRepository.findAllByToDepartment(department, pageable);
        return toMessages;
    }

    @Override
    public ToMessage get(Long id) throws EntityNotFoundException, SecurityException {
        ToMessage toMessage = toMessageRepository.findOne(id);

        logger.info("判断实体是否存在");
        if (null == toMessage) {
            throw new EntityNotFoundException();
        }

        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("判断当前用户是否为消息的接受部门");
        if (toMessage.getToDepartment().getId() != department.getId()) {
            throw new SecurityException("您无此操作权限");
        }

        return toMessage;
    }

    @Override
    public void setMessageToRead(Long id) {
        ToMessage toMessage = toMessageRepository.findOne(id);

        logger.info("判断实体是否存在");
        if (null == toMessage) {
            throw new EntityNotFoundException();
        }

        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("判断当前用户是否为消息的接受部门");
        if (toMessage.getToDepartment().getId() == department.getId()) {
            logger.info("将消息设置为已读");
            toMessage.setIsRead(Boolean.TRUE);
            toMessageRepository.save(toMessage);
        }

        return;
    }

    @Override
    public void reply(ToMessage toMessage) {
        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("设置消息的发送部门为当前用户所在部门");
        toMessage.setFromDepartment(department);

        logger.info("将消息存入接受信息表中");
        toMessageRepository.save(toMessage);

        logger.info("将消息存入发送信息表中");
        FromMessage fromMessage = new FromMessage();
        fromMessage.setContent(toMessage.getContent());
        fromMessage.setTitle(toMessage.getTitle());
        fromMessage.setFromDepartment(department);
        fromMessage.setToDepartment(toMessage.getToDepartment());
        fromMessageRepository.save(fromMessage);

        return;
    }

    @Override
    public Page<ToMessage> pageReceiveUnReadToMessageOfCurrentUser(Pageable pageable) {
        logger.info("获取当前用户所在的部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("通过接受部门获取未读收件消息");
        Page<ToMessage> toMessages = toMessageRepository.findAllByToDepartmentAndIsRead(department, Boolean.FALSE, pageable);
        return toMessages;
    }

    @Override
    public ToMessage sendMessageToManagementDepartmentOfCurrentUser(ToMessage toMessage) {
        logger.info("获取当前用户所在的区域的父区域");
        Department department = userService.getCurrentLoginUser().getDepartment();
        District district = department.getDistrict().getParentDistrict();

        logger.info("获取当前区域的市级管理部门");
        DepartmentType departmentType = departmentTypeRepository.findOneByName("管理部门");
        Department department1 = departmentRepository.findTopOneByDistrictAndDepartmentType(district, departmentType);

        logger.info("将消息存入接受消息表中");
        toMessage.setFromDepartment(department);
        toMessage.setToDepartment(department1);
        toMessageRepository.save(toMessage);

        logger.info("将消息存入发送消息表中");
        FromMessage fromMessage = new FromMessage();
        fromMessage.setToDepartment(department1);
        fromMessage.setFromDepartment(department);
        fromMessage.setTitle(toMessage.getTitle());
        fromMessage.setContent(toMessage.getContent());
        fromMessageRepository.save(fromMessage);

        return toMessage;
    }

    @Override
    public void batchDelete(List<Integer> toMessageIds) {
        logger.info("删除接受消息");
        toMessageIds.forEach(list -> {
            toMessageRepository.delete(Long.valueOf(list));
        });

        return;
    }

    @Override
    public ToMessage sendMessageToDepartment(String content, String title, Department department) {
        logger.info("获取当前用户所在的部门");
        Department currentDepartment = userService.getCurrentLoginUser().getDepartment();

        logger.info("将消息存入接受消息表中");
        ToMessage toMessage = new ToMessage();
        toMessage.setFromDepartment(currentDepartment);
        toMessage.setToDepartment(department);
        toMessage.setContent(content);
        toMessage.setTitle(title);
        toMessageRepository.save(toMessage);

        logger.info("将消息存入发送消息表中");
        FromMessage fromMessage = new FromMessage();
        fromMessage.setFromDepartment(currentDepartment);
        fromMessage.setToDepartment(department);
        fromMessage.setContent(content);
        fromMessage.setTitle(title);
        fromMessageRepository.save(fromMessage);
        return toMessage;
    }

    @Override
    public void delete(Long id) {
        ToMessage toMessage = toMessageRepository.findOne(id);

        logger.info("判断实体是否存在");
        if (null == toMessage) {
            throw new EntityNotFoundException();
        }

        logger.info("获取当前用户所在部门");
        Department department = userService.getCurrentLoginUser().getDepartment();

        logger.info("判断当前用户是否为消息的发送部门");
        if (toMessage.getToDepartment().getId() != department.getId()) {
            throw new SecurityException("您无此操作权限");
        }

        logger.info("删除实体");
        toMessageRepository.delete(id);

        return;
    }

}
