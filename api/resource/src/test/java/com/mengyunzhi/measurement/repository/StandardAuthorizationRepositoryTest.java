package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.StandardAuthorization;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-7.
 */
public class StandardAuthorizationRepositoryTest extends RepositoryTest {
    @Autowired
    private StandardAuthorizationRepository standardAuthorizationRepository;

    @Test
    public void save() {
        StandardAuthorization standardAuthorization = new StandardAuthorization();
        standardAuthorizationRepository.save(standardAuthorization);

        assertThat(standardAuthorization.getId()).isNotNull();
    }

}