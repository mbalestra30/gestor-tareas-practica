# Gestor de tareas: práctica de la ruta de Java

¡Bienvenido! Este es el proyecto que vamos a construir charla a charla, aplicando lo que vieron en los cursos:
un gestor de tareas en consola. Hoy, **charla 1**: fundamentos de Java, POO, colecciones y Git.

## Qué necesitás

- JDK 17 o superior
- IntelliJ IDEA (trae Maven incluido)
- Una cuenta de GitHub (si no tenés, creala gratis en [github.com/signup](https://github.com/signup))

## Cómo empezar

### 1. Hacé un fork

Un **fork** es una copia de este repositorio en **tu** cuenta de GitHub. Ahí vas a subir tus ejercicios.

1. Arriba a la derecha de esta página, apretá **Fork**.
2. Dejá todo como está y apretá **Create fork**.
3. Ahora estás en `github.com/<tu-usuario>/gestor-tareas-practica`: ese repo es tuyo.

### 2. Cloná tu fork en IntelliJ

1. En **tu** fork, apretá el botón verde **Code** y copiá la URL (termina en `.git`).
2. En IntelliJ: **File → New → Project from Version Control** (o **Clone Repository** en la pantalla de bienvenida).
3. Pegá la URL, elegí una carpeta y apretá **Clone**. Si pregunta "Trust project?", apretá **Trust Project**.
4. Esperá a que IntelliJ termine de cargar (la barra de abajo deja de moverse).

### 3. Creá tu rama

Arriba, al lado del nombre del proyecto, dice **charla1 ⌄**. Hacé clic → **New Branch...** → escribí
`charla1-ejercicios` → **Create**. O en la terminal de IntelliJ (**Alt+F12**):

```bash
git switch -c charla1-ejercicios
```

### 4. Corré el programa

Abrí `src/main/java/com/curso/tareas/Main.java` y apretá el ▶ verde al lado de `main`.
Abajo tiene que aparecer el menú.

### 5. Hacé los ejercicios

Seguí [EJERCICIOS.md](EJERCICIOS.md), paso a paso. Al terminar cada ejercicio, hacé un commit:

```bash
git add .
git commit -m "Ejercicio 1: menú con do-while y switch"
```

### 6. Subí tu trabajo a tu fork

```bash
git push -u origin charla1-ejercicios     # la primera vez; después alcanza con git push
```

En IntelliJ también podés hacerlo con **Ctrl+Shift+K → Push**.

Si querés que el tutor vea tu trabajo, pasale el link de tu fork:
`https://github.com/<tu-usuario>/gestor-tareas-practica/tree/charla1-ejercicios`

## ¿Te trabaste?

La solución está en **este** repositorio (el original), en la rama `charla1-solucion`:

1. Arriba a la izquierda, abrí el desplegable de ramas (dice `charla1`) y elegí `charla1-solucion`.
2. Entrá a **Commits**: hay un commit por ejercicio.
3. Hacé clic en el del ejercicio: el mensaje lista los pasos y en verde está el código que se agrega.

Primero intentalo vos; mirá la solución para destrabarte.

## Ramas de este repositorio

| Rama | Qué tiene |
|---|---|
| `charla1` | El punto de partida |
| `charla1-solucion` | La solución: un commit por ejercicio |
