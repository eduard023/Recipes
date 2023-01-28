package me.ea.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.ea.recipes.exception.FileException;
import me.ea.recipes.model.Ingredient;
import me.ea.recipes.model.Recipe;
import me.ea.recipes.services.FilesService;
import me.ea.recipes.exception.IdException;
import me.ea.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<String, Recipe> recipeMap = new LinkedHashMap<>();
    private final FilesService filesService;

    @Value("${name3.of.data.files}")
    private String nameToTxtTemplate;
    @Value("${path.to.data.files}")
    public String dataFilePath;

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
       try {
           readFromFile();
       } catch (Exception e){
           e.printStackTrace();
       }
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

    @Override
    public byte[] downloadTxt() {
        try {
            String template = Files.readString(Path.of(dataFilePath, nameToTxtTemplate), StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            for (Recipe recipe : recipeMap.values()) {
                StringBuilder ingredients = new StringBuilder();
                StringBuilder steps = new StringBuilder();
                for (Ingredient ingredient : recipe.getIngredientList()) {
                    ingredients.append(" - ").append(ingredient).append("\n");
                }
                int stepCounter = 1;
                for (String step : recipe.getCookingSteps()) {
                    steps.append(stepCounter++).append(". ").append(step).append("\n");
                }
                String recipeData = template.replace("%name%", recipe.getName())
                        .replace("%cookingTime%", String.valueOf(recipe.getCookingTime()))
                        .replace("%ingredientList%", ingredients.toString())
                        .replace("&cookingSteps&", steps.toString());
                stringBuilder.append(recipeData).append("\n\n\n");
            }
            return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
