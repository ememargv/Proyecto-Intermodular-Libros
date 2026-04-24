# Proyecto Intermodular - Gestión de Biblioteca
1º Desarrollo de Aplicaciones Multiplataforma (DAM)

## Introducción
Esta aplicación permite gestionar una colección personal de libros. El usuario puede registrar sus lecturas, puntuarlas, realizar un seguimiento del estado (leído, no leído o leyendo) y organizar su biblioteca de forma visual y eficiente.

## Tecnologías Utilizadas
- **Lenguaje:** Java (JDK 17+)
- **Interfaz Gráfica:** JavaFX 21 + Scene Builder
- **Base de Datos:** MySQL (XAMPP) a través de JDBC
- **Driver de Conexión:** MySQL Connector/J
- **Control de Versiones:** Git y GitHub

## Estructura del Proyecto
El código fuente está organizado siguiendo el patrón de diseño **MVC (Modelo-Vista-Controlador)** para garantizar un código limpio y escalable:

- **/src/conexion:** Gestión de la conexión técnica con el servidor MySQL.
- **/src/modelo:** Clases que representan las entidades de datos (Libro, Usuario, Autor).
- **/src/vista:** Archivos FXML que definen la interfaz gráfica.
- **/src/controlador:** Lógica de control que gestiona la interacción del usuario con la interfaz.
- **/src/dao:** Capa de Acceso a Datos (Data Access Object).

### Optimizaciones de Arquitectura
1. **Patrón DAO:** Separación estricta del SQL de los controladores. Esto permite que el Controlador solo gestione la interfaz, mientras que el DAO se encarga exclusivamente de la persistencia, facilitando el mantenimiento y posibles migraciones de base de datos.
2. **Patrón Singleton:** He refactorizado la clase 'ConexionBD' para implementar el patrón Singleton. Esto garantiza que el sistema mantenga una única instancia de la conexión a la base de datos, optimizando el uso de recursos y evitando aperturas innecesarias durante la ejecución.

## Análisis del Modelo de Datos
La aplicación utiliza un diseño relacional normalizado para garantizar la integridad de la información:
- **Seguridad:** Uso de 'PreparedStatement' en todas las consultas para prevenir ataques de inyección SQL.
- **Integridad:** Implementación de restricciones 'NOT NULL', claves primarias autoincrementales y claves foráneas para mantener la consistencia entre tablas.
- **Privacidad y Lógica N:M:** Las consultas están filtradas por el ID del usuario activo. El diseño de la tabla intermedia 'coleccion' permite que cada usuario tenga su propia experiencia, pudiendo puntuar y gestionar estados de forma independiente al catálogo general.

## Funcionalidades Destacadas

### 1. Gestión de Puntuaciones y Estados
- El sistema permite calificar cada libro con una nota del 1 al 5.
- La puntuación es específica para cada usuario, permitiendo valoraciones distintas para una misma obra según la colección personal.

### 2. Búsqueda Dinámica y UX
- **Filtro en tiempo real:** Implementación de un buscador que actualiza la tabla instantáneamente mientras el usuario escribe, buscando coincidencias tanto en el título como en el autor.
- **Usabilidad (Prompt Texts):** Los campos de entrada incluyen guías visuales para facilitar el registro de datos.

### 3. Gestión de Colección (CRUD)
- **Inserción Relacional:** Al añadir un libro, el sistema vincula automáticamente el registro con el ID de usuario logueado.
- **Eliminación Segura:** El usuario puede retirar títulos de su biblioteca personal sin afectar al catálogo maestro de la aplicación.

## Notas de Ejecución
1. Configurar las **VM Options** en el IDE con la ruta local al SDK de JavaFX.
2. Asegurar que el conector de MySQL ('mysql-connector-j') esté incluido en las librerías.
3. Importar el script SQL incluido en la carpeta '/sql' para recrear la base de datos.