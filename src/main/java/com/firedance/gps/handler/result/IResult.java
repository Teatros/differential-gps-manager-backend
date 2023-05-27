package com.firedance.gps.handler.result;

/**
 * @author leiwei
 */
public interface IResult<T> {

    /**
     * code：返回结果的code，错误时为错误码。正常时为00000。
     *
     * @return
     */
    String getReturnCode();

    /**
     * 设置code。
     *
     * @param code
     */
    void setReturnCode(String code);

    /**
     * 结果描述，正常时为success。
     *
     * @return
     */
    String getReturnMsg();

    /**
     * 设置message。
     *
     * @param message
     */
    void setReturnMsg(String message);

    /**
     * 业务数据。
     *
     * @return
     */
    T getReturnObject();

    /**
     * 设置data。
     *
     * @param data
     */
    void setReturnObject(T data);

    /**
     * 获取level
     *
     * @return 用于显示的message级别
     */
    String getLevel();

    /**
     * 设置level
     *
     * @param level 异常提醒等级
     */
    void setLevel(String level);

    /**
     * 是否成功
     *
     * @return true为成功。
     */
    boolean isSuccess();
}
