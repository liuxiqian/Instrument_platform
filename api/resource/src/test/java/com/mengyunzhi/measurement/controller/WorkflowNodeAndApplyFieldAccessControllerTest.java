package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.WorkflowNodeAndApplyFieldAccessService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工作流结点 - 申请字段 权限
 */
public class WorkflowNodeAndApplyFieldAccessControllerTest extends ControllerTest{
    @Autowired
    WorkflowNodeAndApplyFieldAccessService workflowNodeAndApplyFieldAccessService;
    @Test
    public void getByWorkflowNodeIdAndApplyFieldIdTest() throws Exception {
//        WorkflowNodeAndApplyFieldAccess workflowNodeAndApplyFieldAccess = workflowNodeAndApplyFieldAccessService.getOneSavedEntity();
//        String url = "/WorkflowNodeAndApplyFieldAccess/getByWorkflowNodeIdAndApplyFieldId/workflowNodeId/" +
////                workflowNodeAndApplyFieldAccess.getWorkflowNode().getId().toString() +
//                "/applyFieldId/" +
//                workflowNodeAndApplyFieldAccess.getApplyField().getId().toString();
//
//        MvcResult mvcResult = this.mockMvc
//                .perform(get(url).header("x-auth-token", xAuthToken))
//                .andExpect(status().isOk())
//                .andDo(document("WorkflowNodeAndApplyFieldAccess_getByWorkflowNodeIdAndApplyFieldId", preprocessResponse(prettyPrint())))
//                .andReturn();
//
//        JSONObject jsonObject = JSONObject.fromObject(mvcResult.getResponse().getContentAsString());
//        assertThat(jsonObject.getLong("id")).isEqualTo(workflowNodeAndApplyFieldAccess.getId());
    }

}