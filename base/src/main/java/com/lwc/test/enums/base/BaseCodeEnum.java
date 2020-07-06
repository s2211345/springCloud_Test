package com.lwc.test.enums.base;

public enum BaseCodeEnum {
	SUCCESS_0("0","请求处理成功"),
	SUCCESS_200("200","请求处理成功"),
	FAIL("500","网络异常,请稍后再试"),
	REDIRECT_URL("307","重定向"),
	SUCCESS_OK("OK","请求处理成功"),
	;
	
	private BaseCodeEnum(String code, String value) {
		this.code = code;
		this.value = value;
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
	
	public String getValueByCode(String code) {
		for(BaseCodeEnum cyCodeEnum : BaseCodeEnum.values()) {
			if(cyCodeEnum.getCode().equals(code)) {
				return cyCodeEnum.getValue();
			}
		}
		return null;
	}
	
	public String getCodeByValue(String value) {
		for(BaseCodeEnum cyCodeEnum : BaseCodeEnum.values()) {
			if(cyCodeEnum.getValue().equals(value)) {
				return cyCodeEnum.getCode();
			}
		}
		return null;
	}
}
