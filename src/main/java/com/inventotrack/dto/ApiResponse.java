package com.inventotrack.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private boolean success;

    private int status;

    private String message;

    private T data;

    private LocalDateTime timestamp;

    private String path;

    public ApiResponse() {
    }

    public ApiResponse(boolean success,
                       int status,
                       String message,
                       T data,
                       String path) {

        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(int status,
                                             String message,
                                             T data,
                                             String path) {

        return new ApiResponse<>(
                true,
                status,
                message,
                data,
                path);
    }

    public static <T> ApiResponse<T> failure(int status,
                                             String message,
                                             String path) {

        return new ApiResponse<>(
                false,
                status,
                message,
                null,
                path);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}