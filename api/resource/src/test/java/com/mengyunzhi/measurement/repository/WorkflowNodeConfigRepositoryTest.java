package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/10/9.
 * 工作流结点配置
 */
public class WorkflowNodeConfigRepositoryTest extends RepositoryTest {
    @Autowired
    private WorkflowNodeConfigRepository workflowNodeConfigRepository;
    @Test
    public void getTest() {
        assertThat(workflowNodeConfigRepository.findAll()).isNotNull();
    }
}