package com.firedance.gps.controller;

import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.SysAccount;
import com.firedance.gps.service.ISysUserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 系统用户
 */
@RestController
public class SysUserController {


    private ISysUserService sysUserService;

    public SysUserController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping(path = "/sys/user/login")
    public Result<SysAccount> login(@RequestBody SysAccount sysAccountParam){
        String account = sysAccountParam.getAccount();
        String password = sysAccountParam.getPassword();
        SysAccount sysAccount = sysUserService.getAccount(account);
        if(password.equals(sysAccount.getPassword())){
            sysAccount.setLastedLoginDateTime(LocalDateTime.now());
            sysUserService.update(sysAccount);
            return ResultHelper.success(sysAccount);
        }
        return ResultHelper.success(null);
    }
}
