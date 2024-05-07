package com.rezero.rotto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "faq_tb")
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_code")
    private int faqCode;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

}
