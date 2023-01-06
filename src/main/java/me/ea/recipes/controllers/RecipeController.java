package me.ea.recipes.controllers;

import me.ea.recipes.model.Recipe;
import me.ea.recipes.services.impl.RecipeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/recipe")

public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }
    @PostMapping
    public Recipe createRecipe(String id, @RequestBody Recipe recipe){
        return this.recipeService.addRecipe(id, recipe);
    }

    @GetMapping("/{id}")
    public Set<String>getRecipeById(@PathVariable("id") String id){
        return Collections.singleton(this.recipeService.getRecipe(id).toString());
    }


}
