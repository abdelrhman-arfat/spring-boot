package com.second.project.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class APIResponse<T> {

    private String message;
    private boolean success;
    private int code;
    private T data;

    public APIResponse(int code, String message , T data) {
        this.message = message;
        this.success = true;
        this.code = code;
        this.data = data;
    }

    public APIResponse(String message, int code) {
        this.message = message;
        this.success = false;
        this.code = code;
        this.data = null;
    }

    public APIResponse(int code2, String message2) {
      //TODO Auto-generated constructor stub
    }

}
