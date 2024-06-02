package com.spring.tacospring.converter;

import com.spring.tacospring.data.IngredientRepository;
import com.spring.tacospring.model.Ingredient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        log.info("Converting id to ingredient: {}", id);
        return ingredientRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
    }
}
