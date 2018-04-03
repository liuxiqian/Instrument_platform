package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.MandatoryInstrumentCheckApply;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * created by zhangjiahao
 */
public class MandatoryInstrumentCheckApplyRepositoryTest extends RepositoryTest {
    @Autowired
    private MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;

    @Test
    public void saveTest(){
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);
        assertThat(mandatoryInstrumentCheckApply.getId()).isNotNull();
    }

}
