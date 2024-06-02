package com.spring.tacospring.dto;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * A DTO for the {@link com.spring.tacospring.model.TacoOrder} entity
 */
public record OrderReadDTO(
        ObjectId id,
        String deliveryName,
        String deliveryStreet,
        String deliveryCity,
        String deliveryState,
        String deliveryZip,
        String placedAt,
        List<TacoReadDTO> tacos
) {
    public record TacoReadDTO(
            String name,
            List<IngredientReadDTO> ingredients
    ) {
        public record IngredientReadDTO(
                String name,
                String type
        ) {
        }
    }
}
