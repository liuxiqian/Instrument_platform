package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.MeasuringdeviceApplianceArchive;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-4-28.
 */
public class MeasuringdeviceApplianceArchiveServiceTest extends ServiceTest {

    @Autowired
    private MeasuringdeviceApplianceArchiveService measuringdeviceApplianceArchiveService;

    @Test
    public void save() throws Exception {
        MeasuringdeviceApplianceArchive measuringdeviceApplianceArchive = new MeasuringdeviceApplianceArchive();
        measuringdeviceApplianceArchiveService.save(measuringdeviceApplianceArchive);

        assertThat(measuringdeviceApplianceArchive.getId()).isNotNull();
    }

}