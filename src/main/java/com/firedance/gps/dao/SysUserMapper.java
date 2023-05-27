package com.firedance.gps.dao;

import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.SysAccount;
import org.apache.ibatis.annotations.Param;

/**
 * @author tangqi
 */
public interface SysUserMapper {
    /**
     * 获取账户
     * @param account
     * @return
     */
    SysAccount selectByAccount(@Param("account") String account);

    void update(SysAccount sysAccount);
}
