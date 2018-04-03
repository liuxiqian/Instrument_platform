package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.StandardDeviceCheckDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-5-14.
 */
public class StandardDeviceCheckDetailRepositoryTest extends RepositoryTest{

    @Autowired
    private  StandardDeviceCheckDetailRepository standardDeviceCheckDetailRepository;

    @Test
    public void save() {
        StandardDeviceCheckDetail standardDeviceCheckDetail = new StandardDeviceCheckDetail();
        //保存
        standardDeviceCheckDetailRepository.save(standardDeviceCheckDetail);
        //断言
        assertThat(standardDeviceCheckDetail.getId()).isNotNull();
    }

}