package com.spring.tacospring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientRef {
    @Id
    private Long id;
    private Long ingredient;

    @Transient
    private Ingredient ingredientObject;

    public IngredientRef(Ingredient ingredient) {
        this.ingredient = ingredient.getId();
        this.ingredientObject = ingredient;
    }
}
