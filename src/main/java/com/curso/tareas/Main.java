package com.curso.tareas;

import java.util.Scanner;

/**
 * Gestor de tareas en consola: punto de partida de la charla 1.
 * Seguí EJERCICIOS.md: cada ejercicio le agrega una parte a este programa.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Gestor de tareas ===");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("0. Salir");

        // EJERCICIO 1: repetir el menú con un do-while y resolver cada opción con un switch
        int opcion = leerEntero("Elegí una opción: ");
        System.out.println("Elegiste la opción " + opcion);
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
