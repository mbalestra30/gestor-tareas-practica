package com.curso.tareas.model;

public class Tarea {

    private final Long id;
    private final String titulo;
    private boolean hecha;

    public Tarea(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.hecha = false;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isHecha() {
        return hecha;
    }

    public void completar() {
        hecha = true;
    }

    @Override
    public String toString() {
        return (hecha ? "[x] " : "[ ] ") + id + " - " + titulo;
    }
}
