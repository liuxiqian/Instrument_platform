package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by chuhang on 17-8-1.
 * 器具检定信息的实现
 */
@Service("InstrumentCheckInfoService")
public class InstrumentCheckInfoServiceImpl implements InstrumentCheckInfoService {
    public final static Logger logger = Logger.getLogger(InstrumentCheckInfoServiceImpl.class.getName());
    @Autowired InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    @Autowired MandatoryInstrumentService mandatoryInstrumentService;
    @Autowired
    MandatoryInstrumentCheckApplyService mandatoryInstrumentCheckApplyService;
    @Autowired
    MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;
    @Autowired @Qualifier("DepartmentService") DepartmentService departmentService; // 部门
    @Autowired CheckResultService checkResultService;       // 检定结果
    /**
     * 获取一个持久化的实体
     * @return
     * panjie
     */
    @Override
    public InstrumentCheckInfo getOneSavedInstrumentCheckInfo() {
        InstrumentCheckInfo instrumentCheckInfo = this.getOneUnSaveInstrumentCheckInfo();
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
        return instrumentCheckInfo;
    }

    /**
     * 获取一个未持久化的实体
     * @return
     * panjie
     */
    @Override
    public InstrumentCheckInfo getOneUnSaveInstrumentCheckInfo() {
        logger.debug("创建器具检定信息");

        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        instrumentCheckInfo.setCheckDate(new Date(Calendar.getInstance().getTimeInMillis()));

        logger.debug("强检器具及对应的申请");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneSavedMandatoryInstrument();
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = mandatoryInstrumentCheckApplyService.getOneSavedCheckApply();
        mandatoryInstrumentCheckApply.getMandatoryInstrumentSet().add(mandatoryInstrument);
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);

        Department department = departmentService.getOneSavedDepartment();
        instrumentCheckInfo.setCheckDepartment(department); // 检定部门

        CheckResult checkResult =checkResultService.getOneSavedCheckResult();
        instrumentCheckInfo.setCheckResult(checkResult);
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);
        instrumentCheckInfo.setMandatoryInstrumentCheckApply(mandatoryInstrumentCheckApply);
        return instrumentCheckInfo;

    }
}
