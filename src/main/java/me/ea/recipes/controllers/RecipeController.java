package me.ea.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.ea.recipes.model.Ingredient;
import me.ea.recipes.model.Recipe;
import me.ea.recipes.services.impl.RecipeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recipe")

@Tag(name = "Рецепты", description = "Crud-операции и другие эндпоинты для работы с рецептами")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    //Добавление рецепта
    @Operation(
            summary = "Добавление нового рецепта"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен",
                    content =
                    @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @PostMapping
    public ResponseEntity<String> addRecipe(String id, @RequestBody Recipe recipe) {
        this.recipeService.addRecipe(id, recipe);
        return ResponseEntity.ok("Рецепт добавлен");
    }

    //Получение id рецепта
    @Operation(summary = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content =
                    @Content(
                            mediaType = "application/json"
                    )

            )
    })
    @GetMapping("/id")
    public void getRecipe(@RequestBody String id) {
        this.recipeService.getRecipe(id);
    }

    //Обновление рецепта
    @Operation(summary = "Обновление рецепта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт обновлен",
                    content =
                    @Content(mediaType = "application/json")
            )
    })
    @PutMapping("{id}")
    public ResponseEntity<String> updateRecipe(@PathVariable String id, @RequestBody Recipe recipe) {
        this.recipeService.updateRecipe(id, recipe);
        return ResponseEntity.ok("Рецепт обновлен");
    }

    @Operation(summary = "Удаление рецепта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален",
                    content =
                    @Content(mediaType = "application/json")
            )
    })
//Удаление рецепта
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        recipeService.deleteRecipe(id);
    }

    @Operation(summary = "Вывод всех рецептов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipe() {
        List<Recipe> recipe = recipeService.getAllRecipe();
        return ResponseEntity.ok(recipe);
    }


}
