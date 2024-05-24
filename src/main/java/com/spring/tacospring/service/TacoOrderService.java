package com.spring.tacospring.service;

import com.spring.tacospring.data.IngredientRepository;
import com.spring.tacospring.data.TacoOrderRepository;
import com.spring.tacospring.model.Ingredient;
import com.spring.tacospring.model.IngredientRef;
import com.spring.tacospring.model.Taco;
import com.spring.tacospring.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacoOrderService {
    private final TacoOrderRepository tacoOrderRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public TacoOrder save(TacoOrder order) {
        return tacoOrderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Iterable<TacoOrder> findAll() {
        return tacoOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TacoOrder findById(Long id) {
        Optional<TacoOrder> order = tacoOrderRepository.findById(id);
        if (order.isPresent()) {
            for (Taco taco : order.get().getTacos()) {
                List<Ingredient> ingredients = ingredientRepository.findAllByTacoId(taco.getId());
                List<IngredientRef> ingredientRefs = ingredients.stream()
                        .map(IngredientRef::new)
                        .toList();
                taco.setIngredients(ingredientRefs);
            }
        }
        return order.orElse(null);
    }
}