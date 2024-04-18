package com.spring.tacospring.controller;

import com.spring.tacospring.model.Ingredient;
import com.spring.tacospring.model.Taco;
import com.spring.tacospring.model.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("1", "Soft Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("2", "Hard Corn Shell", Ingredient.Type.WRAP),
                new Ingredient("3", "Whole Wheat Tortilla", Ingredient.Type.WRAP),
                new Ingredient("4", "Beef", Ingredient.Type.BEEF),
                new Ingredient("5", "Chicken", Ingredient.Type.CHICKEN),
                new Ingredient("6", "Pork", Ingredient.Type.PORK),
                new Ingredient("7", "Fish", Ingredient.Type.FISH),
                new Ingredient("8", "Shrimp", Ingredient.Type.FISH),
                new Ingredient("9", "Beans", Ingredient.Type.BEANS),
                new Ingredient("10", "Black Beans", Ingredient.Type.BEANS),
                new Ingredient("11", "Lettuce", Ingredient.Type.LETTUCE),
                new Ingredient("12", "Spinach", Ingredient.Type.LETTUCE),
                new Ingredient("13", "Tomatoes", Ingredient.Type.TOMATOES),
                new Ingredient("14", "Cherry Tomatoes", Ingredient.Type.TOMATOES),
                new Ingredient("15", "Onions", Ingredient.Type.ONIONS),
                new Ingredient("16", "Red Onions", Ingredient.Type.ONIONS),
                new Ingredient("17", "Bell Peppers", Ingredient.Type.BELL_PEPPERS),
                new Ingredient("18", "Green Bell Peppers", Ingredient.Type.BELL_PEPPERS),
                new Ingredient("19", "Cheddar", Ingredient.Type.CHEDDAR),
                new Ingredient("20", "Monterey Jack", Ingredient.Type.MONTEREY_JACK),
                new Ingredient("21", "Queso Fresco", Ingredient.Type.QUESO_FRESCO),
                new Ingredient("22", "Mozzarella", Ingredient.Type.QUESO_FRESCO),
                new Ingredient("23", "Salsa", Ingredient.Type.SALSA),
                new Ingredient("24", "Hot Salsa", Ingredient.Type.SALSA),
                new Ingredient("25", "Guacamole", Ingredient.Type.GUACAMOLE),
                new Ingredient("26", "Sour Cream", Ingredient.Type.SOUR_CREAM),
                new Ingredient("27", "Hot Sauce", Ingredient.Type.HOT_SAUCE),
                new Ingredient("28", "Extra Hot Sauce", Ingredient.Type.HOT_SAUCE)
        );
        Ingredient.Type[] types = Ingredient.Type.values();
        Arrays.stream(types).forEach(type -> model.addAttribute(
                type.toString().toLowerCase(),
                filterByType(ingredients, type)
        ));
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.add(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients,
                                              Ingredient.Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .toList();
    }
}

