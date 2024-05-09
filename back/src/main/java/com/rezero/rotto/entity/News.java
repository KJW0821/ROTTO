package com.rezero.rotto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news_tb")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_code")
    private int newsCode;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "post_time")
    private LocalDateTime postTime;

    @Column(name = "img_path")
    private String imgPath;

}
