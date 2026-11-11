# Ejercicios: gestor de tareas en consola

Cada ejercicio parte del anterior y suma temas del curso de Java Profesional.
Seguí los pasos en orden y corré el programa después de cada uno.

Trabajá todo en tu rama (`charla1-` y tus iniciales). Al terminar cada ejercicio, hacé un commit; al final, push.

**¿Te trabaste?** La rama `charla1-solucion` tiene un commit por ejercicio con la solución.

---

## Ejercicio 1: el menú (≈12 min)

**Temas:** do-while · switch · if/else · arreglos · métodos

1. Repetí el menú con un `do-while` hasta que el usuario elija `0`, y resolvé cada opción con un
   `switch` (con `break` y `default` para opciones inválidas).
2. Guardá los títulos en un arreglo `String[] tareas = new String[10]` con un contador `cantidad`.
   Opción 1 agrega un título (con `leerTexto`); opción 2 los lista con un `for`.
3. Pasá cada opción a un método propio: `mostrarMenu()`, `agregarTarea()`, `listarTareas()`.
   Para eso, el arreglo y el contador pasan a ser atributos `static` de `Main`.
4. Validá: título vacío → "El título no puede estar vacío"; arreglo lleno → "No entran más tareas".
5. Sobrecarga: agregá `agregarTarea(String titulo)`, que recibe el título ya leído,
   y hacé que `agregarTarea()` lo lea y llame a la otra.

**Probalo:** agregá dos tareas, listalas, probá un título vacío y una opción inexistente.

---

## Ejercicio 2: la clase Tarea y las colecciones (≈15 min)

**Temas:** clases · encapsulamiento · constructores · `toString` · `HashMap` · `ArrayList` · for-each

1. Creá la clase `Tarea` en el paquete `com.curso.tareas.model` con atributos **privados**
   `Long id`, `String titulo` y `boolean hecha`, un constructor `Tarea(Long id, String titulo)`,
   getters, un método `completar()` y `toString()` que devuelva `[ ] 1 - Estudiar Java` (o `[x]` si está hecha).
2. Cambiá el arreglo por un `Map<Long, Tarea> tareas = new HashMap<>()` y un contador `proximoId`.
3. Opción 3: **completar** una tarea por id (`tareas.get(id)`). Opción 4: **eliminar** por id
   (`tareas.remove(id)`). Si el id no existe: "No existe la tarea N".
4. Opción 5: **buscar** por texto: recorré con for-each y juntá las que coinciden en un `ArrayList`.
5. Listá ordenado por id: pasá los valores a una lista y ordenala con una lambda
   (`lista.sort((a, b) -> a.getId().compareTo(b.getId()))`).

**Probalo:** agregá tres tareas, completá una, eliminá otra, buscá por una palabra y listá.

---

## Ejercicio 3: separar en capas con una interfaz (≈13 min)

**Temas:** interfaces · abstracción · polimorfismo · herencia · organización en paquetes

1. Creá en `com.curso.tareas.repository` la interfaz `TareaRepository`:
   ```java
   List<Tarea> findAll();
   Optional<Tarea> findById(Long id);
   Tarea save(Tarea tarea);          // si no tiene id, le asigna uno
   void deleteById(Long id);
   boolean existsById(Long id);
   ```
2. Implementala en `TareaRepositoryEnMemoria` (mové ahí el `HashMap` y el `proximoId`).
3. Creá `TareaService` en `com.curso.tareas.service`: recibe un `TareaRepository` por constructor
   y tiene `crear`, `listar`, `buscarPorId`, `buscar`, `completar` y `eliminar`.
   El título vacío ahora es una regla del service: lanza `IllegalArgumentException`.
4. Creá `TareaNoEncontradaException extends RuntimeException` con un `getId()`: la lanza el service.
5. `Main` solo muestra el menú, llama al service y atrapa los errores con try/catch.

**Probalo:** todo tiene que funcionar igual que en el ejercicio 2.

---

## Ejercicio 4: Java moderno (≈12 min)

**Temas:** `record` · `var` · streams · switch con flechas · text blocks · pattern matching

1. Convertí `Tarea` en un `record Tarea(Long id, String titulo, boolean hecha)`.
   Como es inmutable, `completar()` pasa a ser `completada()`, que **devuelve una copia** hecha.
   IntelliJ te va a marcar los getters: `getTitulo()` pasa a ser `titulo()`.
2. Cambiá el `switch` del menú a la forma con flechas (`case 1 -> agregarTarea();`).
3. Reescribí `buscar` del service con un stream (`filter` + `toList()`) y agregá la
   opción 6: **ver pendientes**, también con un stream. Usá `var` donde el tipo sea obvio.
4. Pasá el menú a un text block (`"""`).
5. Al mostrar errores, usá pattern matching: `if (e instanceof TareaNoEncontradaException noEncontrada)`.

**Probalo:** agregá, completá y usá la opción 6.

---

## Ejercicio 5 (si te da el tiempo, o de tarea): guardar en un archivo

**Temas:** try-with-resources · manejo de errores · polimorfismo

1. Creá `TareaRepositoryArchivo implements TareaRepository` que guarde cada tarea en `tareas.csv`
   con el formato `id;hecha;titulo`.
2. Usá `try (BufferedReader reader = Files.newBufferedReader(...))` y lo mismo para escribir:
   los archivos se cierran solos.
3. En `Main`, cambiá **una sola línea** para usar el archivo en vez de la memoria.

**Probalo:** agregá tareas, cerrá el programa, abrilo de nuevo: tienen que seguir ahí.
