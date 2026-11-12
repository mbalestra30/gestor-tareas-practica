# Ejercicios: gestor de tareas en consola

Vamos a construir el programa de a poco. Cada ejercicio parte del anterior.

| Ejercicio | Dónde |
|---|---|
| 1. El menú | En la charla, juntos |
| 2. La clase Tarea | En la charla, juntos |
| 3. Separar en capas | En la charla, juntos |
| 4. Java moderno | **De tarea** |
| 5. Guardar en un archivo | **De tarea** |

Antes de empezar, seguí los pasos del [README](README.md): fork, clonar tu fork y crear la rama `charla1-ejercicios`.

**Cómo seguir esta guía:**
- Hacé los pasos **en orden**. Cada uno dice en qué archivo trabajar, dónde escribir y qué código poner.
- Escribí el código vos (no lo copies y pegues): así se aprende.
- Después de los pasos que dicen **▶ Probalo**, corré el programa (▶ verde al lado de `main` en `Main.java`)
  y fijate que pase lo que dice.
- Al terminar cada ejercicio, hacé un commit en tu rama, y cuando quieras, push a tu fork.
- **¿Te trabaste?** En el repositorio original, la rama `charla1-solucion` tiene un commit por ejercicio
  con la solución (ver "¿Te trabaste?" en el README).

**Atajos útiles de IntelliJ:**
- Si algo queda en rojo porque falta un `import`, poné el cursor encima y apretá **Alt+Enter → Import class**.
- **Ctrl+Alt+L** acomoda la indentación del archivo.

---

## Ejercicio 1: el menú

### Qué queremos lograr

Hoy el programa muestra el menú **una sola vez**, lee un número, dice "Elegiste la opción X" y termina.
El menú está dibujado, pero no hace nada.

Queremos que sea un menú de verdad:
- que **se repita** hasta que el usuario elija `0`;
- que la opción `1` **agregue** una tarea (por ahora, solo un título) y la `2` las **liste**;
- que el código quede **ordenado en métodos**, uno por cada cosa que hace el programa;
- que **valide** lo que escribe el usuario (título vacío, lista llena).

Así se va a ver al terminar:

```
=== Gestor de tareas ===
1. Agregar tarea
2. Listar tareas
0. Salir
Elegí una opción: 1
Título: Estudiar Java
Tarea agregada

=== Gestor de tareas ===
...
Elegí una opción: 2
1. Estudiar Java
Elegí una opción: 0
¡Hasta luego!
```

**Qué vas a usar:** do-while · switch · arreglos · métodos · sobrecarga

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

### Qué queremos lograr

Hasta ahora una tarea es solo un texto. Pero una tarea tiene más datos: un **número que la identifica**
(id) y si **ya está hecha** o no. Para eso vamos a crear nuestra propia clase, `Tarea`.

Además, el arreglo tiene dos problemas: entran solo 10 tareas, y para encontrar una hay que recorrerlas
todas. Lo vamos a cambiar por un `HashMap`, que crece solo y encuentra una tarea por su id al instante.

Con eso sumamos tres opciones al menú: **completar**, **eliminar** y **buscar**.

Así se va a ver al terminar:

```
Elegí una opción: 1
Título: Estudiar Java
Tarea agregada con id 1
Elegí una opción: 3
Id de la tarea: 1
Tarea completada
Elegí una opción: 2
[x] 1 - Estudiar Java
[ ] 2 - Practicar Git
Elegí una opción: 5
Texto a buscar: git
[ ] 2 - Practicar Git
```

**Qué vas a usar:** clases · encapsulamiento · constructores · `toString` · `HashMap` · `ArrayList` · for-each

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

### Qué queremos lograr

