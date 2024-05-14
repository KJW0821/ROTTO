package com.rezero.rotto.repository;

import com.rezero.rotto.entity.ApplyHistory;
import com.rezero.rotto.entity.Farm;
import com.rezero.rotto.entity.Subscription;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class SubscriptionSpecification {

    // 가격 범위에 따른 필터링을 하는 스펙
    public static Specification<Subscription> priceBetween(Integer minPrice, Integer maxPrice){
        return (root, query, criteriaBuilder) -> {

            Predicate priceRangePredicate = criteriaBuilder.conjunction(); // 초기설정
            if (minPrice != null) {
                priceRangePredicate = criteriaBuilder.and(priceRangePredicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("confirmPrice"), minPrice));
            }
            if(maxPrice != null){
                priceRangePredicate = criteriaBuilder.and(priceRangePredicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("confirmPrice"), maxPrice));
            }

            return priceRangePredicate;
        };
    }


    // 청약 상태에 따른 필터링
    public static Specification<Subscription> filterBySubscriptionStatus(Integer subsStatus){
        return (root, query, criteriaBuilder) -> {

            Predicate statusPredicate;
            if (subsStatus == 0) { // 청약예정
                statusPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("startedTime"), criteriaBuilder.currentTimestamp());
            } else if (subsStatus == 1){ // 청약진행중
                statusPredicate = criteriaBuilder.and(
                        criteriaBuilder.lessThanOrEqualTo(root.get("startedTime"), criteriaBuilder.currentTimestamp()),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("endedTime"), criteriaBuilder.currentTimestamp())
                );
            } else if (subsStatus == 2) { // 청약 종료
                statusPredicate = criteriaBuilder.greaterThan(criteriaBuilder.currentTimestamp(), root.get("endedTime"));
            } else {
                statusPredicate = null;
            }

            return  statusPredicate;
        };
    }

    // 농장 이름에 특정 키워드가 포함되어 있는지 확인하는 스펙
    public static Specification<Subscription> nameContains(String keyword) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Farm> farmSubquery = query.subquery(Farm.class);
            Root<Farm> farmRoot = farmSubquery.from(Farm.class);

            // Farm 엔티티와의 관계를 나타내는 서브쿼리를 생성합니다.
            farmSubquery.select(farmRoot)
                    .where(criteriaBuilder.equal(root.get("farmCode"), farmRoot.get("farmCode")),
                            criteriaBuilder.like(farmRoot.get("farmName"), "%" + keyword + "%"));

            // Subscription 엔티티와 Farm 엔티티 사이의 관계를 나타내는 서브쿼리를 쿼리에 적용합니다.
            return criteriaBuilder.exists(farmSubquery);
        };
    }

    // 원두 종류에 따른 필터링
    public static Specification<Subscription> filterByBeanType(String beanType) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Farm> farmSubquery = query.subquery(Farm.class);
            Root<Farm> farmRoot = farmSubquery.from(Farm.class);


            // Farm 엔티티와의 관계를 나타내는 서브쿼리를 생성합니다.
            farmSubquery.select(farmRoot)
                    .where(criteriaBuilder.equal(root.get("farmCode"), farmRoot.get("farmCode")),
                            criteriaBuilder.equal(farmRoot.get("farmBeanName"), beanType));

            // Subscription 엔티티와 Farm 엔티티 사이의 관계를 나타내는 서브쿼리를 쿼리에 적용합니다.
            return criteriaBuilder.exists(farmSubquery);
        };
    }

    // 요청된 정렬 기준에 따라 정렬하는 스펙, sort = null : 농장이름순, rate : 수익률 높은 순, deadline: 마감 기한 빠른순,
    // 조각 가격순(highPrice, lowPrice), highApplyPercent : 신청률 높은순
    public static Specification<Subscription> applySorting(String sort) {
        return (root, query, criteriaBuilder) -> {
            if ("rate".equals(sort)) {
                // 가장 최근 청약의 returnRate로 정렬
                Subquery<Integer> rateSubquery = query.subquery(Integer.class);
                Root<Subscription> rateRoot = rateSubquery.from(Subscription.class);
                rateSubquery.select(rateRoot.get("returnRate"));

                //
                Subquery<Date> latestDateSubquery = query.subquery(Date.class);
                Root<Subscription> latestDateRoot = latestDateSubquery.from(Subscription.class);
                latestDateSubquery.select(criteriaBuilder.greatest(latestDateRoot.get("endedTime").as(Date.class)));


                rateSubquery.where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(rateRoot.get("endedTime"), latestDateSubquery.getSelection())
                        )
                );
                query.orderBy(criteriaBuilder.asc(rateSubquery));

            } else if ("deadline".equals(sort) || "highPrice".equals(sort) || "lowPrice".equals(sort)){
                // 청약 진행중인 상태를 필터링
                Specification<Subscription> ongoingSubSpec = SubscriptionSpecification.filterBySubscriptionStatus(1);
                Predicate ongoingPredicate = ongoingSubSpec.toPredicate(root, query, criteriaBuilder);
                query.where(ongoingPredicate);

                Subquery<Integer> priceSubquery = query.subquery(Integer.class);
                Root<Subscription> subscriptionRoot = priceSubquery.from(Subscription.class);
                priceSubquery.select(subscriptionRoot.get("confirmPrice"));
                priceSubquery.where(
                        criteriaBuilder.and(
                                criteriaBuilder.lessThanOrEqualTo(subscriptionRoot.get("startedTime"), criteriaBuilder.currentTimestamp()),
                                criteriaBuilder.greaterThanOrEqualTo(subscriptionRoot.get("endedTime"), criteriaBuilder.currentTimestamp())
                        )
                );

                if ("deadline".equals(sort)) {
                    // 마감 기한이 가장 빠른 순으로 정렬
                    Subquery<Date> deadlineSubquery = query.subquery(Date.class);
                    Root<Subscription> deadlineRoot = deadlineSubquery.from(Subscription.class);
                    deadlineSubquery.select(deadlineRoot.get("endedTime"));
                    deadlineSubquery.where(
                            criteriaBuilder.and(
                                    criteriaBuilder.lessThanOrEqualTo(deadlineRoot.get("startedTime"), criteriaBuilder.currentTimestamp()),
                                    criteriaBuilder.greaterThanOrEqualTo(deadlineRoot.get("endedTime"), criteriaBuilder.currentTimestamp())
                            )
                    );
                    query.orderBy(criteriaBuilder.desc(deadlineSubquery));
                } else if ("highPrice".equals(sort)){
                    query.orderBy(criteriaBuilder.asc(priceSubquery));
                } else if ("lowPrice".equals(sort)) {
                    query.orderBy(criteriaBuilder.desc(priceSubquery));
                }

            }
            if ("highApplyPercent".equals(sort)){
                Subquery<Long> applyHistoryCountSubquery = query.subquery(Long.class);
                Root<ApplyHistory> applyHistoryRoot = applyHistoryCountSubquery.from(ApplyHistory.class);
                applyHistoryCountSubquery.select(criteriaBuilder.count(applyHistoryRoot));

                // Subscription 엔티티와 ApplyHistory 엔티티의 관계를 나타내는 서브쿼리를 생성합니다.
                applyHistoryCountSubquery.where(criteriaBuilder.equal(root, applyHistoryRoot.get("subscriptionCode")));

                // 총 신청내역 갯수를 반환합니다.
                Expression<Long> applyHistoryCount = applyHistoryCountSubquery.getSelection();

                Subquery<Double> totalAmountSubquery = query.subquery(Double.class);
                Root<ApplyHistory> totalAmountRoot = totalAmountSubquery.from(ApplyHistory.class);
                totalAmountSubquery.select(criteriaBuilder.sum(totalAmountRoot.get("applyCount")));

                // Subscription 엔티티와 ApplyHistory 엔티티의 관계를 나타내는 서브쿼리를 생성합니다.
                totalAmountSubquery.where(criteriaBuilder.equal(root, totalAmountRoot.get("subscriptionCode")));

                // 총 발행토큰 수를 반환합니다.
                Expression<Double> totalAmount = totalAmountSubquery.getSelection();

                // 퍼센테이지를 계산합니다.
                Expression<Number> percentage = criteriaBuilder.quot(totalAmount, applyHistoryCount);

                // 최종 결과를 퍼센테이지 순으로 정렬합니다.
                query.orderBy(criteriaBuilder.desc(percentage));
            }

            return query.getRestriction();
        };
    }
}
