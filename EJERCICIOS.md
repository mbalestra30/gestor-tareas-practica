# Ejercicios: gestor de tareas en consola

Vamos a construir el programa de a poco. Cada ejercicio parte del anterior.

**Cómo seguir esta guía:**
- Hacé los pasos **en orden**. Cada uno dice en qué archivo trabajar, dónde escribir y qué código poner.
- Escribí el código vos (no lo copies y pegues): así se aprende.
- Después de los pasos que dicen **▶ Probalo**, corré el programa (▶ verde al lado de `main` en `Main.java`)
  y fijate que pase lo que dice.
- Al terminar cada ejercicio, hacé un commit en tu rama.
- **¿Te trabaste?** En la rama `charla1-solucion` hay un commit por ejercicio con la solución.

**Atajos útiles de IntelliJ:**
- Si algo queda en rojo porque falta un `import`, poné el cursor encima y apretá **Alt+Enter → Import class**.
- **Ctrl+Alt+L** acomoda la indentación del archivo.

---

## Ejercicio 1: el menú

**Qué vas a aprender:** do-while · switch · arreglos · métodos · sobrecarga

**Cómo arrancás:** el programa muestra el menú una vez, lee un número y termina.
**Cómo terminás:** el menú se repite, podés agregar tareas y listarlas, y sale con 0.

### Paso 1: repetir el menú con un do-while

En `Main.java`, adentro de `main`, **borrá todo lo que hay** (los `println` del menú, la línea del comentario
`// EJERCICIO 1` y las dos líneas de abajo) y escribí esto:

```java
int opcion;
do {
    System.out.println();
    System.out.println("=== Gestor de tareas ===");
    System.out.println("1. Agregar tarea");
    System.out.println("2. Listar tareas");
    System.out.println("0. Salir");
    opcion = leerEntero("Elegí una opción: ");

} while (opcion != 0);
```

El `do-while` ejecuta el bloque **al menos una vez** y lo repite mientras la condición sea verdadera.
`opcion` se declara afuera del `do` para poder usarla en el `while`.

**▶ Probalo:** el menú se repite cada vez que elegís una opción, y el programa termina cuando ponés `0`.

### Paso 2: un arreglo para guardar las tareas

Arriba de `int opcion;` (la primera línea de `main`), agregá:

```java
String[] tareas = new String[10];
int cantidad = 0;
```

`tareas` es un arreglo de 10 textos: entran como máximo 10 tareas. `cantidad` cuenta cuántas cargamos.

### Paso 3: resolver cada opción con un switch

Debajo de `opcion = leerEntero("Elegí una opción: ");` (dentro del `do`), agregá:

```java
switch (opcion) {
    case 1:
        tareas[cantidad] = leerTexto("Título: ");
        cantidad++;
        break;
    case 2:
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + tareas[i]);
        }
        break;
    case 0:
        System.out.println("¡Hasta luego!");
        break;
    default:
        System.out.println("Opción inválida");
}
```

- `case 1` guarda el título en la posición `cantidad` y suma 1.
- `case 2` recorre las posiciones cargadas con un `for`. Mostramos `i + 1` para que la lista empiece en 1.
- `break` corta el switch. Sin `break`, sigue de largo al `case` de abajo.
- `default` atrapa cualquier otro número.

**▶ Probalo:** agregá dos tareas (opción 1), listalas (opción 2), probá una opción que no existe (`7`) y salí (`0`).

### Paso 4: pasar cada parte a un método

Vamos a ordenar el código: cada parte del menú va a su propio método. Para que todos los métodos vean
el arreglo y el contador, tienen que estar **en la clase**, no adentro de `main`.

**4.1** Borrá las dos líneas `String[] tareas...` e `int cantidad...` de adentro de `main`.
Agregalas **arriba de `main`**, debajo de la línea del `scanner`, así:

```java
private static final String[] tareas = new String[10];
private static int cantidad = 0;
```

**4.2** Debajo del cierre de `main` (después de su `}`), agregá estos tres métodos:

