package me.ea.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.ea.recipes.exception.FileException;
import me.ea.recipes.model.Recipe;
import me.ea.recipes.services.FilesService;
import me.ea.recipes.exception.IdException;
import me.ea.recipes.services.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<String, Recipe> recipeMap = new LinkedHashMap<>();
    private final FilesService filesService;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Recipe addRecipe(String id, Recipe recipe) {
        if (!recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            saveToFile();
            return recipe;
        } else {
            throw new IdException();
        }
    }

    @Override
    public Recipe getRecipe(String id) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.get(id);
        } else {
            throw new IdException();
        }
    }

    @Override
    public Recipe updateRecipe(String id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            saveToFile();
            return recipe;
        } else {
            throw new IdException();
        }
    }

    @Override
    public Recipe deleteRecipe(String id) {
        if (recipeMap.containsKey(id)) {
            return recipeMap.remove(id);
        } else {
            throw new IdException();
        }
    }

    @Override
    public List<Recipe> getAllRecipe() {
        List<Recipe> result = new ArrayList<>();
        for (Map.Entry<String, Recipe> recipeEntry : recipeMap.entrySet()) {
            result.add(recipeEntry.getValue());
        }
        return result;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesService.saveToRecipeFile(json);
        } catch (JsonProcessingException e) {
            throw new FileException();
        }
    }

    private void readFromFile() {
        String json = filesService.readFromRecipeFiles();
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<String, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new FileException();
        }
    }

}
