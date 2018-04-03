package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;

/**
 * Created by panjie on 17/7/16.
 * 强制检定工作
 */
public interface MandatoryInstrumentWorkService extends WorkService {
    Page<Work> pageOfCurrentLoginUser(Pageable pageable);

    //获得待办工作
    Page<Work> pageOfCurrentLoginUserOfToDo(Pageable pageable);

    //获取在办工作
    Page<Work> pageOfCurrentLoginUserOfDoing(Pageable pageable);

    //获取办结工作
    Page<Work> pageOfCurrentLoginUserOfDone(Pageable pageable);

    //获得已办工作
    Page<Work> pageOfCurrentLoginUserOfNotToDo(Pageable pageable);

    // 当前用户的查询信息
    Page<CurrentWork> pageAllOfCurrentLoginUserBySpecification(Long departmentId, Date applyStartDate, Date applyEndDate, Pageable pageable);

}
