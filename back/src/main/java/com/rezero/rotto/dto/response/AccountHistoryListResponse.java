package com.rezero.rotto.dto.response;

import com.rezero.rotto.dto.dto.AccountHistoryListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class AccountHistoryListResponse  {

    private List<AccountHistoryListDto> accountHistoryListDtoss;
}
