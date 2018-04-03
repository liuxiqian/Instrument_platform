package com.mengyunzhi.measurement.Service;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chuhang on 17-9-7.
 * 手机短信，SMS（short message service）
 * 短信充值地址：http://sms.webchinese.com.cn/User/?action=pay
 */
public interface SMSService {
    // 发送手机短信通过手机号码和短信内容,并返回短信发送后的返回值（返回值小于零则发送失败，大于零则发送成功）
    Integer sendSMSByPhoneNumberAndContent(String phoneNumber, String content) throws IOException;

    /**
     * 批量发送短信
     *
     * @param mobiles 手机号
     * @param message 短信内容
     * @throws IOException
     * @author chuhang
     */
    Integer sentMessage(Set<String> mobiles, String message) throws IOException;

    /**
     * 验证手机号
     *
     * @param mobile 手机号
     * @return
     * @author chuhang
     */
    static Boolean isMobile(String mobile) {
        Pattern pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        Matcher matcher = pattern.matcher(mobile);
        boolean result = matcher.matches();
        return result;
    }
}
