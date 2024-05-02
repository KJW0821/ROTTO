package com.rezero.rotto.api.service;


import com.rezero.rotto.dto.dto.AlertListDto;
import com.rezero.rotto.dto.response.AlertDetailResponse;
import com.rezero.rotto.dto.response.AlertListResponse;
import com.rezero.rotto.entity.Alert;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.AlertRepository;
import com.rezero.rotto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;


    // 알림 목록 조회
    public ResponseEntity<?> getAlertList(int userCode) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // 알림 모두 불러오기
        List<Alert> alertList = alertRepository.findAll();
        // 최신것부터 보여주기 위해 리스트 뒤집기
        Collections.reverse(alertList);
        // stream 을 통해 alerts 를 순회하며 dto 리스트에 값을 담는다
        List<AlertListDto> alerts = alertList.stream()
                .map(alert -> new AlertListDto(alert.getAlertCode(), alert.getTitle(), alert.getCreateTime(), alert.getIsRead()))
                .toList();

        // 리스폰스 생성
        AlertListResponse response = new AlertListResponse(alerts);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 알림 상세 조회
    public ResponseEntity<?> getAlertDetail(int userCode, int alertCode) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // 해당 알림이 존재하는지 검사
        Alert alert = alertRepository.findByAlertCode(alertCode);
        if (alert == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 알림이 존재하지 않습니다.");
        }

        // 읽음 처리
        alert.setIsRead(true);

        // 리스폰스 생성
        AlertDetailResponse response = new AlertDetailResponse(alert.getTitle(), alert.getContent(), alert.getCreateTime());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}