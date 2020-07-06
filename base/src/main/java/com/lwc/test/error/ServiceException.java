package com.lwc.test.error;

public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 8109469326798389194L;
    private String errorCode;

    public ServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public ServiceException(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
