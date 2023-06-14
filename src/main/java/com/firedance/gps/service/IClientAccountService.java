package com.firedance.gps.service;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.controller.param.OnlineAccountQueryParams;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.OnlineAccount;
import com.firedance.gps.model.enums.AccountSpecificationEnum;

import java.io.File;
import java.util.List;

/**
 * @author tangqi
 */
public interface IClientAccountService {
    PageData<ClientAccount> listAccounts(ClientUserQueryParams clientUserQueryParams);

    List<ClientAccount> analyseExcel(File file);

    void createAccount(Integer count, AccountSpecificationEnum specification);

    void updateAccount(ClientAccount clientAccount);

    PageData<OnlineAccount> listOnlineAccounts(OnlineAccountQueryParams onlineAccountQueryParams);

    String getDynamicPw(String account);

    void updatePeriodicalPassword();

}
