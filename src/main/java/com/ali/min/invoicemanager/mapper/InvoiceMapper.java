package com.ali.min.invoicemanager.mapper;

import com.ali.min.invoicemanager.dto.InvoiceDTO;
import com.ali.min.invoicemanager.entity.InvoiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceDTO toDTO(InvoiceEntity invoiceEntity);
    InvoiceEntity toEntity(InvoiceDTO invoiceDTO);
}
