package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.DistrictService;
import com.mengyunzhi.measurement.entity.District;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangxishuo on 2018/3/23
 */
public class InstrumentCheckedRateControllerTest extends ControllerTest {

    private Logger logger = Logger.getLogger(InstrumentCheckedRateControllerTest.class);

    @Autowired
    private DistrictService districtService;

    @Test
    public void getCheckedRateByYearsTest() throws Exception {

        logger.info("初始化数据");
        List<Short> years = new ArrayList<>();
        years.add((short) 2017);
        years.add((short) 2018);
        District district = districtService.getOneSavedDistrict();

        logger.info("拼接url");
        String urlYear = "";
        for (Short year : years) {
            urlYear = urlYear + year + ",";
        }
        String url = "/InstrumentCheckedRate/getAllCheckedRate/years/" + urlYear + "/districtId/" + district.getId();

        logger.info("请求");
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk());
    }

    @Test
    public void getCheckedRateByYearTest() throws Exception {
        logger.info("初始化数据");
        District district = districtService.getOneSavedDistrict();

        logger.info("拼接url");
        String url = "/InstrumentCheckedRate/getAllCheckedRate/year/" + 2018 + "/districtId/" + district.getId();

        logger.info("请求");
        this.mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk());
    }
}