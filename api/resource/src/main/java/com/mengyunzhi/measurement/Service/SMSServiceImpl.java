package com.mengyunzhi.measurement.Service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Set;
/**
 * Created by chuhang on 17-9-7.
 * 手机短信，SMS（short message service）
 */
public class SMSServiceImpl implements SMSService {
    final static Logger logger = Logger.getLogger(SMSServiceImpl.class.getName());

    @Value("${SMS.Uid}")
    private String Uid;

    @Value("${SMS.Key}")
    private String Key;

    private final static String url = "http://sms.webchinese.cn/web_api/";
    private final static String ContentType = "application/x-www-form-urlencoded;charset=gbk";

    @Override
    public Integer sendSMSByPhoneNumberAndContent(String phoneNumber, String content) throws IOException {
        logger.info("验证手机号");
        if (!SMSService.isMobile(phoneNumber)) {
            return 0;
        }

        logger.info("发送手机短信");
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.addRequestHeader("Content-Type", ContentType);// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid", Uid), // 注册的用户名
                new NameValuePair("Key", Key), // 注册成功后,登录网站使用的密钥
                new NameValuePair("smsMob", phoneNumber), // 手机号码
                new NameValuePair("smsText", content) }; // 设置短信内容

        post.setRequestBody(data);

        client.executeMethod(post);
        post.releaseConnection();

        return post.getStatusCode();

    }

    @Override
    public Integer sentMessage(Set<String> mobiles, String message) throws IOException {
        logger.info("批量发送短信");
        for (String mobile : mobiles) {
            this.sendSMSByPhoneNumberAndContent(mobile, message);
        }

        return 200;
    }
}
