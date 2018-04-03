package com.mengyunzhi.measurement.Service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by kongrp on 17/11/12.
 * 文件
 */
public class FileServiceTest extends ServiceTest{
    @Autowired FileService fileService;
    Logger logger = Logger.getLogger(FileServiceTest.class.getName());
    private  MockHttpServletResponse response;
    @Before
    public void before() {
        logger.info("HttpServletResponse是个接口，没有发送http请求时，SPRING 无法为我们自动注入");
        logger.info("所以在这里，示例化一个模拟response");
        response = new MockHttpServletResponse();
    }

    /**
     * 下载文件，下载成功后删除文件
     * @throws Exception
     * panjie
     */
    @Test
    public void downloadFileAndDeleteOnFinishByFileAndExtensionNameAndHttpServletResponse() throws Exception {
        logger.info("新建文件，并指定写入空间为1个字节");
        String filename = "test.xlsx";
        FileOutputStream fileOutputStream = new FileOutputStream(filename);

        logger.info("增加2个字节：范围为 -128 - 127");
        Byte bytes = Byte.valueOf("127");
        fileOutputStream.write(bytes);
        fileOutputStream.write(bytes);
        fileOutputStream.close();

        logger.info("下载文件，并删除");
        File file = new File(filename);
        fileService.downloadFileAndDeleteOnFinishByFileAndExtensionNameAndHttpServletResponse(
                file,
                FileService.EXTENSION_NAME_XLSX,
                response);

        logger.info("断言获取到了下载的文件及内容长度");
        assertThat(response.getContentType()).isEqualTo(FileService.EXTENSION_NAME_XLSX);
        Integer contentLength = response.getContentAsByteArray().length;
        assertThat(contentLength).isEqualTo(2);

        logger.info("TODO: 断言文件已删除, 文件删除成功");
//        assertThat(file.exists()).isFalse();
    }

}