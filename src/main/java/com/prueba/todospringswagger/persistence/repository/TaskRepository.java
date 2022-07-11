package com.prueba.todospringswagger.persistence.repository;

import com.prueba.todospringswagger.persistence.entity.Task;
import com.prueba.todospringswagger.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/***
 * Interfaz que define el repositorio que permitirá interactuar con la base de datos
 * Extiende de JPaRepository que tiene varias funcionalidades incorporadas y recibe la entidad con la que trabajara y el tipo de dato de su ID
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Se implementa usando la funcionalidad de spring data jpa una consulta para obtener
     * todas las tareas según su estatus
     * @param status el estatus por el que se hará la consulta
     * @return la lista de tareas
     */
    public List<Task>findAllByTaskStatus(TaskStatus status);

    /**
     * Método usado para marcar una tarea como finalizada
     * Se indica con la anotación Query que se realizará una Query nativa (código sql), al indicarle en value la query
     * y en nativeQuery = true
     * La anotación Param sirve para indicar que se pasara un parámetro, en este caso la ID, que se pasa
     * al query con :id
     * La anotación modifying índica que es una query de actualización
     * @param id el id de la tarea
     */
    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED=true WHERE ID=:id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id")Long id);
}
