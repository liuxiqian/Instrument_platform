package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.Work;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

/**
 * Created by liming on 17-6-3.
 * 申请的Service
 */
public interface ApplyService {
    Work save(Work work);       // 保存工作

    Apply save(Apply apply);    // 保存


    //获取所有申请
    List<Apply> getAll();

    //获取一个申请
    Apply get(Long id);

    //删除一个申请
    void delete(Long id);

    Apply update(Long id, Apply apply) throws ObjectNotFoundException;  // 更新

    Apply getOneSavedApply();

    Apply getOneUnsavedApply();

    // 更新当前工作
    void updateCurrentWorkByApplyId(Long id, CurrentWork currentWork);

    void updateToDoneByApply(Apply apply);
}
