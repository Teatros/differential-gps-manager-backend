package com.firedance.gps.dao;

import com.firedance.gps.model.MessageDatagram;
import org.apache.ibatis.annotations.Param;

/**
 * @author tangqi
 */
public interface DatagramMapper {

    /**
     * 插入报文信息
     * @param datagram
     */
    void insertGGA(@Param("datagram")MessageDatagram datagram);

    /**
     * 插入服务报文信息
     * @param datagram
     */
    void insertServerData(@Param("datagram") MessageDatagram datagram);


}
