package com.mengyunzhi.measurement.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author panjie on 2017/12/27
 */
public class OauthClientDetailsRepositoryTest extends RepositoryTest {
    @Autowired
    OauthClientDetailsRepository oauthClientDetailsRepository;

    @Test
    public void findTest() {
        oauthClientDetailsRepository.findAll();
    }
}