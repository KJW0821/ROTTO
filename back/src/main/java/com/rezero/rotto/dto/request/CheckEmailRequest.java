package com.rezero.rotto.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CheckEmailRequest {

    @NotBlank(message = "입력된 값이 없습니다.")
    private String email;

}
