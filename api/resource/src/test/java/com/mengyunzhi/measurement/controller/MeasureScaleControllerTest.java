package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.InstrumentTypeService;
import com.mengyunzhi.measurement.entity.InstrumentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author panjie on 2018/1/3
 */
public class MeasureScaleControllerTest extends ControllerTest {
    @Autowired
    @Qualifier("InstrumentTypeService")
    private InstrumentTypeService instrumentTypeService;

    @Test
    public void getAllByInstrumentTypeId() throws Exception {
        InstrumentType instrumentType = instrumentTypeService.getOneSavedInstrumentType();
        String url = "/MeasureScale/getAllByInstrumentTypeId/" + instrumentType.getId().toString();
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(url)
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(document("MeasureScale_getAllByInstrumentTypeId", preprocessResponse(prettyPrint())))
                .andExpect(status().is(200));
    }
}