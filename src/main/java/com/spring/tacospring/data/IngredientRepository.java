package com.spring.tacospring.data;

import com.spring.tacospring.model.Ingredient;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findAll();

    @Query("SELECT * FROM Ingredient " +
            "JOIN Ingredient_Ref " +
            "ON Ingredient.id = Ingredient_Ref.ingredient " +
            "WHERE Ingredient_Ref.taco = :tacoId")
    List<Ingredient> findAllByTacoId(Long tacoId);
}
