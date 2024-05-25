package com.spring.tacospring.config;

import com.spring.tacospring.data.IngredientRepository;
import com.spring.tacospring.model.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DBConfig {
    @Bean
    CommandLineRunner dataLoader(IngredientRepository repo) {
        return args -> repo.saveAll(List.of(
                new Ingredient(1L, "Whole Wheat Wrap", Ingredient.Type.WRAP),
                new Ingredient(2L, "Spinach Wrap", Ingredient.Type.WRAP),
                new Ingredient(3L, "Gluten Free Wrap", Ingredient.Type.WRAP),
                new Ingredient(4L, "Ground Beef", Ingredient.Type.BEEF),
                new Ingredient(5L, "Steak Strips", Ingredient.Type.BEEF),
                new Ingredient(6L, "Roast Beef", Ingredient.Type.BEEF),
                new Ingredient(7L, "Chicken Breast", Ingredient.Type.CHICKEN),
                new Ingredient(8L, "Chicken Thigh", Ingredient.Type.CHICKEN),
                new Ingredient(9L, "Chicken Wings", Ingredient.Type.CHICKEN),
                new Ingredient(10L, "Pulled Pork", Ingredient.Type.PORK),
                new Ingredient(11L, "Pork Chops", Ingredient.Type.PORK),
                new Ingredient(12L, "Pork Ribs", Ingredient.Type.PORK),
                new Ingredient(13L, "Fish Fillet", Ingredient.Type.FISH),
                new Ingredient(14L, "Shrimp", Ingredient.Type.FISH),
                new Ingredient(15L, "Crab", Ingredient.Type.FISH),
                new Ingredient(16L, "Black Beans", Ingredient.Type.BEANS),
                new Ingredient(17L, "Pinto Beans", Ingredient.Type.BEANS),
                new Ingredient(18L, "Refried Beans", Ingredient.Type.BEANS),
                new Ingredient(19L, "Iceberg Lettuce", Ingredient.Type.LETTUCE),
                new Ingredient(20L, "Romaine Lettuce", Ingredient.Type.LETTUCE),
                new Ingredient(21L, "Spring Mix", Ingredient.Type.LETTUCE),
                new Ingredient(22L, "Roma Tomatoes", Ingredient.Type.TOMATOES),
                new Ingredient(23L, "Cherry Tomatoes", Ingredient.Type.TOMATOES),
                new Ingredient(24L, "Green Onions", Ingredient.Type.ONIONS),
                new Ingredient(25L, "Yellow Onions", Ingredient.Type.ONIONS),
                new Ingredient(26L, "Red Onions", Ingredient.Type.ONIONS),
                new Ingredient(27L, "Green Bell Peppers", Ingredient.Type.BELL_PEPPERS),
                new Ingredient(28L, "Red Bell Peppers", Ingredient.Type.BELL_PEPPERS),
                new Ingredient(29L, "Orange Bell Peppers", Ingredient.Type.BELL_PEPPERS),
                new Ingredient(30L, "Cheddar Cheese", Ingredient.Type.CHEDDAR),
                new Ingredient(31L, "Monterey Jack Cheese", Ingredient.Type.MONTEREY_JACK),
                new Ingredient(32L, "Queso Fresco", Ingredient.Type.QUESO_FRESCO),
                new Ingredient(33L, "Salsa", Ingredient.Type.SALSA),
                new Ingredient(34L, "Guacamole", Ingredient.Type.GUACAMOLE),
                new Ingredient(35L, "Sour Cream", Ingredient.Type.SOUR_CREAM),
                new Ingredient(36L, "Hot Sauce", Ingredient.Type.HOT_SAUCE)
        ));
    }
}
