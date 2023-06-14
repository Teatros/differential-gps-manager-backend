package com.firedance.gps.dao;

import com.firedance.gps.controller.param.ClientUserQueryParams;
import com.firedance.gps.model.AccessClient;
import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.model.OnlineAccount;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangqi
 */
public interface AccessClientMapper {

    /**
     *
     * @param accessKey
     * @return
     */
    AccessClient selectByKey(@Param("accessKey") String accessKey);


}
