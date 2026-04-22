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

### Mi enfoque de diseño (Patrón DAO)
En este proyecto he decidido separar estrictamente el SQL de los controladores. De esta forma, aplico el principio de responsabilidad única: el Controlador solo se encarga de la interfaz (leer textos y mostrar alertas), mientras que el DAO se encarga exclusivamente de la base de datos. 

He diseñado esta arquitectura pensando en la escalabilidad. Si en el futuro necesitara cambiar MySQL por otro sistema como Oracle, solo tendría que modificar la clase 'ConexionBD' y los métodos dentro de los 'DAO'. Todo el resto de mi programa y la interfaz gráfica seguirían funcionando exactamente igual sin tener que cambiar ni una sola línea de código en los controladores.

## Análisis del Modelo de Datos
La aplicación utiliza un diseño relacional normalizado para garantizar la integridad de la información:
- **Seguridad:** Uso de 'PreparedStatement' en todas las consultas para prevenir ataques de Inyección SQL.
- **Integridad:** Implementación de restricciones 'NOT NULL', claves primarias autoincrementales y claves foráneas para mantener la consistencia entre las tablas 'autores', 'generos' y 'libros'.
- **Persistencia:** Los datos se almacenan de forma permanente en un servidor local MySQL.

## Estado de la Implementación (Hito: Gestión de Colección)

### 1. Base de Datos
- Tablas de 'usuarios', 'libros', 'autores' y 'generos' operativas con integridad referencial.
- Configuración de campos 'UNIQUE' para evitar correos o nombres de usuario duplicados.

### 2. Capa de Control y Lógica
- **Sistema de Login:** Validación de credenciales en tiempo real contra la base de datos.
- **Carga de Datos Relacionales:** Implementación de 'JOINS' en SQL para mostrar nombres de autores y géneros en lugar de simples IDs numéricos.
- **Gestión de Formularios:** Uso de 'ObservableList' para rellenar desplegables ('ComboBox') de forma dinámica desde la base de datos.
- **Navegación:** Gestión de ventanas mediante 'Stage' y 'FXMLLoader'. Uso de ventanas modales ('APPLICATION_MODAL') para el alta de libros, asegurando que la tabla principal se refresque automáticamente al cerrar el formulario.

### 3. Interfaz Gráfica (JavaFX)
- **Pantalla Principal:** 'TableView' configurada con 'PropertyValueFactory' para mostrar la colección del usuario.
- **Formulario de Alta:** Nueva vista 'NuevoLibroView' implementada para permitir al usuario elegir autores y géneros existentes mediante desplegables, evitando errores de introducción de datos.

## Notas de Ejecución
Para ejecutar el proyecto en un entorno de desarrollo (IntelliJ/Eclipse):
1. Configurar las **VM Options** con la ruta local al SDK de JavaFX:
   '--module-path "RUTA_A_TU_SDK\lib" --add-modules javafx.controls,javafx.fxml'
2. Asegurar que el conector de MySQL ('mysql-connector-j') esté incluido en las librerías del proyecto.
3. Importar el script SQL incluido en la carpeta '/sql' para recrear la base de datos localmente.