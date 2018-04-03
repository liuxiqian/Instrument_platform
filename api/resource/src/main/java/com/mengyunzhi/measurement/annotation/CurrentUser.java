package com.mengyunzhi.measurement.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author panjie on 2018/1/20
 * 当前登录用户
 */
@Target({ElementType.FIELD})        // 该注解应用于字段
@Retention(RetentionPolicy.RUNTIME) // 该注解在运行时生效
public @interface CurrentUser {
}
