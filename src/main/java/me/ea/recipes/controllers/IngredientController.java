package me.ea.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.ea.recipes.model.Ingredient;;
import me.ea.recipes.services.impl.IngredientServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "Crud-операции и другие эндпоинты для работы с ингредиентами")
public class IngredientController {

    private final IngredientServicesImpl ingredientServices;

    public IngredientController(IngredientServicesImpl ingredientServices) {
        this.ingredientServices = ingredientServices;
    }
//Добавление ингредиета
@Operation(
        summary = "Добавление нового ингредиента"
)
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Ингредиент добавлен",
                content =
                @Content(
                        mediaType = "application/json"
                )
        )
})
    @PostMapping
    public ResponseEntity<String> addIngredient(String id, @RequestBody Ingredient ingredient){
        this.ingredientServices.addIngredient(id, ingredient);
        return ResponseEntity.ok("Ингредиент добавлен");
    }
//Получение id ингредиета
@Operation( summary = "Поиск ингредиента по id")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Ингредиент найден",
                content =
                @Content(
                        mediaType = "application/json"
                )

        )
})
    @GetMapping("/id")
    public void getIngredient(@RequestBody String id){
        this.ingredientServices.getIngredient(id);
    }
//Обновление ингредиета
@Operation(summary = "Обновление ингредиента")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Ингредиент обновлен",
                content =
                @Content(mediaType = "application/json")
        )
})

    @PutMapping("{id}")
    public ResponseEntity<String> updateIngredient(@PathVariable String id, @RequestBody Ingredient ingredient){
        this.ingredientServices.updateIngredient(id, ingredient);
        return ResponseEntity.ok("Ингердиент обновлен");
    }

//Удаление ингредиента
@Operation(summary = "Удаление ингредиента")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Ингредиент удален",
                content =
                @Content(mediaType = "application/json")
        )
})
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        ingredientServices.deleteIngredient(id);
    }

@Operation(summary = "вывод всех ингредиентов")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Ингредиенты были найдены",
                content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                        )
                }
        )
})
    @GetMapping()
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        List<Ingredient> ingredientList = ingredientServices.getAllIngredient();
        return ResponseEntity.ok(ingredientList);
    }

}
