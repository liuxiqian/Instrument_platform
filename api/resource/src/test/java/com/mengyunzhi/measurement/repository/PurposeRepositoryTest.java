package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Purpose;
import com.mengyunzhi.measurement.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by zhangjiahao on 2017/5/17.
 */
public class PurposeRepositoryTest extends RepositoryTest {
    @Autowired
    private PurposeRepository purposeRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void save()
    {
//        创建user对象并保存
        User user = new User();
        userRepository.save(user);

        Purpose purpose = new Purpose();
        purpose.setCreateUser(user);

        purposeRepository.save(purpose);
//        断言保存成功
        assertThat(purpose.getId()).isNotNull();
    }



}