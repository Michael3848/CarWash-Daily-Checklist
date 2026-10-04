CarWash-Daily-Checklist

## 1. Descripción del Proyecto

CarWash Daily Checklist es una aplicación móvil para Android diseñada para ayudar a gestionar y organizar las tareas diarias necesarias para el correcto funcionamiento de un lava autos.

La aplicación permitirá crear, visualizar, editar y marcar tareas como completadas, facilitando el seguimiento de actividades rutinarias que son esenciales para la operación diaria del negocio. El proyecto nace de una necesidad real observada en mi lugar de trabajo, donde existen múltiples tareas operativas que deben realizarse diariamente para garantizar un servicio eficiente y de calidad.

El propósito de la aplicación es mejorar la organización, reducir olvidos y asegurar que todas las tareas importantes sean completadas oportunamente.

## 2. Exposición del Problema

En un lava autos se realizan numerosas tareas de rutina que son fundamentales para mantener la calidad del servicio y garantizar que los empleados dispongan de los recursos necesarios para realizar su trabajo correctamente.

Entre estas actividades se encuentran:

Verificar el inventario de productos de limpieza.
Organizar herramientas y equipos.
Revisar el funcionamiento de la hidrolavadora.
Limpiar las áreas de trabajo.
Supervisar la apertura y cierre del establecimiento.
Verificar el abastecimiento de insumos.

Actualmente, estas tareas suelen gestionarse de forma informal, dependiendo de la memoria o de recordatorios verbales. Esto puede ocasionar olvidos, retrasos o inconsistencias que afectan el flujo de trabajo y la eficiencia operativa.

La aplicación propuesta busca solucionar este problema mediante una herramienta sencilla que permita registrar y controlar las tareas diarias de manera organizada.

## 3. Plataforma

La aplicación será desarrollada para dispositivos Android utilizando las siguientes herramientas y tecnologías:

### Tecnologías

- Android Studio
- Kotlin
- Android SDK
- Git
- GitHub
- Figma

## 4. Interfaz de Usuario e Interfaz de Administrador
Interfaz de Usuario

La aplicación contará con una interfaz simple e intuitiva para facilitar su uso diario.

Pantallas principales:

Pantalla de inicio con lista de tareas.
Pantalla para agregar tareas.
Pantalla de detalles de la tarea.
Pantalla de tareas completadas.
Interfaz de Administrador

Debido a que la aplicación está diseñada para un entorno de trabajo pequeño, el administrador tendrá acceso a las mismas funciones de gestión.

Las funciones administrativas incluirán:

Crear tareas.
Editar tareas.
Eliminar tareas.
Marcar tareas como completadas.
Revisar tareas pendientes y finalizadas.

## 5. Funcionalidad

La aplicación incorporará las siguientes funciones:

Gestión de Tareas

-Crear nuevas tareas.
-Editar tareas existentes.
-Eliminar tareas.
-Consultar detalles de cada tarea.

Seguimiento de Actividades

-Marcar tareas como completadas.
-Visualizar tareas pendientes.
-Visualizar tareas finalizadas.

Organización

-Mostrar las tareas en una lista organizada.
-Facilitar el seguimiento de las actividades diarias del lava autos.
-Ayudar a garantizar que las tareas críticas no sean olvidadas.

Ejemplos de Tareas

-Revisar inventario de jabón y cera.
-Organizar herramientas de trabajo.
-Limpiar área de espera.
-Verificar funcionamiento de la hidrolavadora.
-Revisar suministros para la jornada laboral.
-Preparar el área de trabajo para la apertura.

## 6. Diseño (Wireframes o Esquemas de Página)

Los wireframes y prototipos de la aplicación serán diseñados utilizando Figma antes de comenzar el desarrollo en Android Studio.

Wireframe 1: Pantalla Principal

+----------------------------------+
|    CarWash Daily Checklist       |
+----------------------------------+
| Tareas Pendientes                |
|                                  |
| ☐ Revisar inventario             |
| ☐ Organizar herramientas         |
| ☐ Limpiar área de trabajo        |
| ☐ Revisar hidrolavadora          |
|                                  |
|       + Nueva Tarea              |
+----------------------------------+

