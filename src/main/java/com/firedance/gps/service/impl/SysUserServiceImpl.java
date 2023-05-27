package com.firedance.gps.service.impl;

import com.firedance.gps.dao.SysUserMapper;
import com.firedance.gps.model.SysAccount;
import com.firedance.gps.service.ISysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements ISysUserService {

    private SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public SysAccount getAccount(String account) {
        return sysUserMapper.selectByAccount(account);
    }

    @Override
    public void update(SysAccount sysAccount) {
        sysUserMapper.update(sysAccount);
    }
}
