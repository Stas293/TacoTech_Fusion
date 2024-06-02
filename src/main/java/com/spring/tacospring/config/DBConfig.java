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
        repo.deleteAll();
        return args -> repo.saveAll(List.of(
                new Ingredient("Whole Wheat Wrap", Ingredient.Type.WRAP),
                new Ingredient("Spinach Wrap", Ingredient.Type.WRAP),
                new Ingredient("Gluten Free Wrap", Ingredient.Type.WRAP),
                new Ingredient("Ground Beef", Ingredient.Type.BEEF),
                new Ingredient("Steak Strips", Ingredient.Type.BEEF),
                new Ingredient("Roast Beef", Ingredient.Type.BEEF),
                new Ingredient("Chicken Breast", Ingredient.Type.CHICKEN),
                new Ingredient("Chicken Thigh", Ingredient.Type.CHICKEN),
                new Ingredient("Chicken Wings", Ingredient.Type.CHICKEN),
                new Ingredient("Pulled Pork", Ingredient.Type.PORK),
                new Ingredient("Pork Chops", Ingredient.Type.PORK),
                new Ingredient("Pork Ribs", Ingredient.Type.PORK),
                new Ingredient("Fish Fillet", Ingredient.Type.FISH),
                new Ingredient("Shrimp", Ingredient.Type.FISH),
                new Ingredient("Crab", Ingredient.Type.FISH),
                new Ingredient("Black Beans", Ingredient.Type.BEANS),
                new Ingredient("Pinto Beans", Ingredient.Type.BEANS),
                new Ingredient("Refried Beans", Ingredient.Type.BEANS),
                new Ingredient("Iceberg Lettuce", Ingredient.Type.LETTUCE),
                new Ingredient("Romaine Lettuce", Ingredient.Type.LETTUCE),
                new Ingredient("Spring Mix", Ingredient.Type.LETTUCE),
                new Ingredient("Roma Tomatoes", Ingredient.Type.TOMATOES),
                new Ingredient("Cherry Tomatoes", Ingredient.Type.TOMATOES),
                new Ingredient("Green Onions", Ingredient.Type.ONIONS),
                new Ingredient("Yellow Onions", Ingredient.Type.ONIONS),
                new Ingredient("Red Onions", Ingredient.Type.ONIONS),
                new Ingredient("Green Bell Peppers", Ingredient.Type.BELL_PEPPERS),
                new Ingredient("Red Bell Peppers", Ingredient.Type.BELL_PEPPERS),
                new Ingredient("Orange Bell Peppers", Ingredient.Type.BELL_PEPPERS),
                new Ingredient("Cheddar Cheese", Ingredient.Type.CHEDDAR),
                new Ingredient("Monterey Jack Cheese", Ingredient.Type.MONTEREY_JACK),
                new Ingredient("Queso Fresco", Ingredient.Type.QUESO_FRESCO),
                new Ingredient("Salsa", Ingredient.Type.SALSA),
                new Ingredient("Guacamole", Ingredient.Type.GUACAMOLE),
                new Ingredient("Sour Cream", Ingredient.Type.SOUR_CREAM),
                new Ingredient("Hot Sauce", Ingredient.Type.HOT_SAUCE)
        ));
    }
}
