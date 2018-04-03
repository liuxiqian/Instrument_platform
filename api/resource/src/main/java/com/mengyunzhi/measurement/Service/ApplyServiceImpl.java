package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.Work;
import com.mengyunzhi.measurement.repository.ApplyRepository;
import com.mengyunzhi.measurement.repository.WorkRepository;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-6-3.
 * 申请Service层实现
 */
@Service("ApplyService")
public class ApplyServiceImpl implements ApplyService {
    private static final Logger logger = Logger.getLogger(ApplyServiceImpl.class.getName());
    @Autowired
    private ApplyRepository applyRepository;
    @Autowired
    private WorkRepository workRepository;   // 工作
    @Autowired
    private UserService userService; // 用户
    @Autowired
    ApplyTypeService applyTypeService;   // 申请类型
    @Autowired
    SMSService smsService;   // 短信
    @Autowired @Qualifier("WorkService") WorkService workService; // 工作

    @Override
    public Work save(Work work) {
        logger.info("保存工作对应的申请");
        this.save(work.getApply());
        logger.info("保存工作");
        workRepository.save(work);
        return work;
    }

    @Override
    public Apply save(Apply apply) {
        apply.setCreateUser(userService.getCurrentLoginUser());
        apply.setDepartment(userService.getCurrentLoginUser().getDepartment());
        return applyRepository.save(apply);
    }

    @Override
    public List<Apply> getAll() {
        List<Apply> applies = (List<Apply>) applyRepository.findAll();
        return applies;
    }

    @Override
    public Apply get(Long id) {
        return applyRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        applyRepository.delete(id);
        return;
    }

    @Override
    public Apply update(Long id, Apply apply) throws ObjectNotFoundException {
        Apply apply1 = applyRepository.findOne(id);
        if (null == apply1) {
            throw new ObjectNotFoundException(404, "要更新的申请实体不存在");
        }

        logger.info("更新基本的信息，其它的信息或自动更新，或是在创建的时候一经写入便不能更改");
        return applyRepository.save(apply1);
    }

    @Override
    public Apply getOneSavedApply() {
        Apply apply = this.getOneUnsavedApply();
        applyRepository.save(apply);
        return apply;
    }

    @Override
    public Apply getOneUnsavedApply() {
        Apply apply = new Apply();
        apply.setCreateUser(userService.getOneSavedUser());
        apply.setName("测试申请" + CommonService.getRandomStringByLength(10));
        return apply;
    }

    @Override
    public void updateCurrentWorkByApplyId(Long id, CurrentWork currentWork) {
        Apply apply = applyRepository.findOne(id);
        apply.setCurrentWork(currentWork);
        applyRepository.save(apply);
        return;
    }

    @Override
    public void updateToDoneByApply(Apply apply) {
        workService.updateToDoneByWork(apply.getCurrentWork().getWork());
    }
}