Wireframe 2: Agregar Tarea

+----------------------------------+
|          Nueva Tarea             |
+----------------------------------+
| Título                           |
| [________________________]       |
|                                  |
| Descripción                      |
| [________________________]       |
|                                  |
|         Guardar                  |
+----------------------------------+

Wireframe 3: Detalles de la Tarea

+----------------------------------+
|       Detalle de Tarea           |
+----------------------------------+
| Revisar Inventario               |
|                                  |
| Verificar existencia de jabón,   |
| cera y demás productos.          |
|                                  |
| Estado: Pendiente                |
|                                  |
| [ Completar ]                    |
| [ Editar ]                       |
| [ Eliminar ]                     |
+----------------------------------+
## 7. Arquitectura y almacenamiento

La aplicación separará la interfaz de usuario de la gestión de los datos. En una fase posterior se utilizará Room como capa de acceso a una base de datos SQLite local. Cada tarea podrá contener información como identificador, título, descripción, estado, prioridad, categoría y fecha.

## 8. Actualización del proyecto

En esta etapa se creó la estructura inicial de CarWash Daily Checklist en Android Studio utilizando Kotlin. También se organizó el proyecto dentro de la carpeta correcta del repositorio, se configuraron los archivos de Gradle y se verificó que el proyecto compilara correctamente.

Además, se configuró el archivo `.gitignore` para evitar la publicación de archivos locales y configuraciones propias del entorno de desarrollo. Finalmente, se realizó un commit y un push del código inicial al repositorio remoto en GitHub.

La aplicación todavía se encuentra en desarrollo. Las funciones para crear, editar, completar y eliminar tareas, junto con el almacenamiento local, serán incorporadas en las próximas etapas.

Los cambios detallados se encuentran en el archivo [CHANGELOG.md](CHANGELOG.md).

## 9. Estado actual

- [x] Idea y problema empresarial definidos.
- [x] Borrador inicial del proyecto.
- [x] Wireframes preliminares.
- [x] Cuenta de GitHub creada.
- [x] GitHub Desktop configurado.
- [x] Android Studio instalado.
- [x] Repositorio clonado en la ubicación correcta.
- [x] Proyecto Android creado con Kotlin.
- [x] Sincronización de Gradle completada.
- [x] Compilación inicial verificada.
- [x] Código base publicado mediante commit y push.
- [x] Changelog actualizado.
- [ ] Pantalla principal de tareas implementada.
- [ ] Funciones para crear, editar, completar y eliminar tareas implementadas.
- [ ] Almacenamiento local con Room implementado.
- [ ] Pruebas en emulador o dispositivo completadas.
- [ ] Publicación definitiva en GitHub Classroom verificada.
- [ ] Versión final preparada para el módulo 8.

## 10. Registro de cambios resumido

### Pasado

- Se identificó el problema empresarial que atenderá la aplicación.
- Se definieron las funciones iniciales.
- Se elaboraron los wireframes preliminares.
- Se planificó el uso de Figma para los prototipos digitales.

### Actual

- Se creó el proyecto base en Android Studio con Kotlin.
- Se organizó correctamente el repositorio local.
- Se configuraron los archivos de Gradle y el archivo `.gitignore`.
- Se verificó que el proyecto compilara correctamente.
- Se publicó el código inicial mediante un commit y un push.
- Se agregó el archivo `CHANGELOG.md`.

### Futuro

- Diseñar las pantallas finales.
- Implementar la lista de tareas.
- Agregar las funciones para crear, editar, completar y eliminar tareas.
- Incorporar almacenamiento local con Room y SQLite.
- Ejecutar pruebas en un emulador y un dispositivo Android.
- Preparar la versión final del programa para el módulo 8.

## 11. Repositorio y documentación

- Repositorio: [CarWash Daily Checklist](https://github.com/Michael3848/CarWash-Daily-Checklist)
- README: [README.md](https://github.com/Michael3848/CarWash-Daily-Checklist)
- Changelog: [CHANGELOG.md](https://github.com/Michael3848/CarWash-Daily-Checklist)
- Wiki: [Wiki del proyecto](https://github.com/Michael3848/CarWash-Daily-Checklist)

## Autor

Michael Ramirez

