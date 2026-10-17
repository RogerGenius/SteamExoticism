package com.steamexoticism.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应体：{@code { code, message, data }}。
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;

    private int code;
    private String message;
    private T data;

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS);
        result.setMessage("ok");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String message) {
        return error(ERROR, message);
    }

    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
