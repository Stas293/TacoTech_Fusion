package com.spring.tacospring.controller;


import com.spring.tacospring.model.TacoOrder;
import com.spring.tacospring.service.TacoOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

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
    public ResponseEntity<Iterable<TacoOrder>> orders() {
        return ResponseEntity.ok(tacoOrderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoOrder> order(@PathVariable Long id) {
        return ResponseEntity.ok(tacoOrderService.findById(id));
    }
}
