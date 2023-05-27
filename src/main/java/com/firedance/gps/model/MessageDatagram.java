package com.firedance.gps.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDatagram {
    private String account;
    private String datagram;
    private LocalDateTime createDateTime;
}
