package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.ApplyType;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/28.
 */
public class ApplyTypeServiceTest extends ServiceTest {
    private static Logger logger = Logger.getLogger(ApplyTypeServiceTest.class.getName());

    @Autowired ApplyTypeService applyTypeService;   // 申请类型
    @Autowired ApplyTypeServiceTestData applyTypeServiceTestData;
    @Autowired
    ApplyTypeRepository applyTypeRepository;    // 申请类型
    @Test
    public void getOneByName() {
        ApplyType applyType = applyTypeService.getOneSavedApplyType();
        ApplyType applyType1 = applyTypeService.getOneByName(applyType.getName());
        assertThat(applyType1.getId()).isEqualTo(applyType.getId());
    }

    @Test
    public void save() throws Exception {
        logger.info("保存方法测试");
        ApplyType applyType = new ApplyType();
        applyTypeRepository.save(applyType);

        ApplyType applyType1 = applyTypeRepository.findOne(applyType.getId());
        logger.info("断言新增成功");
        assertThat(applyType1).isNotNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("更新方法测试");
        logger.info("新增一个申请类型实体");
        ApplyType applyType = new ApplyType();
        applyType.setName("name");
        applyTypeRepository.save(applyType);

        logger.info("更新这个申请类型实体");
        applyType.setName("hello");
        applyTypeService.update(applyType.getId(), applyType);

        logger.info("断言修改成功");
        assertThat(applyType.getName()).isEqualTo("hello");
        applyTypeRepository.delete(applyType);
    }

    @Test
    public void delete() throws Exception {
        logger.info("删除方法测试");
        ApplyType applyType = new ApplyType();
        applyTypeRepository.save(applyType);

        logger.info("删除该实体");
        applyTypeService.delete(applyType.getId());
        ApplyType applyType1 = applyTypeRepository.findOne(applyType.getId());
        assertThat(applyType1).isNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("获取全部数据方法测试");

        ApplyType applyType = new ApplyType();
        applyTypeRepository.save(applyType);
        List<ApplyType> applyTypes = (List<ApplyType>) applyTypeRepository.findAll();
        assertThat(applyTypes.size()).isGreaterThan(0);
        applyTypeRepository.delete(applyType.getId());

    }

}
