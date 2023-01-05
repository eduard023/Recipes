package me.ea.recipes.services;

import me.ea.recipes.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(String id, Ingredient ingredient);
    Ingredient getIngredient(String id);
}
