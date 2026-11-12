package com.curso.tareas.repository;

import com.curso.tareas.model.Tarea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TareaRepositoryArchivo implements TareaRepository {

    private final Path archivo;

    public TareaRepositoryArchivo(Path archivo) {
        this.archivo = archivo;
    }

    @Override
    public List<Tarea> findAll() {
        return leerTodas();
    }

    @Override
    public Optional<Tarea> findById(Long id) {
        return leerTodas().stream()
                .filter(tarea -> tarea.id().equals(id))
                .findFirst();
    }

    @Override
    public Tarea save(Tarea tarea) {
        var tareas = new ArrayList<>(leerTodas());
        if (tarea.id() == null) {
            long proximoId = tareas.stream().mapToLong(Tarea::id).max().orElse(0) + 1;
            tarea = tarea.conId(proximoId);
        } else {
            Long id = tarea.id();
            tareas.removeIf(existente -> existente.id().equals(id));
        }
        tareas.add(tarea);
        escribirTodas(tareas);
        return tarea;
    }

    @Override
    public void deleteById(Long id) {
        var tareas = new ArrayList<>(leerTodas());
        tareas.removeIf(tarea -> tarea.id().equals(id));
        escribirTodas(tareas);
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    private List<Tarea> leerTodas() {
        if (!Files.exists(archivo)) {
            return List.of();
        }
        var tareas = new ArrayList<Tarea>();
        try (BufferedReader reader = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.isBlank()) {
                    String[] partes = linea.split(";", 3);
                    tareas.add(new Tarea(Long.parseLong(partes[0]), partes[2], Boolean.parseBoolean(partes[1])));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer " + archivo, e);
        }
        tareas.sort(Comparator.comparing(Tarea::id));
        return tareas;
    }

    private void escribirTodas(List<Tarea> tareas) {
        try (BufferedWriter writer = Files.newBufferedWriter(archivo, StandardCharsets.UTF_8)) {
            for (Tarea tarea : tareas) {
                writer.write(tarea.id() + ";" + tarea.hecha() + ";" + tarea.titulo());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo escribir " + archivo, e);
        }
    }
}
