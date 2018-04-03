package com.mengyunzhi.measurement.interceptor;

import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.Service.UserServiceImpl;
import com.mengyunzhi.measurement.entity.User;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author panjie on 2017/11/10
 * token 拦截器，用于用户认证
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    Logger logger = Logger.getLogger(TokenInterceptor.class.getName());
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("在触发action前，先触发本方法");
        logger.info("获取路径中的token值");
        String tokenString = request.getParameter("token");

        UserService userService = new UserServiceImpl();
        logger.info("根据token获取对应的用户");
        User user = userService.getUserByToken(tokenString);
        if (user == null) {
            throw new SecurityException("传入的token已过期");
        }

        logger.info("注册spring security，进行用户名密码认证");
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return true;
    }
}