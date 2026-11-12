package com.curso.tareas;

import com.curso.tareas.model.Tarea;
import com.curso.tareas.repository.TareaRepositoryEnMemoria;
import com.curso.tareas.service.TareaNoEncontradaException;
import com.curso.tareas.service.TareaService;

import java.util.List;
import java.util.Scanner;

/**
 * Gestor de tareas en consola: punto de partida de la charla 1.
 * Seguí EJERCICIOS.md: cada ejercicio le agrega una parte a este programa.
 */
public class Main {

    private static final String MENU = """

            === Gestor de tareas ===
            1. Agregar tarea
            2. Listar tareas
            3. Completar tarea
            4. Eliminar tarea
            5. Buscar tareas
            6. Ver pendientes
            0. Salir""";

    private static final Scanner scanner = new Scanner(System.in);

    private static final TareaService service = new TareaService(new TareaRepositoryEnMemoria());

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println(MENU);
            opcion = leerEntero("Elegí una opción: ");
            try {
                switch (opcion) {
                    case 1 -> agregarTarea();
                    case 2 -> listarTareas();
                    case 3 -> completarTarea();
                    case 4 -> eliminarTarea();
                    case 5 -> buscarTareas();
                    case 6 -> mostrar(service.pendientes());
                    case 0 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida");
                }
            } catch (RuntimeException e) {
                mostrarError(e);
            }
        } while (opcion != 0);
    }

    private static void agregarTarea() {
        var tarea = service.crear(leerTexto("Título: "));
        System.out.println("Tarea agregada con id " + tarea.id());
    }

    private static void listarTareas() {
        mostrar(service.listar());
    }

    private static void completarTarea() {
        service.completar(leerId());
        System.out.println("Tarea completada");
    }

    private static void eliminarTarea() {
        service.eliminar(leerId());
        System.out.println("Tarea eliminada");
    }

    private static void buscarTareas() {
        mostrar(service.buscar(leerTexto("Texto a buscar: ")));
    }

    private static void mostrar(List<Tarea> tareas) {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas");
        }
        tareas.forEach(tarea -> System.out.println(tarea));
    }

    private static void mostrarError(RuntimeException e) {
        if (e instanceof TareaNoEncontradaException noEncontrada) {
            System.out.println("No existe la tarea " + noEncontrada.getId() + ". Revisá la lista con la opción 2.");
        } else if (e instanceof IllegalArgumentException) {
            System.out.println(e.getMessage());
        } else {
            throw e;
        }
    }

    private static Long leerId() {
        return (long) leerEntero("Id de la tarea: ");
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
