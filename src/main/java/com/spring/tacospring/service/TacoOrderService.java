package com.spring.tacospring.service;

import com.spring.tacospring.data.TacoOrderRepository;
import com.spring.tacospring.dto.BaseOrderReadDTO;
import com.spring.tacospring.dto.OrderReadDTO;
import com.spring.tacospring.mapper.BaseTacoOrderMapper;
import com.spring.tacospring.mapper.TacoOrderMapper;
import com.spring.tacospring.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacoOrderService {
    private final TacoOrderRepository tacoOrderRepository;
    private final BaseTacoOrderMapper tacoOrderMapper;
    private final TacoOrderMapper orderMapper;

    @Transactional
    public TacoOrder save(TacoOrder order) {
        return tacoOrderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Page<BaseOrderReadDTO> findAll(Pageable pageable) {
        return tacoOrderRepository.findAll(pageable)
                .map(tacoOrderMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<OrderReadDTO> findById(ObjectId id) {
        return tacoOrderRepository.findById(id)
                .map(orderMapper::toDto);
    }
}