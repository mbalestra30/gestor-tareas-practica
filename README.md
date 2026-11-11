# Gestor de tareas: práctica de la ruta de Java

Un gestor de tareas en consola que vamos construyendo charla a charla, aplicando lo que vieron
en los cursos. Hoy, **charla 1**: fundamentos de Java, POO, colecciones, Java moderno y Git.

## Qué necesitás

- JDK 17 o superior
- IntelliJ IDEA (trae Maven incluido)
- Git y una cuenta de GitHub

## Cómo empezar

1. Cloná el repositorio. Quedás parado en la rama `charla1`, el punto de partida:
   ```bash
   git clone https://github.com/mbalestra30/gestor-tareas-practica.git
   cd gestor-tareas-practica
   ```
2. Creá **tu rama**: `charla1-` y tus iniciales (por ejemplo, Juan Pérez → `charla1-jp`):
   ```bash
   git switch -c charla1-jp
   ```
3. Abrí la carpeta en IntelliJ (**File → Open**) y esperá a que cargue el `pom.xml`.
4. Abrí `src/main/java/com/curso/tareas/Main.java` y apretá el ▶ verde al lado de `main`.
   Tiene que aparecer el menú.
5. Seguí [EJERCICIOS.md](EJERCICIOS.md).

## Cómo trabajamos

Todos los ejercicios se hacen en **tu rama**. Al terminar cada uno, un commit; al final, push:

```bash
git add .
git commit -m "Ejercicio 1: menú con do-while y switch"
# ... ejercicio 2, commit; ejercicio 3, commit; ...

git push -u origin charla1-jp     # la primera vez; después alcanza con git push
```

## Ramas del repositorio

| Rama | Qué tiene |
|---|---|
| `charla1` | El punto de partida: de acá sale tu rama |
| `charla1-solucion` | La solución: **un commit por ejercicio** |
| `charla1-<iniciales>` | La rama de cada uno, con sus ejercicios |

**¿Te trabaste?** En GitHub, elegí la rama `charla1-solucion` y entrá a **Commits**. Cada commit
resuelve un ejercicio: el mensaje lista los pasos y el diff muestra en verde el código que se agrega.
Primero intentalo vos; mirá la solución para destrabarte.
