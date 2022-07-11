package com.prueba.todospringswagger.mapper;

/**
 * Interfaz que define un mapper, un mapper es una herramienta que convierte un objeto en otro objeto
 * Para este caso se usara para convertir un TaskDto en un Task y viceversa
 * Recibe un dato de entrada y otro de salida, la entrada lo mapea a la salida.
 */
public interface IMapper<I, O> {

    /**
     * Mapea lo que entre por I a O
     * @param in
     * @return
     */
    public O map(I in);
}
