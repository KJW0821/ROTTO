package com.rezero.rotto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    int userCode;

    @Column(name = "name")
    String name;

    @Column(name = "sex")
    String sex;

    @Column(name = "password")
    String password;

    @Column(name = "phone_num", unique = true)
    String phoneNum;

    @Column(name = "jumin_no")
    String juminNo;

    @Builder.Default
    @Column(name = "is_delete")
    Boolean isDelete = false;

    @CreationTimestamp
    @Column(name = "join_time")
    LocalDateTime joinTime;

    @Column(name = "delete_time")
    LocalDateTime deleteTime;


}
