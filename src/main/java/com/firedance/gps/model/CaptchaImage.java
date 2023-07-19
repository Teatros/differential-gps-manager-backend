package com.firedance.gps.model;

import lombok.*;

/**
 * @author tangqi
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptchaImage {
    private String id;
    private String code;
    private String image;
}
