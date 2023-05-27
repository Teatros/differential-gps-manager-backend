package com.firedance.gps.controller;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.AccountSpecification;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.enums.AccountSpecificationEnum;
import com.firedance.gps.model.excel.ClientAccountExcelModel;
import com.firedance.gps.service.IClientAccountService;
import com.firedance.gps.utils.excel.ExcelBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tangqi
 */
@RestController
public class ClientAccountController {

    IClientAccountService clientAccountService;

    public ClientAccountController(IClientAccountService clientAccountService) {
        this.clientAccountService = clientAccountService;
    }

    @RequestMapping(value = "/client/accounts",method = RequestMethod.POST)
    public Result<PageData<ClientAccount>> listAccounts(@RequestBody ClientUserQueryParams clientUserQueryParams){
        return ResultHelper.success(clientAccountService.listAccounts(clientUserQueryParams));
    }

    @RequestMapping(value = "/client/specifications",method = RequestMethod.GET)
    public Result<List<AccountSpecification>> getSpecifications(){
        List<AccountSpecificationEnum> accountSpecificationEnums =
            Arrays.asList(AccountSpecificationEnum.values());
        ArrayList<AccountSpecification> accountSpecifications = new ArrayList<>();
        accountSpecificationEnums.stream().forEach(x->{
            AccountSpecification accountSpecification = new AccountSpecification();
            accountSpecification.setType(x);
            accountSpecification.setDescription(x.getDescription());
            accountSpecifications.add(accountSpecification);
        });

        return ResultHelper.success(accountSpecifications);
    }

    @RequestMapping(value = "/client/account",method = RequestMethod.POST)
    public Result<Boolean> createAccount(@RequestParam("count")Integer count,@RequestParam("type")AccountSpecificationEnum specification){
        clientAccountService.createAccount(count,specification);

        return ResultHelper.success(true);
    }

    @RequestMapping(value = "/client/account",method = RequestMethod.PUT)
    public Result<Boolean> updateAccount(@RequestBody ClientAccount clientAccount){
        clientAccountService.updateAccount(clientAccount);

        return ResultHelper.success(true);
    }

    @RequestMapping(value = "/client/account/export",method = RequestMethod.POST)
    public Result<Boolean> exportAccount(@RequestBody ClientUserQueryParams clientUserQueryParams,HttpServletResponse response)
        throws IOException {
        PageData<ClientAccount> clientAccountPageData =
            clientAccountService.listAccounts(clientUserQueryParams);
        List<ClientAccount> clientAccounts = clientAccountPageData.getData();
        List<ClientAccountExcelModel> salesExportResponses =
            clientAccounts.stream().map(ClientAccountExcelModel::fromModel).collect(Collectors.toList());
        ExcelBuilder excelBuilder = new ExcelBuilder(ClientAccountExcelModel.class, "登入用户信息")
            .fill(salesExportResponses);
        excelBuilder.export(response.getOutputStream());
        return ResultHelper.success(true);
    }


//    @PostMapping("/client/batchImportAccounts")
//    public Result<Boolean> batchImportAccounts(@RequestParam(value = "file") MultipartFile file){
//        String fileName = file.getName();
//        File tempFile = null;
//        try {
//            tempFile = File.createTempFile(UUIDUtil.getId(), fileName.substring(fileName.lastIndexOf(".")));
//            file.transferTo(tempFile);
//            List<ClientAccount> clientAccounts = clientAccountService.analyseExcel(tempFile);
//            clientAccountService.importClientAccounts(clientAccounts);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
