package com.rezero.rotto.repository;

import com.rezero.rotto.entity.ReqBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqBoardRepository extends JpaRepository<ReqBoard, Integer> {

    ReqBoard findByReqBoardCode(int reqBoardCode);

}
