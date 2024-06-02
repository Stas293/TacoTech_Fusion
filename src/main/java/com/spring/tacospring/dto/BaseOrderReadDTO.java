package com.spring.tacospring.dto;

import org.bson.types.ObjectId;

/**
 * A DTO for the {@link com.spring.tacospring.model.TacoOrder} entity
 */
public record BaseOrderReadDTO(
        ObjectId id,
        String deliveryName,
        String deliveryStreet,
        String deliveryCity,
        String deliveryState,
        String deliveryZip,
        String placedAt
) {
}
