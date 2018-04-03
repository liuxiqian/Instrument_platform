package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.ApplyFieldRecord;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class ApplyFieldRecordServiceImplTest extends ServiceTest {
    final static Logger logger = Logger.getLogger(ApplyFieldRecordServiceImplTest.class.getName());
    @Autowired
    ApplyFieldRecordService applyFieldRecordService;    // 申请字段记录

    @Test
    public void getByApplyAndApplyFieldName() throws Exception {
        ApplyFieldRecord applyFieldRecord = applyFieldRecordService.getOneSavedApplyFieldRecord();
        ApplyFieldRecord applyFieldRecord1 = applyFieldRecordService.getByApplyAndApplyFieldName(applyFieldRecord.getApply(), applyFieldRecord.getApplyField().getName());
        assertThat(applyFieldRecord1.getId()).isEqualTo(applyFieldRecord.getId());
    }

}