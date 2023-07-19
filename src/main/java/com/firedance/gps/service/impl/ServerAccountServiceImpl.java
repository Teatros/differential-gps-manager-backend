package com.firedance.gps.service.impl;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ExceptionAccount;
import com.firedance.gps.controller.param.ServerAccountQueryParams;
import com.firedance.gps.dao.DatagramMapper;
import com.firedance.gps.dao.ServerAccountMapper;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.MessageDatagram;
import com.firedance.gps.model.ServerAccount;
import com.firedance.gps.service.IServerAccountService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangqi
 */
@Service
public class ServerAccountServiceImpl implements IServerAccountService {


    ServerAccountMapper serverAccountMapper;

    public ServerAccountServiceImpl(ServerAccountMapper serverAccountMapper) {
        this.serverAccountMapper = serverAccountMapper;
    }

    @Override
    public PageData<ServerAccount> listAccounts(ServerAccountQueryParams serverAccountQueryParams) {
        Integer pageNum = serverAccountQueryParams.getPageNum();
        Integer pageSize = serverAccountQueryParams.getPageSize();

        Page<ServerAccount> page =
            PageHelper.startPage(pageNum, pageSize);
        List<ServerAccount> list = serverAccountMapper.list(serverAccountQueryParams);
        PageData<ServerAccount> result =
            new PageData<>(pageNum, pageSize, list.size(), page.getTotal(), list);
        PageHelper.clearPage();
        return result;
    }

    @Override
    public List<ServerAccount> analyseExcel(File tempFile) {
        List<ServerAccount> serverAccounts = new ArrayList<ServerAccount>();
        try {
            Workbook workbook = ExcelUtil.initExcel(tempFile);
            Sheet sheet = workbook.getSheetAt(0);
            List<Row> allRow = ExcelUtil.getAllRow(sheet);
            for (int i = 0; i < allRow.size(); i++) {
                Row cells = allRow.get(i);
                String account = ExcelUtil.getCellFormatValue(cells.getCell(1)).toString();
                String password = ExcelUtil.getCellFormatValue(cells.getCell(2)).toString();
                String ip = ExcelUtil.getCellFormatValue(cells.getCell(3)).toString();
                String port = ExcelUtil.getCellFormatValue(cells.getCell(4)).toString();
                String mountPoint = ExcelUtil.getCellFormatValue(cells.getCell(5)).toString();
                ServerAccount build =
                    ServerAccount.builder().id(UUIDUtil.getId()).ip(ip).port(port).account(account).password(password).serviceProvider("CHINA_MOBILE")
                        .mountPoint(mountPoint).serviceStartDateTime(null).serviceEndDateTime(null).enable(true).inUse(false)
                        .disableReason("")
                        .build();
                serverAccounts.add(build);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(tempFile!=null){
                tempFile.delete();
            }
        }
        return serverAccounts;
    }

    @Override
    public void insert(List<ServerAccount> serverAccounts) {
        serverAccountMapper.batchInsert(serverAccounts);
    }

    @Override
    public void deleteAccount(String id) {
        serverAccountMapper.delete(id);
    }

    @Override
    public void updateAccount(ServerAccount serverAccount) {
        serverAccountMapper.update(serverAccount);
    }
}
