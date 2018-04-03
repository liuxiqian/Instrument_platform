package com.mengyunzhi.measurement.entity;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author panjie on 2018/1/16
 * JSON格式的错误返回信息
 */
@Component
public class JsonErrorResult implements Serializable {
    private String message;
    private String method;
    private String url;

    public JsonErrorResult() {
    }

    public JsonErrorResult(HttpServletRequest httpServletRequest, Exception exception) {
        this.setMethod(httpServletRequest.getMethod());
        this.setUrl(httpServletRequest.getRequestURL().toString());
        this.setMessage(exception.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