```java
private static void mostrarMenu() {
    System.out.println();
    System.out.println("=== Gestor de tareas ===");
    System.out.println("1. Agregar tarea");
    System.out.println("2. Listar tareas");
    System.out.println("0. Salir");
}

private static void agregarTarea() {
    String titulo = leerTexto("Título: ");
    tareas[cantidad] = titulo;
    cantidad++;
    System.out.println("Tarea agregada");
}

private static void listarTareas() {
    for (int i = 0; i < cantidad; i++) {
        System.out.println((i + 1) + ". " + tareas[i]);
    }
}
```

**4.3** Ahora `main` usa esos métodos. Dejalo así:

```java
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
            case 0:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida");
        }
    } while (opcion != 0);
}
```

**▶ Probalo:** tiene que funcionar igual que en el paso 3.

### Paso 5: validar

**5.1** En `agregarTarea()`, reemplazá las tres líneas que guardan la tarea por un `if`:

```java
private static void agregarTarea() {
    String titulo = leerTexto("Título: ");
    if (cantidad == tareas.length) {
        System.out.println("No entran más tareas");
    } else if (titulo.isEmpty()) {
        System.out.println("El título no puede estar vacío");
    } else {
        tareas[cantidad] = titulo;
        cantidad++;
        System.out.println("Tarea agregada");
    }
}
```

`tareas.length` es el tamaño del arreglo (10). Si `cantidad` llegó a 10, no entran más.

**5.2** En `listarTareas()`, agregá al principio un aviso para cuando no hay tareas:

```java
private static void listarTareas() {
    if (cantidad == 0) {
        System.out.println("No hay tareas");
    }
    for (int i = 0; i < cantidad; i++) {
        System.out.println((i + 1) + ". " + tareas[i]);
    }
}
```

**▶ Probalo:** listá sin tareas ("No hay tareas") y agregá una tarea con el título vacío (apretá Enter sin escribir).

### Paso 6: sobrecarga

La **sobrecarga** es tener dos métodos con el mismo nombre y distintos parámetros. Vamos a separar
"leer el título" de "guardar la tarea".

Reemplazá el método `agregarTarea()` por estos **dos** métodos:

```java
private static void agregarTarea() {
    String titulo = leerTexto("Título: ");
    agregarTarea(titulo);
}

private static void agregarTarea(String titulo) {
    if (cantidad == tareas.length) {
        System.out.println("No entran más tareas");
    } else if (titulo.isEmpty()) {
        System.out.println("El título no puede estar vacío");
    } else {
        tareas[cantidad] = titulo;
        cantidad++;
        System.out.println("Tarea agregada");
    }
}
```

Java sabe a cuál llamar por los parámetros: `agregarTarea()` sin nada, o `agregarTarea(titulo)` con un texto.

**▶ Probalo:** todo funciona igual que antes.

### Commit

```bash
git add .
git commit -m "Ejercicio 1: menú con do-while y switch"
```

---

## Ejercicio 2: la clase Tarea y las colecciones

**Qué vas a aprender:** clases · encapsulamiento · constructores · `toString` · `HashMap` · `ArrayList` · for-each

**Cómo terminás:** cada tarea tiene un id y se puede marcar como hecha; hay opciones para completar,
eliminar y buscar.

### Paso 1: crear la clase Tarea

**1.1** En el panel de la izquierda, clic derecho en el paquete `com.curso.tareas` → **New → Package** →
escribí `model` → Enter. Queda `com.curso.tareas.model`.

**1.2** Clic derecho en `model` → **New → Java Class** → escribí `Tarea` → Enter.

**1.3** Escribí la clase completa:

```java
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
```

- Los atributos son `private`: desde afuera solo se usan los métodos. Eso es **encapsulamiento**.
- La única forma de marcar una tarea como hecha es `completar()`.
- `toString()` define cómo se ve la tarea al imprimirla: `[ ] 1 - Estudiar Java`, o `[x]` si está hecha.

### Paso 2: cambiar el arreglo por un HashMap

En `Main.java`, reemplazá las dos líneas del arreglo y el contador:

```java
private static final String[] tareas = new String[10];
private static int cantidad = 0;
```

por estas:

```java
private static final Map<Long, Tarea> tareas = new HashMap<>();
private static long proximoId = 1;
```

