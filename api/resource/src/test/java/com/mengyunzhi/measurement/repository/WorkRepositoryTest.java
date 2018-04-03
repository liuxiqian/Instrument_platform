package com.mengyunzhi.measurement.repository;
import com.mengyunzhi.measurement.entity.Work;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-22.
 */
public class WorkRepositoryTest extends RepositoryTest{
    @Autowired
    private WorkRepository workRepository;
    static private Logger logger = Logger.getLogger(WorkRepositoryTest.class.getName());
    @Autowired private DepartmentRepository departmentRepository;       // 部门
    @Autowired protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository; // 强检器具申请
    @Autowired protected ApplyRepository applyRepository;   // 申请
    @Test
    public void save() {
        logger.info("新建一个工作");
        Work work = new Work();
        logger.info("保存");
        workRepository.save(work);
        logger.info("断言");
        assertThat(work.getId()).isNotNull();
    }
}