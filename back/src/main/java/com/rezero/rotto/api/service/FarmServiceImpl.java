package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.FarmListDto;
import com.rezero.rotto.dto.response.FarmDetailResponse;
import com.rezero.rotto.dto.response.FarmListResponse;
import com.rezero.rotto.dto.response.FarmTop10ListResponse;
import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.FarmTop10;
import com.rezero.rotto.entity.InterestFarm;
import com.rezero.rotto.entity.User;
import com.rezero.rotto.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final InterestFarmRepository interestFarmRepository;
    private final FarmTop10Repository farmTop10Repository;


    // 농장 목록 조회
    public ResponseEntity<?> getFarmList(int userCode, Boolean isLiked, Integer subsStatus, Integer minPrice, Integer maxPrice, String beanType, String sort, String keyword) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // 입력값 유효성 검사
        if (!isValidInput(isLiked, subsStatus, minPrice, maxPrice, beanType)) {
            return ResponseEntity.badRequest().body("잘못된 입력값입니다.");
        }

        // Specification 을 활용하여 필터링 및 정렬
        Specification<Farm> spec = Specification.where(null);
        if (keyword != null) spec = spec.and(FarmSpecification.nameContains(keyword));
        if (isLiked != null && isLiked) spec = spec.and(FarmSpecification.hasInterest(userCode));
        if (subsStatus != null) spec = spec.and(FarmSpecification.filterBySubscriptionStatus(subsStatus));
        if (minPrice != null || maxPrice != null) spec = spec.and(FarmSpecification.priceBetween(minPrice, maxPrice));
        if (beanType != null) spec = spec.and(FarmSpecification.filterByBeanType(beanType));
        spec = spec.and(FarmSpecification.applySorting(sort));

        // 농장 목록 불러오기
        List<Farm> farms = farmRepository.findAll(spec);
        List<FarmListDto> farmListDtos = new ArrayList<>();

        // 최신것부터 보여주기 위해 리스트 뒤집기
        Collections.reverse(farms);

        // Farm 리스트를 순회
        for (Farm farm : farms) {
            // 관심 농장 여부 검사
            boolean farmIsLiked = false;
            InterestFarm interestFarm = interestFarmRepository.findByFarmCodeAndUserCode(farm.getFarmCode(), userCode);
            if (interestFarm != null) {
                farmIsLiked = true;
            }
            // Dto 에 담기
            FarmListDto farmListDto = FarmListDto.builder()
                    .farmCode(farm.getFarmCode())
                    .farmName(farm.getFarmName())
                    .farmLogoPath(farm.getFarmLogoPath())
                    .beanName(farm.getFarmBeanName())
                    .isLiked(farmIsLiked)
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

        // 농장이 존재하는지 검사
        Farm farm =  farmRepository.findByFarmCode(farmCode);
        if (farm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("농장을 찾을 수 없습니다.");
        }

        // 관심 농장 여부 검사
        boolean isLiked = false;
        InterestFarm interestFarm = interestFarmRepository.findByFarmCodeAndUserCode(farmCode, userCode);
        if (interestFarm != null) {
            isLiked = true;
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
                .isLiked(isLiked)
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


    // 입력값 오류 검사
    private boolean isValidInput(Boolean isLiked, Integer subsStatus, Integer minPrice, Integer maxPrice, String beanType) {
        // isLiked 가 null 이 아니면서 true 도 아니다.
        if (isLiked != null && !isLiked) {
            return false;
        }
        // subsStatus 가 null 이 아니면서 0 미만이거나 3 초과다.
        if (subsStatus != null && (subsStatus < 0 || subsStatus > 1)) {
            return false;
        }
        // minPrice 와 maxPrice 가 모두 null 이 아니면서 minPrice 가 maxPrice 보다 크다.
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            return false;
        }
        List<String> validBeanTypes = Arrays.asList("브라질 산토스", "콜롬비아 수프리모", "자메이카 블루마운틴", "에티오피아 예가체프", "케냐 AA", "코스타리카 따라주",
                                                    "탄자니아 AA", "예멘 모카 마타리", "하와이 코나", "과테말라 안티구아", "파나마 게이샤", "엘살바도르");
        // beanType 이 null 이 아니면서 위의 리스트에 포함되지 않는 값이면 false 처리
        return beanType == null || validBeanTypes.contains(beanType);
    }
}