El programa ya funciona, pero `Main` hace **todo**: muestra el menú, tiene las reglas (por ejemplo, "el título
no puede estar vacío") y además guarda las tareas en el `HashMap`. Si mañana queremos guardar las tareas en
un archivo o en una base de datos, o cambiar el menú por una página web, hay que tocar todo `Main`.

Por eso los programas profesionales se separan en **capas**, cada una con una sola responsabilidad:

| Capa | Qué hace | Clase |
|---|---|---|
| Presentación | Muestra el menú y lee lo que escribe el usuario | `Main` |
| Servicio | Tiene las reglas del negocio | `TareaService` |
| Repositorio | Guarda y busca las tareas | `TareaRepository` (interfaz) y `TareaRepositoryEnMemoria` |

```
Main  ──usa──▶  TareaService  ──usa──▶  TareaRepository  ◀──implementa──  TareaRepositoryEnMemoria
```

La clave es la **interfaz** `TareaRepository`: el service usa la interfaz, no sabe si las tareas están en
memoria, en un archivo o en una base de datos. Así se puede cambiar dónde se guardan sin tocar nada más
(lo vas a hacer en el ejercicio 5).

**Al terminar, el programa funciona exactamente igual que en el ejercicio 2**: el cambio está en cómo
está organizado el código, no en lo que hace.

> Este ejercicio es la base de las próximas charlas: en la charla 2, Spring va a usar esta misma interfaz,
> y en la charla 3, Spring Data la va a implementar solo.

**Qué vas a usar:** interfaces · abstracción · polimorfismo · herencia · excepciones · paquetes

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

## Ejercicio 4 (tarea): Java moderno

### Qué queremos lograr

Desde Java 10 se sumaron herramientas para escribir **menos código y más claro**. En este ejercicio las
aplicamos al gestor:

| Herramienta | Para qué sirve | Dónde la usamos |
|---|---|---|
| `record` | Una clase de datos en una línea | `Tarea` |
| switch con flechas | Un switch sin `break` | El menú |
| Streams | Filtrar una lista sin escribir el `for` | Buscar y ver pendientes |
| `var` | No repetir el tipo cuando es obvio | Variables locales |
| Text block | Un texto de varias líneas | El menú |
| Pattern matching | Chequear el tipo y convertirlo en un paso | Mostrar errores |

**Al terminar**, el programa hace lo mismo que antes y suma una opción nueva, **6. Ver pendientes**:

```
Elegí una opción: 6
[ ] 2 - Practicar Git
[ ] 3 - Preparar la charla
```

Si un número no existe, el mensaje de error es más claro:

```
Elegí una opción: 3
Id de la tarea: 99
No existe la tarea 99. Revisá la lista con la opción 2.
```

**Qué vas a usar:** `record` · switch con flechas · streams · `var` · text blocks · pattern matching

> Este ejercicio queda **de tarea**. Hacelo con calma: cada paso explica qué hace cada línea.
> Corré el programa cada vez que dice **▶ Probalo**: si algo falla, sabés que fue en el último paso.

### Paso 1: Tarea pasa a ser un record

**Por qué:** la clase `Tarea` tiene 40 líneas, y casi todas son "de relleno": el constructor que copia los
parámetros a los atributos y un getter por atributo. Un **record** hace todo eso solo.

Un record:
- se declara con los datos entre paréntesis: `record Tarea(Long id, String titulo, boolean hecha)`;
- genera solo el constructor, un getter por dato, `equals`, `hashCode` y `toString`;
- es **inmutable**: una vez creado no se puede cambiar. Para "cambiar" una tarea, se crea otra.

Abrí `model/Tarea.java`, **borrá todo** y escribí:

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

Qué hace cada parte:
- **`public Tarea(Long id, String titulo)`**: un constructor extra, para crear tareas nuevas sin decir si
  están hechas. `this(id, titulo, false)` llama al constructor que genera el record, con `hecha` en `false`.
- **`completada()`** reemplaza a `completar()`. Como el record no se puede modificar, no "marca" la tarea:
  **devuelve una tarea nueva** igual, pero con `hecha` en `true`.
- **`conId(...)`** devuelve una copia con otro id. La va a usar el repositorio para asignarle el id a una tarea nueva.
- **`toString()`**: el record ya trae uno, pero muestra `Tarea[id=1, titulo=...]`. Lo sobrescribimos para que se
  siga viendo como antes.

### Paso 2: arreglar los errores que marca IntelliJ

Ahora aparecen errores en rojo en otros archivos. Es normal: en un record, los getters **no** llevan `get`
ni `is`. Se llaman igual que el dato:

| Antes (clase) | Ahora (record) |
|---|---|
| `tarea.getId()` | `tarea.id()` |
| `tarea.getTitulo()` | `tarea.titulo()` |
| `tarea.isHecha()` | `tarea.hecha()` |

> El compilador te guía: andá archivo por archivo arreglando cada rojo. Para ver todos los errores juntos:
> **Build → Build Project** (Ctrl+F9).

**2.1** En `repository/TareaRepositoryEnMemoria.java`, el método `save` queda así:

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

Antes creábamos la tarea con `new Tarea(proximoId, tarea.getTitulo())`. Ahora usamos `conId`, que copia
todos los datos y solo cambia el id.

En `findAll` del mismo archivo, cambiá `a.getId().compareTo(b.getId())` por `a.id().compareTo(b.id())`.

**2.2** En `service/TareaService.java`:
- en `buscar`, cambiá `tarea.getTitulo()` por `tarea.titulo()`;
- el método `completar` queda así:

```java
public Tarea completar(Long id) {
    Tarea tarea = buscarPorId(id);
    return repository.save(tarea.completada());
}
```

Antes hacíamos `tarea.completar()` (que cambiaba la tarea) y después `save`. Ahora `tarea.completada()`
devuelve **otra** tarea, hecha, y esa es la que guardamos. Como tiene el mismo id, `save` reemplaza la vieja.

**2.3** En `Main.java`, en `agregarTarea()`, cambiá `tarea.getId()` por `tarea.id()`.

**▶ Probalo:** agregá, completá y listá: todo funciona igual que antes.

### Paso 3: switch con flechas

**Por qué:** en el switch clásico, si te olvidás un `break`, el programa sigue de largo y ejecuta el `case`
de abajo. Es un error muy común. El switch con flechas (Java 14+) no tiene ese problema.

En `main` de `Main.java`, reemplazá el `switch` entero (desde `switch (opcion) {` hasta su `}`) por:

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

Cada `case` ejecuta **solo** lo que está a la derecha de la flecha. No hace falta `break`.

**▶ Probalo:** todas las opciones del menú funcionan igual.

### Paso 4: streams en el service

**Por qué:** muchas veces recorremos una lista con un `for` para quedarnos con algunos elementos. Un
**stream** hace eso en una sola expresión, que se lee casi como una frase.

**4.1** En `service/TareaService.java`, reemplazá el método `buscar` por:

```java
public List<Tarea> buscar(String texto) {
    var buscado = texto.toLowerCase();
    return repository.findAll().stream()
            .filter(tarea -> tarea.titulo().toLowerCase().contains(buscado))
            .toList();
}
```

Leelo así, línea por línea:
1. `repository.findAll().stream()`: "de todas las tareas…"
2. `.filter(tarea -> ...)`: "…quedate con las que cumplen esta condición…" (la condición es una lambda: dada
   una tarea, ¿su título contiene el texto?)
3. `.toList()`: "…y juntalas en una lista".

Es lo mismo que el `for` con el `if` de antes, sin crear la lista a mano.

**4.2** Debajo de `buscar`, agregá un método nuevo para las tareas pendientes:

```java
public List<Tarea> pendientes() {
    return repository.findAll().stream()
            .filter(tarea -> !tarea.hecha())
            .toList();
}
```

`!tarea.hecha()` significa "no está hecha". El `!` niega.

**4.3** Arriba de todo, el `import java.util.ArrayList;` quedó en gris porque ya no se usa: borralo.

### Paso 5: opción 6, ver pendientes

**5.1** En `mostrarMenu()` de `Main.java`, debajo de `"5. Buscar tareas"`, agregá:

```java
System.out.println("6. Ver pendientes");
```

**5.2** En el `switch`, debajo de `case 5 -> buscarTareas();`, agregá:

```java
case 6 -> mostrar(service.pendientes());
```

`mostrar(...)` es el método que ya teníamos para imprimir una lista de tareas.

**▶ Probalo:** agregá tres tareas, completá una y elegí la opción 6: aparecen solo las otras dos.

### Paso 6: var

**Por qué:** cuando el tipo es obvio por lo que está a la derecha del `=`, repetirlo a la izquierda no
suma nada. Con `var`, Java lo deduce solo. **Sigue siendo una variable con tipo**: no se puede guardar
otra cosa después.

Cambiá estas dos líneas:
- en `agregarTarea()` de `Main.java`: `Tarea tarea = service.crear(...)` → `var tarea = service.crear(...)`
- en `completar` de `TareaService.java`: `Tarea tarea = buscarPorId(id);` → `var tarea = buscarPorId(id);`

> `var` solo se usa para **variables locales** (adentro de un método). Los atributos de la clase y los
> parámetros llevan el tipo escrito.

### Paso 7: el menú en un text block

**Por qué:** el menú son 9 `println` seguidos. Un **text block** (Java 15+) es un texto de varias líneas
escrito tal cual, entre tres comillas `"""`.

**7.1** En `Main.java`, arriba de la línea del `scanner`, agregá:

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

- El texto empieza en la línea de abajo de las `"""`. La línea vacía del principio hace el mismo salto de
  línea que el `System.out.println();` de antes.
- Java le saca la sangría de la izquierda, así que se imprime pegado al borde.
- `static final` porque es una constante: es la misma para todo el programa y no cambia.

**7.2** En `main`, cambiá `mostrarMenu();` por:

```java
System.out.println(MENU);
```

**7.3** Borrá el método `mostrarMenu()` entero: ya no se usa (IntelliJ lo muestra en gris).

**▶ Probalo:** el menú se ve igual que antes.

### Paso 8: pattern matching al mostrar errores

**Por qué:** cuando atrapamos una excepción, a veces queremos hacer algo distinto según **qué tipo** de
error es. El **pattern matching** (Java 16+) chequea el tipo y, en el mismo paso, te da una variable de ese tipo.

**8.1** En `Main.java`, debajo del método `mostrar(...)`, agregá:

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

Qué hace:
- `e instanceof TareaNoEncontradaException noEncontrada` pregunta "¿`e` es una `TareaNoEncontradaException`?".
  Si es así, crea la variable `noEncontrada` ya con ese tipo, y podemos usar `noEncontrada.getId()`.
  Antes de Java 16 había que escribir aparte un casting: `TareaNoEncontradaException n = (TareaNoEncontradaException) e;`.
- Si es una `IllegalArgumentException` (el título vacío), muestra su mensaje.
- Si es cualquier otro error, lo vuelve a lanzar con `throw e`: un error que no esperábamos no se esconde.

**8.2** En `main`, cambiá el `catch`:

```java
} catch (IllegalArgumentException | TareaNoEncontradaException e) {
    System.out.println(e.getMessage());
}
```

por:

```java
} catch (RuntimeException e) {
    mostrarError(e);
}
```

Ahora atrapamos cualquier `RuntimeException` y `mostrarError` decide qué hacer según el tipo.

**▶ Probalo:** completá la tarea 99 (no existe): aparece "No existe la tarea 99. Revisá la lista con la opción 2."
Agregá una tarea con el título vacío: aparece "El título no puede estar vacío".

### Commit

```bash
git add .
git commit -m "Ejercicio 4: Java moderno"
```

---

## Ejercicio 5 (tarea): guardar en un archivo

### Qué queremos lograr

Hoy las tareas viven en un `HashMap`, en la memoria: **cuando cerrás el programa, se pierden**.
Queremos guardarlas en un archivo de texto, `tareas.csv`, para que sigan ahí la próxima vez.

Acá se ve la ventaja de haber separado en capas en el ejercicio 3: vamos a crear **otra implementación**
de la misma interfaz `TareaRepository`, que en vez de un `HashMap` usa un archivo. El service y el menú
**no se tocan**: en `Main` cambia **una sola línea**.

```
                          ┌─▶ TareaRepositoryEnMemoria   (HashMap: se pierde al cerrar)
TareaService ──usa──▶ TareaRepository
                          └─▶ TareaRepositoryArchivo     (tareas.csv: queda guardado)
```

El archivo va a tener una línea por tarea, con el formato `id;hecha;titulo`:

```
1;true;Estudiar Java
2;false;Practicar Git
```

**Al terminar:** agregás tareas, cerrás el programa, lo volvés a abrir, listás… y siguen ahí.

**Qué vas a usar:** archivos · try-with-resources · manejo de errores · polimorfismo

> Este ejercicio queda **de tarea**. Es el más largo: la clase nueva se arma en partes, y cada parte explica qué hace.

### Paso 1: crear la clase y declarar que implementa la interfaz

En el paquete `repository`, creá la clase `TareaRepositoryArchivo` y escribí:

```java
package com.curso.tareas.repository;

import com.curso.tareas.model.Tarea;

import java.nio.file.Path;

public class TareaRepositoryArchivo implements TareaRepository {

    private final Path archivo;

    public TareaRepositoryArchivo(Path archivo) {
        this.archivo = archivo;
    }
}
```

- `Path` representa la ruta de un archivo. Se la pasamos en el constructor, así la misma clase sirve para
  cualquier archivo.
- La línea de la clase queda en rojo: dice que implementa `TareaRepository` pero todavía no tiene sus métodos.
  Lo arreglamos en el paso 4.

### Paso 2: leer todas las tareas del archivo

**Por qué:** todos los métodos del repositorio van a necesitar leer el archivo. Escribimos esa lógica
**una sola vez**, en un método privado.

Adentro de la clase, debajo del constructor, agregá:

```java
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
```

Paso a paso:
1. Si el archivo todavía no existe (la primera vez que corrés el programa), devuelve una lista vacía.
2. **`try (BufferedReader reader = ...)`** es un **try-with-resources**: abre el archivo para leerlo, y
   cuando termina el bloque **lo cierra solo**, aunque haya un error. Sin esto habría que acordarse de
   llamar a `reader.close()` en un `finally`.
3. `reader.readLine()` lee una línea; cuando no hay más, devuelve `null` y el `while` termina.
4. `linea.split(";", 3)` corta `"1;true;Estudiar Java"` en `["1", "true", "Estudiar Java"]`. El `3` dice
   "como máximo 3 partes", así un título con `;` adentro no se rompe.
5. `Long.parseLong` y `Boolean.parseBoolean` convierten el texto a número y a boolean.
6. Si falla la lectura (`IOException`), la envolvemos en `UncheckedIOException`, que es una `RuntimeException`
   (así no hay que declararla en cada método).
7. Al final ordenamos por id: `Comparator.comparing(Tarea::id)` es "comparar por id".
   `Tarea::id` es una **referencia a método**: lo mismo que la lambda `tarea -> tarea.id()`.

Van a aparecer varias cosas en rojo por los imports: andá una por una con **Alt+Enter → Import class**
(en el paso 5 está la lista completa).

### Paso 3: escribir todas las tareas en el archivo

Debajo de `leerTodas`, agregá:

```java
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
```

- Otra vez **try-with-resources**, ahora para escribir: el archivo se cierra solo al terminar.
- `newBufferedWriter` crea el archivo si no existe, y si existe lo **reemplaza** entero.
- Por cada tarea escribe una línea `id;hecha;titulo` y un salto de línea (`newLine()`).

> La estrategia es simple: cada vez que algo cambia, leemos todas las tareas, hacemos el cambio en la lista
> y volvemos a escribir el archivo completo. Para un programa chico alcanza; una base de datos (charla 3) lo hace mejor.

### Paso 4: los métodos de la interfaz

Poné el cursor en la línea `public class TareaRepositoryArchivo implements TareaRepository` y apretá
**Alt+Enter → Implement methods → OK**. IntelliJ agrega los 5 métodos vacíos debajo del constructor.
Completalos así:

```java
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
```

Qué hace cada uno:
- **`findAll`**: devuelve lo que hay en el archivo.
- **`findById`**: un stream que se queda con la tarea de ese id. `findFirst()` devuelve un `Optional`:
  la tarea, o vacío si no está. Los ids se comparan con `equals`, no con `==`, porque son objetos `Long`.
- **`save`** tiene dos casos:
  - **tarea nueva** (`id` en `null`): busca el id más alto del archivo y le suma 1
    (`mapToLong(Tarea::id).max().orElse(0) + 1`; si no hay tareas, el primero es 1);
  - **tarea que ya existe** (por ejemplo, al completarla): saca la versión vieja de la lista con `removeIf`.

  En los dos casos agrega la tarea a la lista y escribe el archivo.
- **`deleteById`**: saca la tarea de la lista y vuelve a escribir el archivo.
- **`existsById`**: usa `findById` y pregunta si encontró algo.

> `new ArrayList<>(leerTodas())` hace una **copia modificable** de la lista, porque vamos a agregar y sacar tareas.

### Paso 5: revisar los imports

Arriba de todo, los imports tienen que quedar así (si te falta alguno, **Alt+Enter** sobre lo que está en rojo):

```java
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
```

Si no queda nada en rojo, la clase está completa. Comparala con la del commit "Ejercicio 5" de `charla1-solucion`.

### Paso 6: cambiar una sola línea en Main

En `Main.java`, buscá la línea que crea el service:

```java
private static final TareaService service = new TareaService(new TareaRepositoryEnMemoria());
```

y cambiala por:

```java
private static final TareaService service =
        new TareaService(new TareaRepositoryArchivo(Path.of("tareas.csv")));
```

Arreglá los imports de `Main`: agregá `com.curso.tareas.repository.TareaRepositoryArchivo` y
`java.nio.file.Path`, y borrá el de `TareaRepositoryEnMemoria` (queda en gris).

**Esto es polimorfismo:** `TareaService` recibe un `TareaRepository`. No le importa si es el de memoria o el
de archivo: los dos cumplen el mismo contrato. Por eso el service y el menú no cambiaron ni una línea.

`Path.of("tareas.csv")` es un archivo en la carpeta del proyecto.

**▶ Probalo:**
1. Agregá dos tareas y completá una.
2. Salí con `0`.
3. Mirá el panel de la izquierda: apareció `tareas.csv`. Abrilo: tiene una línea por tarea.
4. Volvé a correr el programa y listá (opción 2): **las tareas siguen ahí**.

> `tareas.csv` está en el `.gitignore`, así que no se sube a GitHub: son datos de tu compu, no código.

### Commit

```bash
git add .
git commit -m "Ejercicio 5: guardar en archivo"
```

---

## Al final: subí tu trabajo a tu fork

```bash
git push -u origin charla1-ejercicios     # la primera vez; después alcanza con git push
```

O en IntelliJ: **Ctrl+Shift+K → Push**.

Entrá a tu fork en GitHub, elegí la rama `charla1-ejercicios` en el desplegable y fijate que estén tus commits.
Si querés que el tutor lo vea, pasale el link:
`https://github.com/<tu-usuario>/gestor-tareas-practica/tree/charla1-ejercicios`
