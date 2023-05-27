package com.firedance.gps.model.excel;

import com.firedance.gps.model.ClientAccount;
import com.firedance.gps.utils.excel.ExcelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class ClientAccountExcelModel {
    @ExcelProperty("账户")
    private String account;
    @ExcelProperty("密码")
    private String password;
    @ExcelProperty("规格")
    private String specification;

    public static ClientAccountExcelModel fromModel(ClientAccount clientAccount) {
        if (clientAccount == null) {
            return null;
        }
        return ClientAccountExcelModel.builder()
            .account(clientAccount.getAccount())
            .password(clientAccount.getPassword())
            .specification(clientAccount.getSpecification().getDescription())
            .build();
    }
}
