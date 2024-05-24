package com.spring.tacospring.data;

import com.spring.tacospring.model.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findAll();

    @Query("select i FROM Taco t JOIN t.ingredients it join it.ingredient i where t.id = :tacoId")
    List<Ingredient> findAllByTacoId(Long tacoId);
}
