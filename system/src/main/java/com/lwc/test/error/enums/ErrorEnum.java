package com.lwc.test.error.enums;

public enum ErrorEnum {

    NETWORK_ERROR("001","网络异常，请刷新重试"),
    NOT_LOGIN_ERROR("002","请先登录才能进行操作"),
    TOKEN_SELORDER_ERROR("003","数据不存在"),
    JSON_FORMAT_ERROR("004","Json格式异常"),
    DATA_FORMAT_ERROR("005","传入数据格式异常"),
    REQUWST_METHOD_ERROR("06","请求method不正确"),
    CONTENT_TYPE_ERROR("07","Content-Type需设置application/json"),
    VERIFY_CODE_ERROR("08","验证码异常"),
    SYSTEM_ERROR("500","网络异常，请刷新页面重试"),
    TOKEN_EXPIRED_ERROR("500","307"),
         ;

    private ErrorEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(String code) {
        for(ErrorEnum errorEnum : ErrorEnum.values()) {
            if(errorEnum.getCode().equals(code)) {
                return errorEnum.getValue();
            }
        }
        return null;
    }

    public static String getCodeByValue(String value) {
        for(ErrorEnum errorEnum : ErrorEnum.values()) {
            if(errorEnum.getValue().equals(value)) {
                return errorEnum.getCode();
            }
        }
        return null;
    }
    
    private String code;
    private String value;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
