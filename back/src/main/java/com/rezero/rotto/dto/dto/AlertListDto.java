package com.rezero.rotto.dto.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AlertListDto {

    private int alertCode;
    private String title;
    private LocalDateTime createTime;
    private Boolean isRead;

}
