package com.prueba.todospringswagger.controller;

import com.prueba.todospringswagger.persistence.entity.Task;
import com.prueba.todospringswagger.persistence.entity.TaskStatus;
import com.prueba.todospringswagger.service.TaskService;
import com.prueba.todospringswagger.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador encargado de controlar las peticiones a la API
 * RestController: anotación que indica que será un controlador
 * RequestMapping: la ruta de acceso, debe ser, por buenas prácticas, plural de la entidad que gestiona
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    /**
     * El servicio usado para interactuar las entidades.
     */
    private final TaskService taskService;

    /**
     * Crea el controlador e inyecta el servicio
     *
     * @param taskService el servicio de tareas a usar
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * Recibe una tareaDTO del usuario y almacena la tarea correspondiente
     * Anotación PostMapping permite indicar que con este método se van a crear elementos
     * Anotación RequestBody sirve para definir o relacionar el tipo de entidad que se espera recibir en el cuerpo
     *
     * @param taskInDTO la tarea DTO del usuario
     * @return la tarea almacenada
     */
    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO) {
        return this.taskService.createTask(taskInDTO);
    }

    /**
     * Retorna la lista de tareas
     * La anotación GetMapping permite indicar que este método responderá a peticiones GET que se hagan a la apia
     *
     * @return la lista de tareas
     */
    @GetMapping
    public List<Task> findAll() {
        return this.taskService.findAll();
    }

    /**
     * Retorna la lista de tareas según status
     * En la anotación GetMapping se le pasa la ruta status y el parámetro status que se espera recibir del usuario
     * Con la anotación PathVariable se indica que se leerá una variable que está en la url, primero se indica
     * entre comillas el nombre que viene en la ruta, para este caso "status", y la variable con el tipo de dato correspondiente
     * @return la lista de tareas
     */
    @GetMapping("/status/{status}")
    public List<Task> findAllByStatus(@PathVariable("status") TaskStatus status) {
        return this.taskService.findAllByStatus(status);
    }

    /**
     * Actualiza el estado de una tarea según su id
     * La anotación PatchMapping sirve para indicar que será un método para actualizar (asociado a PATCH)
     * @param id la id de la tarea
     * @return una respuesta 204 (solicitud exitosa sin contenido)
     */
    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsFinished(id);
        // Retorna respuesta vacia con código 204: Se procesó con éxito la solicitud, pero no devolvera ningún contenido
        return ResponseEntity.noContent().build();
    }

    /**
     * Elimina una tarea según la id
     * La anotación DeleteMapping sirve para indicar que será un método asociado a petición Delete
     * @param id la id de la tarea a eliminar
     * @return una respuesta 204 (solicitud exitosa sin contenido)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable("id") Long id){
        this.taskService.deleteById(id);
        // Retorna respuesta vacia con código 204: Se procesó con éxito la solicitud, pero no devolvera ningún contenido
        return ResponseEntity.noContent().build();
    }
}
