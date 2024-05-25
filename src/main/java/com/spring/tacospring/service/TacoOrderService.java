package com.spring.tacospring.service;

import com.spring.tacospring.data.TacoOrderRepository;
import com.spring.tacospring.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TacoOrderService {
    private final TacoOrderRepository tacoOrderRepository;

    @Transactional
    public TacoOrder save(TacoOrder order) {
        return tacoOrderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Iterable<TacoOrder> findAll() {
        return tacoOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TacoOrder findById(String id) {
        return tacoOrderRepository.findById(UUID.fromString(id)).orElse(null);
    }
}