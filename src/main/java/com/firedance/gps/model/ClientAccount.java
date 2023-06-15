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
public class ClientAccount {
    private String id;
    private String account;
    private String password;
    private AccountSpecificationEnum specification;
    private LocalDateTime serviceStartDateTime;
    private LocalDateTime serviceEndDateTime;
    private LocalDateTime lastLoginDateTime;
    private Boolean inUse;
    private LocalDateTime createDateTime;
    private Boolean forbidden;
    private Integer type;
}
