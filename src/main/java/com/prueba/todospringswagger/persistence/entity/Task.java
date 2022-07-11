package com.prueba.todospringswagger.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Clase que representa la entidad task
 * Con la anotación entity se indica que está clase es una entidad de la base de datos
 * y con table, el nombre que tendrá la entidad como tabla
 * Con la anotación data de lombok sé generara de forma automatica e implicita los getters y setters
 */
@Data
@Entity
@Table(name = "task")
public class Task {

    /**
     * Id de la tarea. Se especifica con la anotación ID que será el identificador a usar, también
     * se especifica con GeneratedValue que será un valor autoincrementable
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * El titulo de la tarea
     */
    private String title;

    /**
     * La descripción de la tarea
     */
    private String description;

    /**
     * La fecha de creación de la tarea
     */
    private LocalDateTime createdData;

    /**
     * La fecha de finalización estimada de la tarea (siglas en inglés)
     */
    private LocalDateTime eta; // tiempo estimado de finalización (siglas en inglés)

    /**
     * Identifica si la tarea ha sido finalizada o no
     */
    private boolean finished;

    /**
     * El estado de la tarea, el cual puede ser: ON_TIME o LATE
     */
    private TaskStatus taskStatus;
}
