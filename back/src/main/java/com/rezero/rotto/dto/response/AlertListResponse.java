package com.rezero.rotto.dto.response;

import com.rezero.rotto.dto.dto.AlertListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AlertListResponse {

    private List<AlertListDto> notices;

}
