package com.spring.tacospring.controller;


import com.spring.tacospring.model.TacoOrder;
import com.spring.tacospring.service.TacoOrderService;
import com.spring.tacospring.utility.PageModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String orders(Model model, @RequestParam(required = false) String pagingState) {
        Pageable pageable = PageRequest.of(0, 2);
        PageModel<TacoOrder> pageModel = tacoOrderService.findAll(pageable, pagingState);
        model.addAttribute("orders", pageModel.page());
        if (pageModel.nextPagingState() != null) {
            model.addAttribute("nextPagingState", pageModel.nextPagingState());
        }
        return "orders";
    }

    @GetMapping("/{id}")
    public String order(Model model, @PathVariable String id) {
        TacoOrder order = tacoOrderService.findById(id);
        if (order == null) {
            return "redirect:/orders";
        }
        model.addAttribute("order", order);
        return "order";
    }
}