Un `HashMap` guarda pares **clave → valor**: la clave es el id y el valor, la tarea. Así se busca una tarea
por id sin recorrer todas. `proximoId` es el id que va a tener la próxima tarea.

Arriba de todo, debajo de `package`, agregá los imports (o usá **Alt+Enter**):

```java
import com.curso.tareas.model.Tarea;

import java.util.HashMap;
import java.util.Map;
```

IntelliJ te va a marcar errores en `agregarTarea` y `listarTareas`: los arreglamos ahora.

### Paso 3: agregar y listar con el HashMap

**3.1** Reemplazá el método `agregarTarea(String titulo)` por:

```java
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
```

Ya no hace falta validar "No entran más tareas": el `HashMap` crece solo.
`return` corta el método ahí mismo.

**3.2** Reemplazá el método `listarTareas()` por:

```java
private static void listarTareas() {
    if (tareas.isEmpty()) {
        System.out.println("No hay tareas");
    }
    for (Tarea tarea : tareas.values()) {
        System.out.println(tarea);
    }
}
```

Esto es un **for-each**: "para cada tarea de los valores del mapa". `println(tarea)` usa el `toString()` de `Tarea`.

**▶ Probalo:** agregá dos tareas y listalas. Se tienen que ver así: `[ ] 1 - Estudiar Java`.

### Paso 4: opción 3, completar una tarea

**4.1** En `mostrarMenu()`, debajo de `"2. Listar tareas"`, agregá:

```java
System.out.println("3. Completar tarea");
```

**4.2** En el `switch` de `main`, debajo del `case 2` (después de su `break;`), agregá:

```java
case 3:
    completarTarea();
    break;
```

**4.3** Debajo de `listarTareas()`, agregá el método:

```java
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
```

`tareas.get(id)` busca por clave. Si no existe, devuelve `null`.

**▶ Probalo:** agregá una tarea, completala (opción 3, id 1) y listá: tiene que aparecer `[x]`.
Probá también un id que no existe.

### Paso 5: opción 4, eliminar una tarea

**5.1** En `mostrarMenu()`, debajo de `"3. Completar tarea"`, agregá:

```java
System.out.println("4. Eliminar tarea");
```

**5.2** En el `switch`, debajo del `case 3`, agregá:

```java
case 4:
    eliminarTarea();
    break;
```

**5.3** Debajo de `completarTarea()`, agregá:

```java
private static void eliminarTarea() {
    long id = leerEntero("Id de la tarea: ");
    if (tareas.remove(id) == null) {
        System.out.println("No existe la tarea " + id);
    } else {
        System.out.println("Tarea eliminada");
    }
}
```

`remove` borra y devuelve la tarea borrada, o `null` si no existía.

### Paso 6: opción 5, buscar por texto

**6.1** En `mostrarMenu()`, debajo de `"4. Eliminar tarea"`, agregá:

```java
System.out.println("5. Buscar tareas");
```

**6.2** En el `switch`, debajo del `case 4`, agregá:

```java
case 5:
    buscarTareas();
    break;
```

**6.3** Debajo de `eliminarTarea()`, agregá:

```java
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
```

Recorremos todas las tareas y juntamos en un `ArrayList` las que contienen el texto.
`toLowerCase()` pasa todo a minúsculas para que "java" encuentre "Java".

Agregá los imports que faltan: `java.util.ArrayList` y `java.util.List` (o **Alt+Enter**).

**▶ Probalo:** agregá tres tareas, completá una, eliminá otra y buscá por una palabra.

### Paso 7: listar ordenado por id

El `HashMap` no garantiza ningún orden. Para listar ordenado, pasamos las tareas a una lista y la ordenamos.

Reemplazá el método `listarTareas()` por:

```java
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
```

`(a, b) -> a.getId().compareTo(b.getId())` es una **lambda**: le dice a `sort` cómo comparar dos tareas (por id).

**▶ Probalo:** agregá varias tareas y listá: tienen que salir ordenadas por id.

### Commit

```bash
git add .
git commit -m "Ejercicio 2: clase Tarea, HashMap y ArrayList"
```

---

## Ejercicio 3: separar en capas con una interfaz

