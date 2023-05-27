package com.firedance.gps.service;

import com.firedance.gps.model.MessageDatagram;
import com.firedance.gps.model.SysAccount;

/**
 * @author tangqi
 */
public interface ISysUserService {

    SysAccount getAccount(String account);

    void update(SysAccount sysAccount);
}
