package com.firedance.gps.controller;

import com.firedance.gps.config.PageData;
import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.controller.param.ServerProviderQueryParams;
import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.AccountSpecification;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.ServerProvider;
import com.firedance.gps.model.enums.AccountSpecificationEnum;
import com.firedance.gps.model.excel.ClientAccountExcelModel;
import com.firedance.gps.service.IClientAccountService;
import com.firedance.gps.service.IServerProviderService;
import com.firedance.gps.utils.UUIDUtil;
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
public class ServerProviderController {

    IServerProviderService serverProviderService;

    public ServerProviderController(IServerProviderService serverProviderService) {
        this.serverProviderService = serverProviderService;
    }

    @RequestMapping(value = "/server/providers",method = RequestMethod.POST)
    public Result<PageData<ServerProvider>> listAccounts(@RequestBody ServerProviderQueryParams serverProviderQueryParams){
        return ResultHelper.success(serverProviderService.listProviders(serverProviderQueryParams));
    }

    @RequestMapping(value = "/server/mount_points",method = RequestMethod.GET)
    public Result<List<String>> getMountPoints(){
        ArrayList<String> mountPoints = new ArrayList<>();
        mountPoints.add("RTCM33");
        mountPoints.add("RTCM33_GRCE");
        mountPoints.add("RTCM30_GR");
        mountPoints.add("RTCM33_GRC");
        mountPoints.add("RTCM33_GRCEpro");
        mountPoints.add("RTCM33_GRCEJ");
        return ResultHelper.success(mountPoints);
    }

    @RequestMapping(value = "/server/provider",method = RequestMethod.POST)
    public Result<Boolean> createServerProvider(@RequestBody ServerProvider serverProvider){
        serverProvider.setId(UUIDUtil.getId());
        serverProviderService.createProvider(serverProvider);
        return ResultHelper.success(true);
    }

    @RequestMapping(value = "/server/provider",method = RequestMethod.PUT)
    public Result<Boolean> updateServerProvider(@RequestBody ServerProvider serverProvider){
        serverProviderService.updateProvider(serverProvider);
        return ResultHelper.success(true);
    }
}
