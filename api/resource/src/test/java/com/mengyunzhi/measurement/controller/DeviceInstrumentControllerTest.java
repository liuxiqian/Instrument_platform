package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.CommonService;
import com.mengyunzhi.measurement.Service.DeviceInstrumentService;
import com.mengyunzhi.measurement.Service.DeviceInstrumentServiceTestData;
import com.mengyunzhi.measurement.Service.UserService;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.DeviceInstrument;
import com.mengyunzhi.measurement.entity.DeviceSet;
import com.mengyunzhi.measurement.entity.User;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.client.HttpResponseException;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目save实体
 */
public class DeviceInstrumentControllerTest extends ControllerTest {
    static private Logger logger = Logger.getLogger(DeviceInstrumentControllerTest.class.getName());
    @Autowired
    protected UserService userService;                                          // 用户
    @Autowired
    protected UserRepository userRepository;                                    // 用户
    @Autowired
    protected DepartmentTypeRepository departmentTypeRepository;                // 部门类型
    @Autowired
    private DeviceInstrumentServiceTestData deviceInstrumentServiceTestData;    // 数据准备
    @Autowired
    private DeviceSetControllerTestDataInit deviceSetControllerTestDataInit;
    @Autowired
    private MeasureScaleRepository measureScaleRepository;
    @Autowired
    private AccuracyRepository accuracyRepository;
    @Autowired
    private DeviceSetRepository deviceSetRepository;
    @Autowired
    private DeviceInstrumentRepository deviceInstrumentRepository;

