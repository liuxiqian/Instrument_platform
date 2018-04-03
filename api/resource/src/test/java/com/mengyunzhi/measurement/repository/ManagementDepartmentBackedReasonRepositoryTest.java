package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.ManagementDepartmentBackedReason;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 退回理由实体仓库测试
 */
public class ManagementDepartmentBackedReasonRepositoryTest extends RepositoryTest {
    private static Logger logger = Logger.getLogger(ManagementDepartmentBackedReasonRepositoryTest.class.getName());

    @Autowired
    ManagementDepartmentBackedReasonRepository managementDepartmentBackedReasonRepository;

    @Test
    public void save() {
        logger.info("新建一个退回理由实体");
        ManagementDepartmentBackedReason managementDepartmentBackedReason = new ManagementDepartmentBackedReason();
        managementDepartmentBackedReasonRepository.save(managementDepartmentBackedReason);

        logger.info("断言");
        assertThat(managementDepartmentBackedReason.getId()).isNotNull();
    }
}
