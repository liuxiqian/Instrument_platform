package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.CheckResult;
import com.mengyunzhi.measurement.repository.CheckResultRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liming on 17-7-26.
 * 检定结果Service实现
 */
@Service
public class CheckResultServiceImpl implements CheckResultService {
    static private Logger logger = Logger.getLogger(CheckResultServiceImpl.class.getName());
    @Autowired
    protected CheckResultRepository checkResultRepository;
    @Override
    public List<CheckResult> getCheckResultTree() {
        logger.info("获取检查结果的根节点");
        List<CheckResult> checkResults  = checkResultRepository.findAllByParentCheckResultIsNull();
        for (CheckResult checkResult: checkResults) {
            getTreeByCheckResult(checkResult);
        }
        return checkResults;
    }

    @Override
    public CheckResult getTreeByCheckResult(CheckResult checkResult) {
        List<CheckResult> checkResults = checkResultRepository.findAllByParentCheckResult(checkResult);
        for (CheckResult checkResult1: checkResults) {
            this.getTreeByCheckResult(checkResult1);
        }
        checkResult.setSonCheckResults(checkResults);
        return checkResult;
    }

    @Override
    public CheckResult getOneSavedCheckResult() {
        logger.debug("获取一个检定结果");
        CheckResult checkResult = new CheckResult();
        checkResultRepository.save(checkResult);

        CheckResult sonCheckResult = new CheckResult();
        sonCheckResult.setParentCheckResult(checkResult);
        checkResultRepository.save(sonCheckResult);
        return sonCheckResult;

    }

    @Override
    public CheckResult findRootCheckResult(CheckResult checkResult) {
        CheckResult parentCheckResult = checkResult.getParentCheckResult();
        if ( parentCheckResult != null) {
            parentCheckResult = this.findRootCheckResult(parentCheckResult);
            return parentCheckResult;
        }
        return checkResult;
    }
}
