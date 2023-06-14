package com.firedance.gps.model.enums;

/**
 * @author qi.tang
 */
public enum ExceptionEnum {
    C0001("C0001","invalid access key"),
    C0002("C0002","invalid sign"),
    C0003("C0003","password has expired"),
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
