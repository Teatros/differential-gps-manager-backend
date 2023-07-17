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
public class SmsCode {
    private String mobile;
    private String code;
    private LocalDateTime dateTime;
    private Boolean used;
}

