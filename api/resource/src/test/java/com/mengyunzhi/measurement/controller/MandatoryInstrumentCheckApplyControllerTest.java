package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.DepartmentTypeRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentCheckApplyRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/11/21.
 * 强检申请
 */
public class MandatoryInstrumentCheckApplyControllerTest extends ControllerTest {

    private final static Logger logger = Logger.getLogger(MandatoryInstrumentCheckApplyControllerTest.class.getName());
    private
    @Autowired
    TechnicalInstitutionDepartmentService technicalInstitutionDepartmentService;    // 技术机构
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;                          // 强检器具
    @Autowired
    MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;                                      // 检定申请
    @Autowired
    MandatoryInstrumentCheckApplyService mandatoryInstrumentCheckApplyService;                                            // 检定申请
    @Autowired
    CheckApplyServiceTestData checkApplyServiceTestData;                            // 检定申请测试数据
    @Qualifier("applianceUserDepartmentServiceImpl")
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentTypeRepository departmentTypeRepository;   // 部门类型
    @Autowired
    @Qualifier("WorkService")
    WorkService workService; // 工作

    @Test
    public void audit() throws Exception {
        logger.info("数据准备");
        String userPhone = "12132324";
        User user = userService.loginWithOneUser();
        Long id = checkApplyServiceTestData.reply(user);

        logger.info("更新数据");
        String phone = "123232";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("acceptUserPhone", phone);
        String url = "/MandatoryInstrumentCheckApply/audit/" + id.toString();

        logger.info("发起请求");
        this.loginByUser(user);
        this.mockMvc
                .perform(patch(url)
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonObject.toString()))
                .andExpect(status().is(202))
                .andDo(document("MandatoryInstrumentCheckApply_audit", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getById() throws Exception {
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = mandatoryInstrumentCheckApplyService.getOneSavedCheckApply();
        String url = "/MandatoryInstrumentCheckApply/" + mandatoryInstrumentCheckApply.getId().toString();
        this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrumentCheckApply_getById", preprocessResponse(prettyPrint())));
    }

    /**
     * 获取当前登录用户的分页数据
     *
     * @throws Exception panjie
     */
    @Test
    public void pageOfCurrentUserTest() throws Exception {
        logger.info("使用一个新用户并登录");
        User user = userService.loginWithOneUser();

        logger.info("新建一个检定申请实体A, 并设置检定部门");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApplyA = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApplyA);

        Work work = new Work();
        work.setApply(mandatoryInstrumentCheckApplyA);
        workService.saveWorkWithCurrentUserAudit(work);

        logger.info("登录并验证");
        this.loginByUser(user);
        String url = "/MandatoryInstrumentCheckApply/pageOfCurrentUser";
        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("page", "0")
                        .param("size", "1")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrumentCheckApply_pageOfCurrentUser", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言");
        JSONObject jsonObject1 = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
        JSONArray jsonArray = JSONArray.fromObject(jsonObject1.get("content"));
        assertThat(jsonObject1.getInt("totalElements")).isEqualTo(1);

        logger.info("获取第一条数据");
        JSONObject jsonObjectItem = (JSONObject) jsonArray.get(0);

        logger.info("按前台的字段需求进行断言");
    }

    @Test
    public void saveTest() throws Exception {
        Department technicalInstitutionDepartment = technicalInstitutionDepartmentService.getOneTechnicalInstitutionDepartment();
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneSavedMandatoryInstrument();
        String url = "/MandatoryInstrumentCheckApply";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("remarks", "备注");
        jsonObject.put("checkPlace", "检定地点");
        jsonObject.put("technicalInstitutionDepartment", "{id: " + technicalInstitutionDepartment.getId().toString() + "}");
        jsonObject.put("mandatoryInstrumentSet", "[{id: " + mandatoryInstrument.getId().toString() + "}]");

        logger.debug("模拟请求");
        this.mockMvc
                .perform(post(url)
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonObject.toString()))
                .andExpect(status().is(202))
                .andDo(document("MandatoryInstrumentCheckApply_save", preprocessResponse(prettyPrint())))
                .andReturn();
    }


    /**
     * 测试CheckApplyController中的generatePdfApply方法
     *
     * @throws Exception
     * @author zhangxishuo
     */
    @Test
    public void generatePdfApplyByIdTest() throws Exception {
        logger.info("用户登录");
        User user = userService.loginWithOneUser();

        logger.info("检定申请设置申请人与申请部门");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = mandatoryInstrumentCheckApplyService.getOneUnSavedCheckApply();
        mandatoryInstrumentCheckApply.setCreateUser(user);
        mandatoryInstrumentCheckApply.setDepartment(user.getDepartment());
        user.getDepartment().getDepartmentType().setPinyin("jishujigou");
        departmentTypeRepository.save(user.getDepartment().getDepartmentType());

        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);

        logger.info("获取token, 拼接字符串");
        String token = userService.getTempTokenOfCurrentUser();
        String url = "/MandatoryInstrumentCheckApply/generatePdfApply/" + mandatoryInstrumentCheckApply.getId().toString() + "/withToken";

        logger.info("模拟请求");
//        this.mockMvc.perform(get(url)
//                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .param("token", token))
//                    .andExpect(status().isOk())
//                    .andDo(document("CheckApply_generatePdfApplyById", preprocessResponse(prettyPrint())))
//                    .andReturn();
    }
}