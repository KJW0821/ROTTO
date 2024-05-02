package com.rezero.rotto.dto.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AlertListDto {

    private int alertCode;
    private String title;
    private LocalDateTime createTime;
    private Boolean isRead;

}
