package com.ali.min.invoicemanager.dto;

import com.ali.min.invoicemanager.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ResultDTO {
    private String message;
    private Status status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
