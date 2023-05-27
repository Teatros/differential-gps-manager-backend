package com.firedance.gps.controller;

import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.SysAccount;
import com.firedance.gps.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/sys/login")
    public Result<SysAccount> login(@RequestParam("account")String account,@RequestParam("pw")String pw){
        SysAccount sysAccount = sysUserService.getAccount(account);
        if(pw.equals(sysAccount.getPassword())){
            sysAccount.setLastedLoginDateTime(LocalDateTime.now());
            sysUserService.update(sysAccount);
            return ResultHelper.success(sysAccount);
        }
        return ResultHelper.success(null);
    }
}
