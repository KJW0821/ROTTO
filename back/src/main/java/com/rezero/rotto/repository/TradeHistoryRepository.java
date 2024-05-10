package com.rezero.rotto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rezero.rotto.entity.TradeHistory;

@Repository
public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Integer> {

}

