package com.curso.tareas.service;

import com.curso.tareas.model.Tarea;
import com.curso.tareas.repository.TareaRepository;

import java.util.ArrayList;
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
        String buscado = texto.toLowerCase();
        List<Tarea> encontradas = new ArrayList<>();
        for (Tarea tarea : repository.findAll()) {
            if (tarea.getTitulo().toLowerCase().contains(buscado)) {
                encontradas.add(tarea);
            }
        }
        return encontradas;
    }

    public Tarea completar(Long id) {
        Tarea tarea = buscarPorId(id);
        tarea.completar();
        return repository.save(tarea);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new TareaNoEncontradaException(id);
        }
        repository.deleteById(id);
    }
}
