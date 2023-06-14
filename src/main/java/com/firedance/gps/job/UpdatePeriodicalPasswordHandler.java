package com.firedance.gps.job;

import com.firedance.gps.service.IClientAccountService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qi.tang
 */
@Component
@Slf4j
public class UpdatePeriodicalPasswordHandler {

    private final IClientAccountService clientAccountService;

    public UpdatePeriodicalPasswordHandler(IClientAccountService clientAccountService) {
        this.clientAccountService = clientAccountService;
    }

    @XxlJob(value = "updatePeriodicalPassword")
    public ReturnT<String> execute(String params) {
        try {
            clientAccountService.updatePeriodicalPassword();
        } catch (Exception e) {
            log.error("updatePeriodicalPassword发生异常:", e);
            return ReturnT.FAIL;
        }
        return ReturnT.SUCCESS;
    }
}
