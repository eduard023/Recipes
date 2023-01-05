package me.ea.recipes.services.impl;

import me.ea.recipes.model.Ingredient;
import me.ea.recipes.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class IngredientServicesImpl implements IngredientService {
    public Map<String, Ingredient> ingredientMap = new LinkedHashMap<>();

    @Override
    public Ingredient addIngredient(String id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)){
            return ingredientMap.put(id, ingredient);
        }else {
            throw new RuntimeException("id уже существует");
        }
    }

    @Override
    public Ingredient getIngredient(String id) {
        if (ingredientMap.containsKey(id)){
            return ingredientMap.get(id);
        }else {
            throw new RuntimeException("id не найден");
        }
    }
}
