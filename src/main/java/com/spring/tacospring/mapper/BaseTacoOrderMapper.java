package com.spring.tacospring.mapper;

import com.spring.tacospring.dto.BaseOrderReadDTO;
import com.spring.tacospring.model.TacoOrder;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BaseTacoOrderMapper {
    TacoOrder toEntity(BaseOrderReadDTO orderReadDTO);

    BaseOrderReadDTO toDto(TacoOrder tacoOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TacoOrder partialUpdate(BaseOrderReadDTO orderReadDTO, @MappingTarget TacoOrder tacoOrder);
}