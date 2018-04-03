package com.mengyunzhi.measurement.controller;

import com.alibaba.fastjson.JSON;
import com.mengyunzhi.measurement.Service.*;
import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.ApplyFieldRepository;
import com.mengyunzhi.measurement.repository.ApplyRepository;
import com.mengyunzhi.measurement.repository.ApplyTypeRepository;
import com.mengyunzhi.measurement.repository.WorkRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panjie on 17/7/18.
 * 工作
 */
public class WorkControllerTest extends ControllerTest {
    private static final Logger logger = Logger.getLogger(WorkControllerTest.class.getName());
    @Autowired
    private WorkRepository workRepository;   // 工作
    @Autowired
    private ApplyRepository applyRepository;    // 申请
    @Autowired
    @Qualifier("WorkService")
    private WorkService workService;
    @Autowired
    private WorkflowNodeService workflowNodeService;
    @Autowired
    @Qualifier("ApplyService")
    private ApplyService applyService;

    @Autowired
    private ApplyFieldRepository applyFieldRepository;  // 申请字段

    @Autowired
    private ApplyTypeService applyTypeService;   // 申请类型
    @Autowired
    private ApplyTypeRepository applyTypeRepository;
    @Autowired
    private ApplyFieldService applyFieldService; // 申请字段

    @Test
    public void getByIdTest() throws Exception {
        Work work = workService.getOneUnSavedWork();

        logger.info("持久化申请类型");
        ApplyType applyType = applyTypeService.getOneSavedApplyType();

        logger.info("持久化申请");
        Apply apply = applyService.getOneSavedApply();
        work.setApply(apply);

        logger.info("持久化申请字段");
        ApplyField applyField = applyFieldService.getOneApplyField();
        applyField.setApplyType(applyType);
        applyFieldRepository.save(applyField);
        applyTypeRepository.save(applyType);

        workRepository.save(work);
        String url = "/Work/" + work.getId().toString();

        MvcResult mvcResult = this.mockMvc
                .perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(200))
                .andDo(document("Work_getById", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        logger.info("断言获取到了工作实体");
        assertThat(jsonObject.optLong("id")).isEqualTo(work.getId());

    }

    @Test
    public void getNextWorkById() throws Exception {
        Work work = workService.getOneSavedWork();
        Work nextWork = workService.getOneUnSavedWork();
        nextWork.setPreWork(work);
        workRepository.save(nextWork);
        String url = "/Work/getNextWorkById/" + work.getId().toString();
        MvcResult mvcResult = this.mockMvc
                .perform(
                        get(url).header("x-auth-token", xAuthToken)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                ).andExpect(status().is(200)).andReturn();
        Work work1 = JSON.parseObject(mvcResult.getResponse().getContentAsString(), Work.class);
        assertThat(work1.getId()).isEqualTo(nextWork.getId());


    }

    @Test
    public void updateToDoingById() throws Exception {
        Work work = workService.getOneUnSavedWork();
        workRepository.save(work);
        this.mockMvc.perform(patch("/Work/updateToDoingById/" + work.getId().toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("Work_updateToDoingById", preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAllByApplyId() throws Exception {
        Apply apply = applyService.getOneSavedApply();
        applyRepository.save(apply);
        Work work = workService.getOneUnSavedWork();
        work.setApply(apply);
        workRepository.save(work);
        MvcResult mvcResult = this.mockMvc.perform(
                get("/Work/getAllByApplyId/" + apply.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().is(200))
                .andDo(document("Work_getAllByApplyId", preprocessResponse(prettyPrint())))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONArray jsonArray = JSONArray.fromObject(content);
        assertThat(jsonArray.size()).isEqualTo(1);
    }

    @Test
    public void audit() throws Exception {
        User user = userService.loginWithOneUser();
        logger.info("新建一个新申请");
        Apply apply = applyService.getOneSavedApply();

        applyRepository.save(apply);
        logger.info("新建一个新工作");
        Work work = new Work();
        work.setApply(apply);
        work.setAuditDepartment(user.getDepartment());
        workService.saveWorkWithCurrentUserAudit(work);

        logger.info("新建一个工作流经点");
        WorkflowNode workflowNode = workflowNodeService.getOneCompleteWorkflowNode();

        Work nextWork = new Work();
        nextWork.setAuditDepartment(user.getDepartment());
        nextWork.setWorkflowNode(workflowNode);

        work.setAuditType(Work.AUDIT_TYPE_SEND_TO_NEXT_DEPARTMENT);
        work.setOpinion("这里是审核内容");

        JSONObject nextWorkJsonObject = new JSONObject();
        nextWorkJsonObject.put("auditDepartment", "{id:" + nextWork.getAuditDepartment().getId().toString() + "}");
        nextWorkJsonObject.put("workflowNode", "{id: " + nextWork.getWorkflowNode().getId().toString() + "}");

        JSONObject workJsonObject = new JSONObject();
        workJsonObject.put("id", work.getId().toString());
        workJsonObject.put("pinion", work.getOpinion());
        workJsonObject.put("auditType", work.getAuditType());
        workJsonObject.put("nextWork", nextWorkJsonObject);
        workJsonObject.put("apply", "{id: " + apply.getId().toString() + "}");
        workJsonObject.put("auditDepartment", "{id:" + nextWork.getAuditDepartment().getId().toString() + "}");

        String content = workJsonObject.toString();

        this.loginByUser(user);
        this.mockMvc
                .perform(patch("/Work/audit")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken)
                        .content(content))
                .andExpect(status().is(204))
                .andDo(document("Work_audit", preprocessResponse(prettyPrint())));
        Work work1 = workRepository.findOne(work.getId());
    }

    @Test
    public void getPageOfCurrentUserByApplyTypeNameTest() throws Exception {
        User user = userService.loginWithOneUser();
        String url = "/Work/getPageOfCurrentUserByApplyTypeFlag/applyTypeFlag";
        this.loginByUser(user);
        this.mockMvc
                .perform(get(url).header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Work_getPageOfCurrentUserByApplyTypeFlag", preprocessResponse(prettyPrint())));
    }



    @Test
    public void pageUnHandleWorkOfCurrentUser() throws Exception {
        String url = "/Work/pageUnHandleWorkOfCurrentUser";
        this.mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8).header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("work_pageUnHandleWorkOfCurrentUser", preprocessResponse(prettyPrint())));
    }

    @Test
    public void pageHandlingWorkOfCurrentUser() throws Exception {
        String url = "/Work/pageHandlingWorkOfCurrentUser";
        this.mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8).header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("work_pageHandlingWorkOfCurrentUser", preprocessResponse(prettyPrint())));
    }

    @Test
    public void pageHandledWorkOfCurrentUser() throws Exception {
        String url = "/Work/pageHandledWorkOfCurrentUser";
        this.mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8).header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("work_pageHandledWorkOfCurrentUser", preprocessResponse(prettyPrint())));
    }

    @Test
    public void pageDoneWorkOfCurrentUser() throws Exception {
        String url = "/Work/pageDoneWorkOfCurrentUser";
        this.mockMvc
                .perform(get(url).contentType(MediaType.APPLICATION_JSON_UTF8).header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("work_pageDoneWorkOfCurrentUser", preprocessResponse(prettyPrint())));
    }

}
