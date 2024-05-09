package com.rezero.rotto.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountZeroResponse {

    private int accountCode;
    private String bankName;
    private String accountHolder;
    private String accountNum;
    private int accountBalance;
    private int accountType;
}
