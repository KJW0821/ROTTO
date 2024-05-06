package com.rezero.rotto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "faq_tb")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_code")
    int faqCode;


}
