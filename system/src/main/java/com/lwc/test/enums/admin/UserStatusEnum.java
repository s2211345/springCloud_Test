package com.lwc.test.enums.admin;


public enum UserStatusEnum {
    NORMAL(1,"正常"),
    DISABLE(-1,"禁用"),
    DELETE(-2,"删除");

    private Integer code;
    private String value;
    private UserStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByCode(String code) {
        for(UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if(userStatusEnum.getCode().equals(code)) {
                return userStatusEnum.getValue();
            }
        }
        return null;
    }

    public static UserStatusEnum getByCode(String code) {
        for(UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if(userStatusEnum.getCode().equals(code)) {
                return userStatusEnum;
            }
        }
        return null;
    }
}
