package com.mengyunzhi.measurement.Service;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by panjie on 17/11/12.
 * 文件
 */
@Service
public class FileServiceImpl implements FileService {
    Logger logger = Logger.getLogger(FileServiceImpl.class.getName());
    /**
     * 通过扩展名下载文件，下载完成后删除文件
     * 1. 调用下载文件函数
     * 2. 删除让你说的
     * @param file
     * @param contentType 扩展名
     * @param httpServletResponse
     * panjie
     */
    @Override
    public void downloadFileAndDeleteOnFinishByFileAndExtensionNameAndHttpServletResponse(File file, String contentType, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("调用下载文件");
        this.downloadFileByFileAndExtensionNameAndHttpServletResponse(
                file,
                contentType,
                httpServletResponse
        );

        logger.info("删除文件(未生效)");
        file.deleteOnExit();
    }

    /**
     * 1. 通过扩展名找到 ContentType
     * 2. 输出文件流
     * @param file
     * @param contentType
     * @param httpServletResponse
     * panjie
     */
    @Override
    public void downloadFileByFileAndExtensionNameAndHttpServletResponse(File file, String contentType, HttpServletResponse httpServletResponse) throws Exception {
        this.downloadFileByFilenameAndFileAndExtensionNameAndHttpServletResponse(file.getName(), file, contentType, httpServletResponse);
    }

    @Override
    public void downloadFileByFilenameAndFileAndExtensionNameAndHttpServletResponse(String filename, File file, String contentType, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("输出文件类型");
        FileInputStream inputStream = new FileInputStream(file);
        httpServletResponse.setHeader("Content-Type", contentType);

        logger.info("输出文件名");
        String fileName = URLEncoder.encode(filename, "UTF-8");
        fileName = URLDecoder.decode(fileName, "ISO8859_1");
        httpServletResponse.setHeader("Content-disposition", "attachment; filename="+ fileName);
        httpServletResponse.setContentLength((int)file.length());

        logger.info("写入数据流");
        org.apache.commons.io.IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
        httpServletResponse.flushBuffer();
    }

    @Override
    public File getResourcesFileByFilename(String filename) throws IOException {
        String targetFilename = "./resources/" + filename;
        File targetFile = new File(targetFilename);
        if (!targetFile.exists()) {
            logger.info("新建文件夹，防止出现路径错误");
            targetFile.getParentFile().mkdirs();
            logger.info("文件不存在，执行复制操作，先由classPathResource读取源文件");
            // 获取resource下的test.docx。在spring boot 模式下，只能使用getInputStream来获取InputStream。如果使用getFile()。在执行jar文件时，获取文件路径会报错
            InputStream initialStream = new ClassPathResource(filename).getInputStream();

            logger.info("新建一个输出流，并按buffer(缓存：读8 * 1024个字节，然后写8 * 1024个字节)执行复制操作。");
            /**
             * 只所有要这么做，是由于：
             * 1. 一个字节字节的读写，将由于频繁的IO（input/output）降低性能。
             * 2. 一次性读完，然后再执行写操作，如果文件过大，将造成占用大量内存。
             */
            OutputStream outStream = new FileOutputStream(targetFile);
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = initialStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            logger.info("文件读写完毕，关闭输入输出流。");
            IOUtils.closeQuietly(initialStream);
            IOUtils.closeQuietly(outStream);
        }
        return targetFile;
    }
}
