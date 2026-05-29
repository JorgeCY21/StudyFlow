# StudyFlow

StudyFlow es una aplicación Android desarrollada con Kotlin y Jetpack Compose para la gestión académica de estudiantes. El proyecto fue diseñado como una práctica de **“SOLID en Compose: Refactorización de UI”**, priorizando una arquitectura clara, modular y fácil de mantener.

La app permite organizar:

- hábitos académicos
- tareas
- cursos
- progreso semanal
- resumen general del estudiante

## Objetivo del proyecto

El propósito principal de StudyFlow no es solo mostrar información académica, sino también demostrar buenas prácticas de arquitectura y separación de responsabilidades en una app moderna con Compose.

## Funcionalidades principales

- Pantalla de inicio con resumen del estudiante, tareas pendientes, hábitos completados y progreso semanal.
- Pantalla de hábitos con listado, creación y marcado como completado.
- Pantalla de tareas con listado, creación, selección de curso asociado y marcado como completado.
- Pantalla de cursos con listado de cursos creados por el usuario.
- Pantalla de progreso con estadísticas generales de avance académico.
- Navegación inferior entre las secciones principales.

## Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- Material 3
- ViewModel
- Navigation Compose
- Coroutines y StateFlow
- Arquitectura MVVM
- Repositorios mediante interfaces

## Arquitectura

El proyecto está organizado por capas para facilitar el mantenimiento y la escalabilidad:

- `data`: implementaciones concretas de repositorios y fuentes de datos locales en memoria.
- `domain`: modelos, contratos de repositorio y casos de uso.
- `presentation`: pantallas, ViewModels y componentes reutilizables de UI.
- `navigation`: navegación entre pantallas.
- `ui/theme`: colores, tipografía y tema global.

### Estructura general

```text
app/src/main/java/com/example/studyflow/
├── data/
├── domain/
├── presentation/
├── navigation/
├── ui/theme/
├── di/
├── MainActivity.kt
└── StudyFlowApp.kt
```

## Aplicación de SOLID

### SRP - Single Responsibility Principle

Cada pieza del sistema cumple una función específica:

- `HabitCard` solo representa un hábito.
- `TaskCard` solo representa una tarea.
- `CourseCard` solo representa un curso.
- `HabitScreen`, `TaskScreen` y `CourseScreen` solo conectan estado y eventos.
- Los ViewModels manejan estado y acciones, no la UI visual.

### OCP - Open/Closed Principle

La UI está construida con componentes reutilizables y extensibles:

- tarjetas reutilizables
- listas separadas
- componentes como `AppStatCard`, `SectionTitle` y `EmptyState`

Esto permite agregar nuevas variantes sin reescribir la lógica central.

### LSP - Liskov Substitution Principle

Los componentes mantienen contratos consistentes y pueden ser reemplazados por variantes similares sin romper la pantalla. Por ejemplo:

- una tarjeta puede representar un elemento simple o una versión más detallada
- la lista solo depende del contrato visual esperado

### ISP - Interface Segregation Principle

Las interfaces del dominio están separadas por responsabilidad:

- repositorio de hábitos
- repositorio de tareas
- repositorio de cursos
- repositorio de estudiante

No se obliga a una clase a depender de funciones que no necesita.

### DIP - Dependency Inversion Principle

Los ViewModels dependen de abstracciones y casos de uso, no de implementaciones concretas. Las implementaciones de datos viven en `data`, mientras que el dominio define los contratos.

## Datos simulados

La aplicación utiliza datos locales en memoria para simular el comportamiento del sistema sin depender todavía de una base de datos o backend real.

Datos iniciales incluidos:

- Hábitos:
  - Estudiar Kotlin
  - Leer documentación
  - Repasar inglés
  - Practicar algoritmos
- Tareas:
  - Laboratorio SOLID en Compose
  - Informe de arquitectura
  - Exposición de proyecto
- Cursos:
  - Desarrollo Avanzado en Nuevas Plataformas
  - Ingeniería de Software
  - Base de Datos

## Flujo de uso

1. Abrir la aplicación.
2. Revisar el resumen general en `Home`.
3. Crear o completar hábitos.
4. Crear tareas asociándolas a un curso existente.
5. Consultar los cursos registrados.
6. Ver estadísticas de avance en `Progreso`.

## Requisitos

- Android Studio actualizado
- Kotlin
- JDK compatible con el proyecto
- Emulador o dispositivo Android con soporte para Compose

## Compilación y ejecución

### Desde Android Studio

1. Abrir el proyecto `StudyFlow`.
2. Esperar la sincronización de Gradle.
3. Ejecutar la app en un emulador o dispositivo físico.

### Desde línea de comandos

```bash
./gradlew assembleDebug
```

## Archivos recomendados para revisar primero

- `app/src/main/java/com/example/studyflow/MainActivity.kt`
- `app/src/main/java/com/example/studyflow/StudyFlowApp.kt`
- `app/src/main/java/com/example/studyflow/navigation/AppNavigation.kt`
- `app/src/main/java/com/example/studyflow/di/StudyFlowContainer.kt`
- `app/src/main/java/com/example/studyflow/presentation/home/HomeScreen.kt`
- `app/src/main/java/com/example/studyflow/presentation/tasks/TaskScreen.kt`
- `app/src/main/java/com/example/studyflow/presentation/habits/HabitScreen.kt`

## Estado del proyecto

El proyecto está estructurado para una demostración académica clara, con enfoque en:

- separación de responsabilidades
- reutilización de componentes
- navegación simple
- estado controlado por ViewModel
- lógica de negocio fuera de los Composables

## Licencia

Proyecto académico para uso educativo.
