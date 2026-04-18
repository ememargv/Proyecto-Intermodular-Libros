# Proyecto Intermodular - Gestión de Biblioteca
1º Desarrollo de Aplicaciones Multiplataforma (DAM)

## Introducción
Este proyecto consiste en el desarrollo de una aplicación para la gestión de una colección personal de libros. El sistema permite administrar usuarios, autores y un catálogo bibliográfico, facilitando el seguimiento de lecturas y valoraciones personales.

## Tecnologías
- Lenguaje: Java (JDK 17+)
- Interfaz: JavaFX + Scene Builder
- Base de datos: MySQL (XAMPP)
- Driver de conexión: MySQL Connector/J
- Gestión de versiones: Git / GitHub

## Estructura del repositorio
- /src: Directorio para el código fuente de la aplicación.
  - /conexion: Lógica de enlace con MySQL.
  - /modelo: Clases de objeto (Libro, Usuario, Autor).
  - /dao: Clases de acceso a datos (LibroDAO, AutorDAO, GeneroDAO).
  - /vista: Archivos FXML para la interfaz gráfica (LoginView.fxml).
- /recursos: Carpeta para archivos multimedia.
  - /portadas: Almacenamiento de imágenes de los libros.
- /sql: Script de creación de la base de datos y tablas.
- /docs: Documentación técnica y diagramas.

## Análisis del Modelo de Datos
La aplicación utiliza un diseño relacional normalizado para garantizar la integridad de los datos y evitar redundancias.

### Entidades y Relaciones
- **Relación N:M (Libro-Usuario):** Gestionada a través de la tabla "coleccion", donde se almacenan estados de lectura y puntuaciones personalizadas.
- **Integridad:** Uso de claves foráneas (FK), claves primarias autoincrementales y restricciones de unicidad en credenciales de usuario.

## Estado de la implementación (Actualizado)
- **Base de Datos:** Estructura completada en MySQL. Tablas pobladas con géneros, autores y libros de prueba.
- **Capa de Datos (DAO):** - GeneroDAO: Funcional y verificado.
  - LibroDAO y AutorDAO: Creados para gestionar la persistencia de los objetos.
- **Interfaz Gráfica:** - Entorno JavaFX SDK configurado e integrado en el IDE.
  - Diseño base de LoginView.fxml creado en Scene Builder.
  - Identificadores (fx:id) asignados para la lógica de carga de portadas.
- **Conexión:** Test de conectividad superado con éxito mediante la clase PruebaConexion.