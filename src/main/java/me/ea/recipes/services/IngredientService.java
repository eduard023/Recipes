package me.ea.recipes.services;

import me.ea.recipes.model.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient addIngredient(String id, Ingredient ingredient);
    Ingredient getIngredient(String id);
    Ingredient updateIngredient(String id, Ingredient ingredient);
    Ingredient deleteIngredient(String id);
    List<Ingredient> getAllIngredient();
}
