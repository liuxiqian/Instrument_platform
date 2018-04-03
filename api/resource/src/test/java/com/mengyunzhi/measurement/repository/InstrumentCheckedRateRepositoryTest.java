package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.Service.DistrictService;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.InstrumentCheckedRate;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 器具受检率统计 单元测试
 * @Author 朴世超
 */
public class InstrumentCheckedRateRepositoryTest extends RepositoryTest {
    static private Logger logger = Logger.getLogger(InstrumentCheckedRateRepositoryTest.class);
    private static final short year = 2018;
    @Autowired
    private InstrumentCheckedRateRepository instrumentCheckedRateRepository;
    @Autowired
    private DistrictService districtService;                                    // 区域

    @Test
    public void findAllTest() {
        logger.info("新增一个器具受检率统计实体");
        InstrumentCheckedRate instrumentCheckedRate = new InstrumentCheckedRate();

        logger.info("查找所有实体");
        Iterable<InstrumentCheckedRate> instrumentCheckedRates = instrumentCheckedRateRepository.findAll();

        logger.info("断言结果不为空");
        assertThat(instrumentCheckedRates).isNotNull();
    }

    @Test
    public void findInstrumentCheckedRatesByYear() throws Exception {
        logger.info("创建一个器具受检对象");
        InstrumentCheckedRate instrumentCheckedRate = new InstrumentCheckedRate();
        instrumentCheckedRate.setYear((short)2018);
        instrumentCheckedRateRepository.save(instrumentCheckedRate);
        logger.info("获取一年中所有的器具受检信息的对象");
        List<InstrumentCheckedRate> instrumentCheckedRates = instrumentCheckedRateRepository.findAllByYear((short)2018);
        logger.info("断言数组大小不为0");
        assertThat(instrumentCheckedRates.size()).isNotZero();
    }

    @Test
    public void countPreYearNumber() throws Exception {
        logger.info("创建多条数据并持久化");
        for (int i = 0; i < 100; i++) {
            InstrumentCheckedRate instrumentCheckedRate = new InstrumentCheckedRate();
            instrumentCheckedRate.setYear((short)2018);
            instrumentCheckedRateRepository.save(instrumentCheckedRate);
        }

        logger.info("添加一个不同年份的对象并持久化");
        InstrumentCheckedRate instrumentCheckedRate = new InstrumentCheckedRate();
        instrumentCheckedRate.setYear((short)2017);
        instrumentCheckedRateRepository.save(instrumentCheckedRate);

        logger.info("获取年份为2018的数据条数");
        Long count = instrumentCheckedRateRepository.countByYear((short)2018);
        logger.info("断言数据条数为100");
        assertThat(count).isEqualTo(100);
    }

    @Test
    public void getByYear() throws Exception {
        logger.info("创建多条数据并持久化");
        for (int i = 0; i < 1001; i++){
            InstrumentCheckedRate instrumentCheckedRate = new InstrumentCheckedRate();
            instrumentCheckedRate.setYear((short)2018);
            instrumentCheckedRateRepository.save(instrumentCheckedRate);
        }
        Pageable pageRequest = new PageRequest(0, 1000);
        logger.info("获取年份为2018的数据条数");
        Page<InstrumentCheckedRate> instrumentCheckedRates = instrumentCheckedRateRepository.findAllByYear((short)2018, pageRequest);
        logger.info("断言数据条数为1000");
        assertThat(instrumentCheckedRates.getSize()).isEqualTo(1000);
    }

    @Test
    public void findAllByYearAndCheckedTest() {

        logger.info("初始化信息");
        short year = 2018;
        boolean isChecked = true;

        logger.info("新建器具检定率实体");
        InstrumentCheckedRate instrumentCheckedRate = new InstrumentCheckedRate();

        logger.info("设置属性");
        instrumentCheckedRate.setYear(year);
        instrumentCheckedRate.setChecked(isChecked);

        logger.info("保存");
        instrumentCheckedRateRepository.save(instrumentCheckedRate);

        logger.info("查询并断言");
        List<InstrumentCheckedRate> instrumentCheckedRateList = instrumentCheckedRateRepository.findAllByYearAndChecked(year, isChecked);
        assertThat(instrumentCheckedRateList.size()).isNotZero();
    }
}