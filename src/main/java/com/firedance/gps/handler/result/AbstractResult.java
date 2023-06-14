package com.firedance.gps.handler.result;

import com.alibaba.fastjson.JSONObject;

/**
 * @author leiwei
 */
public abstract class AbstractResult<T> implements IResult<T> {

    private String returnCode;
    private String returnMsg;
    private T returnObject;
    private String level;

    /**
     * 默认构造函数，序列化框架需要
     */
    AbstractResult() {
    }

    AbstractResult(String code, String message, String level, T data) {
        this.returnCode = code;
        this.returnMsg = message;
        this.returnObject = data;
        this.level = level;
    }

    @Override
    public String getReturnCode() {
        return returnCode;
    }

    @Override
    public void setReturnCode(String code) {
        this.returnCode = code;
    }

    @Override
    public String getReturnMsg() {
        return returnMsg;
    }

    @Override
    public void setReturnMsg(String message) {
        this.returnMsg = message;
    }

    @Override
    public T getReturnObject() {
        return returnObject;
    }

    @Override
    public void setReturnObject(T data) {
        this.returnObject = data;
    }

    @Override
    public String getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean isSuccess() {
        return "200".equals(this.getReturnCode());
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("returnCode",returnCode);
        jsonObject.put("returnMsg",returnMsg);
        jsonObject.put("returnObject",returnObject);
        jsonObject.put("level",level);
        return jsonObject.toJSONString();
    }
}
