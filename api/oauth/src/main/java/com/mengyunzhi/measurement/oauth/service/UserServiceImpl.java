package com.mengyunzhi.measurement.oauth.service;

import com.mengyunzhi.measurement.entity.OauthClientDetails;
import com.mengyunzhi.measurement.repository.OauthClientDetailsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author panjie on 2017/12/27
 */
@Service("oauth.service.UserService")
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private InstrumentApiService instrumentApiService;    // 计量管理系统API
    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    @Override
    public Map<String, Object> getUserInfoByPrincipalUser(Principal principalUser) {
        Map<String, Object> map = new LinkedHashMap<>();
        logger.info("加入clientId");
        map.put("name", principalUser.getName());

        logger.info("加入计量管理系统登录信息");
        Map<String, String> tokenInfo = instrumentApiService.loginByClientId(principalUser.getName());
        map.put("token", tokenInfo);

        OauthClientDetails oauthClientDetails = oauthClientDetailsRepository.findOne(principalUser.getName());
        map.put("user", oauthClientDetails.getUser());
        return map;
    }
}
