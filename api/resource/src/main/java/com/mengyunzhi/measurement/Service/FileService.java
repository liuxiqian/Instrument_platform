package com.mengyunzhi.measurement.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by panjie on 17/11/12.
 */
public interface FileService {
    // 设置扩展名对应content-type
    String EXTENSION_NAME_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    /**
     * 通过扩展名下载文件,下载成功后删除文件
     * @param file
     * @param contentType
     * @param httpServletResponse
     */
    void downloadFileAndDeleteOnFinishByFileAndExtensionNameAndHttpServletResponse(
            File file,
            String contentType,
            HttpServletResponse httpServletResponse) throws Exception;

    /**
     * 下载文件
     * @param file
     * @param contentType
     * @param httpServletResponse
     * @throws Exception
     * panjie
     */
    void downloadFileByFileAndExtensionNameAndHttpServletResponse(
            File file,
            String contentType,
            HttpServletResponse httpServletResponse
    ) throws Exception;

    void downloadFileByFilenameAndFileAndExtensionNameAndHttpServletResponse(
            String filename,
            File file,
            String contentType,
            HttpServletResponse httpServletResponse
    ) throws Exception;

    /**
     * 通过文件名，获取资源文件夹下的文件
     * 1. 查找是否生产环境下的Resources（根目录）是否存在文件
     * 1.1 存在，直接返回
     * 1.2 不存，从jar包中复制，然后返回。
     *
     * 这是由于，在生产环境下，我们将JAVA文件及资源文件都进行了打包，而打包中的内容是直接读取不到的。
     * @param filename
     * @return
     * panjie
     */
    File getResourcesFileByFilename(String filename) throws IOException;
}
