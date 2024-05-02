package com.rezero.rotto.dto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FarmListDto {

    private int farmCode;
    private String farmName;
    private String farmLogoPath;
    private String beanName;

}
