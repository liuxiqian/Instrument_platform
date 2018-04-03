package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.entity.FromMessage;
import com.mengyunzhi.measurement.repository.FromMessageRepository;
import com.mengyunzhi.measurement.entity.User;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created By chuhang on 17-10-17
 */
public class FromMessageControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(FromMessageControllerTest.class.getName());
    @Autowired
    private FromMessageRepository fromMessageRepository;

    @Test
    public void pageAllOfCurrentUser() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建一个信息实体");
        FromMessage fromMessage = new FromMessage();
        fromMessage.setFromDepartment(user.getDepartment());
        fromMessageRepository.save(fromMessage);

        logger.info("测试");
        MvcResult mvcResult = this.mockMvc.perform(get("/FromMessage/pageAllOfCurrentUser?page=0&size=2")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("FromMessage_pageAllOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("删除数据");
        fromMessageRepository.delete(fromMessage);
    }

    @Test
    public void getTest() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建一个信息实体");
        FromMessage fromMessage = new FromMessage();
        fromMessage.setToDepartment(user.getDepartment());
        fromMessage.setFromDepartment(user.getDepartment());
        fromMessageRepository.save(fromMessage);

        logger.info("测试");
        MvcResult mvcResult = this.mockMvc.perform(get("/FromMessage/" + fromMessage.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("FromMessage_get", preprocessResponse(prettyPrint())))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("id")).isNotNull();

        logger.info("删除数据");
        fromMessageRepository.delete(fromMessage);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建信息实体");
        FromMessage fromMessage = new FromMessage();
        fromMessage.setToDepartment(user.getDepartment());
        fromMessage.setFromDepartment(user.getDepartment());
        fromMessageRepository.save(fromMessage);

        logger.info("模拟请求");
        this.mockMvc.perform(delete("/FromMessage/" + fromMessage.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("FromMessage_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(fromMessageRepository.findOne(fromMessage.getId())).isNull();
    }

    @Test
    public void batchDelete() throws Exception {
        logger.info("新建两个发送消息实体");
        FromMessage fromMessage = new FromMessage();
        fromMessageRepository.save(fromMessage);

        FromMessage fromMessage1 = new FromMessage();
        fromMessageRepository.save(fromMessage1);

        logger.info("模拟请求");
        this.mockMvc.perform(delete("/FromMessage/batchDelete/" + fromMessage.getId() + ',' + fromMessage1.getId())
                .header("x-auth-token", xAuthToken))
                .andDo(document("FromMessage_batchDelete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言");
        assertThat(fromMessageRepository.findOne(fromMessage.getId())).isNull();
        assertThat(fromMessageRepository.findOne(fromMessage1.getId())).isNull();
    }

}