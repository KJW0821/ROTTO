package com.rezero.rotto.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenResponse {

    private String grantType; // Bearer
    private String accessToken;
    private String refreshToken;

}
