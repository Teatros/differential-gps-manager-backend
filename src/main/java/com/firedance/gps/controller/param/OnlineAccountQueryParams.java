package com.firedance.gps.controller.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qi.tang
 * 登入用户搜索参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnlineAccountQueryParams {
    private String account;
    private Integer pageNum;
    private Integer pageSize;
}
