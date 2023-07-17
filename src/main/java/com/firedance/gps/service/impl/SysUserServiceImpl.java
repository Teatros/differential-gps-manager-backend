package com.firedance.gps.service.impl;

import com.firedance.gps.dao.SysUserMapper;
import com.firedance.gps.model.SysAccount;
import com.firedance.gps.service.ISysUserService;
import com.firedance.gps.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    @Override
    public void registerAccount(SysAccount sysAccount) {
        sysAccount.setId(UUIDUtil.getId());
        sysAccount.setPassword(String.valueOf(UUIDUtil.get8Number()));
        sysAccount.setRole("customer");
        sysUserMapper.insert(sysAccount);
    }

    @Override
    public SysAccount getByUserId(String userId) {
        return sysUserMapper.selectByUserId(userId);
    }
}
