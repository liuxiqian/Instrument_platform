package com.mengyunzhi.measurement.Service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author panjie on 2017/12/4
 * 短信发送
 * 对接http://www.c123.cn/
 * panjie
 */
@Service
public class SMSServiceImplC123 implements SMSService {
    Logger logger = Logger.getLogger(SMSServiceImplC123.class.getName());

    private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
    private static String sDataUrl = "http://smsapi.c123.cn/DataPlatform/DataApi";
    private final static String ContentType = "application/x-www-form-urlencoded;charset=utf8";

    // 接口帐号
    private static final String account = "1001@501370520001";

    // 接口密钥
    private static final String authkey = "F5E43885689B40944B765A4D35EB08A2";

    // 通道组编号
    private static final Integer cgid = 7782;

    // 默认使用的签名编号(未指定签名编号时传此值到服务器)
    private static final int csid = 0;

    @Override
    public Integer sendSMSByPhoneNumberAndContent(String phoneNumber, String content) throws IOException {
        Set<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add(phoneNumber);

        logger.debug("调用批量发送短信");
        return this.sentMessage(phoneNumbers, content);
    }

    /**
     * 批量发送短信
     * @param phoneNumbers
     * @param message 短信内容
     * @return 成功200 ，不成功400(短信验证错误或未传入发送手机号)
     * @throws IOException
     * panjie
     */
    @Override
    public Integer sentMessage(Set<String> phoneNumbers, String message) throws IOException {
        logger.debug("验证手机号");
        for (String phoneNumber :
                phoneNumbers) {
            if (!SMSService.isMobile(phoneNumber)) {
                logger.warn("手机号：" + phoneNumber + "较验失败");
                phoneNumbers.remove(phoneNumber);
            } else if (phoneNumber.equals("18888888888")) {
                logger.warn("检测到测试号码，移除");
                phoneNumbers.remove(phoneNumber);
            }
        }

        if (phoneNumbers.size() > 0) {
            logger.debug("发送手机短信");
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod(sOpenUrl);
            post.addRequestHeader("Content-Type", ContentType);// 在头文件中设置转码
            NameValuePair[] data = {new NameValuePair("action", "sendOnce"), // 注册的用户名
                    new NameValuePair("ac", account), // 注册成功后,登录网站使用的密钥
                    new NameValuePair("authkey", authkey), // 手机号码
                    new NameValuePair("cgid", cgid.toString()),
                    new NameValuePair("c", message),
                    new NameValuePair("m", String.join(",", phoneNumbers))}; // 设置短信内容

            post.setRequestBody(data);

            client.executeMethod(post);
            post.releaseConnection();
            return post.getStatusCode();
        } else {
            return 200;
        }
    }
}
