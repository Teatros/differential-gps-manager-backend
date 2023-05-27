package com.firedance.gps.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author tangqi
 */
public interface ClientLoginRecordMapper {

    /**
     * 添加登录记录
     * @param account
     * @param ip
     */
    void addLoginRecord(@Param("account") String account,@Param("ip") String ip);
}
