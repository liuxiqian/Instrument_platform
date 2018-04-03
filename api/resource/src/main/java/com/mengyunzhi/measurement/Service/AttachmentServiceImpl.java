package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Attachment;
import com.mengyunzhi.measurement.repository.AttachmentRepository;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.Resource;

/**
 * Created By chuhang on 17-9-20
 */

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentApplyServiceImpl.class.getName());

    @Value("${attachment.image.directory}")
    private String imageDirectory;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Path getImagePath() {
        return Paths.get(this.getImageDirectory());
    }
    @Override
    public String getImageDirectory() {
        return imageDirectory;
    }

    @Override
    public void delete(Long id) {
        logger.info("获取附件的保存名");
        Attachment attachment = attachmentRepository.findOne(id);
        String saveName = attachment.getSaveName();

        logger.info("删除对应路径下的附件文件，释放服务器资源");
        FileSystemUtils.deleteRecursively(this.getPathByImageSaveName(saveName).toFile());

        logger.info("删除附件表的记录");
        attachmentRepository.delete(id);
        return;
    }

    @Override
    public Attachment uploadImage(MultipartFile multipartFile) {
        logger.info("新建附件对象");
        Attachment attachment = new Attachment();

        try {
            logger.debug("获取文件名");
            String fileName = multipartFile.getOriginalFilename();

            logger.debug("从文件名中截取拓展名");
            // 从"."最后一次出现的位置的下一位开始截取，获取扩展名
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

            logger.info("文件名");
            String name = multipartFile.getOriginalFilename();

            logger.info("对文件进行sha1,md5加密");
            String sha1ToMultipartFile = CommonService.encrypt(multipartFile, "SHA-1");
            String md5ToMultipartFile = CommonService.encrypt(multipartFile, "MD5");

            // 因为用户可能会上传相同的图片，所以设置文件的 保存名=md5加密（对文件进行md5加密 + 当前时间戳）
            // 即认为用户不会在同一毫秒上传相同的图片
            logger.info("设置保存文件名");
            String saveName = CommonService.md5(md5ToMultipartFile + System.currentTimeMillis()) + "." + ext;

            logger.info("设置文件路径");
            String savePath = this.getPathByImageSaveName(saveName).toString();

            logger.info("判断上传的文件是否为空");
            if (multipartFile.isEmpty()) {
                throw new RuntimeException("上传的图片不能为空" + name);
            }

            logger.info("如果目录不存在，则创建目录。如果目录存在，则不创建");
            Files.createDirectories(this.getImagePath());

            logger.info("将文件移动至储存图片的路径下");
            Files.copy(multipartFile.getInputStream(), this.getImagePath().resolve(saveName),
                    StandardCopyOption.REPLACE_EXISTING);

            logger.info("将附件存入到数据库中");
            attachment.setName(name);
            attachment.setSaveName(saveName);
            attachment.setMIME(multipartFile.getContentType());
            attachment.setSize(String.valueOf(multipartFile.getSize()));
            attachment.setExt(ext);
            attachment.setSha1(sha1ToMultipartFile);
            attachment.setMd5(md5ToMultipartFile);
            attachment.setSavePath(savePath);
            attachmentRepository.save(attachment);
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return attachment;
    }

    @Override
    public Path getPathByImageSaveName(String name) {
        return this.getImagePath().resolve(name);
    }

    @Override
    public Resource getResourceByPath(Path path) {
        try {
            logger.info("根据附件路径获取附件");
            Resource resource = new UrlResource(path.toUri());

            logger.info("判断附件是否存在");
            if (resource.exists()) {
                return resource;
            } else {
                throw new ObjectNotFoundException("404", "文件不存在");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("获取附件时存在异常");
        }
    }

    @Override
    public Resource image(String saveName) {
        logger.info("根据文件的保存名获取文件路径");
        Path path = this.getPathByImageSaveName(saveName);

        logger.info("根据路劲获取文件资源");
        Resource resource = this.getResourceByPath(path);

        return resource;
    }

    @Override
    public String getMediaTypeBySaveName(String saveName) {
        Attachment attachment = attachmentRepository.findBySaveName(saveName);
        return attachment.getMIME();
    }
}
