package com.rezero.rotto.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterPinRequest {

    @NotBlank(message = "PIN 번호는 필수입니다.")
    @Pattern(regexp = "\\d{6}", message = "핀번호는 숫자만을 포함하여야 합니다.")
    @Size(min = 6, max = 6, message = "핀번호는 6자리여야 합니다.")
    private String pin;

}
