package com.firedance.gps.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author tangqi
 */
@Setter
@Getter
public class SysAccount {
    private String id;
    private String account;
    private String password;
    private String accountName;
    private String mobile;
    private String email;
    private String company;
    private LocalDateTime lastedLoginDateTime;
    private String role;
}
