package com.spring.tacospring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UserDefinedType("taco")
public class TacoUDT {
    private String name;

    private List<IngredientUDT> ingredients;

    public TacoUDT(Taco taco) {
        this.name = taco.getName();
        this.ingredients = taco.getIngredients();
    }
}
