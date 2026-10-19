Changelog

Todos los cambios relevantes de CarWash Daily Checklist se documentan en este archivo.

[No publicado]

Planeado

    Incorporar prioridades y categorías.

    Agregar fechas límite y recordatorios.

    Evaluar el uso de WorkManager para trabajo periódico.

    Añadir búsqueda e historial de tareas.

    Evaluar sincronización opcional en la nube.

    Incorporar pruebas automatizadas adicionales.

[1.0.0] - 2026-10-18

Agregado

    Pantalla principal de tareas.

    Formulario para crear tareas.

    Campo de descripción opcional.

    Validación del título obligatorio.

    Opciones para editar y eliminar tareas.

    Confirmación antes de eliminar.
    
    Marcado de tareas como completadas o pendientes.

    Contador dinámico de tareas pendientes.

    Filtros para mostrar todas, pendientes o completadas.

    Mensajes cuando un filtro no tiene resultados.

    Desplazamiento vertical mediante NestedScrollView.

    Persistencia local con Room y SQLite.

    Entidad Task.

    Interfaz TaskDao.
    
    Base de datos AppDatabase.

    Uso de Flow para observar cambios.

    Procesamiento de anotaciones mediante KSP.

    Capturas de evidencia en docs/screenshots.

    Recursos de texto organizados en strings.xml.

Cambiado

    Se sustituyeron las tareas fijas del XML por registros dinámicos almacenados en Room.

    Se actualizó la interfaz principal para admitir listas desplazables.

    Se corrigió la ubicación de MainActivity dentro del paquete principal.

    Se actualizó AndroidManifest.xml para registrar correctamente la actividad inicial.

    Se actualizó el nombre visible de la aplicación.

    Se reorganizó README.md para reflejar la implementación real.

Verificado

    Creación, consulta, edición y eliminación de tareas.

    Validación de datos.

    Cambio de estado entre pendiente y completada.

    Persistencia después del reinicio.

    Funcionamiento de los filtros.

    Comportamiento de listas vacías.

    Desplazamiento con múltiples tareas.

    Ejecución en un emulador Pixel 6 con API 35.

[0.3.0] - 2026-10-04

Agregado

    Estructura inicial del proyecto Android.

    Proyecto configurado con Kotlin.

    Configuración de Gradle.

    Archivo .gitignore.

    Publicación inicial del código en GitHub.

    Sección de arquitectura y almacenamiento.

    Estado inicial del proyecto.

    Enlace entre README.md y CHANGELOG.md.

Cambiado

    Se definió el uso futuro de Room y SQLite.

    Se ajustó el alcance inicial para mantener una aplicación viable.

    Se aclaró que la primera versión no tendría un panel administrativo separado.

[0.2.0] - 2026-09-20

Agregado

    Wireframes preliminares.

    Planificación de prototipos mediante Figma.

    Definición de las funciones para crear, editar, completar y eliminar tareas.

[0.1.0] - 2026-09-06

    Agregado

    Descripción inicial de CarWash Daily Checklist.

    Exposición del problema operativo del lava autos.

    Herramientas y plataformas propuestas.

    Diseño y funcionalidad inicial.