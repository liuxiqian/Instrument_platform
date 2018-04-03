package com.mengyunzhi.measurement.controller;

import org.junit.Test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SystemConfigControllerTest extends ControllerTest {
    @Test
    public void findAll() throws Exception {
        String url = "/SystemConfig/getAll";
        this.mockMvc
                .perform(get(url)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("SystemConfig_getAll", preprocessResponse(prettyPrint())));
    }
}