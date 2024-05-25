package com.spring.tacospring.converter;

import com.spring.tacospring.data.IngredientRepository;
import com.spring.tacospring.model.Ingredient;
import com.spring.tacospring.model.IngredientUDT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {
    private final IngredientRepository ingredientRepository;

    @Override
    public IngredientUDT convert(String id) {
        log.info("Converting id to ingredient: {}", id);
        Optional<Ingredient> ingredient = ingredientRepository.findById(Long.valueOf(id));
        return ingredient.map(IngredientUDT::new).orElse(null);
    }
}
