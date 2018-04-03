package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.OverdueCheckApply;
import com.mengyunzhi.measurement.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;

/**
 * @author panjie on 2017/12/12
 */
public interface OverdueCheckApplyService extends ApplyService {
    OverdueCheckApply save(OverdueCheckApply overdueCheckApply);    // 保存

    OverdueCheckApply getOneUnsavedOverdueCheckApply();

    /**
     * 回复
     *
     * @param work 工作
     *             panjie
     */
    void reply(Work work);

    /**
     * 分页数据
     * @param startApplyDate 开始日期
     * @param endApplyDate 结束日期
     * @param applyDepartmentId 申请用户ID
     * @param pageable 分页信息
     * @return
     * @author panjie
     */
    Page<CurrentWork> pageOfCurrentUser(Date startApplyDate, Date endApplyDate, Long applyDepartmentId, Pageable pageable);

}
