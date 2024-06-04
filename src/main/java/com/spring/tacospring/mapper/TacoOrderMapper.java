package com.spring.tacospring.mapper;

import com.spring.tacospring.dto.OrderReadDTO;
import com.spring.tacospring.model.IngredientRef;
import com.spring.tacospring.model.Taco;
import com.spring.tacospring.model.TacoOrder;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TacoOrderMapper {
    TacoOrder toEntity(OrderReadDTO orderReadDTO);

    @AfterMapping
    default void linkTacos(@MappingTarget TacoOrder tacoOrder) {
        tacoOrder.getTacos().forEach(taco -> taco.setTacoOrder(tacoOrder));
    }

    @Mapping(source = "tacos", target = "tacos")
    OrderReadDTO toDto(TacoOrder tacoOrder);

    @Mapping(source = "ingredients", target = "ingredients")
    OrderReadDTO.TacoReadDTO tacoToTacoReadDTO(Taco taco);

    @Mapping(source = "ingredient.name", target = "name")
    @Mapping(source = "ingredient.type", target = "type")
    OrderReadDTO.TacoReadDTO.IngredientReadDTO ingredientRefToIngredientReadDTO(IngredientRef ingredientRef);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TacoOrder partialUpdate(OrderReadDTO orderReadDTO, @MappingTarget TacoOrder tacoOrder);
}