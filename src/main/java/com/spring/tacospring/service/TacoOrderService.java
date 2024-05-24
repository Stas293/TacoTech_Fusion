package com.spring.tacospring.service;

import com.spring.tacospring.data.IngredientRepository;
import com.spring.tacospring.data.TacoOrderRepository;
import com.spring.tacospring.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacoOrderService {
    private final TacoOrderRepository tacoOrderRepository;
    private final IngredientRepository ingredientRepository;

    @Transactional
    public TacoOrder save(TacoOrder order) {
        order.getTacos().forEach(taco -> {
            taco.setTacoOrder(order);
            taco.getIngredients().forEach(ingredientRef -> {
                ingredientRef.setIngredient(ingredientRepository.findById(ingredientRef.getIngredient().getId()).orElse(null));
                ingredientRef.setTaco(taco);
            });
        });
        return tacoOrderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Iterable<TacoOrder> findAll() {
        return tacoOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TacoOrder findById(Long id) {
        Optional<TacoOrder> completeTacoOrder = tacoOrderRepository.findCompleteTacoOrderById(id);
        if (completeTacoOrder.isPresent()) {
            TacoOrder tacoOrder = completeTacoOrder.get();
            tacoOrder.getTacos().forEach(taco -> {
                taco.setTacoOrder(null);
            });
            return tacoOrder;
        }
        return null;
    }
}