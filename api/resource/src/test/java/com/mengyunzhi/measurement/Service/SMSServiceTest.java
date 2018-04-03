package com.mengyunzhi.measurement.Service;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by chuhang on 17-11-30
 */
public class SMSServiceTest extends ServiceTest {
    final static Logger logger = Logger.getLogger(SMSServiceTest.class.getName());

    @Autowired
    SMSService smsService;

    @Test
    public void isMobile() throws Exception {
        String mobile = "43643543453";

        logger.info("断言");
        assertThat(SMSService.isMobile(mobile)).isEqualTo(false);

    }

    @Test
    public void sendSMSByPhoneNumberAndContent() throws Exception {
//        String mobile = "13920618851";
//        String content = "短信发送测试"
//                + Calendar.getInstance().get(Calendar.DATE)
//                + Calendar.getInstance().get(Calendar.HOUR)
//                + Calendar.getInstance().get(Calendar.MINUTE)
//                + Calendar.getInstance().get(Calendar.SECOND);
//
//        assertThat(smsService.sendSMSByPhoneNumberAndContent(mobile, content)).isEqualTo(200);
    }

}