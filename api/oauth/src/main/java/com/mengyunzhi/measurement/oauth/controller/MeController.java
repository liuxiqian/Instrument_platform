package com.mengyunzhi.measurement.oauth.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.UserJsonView;
import com.mengyunzhi.measurement.oauth.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

/**
 * @author panjie on 2017/12/27
 */
@RestController
@RequestMapping("/me")
public class MeController {
    private final static Logger logger = Logger.getLogger(MeController.class.getName());
    @Autowired @Qualifier("oauth.service.UserService")
    UserService userService;

    @RequestMapping
    @JsonView(UserJsonView.GetAll.class)
    public Map<String, Object> home(Principal user) throws IOException {
        return userService.getUserInfoByPrincipalUser(user);
    }
}
