package com.spring.tacospring.service;

import com.spring.tacospring.model.Taco;
import com.spring.tacospring.model.TacoOrder;
import com.spring.tacospring.data.TacoOrderRepository;
import com.spring.tacospring.data.TacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoOrderService {
    private final TacoOrderRepository tacoOrderRepository;
    private final TacoRepository tacoRepository;

    @Transactional
    public TacoOrder save(TacoOrder order) {
        tacoOrderRepository.save(order);
        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            tacoRepository.save(order.getId(), i++, taco);
        }
        return order;
    }
}