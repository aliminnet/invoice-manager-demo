package com.ali.min.invoicemanager.mapper;

import com.ali.min.invoicemanager.dto.ActivityDTO;
import com.ali.min.invoicemanager.entity.ActivityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    ActivityDTO toDTO(ActivityEntity productEntity);

    ActivityEntity toEntity(ActivityDTO productDTO);
}
