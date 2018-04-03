package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by liming on 17-7-15.
 */
public class MandatoryInstrumentApplyControllerTest extends ControllerTest {

    static private Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceImplTest.class.getName());
    @Autowired
    protected UserService userService;
    @Autowired
    protected WorkRepository workRepository;
    @Autowired
    protected MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;
    @Autowired
    private MandatoryInstrumentApplyService mandatoryInstrumentApplyService; // 强制检定器具
    @Autowired
    private MandatoryInstrumentApplyServiceImplTestData mandatoryInstrumentApplyServiceTestData; // 强制检定申请测试数据
    @Autowired
    protected WorkflowTypeRepository workflowTypeRepository;        // 工作流类型
    @Autowired
    protected WorkflowNodeService workflowNodeService;              // 工作流结点
    @Autowired
    protected WorkflowNodeRepository workflowNodeRepository;         // 工作流结点
    @Autowired
    protected DepartmentRepository departmentRepository; // 部门
    @Autowired
    private UserRepository userRepository;                //e 用户
    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier("DepartmentService")
    private DepartmentService departmentService; // 部门
    @Autowired
    MeasureScaleService measureScaleService;                 // 测试范围
    @Autowired
    AccuracyService accuracyService;                         // 精确度
    @Autowired
    DeviceSetService deviceSetService;                       // 标准装置
    @Autowired
    DeviceSetRepository deviceSetRepository;                 // 标准装置
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;   // 强制检定
    @Autowired
    DeviceInstrumentRepository deviceInstrumentRepository;       // 授权检定范围
    @Autowired
    MandatoryInstrumentServiceTestData mandatoryIntegratedServiceTestData;   // 测试数据
    @Autowired
    AttachmentRepository attachmentRepository;                  //附件
    @Autowired
    AttachmentService attachmentService;                    //附件
    @Autowired @Qualifier("WorkService") WorkService workService;   // 工作

    @Test
    public void getPageOfCurrentDepartment() throws Exception {
        logger.info("设置部门");
        Department department = new Department();
        departmentRepository.save(department);
        logger.info("设置当前登录用户");
        User user = new User();
        user.setDepartment(department);
        userRepository.save(user);
        userService.setCurrentLoginUser(user);
        logger.info("设置申请");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        logger.info("设置工作");
        Work work = new Work();
        work.setAuditDepartment(department);
        work.setApply(mandatoryInstrumentApply);
        workRepository.save(work);

        logger.info("测试");
        this.mockMvc.perform(get("/MandatoryInstrumentApply/getPageOfCurrentDepartment" + "?page=1&size=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andDo(document("MandatoryInstrumentApply_getPageOfCurrentDepartment", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());

        logger.info("删除数据");
        workRepository.delete(work);
        mandatoryInstrumentApplyRepository.delete(mandatoryInstrumentApply);
        userRepository.delete(user);
        departmentRepository.delete(department);
    }

    @Test
    public void findById() throws Exception {
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();

        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        assertThat(mandatoryInstrumentApplyService.findById(mandatoryInstrumentApply.getId())).isNotNull();
        MvcResult mvcResult = this.mockMvc.perform(get("/MandatoryInstrumentApply/findById/" + mandatoryInstrumentApply.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrumentApply_findById", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.get("id")).isEqualTo(mandatoryInstrumentApply.getId().intValue());
        mandatoryInstrumentApplyRepository.delete(mandatoryInstrumentApply);
    }

    @Test
    public void save() throws Exception {

        logger.info("构造json串");
        JSONObject workJsonObject = new JSONObject();
        workJsonObject.put("opinion", "申请意见");

        this.mockMvc.perform(post("/MandatoryInstrumentApply/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken)
                .content(workJsonObject.toString()))
                .andExpect(status().is(201))
                .andDo(document("MandatoryInstrumentApply_save", preprocessResponse(prettyPrint())));
    }

    @Test
    public void computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId() throws Exception {

        logger.info("新建技术机构");
        Department department = departmentService.getOneTechnicalInstitutionDepartment();

        logger.info("新建10个强制检定器具");
        JSONArray jsonArray = new JSONArray();
        Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();

        logger.info("数据准备");
        mandatoryIntegratedServiceTestData
                .computerCheckAbilityByDepartmentIdOfMandatoryInstruments(
                        department,
                        jsonArray,
                        mandatoryInstruments);

        logger.info("新建申请，并为申请加入强检器具");
        MandatoryInstrumentApply mandatoryInstrumentApply = new MandatoryInstrumentApply();
        mandatoryInstrumentApply.setMandatoryInstruments(mandatoryInstruments);
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);

        String url = "/MandatoryInstrumentApply/computeCheckAbilityBy/MandatoryInstrumentApplyId/"
                + mandatoryInstrumentApply.getId().toString() + "/DepartmentId/" +
                department.getId().toString();

        logger.info("模拟进行请求");
        this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(202))
                .andDo(document("MandatoryInstrumentApply_computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId", preprocessResponse(prettyPrint())));
    }

    /**
     * 通过审核但没有检定能力的器具生成一个PDF
     *
     * @throws Exception panjie
     */
    @Test
    public void generateDoNotHaveCheckAbilityPdfReportByApplyId() throws Exception {
        logger.info("测试测试数据准备");
        User user = userService.loginWithOneUser();
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyServiceTestData.generatePdfApplyById(user);

        String token = userService.getTempTokenOfCurrentUser();
        String url = "/MandatoryInstrumentApply/generateDoNotHaveCheckAbilityPdfReportByApplyId/"
                + mandatoryInstrumentApply.getId().toString()
                + "/withToken/";
//
//        this.mockMvc
//                .perform(get(url)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .param("token", token))
//                .andExpect(status().isOk());
    }

    @Test
    public void generatePdfReportByApplyIdAndCheckDepartmentId() throws Exception {
        logger.info("测试测试数据准备");
        User user = userService.loginWithOneUser();
        HashSet<Long> checkDepartmentIds = new HashSet<>();
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyServiceTestData.generatePdfReportByApplyIdAndCheckDepartmentId(user, checkDepartmentIds);

        String token = userService.getTempTokenOfCurrentUser();
        String baseUrl = "/MandatoryInstrumentApply/generatePdfReportByApplyId/" +
                mandatoryInstrumentApply.getId().toString()
                + "/checkDepartmentId/";
//        for (Long checkDepartmentId : checkDepartmentIds) {
//            String url = baseUrl + checkDepartmentId.toString() + "/withToken";
//            this.mockMvc
//                    .perform(get(url)
//                            .contentType(MediaType.APPLICATION_JSON_UTF8)
//                            .param("token", token))
//                    .andExpect(status().isOk());
//        }


    }

    @Test
    public void getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId() throws Exception {
        User user = userService.loginWithOneUser();
        logger.info("数据准备");
        MandatoryInstrumentApply mandatoryInstrumentApply =
                mandatoryInstrumentApplyServiceTestData.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(user);

        logger.info("请求信息");
        String url = "/MandatoryInstrumentApply/getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId/" +
                mandatoryInstrumentApply.getId().toString();
        this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("MandatoryInstrumentApply_getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId", preprocessResponse(prettyPrint())));
    }

    /**
     * 通过改变URL进行 一键指定 批量通过 批量退回的操作
     *
     * @param url
     * @throws Exception
     */
    private void requestByUrl(String url) throws Exception {
        String baseUrl = "/MandatoryInstrumentApply/" + url;
        String nickName = "MandatoryInstrumentApply_" + url;
        User user = userService.loginWithOneUser();
        logger.info("数据准备");
        MandatoryInstrumentApply mandatoryInstrumentApply =
                mandatoryInstrumentApplyServiceTestData.assignToTechnicalInstitutionByMandatoryInstruments(user);
        JSONArray jsonArray = new JSONArray();
        for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentApply.getMandatoryInstruments()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", mandatoryInstrument.getId().toString());
            jsonArray.add(jsonObject);
        }

        logger.info("请求信息");
        this.loginByUser(user);
        this.mockMvc
                .perform(patch(baseUrl)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken)
                        .content(jsonArray.toString())
                )
                .andExpect(status().is(204))
                .andDo(document(nickName, preprocessResponse(prettyPrint())));
        return;
    }

    @Test
    public void handleWhenApplyDoneByApplyId() throws Exception {
        User currentUser = userService.loginWithOneUser();
        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyServiceTestData.handleWhenApplyDoneByApplyId();
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        workService.saveWorkWithCurrentUserAudit(work);
        workService.updateToDoneByWork(work);

        this.loginByUser(currentUser);
        String url = "/MandatoryInstrumentApply/handleWhenApplyDoneByApplyId/" + mandatoryInstrumentApply.getId().toString();
        this.mockMvc
                .perform(patch(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("MandatoryInstrumentApply_handleWhenApplyDoneByApplyId", preprocessResponse(prettyPrint())));
    }

    @Test
    public void replyByIdAndReplyOpinions() throws Exception {

        MandatoryInstrumentApply mandatoryInstrumentApply = mandatoryInstrumentApplyService.getOneSavedMandatoryInstrumentApply();

        logger.info("触发第三个异常");
        User currentUser = userService.loginWithOneUser();

        logger.info("新建一个工作，并设置申请及审核部门");
        Work work = new Work();
        work.setApply(mandatoryInstrumentApply);
        mandatoryInstrumentApplyRepository.save(mandatoryInstrumentApply);
        work.setAuditDepartment(currentUser.getDepartment());
        workService.saveWork(work);

        this.loginByUser(currentUser);
        String url = "/MandatoryInstrumentApply/reply/" + mandatoryInstrumentApply.getId().toString();
        JSONObject jsonObject = new JSONObject();
        String opinion = "审核意见";
        jsonObject.put("replyOpinions", opinion);
        this.mockMvc
                .perform(patch(url)
                        .content(jsonObject.toString())
                        .header("x-auth-token", xAuthToken)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(204))
                .andDo(document("MandatoryInstrumentApply_reply", preprocessResponse(prettyPrint())));

    }

}