package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.ApplyTypeService;
import com.mengyunzhi.measurement.Service.ApplyTypeServiceTestData;
import com.mengyunzhi.measurement.entity.ApplyType;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
/**
 * Created by panjie on 17/5/5.
 */
public class ApplyTypeControllerTest extends ControllerTest {


    private static Logger logger = Logger.getLogger(ApplyTypeControllerTest.class.getName());
    @Autowired
    ApplyTypeService applyTypeService;   // 申请类型
    @Autowired
    ApplyTypeRepository applyTypeRepository;
    @Autowired
    ApplyTypeServiceTestData applyTypeServiceTestData;

    @Test
    public void getOneByName() throws Exception {
        ApplyType applyType = applyTypeService.getOneSavedApplyType();
        applyTypeRepository.save(applyType);
        String url = "/ApplyType/getOneByName/" + applyType.getName();
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ApplyType_getOneByName", preprocessResponse(prettyPrint())))
                .andReturn();

        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        assertThat((String)jsonObject.get("name")).isEqualTo(applyType.getName());
    }

    @Test
    public void getById() throws Exception {
        ApplyType applyType = applyTypeService.getOneSavedApplyType();
        applyTypeRepository.save(applyType);
        String url = "/ApplyType/getById/" + applyType.getId().toString();
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ApplyType_getById", preprocessResponse(prettyPrint())))
                .andReturn();

        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        assertThat(jsonObject.getLong("id")).isEqualTo(applyType.getId());
    }

    @Test
    public void save() throws Exception {
        logger.info("---- 测试save方法 ----");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangjiahao");

        MvcResult result = this.mockMvc.perform(post("/ApplyType/")
                .contentType("application/json")
                .content(jsonObject.toString())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Discipline_save", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取的内容转化为json对象，并断言其id存在，且不为空");
        JSONObject jsonObject1 = JSONObject.fromObject(content);

        logger.info("断言学科类别实体存在且不为空");
        assertThat(jsonObject1.optLong("id")).isNotNull();
    }

    @Test
    public void update() throws Exception {
        logger.info("----- 更新实体 -----");
        logger.info("新建一个申类型别实体");
        ApplyType applyType = new ApplyType();
        applyType.setName("name");
        logger.info("保存该实体");
        applyTypeRepository.save(applyType);

        logger.info("修改实体, 并断言状态码返回204");
        applyType.setName("hello");
        this.mockMvc.perform(put("/ApplyType/" + applyType.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(applyType).toString()))
                .andDo(document("ApplyType_update", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("查询实体，并断言修改成功");
        assertThat(applyTypeRepository.findOne(applyType.getId()).getName()).isEqualTo("hello");
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("----- 删除实体 -----");
        logger.info("新建一个实体");
        ApplyType applyType = new ApplyType();
        applyType.setName("name");
        logger.info("保存该实体");
        applyTypeRepository.save(applyType);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/ApplyType/" + applyType.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(applyType).toString()))
                .andDo(document("ApplyType_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(applyTypeRepository.findOne(applyType.getId())).isNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("---- getAll方法测试 ----");
        logger.info("创建一个申请类型实体");
        ApplyType applyType = new ApplyType();
        logger.info("保存该实体");
        applyType.setName("name");
        applyTypeRepository.save(applyType);
        logger.info("发送模拟数据");
        MvcResult result = this.mockMvc.perform(get("/ApplyType/getAll")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(200))
                .andDo(document("ApplyType_getAll", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("获取返回数据");
        String content = result.getResponse().getContentAsString();
        logger.info("将获取到的内容转化为jsonArray对象，并断言数据长度为1");
        JSONArray jsonArray = JSONArray.fromObject(content);
        List<ApplyType> applyTypes = (List<ApplyType>) applyTypeRepository.findAll();
        assertThat(jsonArray.size()).isEqualTo(applyTypes.size());
    }
}