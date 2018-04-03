package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.entity.InstrumentCertificateType;
import com.mengyunzhi.measurement.repository.InstrumentCertificateTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjiahao on 2017/8/15.
 * 器具证书类型 数据初始化
 */
@Component
public class InstrumentCertificateTypeDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private Logger logger = Logger.getLogger(InstrumentCertificateTypeDataInit.class.getName());
    @Autowired
    private InstrumentCertificateTypeRepository instrumentCertificateTypeRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<InstrumentCertificateType> instrumentCertificateType1 = (List<InstrumentCertificateType>) instrumentCertificateTypeRepository.findAll();
        if (instrumentCertificateType1.size() == 0)
        {
            logger.info("---- 添加器具证书类型 ----");
            List<InstrumentCertificateType> instrumentCertificateTypes = new ArrayList<>();
            instrumentCertificateTypes.add(new InstrumentCertificateType("检定证书","jiandingzhengshu",20170815L,20170816L,null));
            instrumentCertificateTypes.add(new InstrumentCertificateType("测试证书","ceshizhengshu",20170815L,20170816L,null));
            instrumentCertificateTypes.add(new InstrumentCertificateType("校准证书","jiaozhunzhengshu",20170815L,20170816L,null));
            instrumentCertificateTypeRepository.save(instrumentCertificateTypes);
        }
    }

    @Override
    public int getOrder()
    {
        return HIGHEST_PRECEDENCE;
    }
}
