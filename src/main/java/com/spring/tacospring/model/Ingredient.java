package com.spring.tacospring.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Ingredient {
    @Id
    private ObjectId id;

    private String name;

    private Type type;

    public Ingredient(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public enum Type {
        WRAP,
        BEEF,
        CHICKEN,
        PORK,
        FISH,
        BEANS,
        LETTUCE,
        TOMATOES,
        ONIONS,
        BELL_PEPPERS,
        CHEDDAR,
        MONTEREY_JACK,
        QUESO_FRESCO,
        SALSA,
        GUACAMOLE,
        SOUR_CREAM,
        HOT_SAUCE
    }
}
