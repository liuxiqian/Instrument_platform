package com.mengyunzhi.measurement.oauth.properties;

import com.mengyunzhi.measurement.oauth.config.InstrumentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author panjie on 2017/12/27
 * 计量管理系统平台的对接信息
 */
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class InstrumentProperties {
    @Autowired
    Environment environment;

    @Bean
    public InstrumentConfig instrumentConfig() {
        InstrumentConfig instrumentConfig = new InstrumentConfig();
        instrumentConfig.setUrl(environment.getProperty("instrument.login.url"));
        return instrumentConfig;
    }
}