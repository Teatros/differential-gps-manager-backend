package com.firedance.gps.dao;

import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.controller.param.ServerProviderQueryParams;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.ServerProvider;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangqi
 */
public interface ServerProviderMapper {
    /**
     * @param serverProvider
     */
    void insert(ServerProvider serverProvider);

    /**
     *
     * @param serverProviderQueryParams
     * @return
     */
    List<ServerProvider> list(ServerProviderQueryParams serverProviderQueryParams);

    /**
     *
     * @param serverProvider
     */
    void update(ServerProvider serverProvider);
}
