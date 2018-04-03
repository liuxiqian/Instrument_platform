package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.WorkflowNodeConfig;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by panjie on 17/10/9.
 * 工作流结点配置
 */
public interface WorkflowNodeConfigRepository extends CrudRepository<WorkflowNodeConfig, Long> {
}
