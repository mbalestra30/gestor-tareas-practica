package com.curso.tareas.service;

public class TareaNoEncontradaException extends RuntimeException {

    private final Long id;

    public TareaNoEncontradaException(Long id) {
        super("No existe la tarea " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
