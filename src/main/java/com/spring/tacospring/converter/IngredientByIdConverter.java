package com.spring.tacospring.converter;

import com.spring.tacospring.data.IngredientRepository;
import com.spring.tacospring.model.Ingredient;
import com.spring.tacospring.model.IngredientRef;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class IngredientByIdConverter implements Converter<String, IngredientRef> {
    private final IngredientRepository ingredientRepository;

    @Override
    public IngredientRef convert(String id) {
        log.info("Converting id to ingredient: {}", id);
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return ingredient.map(value -> new IngredientRef(null, value)).orElse(null);
    }
}
