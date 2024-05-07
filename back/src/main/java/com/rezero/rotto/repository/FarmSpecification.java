package com.rezero.rotto.repository;

import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.InterestFarm;
import com.rezero.rotto.entity.Subscription;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class FarmSpecification {


    // 가격 범위에 따른 필터링을 하는 스펙
    public static Specification<Farm> priceBetween(Integer minPrice, Integer maxPrice) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Double> priceSubquery = query.subquery(Double.class);
            Root<Subscription> subscriptionRoot = priceSubquery.from(Subscription.class);
            priceSubquery.select(subscriptionRoot.get("confirmPrice"));

            // Farm 과 연결된 Subscription
            priceSubquery.where(criteriaBuilder.equal(subscriptionRoot.get("farmCode"), root.get("farmCode")));

            Predicate pricePredicate;
            if (minPrice != null && maxPrice != null) {
                pricePredicate = criteriaBuilder.between(priceSubquery, minPrice.doubleValue(), maxPrice.doubleValue());
            } else if (minPrice != null) {
                pricePredicate = criteriaBuilder.greaterThanOrEqualTo(priceSubquery, minPrice.doubleValue());
            } else if (maxPrice != null) {
                pricePredicate = criteriaBuilder.lessThanOrEqualTo(priceSubquery, maxPrice.doubleValue());
            } else {
                pricePredicate = criteriaBuilder.conjunction();
            }

            return query.where(pricePredicate).getRestriction();
        };
    }


    // 청약 상태에 따라 농장을 필터링하는 스펙. subsStatus 0: 청약 예정, 1: 청약 진행 중, 2: 청약 종료
    public static Specification<Farm> filterBySubscriptionStatus(Integer subsStatus) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Subscription> subscriptionSubquery = query.subquery(Subscription.class);
            Root<Subscription> subscriptionRoot = subscriptionSubquery.from(Subscription.class);
            subscriptionSubquery.select(subscriptionRoot);

            // 연결 조건
            Predicate joinCondition = criteriaBuilder.equal(subscriptionRoot.get("farmCode"), root.get("farmCode"));

            // 상태 조건
            Predicate statusPredicate;
            if (subsStatus == 0) { // 청약 예정
                statusPredicate = criteriaBuilder.greaterThanOrEqualTo(subscriptionRoot.get("startedTime"), criteriaBuilder.currentTimestamp());
            } else if (subsStatus == 1) { // 청약 진행 중
                statusPredicate = criteriaBuilder.and(
                        criteriaBuilder.lessThanOrEqualTo(subscriptionRoot.get("startedTime"), criteriaBuilder.currentTimestamp()),
                        criteriaBuilder.greaterThanOrEqualTo(subscriptionRoot.get("endTime"), criteriaBuilder.currentTimestamp())
                );
            } else if (subsStatus == 2) { // 청약 종료
                statusPredicate = criteriaBuilder.lessThanOrEqualTo(subscriptionRoot.get("endTime"), criteriaBuilder.currentTimestamp());
            } else {
                statusPredicate = null;
            }

            return criteriaBuilder.exists(subscriptionSubquery.where(joinCondition, statusPredicate));
        };
    }


    // 관심 농장 여부를 확인하는 스펙
    public static Specification<Farm> hasInterest(int userCode) {
        return (root, query, criteriaBuilder) -> {
            Subquery<InterestFarm> interestSubquery = query.subquery(InterestFarm.class);
            Root<InterestFarm> interestRoot = interestSubquery.from(InterestFarm.class);
            interestSubquery.select(interestRoot);

            // 연결 조건
            Predicate joinCondition = criteriaBuilder.equal(interestRoot.get("farmCode"), root.get("farmCode"));

            // 사용자 코드 확인
            Predicate userCodePredicate = criteriaBuilder.equal(interestRoot.get("userCode"), userCode);

            return criteriaBuilder.exists(interestSubquery.where(joinCondition, userCodePredicate));
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
    // 요청된 정렬 기준에 따라 정렬하는 스펙
    public static Specification<Farm> applySorting(String sort) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Double> rateSubquery = query.subquery(Double.class);
            Root<Subscription> subscriptionRoot = rateSubquery.from(Subscription.class);
            rateSubquery.select(subscriptionRoot.get("returnRate"));
            rateSubquery.where(criteriaBuilder.equal(subscriptionRoot.get("farmCode"), root.get("farmCode")));

            if ("rate".equals(sort)) {
                query.orderBy(criteriaBuilder.asc(rateSubquery));
            } else if ("deadline".equals(sort)) {
                rateSubquery.select(subscriptionRoot.get("endTime"));
                query.orderBy(criteriaBuilder.asc(rateSubquery));
            } else if ("confirmPrice".equals(sort)) {
                rateSubquery.select(subscriptionRoot.get("confirmPrice"));
                query.orderBy(criteriaBuilder.asc(rateSubquery));
            } else {
                query.orderBy(criteriaBuilder.asc(root.get("farmName")));
            }
            return query.getRestriction();
        };
    }
}
