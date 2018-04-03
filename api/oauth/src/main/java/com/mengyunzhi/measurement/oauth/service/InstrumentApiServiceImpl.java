package com.mengyunzhi.measurement.oauth.service;

import com.mengyunzhi.measurement.entity.OauthClientDetails;
import com.mengyunzhi.measurement.oauth.config.InstrumentConfig;
import com.mengyunzhi.measurement.repository.OauthClientDetailsRepository;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author panjie on 2017/12/27
 */
@Service
public class InstrumentApiServiceImpl implements InstrumentApiService {
    private Logger logger = Logger.getLogger(InstrumentApiServiceImpl.class.getName());
    @Autowired
    OauthClientDetailsRepository oauthClientDetailsRepository;  // oauth客户信息
    @Autowired
    InstrumentConfig instrumentConfig;      // 计量的配置信息

    @Override
    public GetMethod loginByUsernameAndPassword(String username, String password) throws IOException {
        logger.info("使用新创建的用户信息发起登录请求，并获取到用于后期认证的xAuthToken");
        logger.info("创建基于base64的认证信息");
        byte[] encodedBytes = Base64.encodeBase64((username + ":" + password).getBytes());
        logger.info("使用base64信息进行跨域登录认证");
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(instrumentConfig.getUrl());

        get.addRequestHeader("Authorization", "Basic " + new String(encodedBytes));
        get.releaseConnection();

        client.executeMethod(get);
        get.releaseConnection();
        return get;
    }

    @Override
    public Map<String, String> loginByClientId(String clientId) {
        OauthClientDetails oauthClientDetails = oauthClientDetailsRepository.findOne(clientId);
        // 获取x-auth-token，并返回
        Map<String, String> instrument = new LinkedHashMap();
        try {
            GetMethod getMethod = this.loginByUsernameAndPassword(oauthClientDetails.getUser().getUsername(), oauthClientDetails.getUser().getPassword());

            instrument.put("description", "计量器具管理平台");
            instrument.put("headerName", "x-auth-token");
            instrument.put("Expires", getMethod.getResponseHeader("Expires").getValue());
            instrument.put("token", getMethod.getResponseHeader("x-auth-token").getValue());

        } catch (IOException exception) {
            logger.error("请求登录计理管理系统时发生了IO异常");
            exception.printStackTrace();
        }

        return instrument;
    }
}
