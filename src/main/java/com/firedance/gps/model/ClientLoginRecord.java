package com.firedance.gps.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author tangqi
 */
@Data
public class ClientLoginRecord {
    private Integer id;
    private String account;
    private String ip;
    private LocalDateTime loginDateTime;
}
