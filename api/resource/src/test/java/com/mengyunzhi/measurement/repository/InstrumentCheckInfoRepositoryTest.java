package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.MandatoryInstrumentCheckApply;
import com.mengyunzhi.measurement.entity.InstrumentCheckInfo;
import com.mengyunzhi.measurement.entity.MandatoryInstrument;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * Created by liming on 17-5-25.
 */
public class InstrumentCheckInfoRepositoryTest extends RepositoryTest {
    @Autowired
    private InstrumentCheckInfoRepository instrumentCheckInfoRepository;
    static private Logger logger = Logger.getLogger(InstrumentCheckInfoRepositoryTest.class.getName());
    @Autowired private MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired private MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;

    @Test
    public void findAllByMandatoryInstrumentAndCheckApplyTest() {
        logger.debug("获取一个强检器具，一个检定申请");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);
        List<InstrumentCheckInfo> instrumentCheckInfos = new ArrayList<>();

        logger.debug("持久化两个实体");
        for (int i = 0; i < 2; i++) {
            InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
            instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);
            instrumentCheckInfo.setMandatoryInstrumentCheckApply(mandatoryInstrumentCheckApply);
            instrumentCheckInfos.add(instrumentCheckInfo);
        }

        instrumentCheckInfoRepository.save(instrumentCheckInfos);

        logger.debug("查询并断言");
        List<InstrumentCheckInfo> instrumentCheckInfoList = (List<InstrumentCheckInfo>) instrumentCheckInfoRepository.findAllByMandatoryInstrumentAndMandatoryInstrumentCheckApply(mandatoryInstrument, mandatoryInstrumentCheckApply);
        assertThat(instrumentCheckInfoList.size()).isEqualTo(2);
    }

    @Test
    public void setCheckDateTest() {
        InstrumentCheckInfo instrumentCheckInfo = new InstrumentCheckInfo();
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        Date nowdata = new Date(calendar.getTimeInMillis());
        // 将检定日期设置为当前日期
        instrumentCheckInfo.setCheckDate(nowdata);
        // 断言
        short nowYear = 2018; // 当前的年份
        assertThat(instrumentCheckInfo.getCheckYear()).isEqualTo(nowYear);
    }
}