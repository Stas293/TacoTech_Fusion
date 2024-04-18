package com.spring.tacospring.converter;

import com.spring.tacospring.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("1", new Ingredient("1", "Soft Flour Tortilla", Ingredient.Type.WRAP));
        ingredientMap.put("2", new Ingredient("2", "Hard Corn Shell", Ingredient.Type.WRAP));
        ingredientMap.put("3", new Ingredient("3", "Beef", Ingredient.Type.BEEF));
        ingredientMap.put("4", new Ingredient("4", "Chicken", Ingredient.Type.CHICKEN));
        ingredientMap.put("5", new Ingredient("5", "Pork", Ingredient.Type.PORK));
        ingredientMap.put("6", new Ingredient("6", "Fish", Ingredient.Type.FISH));
        ingredientMap.put("7", new Ingredient("7", "Beans", Ingredient.Type.BEANS));
        ingredientMap.put("8", new Ingredient("8", "Lettuce", Ingredient.Type.LETTUCE));
        ingredientMap.put("9", new Ingredient("9", "Tomatoes", Ingredient.Type.TOMATOES));
        ingredientMap.put("10", new Ingredient("10", "Onions", Ingredient.Type.ONIONS));
        ingredientMap.put("11", new Ingredient("11", "Bell Peppers", Ingredient.Type.BELL_PEPPERS));
        ingredientMap.put("12", new Ingredient("12", "Cheddar", Ingredient.Type.CHEDDAR));
        ingredientMap.put("13", new Ingredient("13", "Monterey Jack", Ingredient.Type.MONTEREY_JACK));
        ingredientMap.put("14", new Ingredient("14", "Queso Fresco", Ingredient.Type.QUESO_FRESCO));
        ingredientMap.put("15", new Ingredient("15", "Salsa", Ingredient.Type.SALSA));
        ingredientMap.put("16", new Ingredient("16", "Guacamole", Ingredient.Type.GUACAMOLE));
        ingredientMap.put("17", new Ingredient("17", "Sour Cream", Ingredient.Type.SOUR_CREAM));
        ingredientMap.put("18", new Ingredient("18", "Hot Sauce", Ingredient.Type.HOT_SAUCE));
    }

    @Override
    public Ingredient convert(String id) {
        log.info("Converting id to ingredient: {}", id);
        return ingredientMap.get(id);
    }
}
