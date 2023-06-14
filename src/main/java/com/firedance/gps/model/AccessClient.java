package com.firedance.gps.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author qi.tang
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessClient {
    private String accessKey;
    private String accessSecret;
    private LocalDateTime serviceStartDateTime;
    private LocalDateTime serviceEndDateTime;
    private Boolean enable;
}