**Qué vas a aprender:** interfaces · abstracción · polimorfismo · herencia · excepciones · paquetes

**Cómo terminás:** el programa funciona igual que en el ejercicio 2, pero ordenado en tres partes:
- `TareaRepository`: **dónde** se guardan las tareas.
- `TareaService`: **las reglas** (por ejemplo, que el título no puede estar vacío).
- `Main`: solo muestra el menú y lee lo que escribe el usuario.

### Paso 1: la interfaz TareaRepository

**1.1** Creá el paquete `com.curso.tareas.repository` (clic derecho en `com.curso.tareas` → **New → Package**).

**1.2** Clic derecho en `repository` → **New → Java Class** → elegí **Interface** → escribí `TareaRepository`.

**1.3** Escribí:

```java
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
```

Una **interfaz** dice **qué** se puede hacer, sin decir **cómo**. Es un contrato.
`Optional<Tarea>` significa "puede haber una tarea o ninguna", sin usar `null`.
Los nombres en inglés son los mismos que usa Spring Data: en las próximas charlas esta interfaz se reutiliza.

### Paso 2: la implementación en memoria

**2.1** En `repository`, creá la clase `TareaRepositoryEnMemoria`.

**2.2** Escribí:

```java
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
```

- `implements TareaRepository`: esta clase **cumple el contrato**. Tiene que tener todos sus métodos.
  Truco: escribí solo la primera línea de la clase y apretá **Alt+Enter → Implement methods**:
  IntelliJ te genera todos los métodos vacíos.
- El `HashMap` y el `proximoId` se mudan acá desde `Main`.
- `save`: si la tarea no tiene id (`null`), es nueva: le asigna uno.

### Paso 3: la excepción TareaNoEncontradaException

**3.1** Creá el paquete `com.curso.tareas.service`.

**3.2** En `service`, creá la clase `TareaNoEncontradaException`:

```java
package com.curso.tareas.service;

public class TareaNoEncontradaException extends RuntimeException {

    private final Long id;

    public TareaNoEncontradaException(Long id) {
        super("No existe la tarea " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
```

`extends RuntimeException` es **herencia**: nuestra excepción hereda todo lo de una excepción
(el mensaje, por ejemplo) y le suma el id. `super(...)` llama al constructor de la clase padre.

### Paso 4: el service con las reglas

En `service`, creá la clase `TareaService`:

```java
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
```

- El service recibe un `TareaRepository` (la **interfaz**) en el constructor: no sabe ni le importa
  dónde se guardan las tareas.
- Cuando algo está mal, **lanza una excepción** (`throw`) en vez de imprimir un mensaje.
- `orElseThrow`: si el `Optional` está vacío, lanza la excepción.

### Paso 5: Main usa el service

Reemplazá **todo el contenido** de `Main.java` por esto (los métodos `leerEntero` y `leerTexto` del final
quedan igual):

```java
package com.curso.tareas;

import com.curso.tareas.model.Tarea;
import com.curso.tareas.repository.TareaRepositoryEnMemoria;
import com.curso.tareas.service.TareaNoEncontradaException;
import com.curso.tareas.service.TareaService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final TareaService service = new TareaService(new TareaRepositoryEnMemoria());

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elegí una opción: ");
            try {
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
            } catch (IllegalArgumentException | TareaNoEncontradaException e) {
                System.out.println(e.getMessage());
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
        Tarea tarea = service.crear(leerTexto("Título: "));
        System.out.println("Tarea agregada con id " + tarea.getId());
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

    private static Long leerId() {
        return (long) leerEntero("Id de la tarea: ");
    }

    // acá abajo siguen leerEntero y leerTexto, sin cambios
}
```

- La línea `new TareaService(new TareaRepositoryEnMemoria())` arma todo: el service usa el repositorio en memoria.
- `Main` ya no tiene el `HashMap` ni las reglas: solo llama al service.
- El `try/catch` atrapa los errores que lanza el service y muestra su mensaje.
- `(long) leerEntero(...)` es un **casting** de `int` a `long`.

**▶ Probalo:** todo tiene que funcionar igual que en el ejercicio 2: agregar, listar, completar, eliminar,
buscar, título vacío y un id que no existe.

### Commit

