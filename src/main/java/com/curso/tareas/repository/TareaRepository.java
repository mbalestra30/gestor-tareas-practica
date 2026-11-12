package com.curso.tareas.repository;

import com.curso.tareas.model.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaRepository {

    List<Tarea> findAll();

    Optional<Tarea> findById(Long id);

    Tarea save(Tarea tarea);

    void deleteById(Long id);

    boolean existsById(Long id);
}
