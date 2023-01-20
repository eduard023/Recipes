package me.ea.recipes.controllers;

import me.ea.recipes.model.Recipe;
import me.ea.recipes.services.impl.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recipe")

public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }
//Добавление рецепта
    @PostMapping
    public ResponseEntity<String> addRecipe(String id, @RequestBody Recipe recipe){
        this.recipeService.addRecipe(id, recipe);
        return ResponseEntity.ok("Рецепт добавлен");
    }

//Получение id рецепта
    @GetMapping("/id")
    public void getRecipe(@RequestBody String id){
        this.recipeService.getRecipe(id);
    }

//Обновление рецепта
    @PutMapping("{id}")
    public ResponseEntity<String> updateRecipe(@PathVariable String id, @RequestBody Recipe recipe){
        this.recipeService.updateRecipe(id, recipe);
        return ResponseEntity.ok("Рецепт обновлен");
    }

//Удаление рецепта
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        recipeService.deleteRecipe(id);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipe(){
        List<Recipe> recipe = recipeService.getAllRecipe();
        return ResponseEntity.ok(recipe);
    }


}
