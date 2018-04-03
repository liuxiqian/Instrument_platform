package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.WorkflowType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-17.
 * 工作流类型
 */
public interface WorkflowTypeRepository extends CrudRepository<WorkflowType, Long> {
    WorkflowType findTopOneByName(String name);
    WorkflowType findOneByPinyin(String pinyin);
}
