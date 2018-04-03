package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.MandatoryInstrumentCheckApply;
import com.mengyunzhi.measurement.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.AccessException;

import java.io.File;
import java.sql.Date;
import java.util.Set;

/**
 * Created by zhangjiahao
 * 检定申请
 */
public interface MandatoryInstrumentCheckApplyService {
    MandatoryInstrumentCheckApply findById(Long id);

    MandatoryInstrumentCheckApply getOneSavedCheckApply();

    MandatoryInstrumentCheckApply getOneUnSavedCheckApply();

    /**
     * 获取当前登录的器具用户发起的申请检定的分页信息
     *
     * @param pageable
     * @return
     */
    Page<Work> pageOfCurrentUser(Pageable pageable);

    Set<MandatoryInstrumentCheckApply> save(MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) throws IllegalArgumentException;

    /**
     * 获取当前技术机构的分页数据
     *
     * @param instrumentUserName 发起申请的器具用户
     * @param startApplyDate     开始时间
     * @param endApplyDate       截至时间
     * @param pageable           分页信息
     * @return panjie
     */
    Page<CurrentWork> pageOfCurrentUser(String instrumentUserName, Date startApplyDate, Date endApplyDate, Pageable pageable
    );

    /**
     * 回复检定申请
     *
     * @param id
     * @param mandatoryInstrumentCheckApply 检定申请
     *                   panjie
     */
    void auditById(Long id, MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) throws AccessException;

    /**
     * 根据检定申请id生成pdf文档
     *
     * @param checkApplyId 检定申请id
     * @return File            检定申请文件
     * @throws AccessException
     * @author zhangxishuo
     */
    File generatePdfApplyById(Long checkApplyId) throws AccessException;
}
