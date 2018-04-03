package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Accuracy;
import com.mengyunzhi.measurement.repository.AccuracyRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/6/28.
 * 精度
 */
public class AccuracyServiceTest extends ServiceTest {
    private Logger logger = Logger.getLogger(AccuracyServiceTest.class.getName());

    @Autowired
    private AccuracyService accuracyService; // 精度

    @Autowired
    private AccuracyRepository accuracyRepository; // 精度

    @Test
    @Transactional
    public void save() {
        Accuracy accuracy = new Accuracy();
        accuracyService.save(accuracy);
        assertThat(accuracy.getId()).isNotNull();

        accuracyRepository.delete(accuracy.getId());
    }

    @Test
    @Transactional
    public void delete() {
        Accuracy accuracy = new Accuracy();
        accuracyService.save(accuracy);
        accuracyRepository.delete(accuracy.getId());
        assertThat(accuracyRepository.findOne(accuracy.getId())).isNull();
    }
}