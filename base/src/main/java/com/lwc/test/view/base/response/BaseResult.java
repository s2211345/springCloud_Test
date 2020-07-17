package com.lwc.test.view.base.response;

import com.lwc.test.enums.base.BaseCodeEnum;
import lombok.Data;

@Data
public class BaseResult<T> {
    private String code;
    private String msg;
    private T data;

    public BaseResult<T> fail(String errorCode, String message) {
        this.code = errorCode;
        this.msg = message;
        return this;
    }
    public BaseResult<T> fail(String msg) {
        this.code = BaseCodeEnum.FAIL.getCode();
        this.msg = msg;
        return this;
    }
    public BaseResult<T> fail() {
        this.code = BaseCodeEnum.FAIL.getCode();
        return this;
    }
    public BaseResult<T> success(T data,String message) {
        this.code = BaseCodeEnum.SUCCESS_200.getCode();
        this.data = data;
        this.msg = message;
        return this;
    }
    public BaseResult<T> success() {
        this.code = BaseCodeEnum.SUCCESS_200.getCode();
        return this;
    }
    public BaseResult<T> success(T data) {
        this.code = BaseCodeEnum.SUCCESS_200.getCode();
        this.data = data;
        return this;
    }
    public void setData(T t){
        this.data = t;
    }
}
