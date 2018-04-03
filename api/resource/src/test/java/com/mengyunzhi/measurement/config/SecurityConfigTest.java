package com.mengyunzhi.measurement.config;

import com.mengyunzhi.measurement.ResourceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by panjie on 17/5/18.
 */
@SpringBootTest(classes = ResourceApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/asciidoc/snippets")
public class SecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getToken() throws Exception {
        mockMvc.perform(get("/getToken"))
        .andDo(document("getToken", preprocessResponse(prettyPrint())));
    }

}