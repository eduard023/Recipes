package me.ea.recipes.services.impl;

import me.ea.recipes.model.Ingredient;
import me.ea.recipes.model.Recipe;
import me.ea.recipes.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<String, Recipe> recipeMap = new LinkedHashMap<>();

    @Override
    public Recipe addRecipe(String id, Recipe recipe) {
        if (!recipeMap.containsKey(id)){
            return recipeMap.put(id, recipe);
        }else {
            throw new RuntimeException("id уже существует");
        }
    }

    @Override
    public Recipe getRecipe(String id) {
        if (recipeMap.containsKey(id)){
            return recipeMap.get(id);
        }else{
            throw new RuntimeException("id не найден");
        }
    }
}
