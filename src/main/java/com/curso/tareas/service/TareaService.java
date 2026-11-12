package com.curso.tareas.service;

import com.curso.tareas.model.Tarea;
import com.curso.tareas.repository.TareaRepository;

import java.util.List;

public class TareaService {

    private final TareaRepository repository;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public Tarea crear(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        return repository.save(new Tarea(null, titulo.trim()));
    }

    public List<Tarea> listar() {
        return repository.findAll();
    }

    public Tarea buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TareaNoEncontradaException(id));
    }

    public List<Tarea> buscar(String texto) {
        var buscado = texto.toLowerCase();
        return repository.findAll().stream()
                .filter(tarea -> tarea.titulo().toLowerCase().contains(buscado))
                .toList();
    }

    public List<Tarea> pendientes() {
        return repository.findAll().stream()
                .filter(tarea -> !tarea.hecha())
                .toList();
    }

    public Tarea completar(Long id) {
        var tarea = buscarPorId(id);
        return repository.save(tarea.completada());
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new TareaNoEncontradaException(id);
        }
        repository.deleteById(id);
    }
}
