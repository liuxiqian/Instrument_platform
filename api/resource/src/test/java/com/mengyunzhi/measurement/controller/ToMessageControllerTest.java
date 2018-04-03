package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.ToMessage;
import com.mengyunzhi.measurement.entity.User;
import com.mengyunzhi.measurement.repository.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created By chuhang on 17-10-17
 */
public class ToMessageControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(ToMessageControllerTest.class.getName());

    @Autowired
    private ToMessageRepository toMessageRepository;

    @Autowired
    private UserService userService;

    @Test
    public void pageAllOfCurrentUser() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建一个信息实体");
        ToMessage toMessage = new ToMessage();
        toMessage.setToDepartment(user.getDepartment());
        toMessageRepository.save(toMessage);

        logger.info("测试");
        MvcResult mvcResult = this.mockMvc.perform(get("/ToMessage/pageAllOfCurrentUser?page=0&size=2")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ToMessage_pageAllOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("删除数据");
        toMessageRepository.delete(toMessage);
    }

    @Test
    public void getTest() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建一个信息实体");
        ToMessage toMessage = new ToMessage();
        toMessage.setToDepartment(user.getDepartment());
        toMessage.setFromDepartment(user.getDepartment());
        toMessageRepository.save(toMessage);

        logger.info("测试");
        MvcResult mvcResult = this.mockMvc.perform(get("/ToMessage/" + toMessage.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ToMessage_get", preprocessResponse(prettyPrint())))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("id")).isNotNull();

        logger.info("删除数据");
        toMessageRepository.delete(toMessage);
    }

    @Test
    public void setMessageToRead() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建一个信息实体");
        ToMessage toMessage = new ToMessage();
        toMessage.setToDepartment(user.getDepartment());
        toMessage.setFromDepartment(user.getDepartment());
        toMessageRepository.save(toMessage);

        logger.info("测试");
        MvcResult mvcResult = this.mockMvc.perform(post("/ToMessage/setMessageToRead/" + toMessage.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ToMessage_setMessageToRead", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言");
        assertThat(toMessageRepository.findOne(toMessage.getId()).getIsRead()).isEqualTo(Boolean.TRUE);

        logger.info("删除数据");
        toMessageRepository.delete(toMessage);
    }

    @Test
    public void reply() throws Exception {
        logger.info("新建部门实体");
        Department department = new Department();
        departmentRepository.save(department);
        JSONObject departmentJsonObject = new JSONObject();
        departmentJsonObject.put("id", department.getId());

        JSONObject sendJsonObject = new JSONObject();
        sendJsonObject.put("content", "test");
        sendJsonObject.put("title", "test");
        sendJsonObject.put("fromDepartment", departmentJsonObject);

        this.mockMvc.perform(post("/ToMessage/reply")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken)
                .content(sendJsonObject.toString()))
                .andDo(document("ToMessage_reply", preprocessResponse(prettyPrint())));

        logger.info("删除");
        departmentRepository.delete(department);
    }

    @Test
    public void pageReceiveUnReadToMessageOfCurrentUser() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建一个信息实体");
        ToMessage toMessage = new ToMessage();
        toMessage.setToDepartment(user.getDepartment());
        toMessageRepository.save(toMessage);

        logger.info("测试");
        MvcResult mvcResult = this.mockMvc.perform(get("/ToMessage/pageReceiveUnReadToMessageOfCurrentUser?page=0&size=2")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ToMessage_pageReceiveUnReadToMessageOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);

        logger.info("删除数据");
        toMessageRepository.delete(toMessage);
    }

    @Test
    public void sendMessageToManagementDepartmentOfCurrentUser() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        JSONObject sendJsonObject = new JSONObject();
        sendJsonObject.put("content", "test");
        sendJsonObject.put("title", "test");

        logger.info("模拟请求");
        MvcResult mvcResult = this.mockMvc.perform(post("/ToMessage/sendMessageToManagementDepartmentOfCurrentUser")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken)
                .content(sendJsonObject.toString()))
                .andDo(document("ToMessage_sendMessageToManagementDepartmentOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言");
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("id")).isNotNull();
    }

    @Test
    public void batchDelete() throws Exception {
        logger.info("新建两个接收消息实体");
        ToMessage toMessage = new ToMessage();
        toMessageRepository.save(toMessage);

        ToMessage toMessage1 = new ToMessage();
        toMessageRepository.save(toMessage1);

        logger.info("模拟请求");
        this.mockMvc.perform(delete("/ToMessage/batchDelete/" + toMessage.getId() + ',' + toMessage1.getId())
                .header("x-auth-token", xAuthToken))
                .andDo(document("ToMessage_batchDelete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言");
        assertThat(toMessageRepository.findOne(toMessage.getId())).isNull();
        assertThat(toMessageRepository.findOne(toMessage1.getId())).isNull();
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("设置一个登录用户");
        User user = userService.loginWithOneUser();

        logger.info("新建信息实体");
        ToMessage toMessage = new ToMessage();
        toMessage.setToDepartment(user.getDepartment());
        toMessage.setFromDepartment(user.getDepartment());
        toMessageRepository.save(toMessage);

        logger.info("模拟请求");
        this.mockMvc.perform(delete("/ToMessage/" + toMessage.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("ToMessage_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(toMessageRepository.findOne(toMessage.getId())).isNull();
    }
}