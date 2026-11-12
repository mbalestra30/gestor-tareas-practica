package com.curso.tareas.model;

public record Tarea(Long id, String titulo, boolean hecha) {

    public Tarea(Long id, String titulo) {
        this(id, titulo, false);
    }

    public Tarea completada() {
        return new Tarea(id, titulo, true);
    }

    public Tarea conId(Long nuevoId) {
        return new Tarea(nuevoId, titulo, hecha);
    }

    @Override
    public String toString() {
        return (hecha ? "[x] " : "[ ] ") + id + " - " + titulo;
    }
}
