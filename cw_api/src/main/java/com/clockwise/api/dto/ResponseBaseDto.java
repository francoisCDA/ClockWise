package com.clockwise.api.dto;

public class ResponseBaseDto {

    private String message;

    private Object data;

    public ResponseBaseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
