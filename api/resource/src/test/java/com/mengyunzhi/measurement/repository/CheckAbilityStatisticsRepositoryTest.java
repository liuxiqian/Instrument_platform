package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.CheckAbilityStatistics;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.Discipline;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.specs.CheckAbilityStatisticsSpecs;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 器具受检能力统计 仓库测试
 * @Author poshichao
 */
public class CheckAbilityStatisticsRepositoryTest extends RepositoryTest {
    private final static Logger logger = Logger.getLogger(CheckAbilityStatisticsRepositoryTest.class);
    @Autowired
    private CheckAbilityStatisticsRepository checkAbilityStatisticsRepository;  // 器具检定能力
    @Autowired
    private DisciplineRepository disciplineRepository;      // 学科类别
    @Autowired
    private DistrictRepository districtRepository;          // 区域
    @Autowired
    private DepartmentRepository departmentRepository;      // 部门
    @Test
    public void findAllTest() throws Exception {
        logger.info("新建一个器具受检能力对象");
        CheckAbilityStatistics checkAbilityStatistics = new CheckAbilityStatistics();
        logger.info("持久化");
        checkAbilityStatisticsRepository.save(checkAbilityStatistics);
        logger.info("获取所有数据");
        Iterable<CheckAbilityStatistics> checkAbilityStatisticsList = checkAbilityStatisticsRepository.findAll();
        logger.info("段言获取到的信息不为空");
        assertThat(checkAbilityStatisticsList).isNotNull();
    }
}