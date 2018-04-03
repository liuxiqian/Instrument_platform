package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.StandardAuthorization;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-5-7.
 */
public class StandardAuthorizationServieTest extends ServiceTest {
    @Autowired
    private  StandardAuthorizationServie standardAuthorizationServie;
    @Test
    public void save() throws Exception {
        StandardAuthorization standardAuthorization = new StandardAuthorization();
        standardAuthorizationServie.save(standardAuthorization);

        assertThat(standardAuthorization.getId()).isNotNull();
    }

}