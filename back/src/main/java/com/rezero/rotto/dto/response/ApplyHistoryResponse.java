package com.rezero.rotto.dto.response;

import com.rezero.rotto.dto.dto.ApplyHistoryListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ApplyHistoryResponse {

    private List<ApplyHistoryListDto> applyHistory;
    private int totalPages;
}
