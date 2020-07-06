package com.lwc.test.view.base.response;

import com.lwc.test.enums.base.BaseCodeEnum;
import lombok.Data;

@Data
public class BaseResult<T> {
    private String code;
    private String msg;
    private T t;

    public BaseResult<T> fail(String errorCode, String message) {
        this.code = errorCode;
        this.msg = message;
        return this;
    }
    public BaseResult<T> fail(String errorCode) {
        this.code = errorCode;
        return this;
    }
    public BaseResult<T> success(T data,String message) {
        this.code = BaseCodeEnum.SUCCESS_200.getCode();
        this.t = t;
        this.msg = message;
        return this;
    }
    public BaseResult<T> success(String message) {
        this.code = BaseCodeEnum.SUCCESS_200.getCode();
        this.msg = message;
        return this;
    }
    public BaseResult<T> success(T data) {
        this.code = BaseCodeEnum.SUCCESS_200.getCode();
        this.t = t;
        return this;
    }
    public void setData(T t){
        this.t = t;
    }
}
