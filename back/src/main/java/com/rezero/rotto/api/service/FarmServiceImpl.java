package com.rezero.rotto.api.service;

import com.rezero.rotto.dto.dto.FarmDto;
import com.rezero.rotto.dto.dto.FarmListDto;
import com.rezero.rotto.dto.dto.MyPageFarmListDto;
import com.rezero.rotto.dto.response.FarmDetailResponse;
import com.rezero.rotto.dto.response.FarmListResponse;
import com.rezero.rotto.dto.response.FarmTop10ListResponse;
import com.rezero.rotto.dto.response.MyPageFarmListResponse;
import com.rezero.rotto.entity.*;
import com.rezero.rotto.repository.*;
import com.rezero.rotto.utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.rezero.rotto.utils.Const.VALID_BEAN_TYPES;

@Service
@Transactional
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final InterestFarmRepository interestFarmRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final FarmTop10Repository farmTop10Repository;
    private final Pagination pagination;


    // 농장 목록 조회
    public ResponseEntity<?> getFarmList(int userCode, Integer page, Boolean isLiked, Integer subsStatus, Integer minPrice, Integer maxPrice, String beanType, String sort, String keyword) {
        // 해당 유저가 존재하는지 검사
        User user = userRepository.findByUserCode(userCode);
        if (user == null || user.getIsDelete()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 사용자입니다.");
        }

        // 입력값 유효성 검사
        if (!isValidInput(isLiked, subsStatus, minPrice, maxPrice, beanType)) {
            return ResponseEntity.badRequest().body("잘못된 입력값입니다.");
        }

        // Specification 를 통해 필터링 및 정렬
        Specification<Farm> spec = buildSpecification(userCode, isLiked, subsStatus, minPrice, maxPrice, beanType, sort, keyword);

        // 농장 목록 불러오기
        List<Farm> farms = farmRepository.findAll(spec);
        // 인덱스 선언
        int startIdx = 0;
        int endIdx = 0;
        // 총 페이지 수 선언
        int totalPages = 1;

        // 페이지네이션
        List<Integer> indexes = pagination.pagination(page, 10, farms.size());
        startIdx = indexes.get(0);
        endIdx = indexes.get(1);
        totalPages = indexes.get(2);

        // 페이지네이션
        List<Farm> pageFarms = farms.subList(startIdx, endIdx);

        List<? extends FarmDto> farmDtos = convertToDtoList(pageFarms, userCode,  isLiked != null && isLiked && subsStatus == null && minPrice == null && maxPrice == null && beanType == null && sort == null && keyword == null);

        return ResponseEntity.status(HttpStatus.OK).body(buildResponse(farmDtos, totalPages));
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

        LocalDateTime now = LocalDateTime.now();
        List<Subscription> latestEndedSubscriptions = subscriptionRepository.findLatestEndedSubscription(farmCode, now);
        Subscription latestEndedSubscription = null;
        if (!latestEndedSubscriptions.isEmpty()) {
            latestEndedSubscription = latestEndedSubscriptions.get(0);
        }

        BigDecimal returnRate;
        if (latestEndedSubscription == null) {
            returnRate = null;
        } else {
            returnRate = latestEndedSubscription.getReturnRate();
        }

        Long likeCount = interestFarmRepository.countByFarmCode(farmCode);

        FarmDetailResponse response = FarmDetailResponse.builder()
                .farmCode(farmCode)
                .farmName(farm.getFarmName())
                .farmCeoName(farm.getFarmCeoName())
                .farmLogoPath(farm.getFarmLogoPath())
                .farmAddress(farm.getFarmAddress())
                .farmScale(farm.getFarmScale())
                .farmStartedDate(farm.getFarmStartedTime())
                .awardHistory(farm.getAwardHistory())
                .beanName(farm.getFarmBeanName())
                .beanGrade(farm.getFarmBeanGrade())
                .returnRate(returnRate)
                .isLiked(isLiked)
                .likeCount(likeCount)
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
            // 관심 농장 여부 검사
            boolean isLiked = false;
            InterestFarm interestFarm = interestFarmRepository.findByFarmCodeAndUserCode(farm.getFarmCode(), userCode);
            if (interestFarm != null) {
                isLiked = true;
            }
            int farmCode = farm.getFarmCode();
            LocalDateTime now = LocalDateTime.now();
            List<Subscription> latestEndedSubscriptions = subscriptionRepository.findLatestEndedSubscription(farmCode, now);
            Subscription latestEndedSubscription = null;
            if (!latestEndedSubscriptions.isEmpty()) {
                latestEndedSubscription = latestEndedSubscriptions.get(0);
            }
            BigDecimal returnRate;
            if (latestEndedSubscription == null) {
                returnRate = null;
            } else {
                returnRate = latestEndedSubscription.getReturnRate();
            }

            Long likeCount = interestFarmRepository.countByFarmCode(farmCode);

            FarmListDto farmListDto = FarmListDto.builder()
                    .farmCode(farmCode)
                    .farmName(farm.getFarmName())
                    .farmLogoPath(farm.getFarmLogoPath())
                    .beanName(farm.getFarmBeanName())
                    .returnRate(returnRate)
                    .isLiked(isLiked)
                    .likeCount(likeCount)
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
        // subsStatus 가 null 이 아니면서 0 미만이거나 2 초과다.
        if (subsStatus != null && (subsStatus < 0 || subsStatus > 2)) {
            return false;
        }
        // minPrice 와 maxPrice 가 모두 null 이 아니면서 minPrice 가 maxPrice 보다 크다.
        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            return false;
        }

        // beanType 이 null 이 아니면서 허용 리스트에 포함되지 않는 값이면 false 처리
        return beanType == null || VALID_BEAN_TYPES.contains(beanType);
    }


    private Specification<Farm> buildSpecification(int userCode, Boolean isLiked, Integer subsStatus, Integer minPrice, Integer maxPrice, String beanType, String sort, String keyword) {
        Specification<Farm> spec = Specification.where(null);
        if (keyword != null) spec = spec.and(FarmSpecification.nameContains(keyword));
        if (isLiked != null && isLiked) spec = spec.and(FarmSpecification.hasInterest(userCode));
        if (subsStatus != null) spec = spec.and(FarmSpecification.filterBySubscriptionStatus(subsStatus));
        if (minPrice != null || maxPrice != null)
            spec = spec.and(FarmSpecification.priceBetween(minPrice, maxPrice));
        if (beanType != null) spec = spec.and(FarmSpecification.filterByBeanType(beanType));
        spec = spec.and(FarmSpecification.applySorting(sort));
        return spec;
    }

    private List<? extends FarmDto> convertToDtoList(List<Farm> farms, int userCode, Boolean isMyPage) {
        List<FarmDto> farmDtos = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Farm farm : farms) {
            Boolean farmIsLiked = interestFarmRepository.findByFarmCodeAndUserCode(farm.getFarmCode(), userCode) != null;
            Boolean isFunding = isFunding(farm.getFarmCode());

            FarmDto farmDto;
            int farmCode = farm.getFarmCode();
            List<Subscription> latestEndedSubscriptions = subscriptionRepository.findLatestEndedSubscription(farmCode, now);
            Subscription latestEndedSubscription = null;
            if (!latestEndedSubscriptions.isEmpty()) {
                latestEndedSubscription = latestEndedSubscriptions.get(0);
            }
            BigDecimal returnRate;
            if (latestEndedSubscription == null) {
                returnRate = null;
            } else {
                returnRate = latestEndedSubscription.getReturnRate();
            }

            Long likeCount = interestFarmRepository.countByFarmCode(farmCode);

            if (isMyPage) {
                farmDto = new MyPageFarmListDto(farmCode, farm.getFarmName(), farm.getFarmLogoPath(), farm.getFarmBeanName(), farmIsLiked, returnRate, isFunding, likeCount);
            } else {
                farmDto = new FarmListDto(farmCode, farm.getFarmName(), farm.getFarmLogoPath(), farm.getFarmBeanName(), farmIsLiked, returnRate, likeCount);
            }
            farmDtos.add(farmDto);
        }
        return farmDtos;
    }

    private Object buildResponse(List<? extends FarmDto> farmDtos, int totalPages) {
        if (farmDtos.isEmpty()) {
            return MyPageFarmListResponse.builder().farms(Collections.emptyList()).build();
        }

        if (farmDtos.get(0) instanceof MyPageFarmListDto) {
            List<MyPageFarmListDto> myPageFarmListDtos = farmDtos.stream()
                    .map(farmDto -> (MyPageFarmListDto) farmDto)
                    .collect(Collectors.toList());
            return MyPageFarmListResponse.builder()
                    .farms(myPageFarmListDtos)
                    .totalPages(totalPages)
                    .build();
        } else {
            List<FarmListDto> farmListDtos = farmDtos.stream()
                    .map(farmDto -> (FarmListDto) farmDto)
                    .collect(Collectors.toList());
            return FarmListResponse.builder()
                    .farms(farmListDtos)
                    .totalPages(totalPages)
                    .build();
        }
    }


    // 펀딩 진행 여부 검사
    private boolean isFunding(int farmCode) {
        LocalDateTime now = LocalDateTime.now();
        List<Subscription> subscriptions = subscriptionRepository.findByFarmCode(farmCode);

        for (Subscription subscription : subscriptions) {
            if (subscription.getStartedTime().isBefore(now) && subscription.getEndedTime().isAfter(now)) {
                return true;
            }
        }

        return false;
    }
}
