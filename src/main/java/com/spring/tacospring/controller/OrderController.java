package com.spring.tacospring.controller;


import com.spring.tacospring.dto.OrderReadDTO;
import com.spring.tacospring.model.TacoOrder;
import com.spring.tacospring.service.TacoOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private final TacoOrderService tacoOrderService;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: {}", order);
        tacoOrderService.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String orders(@PageableDefault(size = 5) Pageable pageable, Model model) {
        model.addAttribute("orders", tacoOrderService.findAll(pageable));
        return "orders";
    }

    @GetMapping("/{id}")
    public String order(@PathVariable ObjectId id, Model model) {
        Optional<OrderReadDTO> order = tacoOrderService.findById(id);
        if (order.isEmpty()) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order.get());
        return "order";
    }
}