```bash
git add .
git commit -m "Ejercicio 3: capas con una interfaz"
```

---

## Ejercicio 4: Java moderno

**Qué vas a aprender:** `record` · switch con flechas · streams · `var` · text blocks · pattern matching

**Cómo terminás:** el programa funciona igual, con menos código, y suma la opción 6 "Ver pendientes".

### Paso 1: Tarea pasa a ser un record

Un **record** es una clase de datos en una línea: Java genera solo el constructor, los getters,
`equals` y `hashCode`. Es **inmutable**: no se puede modificar, así que para "cambiar" una tarea se crea otra.

Reemplazá **todo** `Tarea.java` por:

```java
package com.curso.tareas.model;

public record Tarea(Long id, String titulo, boolean hecha) {

    public Tarea(Long id, String titulo) {
        this(id, titulo, false);
    }

    public Tarea completada() {
        return new Tarea(id, titulo, true);
    }

    public Tarea conId(Long nuevoId) {
        return new Tarea(nuevoId, titulo, hecha);
    }

    @Override
    public String toString() {
        return (hecha ? "[x] " : "[ ] ") + id + " - " + titulo;
    }
}
```

- `completada()` reemplaza a `completar()`: en vez de cambiar la tarea, **devuelve una copia** hecha.
- `conId(...)` devuelve una copia con otro id (la vamos a usar en el repositorio).

### Paso 2: arreglar los errores que marca IntelliJ

Ahora hay errores en rojo: en un record los getters **no** llevan `get`. El compilador te guía:

| Antes | Ahora |
|---|---|
| `getId()` | `id()` |
| `getTitulo()` | `titulo()` |
| `isHecha()` | `hecha()` |

**2.1** En `TareaRepositoryEnMemoria`, el método `save` queda:

```java
@Override
public Tarea save(Tarea tarea) {
    if (tarea.id() == null) {
        tarea = tarea.conId(proximoId);
        proximoId++;
    }
    tareas.put(tarea.id(), tarea);
    return tarea;
}
```

y en `findAll`, cambiá `a.getId().compareTo(b.getId())` por `a.id().compareTo(b.id())`.

**2.2** En `TareaService`:
- en `buscar`, cambiá `tarea.getTitulo()` por `tarea.titulo()`.
- el método `completar` queda:

```java
public Tarea completar(Long id) {
    Tarea tarea = buscarPorId(id);
    return repository.save(tarea.completada());
}
```

**2.3** En `Main`, en `agregarTarea()`, cambiá `tarea.getId()` por `tarea.id()`.

**▶ Probalo:** todo funciona igual que antes.

### Paso 3: switch con flechas

En `main`, reemplazá el `switch` entero por esta forma nueva, que no necesita `break`:

```java
switch (opcion) {
    case 1 -> agregarTarea();
    case 2 -> listarTareas();
    case 3 -> completarTarea();
    case 4 -> eliminarTarea();
    case 5 -> buscarTareas();
    case 0 -> System.out.println("¡Hasta luego!");
    default -> System.out.println("Opción inválida");
}
```

Con flechas no se puede "caer" al caso siguiente: es la forma recomendada hoy.

### Paso 4: streams en el service

**4.1** En `TareaService`, reemplazá el método `buscar` por:

```java
public List<Tarea> buscar(String texto) {
    var buscado = texto.toLowerCase();
    return repository.findAll().stream()
            .filter(tarea -> tarea.titulo().toLowerCase().contains(buscado))
            .toList();
}
```

Un **stream** encadena pasos: de todas las tareas, `filter` deja pasar las que contienen el texto,
y `toList()` las junta en una lista. Hace lo mismo que el `for` con el `if`, en menos líneas.
`var` deja que Java deduzca el tipo (acá, `String`).

**4.2** Debajo de `buscar`, agregá:

```java
public List<Tarea> pendientes() {
    return repository.findAll().stream()
            .filter(tarea -> !tarea.hecha())
            .toList();
}
```

Si ya no se usa, borrá el `import java.util.ArrayList;` del service (IntelliJ lo muestra en gris).

### Paso 5: opción 6, ver pendientes

**5.1** En `mostrarMenu()`, debajo de `"5. Buscar tareas"`, agregá:

