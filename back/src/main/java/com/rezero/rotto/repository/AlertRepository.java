package com.rezero.rotto.repository;

import com.rezero.rotto.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {

    Alert findByAlertCode(int alertCode);

    @Modifying
    @Transactional
    @Query("UPDATE Alert a Set a.isRead = true WHERE a.user.code = :userCode AND a.isRead = false")
    int markAllAlertAsReadByUserCode(int userCode);

}
