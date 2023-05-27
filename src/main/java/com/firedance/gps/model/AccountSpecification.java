package com.firedance.gps.model;

import com.firedance.gps.model.enums.AccountSpecificationEnum;
import lombok.Data;

/**
 * @author tangqi
 */
@Data
public class AccountSpecification {
    private AccountSpecificationEnum type;
    private String description;
}
