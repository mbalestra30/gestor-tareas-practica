package com.curso.tareas.repository;

import com.curso.tareas.model.Tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TareaRepositoryEnMemoria implements TareaRepository {

    private final Map<Long, Tarea> tareas = new HashMap<>();
    private long proximoId = 1;

    @Override
    public List<Tarea> findAll() {
        List<Tarea> lista = new ArrayList<>(tareas.values());
        lista.sort((a, b) -> a.getId().compareTo(b.getId()));
        return lista;
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return Optional.ofNullable(tareas.get(id));
    }

    @Override
    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea = new Tarea(proximoId, tarea.getTitulo());
            proximoId++;
        }
        tareas.put(tarea.getId(), tarea);
        return tarea;
    }

    @Override
    public void deleteById(Long id) {
        tareas.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return tareas.containsKey(id);
    }
}
