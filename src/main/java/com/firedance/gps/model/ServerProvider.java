package com.firedance.gps.model;

import com.firedance.gps.model.enums.AccountSpecificationEnum;
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
public class ServerProvider {
    private String id;
    private String ip;
    private String port;
    private String mountPoint;
    private String origin;
    private LocalDateTime createDateTime;
    private Boolean delete;
}
