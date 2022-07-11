package com.prueba.todospringswagger.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Clase encargada de gestionar las excepciones de la aplicación
 * Será una excepción no checkeada que extiende de RuntimeException
 */
@Data
public class ToDoException extends RuntimeException{

    /**
     * El mensaje de la excepción
     */
    private String message;

    /**
     * El código http asociado a la excepción
     */
    private HttpStatus httpStatus;

    /**
     * Constructor de la excepción
     * @param message El mensaje de la excepción
     * @param httpStatus El código http de la excepción
     */
    public ToDoException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
