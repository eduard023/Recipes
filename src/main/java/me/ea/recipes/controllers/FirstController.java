package me.ea.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FirstController {

    @GetMapping
    public String play(){
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String getInfo(){
        return "Авджян Эдуард <br>" +
                "Recipes <br>" +
                "11.12.2022 <br>" +
                "Проект где я разбираюсь с Spring и Maven "
                ;
    }
}
