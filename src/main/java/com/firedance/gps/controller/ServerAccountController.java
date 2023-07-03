package com.firedance.gps.controller;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ExceptionAccount;
import com.firedance.gps.controller.param.ServerAccountQueryParams;
import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.MessageDatagram;
import com.firedance.gps.model.ServerAccount;
import com.firedance.gps.service.IServerAccountService;
import com.firedance.gps.utils.UUIDUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author tangqi
 */
@RestController
public class ServerAccountController {

    IServerAccountService serverAccountService;

    public ServerAccountController(IServerAccountService serverAccountService) {
        this.serverAccountService = serverAccountService;
    }

    @RequestMapping(value = "/server/accounts",method = RequestMethod.POST)
    public Result<PageData<ServerAccount>> postExceptionServerAccount(@RequestBody ServerAccountQueryParams serverAccountQueryParams){
        return ResultHelper.success(serverAccountService.listAccounts(serverAccountQueryParams));
    }

    @RequestMapping(value = "/server/account",method = RequestMethod.PUT)
    public Result<Boolean> updateServerAccounts(@RequestBody ServerAccount serverAccount) throws IOException {
        serverAccountService.updateAccount(serverAccount);
        return ResultHelper.success(true);
    }

    @RequestMapping(value = "/server/account/{id}",method = RequestMethod.DELETE)
    public Result<Boolean> postServerAccounts(@PathVariable("id")String id) throws IOException {
        serverAccountService.deleteAccount(id);
        return ResultHelper.success(true);
    }

    @RequestMapping(value = "/server/account/upload",method = RequestMethod.POST)
    public Result<Boolean> postServerAccounts(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        File tempFile =
            File.createTempFile(UUIDUtil.getId(), originalFilename.substring(originalFilename.lastIndexOf(".")));
        multipartFile.transferTo(tempFile);
        List<ServerAccount> serverAccounts = serverAccountService.analyseExcel(tempFile);
        serverAccountService.insert(serverAccounts);
        return ResultHelper.success(true);
    }
}
