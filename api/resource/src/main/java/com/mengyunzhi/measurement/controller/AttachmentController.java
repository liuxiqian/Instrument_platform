package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.AttachmentService;
import com.mengyunzhi.measurement.entity.Attachment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created By chuhang on 17-9-20
 * 附件控制器
 */

@RestController
@RequestMapping("/Attachment")
@Api(tags = "Attachment 附件")
public class AttachmentController {
    private final static Logger logger = Logger.getLogger(AttachmentController.class.getName());

    @Autowired
    private AttachmentService attachmentService;

    @ApiOperation(value = "delete(删除)", notes = "删除单个附件", nickname = "Attachment_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id) {
        attachmentService.delete(id);
        return;
    }

    @ApiOperation(value = "上传图片类型的附件", notes = "上传图片类型的附件", nickname = "Attachment_uploadImage")
    @PostMapping("/uploadImage")
    @ResponseStatus(HttpStatus.CREATED)
    public Attachment uploadImage(@RequestParam("attachment") MultipartFile multipartFile) {
        return attachmentService.uploadImage(multipartFile);
    }

    @ApiOperation(value = "图片类型的附件访问入口", notes = "图片类型的附件访问入口", nickname = "Attachment_image")
    @GetMapping("/image/{saveName:.+}")
    public void image(@PathVariable String saveName, HttpServletResponse response) throws IOException {
        Resource resource = attachmentService.image(saveName);

        logger.info("设置响应信息的内容类型 ");
        response.setContentType(attachmentService.getMediaTypeBySaveName(saveName));

        logger.info("显示图片");
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
    }

}
