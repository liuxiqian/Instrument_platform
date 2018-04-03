package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.StandardDeviceService;
import com.mengyunzhi.measurement.entity.DeviceSet;
import com.mengyunzhi.measurement.entity.StandardDevice;
import com.mengyunzhi.measurement.entity.User;
import com.mengyunzhi.measurement.repository.DeviceSetRepository;
import com.mengyunzhi.measurement.repository.StandardDeviceRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-5-9.
 */
public class StandardDeviceControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(StandardDeviceControllerTest.class.getName());
    @Autowired
    protected DeviceSetRepository deviceSetRepository;
    @Autowired
    protected StandardDeviceRepository standardDeviceRepository;
    @Autowired
    protected StandardDeviceService standardDeviceService;

    @Test
    public void getAllByStandardFileId() throws Exception {
        logger.info("新建一个标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setDeviceSet(deviceSet);
        standardDeviceRepository.save(standardDevice);
        logger.info("测试");

        String content = this.mockMvc.perform(get("/StandardDevice/getAllByDeviceSetId/" + deviceSet.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("StandardDevice_getAllByStandardFile", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        logger.info("断言");
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(1);
        logger.info("删除数据");
        standardDeviceRepository.delete(standardDevice);
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void save() throws Exception {
        logger.info("新建一个计量标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        JSONObject deviceSetJsonObject = new JSONObject();
        deviceSetJsonObject.put("id", deviceSet.getId());
        logger.info("新建一个标准器");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceSet", deviceSetJsonObject);

        logger.info("保存");
        this.mockMvc.perform(post("/StandardDevice")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken)
                .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andDo(document("StandardDevice_save", preprocessResponse(prettyPrint())));

        logger.info("删除数据");
        deviceSetRepository.delete(deviceSet);

    }

    @Test
    public void update() throws Exception {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setName("name");
        standardDeviceService.save(standardDevice);
        logger.info("更改名字");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "newName");
        this.mockMvc.perform(put("/StandardDevice/update/" + standardDevice.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken)
                .content(jsonObject.toString()))
                .andExpect(status().is(204))
                .andDo(document("StandardDevice_update", preprocessResponse(prettyPrint())));

        logger.info("删除实体");
        standardDeviceRepository.delete(standardDevice);
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDeviceService.save(standardDevice);
        this.mockMvc.perform(delete("/StandardDevice/delete/" + standardDevice.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("StandardDevice_delete", preprocessResponse(prettyPrint())));
    }

    @Test
    public void pageAllByDeviceSetId() throws Exception {
        logger.info("新建一个标准装置");
        DeviceSet deviceSet = new DeviceSet();
        deviceSetRepository.save(deviceSet);
        logger.info("新建一个标准器");
        StandardDevice standardDevice = new StandardDevice();
        standardDevice.setDeviceSet(deviceSet);
        standardDeviceRepository.save(standardDevice);
        logger.info("测试");

        this.mockMvc.perform(get("/StandardDevice/getAllByDeviceSetId/" + deviceSet.getId().toString() + "?page=1&size=1")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("StandardDevice_pageAllByStandardFile", preprocessResponse(prettyPrint())));
        logger.info("删除数据");
        standardDeviceRepository.delete(standardDevice);
        deviceSetRepository.delete(deviceSet);
    }

    @Test
    public void pageAllWarnStandardDevice() throws Exception {
        logger.info("标准器预警");
        User user = userService.loginWithOneUser();
        this.loginByUser(user);
        this.mockMvc.perform(get("/StandardDevice/pageAllWarnStandardDevice/")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("standardDevice_pageAllWarnStandardDevice", preprocessResponse(prettyPrint())));

    }

    @Test
    public void pageAllAlarmStandardDevice() throws Exception {
        logger.info("标准器报警");
        User user = userService.loginWithOneUser();
        this.loginByUser(user);
        this.mockMvc.perform(get("/StandardDevice/pageAllAlarmStandardDevice/")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("standardDevice_pageAllAlarmStandardDevice", preprocessResponse(prettyPrint())));

    }

    @Test
    public void findById() throws Exception {
        StandardDevice standardDevice = standardDeviceService.getOneSavedStandardDevice();
        String url = "/StandardDevice/" + standardDevice.getId().toString();
        this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("standardDevice_findById", preprocessResponse(prettyPrint())));

    }

}