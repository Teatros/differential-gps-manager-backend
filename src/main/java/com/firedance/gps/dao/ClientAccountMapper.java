package com.firedance.gps.dao;

import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.model.ClientAccount;
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
}
