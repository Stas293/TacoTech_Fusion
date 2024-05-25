package com.spring.tacospring.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("ingredients")
public class Ingredient {
    @PrimaryKey
    private Long id;

    private String name;

    private Type type;

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
