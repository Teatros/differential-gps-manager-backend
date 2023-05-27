package com.firedance.gps.utils.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author minghu.he
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelProperty {

    String value() default "";

    String field() default "";

    String dateFormat() default "yyyy-MM-dd";
}
