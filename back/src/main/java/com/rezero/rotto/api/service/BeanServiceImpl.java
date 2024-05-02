package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.BeanListDto;
import com.rezero.rotto.dto.response.BeanListResponse;
import com.rezero.rotto.entity.Bean;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.BeanRepository;
import com.rezero.rotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BeanServiceImpl implements BeanService {

    private final UserRepository userRepository;
    private final BeanRepository beanRepository;


    // 원두 목록 조회
    public ResponseEntity<?> getBeanList(int userCode) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        List<Bean> beanList = beanRepository.findAll();
        List<BeanListDto> beans = new ArrayList<>();

        for (Bean bean : beanList) {
            BeanListDto beanListDto = BeanListDto.builder()
                    .beanCode(bean.getBeanCode())
                    .beanName(bean.getBeanName())
                    .beanImgPath(bean.getBeanImgPath())
                    .build();

            beans.add(beanListDto);
        }

        BeanListResponse response = BeanListResponse.builder()
                .beans(beans)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
