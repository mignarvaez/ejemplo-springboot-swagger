package com.prueba.todospringswagger.mapper;

import com.prueba.todospringswagger.persistence.entity.Task;
import com.prueba.todospringswagger.persistence.entity.TaskStatus;
import com.prueba.todospringswagger.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Implementación del mapper encargado de transformar de TaskDto a task
 * Para poder inyectarlo después en la aplicación se lo declara como un componente de spring con la anotación componente
 */
@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task>{

    @Override
    public Task map(TaskInDTO in) {

        Task task = new Task();
        // Atributos obtenidos del DTO
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());

        // Atributos establecidos por defecto
        task.setCreatedData(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);

        return task;
    }
}
