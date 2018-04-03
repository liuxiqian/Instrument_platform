package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.TechnicalInstitutionBackedReason;
import com.mengyunzhi.measurement.repository.TechnicalInstitutionBackedReasonRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chuhang on 17-11-10
 * 技术机构退回原因 service实现
 */
@Service
public class TechnicalInstitutionBackedReasonServiceImpl implements TechnicalInstitutionBackedReasonService {
    private Logger logger = Logger.getLogger(TechnicalInstitutionBackedReasonServiceImpl.class.getName());

    @Autowired
    TechnicalInstitutionBackedReasonRepository managementDepartmentBackedReasonRepository;

    @Override
    public List<TechnicalInstitutionBackedReason> getTop10TechnicalInstitutionBackedReasons() {
        List<TechnicalInstitutionBackedReason> managementDepartmentBackedReasons = managementDepartmentBackedReasonRepository.findTop10ByOrderByIdDesc();
        return managementDepartmentBackedReasons;
    }

    /**
     * 1. 查询是否存在这样的理由
     * 1.1 存在，即返回。
     * 2.2 不存在，new一个新理由，持久化并返回
     * @param reason 理由
     * @return
     * @author panjie
     */
    @Override
    public TechnicalInstitutionBackedReason getOnePersistenceTechnicalInstitutionBackedReasonByReason(String reason) {
        TechnicalInstitutionBackedReason managementDepartmentBackedReason = managementDepartmentBackedReasonRepository.findTechnicalInstitutionBackedReasonByReason(reason);

        if (null == managementDepartmentBackedReason) {
            logger.info("不存在相同的退回理由");
            managementDepartmentBackedReason = new TechnicalInstitutionBackedReason();
            managementDepartmentBackedReason.setReason(reason);
            managementDepartmentBackedReasonRepository.save(managementDepartmentBackedReason);
        }

        return managementDepartmentBackedReason;
    }
}
