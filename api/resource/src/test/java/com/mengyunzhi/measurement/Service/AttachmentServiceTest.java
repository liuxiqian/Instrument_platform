package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Attachment;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

/**
 * Created By chuhang on 17-9-21
 */
public class AttachmentServiceTest extends ServiceTest {
    static private Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceImplTest.class.getName());
    @Autowired
    private AttachmentService attachmentService;


    @Test
    public void uploadImage() throws Exception {
        logger.info("新一个图片文件");
        // 默认查找测试文件中resources目录下当前包下的文件
        ClassPathResource resource = new ClassPathResource("example.png", getClass());
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "attachment",                                 // 字段名
                "example.png",                         // 文件名
                ContentType.APPLICATION_OCTET_STREAM.toString(),   // 文件类型
                new FileInputStream(resource.getFile())
        );
        Attachment attachment = attachmentService.uploadImage(mockMultipartFile);

        logger.info("断言附件是否已保存");
        assertThat(attachment.getId()).isNotNull();

        logger.info("删除测试生成的附件");
        attachmentService.delete(attachment.getId());
    }

}