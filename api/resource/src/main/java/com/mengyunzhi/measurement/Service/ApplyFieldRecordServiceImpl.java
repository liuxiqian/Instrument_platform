package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.entity.ApplyFieldRecord;
import com.mengyunzhi.measurement.entity.ApplyType;
import com.mengyunzhi.measurement.repository.ApplyFieldRecordRepository;
import com.mengyunzhi.measurement.repository.ApplyFieldRepository;
import com.mengyunzhi.measurement.repository.ApplyRepository;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ApplyFieldRecordServiceImpl implements ApplyFieldRecordService {
    final static Logger logger = Logger.getLogger(ApplyFieldRecordServiceImpl.class.getName());
    @Autowired
    ApplyRepository applyRepository;    // 申请
    @Autowired ApplyFieldRecordRepository applyFieldRecordRepository;

    @Qualifier("ApplyService")
    @Autowired ApplyService applyService;   // 申请

    @Autowired ApplyFieldService applyFieldService; // 申请字段
    @Autowired
    ApplyFieldRepository applyFieldRepository;  // 申请字段

    @Autowired
    ApplyTypeRepository applyTypeRepository;    // 申请类型

    @Autowired ApplyTypeService applyTypeService;

    /**
     * 获取指定的某条记录
     * @param apply 申请
     * @param applyFieldName 申请字段
     * @return
     */
    @Override
    public ApplyFieldRecord getByApplyAndApplyFieldName(Apply apply, String applyFieldName) {
        ApplyType applyType = applyTypeService.findOneByName(apply.CLASS_NAME);
        ApplyFieldRecord applyFieldRecord = applyFieldRecordRepository.findByApplyAndApplyTypeAndApplyFieldName(apply, applyType, applyFieldName);
        return applyFieldRecord;
    }

    @Override
    public String getValueByApplyAndApplyFieldName(Apply apply, String applyFieldName) {
        ApplyFieldRecord applyFieldRecord = this.getByApplyAndApplyFieldName(apply, applyFieldName);
        if (applyFieldRecord == null) {
            return "";
        } else {
            return applyFieldRecord.getValue();
        }
    }

    @Override
    public ApplyFieldRecord getOneUnSaveApplyFieldRecord() {
        logger.info("获取一个申请");
        Apply apply = applyService.getOneSavedApply();

        logger.info("获取一个申请字段");
        ApplyField applyField = applyFieldService.getOneSavedApplyField();
        applyField.setApplyType(applyTypeService.findOneByName(apply.CLASS_NAME));
        applyFieldRepository.save(applyField);

        logger.info("设置属性并返回");
        ApplyFieldRecord applyFieldRecord = new ApplyFieldRecord();
        applyFieldRecord.setApply(apply);
        applyFieldRecord.setApplyField(applyField);
        return applyFieldRecord;
    }


    @Override
    public ApplyFieldRecord getOneSavedApplyFieldRecord() {
        ApplyFieldRecord applyFieldRecord = this.getOneUnSaveApplyFieldRecord();
        applyFieldRecordRepository.save(applyFieldRecord);
        return applyFieldRecord;
    }
}
