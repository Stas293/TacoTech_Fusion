package com.spring.tacospring.service;

import com.spring.tacospring.data.IngredientRepository;
import com.spring.tacospring.data.TacoOrderRepository;
import com.spring.tacospring.dto.BaseOrderReadDTO;
import com.spring.tacospring.dto.OrderReadDTO;
import com.spring.tacospring.mapper.BaseTacoOrderMapper;
import com.spring.tacospring.mapper.TacoOrderMapper;
import com.spring.tacospring.model.AddressInformation;
import com.spring.tacospring.model.TacoOrder;
import com.spring.tacospring.model.User;
import com.spring.tacospring.model.UserInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacoOrderService {
    private final TacoOrderRepository tacoOrderRepository;
    private final IngredientRepository ingredientRepository;
    private final TacoOrderMapper tacoOrderMapper;
    private final BaseTacoOrderMapper baseTacoOrderMapper;

    @Transactional
    public TacoOrder save(TacoOrder order, UserDetails user) {
        UserInformation userInformation = (UserInformation) user;
        order.setUser(User.builder()
                .id(userInformation.getId())
                .build());
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
    public Page<BaseOrderReadDTO> findAll(Pageable pageable, UserInformation userDetails) {
        return tacoOrderRepository.findAllByUser_IdOrderByPlacedAtDesc(pageable, userDetails.getId())
                .map(baseTacoOrderMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<OrderReadDTO> findById(Long id, UserInformation user) {
        return tacoOrderRepository.findCompleteTacoOrderById(id, user.getId())
                .map(tacoOrderMapper::toDto);
    }

    public TacoOrder createOrder(AddressInformation user) {
        return TacoOrder.builder()
                .deliveryCity(user.getCity())
                .deliveryName(user.getFullname())
                .deliveryState(user.getState())
                .deliveryStreet(user.getStreet())
                .deliveryZip(user.getZip())
                .build();
    }
}