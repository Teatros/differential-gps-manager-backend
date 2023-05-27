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
public class ServerAccountQueryParams {
    private String ip;
    private String port;
    private String mountPoint;
    private String account;
    private Boolean inUse;
    private Boolean enable;
    private Integer pageNum;
    private Integer pageSize;
}
