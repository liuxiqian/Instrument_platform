package com.mengyunzhi.measurement.oauth.service;

import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.util.Map;

/**
 * @author panjie on 2017/12/27
 * 计量管理平台
 */
public interface InstrumentApiService {
    /**
     * 使用用户名密码，登录系统
     * @param username
     * @param password
     */
    GetMethod loginByUsernameAndPassword(String username, String password) throws IOException;

    /**
     * 使用clientId登录
     * @param clientId
     */
    Map<String, String> loginByClientId(String clientId);
}
