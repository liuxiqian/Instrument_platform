package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.entity.ManagementDepartmentBackedReason;
import com.mengyunzhi.measurement.repository.ManagementDepartmentBackedReasonRepository;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created By chuhang on 17-11-02
 * 管理部门退回原因控制器测试
 */

public class ManagementDepartmentBackedReasonControllerTest extends ControllerTest {
    private static Logger logger = Logger.getLogger(ManagementDepartmentBackedReasonControllerTest.class.getName());
    @Autowired
    ManagementDepartmentBackedReasonRepository managementDepartmentBackedReasonRepository;

    @Test
    public void getTop10ManagementDepartmentBackedReasons() throws Exception {
        logger.info("新建20个退回原因");
        for (int i = 0; i < 20; i++) {
            ManagementDepartmentBackedReason managementDepartmentBackedReason = new ManagementDepartmentBackedReason();
            managementDepartmentBackedReason.setReason("退回原因" + i);
            managementDepartmentBackedReasonRepository.save(managementDepartmentBackedReason);
        }

        logger.info("模拟请求");
        String mvcResult = this.mockMvc
                .perform(get("/ManagementDepartmentBackedReason/getTop10ManagementDepartmentBackedReasons")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("ManagementDepartmentBackedReason_getTop10ManagementDepartmentBackedReasons", preprocessResponse(prettyPrint())))
                .andReturn().getResponse().getContentAsString();

        logger.info("断言");
        JSONArray jsonArray = JSONArray.fromObject(mvcResult);
        assertThat(jsonArray.size()).isEqualTo(10);

    }

}