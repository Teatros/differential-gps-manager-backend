package com.firedance.gps.dao;

import com.firedance.gps.model.SmsCode;
import com.firedance.gps.model.SysAccount;
import org.apache.ibatis.annotations.Param;

/**
 * @author tangqi
 */
public interface SmsCodeMapper {

    /**
     * 获取最近的手机验证码
     * @param mobile
     * @return
     */
    SmsCode getLastedSmsCode(@Param("mobile") String mobile);

    /**
     *
     * @param smsCode
     */
    void insert(SmsCode smsCode);

    /**
     *
     * @param mobile
     * @param code
     */
    void updateUsed(@Param("mobile") String mobile,@Param("code") String code);
}
