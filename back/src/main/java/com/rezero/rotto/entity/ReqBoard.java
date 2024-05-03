package com.rezero.rotto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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


    @NotNull(message = "Content cannot be null")
    @NotEmpty(message = "Content cannot be empty")
    @Column(nullable = false, columnDefinition = "TEXT DEFAULT ''", name = "content")
    String content;

    @CreationTimestamp
    @Column(name = "createTime")
    LocalDateTime createTime;

}
