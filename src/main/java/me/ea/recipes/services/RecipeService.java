package me.ea.recipes.services;

import me.ea.recipes.model.Recipe;

import java.util.Collection;
import java.util.List;

public interface RecipeService {

    Recipe addRecipe(String id, Recipe recipe);

    Recipe getRecipe(String id);

    Recipe updateRecipe(String id, Recipe recipe);

    Recipe deleteRecipe(String id);

    List<Recipe> getAllRecipe();
}
