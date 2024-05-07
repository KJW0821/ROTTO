package com.rezero.rotto.repository;

import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.InterestFarm;
import com.rezero.rotto.entity.Subscription;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class FarmSpecification {


    // 가격 범위에 따른 필터링을 하는 스펙
    public static Specification<Farm> priceBetween(Integer minPrice, Integer maxPrice) {
        return (root, query, criteriaBuilder) -> {
            Join<Farm, Subscription> subscriptionJoin = root.join("subscriptions", JoinType.INNER);
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("confirmPrice"), minPrice, maxPrice);
            } else if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(subscriptionJoin.get("confirmPrice"), minPrice);
            } else if (maxPrice != null) {
                return criteriaBuilder.lessThanOrEqualTo(subscriptionJoin.get("confirmPrice"), maxPrice);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }


    // 청약 상태에 따라 농장을 필터링하는 스펙. subsStatus 0: 청약 예정, 1: 청약 진행 중, 2: 청약 종료
    public static Specification<Farm> filterBySubscriptionStatus(Integer subsStatus) {
        return (root, query, criteriaBuilder) -> {
            Join<Farm, Subscription> subscriptionJoin = root.join("subscriptions", JoinType.INNER);
            switch (subsStatus) {
                case 0: // 청약 예정
                    return criteriaBuilder.greaterThanOrEqualTo(subscriptionJoin.get("startedTime"), criteriaBuilder.currentTimestamp());
                case 1: // 청약 진행 중
                    return criteriaBuilder.and(
                            criteriaBuilder.lessThanOrEqualTo(subscriptionJoin.get("startedTime"), criteriaBuilder.currentTimestamp()),
                            criteriaBuilder.greaterThanOrEqualTo(subscriptionJoin.get("endTime"), criteriaBuilder.currentTimestamp())
                    );
                case 2: // 청약 종료
                    return criteriaBuilder.lessThanOrEqualTo(subscriptionJoin.get("endTime"), criteriaBuilder.currentTimestamp());
                default: // 유효하지 않은 상태 코드가 주어진 경우
                    return null;
            }
        };
    }


    // 관심 농장 여부를 확인하는 스펙
    public static Specification<Farm> hasInterest(int userCode) {
        return (root, query, criteriaBuilder) -> {
            Join<Farm, InterestFarm> interestJoin = root.join("interestFarms", JoinType.INNER);
            return criteriaBuilder.equal(interestJoin.get("userCode"), userCode);
        };
    }


    // 농장 이름에 특정 키워드가 포함되어 있는지 확인하는 스펙
    public static Specification<Farm> nameContains(String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("farmName"), "%" + keyword + "%");
    }


    // 원두 종류로 필터링하는 스펙
    public static Specification<Farm> filterByBeanType(String beanType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("farmBeanName"), beanType);
    }


    // 요청된 정렬 기준에 따라 정렬하는 스펙. sort = null: 농장 이름 순, rate: 수익률 높은 순, deadline: 마감 기한 빠른 순,
    // highPrice: 조각 가격 높은 순, lowPrice: 조각 가격 낮은 순
    public static Specification<Farm> applySorting(String sort) {
        return (root, query, criteriaBuilder) -> {
            Join<Farm, Subscription> subscriptionJoin = root.join("subscriptions", JoinType.INNER);

            if ("rate".equals(sort)) {
                query.orderBy(criteriaBuilder.asc(subscriptionJoin.get("returnRate")));
            } else if ("deadline".equals(sort)) {
                // 청약 진행중인 것들만 필터링
                Predicate inProgress = criteriaBuilder.between(criteriaBuilder.currentTimestamp(),
                        subscriptionJoin.get("startedTime"), subscriptionJoin.get("endTime"));
                query.where(inProgress);
                query.orderBy(criteriaBuilder.asc(subscriptionJoin.get("endTime")));
            } else if ("highPrice".equals(sort)) {
                query.orderBy(criteriaBuilder.desc(subscriptionJoin.get("confirmPrice")));
            } else if ("lowPrice".equals(sort)) {
                query.orderBy(criteriaBuilder.asc(subscriptionJoin.get("confirmPrice")));
            } else {
                // 기본 정렬 기준: 농장 이름 순
                query.orderBy(criteriaBuilder.asc(root.get("farmName")));
            }
            return query.getRestriction();
        };
    }
}
