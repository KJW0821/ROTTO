package com.rezero.rotto.dto.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageFarmListDto extends FarmDto {

    private int farmCode;
    private String farmName;
    private String farmLogoPath;
    private String beanName;
    private Boolean isLiked;
    private Boolean isFunding;

}
