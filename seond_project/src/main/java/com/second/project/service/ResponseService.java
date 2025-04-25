package com.second.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.second.project.response.APIResponse;

@Service
public class ResponseService<T> {

    private String message;
    private boolean success;
    private int code;
    private T data;

    public ResponseEntity<APIResponse<T>> returnResponse(String message, int code, T data) {
        return ResponseEntity.status(code).body(new APIResponse<>(code, message, data));
    }

    public ResponseEntity<APIResponse<T>> returnResponse(String message, int code) {
        return ResponseEntity.status(code).body(new APIResponse<>(code, message));
    }

    public ResponseEntity<APIResponse<T>> returnResponseError(String message) {
        return ResponseEntity.status(500).body(new APIResponse<>(message, 500));
    }

}