    @Test
    public void pageAllByCurrentUserOfDeviceInstrument() throws Exception {
        logger.info("数据初始化");
        deviceInstrumentServiceTestData.pageAllByCurrentUserOfDeviceInstrument();

        logger.info("模拟请求");
        this.mockMvc.perform(get("/DeviceInstrument/pageAllByCurrentUserOfDeviceInstrument" + "?page=1&size=1")
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DeviceInstrument_pageAllByCurrentUserOfDeviceInstrument", preprocessResponse(prettyPrint())));
    }

    @Test
    public void pageByDeviceSetOfCurrentUser() throws Exception {
        DeviceSet deviceSet = new DeviceSet();
        User loginUser = deviceInstrumentServiceTestData.pageByDeviceSetOfCurrentUser(deviceSet);

        this.loginByUser(loginUser);
        String url = "/DeviceInstrument/pageByDeviceSetIdOfCurrentUser/" + deviceSet.getId().toString() + "?page=0&size=2";
        MvcResult mvcResult = this.mockMvc.perform(get(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andDo(document("DeviceInstrument_pageByDeviceSetIdOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        String content1 = mvcResult.getResponse().getContentAsString();
        assertThat(JSONObject.fromObject(content1).get("totalElements")).isEqualTo(2);

        url = "/DeviceInstrument/pageOfCurrentUser?page=0&size=2";
        MvcResult mvcResult1 = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DeviceInstrument_pageOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        String response = mvcResult1.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(response);
        assertThat(jsonObject.get("totalElements")).isEqualTo(4);

        logger.info("断言输出了精度及测试范围信息");
        JSONArray jsonArray = (JSONArray) jsonObject.get("content");
        JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
        assertThat(jsonObject1.get("minMeasureScale")).isNotNull();
        assertThat(jsonObject1.get("maxMeasureScale")).isNotNull();
        assertThat(jsonObject1.get("accuracy")).isNotNull();
    }

    @Test
    public void pageAllOfCurrentUserBySpecification() throws Exception {
        // 数据初始化
        User user = userService.loginWithOneUser();
        String name = CommonService.getRandomStringByLength(10);        // 名称

        // 调用数据准备
        deviceInstrumentServiceTestData.pageAllOfCurrentManageDepartmentBySpecification(name, user);

        String url = "/DeviceInstrument/pageAllOfCurrentUserBySpecification?name=" + name + "&page=0&size=2";

        this.loginByUser(user);
        String content = this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DeviceInstrument_pageByDeviceSetIdOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("totalElements")).isEqualTo(1);
    }


    @Test
    public void checkAbilityByAccuracyIdAndMeasureScaleIdAndInstrumentTypeIdAndDepartmentId() throws Exception {
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        Department department = new Department();
        deviceSetControllerTestDataInit.countByDeviceInstrumentAndDeviceSetDepartment(deviceInstrument, department);
        logger.info("调用方法，并断言返回的条目数为1");
        String url = "/DeviceInstrument/checkAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId" +
                "/accuracyId/" +
                deviceInstrument.getAccuracy().getId().toString() +
                "/minMeasureScaleId/" +
                deviceInstrument.getMinMeasureScale().getId().toString() +
                "/maxMeasureScaleId/" +
                deviceInstrument.getMaxMeasureScale().getId().toString() +
                "/instrumentTypeId/" +
                deviceInstrument.getInstrumentType().getId().toString() +
                "/departmentId/" +
                department.getId().toString();

        MvcResult result = this.mockMvc
                .perform(get(url)
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("DeviceInstrument_checkAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(content).isEqualTo("true");
    }

    /**
     * 过期的方法，返回410及异常
     *
     * @throws Exception
     */
    @Test
    public void countByAccuracyIdAndMeasureScaleIdAndDepartmentId() throws Exception {
        String url = "/DeviceInstrument/countByAccuracyIdAndMeasureScaleIdAndDepartmentId" +
                "/accuracyId/1/measureScaleId/2/departmentId/3";

        Boolean catchException = false;
        try {
            this.mockMvc
                    .perform(get(url)
                            .header("x-auth-token", xAuthToken)
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().is(410));
        } catch (HttpResponseException e) {
            catchException = true;
            assertThat(e.getStatusCode()).isEqualTo(410);
        }

        assertThat(catchException).isTrue();
    }

    @Test
    public void deleteTest() throws Exception {
        logger.info("实例化器具二级类别");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        deviceInstrumentServiceTestData.deleteTest(deviceInstrument);

        String url = "/DeviceInstrument/" + deviceInstrument.getId().toString();
        this.mockMvc.perform(delete(url)
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("DeviceInstrument_delete", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getByDeviceSetIdAndInstrumentTypeIdTest() throws Exception {
        logger.info("测试数据");
        DeviceInstrument deviceInstrument1 = new DeviceInstrument();
        deviceInstrumentServiceTestData.deleteTest(deviceInstrument1);
        String url = "/DeviceInstrument/deviceSetId/" + deviceInstrument1.getDeviceSet().getId().toString()
                + "/instrumentTypeId/" + deviceInstrument1.getInstrumentType().getId().toString();

        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("DeviceInstrument_getByDeviceSetIdAndInstrumentTypeId", preprocessResponse(prettyPrint())))
                .andReturn();

        JSONObject deviceInstrumentJsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        assertThat(deviceInstrumentJsonObject.get("id")).isNotNull();
    }

    @Autowired
    DeviceInstrumentService deviceInstrumentService;    // 授权检定项目

    @Test
    public void saveTest() throws Exception {
        logger.info("由于要使用到用户的部门进行权限判断，所以首先使用带部门的用户进行模拟登录");
        User user = userService.loginWithOneUser();

        String url = "/DeviceInstrument/";
        DeviceInstrument deviceInstrument = deviceInstrumentService.getOneSavedDeviceInstrument();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accuracy", "{id: " + deviceInstrument.getAccuracy().getId().toString() + "}");
        jsonObject.put("minMeasureScale", "{id: " + deviceInstrument.getMinMeasureScale().getId().toString() + "}");
        jsonObject.put("maxMeasureScale", "{id: " + deviceInstrument.getMaxMeasureScale().getId().toString() + "}");
        jsonObject.put("deviceSet", "{id: " + deviceInstrument.getDeviceSet().getId().toString() + "}");
        jsonObject.put("instrumentType", "{id: " + deviceInstrument.getInstrumentType().getId().toString() + "}");

        this.loginByUser(user);
        this.mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token",xAuthToken)
                        .content(jsonObject.toString()))
                .andExpect(status().is(201))
                .andDo(document("DeviceInstrument_save", preprocessResponse(prettyPrint())));
    }
}