```java
System.out.println("6. Ver pendientes");
```

**5.2** En el `switch`, debajo del `case 5`, agregá:

```java
case 6 -> mostrar(service.pendientes());
```

**▶ Probalo:** agregá tres tareas, completá una y usá la opción 6: aparecen solo las otras dos.

### Paso 6: var

`var` sirve cuando el tipo es obvio por lo que está a la derecha del `=`. Cambiá:
- en `agregarTarea()` de `Main`: `Tarea tarea = ...` por `var tarea = ...`
- en `completar` del service: `Tarea tarea = buscarPorId(id);` por `var tarea = buscarPorId(id);`

### Paso 7: el menú en un text block

Un **text block** es un texto de varias líneas entre `"""`.

**7.1** En `Main`, arriba del `scanner`, agregá:

```java
private static final String MENU = """

        === Gestor de tareas ===
        1. Agregar tarea
        2. Listar tareas
        3. Completar tarea
        4. Eliminar tarea
        5. Buscar tareas
        6. Ver pendientes
        0. Salir""";
```

**7.2** En `main`, cambiá `mostrarMenu();` por:

```java
System.out.println(MENU);
```

**7.3** Borrá el método `mostrarMenu()`: ya no se usa.

### Paso 8: pattern matching al mostrar errores

**8.1** Debajo del método `mostrar(...)`, agregá:

```java
private static void mostrarError(RuntimeException e) {
    if (e instanceof TareaNoEncontradaException noEncontrada) {
        System.out.println("No existe la tarea " + noEncontrada.getId() + ". Revisá la lista con la opción 2.");
    } else if (e instanceof IllegalArgumentException) {
        System.out.println(e.getMessage());
    } else {
        throw e;
    }
}
```

`e instanceof TareaNoEncontradaException noEncontrada` chequea el tipo y, si coincide, ya te deja usar
`noEncontrada` con ese tipo, sin hacer un casting. Eso es **pattern matching**.

**8.2** En `main`, cambiá el `catch` por:

```java
} catch (RuntimeException e) {
    mostrarError(e);
}
```

**▶ Probalo:** completá un id que no existe: aparece "No existe la tarea 99. Revisá la lista con la opción 2."

### Commit

```bash
git add .
git commit -m "Ejercicio 4: Java moderno"
```

---

## Ejercicio 5 (si te da el tiempo, o de tarea): guardar en un archivo

**Qué vas a aprender:** try-with-resources · manejo de errores · polimorfismo

**Cómo terminás:** las tareas se guardan en un archivo `tareas.csv` y siguen ahí al cerrar el programa.

### Paso 1: el repositorio de archivo

En `repository`, creá la clase `TareaRepositoryArchivo`:

```java
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
```

- Cada tarea es una línea del archivo con el formato `id;hecha;titulo`. Por ejemplo: `1;false;Estudiar Java`.
- `try (BufferedReader reader = ...)` es **try-with-resources**: el archivo se cierra solo al salir del bloque,
  aunque haya un error.
- `split(";", 3)` corta la línea en 3 partes como máximo, así el título puede tener `;` adentro.

### Paso 2: cambiar una sola línea en Main

En `Main.java`, cambiá la línea del service:

```java
private static final TareaService service = new TareaService(new TareaRepositoryEnMemoria());
```

por:

```java
private static final TareaService service =
        new TareaService(new TareaRepositoryArchivo(Path.of("tareas.csv")));
```

y arreglá los imports: agregá `TareaRepositoryArchivo` y `java.nio.file.Path`, y borrá el de `TareaRepositoryEnMemoria`.

El service y el menú **no cambian**: reciben cualquier cosa que cumpla la interfaz `TareaRepository`.
Eso es **polimorfismo**.

**▶ Probalo:** agregá tareas, salí con `0`, volvé a correr el programa y listá: las tareas siguen ahí.
El archivo `tareas.csv` aparece en la carpeta del proyecto.

### Commit

```bash
git add .
git commit -m "Ejercicio 5: guardar en archivo"
```

---

## Al final: subí tu rama

```bash
git push -u origin charla1-jp     # con tus iniciales
```
