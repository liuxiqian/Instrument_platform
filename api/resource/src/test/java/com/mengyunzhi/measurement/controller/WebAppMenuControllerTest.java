package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.entity.WebAppMenu;
import com.mengyunzhi.measurement.repository.WebAppMenuRepository;
import io.swagger.util.Json;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

/**
 * Created by panjie on 17/6/7.
 * 前台菜单
 */
public class WebAppMenuControllerTest extends ControllerTest {
    @Autowired
    WebAppMenuRepository webAppMenuRepository;
    @Test
    public void getAll() throws Exception {
        MvcResult mcvResult = this.mockMvc.perform(get("/WebAppMenu/")
                .header("x-auth-token", xAuthToken)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("WebAppMenu",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andReturn();

        String content = mcvResult.getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isGreaterThan(8);
        return;

    }

    // 更新菜单权重 panjie
    @Test
    public void updateWeightByIdAndWeight() throws Exception {
        //存前台菜单
        WebAppMenu webAppMenu = new WebAppMenu();
        webAppMenuRepository.save(webAppMenu);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weight", 1232123);
        String url = "/WebAppMenu/updateWeight/" + webAppMenu.getId().toString();
        this.mockMvc.perform(put(url)
                .header("x-auth-token", xAuthToken)
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("WebAppMenu_updateWeight",  preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());

    }

}