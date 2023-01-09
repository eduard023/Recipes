package me.ea.recipes.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "не верно указан id")

public class IdException extends RuntimeException{

}
