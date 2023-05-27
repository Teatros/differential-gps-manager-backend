package com.firedance.gps.dao;

import com.firedance.gps.controller.param.ServerAccountQueryParams;
import com.firedance.gps.model.ServerAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tangqi
 */
public interface ServerAccountMapper {
    /**
     * 获取列表
     * @param serverAccountQueryParams
     * @return
     */
    List<ServerAccount> list(ServerAccountQueryParams serverAccountQueryParams);

    /**
     * 更新
     * @param serverAccount
     */
    void update(ServerAccount serverAccount);

    /**
     * 单个插入
     * @param serverAccount
     */
    void insert(ServerAccount serverAccount);

    /**
     * 批量插入
     * @param serverAccounts
     */
    void batchInsert(@Param("serverAccounts")List<ServerAccount> serverAccounts);

    void delete(@Param("id")String id);
}
