package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.ApplyTypeService;
import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.entity.ApplyFieldRecord;
import com.mengyunzhi.measurement.entity.ApplyType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/10/9.
 */
public class ApplyFieldRecordRepositoryTest extends RepositoryTest {


    final static Logger logger = Logger.getLogger(ApplyFieldRecordRepositoryTest.class.getName());
    @Autowired
    private ApplyFieldRecordRepository applyFieldRecordRepository;
    @Autowired private ApplyRepository applyRepository;
    @Autowired private ApplyTypeRepository applyTypeRepository;
    @Autowired private ApplyFieldRepository applyFieldRepository;
    @Autowired
    ApplyTypeService applyTypeService;       // 申请类型
    @Test
    public void findAllTest() {
        assertThat(applyFieldRecordRepository.findAll()).isNotNull();
    }

    @Test
    public void findByApplyAndApplyTypeAndApplyFieldName() throws Exception {
        Apply apply = new Apply();
        applyRepository.save(apply);
        ApplyType applyType = applyTypeService.findByApply(apply);

        logger.info("获取一个申请字段");
        ApplyField applyField = new ApplyField();
        applyField.setApplyType(applyType);
        applyField.setName("YzpXe2Xk7pdE05xqUiVF");
        applyFieldRepository.save(applyField);

        logger.info("设置属性并返回");
        ApplyFieldRecord applyFieldRecord = new ApplyFieldRecord();
        applyFieldRecord.setApply(apply);
        applyFieldRecord.setApplyField(applyField);
        applyFieldRecordRepository.save(applyFieldRecord);

        ApplyFieldRecord applyFieldRecord1 = applyFieldRecordRepository.findByApplyAndApplyTypeAndApplyFieldName(applyFieldRecord.getApply(), applyType, applyFieldRecord.getApplyField().getName());
        assertThat(applyFieldRecord1.getId()).isEqualTo(applyFieldRecord.getId());
    }
}