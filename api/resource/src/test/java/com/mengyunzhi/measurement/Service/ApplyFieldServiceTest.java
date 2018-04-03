package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.repository.ApplyFieldRepository;
import com.mengyunzhi.measurement.entity.ApplyType;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/11/7.
 */
public class ApplyFieldServiceTest extends ServiceTest{

    private static Logger logger = Logger.getLogger(ApplyTypeServiceTest.class.getName());

    @Autowired
    ApplyFieldService applyFieldService;
    @Autowired
    ApplyFieldRepository applyFieldRepository;
    @Autowired
    ApplyTypeRepository applyTypeRepository;
    @Test
    public void save() throws Exception {
        logger.info("新建一个申请字段实体");
        ApplyField applyField = new ApplyField();
        applyFieldService.save(applyField);

        ApplyField applyField1 = applyFieldRepository.findOne(applyField.getId());
        assertThat(applyField1).isNotNull();
    }

    @Test
    public void delete() throws Exception {
        logger.info("新建一个申请字段实体");
        ApplyField applyField = new ApplyField();
        applyFieldRepository.save(applyField);
        assertThat(applyFieldRepository.findOne(applyField.getId())).isNotNull();

        logger.info("删除该实体并断言为空");
        applyFieldService.delete(applyField.getId());
        assertThat(applyFieldRepository.findOne(applyField.getId())).isNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("新建一个申请字段实体");
        ApplyField applyField = new ApplyField();
        applyFieldRepository.save(applyField);
        List<ApplyField> applyFields = (List<ApplyField>)applyFieldService.getAll();
        assertThat(applyFields.size()).isGreaterThan(0);
    }

    @Test
    public void update() throws Exception {
        logger.info("新建一个申请字段实体");
        ApplyField applyField = new ApplyField();
        applyField.setName("name");
        applyFieldRepository.save(applyField);

        applyField.setName("hahaha");
        applyFieldService.update(applyField.getId(), applyField);
        assertThat(applyField.getName()).isEqualTo("hahaha");
    }

    @Test
    public void getAllByApplyTypeId() throws Exception {
        logger.info("新建一个申请类型实体");
        ApplyType applyType = new ApplyType();
        applyType.setName("zjh");
        applyTypeRepository.save(applyType);

        logger.info("新建一个申请字段实体");
        ApplyField applyField = new ApplyField();
        applyField.setName("1");
        applyField.setApplyType(applyType);
        applyFieldRepository.save(applyField);

        logger.info("再建立一个申请字段实体");
        ApplyField applyField1 = new ApplyField();
        applyField1.setName("2");
        applyField1.setApplyType(applyType);
        applyFieldRepository.save(applyField1);

        List<ApplyField> applyFields = applyFieldService.getAllByApplyTypeId(applyType.getId());
        assertThat(applyFields.size()).isEqualTo(2);
    }
}