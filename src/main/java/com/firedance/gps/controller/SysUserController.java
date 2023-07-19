package com.firedance.gps.controller;

import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.CaptchaImage;
import com.firedance.gps.model.SmsCode;
import com.firedance.gps.model.SysAccount;
import com.firedance.gps.model.enums.ExceptionEnum;
import com.firedance.gps.service.ISmsCodeService;
import com.firedance.gps.service.ISysUserService;
import com.firedance.gps.utils.UUIDUtil;
import com.firedance.gps.utils.VerifyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Base64;

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
    public Result<SysAccount> login(@RequestBody SysAccount sysAccountParam) {
        String account = sysAccountParam.getAccount();
        String password = sysAccountParam.getPassword();
        SysAccount sysAccount = sysUserService.getAccount(account);
        if (StringUtils.isNotBlank(password) && password.equals(sysAccount.getPassword())) {
            sysAccount.setLastedLoginDateTime(LocalDateTime.now());
            sysUserService.update(sysAccount);
            return ResultHelper.success(sysAccount);
        } else {
            return ResultHelper.success(null);
        }
    }

    @PostMapping(path = "/sys/user/sms_code/send")
    public Result<Boolean> sendSmsCode(@RequestParam("mobile") String mobile) {
        smsCodeService.sendSmsCode(mobile);
        return ResultHelper.success(true);
    }

    @PostMapping(path = "/sys/user/sms_code/check")
    public Result<Boolean> checkSmsCode(@RequestParam("mobile") String mobile,
                                        @RequestParam("smsCode") String smsCode) {
        SmsCode lastedSmsCode = smsCodeService.getLastedSmsCode(mobile);
        if (lastedSmsCode == null) {
            ResultHelper.fail(ExceptionEnum.S0001.getCode(), ExceptionEnum.S0001.getMessage(), null);
        }
        if (lastedSmsCode.getUsed()) {
            ResultHelper.fail(ExceptionEnum.S0002.getCode(), ExceptionEnum.S0002.getMessage(), null);
        }
        if (lastedSmsCode.getCode().equals(smsCode)) {
            return ResultHelper.success(true);
        } else {
            ResultHelper.fail(ExceptionEnum.S0001.getCode(), ExceptionEnum.S0001.getMessage(), null);
        }
        return ResultHelper.success(false);
    }

    @PostMapping(path = "/sys/user/register")
    public Result<Boolean> register(@RequestBody SysAccount sysAccount) {
        sysUserService.registerAccount(sysAccount);
        return ResultHelper.success(true);
    }

    /**
     * 生成验证码
     *
     * @param response Response对象
     * @throws Exception
     */
    @GetMapping("/captcha_image")
    public Result<CaptchaImage> getCode(HttpServletResponse response) throws Exception {


        // 返回的数组第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.newBuilder()
            .setWidth(120)
            .setHeight(35)
            .setSize(6)
            .setLines(10)
            .setFontSize(25)
            .setTilt(true)
            .setBackgroundColor(Color.LIGHT_GRAY)
            .build()
            .createImage();

        String id = UUIDUtil.getId();
        // 打印验证码
        System.out.println(objs[0]);

        smsCodeService.saveImageCode(id,objs[0].toString());

        Base64.Encoder encoder = Base64.getEncoder();
        BufferedImage image = (BufferedImage)objs[1];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        byte[] imageBytes = baos.toByteArray();
        baos.close();
        CaptchaImage build =
            CaptchaImage.builder().id(id).code(objs[0].toString()).image(encoder.encodeToString(imageBytes))
                .build();

        return ResultHelper.success(build);
    }
}
