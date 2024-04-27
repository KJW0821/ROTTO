package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.TokenDto;

public interface UserService {

    TokenDto login(String phoneNum, String pin);

    TokenDto refreshToken(String refreshToken);

}
