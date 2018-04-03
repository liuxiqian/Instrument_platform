package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DisciplineService;
import com.mengyunzhi.measurement.Service.InstrumentFirstLevelTypeService;
import com.mengyunzhi.measurement.entity.Discipline;
import com.mengyunzhi.measurement.repository.DisciplineRepository;
import com.mengyunzhi.measurement.entity.InstrumentFirstLevelType;
import com.mengyunzhi.measurement.repository.InstrumentFirstLevelTypeRepository;
import net.sf.json.JSONArray;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/7/26.
 * 一级器具类别
 */
public class InstrumentFirstLevelTypeControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(InstrumentFirstLevelTypeControllerTest.class.getName());

    @Autowired
    InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;  // 一级器具类别
    @Autowired
    DisciplineRepository disciplineRepository;  // 学科类别
    @Test
    public void save() throws Exception {
        InstrumentFirstLevelType instrumentFirstLevelType =
                InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
        MvcResult mockMvc = this.mockMvc.perform(
                post("/InstrumentFirstLevelType/")
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.fromObject(instrumentFirstLevelType).toString()))
                .andDo(document("InstrumentFirstLevelType_save", preprocessResponse(prettyPrint())))
                .andExpect(status().is(201))
                .andReturn();
        String content = mockMvc.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        int id = (int) jsonObject.get("id");
        assertThat(id).isGreaterThan(0);
        instrumentFirstLevelTypeRepository.delete((long) id);
    }

    @Test
    public void getAllByDisciplineId() throws Exception {
        Discipline discipline = DisciplineService.getOneDiscipline();
        disciplineRepository.save(discipline);

        for (int i = 0; i < 10; i++) {
            InstrumentFirstLevelType instrumentFirstLevelType = InstrumentFirstLevelTypeService.getOneInstrumentFirstLevelType();
            instrumentFirstLevelType.setDiscipline(discipline);
            instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        }

        MvcResult mockMvc = this.mockMvc.perform(
                get("/InstrumentFirstLevelType/getAllByDisciplineId/" + discipline.getId().toString())
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("InstrumentFirstLevelType_getAllByDisciplineId", preprocessResponse(prettyPrint())))
                .andExpect(status().is(200))
                .andReturn();

        assertThat(JSONArray.fromObject(mockMvc.getResponse().getContentAsString()).size()).isEqualTo(10);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("----- 删除实体 -----");
        logger.info("新建一个实体");
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setName("name");
        instrumentFirstLevelType.setPinyin("pinyin");
        logger.info("保存该实体");
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("删除实体中的一条数据, 并断言状态码返回204");
        this.mockMvc.perform(delete("/InstrumentFirstLevelType/delete/" + instrumentFirstLevelType.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(JSONObject.fromObject(instrumentFirstLevelType).toString()))
                .andDo(document("InstrumentFirstLevelType_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(204));

        logger.info("断言删除成功");
        assertThat(instrumentFirstLevelTypeRepository.findOne(instrumentFirstLevelType.getId())).isNull();
    }

    @Test
    public void updateTest() throws Exception{
        logger.info("更新方法测试");
        logger.info("---- 新建一个实体 ----");
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setName("name");
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("更新该实体");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", instrumentFirstLevelType.getId());
        jsonObject.put("name", "zhangjiahao");
        logger.info("模拟测试");
        this.mockMvc.perform(put("/InstrumentFirstLevelType/update/" + instrumentFirstLevelType.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(jsonObject.toString()))
                .andExpect(status().is(204))
                .andDo(document("InstrumentFirstLevelType_update", preprocessResponse(prettyPrint())))
                .andReturn();
        logger.info("断言修改成功");
        InstrumentFirstLevelType instrumentFirstLevelType1 = instrumentFirstLevelTypeRepository.findOne(instrumentFirstLevelType.getId());
        assertThat(instrumentFirstLevelType1.getName()).isEqualTo("zhangjiahao");
    }

    @Test
    public void getTest() throws Exception {
        logger.info("创建一个一级类别实体并保存");
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("发送请求");
        MvcResult result = this.mockMvc.perform(get("/InstrumentFirstLevelType/get/" + instrumentFirstLevelType.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("InstrumentFirstLevelType_get", preprocessResponse(prettyPrint())))
                .andReturn();
        logger.info("获取返回内容");
        String content = result.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.optLong("id")).isEqualTo(instrumentFirstLevelType.getId());

    }


}