package com.lwc.test.enums.base;

public enum BaseStatusCodeEnum {
	DELETE(-1,"删除"),
	NORMAL(1,"正常"),
	DISABLE(2,"禁用")
	;

	private BaseStatusCodeEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}
	private Integer code;
	private String value;
	
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
	
	public String getValueByCode(String code) {
		for(BaseStatusCodeEnum statusCodeEnum : BaseStatusCodeEnum.values()) {
			if(statusCodeEnum.getCode().equals(code)) {
				return statusCodeEnum.getValue();
			}
		}
		return null;
	}
	
	public Integer getCodeByValue(String value) {
		for(BaseStatusCodeEnum statusCodeEnum : BaseStatusCodeEnum.values()) {
			if(statusCodeEnum.getValue().equals(value)) {
				return statusCodeEnum.getCode();
			}
		}
		return null;
	}
}
