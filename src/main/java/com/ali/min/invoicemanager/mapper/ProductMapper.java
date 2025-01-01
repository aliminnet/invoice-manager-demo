package com.ali.min.invoicemanager.mapper;

import com.ali.min.invoicemanager.dto.ProductDTO;
import com.ali.min.invoicemanager.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(ProductEntity productEntity);
    ProductEntity toEntity(ProductDTO productDTO);
}
