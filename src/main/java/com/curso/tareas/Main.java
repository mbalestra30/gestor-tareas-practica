package com.curso.tareas;

import com.curso.tareas.model.Tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Gestor de tareas en consola: punto de partida de la charla 1.
 * Seguí EJERCICIOS.md: cada ejercicio le agrega una parte a este programa.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final Map<Long, Tarea> tareas = new HashMap<>();
    private static long proximoId = 1;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elegí una opción: ");

            switch (opcion) {
                case 1:
                    agregarTarea();
                    break;
                case 2:
                    listarTareas();
                    break;
                case 3:
                    completarTarea();
                    break;
                case 4:
                    eliminarTarea();
                    break;
                case 5:
                    buscarTareas();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== Gestor de tareas ===");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Completar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Buscar tareas");
        System.out.println("0. Salir");
    }

    private static void agregarTarea() {
        String titulo = leerTexto("Título: ");
        agregarTarea(titulo);
    }

    private static void agregarTarea(String titulo) {
        if (titulo.isEmpty()) {
            System.out.println("El título no puede estar vacío");
            return;
        }
        Tarea tarea = new Tarea(proximoId, titulo);
        tareas.put(tarea.getId(), tarea);
        proximoId++;
        System.out.println("Tarea agregada con id " + tarea.getId());
    }

    private static void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas");
        }
        List<Tarea> lista = new ArrayList<>(tareas.values());
        lista.sort((a, b) -> a.getId().compareTo(b.getId()));
        for (Tarea tarea : lista) {
            System.out.println(tarea);
        }
    }

    private static void completarTarea() {
        long id = leerEntero("Id de la tarea: ");
        Tarea tarea = tareas.get(id);
        if (tarea == null) {
            System.out.println("No existe la tarea " + id);
        } else {
            tarea.completar();
            System.out.println("Tarea completada");
        }
    }

    private static void eliminarTarea() {
        long id = leerEntero("Id de la tarea: ");
        if (tareas.remove(id) == null) {
            System.out.println("No existe la tarea " + id);
        } else {
            System.out.println("Tarea eliminada");
        }
    }

    private static void buscarTareas() {
        String texto = leerTexto("Texto a buscar: ").toLowerCase();
        List<Tarea> encontradas = new ArrayList<>();
        for (Tarea tarea : tareas.values()) {
            if (tarea.getTitulo().toLowerCase().contains(texto)) {
                encontradas.add(tarea);
            }
        }
        if (encontradas.isEmpty()) {
            System.out.println("No se encontraron tareas");
        }
        for (Tarea tarea : encontradas) {
            System.out.println(tarea);
        }
    }

    // ------------------------------------------------------------------
    // Métodos de ayuda para leer de la consola: ya están listos, usalos.
    // ------------------------------------------------------------------

    /** Lee un número; si el usuario escribe otra cosa, lo vuelve a pedir. */
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Ingresá un número: ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // descarta el Enter que deja nextInt()
        return valor;
    }

    /** Lee una línea de texto, sin espacios al principio ni al final. */
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}
