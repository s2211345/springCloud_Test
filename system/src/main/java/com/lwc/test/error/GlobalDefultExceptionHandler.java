package com.lwc.test.error;


import com.lwc.test.error.enums.ErrorEnum;
import com.lwc.test.view.base.response.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

@RestControllerAdvice
@Slf4j
public class GlobalDefultExceptionHandler {
    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(ServiceException.class)
    public BaseResult<Object> handleValidationBodyException(ServiceException e) {
        log.error(e.getMessage(),e);
        return new BaseResult<Object>().fail(e.getErrorCode(),e.getMessage());
    }

    @ExceptionHandler({IllegalAccessException.class,IntrospectionException.class,InvocationTargetException.class})
    public BaseResult<Object> handleValidationBodyException(Exception e) {
        return new BaseResult<Object>().fail(ErrorEnum.NETWORK_ERROR.getCode(),ErrorEnum.NETWORK_ERROR.getValue());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public BaseResult<Object> requestMethonException(Exception e) {
        return new BaseResult<Object>().fail(ErrorEnum.REQUWST_METHOD_ERROR.getCode(),ErrorEnum.REQUWST_METHOD_ERROR.getValue());
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public BaseResult<Object> DataFormatException(Exception e) {
        return new BaseResult<Object>().fail(ErrorEnum.CONTENT_TYPE_ERROR.getCode(),ErrorEnum.CONTENT_TYPE_ERROR.getValue());
    }

    /**
     * 处理全局异常异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseResult<Object> handleUnProccessableServiceException(Exception e) {
        log.error(e.getMessage(),e);
        return new BaseResult<Object>().fail(ErrorEnum.SYSTEM_ERROR.getCode(),ErrorEnum.SYSTEM_ERROR.getValue());
    }
}
