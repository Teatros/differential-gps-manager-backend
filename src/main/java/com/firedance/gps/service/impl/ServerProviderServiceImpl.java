package com.firedance.gps.service.impl;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.controller.param.ServerProviderQueryParams;
import com.firedance.gps.dao.ClientAccountMapper;
import com.firedance.gps.dao.ClientLoginRecordMapper;
import com.firedance.gps.dao.ServerProviderMapper;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.ServerProvider;
import com.firedance.gps.model.enums.AccountSpecificationEnum;
import com.firedance.gps.service.IClientAccountService;
import com.firedance.gps.service.IServerProviderService;
import com.firedance.gps.utils.ExcelUtil;
import com.firedance.gps.utils.UUIDUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangqi
 */
@Service
public class ServerProviderServiceImpl implements IServerProviderService {
    private ServerProviderMapper serverProviderMapper;

    public ServerProviderServiceImpl(ServerProviderMapper serverProviderMapper) {
        this.serverProviderMapper = serverProviderMapper;
    }

    @Override
    public PageData<ServerProvider> listProviders(ServerProviderQueryParams serverProviderQueryParams) {
        Integer pageNum = serverProviderQueryParams.getPageNum();
        Integer pageSize = serverProviderQueryParams.getPageSize();

        Page<ServerProvider> page =
            PageHelper.startPage(pageNum, pageSize);
        List<ServerProvider> list = serverProviderMapper.list(serverProviderQueryParams);
        PageData<ServerProvider> result =
            new PageData<>(pageNum, pageSize, list.size(), page.getTotal(), list);
        PageHelper.clearPage();
        return result;
    }

    @Override
    public void createProvider(ServerProvider serverProvider) {
        serverProviderMapper.insert(serverProvider);
    }

    @Override
    public void updateProvider(ServerProvider serverProvider) {
        serverProviderMapper.update(serverProvider);
    }

}
