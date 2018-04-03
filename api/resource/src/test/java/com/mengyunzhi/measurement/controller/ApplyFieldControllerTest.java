package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ApplyFieldService;
import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.repository.ApplyFieldRepository;
import com.mengyunzhi.measurement.entity.ApplyType;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.logging.Logger;


import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 申请字段
 */
public class ApplyFieldControllerTest extends ControllerTest {
    private static Logger logger = Logger.getLogger(ApplyFieldControllerTest.class.getName());
    @Autowired
    ApplyFieldService applyFieldService;
    @Autowired
    ApplyFieldRepository applyFieldRepository;
    @Autowired
    ApplyTypeRepository applyTypeRepository;

    @Test
    public void getById() throws Exception {
        ApplyField applyField = applyFieldService.getOneSavedApplyField();
        String url = "/ApplyField/" + applyField.getId().toString();
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ApplyField_getById", preprocessResponse(prettyPrint())))
                .andReturn();
        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        assertThat(jsonObject.getLong("id")).isEqualTo(applyField.getId());
        assertThat(jsonObject.get("applyType")).isNotNull();
        assertThat(JSONObject.fromObject(jsonObject.get("applyType")).get("workflowType")).isNotNull();
        assertThat(JSONObject.fromObject(JSONObject.fromObject(jsonObject.get("applyType")).get("workflowType")).get("workflowNodes")).isNotNull();
    }

    @Test
    public void save() throws Exception {
        logger.info("---- 测试save方法 ----");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangjiahao");

        MvcResult result = this.mockMvc.perform(post("/ApplyField/")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ApplyField_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为json对象，并断言其id存在，且不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);

        logger.info("断言学科类别实体存在且不为空");
        Assertions.assertThat(jsonObject1.optLong("id")).isNotNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("----- 更新实体 -----");
        logger.info("新建一个申请字段实体");
        ApplyField applyField = new ApplyField();
        applyField.setName("name");
        logger.info("保存该实体");
        applyFieldRepository.save(applyField);

        logger.info("修改实体, 并断言状态码返回204");
        applyField.setName("hello");
        this.mockMvc.perform(put("/ApplyField/" + applyField.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(applyField).toString()))
                .andDo(document("ApplyField_update", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("查询实体，并断言修改成功");
        Assertions.assertThat(applyFieldRepository.findOne(applyField.getId()).getName()).isEqualTo("hello");
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("----- 删除实体 -----");
        logger.info("新建一个实体");
        ApplyField applyField = new ApplyField();
        applyField.setName("name");
        logger.info("保存该实体");
        applyFieldRepository.save(applyField);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/ApplyField/" + applyField.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(applyField).toString()))
                .andDo(document("ApplyField_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        Assertions.assertThat(applyFieldRepository.findOne(applyField.getId())).isNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("创建一个申请类型实体");
        ApplyField applyField = new ApplyField();
        logger.info("保存该实体");
        applyField.setName("name");
        applyFieldRepository.save(applyField);
        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/ApplyField/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(200))
                .andDo(document("ApplyField_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为jsonArray对象，并断言数据长度为1");
        JSONArray jsonArray = JSONArray.fromObject(content);
        List<ApplyField> applyFields = (List<ApplyField>) applyFieldRepository.findAll();
        Assertions.assertThat(jsonArray.size()).isEqualTo(applyFields.size());
    }

    @Test
    public void getAllByApplyTypeId() throws Exception{
        logger.info("新建一个申请类型实体");
        ApplyType applyType = new ApplyType();
        applyType.setName("haha");
        applyTypeRepository.save(applyType);

        logger.info("新建一个申请字段实体");
        ApplyField applyField = new ApplyField();
        applyField.setName("1");
        applyField.setApplyType(applyType);
        applyFieldRepository.save(applyField);

        logger.info("再建立一个申请字段实体");
        ApplyField applyField1 = new ApplyField();
        applyField1.setName("2");
        applyField1.setApplyType(applyType);
        applyFieldRepository.save(applyField1);

        String content = this.mockMvc.perform(get("/ApplyField/getAllByApplyTypeId/" + applyType.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ApplyField_getAllByApplyTypeId", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        logger.info("断言");
        JSONArray jsonArray = JSONArray.fromObject(content);
        Assertions.assertThat(jsonArray.size()).isEqualTo(2);

    }
}