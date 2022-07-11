package com.prueba.todospringswagger.service;

import com.prueba.todospringswagger.exceptions.ToDoException;
import com.prueba.todospringswagger.mapper.TaskInDTOToTask;
import com.prueba.todospringswagger.persistence.entity.Task;
import com.prueba.todospringswagger.persistence.entity.TaskStatus;
import com.prueba.todospringswagger.persistence.repository.TaskRepository;
import com.prueba.todospringswagger.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Clase que se encarga de la lógica de negocio de la aplicación.
 * La anotación Service indica que es un servicio que será interpretado como un bean para
 * que pueda ser inyectado donde se requiera.
 */
@Service
public class TaskService {

    /**
     * El repositorio usado a través de inyección de dependencias =Se inyecta un objeto de una clase en otra).
     * Se puede usar autowired para esto, aunque se recomienda por buena práctica hacerlo por constructor
     */
    private final TaskRepository repository;

    /**
     * El mapper usado para transformar de taskdto a task
     */
    private final TaskInDTOToTask mapper;

    /**
     * Constructor que inyecta el repositorio
     *
     * @param repository el repositorio a usar
     * @param mapper el mapper a usar
     */
    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Mapea una TareaDto en una tarea para almacenarla en la base de datos y retórnala
     * @param taskInDto la tarea DTO obtenida del usuario
     * @return la tarea creada
     */
    public Task createTask(TaskInDTO taskInDto){
        Task task = mapper.map(taskInDto);
        return this.repository.save(task);
    }

    /**
     * Retorna la lista de tareas
     * Esta funcionalidad ya viene implementada en JPA repository, interfaz que está siendo usado por el TaskRepository
     * @return la lista de tareas
     */
    public List<Task> findAll(){
        return this.repository.findAll();
    }

    /**
     * Retorna la lista de tareas que tienen el estado indicado por parámetro
     * @param status el estado por el que se desea buscar
     * @return la lista de tareas según estado
     */
    public List<Task> findAllByStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    /**
     * Marca una tarea como finalizada
     * La anotación transaccional permite que se pueda realizar una operación de query nativo
     * pues indica que o se realiza toda la operación o nada.
     * Lanza una excepción si no encuentra la tarea con el id que llega por parámetro
     * @param id el id de la tarea
     */
    @Transactional
    public void updateTaskAsFinished(Long id){

        // Se busca la tarea que tenga la id indicada
        // findById retorna un opcional, es decir, la tarea o vacio
        Optional<Task> optionalTask = this.repository.findById(id);
        // Si no se encuentra una tarea con el id suministrado se lanza una excepción.
        // Con un mensaje y un código http adecuado
        if(optionalTask.isEmpty()){
            throw new ToDoException("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }

    /**
     * Método que elimina una tarea según su ID
     * @param id el id de la tarea a eliminar
     */
    public void deleteById(Long id){
        // Se busca la tarea que tenga la id indicada
        // findById retorna un opcional, es decir, la tarea o vacio
        Optional<Task> optionalTask = this.repository.findById(id);
        // Si no se encuentra una tarea con el id suministrado se lanza una excepción.
        // Con un mensaje y un código http adecuado
        if(optionalTask.isEmpty()){
            throw new ToDoException("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}

