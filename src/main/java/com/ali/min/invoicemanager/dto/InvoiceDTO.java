package com.ali.min.invoicemanager.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InvoiceDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal amount;
    private String billNo;
}
