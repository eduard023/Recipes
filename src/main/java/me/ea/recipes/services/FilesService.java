package me.ea.recipes.services;

import java.io.File;

public interface FilesService {
    boolean saveToFile(String json);

    String readFromFiles();

    boolean cleanDataFile();

    //Файловый сервис для рецепта
    boolean cleanRecipeDataFile();

    String readFromRecipeFiles();

    boolean saveToRecipeFile(String json);

    File getRecipeDataFile();

    File getIngredientDataFile();
}
