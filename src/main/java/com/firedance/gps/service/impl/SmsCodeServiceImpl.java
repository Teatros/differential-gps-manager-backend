package com.firedance.gps.service.impl;

import com.firedance.gps.dao.SmsCodeMapper;
import com.firedance.gps.model.SmsCode;
import com.firedance.gps.service.ISmsCodeService;
import com.firedance.gps.utils.UUIDUtil;
import org.springframework.stereotype.Service;

/**
 * @author tangqi
 */
@Service
public class SmsCodeServiceImpl implements ISmsCodeService {

    SmsCodeMapper smsCodeMapper;

    public SmsCodeServiceImpl(SmsCodeMapper smsCodeMapper) {
        this.smsCodeMapper = smsCodeMapper;
    }

    @Override
    public SmsCode getLastedSmsCode(String mobile) {
        SmsCode lastedSmsCode = smsCodeMapper.getLastedSmsCode(mobile);
        return lastedSmsCode;
    }

    @Override
    public void sendSmsCode(String mobile) {
        SmsCode build = SmsCode.builder().mobile(mobile).code(String.valueOf(UUIDUtil.get4Number())).build();
        smsCodeMapper.insert(build);
    }
}
