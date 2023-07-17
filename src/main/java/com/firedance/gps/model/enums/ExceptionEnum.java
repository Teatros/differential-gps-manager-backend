package com.firedance.gps.model.enums;

/**
 * @author qi.tang
 */
public enum ExceptionEnum {
    C0001("C0001","invalid access key"),
    C0002("C0002","invalid sign"),
    C0003("C0003","password has expired"),
    C0004("C0004","password is valid"),
    S0001("S0001","code is valid"),
    S0002("S0002","code has been used"),
    ;

    private String code;
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
