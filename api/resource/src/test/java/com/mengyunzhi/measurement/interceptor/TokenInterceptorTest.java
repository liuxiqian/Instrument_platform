package com.mengyunzhi.measurement.interceptor;

import com.mengyunzhi.measurement.ResourceApplication;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author panjie on 2017/11/10
 * token认证拦截器
 */
@SpringBootTest(classes = ResourceApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TokenInterceptorTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Test
    public void preHandle() throws Exception {
        User user = userService.loginWithOneUser();
        String token = userService.getTempTokenOfCurrentUser();

        String url = "/test/withToken/hello";
        mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is(404));



        url = "/test/test/withToken/hello/sfefef";
        mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(404));

        url = "/test/test/withToken?/hello/sfefef";
        mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(404));

        url = "/test/test/withToken?hello/sfefef";
        mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(404));

        url = "/test/testwithToken/hello/sfefef";
        mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(401));

        url = "/test/test/withTokenhello/sfefef";
        mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(401));

        url = "/test/test/withToken?token=1234";
        mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(404));

        url ="/MandatoryInstrument/withToken/hello?token=" + token;
        mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(200));
    }

}