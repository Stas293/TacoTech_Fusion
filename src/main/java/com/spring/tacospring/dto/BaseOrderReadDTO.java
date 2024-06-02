package com.spring.tacospring.dto;

import java.util.UUID;

/**
 * A DTO for the {@link com.spring.tacospring.model.TacoOrder} entity
 */
public record BaseOrderReadDTO(
        UUID id,
        String deliveryName,
        String deliveryStreet,
        String deliveryCity,
        String deliveryState,
        String deliveryZip,
        String placedAt
) {
}
