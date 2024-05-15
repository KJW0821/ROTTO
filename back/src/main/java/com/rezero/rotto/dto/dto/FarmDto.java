package com.rezero.rotto.dto.dto;

import lombok.*;

@RequiredArgsConstructor
public abstract class FarmDto {

    private int farmCode;
    private String farmName;
    private String farmLogoPath;
    private String beanName;
    private boolean isLiked;

}
