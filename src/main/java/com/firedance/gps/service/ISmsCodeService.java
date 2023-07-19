package com.firedance.gps.service;

import com.firedance.gps.model.SmsCode;

/**
 * @author tangqi
 */
public interface ISmsCodeService {
    SmsCode getLastedSmsCode(String mobile);

    void sendSmsCode(String mobile);

    void saveImageCode(String s, String code);
}
