package me.ea.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ingredient {
    private String name;
    private int weight;
    private String measureUnit;

    @Override
    public String toString() {
        return name + " - " + weight + " " + measureUnit;
    }
}
