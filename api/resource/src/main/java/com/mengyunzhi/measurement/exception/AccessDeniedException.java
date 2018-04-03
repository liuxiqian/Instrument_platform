package com.mengyunzhi.measurement.exception;

/**
 * @author panjie on 2018/1/16
 * 无此操作权限
 */
public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message) {
        super(message);
    }
}
