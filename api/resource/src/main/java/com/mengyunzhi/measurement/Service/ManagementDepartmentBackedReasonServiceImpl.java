package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.ManagementDepartmentBackedReason;
import com.mengyunzhi.measurement.repository.ManagementDepartmentBackedReasonRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By chuhang on 17-11-02
 * 退回理由service实现
 */
@Service
public class ManagementDepartmentBackedReasonServiceImpl implements ManagementDepartmentBackedReasonService {
    private Logger logger = Logger.getLogger(ManagementDepartmentBackedReasonServiceImpl.class.getName());

    @Autowired
    ManagementDepartmentBackedReasonRepository managementDepartmentBackedReasonRepository;

    @Override
    public List<ManagementDepartmentBackedReason> getTop10ManagementDepartmentBackedReasons() {
        List<ManagementDepartmentBackedReason> managementDepartmentBackedReasons = managementDepartmentBackedReasonRepository.findTop10ByOrderByIdDesc();
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
    public ManagementDepartmentBackedReason getOnePersistenceManagementDepartmentBackedReasonByReason(String reason) {
        ManagementDepartmentBackedReason managementDepartmentBackedReason = managementDepartmentBackedReasonRepository.findManagementDepartmentBackedReasonByReason(reason);

        if (null == managementDepartmentBackedReason) {
            logger.info("不存在相同的退回理由");
            managementDepartmentBackedReason = new ManagementDepartmentBackedReason();
            managementDepartmentBackedReason.setReason(reason);
            managementDepartmentBackedReasonRepository.save(managementDepartmentBackedReason);
        }

        return managementDepartmentBackedReason;
    }
}
