package com.rezero.rotto.repository;

import com.rezero.rotto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByPhoneNumAndPin(String phoneNum, String pin);

    Optional<User> findByPhoneNum(String phoneNum);

    User findByUserCode(int userCode);

    Boolean existsByPhoneNum(String phoneNum);

}
