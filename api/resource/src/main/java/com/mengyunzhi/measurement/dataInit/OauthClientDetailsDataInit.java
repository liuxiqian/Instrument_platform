package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.entity.OauthClientDetails;
import com.mengyunzhi.measurement.entity.User;
import com.mengyunzhi.measurement.repository.OauthClientDetailsRepository;
import com.mengyunzhi.measurement.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author panjie on 2017/12/28
 */
@Component
public class OauthClientDetailsDataInit implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    private final static Logger logger = Logger.getLogger(OauthClientDetailsDataInit.class.getName());
    @Autowired
    UserDataInit userDataInit;
    @Autowired
    UserRepository userRepository;

    @Autowired
    OauthClientDetailsRepository oauthClientDetailsRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<OauthClientDetails> oauthClientDetailsSet = (List<OauthClientDetails>) oauthClientDetailsRepository.findAll();
        if (oauthClientDetailsSet.size() == 0) {
            logger.info("初始化admin的oauth2认证信息");
            User user = userRepository.findOneByUsername("admin");
            OauthClientDetails oauthClientDetails = new OauthClientDetails();
            oauthClientDetails.setUser(user);
            oauthClientDetails.setAccessTokenValidity(3600);
            oauthClientDetails.setRefreshTokenValidity(3600);
            oauthClientDetails.setAuthorizedGrantTypes("client_credentials,authorization_code,refresh_token,password");
            oauthClientDetails.setClientId("TBayWeTw");
            oauthClientDetails.setClientSecret("sffpf7GsE6ctNDO3Tif701ptutUNyWf71u2Eo7bS");
            oauthClientDetails.setScope("read,write");
            oauthClientDetails.setAutoapprove(true);
            oauthClientDetailsRepository.save(oauthClientDetails);
        }

    }

    @Override
    public int getOrder() {
        return userDataInit.getOrder() + 10;
    }
}
