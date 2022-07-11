package com.prueba.todospringswagger.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Clase que maneja todas las excepciones de la aplicación
 * La anotación controllerAdvice permite indicar que será está la clase encargada de gestionar las excepciones de toda
 * la aplicación.
 * Hereda de la clase responseEntityExceptionHandler la cual implementa metodos para gestionar excepciones
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Método encargado de gestionar las excepciones de la aplicación
     * La anotación ExceptionHandler sirve para indicar que este método se encargara de las excepciones
     * @param ex La excepción que se gestionará
     * @param request La petición asociada
     * @return una respuesta de gestión adecuada de la excepción
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value={ToDoException.class})
    protected ResponseEntity<Object> handleConflict(ToDoException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getHttpStatus(), request);
    }

}
