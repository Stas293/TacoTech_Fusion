package com.spring.tacospring.controller;


import com.spring.tacospring.dto.OrderReadDTO;
import com.spring.tacospring.model.TacoOrder;
import com.spring.tacospring.model.User;
import com.spring.tacospring.service.TacoOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String processOrder(@Valid TacoOrder order, BindingResult errors,
                               SessionStatus sessionStatus, RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tacoOrder", errors);
            return "redirect:/orders/current";
        }

        log.info("Order submitted: {}", order);
        tacoOrderService.save(order, user);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String orders(@PageableDefault(size = 5) Pageable pageable, Model model,
                         @AuthenticationPrincipal User user) {
        model.addAttribute("orders", tacoOrderService.findAll(pageable, user));
        return "orders";
    }

    @GetMapping("/{id}")
    public String order(@PathVariable Long id, Model model, @AuthenticationPrincipal User user) {
        Optional<OrderReadDTO> order = tacoOrderService.findById(id, user);
        if (order.isEmpty()) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order.get());
        return "order";
    }
}
