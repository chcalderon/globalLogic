package com.example.demo.globallogic.userApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceException extends Exception{
    private static final long serialVersionUID = 1L;
    @JsonProperty("httpStatus")
    private Integer httpStatus;
    @JsonProperty("message")
    private String message;
    public ServiceException(Integer httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;

    }

    public ServiceException(ErrorMessage errorMessage){
        this.httpStatus = errorMessage.getHttpStatus();
        this.message = errorMessage.getMessage();
    }
    public Integer getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}