package me.ea.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.ea.recipes.services.FilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
@Tag(name = "Работа с файлами", description = "загрузка и выгрузка файлов")
public class FilesController {

    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }
@Operation(summary = "Выгрузка файла рецептов")
    @GetMapping(value = "/export")
    public ResponseEntity<InputStreamResource> downloadRecipeDataFile() throws FileNotFoundException {
        File file = filesService.getRecipeDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"allRecipe.json\"")
                    .body(resource);

        } else {
            return ResponseEntity.noContent().build();
        }

    }
@Operation(summary = "Загрузка файла рецептов")
    @PostMapping(value = "/import recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeDataFile(@RequestParam MultipartFile file) {
        filesService.cleanRecipeDataFile();
        File recipeFile = filesService.getRecipeDataFile();

        try (FileOutputStream fos = new FileOutputStream(recipeFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

@Operation(summary = "Загрузка файла ингредиентов")
    @PostMapping(value = "/import ingredient")
    public ResponseEntity<Void> uploadIngredientDataFile(@RequestParam MultipartFile file){
        filesService.cleanDataFile();
        File ingredientFile = filesService.getIngredientDataFile();

        try(FileOutputStream fos = new FileOutputStream(ingredientFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
