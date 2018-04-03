package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 器具检定能力 controller测试
 * poshichao
 */
public class CheckAbilityStatisticsControllerTest extends ControllerTest {
    private static final Logger logger = Logger.getLogger(CheckAbilityStatisticsControllerTest.class.getName());
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictTypeRepository districtTypeRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired
    private CheckAbilityStatisticsRepository checkAbilityStatisticsRepository;

    @Test
    public void getAllBySpecification() throws Exception {
        logger.info("新建一个器具检定能力统计 对象");
        CheckAbilityStatistics checkAbilityStatistics = new CheckAbilityStatistics();

        logger.info("为对象赋值并持久化");
        logger.info("为对象赋值");
        District district = new District();
        district.setName("测试市");
        DistrictType districtType = new DistrictType();
        districtType.setName("市");
        districtTypeRepository.save(districtType);
        district.setDistrictType(districtType);
        districtRepository.save(district);
        Department department = new Department();
        department.setDistrict(district);
        departmentRepository.save(department);
        Discipline discipline = new Discipline();
        discipline.setName("测试学科类别");
        disciplineRepository.save(discipline);
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        checkAbilityStatistics.setMandatoryInstrument(mandatoryInstrument, null, 1, 1, null, discipline, department, district);
        checkAbilityStatisticsRepository.save(checkAbilityStatistics);

        logger.info("拼接url");
        String url = "/CheckAbilityStatistics/getAllBySpecification?districtId=" + district.getId().toString() + "&disciplineId=" + discipline.getId().toString();
        logger.info("模拟请求并断言");
        this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andDo(document("CheckAbilityStatistics_getAllBySpecification", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }

    @Test
    public void dataInit() throws Exception {
        logger.info("设置路径");
        String url = "/CheckAbilityStatistics/dataInit";

        logger.info("模拟请求并断言");
        this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andDo(document("CheckAbilityStatistics_dataInit", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }

    @Test
    public void hasFinished() throws Exception {
        logger.info("拼接url");
        String url = "/CheckAbilityStatistics/hasFinished";

        logger.info("模拟请求并断言");
        this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andDo(document("CheckAbilityStatistics_hasFinished", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllByDistrictAndDiscipline() throws Exception {
        logger.info("拼接url");
        String url = "/CheckAbilityStatistics/getAllByDistrictAndDiscipline";

        logger.info("模拟请求并断言");
        this.mockMvc
                .perform(get(url)
                        .contentType("application/json")
                        .header("x-auth-token", xAuthToken))
                .andDo(document("CheckAbilityStatistics_getAllByDistrictAndDiscipline", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllByDistrictAndInstrument() throws Exception {
        logger.info("拼接url");
        String url = "/CheckAbilityStatistics/getAllByDistrictAndInstrumentType";

        logger.info("模拟请求并断言");
        this.mockMvc
                .perform(get(url)
                    .contentType("application/json")
                    .header("x-auth-token", xAuthToken))
                .andDo(document("CheckAbilityStatistics_getAllByDistrictAndInstrument", preprocessResponse(prettyPrint())))
                .andExpect(status().isOk());
    }

}