package com.spring.tacospring.mapper;

import com.spring.tacospring.dto.OrderReadDTO;
import com.spring.tacospring.model.TacoOrder;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TacoOrderMapper {
    TacoOrder toEntity(OrderReadDTO orderReadDTO);

    OrderReadDTO toDto(TacoOrder tacoOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TacoOrder partialUpdate(OrderReadDTO orderReadDTO, @MappingTarget TacoOrder tacoOrder);
}
