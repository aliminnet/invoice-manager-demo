package com.ali.min.invoicemanager.entity;

import com.ali.min.invoicemanager.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private String email;
    private String billNo;
    private String firstName;
    private String lastName;
    private BigDecimal amount;
    private LocalDateTime activityDate;
}
