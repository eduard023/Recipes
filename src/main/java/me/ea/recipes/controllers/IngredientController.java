package me.ea.recipes.controllers;

import me.ea.recipes.model.Ingredient;;
import me.ea.recipes.services.impl.IngredientServicesImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientServicesImpl ingredientServices;

    public IngredientController(IngredientServicesImpl ingredientServices) {
        this.ingredientServices = ingredientServices;
    }

    @PostMapping
    public Ingredient createIngredient(String id, @RequestBody Ingredient ingredient){
        return this.ingredientServices.addIngredient(id, ingredient);
    }

    public Set<String> getAllIngredient(@PathVariable("id") String id){
        return Collections.singleton(this.ingredientServices.getIngredient(id).toString());
    }
}
