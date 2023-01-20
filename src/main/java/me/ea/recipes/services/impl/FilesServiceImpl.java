package me.ea.recipes.services.impl;

import me.ea.recipes.services.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {
@Value("${path.to.data.files}")
    private String dataFilePath;
@Value("${name1.of.data.files}")
    private String dataFileName;
@Value("${name2.of.data.files}")
    private String recipeDataFileName;
@Override
    public boolean saveToFile(String json){
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
@Override
    public String readFromFiles(){
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFile(){
        try {
            Files.deleteIfExists(Path.of(dataFilePath, dataFileName));
            Files.createFile(Path.of(dataFilePath, dataFileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Файловый сервис для рецепта
    private boolean cleanRecipeDataFile(){
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
    public String readFromRecipeFiles(){
        try {
            return Files.readString(Path.of(dataFilePath, recipeDataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveToRecipeFile(String json){
        try {
            cleanRecipeDataFile();
            Files.writeString(Path.of(dataFilePath, recipeDataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
