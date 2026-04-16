# Proyecto Intermodular - Gestión de Biblioteca
1º Desarrollo de Aplicaciones Multiplataforma (DAM)

## Introducción
Este proyecto consiste en el desarrollo de una aplicación para la gestión de una colección personal de libros. El sistema permite administrar usuarios, autores y un catálogo bibliográfico, facilitando el seguimiento de lecturas y valoraciones personales.

## Tecnologías
- Lenguaje: Java (JDK 17+)
- Interfaz: JavaFX
- Base de datos: MySQL (XAMPP)
- Driver de conexión: MySQL Connector/J
- Gestión de versiones: Git / GitHub

## Estructura del repositorio
- /src: Directorio para el código fuente de la aplicación.
  - /conexion: Lógica de enlace con MySQL.
  - /modelo: Clases de objeto (Libro, Usuario, etc.).
  - /dao: Gestión de acceso a datos.
- /sql: Contiene el script de creación de la base de datos (crear_tablas.txt).
- /docs: Documentación técnica y archivos de configuración.
- /docs/diagramas: Esquemas gráficos del modelo de datos.

## Análisis del Modelo de Datos
La aplicación gestiona la información centralizada en la entidad Libro. Para permitir una organización eficiente y escalable, se han definido las siguientes relaciones y entidades:

### Entidades Principales
- **Libro y Colección:** Relación de muchos a muchos (N:M) gestionada mediante una tabla intermedia. Permite que cada usuario gestione su propia lista con estados de lectura y puntuaciones.
- **Autores y Géneros:** Entidades independientes con relaciones de uno a muchos (1:N) hacia la tabla de libros.
- **Usuarios:** Gestión de perfiles con credenciales únicas para el acceso al sistema.

## Estado de la implementación
- **Base de Datos:** Implementación física completada con éxito en MySQL. El esquema incluye 6 tablas (autores, géneros, estados, libros, usuarios y colección) con sus respectivas claves foráneas y restricciones.
- **Conexión:** Capa de conexión JDBC operativa y testeada.
- **Modelado:** Creación de clases POJO (Plain Old Java Objects) para representar las entidades del sistema en el entorno Java.

Próximos pasos:
- Diseño de interfaces gráficas con JavaFX (Ventanas de Login y Panel Principal).
- Desarrollo de métodos CRUD (Crear, Leer, Actualizar, Borrar) en la capa DAO.
- Implementación de la lógica de filtrado y búsqueda de libros.