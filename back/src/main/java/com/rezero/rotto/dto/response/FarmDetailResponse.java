package com.rezero.rotto.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class FarmDetailResponse {

    private int farmCode;
    private String farmName;
    private String farmLogoPath;
    private String farmImgPath;
    private String farmAddress;
    private int farmScale;
    private LocalDateTime farmStartedDate;
    private String awardHistory;
    private String beanName;
    private Integer beanGrade;
    private Boolean isInterested;

}
