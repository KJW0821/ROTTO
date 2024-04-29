package com.rezero.rotto.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 20, message = "이름은 20자 이하여야 합니다.")
    String name;
    @NotBlank(message = "성별은 필수입니다.")
    @Pattern(regexp = "^[MF]$", message = "성별은 M 또는 F 이어야 합니다.")
    String sex;
    @NotBlank(message = "휴대폰 번호는 필수입니다.")
    @Size(min = 11, max = 11, message = "휴대폰 번호는 11자리 숫자여야 합니다.")
    String phoneNum;
    @NotBlank(message = "주민등록번호는 필수입니다.")
    @Pattern(regexp = "[0-9]{6}", message = "주민등록번호는 6자리 숫자여야 합니다.")
    String juminNo;

}
