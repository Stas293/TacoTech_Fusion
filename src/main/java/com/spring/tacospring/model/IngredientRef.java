package com.spring.tacospring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientRef {
    private Long id;
    private Taco taco;
    private Ingredient ingredient;

    public IngredientRef(Taco taco, Ingredient ingredient) {
        this.taco = taco;
        this.ingredient = ingredient;
    }
}
