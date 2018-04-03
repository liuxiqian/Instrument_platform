package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.entity.DistrictType;
import com.mengyunzhi.measurement.repository.DistrictTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/7/25.
 * 区域类型数据初始化
 */
@Component
public class DistrictTypeDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private Logger logger = Logger.getLogger(DistrictTypeDataInit.class.getName());
    @Autowired
    private DistrictTypeRepository districtTypeRepository;       // 区域类型

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<DistrictType> districtTypes1 = (List<DistrictType>) districtTypeRepository.findAll();
        if (districtTypes1.size() == 0) {
            logger.info("----- 添加区域类型 panjie -----");
            List<DistrictType> districtTypes = new ArrayList<>();
            districtTypes.add(new DistrictType("省", "sheng", null));
            districtTypes.add(new DistrictType("市", "shi", null));
            districtTypes.add(new DistrictType("区\\县", "quxian", null));
            districtTypeRepository.save(districtTypes);
        }
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
