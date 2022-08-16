package com.example.pensionerdetailsmicroservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PensionerDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String aadhaarNumber;
    private String name;
    private LocalDate dateOfBirth;
    private String PAN;
    private BigDecimal SalaryEarned;
    private BigDecimal allowences;
    @Enumerated(value = EnumType.STRING)
    private PensionType pensionType;
    @OneToOne
    private BankDetails bankDetails;
}
