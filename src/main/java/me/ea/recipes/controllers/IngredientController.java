package me.ea.recipes.controllers;

import me.ea.recipes.model.Ingredient;;
import me.ea.recipes.services.impl.IngredientServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientServicesImpl ingredientServices;

    public IngredientController(IngredientServicesImpl ingredientServices) {
        this.ingredientServices = ingredientServices;
    }
//Добавление ингредиета
    @PostMapping("/addIngredient")
    public ResponseEntity<String> addIngredient(String id, @RequestBody Ingredient ingredient){
        this.ingredientServices.addIngredient(id, ingredient);
        return ResponseEntity.ok("Ингредиент добавлен");
    }
//Получение id ингредиета
    @GetMapping("/id")
    public void getIngredient(@RequestBody String id){
        this.ingredientServices.getIngredient(id);
    }
//Обновление ингредиета
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateIngredient(@PathVariable String id, @RequestBody Ingredient ingredient){
        this.ingredientServices.updateIngredient(id, ingredient);
        return ResponseEntity.ok("Ингердиент обновлен");
    }

//Удаление ингредиента
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        ingredientServices.deleteIngredient(id);
    }

    @GetMapping("/allIngredients")
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        List<Ingredient> ingredientList = ingredientServices.getAllIngredient();
        return ResponseEntity.ok(ingredientList);
    }

}
