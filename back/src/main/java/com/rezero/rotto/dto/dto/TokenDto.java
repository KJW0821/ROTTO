package com.rezero.rotto.dto.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenDto {

    private String grantType; // Bearer
    private String accessToken;
    private String refreshToken;

}
