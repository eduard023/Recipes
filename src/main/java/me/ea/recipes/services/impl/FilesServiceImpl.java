package me.ea.recipes.services.impl;

import me.ea.recipes.exception.FileException;
import me.ea.recipes.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {
    @Value("${path.to.data.files}")
    public String dataFilePath;
    @Value("${name1.of.data.files}")
    private String ingredientDataFileName;
    @Value("${name2.of.data.files}")
    private String recipeDataFileName;


    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, ingredientDataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String readFromFiles() {
        try {
            return Files.readString(Path.of(dataFilePath, ingredientDataFileName));
        } catch (IOException e) {
            throw new FileException();
        }
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath, ingredientDataFileName));
            Files.createFile(Path.of(dataFilePath, ingredientDataFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override   //Файловый сервис для рецепта
    public boolean cleanRecipeDataFile() {
        try {
            Files.deleteIfExists(Path.of(dataFilePath, recipeDataFileName));
            Files.createFile(Path.of(dataFilePath, recipeDataFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromRecipeFiles() {
        try {
            return Files.readString(Path.of(dataFilePath, recipeDataFileName));
        } catch (IOException e) {
            throw new FileException();
        }
    }

    @Override
    public boolean saveToRecipeFile(String json) {
        try {
            cleanRecipeDataFile();
            Files.writeString(Path.of(dataFilePath, recipeDataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public File getRecipeDataFile() {
        return new File(dataFilePath + "/" + recipeDataFileName);
    }

    @Override
    public File getIngredientDataFile() {
        return new File(dataFilePath + "/" + ingredientDataFileName);
    }

}
