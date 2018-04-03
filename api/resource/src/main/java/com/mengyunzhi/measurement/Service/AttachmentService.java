package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Attachment;
import org.hibernate.ObjectNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.nio.file.Path;

/**
 * Created by chuhang on 2017/9/16.
 * 附件接口
 */
public interface AttachmentService {
    // 上传附件类型为图片的存储目录
    String getImageDirectory();

    // 上传附件类型为图片的存储路径
    Path getImagePath();

    // 删除附件
    void delete(Long id);

    // 保存图片类型的附件
    Attachment uploadImage(MultipartFile multipartFile) throws RuntimeException;

    // 根据图片的名字，获取图片的完整路径
    Path getPathByImageSaveName(String SaveName);

    // 获取附件通过附件的路径
    Resource getResourceByPath(Path path) throws ObjectNotFoundException;

    // 提供图片类型的附件访问入口
    Resource image(String saveName) throws RuntimeException;

    // 根据图片的保存名获取图片的类型
    String getMediaTypeBySaveName(String saveName);
}
