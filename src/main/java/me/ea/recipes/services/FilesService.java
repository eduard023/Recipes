package me.ea.recipes.services;

public interface FilesService {
    boolean saveToFile(String json);

    String readFromFiles();

    String readFromRecipeFiles();

    boolean saveToRecipeFile(String json);
}
