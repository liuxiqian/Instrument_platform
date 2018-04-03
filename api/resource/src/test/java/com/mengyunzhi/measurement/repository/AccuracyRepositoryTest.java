package com.mengyunzhi.measurement.repository;
import com.mengyunzhi.measurement.entity.Accuracy;
import com.mengyunzhi.measurement.entity.InstrumentType;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Created by administrator on 2017/5/24.
 * 精度
 */
public class AccuracyRepositoryTest extends RepositoryTest {
    private Logger logger = Logger.getLogger(AccuracyRepositoryTest.class.getName());

    @Autowired
    private AccuracyRepository accuracyRepository; // 精度
    @Autowired private InstrumentTypeRepository instrumentTypeRepository;

    /**
     * 通过器具类别ID获取精度
     * panjie
     */
    @Test
    public void findAllByInstrumentType() {
        logger.debug("获取一个器具类别");
        InstrumentType instrumentType = new InstrumentType();
        instrumentTypeRepository.save(instrumentType);

        logger.debug("设置这个器具类上有5个精度");
        Set<Accuracy> accuracies = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Accuracy accuracy = new Accuracy();
            accuracyRepository.save(accuracy);
            if (i % 2 == 0) {
                accuracy.setInstrumentType(instrumentType);
            }
            accuracies.add(accuracy);

        }
        accuracyRepository.save(accuracies);

        logger.debug("断言查询到的数据大小为5");
        List<Accuracy> accuracies1 = (List<Accuracy>) accuracyRepository.findAllByInstrumentType(instrumentType);
        assertThat(accuracies1.size()).isEqualTo(5);
    }
}