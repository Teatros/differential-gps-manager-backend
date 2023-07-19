package com.firedance.gps.dao;

import com.firedance.gps.model.SmsCode;
import org.apache.ibatis.annotations.Param;

/**
 * @author tangqi
 */
public interface ImageCodeMapper {

    String get(@Param("id") String id);

    void insert(@Param("id") String id,@Param("code") String code);

}
