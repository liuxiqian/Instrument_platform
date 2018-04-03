package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/10/9.
 * 工作流结点-申请字段 权限
 */
public class WorkflowNodeAndApplyFieldAccessRepositoryTest extends RepositoryTest{
    @Autowired
    private WorkflowNodeAndApplyFieldAccessRepository workflowNodeAndApplyFieldAccessRepository;

    @Test
    public void findAllTest() {
        assertThat(workflowNodeAndApplyFieldAccessRepository.findAll()).isNotNull();
    }
}