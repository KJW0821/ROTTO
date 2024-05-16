package com.rezero.rotto.repository;

import com.rezero.rotto.entity.ExpenseDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpenseDetailRepository extends CrudRepository<ExpenseDetail, String> {

    // 청약 지출내역
    List<ExpenseDetail> findBySubscriptionCode(int subscriptionCode);

    @Query(value = "SELECT SUM(expenses) FROM ExpenseDetail WHERE subscriptionCode = :subscriptionCode")
    Integer sumExpenseDetailBySubscriptionCode(int subscriptionCode);
}
