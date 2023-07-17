package com.firedance.gps.controller;

import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.SmsCode;
import com.firedance.gps.model.SysAccount;
import com.firedance.gps.model.enums.ExceptionEnum;
import com.firedance.gps.service.ISmsCodeService;
import com.firedance.gps.service.ISysUserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 系统用户
 */
@RestController
public class SysUserController {


    private ISysUserService sysUserService;
    private ISmsCodeService smsCodeService;

    public SysUserController(ISysUserService sysUserService, ISmsCodeService smsCodeService) {
        this.sysUserService = sysUserService;
        this.smsCodeService = smsCodeService;
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
        }else{
            return ResultHelper.success(null);
        }
    }

    @PostMapping(path = "/sys/user/sms_code/send")
    public Result<Boolean> sendSmsCode(@RequestParam("mobile") String mobile){
        smsCodeService.sendSmsCode(mobile);
        return ResultHelper.success(true);
    }

    @PostMapping(path = "/sys/user/sms_code/check")
    public Result<Boolean> checkSmsCode(@RequestParam("mobile") String mobile,@RequestParam("smsCode") String smsCode){
        SmsCode lastedSmsCode = smsCodeService.getLastedSmsCode(mobile);
        if(lastedSmsCode == null){
            ResultHelper.fail(ExceptionEnum.S0001.getCode(),ExceptionEnum.S0001.getMessage(),null);
        }
        if(lastedSmsCode.getUsed()){
            ResultHelper.fail(ExceptionEnum.S0002.getCode(),ExceptionEnum.S0002.getMessage(),null);
        }
        if(lastedSmsCode.getCode().equals(smsCode)){
            return ResultHelper.success(true);
        }else{
            ResultHelper.fail(ExceptionEnum.S0001.getCode(),ExceptionEnum.S0001.getMessage(),null);
        }
        return ResultHelper.success(false);
    }

    @PostMapping(path = "/sys/user/register")
    public Result<Boolean> register(@RequestBody SysAccount sysAccount){
        sysUserService.registerAccount(sysAccount);
        return ResultHelper.success(true);
    }
}
