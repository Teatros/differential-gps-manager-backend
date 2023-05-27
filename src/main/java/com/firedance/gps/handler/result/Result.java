package com.firedance.gps.handler.result;

/**
 * @author leiwei
 */
public class Result<T> extends AbstractResult<T> {

    private Result() {
        super();
    }

    public Result(String code, String message, String level, T data) {
        super(code, message, level, data);
    }
}
