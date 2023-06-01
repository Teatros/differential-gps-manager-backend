package com.firedance.gps.service.impl;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.controller.param.OnlineAccountQueryParams;
import com.firedance.gps.dao.ClientAccountMapper;
import com.firedance.gps.dao.ClientLoginRecordMapper;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.OnlineAccount;
import com.firedance.gps.model.enums.AccountSpecificationEnum;
import com.firedance.gps.service.IClientAccountService;
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
public class ClientAccountServiceImpl implements IClientAccountService {

    private ClientAccountMapper clientAccountMapper;
    private ClientLoginRecordMapper clientLoginRecordMapper;

    public ClientAccountServiceImpl(ClientAccountMapper clientAccountMapper,
                                    ClientLoginRecordMapper clientLoginRecordMapper) {
        this.clientAccountMapper = clientAccountMapper;
        this.clientLoginRecordMapper = clientLoginRecordMapper;
    }

    @Override
    public PageData<ClientAccount> listAccounts(ClientUserQueryParams clientUserQueryParams) {
        Integer pageNum = clientUserQueryParams.getPageNum();
        Integer pageSize = clientUserQueryParams.getPageSize();

        Page<ClientAccount> page =
            PageHelper.startPage(pageNum, pageSize);
        List<ClientAccount> list = clientAccountMapper.list(clientUserQueryParams);
        PageData<ClientAccount> result =
            new PageData<>(pageNum, pageSize, list.size(), page.getTotal(), list);
        PageHelper.clearPage();
        return result;
    }

    @Override
    public List<ClientAccount> analyseExcel(File file) {
        Workbook workbook = null;
        try {
            workbook = ExcelUtil.initExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        List<Row> allRow = ExcelUtil.getAllRow(sheet);
        for (int i = 1; i < physicalNumberOfRows; i++) {
            Row cells = allRow.get(i);
            String account = ExcelUtil.getCellFormatValueWithNoFormat(cells.getCell(0)).toString();
            String password = ExcelUtil.getCellFormatValueWithNoFormat(cells.getCell(1)).toString();
        }
        return null;
    }

    @Override
    public void createAccount(Integer count, AccountSpecificationEnum specification) {
        ArrayList<ClientAccount> clientAccounts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String account = UUIDUtil.get4UUID() + UUIDUtil.get4Number();
            String password = String.valueOf(UUIDUtil.get8Number());
            Integer interval = specification.getInterval();
            LocalDateTime serviceStartDateTime = LocalDateTime.now();
            LocalDateTime serviceEndDateTime = null;
            String regulation = specification.getRegulation();
            if(regulation.equals("DAY")){
                serviceEndDateTime = serviceStartDateTime.plusDays(interval);
            }
            if(regulation.equals("MONTH")){
                serviceEndDateTime = serviceStartDateTime.plusMonths(interval);
            }
            if(regulation.equals("YEAR")){
                serviceEndDateTime = serviceStartDateTime.plusYears(interval);
            }
            ClientAccount build = ClientAccount.builder().id(UUIDUtil.getId()).account(account).password(password)
                .serviceStartDateTime(serviceStartDateTime).serviceEndDateTime(serviceEndDateTime)
                .createDateTime(LocalDateTime.now()).forbidden(false).specification(specification)
                .build();
            clientAccounts.add(build);
        }
        clientAccountMapper.batchInsert(clientAccounts);
    }

    @Override
    public void updateAccount(ClientAccount clientAccount) {
        clientAccountMapper.update(clientAccount);
    }

    @Override
    public PageData<OnlineAccount> listOnlineAccounts(OnlineAccountQueryParams onlineAccountQueryParams) {
        Integer pageNum = onlineAccountQueryParams.getPageNum();
        Integer pageSize = onlineAccountQueryParams.getPageSize();

        Page<OnlineAccount> page =
            PageHelper.startPage(pageNum, pageSize);
        List<OnlineAccount> list = clientAccountMapper.listOnlineAccounts();
        PageData<OnlineAccount> result =
            new PageData<>(pageNum, pageSize, list.size(), page.getTotal(), list);
        PageHelper.clearPage();
        return result;
    }
}
