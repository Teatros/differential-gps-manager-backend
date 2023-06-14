package com.firedance.gps.handler.result;

/**
 * @author chong.sun
 */
public class ResultHelper {
    public static <T> Result<T> success(T data) {
        return new Result<>("200", "成功", "INFO", data);
    }
    public static <T> Result<T> fail(String code,String message,T data) {
        return new Result<>(code, message, "ERROR", data);
    }
}
