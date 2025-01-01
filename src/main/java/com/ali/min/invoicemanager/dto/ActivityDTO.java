package com.ali.min.invoicemanager.dto;

import com.ali.min.invoicemanager.enums.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ActivityDTO {
    private Long id;
    private Status status;
    private String email;
    private String billNo;
    private String firstName;
    private String lastName;
    private BigDecimal amount;
    private LocalDateTime activityDate;
}
