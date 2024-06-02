package com.spring.tacospring.data;

import com.spring.tacospring.model.Ingredient;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, ObjectId> {
    List<Ingredient> findAll();
}
