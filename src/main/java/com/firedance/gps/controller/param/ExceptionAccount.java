package com.firedance.gps.controller.param;

import lombok.Data;

@Data
public class ExceptionAccount {
    private String ip;
    private String port;
    private String exception;
}
