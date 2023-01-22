package me.ea.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.ea.recipes.exception.FileException;
import me.ea.recipes.model.Ingredient;
import me.ea.recipes.services.FilesService;
import me.ea.recipes.exception.IdException;
import me.ea.recipes.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientServicesImpl implements IngredientService {
    public Map<String, Ingredient> ingredientMap = new LinkedHashMap<>();
    private final FilesService filesService;

    public IngredientServicesImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Ingredient addIngredient(String id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            saveToFile();
            return ingredient;
        } else {
            throw new IdException();
        }
    }

    @Override
    public Ingredient getIngredient(String id) {
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.get(id);
        } else {
            throw new IdException();
        }
    }

    @Override
    public Ingredient updateIngredient(String id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            saveToFile();
            return ingredient;
        } else {
            throw new IdException();
        }
    }

    @Override
    public Ingredient deleteIngredient(String id) {
        if (ingredientMap.containsKey(id)) {
            return ingredientMap.remove(id);
        } else {
            throw new IdException();
        }
    }

    @Override
    public List<Ingredient> getAllIngredient() {
        List<Ingredient> s = new ArrayList<>();
        for (Map.Entry<String, Ingredient> ingredientEntry : ingredientMap.entrySet()) {
            s.add(ingredientEntry.getValue());
        }
        return s;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new FileException();
        }

    }

    private void readFromFile() {
        String json = filesService.readFromFiles();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<String, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new FileException();
        }

    }

}
