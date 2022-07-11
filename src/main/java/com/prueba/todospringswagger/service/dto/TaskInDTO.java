package com.prueba.todospringswagger.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Clase DTO (Patrón que permite recibir o enviar la información relevante de una o más entidades
 * por medio de una sola clase)
 * En particular esta clase representa la información de la tarea que el usuario enviará
 */
@Data
public class TaskInDTO {

    /**
     * El titulo de la tarea
     */
    private String title;

    /**
     * La descripción de la tarea
     */
    private String description;

    /**
     * La fecha de finalización estimada de la tarea (siglas en inglés)
     */
    private LocalDateTime eta; // tiempo estimado de finalización (siglas en inglés)

}
