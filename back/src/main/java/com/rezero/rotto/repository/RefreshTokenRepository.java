package com.rezero.rotto.repository;

import com.rezero.rotto.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    RefreshToken findByUserCode(int userCode);
}
