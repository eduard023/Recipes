package me.ea.recipes.services.impl;

import me.ea.recipes.model.Recipe;
import me.ea.recipes.services.IdException;
import me.ea.recipes.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<String, Recipe> recipeMap = new LinkedHashMap<>();

    @Override
    public Recipe addRecipe(String id, Recipe recipe) {
        if (!recipeMap.containsKey(id)){
            return recipeMap.put(id, recipe);
        }else {
            throw new IdException();
        }
    }

    @Override
    public Recipe getRecipe(String id) {
        if (recipeMap.containsKey(id)){
            return recipeMap.get(id);
        }else{
            throw new IdException();
        }
    }

    @Override
    public Recipe updateRecipe(String id, Recipe recipe) {
        if (recipeMap.containsKey(id)){
            return recipeMap.put(id, recipe);
        }else {
            throw new IdException();
        }
    }

    @Override
    public Recipe deleteRecipe(String id) {
        if (recipeMap.containsKey(id)){
            return recipeMap.remove(id);
        }else {
            throw new IdException();
        }
    }

    @Override
    public List<Recipe> getAllRecipe() {
        List<Recipe> result = new ArrayList<>();
        for (Map.Entry<String, Recipe> recipeEntry : recipeMap.entrySet()){
            result.add(recipeEntry.getValue());
        }
        return result;
    }

}
