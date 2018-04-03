package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.CertifiedProduct;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by liming on 17-5-7.
 */
public class CertifiedProductServiceTest extends ServiceTest {
    @Autowired
    private CertifiedProductService certifiedProductService;
    @Test
    public void save() throws Exception {
        CertifiedProduct certifiedProduct = new CertifiedProduct();
        certifiedProductService.save(certifiedProduct);

        assertThat(certifiedProduct.getId()).isNotNull();
    }
}