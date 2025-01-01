package com.ali.min.invoicemanager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Column(columnDefinition = "NUMERIC(38, 2)")
    private BigDecimal amount;
    private String billNo;
}
