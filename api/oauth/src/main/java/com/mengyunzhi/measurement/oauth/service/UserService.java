package com.mengyunzhi.measurement.oauth.service;

import java.security.Principal;
import java.util.Map;

/**
 * @author panjie on 2017/12/27
 */
public interface UserService {
    Map<String, Object> getUserInfoByPrincipalUser(Principal principalUser);
}
