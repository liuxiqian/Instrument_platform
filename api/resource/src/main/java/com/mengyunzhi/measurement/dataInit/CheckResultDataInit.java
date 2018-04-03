package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.entity.CheckResult;
import com.mengyunzhi.measurement.repository.CheckResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by liming on 17-7-26.
 * 检定结果数据初始化
 */
@Component
public class CheckResultDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered{
    private Logger logger = Logger.getLogger(CheckResultDataInit.class.getName());
    @Autowired private CheckResultRepository checkResultRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<CheckResult> checkResults = (List<CheckResult>) checkResultRepository.findAll();
        if (checkResults.size() == 0) {
            logger.info("---------------添加检定结果-------------------");
            CheckResult checkResult1 = new CheckResult();
            checkResult1.setPinyin("buhege");
            checkResult1.setName("不合格");

            checkResultRepository.save(checkResult1);
            CheckResult checkResult = new CheckResult();
            checkResult.setName("合格");
            checkResult.setPinyin("hege");
            checkResultRepository.save(checkResult);

            CheckResult checkResult2 = new CheckResult();
            checkResult2.setName("一次合格");
            checkResult2.setPinyin("yicihege");
            checkResult2.setParentCheckResult(checkResult);
            checkResultRepository.save(checkResult2);

            checkResult.getSonCheckResults().add(checkResult2);
            checkResultRepository.save(checkResult);
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
