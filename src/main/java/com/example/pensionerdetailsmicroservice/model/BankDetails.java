package com.example.pensionerdetailsmicroservice.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BankDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String bankName;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private BankType bankType;
}
