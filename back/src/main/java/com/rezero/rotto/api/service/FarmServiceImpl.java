package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.FarmListDto;
import com.rezero.rotto.dto.response.FarmDetailResponse;
import com.rezero.rotto.dto.response.FarmListResponse;
import com.rezero.rotto.dto.response.FarmTop10ListResponse;
import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.FarmTop10;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.FarmRepository;
import com.rezero.rotto.repository.FarmTop10Repository;
import com.rezero.rotto.repository.UserRepository;
import com.rezero.rotto.utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final Pagination pagination;
    private final FarmTop10Repository farmTop10Repository;


    // 농장 목록 조회
    public ResponseEntity<?> getFarmList(int userCode, String sort, String keyword, Integer page) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // 농장 목록 모두 불러오기
        List<Farm> farms = farmRepository.findAll();
        List<FarmListDto> farmListDtos = new ArrayList<>();

        // 최신것부터 보여주기 위해 리스트 뒤집기
        Collections.reverse(farms);

        // Farm 리스트를 순회
        for (Farm farm : farms) {
            // Dto 에 담기
            FarmListDto farmListDto = FarmListDto.builder()
                    .farmCode(farm.getFarmCode())
                    .farmName(farm.getFarmName())
                    .farmLogoPath(farm.getFarmLogoPath())
                    .beanName(farm.getFarmBeanName())
                    .build();
            // farmListDtos 에 담기
            farmListDtos.add(farmListDto);
        }

        // 리스폰스 생성
        FarmListResponse response = FarmListResponse.builder()
                .farms(farmListDtos)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 농장 상세 조회(원두 상세 조회, 관심 농장 여부 추가 필요)
    public ResponseEntity<?> getFarmDetail(int userCode, int farmCode) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        Farm farm =  farmRepository.findByFarmCode(farmCode);
        if (farm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("농장을 찾을 수 없습니다.");
        }

        FarmDetailResponse response = FarmDetailResponse.builder()
                .farmCode(farmCode)
                .farmName(farm.getFarmName())
                .farmLogoPath(farm.getFarmLogoPath())
                .farmImgPath(farm.getFarmImgPath())
                .farmAddress(farm.getFarmAddress())
                .farmScale(farm.getFarmScale())
                .farmStartedDate(farm.getFarmStartedTime())
                .awardHistory(farm.getAwardHistory())
                .beanName(farm.getFarmBeanName())
                .beanGrade(farm.getFarmBeanGrade())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 수익률 Top 10 농장 목록 조회
    public ResponseEntity<?> getRateTop10FarmList(int userCode) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }
        // FarmTop10 리스트 가져오기
        List<FarmTop10> farmTop10List = farmTop10Repository.findAll();
        // farm 빈 리스트로 생성
        List<FarmListDto> farms = new ArrayList<>();

        // farmTop10List 순회하며 farmCode 이용하여 정보 불러오고 Dto 에 넣기
        for (FarmTop10 farmTop10 : farmTop10List) {
            Farm farm = farmRepository.findByFarmCode(farmTop10.getFarmCode());
            FarmListDto farmListDto = FarmListDto.builder()
                    .farmCode(farm.getFarmCode())
                    .farmName(farm.getFarmName())
                    .farmLogoPath(farm.getFarmLogoPath())
                    .beanName(farm.getFarmBeanName())
                    .build();
            // farms 에 하나씩 담기
            farms.add(farmListDto);
        }
        // 리스폰스 생성
        FarmTop10ListResponse response = FarmTop10ListResponse.builder()
                .farms(farms)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
