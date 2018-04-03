package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.InstrumentTypeService;
import com.mengyunzhi.measurement.entity.Accuracy;
import com.mengyunzhi.measurement.entity.InstrumentType;
import com.mengyunzhi.measurement.repository.AccuracyRepository;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/6/28.
 * 精度控制器
 */
public class AccuracyControllerTest extends ControllerTest {
    @Autowired
    private AccuracyRepository accuracyRepository; // 精度
    @Autowired
    @Qualifier("InstrumentTypeService")
    private InstrumentTypeService instrumentTypeService;

    @Test
    public void save() throws Exception {
        Accuracy accuracy = new Accuracy();
        accuracy.setValue("hello");
        JSONObject jsonObject = JSONObject.fromObject(accuracy);

        accuracy.setValue("test value");
        MvcResult mvcResult = this.mockMvc.perform(post("/Accuracy/")
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonObject.toString()))
                .andDo(document("Accuracy_save", preprocessResponse(prettyPrint())))
                .andExpect(status().is(201))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject1 = JSONObject.fromObject(content);
        assertThat(jsonObject.get("value")).isEqualTo("hello");
    }

    @Test
    public void deleteTest() throws Exception {
        Accuracy accuracy = new Accuracy();
        accuracyRepository.save(accuracy);
        String url = "/Accuracy/" + accuracy.getId().toString();
        this.mockMvc
                .perform(delete(url)
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("Accuracy_delete", preprocessResponse(prettyPrint())))
                .andExpect(status().is(202));
        assertThat(accuracyRepository.findOne(accuracy.getId())).isNull();
    }

    @Test
    public void getAllByInstrumentTypeId() throws Exception {
        InstrumentType instrumentType = instrumentTypeService.getOneSavedInstrumentType();
        String url = "/Accuracy/getAllByInstrumentTypeId/" + instrumentType.getId().toString();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(url)
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("Accuracy_getAllByInstrumentTypeId", preprocessResponse(prettyPrint())))
                .andExpect(status().is(200));
    }

}