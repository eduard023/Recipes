package me.ea.recipes.services.impl;

import me.ea.recipes.model.Ingredient;
import me.ea.recipes.services.IdException;
import me.ea.recipes.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class IngredientServicesImpl implements IngredientService {
    public Map<String, Ingredient> ingredientMap = new LinkedHashMap<>();

    @Override
    public Ingredient addIngredient(String id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)){
            return ingredientMap.put(id, ingredient);
        }else {
            throw new IdException();
        }
    }

    @Override
    public Ingredient getIngredient(String id) {
        if (ingredientMap.containsKey(id)){
            return ingredientMap.get(id);
        }else {
            throw new IdException();
        }
    }

    @Override
    public Ingredient updateIngredient(String id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)){
            return ingredientMap.put(id, ingredient);
        }else {
            throw new IdException();
        }
    }

    @Override
    public Ingredient deleteIngredient(String id) {
        if (ingredientMap.containsKey(id)){
            return ingredientMap.remove(id);
        }else {
            throw new IdException();
        }
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        List<Ingredient> s = new ArrayList<>();
        for (Map.Entry<String, Ingredient> ingredientEntry : ingredientMap.entrySet()){
            s.add(ingredientEntry.getValue());
        }
        return s;
    }

}
