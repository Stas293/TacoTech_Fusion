package com.spring.tacospring.dto;

/**
 * A DTO for the {@link com.spring.tacospring.model.TacoOrder} entity
 */
public record BaseOrderReadDTO(
    Long id,
    String deliveryName,
    String deliveryStreet,
    String deliveryCity,
    String deliveryState,
    String deliveryZip,
    String placedAt
) {
}
