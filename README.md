# Proyecto Intermodular - Gestión de Biblioteca Personal
**Nombre**: María del Mar García Ventura

## Introducción
Este proyecto consiste en una aplicación de escritorio diseñada para gestionar una biblioteca de libros de forma personalizada. El sistema permite a cada usuario llevar un control detallado de sus lecturas, puntuar sus obras favoritas y organizar su colección mediante una interfaz visual sencilla y eficiente. El objetivo principal ha sido integrar los conocimientos de todos los módulos del primer curso en una herramienta funcional y segura.

## Organización del Proyecto (Índice por Módulos)
Para facilitar la revisión de cada parte del proyecto, he organizado los archivos de la siguiente manera:

* **Programación (PROG/FDP):** El código fuente se encuentra en la carpeta `/Proyecto-Intermodular-libros`. Está organizado en paquetes (controlador, modelo, dao, conexion) aplicando el patrón MVC para que la lógica de la aplicación esté separada de la interfaz.
* **Bases de Datos (BBDD):** En la carpeta `/sql` se incluye el script de creación de tablas y los diagramas Entidad-Relación y Relacional y algunas capturas de la BBDD en PHP. El diseño está normalizado para evitar la duplicidad de datos.
* **Sistemas Informáticos (SSII):** En la carpeta `/docs(Sistemas Informáticos)` he adjuntado un informe técnico en PDF que explica los requisitos de hardware, el software necesario (XAMPP, JavaFX) y los pasos para el despliegue del sistema.
* **Lenguajes de Marcas (LMG):** En la carpeta `Proyecto-Intermodular-Libros\src\XML` se encuentran los archivos `biblioteca.xml` y `biblioteca.xsd`. Estos archivos sirven para demostrar cómo se pueden estructurar y validar los datos de la biblioteca en un formato estándar.

## Funcionalidades Principales
* **Búsqueda Dinámica:** Filtro en tiempo real que permite buscar libros por título o autor mientras se escribe.
* **Control de Lectura:** Posibilidad de marcar libros como "Pendiente", "Leyendo" o "Leído".
* **Sistema de Valoración:** Cada usuario puede puntuar sus libros del 1 al 5 de manera independiente al resto de usuarios.
* **Seguridad de Datos:** Implementación de consultas preparadas (PreparedStatements) para proteger la base de datos contra inyecciones SQL.
* **Persistencia Real:** Los datos se guardan de forma permanente en un servidor local MySQL a través de JDBC.

## Guía de Instalación Rápida
1. **Base de Datos:** Crear una base de datos llamada `biblioteca` en phpMyAdmin e importar el contenido del archivo `/sql/crear_tablas.txt`.
2. **Entorno de Desarrollo:** Abrir el proyecto en IntelliJ o Eclipse y añadir las librerías del SDK de JavaFX y el conector de MySQL.
3. **Ejecución:** Configurar las VM Options con la ruta de tu SDK local:
   `--module-path "C:\JavaFX\openjfx-26_windows-x64_bin-sdk\javafx-sdk-26\lib" --add-modules javafx.controls,javafx.fxml`

---
*Este proyecto ha sido desarrollado como parte de la evaluación del primer año de DAM en Prometeo.*