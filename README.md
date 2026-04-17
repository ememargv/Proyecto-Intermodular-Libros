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
  - /dao: Gestión de acceso a datos (En desarrollo).
- /sql: Contiene el script de creación de la base de datos (crear_tablas.txt).
- /docs: Documentación técnica y archivos de configuración.
- /docs/diagramas: Esquemas gráficos del modelo de datos (E-R y Relacional).

## Análisis del Modelo de Datos
La aplicación utiliza un diseño relacional normalizado para garantizar la integridad de los datos.

### Entidades y Relaciones
- **Relación N:M (Libro-Usuario):** Implementada mediante la tabla intermedia "coleccion", permitiendo que cada usuario gestione estados de lectura y puntuaciones (1-5) de forma independiente.
- **Integridad Referencial:** Se han aplicado restricciones de clave foránea (FK), valores únicos (UQ) en credenciales y autoincrementos (AI) en claves primarias.

## Estado de la implementación
- **Base de Datos:** Estructura física completada en MySQL ('biblioteca_dam').
- **Población de Datos:** Tabla "generos" inicializada con 11 categorías (Fantasía, Erótica, Ciencia-ficción, etc.).
- **Documentación:** Finalizados los diagramas Entidad-Relación y el Modelo Relacional con cardinalidades.
- **Conexión:** Capa JDBC configurada y testeada con éxito.

Proximos pasos:
- Implementación de la capa DAO para la extracción de datos.
- Introducción de datos de prueba (Libros y Autores) vía phpMyAdmin.
- Desarrollo de la interfaz gráfica con JavaFX.