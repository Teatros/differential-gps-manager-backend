package com.firedance.gps.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author tangqi
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerAccount {
    private String id;
    private String ip;
    private String port;
    private String serviceProvider;
    private String mountPoint;
    private String account;
    private String password;
    private LocalDateTime serviceStartDateTime;
    private LocalDateTime serviceEndDateTime;
    private Boolean inUse;
    private Boolean enable;
    private String disableReason;
}
