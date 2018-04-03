package com.mengyunzhi.measurement.exceptionHandler;

import com.mengyunzhi.measurement.entity.JsonErrorResult;
import com.mengyunzhi.measurement.exception.AccessDeniedException;
import com.mengyunzhi.measurement.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author panjie on 2018/1/16
 * 全局异常处理
 */
@RestController // 以rest形式返回异常信息
@ControllerAdvice   // 全局异常处理器
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<JsonErrorResult> validationExceptionHandler(HttpServletRequest httpServletRequest, Exception exception) throws Exception {
        logger.error("---较验发生错误：---Host {} invokes url {} ERROR: {}", httpServletRequest.getRemoteHost(), httpServletRequest.getRequestURL(), exception.getMessage());
        return new ResponseEntity<>(new JsonErrorResult(httpServletRequest, exception), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<JsonErrorResult>  definedAccessDeniedExceptionHandler(HttpServletRequest httpServletRequest, Exception exception) throws Exception {
        logger.error("---权限验证发生错误：---Host {} invokes url {} ERROR: {}", httpServletRequest.getRemoteHost(), httpServletRequest.getRequestURL(), exception.getMessage());
        return new ResponseEntity<>(new JsonErrorResult(httpServletRequest, exception), HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(value = org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<JsonErrorResult> accessDeniedExceptionHandler(HttpServletRequest httpServletRequest, Exception exception) throws Exception {
        logger.error("---权限验证发生错误：---Host {} invokes url {} ERROR: {}", httpServletRequest.getRemoteHost(), httpServletRequest.getRequestURL(), exception.getMessage());
        return new ResponseEntity<>(new JsonErrorResult(httpServletRequest, exception), HttpStatus.FORBIDDEN);

    }
}
