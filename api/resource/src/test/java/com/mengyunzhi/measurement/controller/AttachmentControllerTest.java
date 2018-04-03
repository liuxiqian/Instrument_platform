package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.AttachmentService;
import com.mengyunzhi.measurement.entity.Attachment;
import com.mengyunzhi.measurement.repository.AttachmentRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentApplyRepository;
import net.sf.json.JSONObject;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;

import java.io.FileInputStream;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created By chuhang on 17-9-20
 * 附件控制器测试
 */
public class AttachmentControllerTest extends ControllerTest {
    private Logger logger = Logger.getLogger(CertificateStatusControllerTest.class.getName());
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;
    @Autowired
    private AttachmentService attachmentService;

    @Test
    public void deleteTest() throws Exception {
        logger.info("新一个图片文件");
        // 默认查找测试文件中resources目录下当前包下的文件
        ClassPathResource resource = new ClassPathResource("test.png", getClass());
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "attachment",                                 // 字段名
                "test.png",                         // 文件名
                ContentType.APPLICATION_OCTET_STREAM.toString(),   // 文件类型
                new FileInputStream(resource.getFile())
        );

        logger.info("保存附件");
        Attachment attachment = attachmentService.uploadImage(mockMultipartFile);

        logger.info("发送模拟数据");
        this.mockMvc.perform(delete("/Attachment/" + attachment.getId().toString())
                .contentType("application/json")
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(204))
                .andDo(document("Attachment_delete", preprocessResponse(prettyPrint())));

        logger.info("断言是否删除成功");
        assertThat(attachmentRepository.findOne(attachment.getId())).isNull();
    }

    @Test
    public void uploadImage() throws Exception {
        logger.info("新一个图片文件");
        // 默认查找测试文件中resources目录下当前包下的文件
        ClassPathResource resource = new ClassPathResource("test.png", getClass());
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "attachment",                                 // 字段名
                "test.png",                         // 文件名
                ContentType.APPLICATION_OCTET_STREAM.toString(),   // 文件类型
                new FileInputStream(resource.getFile())
        );

        logger.info("模拟请求");
        MvcResult mvcResult = this.mockMvc.perform(fileUpload("/Attachment/uploadImage")
                .file(mockMultipartFile)
                .header("x-auth-token", xAuthToken))
                .andExpect(status().is(201))
                .andDo(document("Attachment_uploadImage", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("断言图片是否上传成功");
        String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = JSONObject.fromObject(content);
        assertThat(jsonObject.getLong("id")).isNotNull();

        logger.info("删除上传的图片");
        attachmentService.delete(jsonObject.getLong("id"));
    }


    /**
     * 进行mvn test时，将导致屏幕打印返回结果。
     * 如果test.png为正常的图片，则直接将二进制的图片进行屏幕打印
     * 此时，便打印出了乱码。
     * 解决方法：建立个文本文档，然后将其改名为test.png
     * @author 潘杰
     * @throws Exception
     */
    @Test //
    public void image() throws Exception {
        logger.info("新一个图片文件");
        // 默认查找测试文件中resources目录下当前包下的文件
        ClassPathResource resource = new ClassPathResource("test.png", getClass());
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "attachment",                                 // 字段名
                "test.png",                         // 文件名
                ContentType.APPLICATION_OCTET_STREAM.toString(),   // 文件类型
                new FileInputStream(resource.getFile())
        );
        logger.info("保存附件");
        Attachment attachment = attachmentService.uploadImage(mockMultipartFile);

        logger.info("模拟请求测试");
        MvcResult mvcResult = this.mockMvc.perform(get("/Attachment/image/" + attachment.getSaveName())
                .header("x-auth-token", xAuthToken))
                .andExpect(status().isOk())
                .andDo(document("Attachment_image", preprocessResponse(prettyPrint())))
                .andReturn();

        logger.info("删除测试生成的附件");
        attachmentService.delete(attachment.getId());
    }

}