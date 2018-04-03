package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Attachment;
import com.mengyunzhi.measurement.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by administrator on 2017/5/20.
 */
public class AttachmentRepositoryTest extends RepositoryTest{
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save(){
        //存操作用户
        User user = new User();
        userRepository.save(user);

        //存附件
        Attachment attachment = new Attachment();
        attachment.setOperator(user);
        attachmentRepository.save(attachment);

        //断言
        assertThat(attachment.getId()).isNotNull();
    }

}