# Proyecto Intermodular - Gestion de Biblioteca
1º Desarrollo de Aplicaciones Multiplataforma (DAM)

## Introduccion
Este proyecto consiste en el desarrollo de una aplicacion para la gestion de una coleccion personal de libros. El sistema permite administrar usuarios, autores y un catalogo bibliografico, facilitando el seguimiento de lecturas y valoraciones personales.

## Tecnologias
- Lenguaje: Java
- Interfaz: JavaFX
- Base de datos: MySQL (XAMPP)
- Gestion de versiones: Git / GitHub

## Estructura del repositorio
- /src: Directorio para el codigo fuente de la aplicacion.
- /sql: Contiene el script de creacion de la base de datos (crear_tablas.sql).
- /docs: Documentacion tecnica y archivos de configuracion.
- /docs/diagramas: Esquemas graficos del modelo de datos.

## Analisis del Modelo de Datos
La aplicacion gestiona la informacion centralizada en la entidad Libro. Para permitir una organizacion eficiente y escalable, se han definido las siguientes relaciones y entidades:

### Entidades Principales
- **Libro y Coleccion:** Existe una relacion de muchos a muchos (N:M) entre libros y las colecciones de los usuarios. Esto se gestiona mediante una tabla intermedia que permite que un libro pertenezca a varias listas y que cada coleccion contenga multiples ejemplares.
- **Autores y Generos:** Se han definido como entidades independientes. Un autor puede estar vinculado a varios libros y un genero puede englobar diversas obras, estableciendo relaciones de uno a muchos (1:N).

### Normalización y Escalabilidad del Diseño
Se ha aplicado un proceso de normalizacion al diseño, decidiendo crear entidades propias para categorias como el Estado de lectura y los Generos Literarios. Esta estructura presenta una ventaja clave: si en el futuro se desea ampliar el sistema (por ejemplo, añadiendo nuevos estados), es mucho mas sencillo y eficiente realizar la actualizacion directamente en las tablas de la base de datos que modificar el codigo de la aplicacion. Esto garantiza un sistema mas flexible y mantenible.

## Estado de la implementacion
Se ha completado la implementacion fisica de la base de datos. El script SQL para la creacion de tablas, relaciones y restricciones se encuentra disponible en la carpeta /sql.

Proximos pasos:
- Desarrollo de la capa de conexion mediante JDBC en Java.
- Diseño de interfaces graficas con JavaFX.
- Integracion de la logica de negocio y gestion de eventos.