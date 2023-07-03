package com.firedance.gps.dao;

import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.OnlineAccount;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangqi
 */
public interface ClientAccountMapper {
    /**
     * 获取客户端账户
     * @param account
     * @return
     */
    ClientAccount selectByAccount(@Param("account") String account);

    /**
     * 获取列表
     * @param clientUserQueryParams
     * @return
     */
    List<ClientAccount> list(ClientUserQueryParams clientUserQueryParams);

    void batchInsert(@Param("clientAccounts")ArrayList<ClientAccount> clientAccounts);

    void update(ClientAccount clientAccount);

    List<OnlineAccount> listOnlineAccounts();

    Integer getClientAccountLastSerial();

    void updateClientAccountLastSerial(@Param("serialNumber")Integer serialNumber);

}
