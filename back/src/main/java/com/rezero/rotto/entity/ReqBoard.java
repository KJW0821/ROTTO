package com.rezero.rotto.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Builder
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "req_board_tb")
public class ReqBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_board_code")
    int reqBoardCode;

    @Column(name = "user_code")
    int userCode;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @CreationTimestamp
    @Column(name = "createTime")
    Timestamp createTime;

}
