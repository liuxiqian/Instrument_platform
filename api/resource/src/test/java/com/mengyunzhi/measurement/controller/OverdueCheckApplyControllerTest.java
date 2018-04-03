package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.OverdueCheckApplyServiceImplTestData;
import com.mengyunzhi.measurement.Service.WorkService;
import com.mengyunzhi.measurement.entity.MandatoryInstrument;
import com.mengyunzhi.measurement.entity.OverdueCheckApply;
import com.mengyunzhi.measurement.entity.User;
import com.mengyunzhi.measurement.entity.Work;
import com.mengyunzhi.measurement.repository.OverdueCheckApplyRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;

import java.sql.Date;
import java.util.Calendar;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author panjie on 2017/12/13
 * 超期检定申请
 */
public class OverdueCheckApplyControllerTest extends ControllerTest {

    private final static Logger logger = Logger.getLogger(OverdueCheckApplyControllerTest.class.getName());
    @Autowired
    OverdueCheckApplyServiceImplTestData overdueCheckApplyServiceImplTestData;

    @Autowired
    OverdueCheckApplyRepository overdueCheckApplyRepository;    // 超期检定

    @Autowired @Qualifier("WorkService")
    WorkService workService; // 工作

    @Test
    public void saveTest() throws Exception {
        logger.info("初始化测试变量，调用M层方法");
        OverdueCheckApply overdueCheckApply = overdueCheckApplyServiceImplTestData.save();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reason", "过期未检原因");
        JSONArray jsonArray = new JSONArray();
        for (MandatoryInstrument mandatoryInstrument : overdueCheckApply.getMandatoryInstrumentSet()) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("id", mandatoryInstrument.getId().toString());
        }
        jsonObject.put("mandatoryInstrumentSet", jsonArray);

        logger.info("登录并发起请求，断言");
        String url = "/OverdueCheckApply/";
        this.loginByUser(overdueCheckApplyServiceImplTestData.getCurrentUser());
        this.mockMvc
                .perform(post(url)
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andDo(document("OverdueCheckApply_save", preprocessResponse(prettyPrint())))
                .andExpect(status().is(201));
    }

    /**
     * 回复
     * @throws Exception
     * panjie
     */
    @Test
    public void reply() throws Exception {
        logger.info("初始化数据");
        Work work = overdueCheckApplyServiceImplTestData.reply();
        JSONObject applyJsonObject = new JSONObject();
        applyJsonObject.put("agree", true);
        applyJsonObject.put("latestCheckDate", "2017-12-13");
        JSONObject workJsonObject = new JSONObject();
        workJsonObject.put("id", work.getId());
        workJsonObject.put("opinion", "申请意见");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("work", workJsonObject);
        jsonObject.put("apply", applyJsonObject);

        logger.info("发起请求");
        String url = "/OverdueCheckApply/";
        this.loginByUser(overdueCheckApplyServiceImplTestData.getCurrentUser());
        this.mockMvc
                .perform(patch(url)
                        .content(jsonObject.toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andDo(document("OverdueCheckApply_reply", preprocessResponse(prettyPrint())))
                .andExpect(status().is(202));

    }

    @Test
    public void pageOfCurrentUser() throws Exception {
        User currentUser = userService.loginWithOneUser();

        logger.debug("新建申请");
        OverdueCheckApply overdueCheckApply = new OverdueCheckApply();
        Calendar calendar = Calendar.getInstance();
        overdueCheckApply.setApplyTime(calendar);
        overdueCheckApplyRepository.save(overdueCheckApply);

        logger.info("新建工作");
        Work work = new Work();
        work.setApply(overdueCheckApply);
        workService.saveWorkWithCurrentUserAudit(work);

        String url = "/OverdueCheckApply/pageOfCurrentUser";
        Long time = calendar.getTimeInMillis();
        this.mockMvc
                .perform(get(url)
                        .param("startApplyDate", new Date(time).toString())
                        .param("endApplyDate", new Date(time).toString())
                        .param("applyDepartmentId", currentUser.getDepartment().getId().toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andDo(document("OverdueCheckApply_pageOfCurrentUser", preprocessResponse(prettyPrint())))
                .andExpect(status().is(200));

    }

}