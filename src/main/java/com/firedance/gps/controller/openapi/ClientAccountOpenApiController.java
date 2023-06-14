package com.firedance.gps.controller.openapi;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.controller.param.OnlineAccountQueryParams;
import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.AccountSpecification;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.OnlineAccount;
import com.firedance.gps.model.enums.AccountSpecificationEnum;
import com.firedance.gps.model.excel.ClientAccountExcelModel;
import com.firedance.gps.service.IClientAccountService;
import com.firedance.gps.utils.excel.ExcelBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tangqi
 */
@RestController
public class ClientAccountOpenApiController {

    IClientAccountService clientAccountService;

    public ClientAccountOpenApiController(IClientAccountService clientAccountService) {
        this.clientAccountService = clientAccountService;
    }

    @GetMapping("/openapi/client/{account}/dynamic_pw")
    public Result<String> getDynamicPw(@PathVariable("account") String account){
        String newPw = clientAccountService.getDynamicPw(account);
        return ResultHelper.success(newPw);
    }
}
