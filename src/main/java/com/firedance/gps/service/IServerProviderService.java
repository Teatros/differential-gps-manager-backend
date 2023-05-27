package com.firedance.gps.service;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.controller.param.ServerProviderQueryParams;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.ServerProvider;
import com.firedance.gps.model.enums.AccountSpecificationEnum;

import java.io.File;
import java.util.List;

/**
 * @author tangqi
 */
public interface IServerProviderService {
    PageData<ServerProvider> listProviders(ServerProviderQueryParams serverProviderQueryParams);

    void createProvider(ServerProvider serverProvider);

    void updateProvider(ServerProvider serverProvider);
}
