package com.firedance.gps.service;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ExceptionAccount;
import com.firedance.gps.controller.param.ServerAccountQueryParams;
import com.firedance.gps.model.MessageDatagram;
import com.firedance.gps.model.ServerAccount;

import java.io.File;
import java.util.List;

/**
 * @author tangqi
 */
public interface IServerAccountService {


    PageData<ServerAccount> listAccounts(ServerAccountQueryParams serverAccountQueryParams);

    List<ServerAccount> analyseExcel(File tempFile);

    void insert(List<ServerAccount> serverAccounts);

    void deleteAccount(String id);

    void updateAccount(ServerAccount serverAccount);
}
