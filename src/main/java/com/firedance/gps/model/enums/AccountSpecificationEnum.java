package com.firedance.gps.model.enums;

/**
 * 1天、3天、1个月、2个月、1年、3年
 * @author qi.tang
 */
public enum AccountSpecificationEnum {
    ONE_DAY("1天",1,"DAY"),
    THREE_DAY("3天",3,"DAY"),
    ONE_MONTH("1个月",1,"MONTH"),
    TWO_MONTH("2个月",2,"MONTH"),
    ONE_YEAR("1年",1,"YEAR"),
    THREE_YEAR("3年",3,"YEAR"),
    ;

    private String description;
    private Integer interval;
    private String regulation;

    AccountSpecificationEnum(String description, Integer interval, String regulation) {
        this.description = description;
        this.interval = interval;
        this.regulation = regulation;
    }

    public String getDescription() {
        return description;
    }

    public Integer getInterval() {
        return interval;
    }

    public String getRegulation() {
        return regulation;
    }
}
