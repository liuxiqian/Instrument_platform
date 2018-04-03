package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.InstrumentType;
import com.mengyunzhi.measurement.entity.MeasureScale;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by liming on 17-5-21.
 */
public class MeasureScaleRepositoryTest extends RepositoryTest {
    static private Logger logger = Logger.getLogger(MeasureScaleRepositoryTest.class.getName());

    @Autowired
    private MeasureScaleRepository measureScaleRepository;

    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;

    @Test
    public void save() {
        logger.info("创建测量范围");
        MeasureScale measureScale = new MeasureScale();
        logger.info("保存");
        measureScaleRepository.save(measureScale);
        logger.info("断言");
        assertThat(measureScale.getId()).isNotNull();
    }

    @Test
    public void findAllByInstrumentType() {
        logger.debug("获取一个器具类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentTypeRepository.save(instrumentType);

        logger.debug("设置这个器具类上有5个精度");
        Set<MeasureScale> measureScales = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            MeasureScale measureScale = new MeasureScale();
            measureScaleRepository.save(measureScale);
            if (i % 2 == 0) {
                measureScale.setInstrumentType(instrumentType);
            }
            measureScales.add(measureScale);

        }
        measureScaleRepository.save(measureScales);

        logger.debug("断言查询到的数据大小为5");
        List<MeasureScale> measureScales1 = (List<MeasureScale>) measureScaleRepository.findAllByInstrumentType(instrumentType);
        assertThat(measureScales1.size()).isEqualTo(5);
    }
}