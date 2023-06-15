package com.firedance.gps.controller.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author qi.tang
 * 登入用户搜索参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUserQueryParams {
    private String account;
    private LocalDateTime serviceStartDateTime;
    private LocalDateTime serviceEndDateTime;
    private Integer type;
    private Integer pageNum;
    private Integer pageSize;
}
