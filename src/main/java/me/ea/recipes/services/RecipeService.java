package me.ea.recipes.services;

import me.ea.recipes.model.Recipe;

public interface RecipeService {

    Recipe addRecipe(String id, Recipe recipe);
    Recipe getRecipe(String id);
}
