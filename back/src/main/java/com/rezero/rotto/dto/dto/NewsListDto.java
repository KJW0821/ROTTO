package com.rezero.rotto.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NewsListDto {

    private int newsCode;
    private String title;
    private String content;
    private LocalDateTime postTime;
    private String imgPath;

}
