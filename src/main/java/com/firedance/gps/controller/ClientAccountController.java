package com.firedance.gps.controller;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.controller.param.OnlineAccountQueryParams;
import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.*;
import com.firedance.gps.model.enums.AccountSpecificationEnum;
import com.firedance.gps.model.enums.ServiceProviderEnum;
import com.firedance.gps.model.excel.ClientAccountExcelModel;
import com.firedance.gps.service.IClientAccountService;
import com.firedance.gps.service.ISysUserService;
import com.firedance.gps.utils.UUIDUtil;
import com.firedance.gps.utils.excel.ExcelBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    public Result<Boolean> createAccount(@RequestParam("count")Integer count,@RequestParam("type")AccountSpecificationEnum specification,@RequestParam("serviceProvider")
        ServiceProviderEnum serviceProvider){
        clientAccountService.createAccount(count,specification,serviceProvider.toString());

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


    @PostMapping("/client/on_line_account")
    public Result<PageData<OnlineAccount>> getOnlineAccount(@RequestBody OnlineAccountQueryParams onlineAccountQueryParams){
        return ResultHelper.success(clientAccountService.listOnlineAccounts(onlineAccountQueryParams));
    }


    //v2.0
    @PostMapping("/client/SIM/import")
    public Result<Boolean> importSIMs(@RequestParam("userId") String userId,@RequestParam("file") MultipartFile multipartFile)  throws IOException{
        String originalFilename = multipartFile.getOriginalFilename();
        File tempFile =
            File.createTempFile(UUIDUtil.getId(), originalFilename.substring(originalFilename.lastIndexOf(".")));
        multipartFile.transferTo(tempFile);
        List<ClientAccount> clientAccounts = clientAccountService.analyseExcel(tempFile);
        clientAccounts.stream().forEach(x->x.setUserId(userId));
        clientAccountService.batchInsertAccounts(clientAccounts);
        return ResultHelper.success(true);
    }
}
