package com.rezero.rotto.dto.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmListDto extends FarmDto {

    private int farmCode;
    private String farmName;
    private String farmLogoPath;
    private String beanName;
    private Boolean isLiked;
    private BigDecimal returnRate;

}
