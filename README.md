# Proyecto Intermodular - Gestión de Biblioteca
1º Desarrollo de Aplicaciones Multiplataforma (DAM)

## Introducción
Esta aplicación permite gestionar una colección personal de libros, permitiendo al usuario registrar sus lecturas, puntuarlas, realizar un seguimiento (leído/no leído/leyendo), visualizar las portadas de forma organizada y realizar una valoración personal.

## Tecnologías
- **Lenguaje:** Java (JDK 17+)
- **Interfaz:** JavaFX 21+ + Scene Builder
- **Base de datos:** MySQL (XAMPP)/JDBC
- **Driver de conexión:** MySQL Connector/J
- **Gestión de versiones:** Git / GitHub

## Estructura del repositorio
- **/src:** Código fuente organizado bajo el patrón **MVC** (Modelo-Vista-Controlador).
  - **/conexion:** Lógica de enlace con MySQL ('ConexionBD').
  - **/controlador:** Lógica de negocio y gestión de eventos ('LoginController', 'PrincipalController').
  - **/modelo:** Clases de objeto (Libro, Usuario, Autor).
  - **/dao:** Clases de acceso a datos (LibroDAO, AutorDAO, etc.).
  - **/vista:** Archivos FXML para la interfaz gráfica ('LoginView.fxml', 'PrincipalView.fxml').
- **/sql:** Script de creación de la base de datos 'biblioteca_dam'.
- **/docs:** Documentación técnica y diagramas.
- **/docs/xml:** Definición y validación de datos en formato XML.

## Análisis del Modelo de Datos
La aplicación utiliza un diseño relacional normalizado para garantizar la integridad de los datos.
- **Seguridad:** Implementación de 'PreparedStatement' para prevenir ataques de Inyección SQL.
- **Integridad:** Restricciones 'NOT NULL' en credenciales y uso de claves foráneas para la consistencia del catálogo.
- **Normalización:** Uso de tablas intermedias para relaciones N-M y tablas maestras para Estados, Géneros y Autores.

## Estado de la implementación (Hito: Navegación y Pantalla Principal)

### 1. Base de Datos
- Estructura completada en MySQL con las tablas de usuarios y catálogo operativas.
- Sistema de autenticación verificado y restricciones de integridad ('UNIQUE', 'NOT NULL') aplicadas.

### 2. Capa de Control y Lógica
- **Controlador de Login:** Implementado con validación contra BD y gestión de alertas.
- **Navegación:** Implementado el "salto" de ventana exitoso. El sistema cierra el Login y abre la Ventana Principal tras validar credenciales.
- **Comunicación entre Ventanas:** Implementado el paso de parámetros mediante 'loader.getController()', permitiendo que la ventana principal reciba y muestre el nombre del usuario logueado.
- **Gestión de Eventos:** Vinculación estricta en código mediante '@FXML initialize()' y expresiones Lambda, evitando el uso de atributos de evento en el archivo FXML.

### 3. Interfaz Gráfica (JavaFX)
- **Escena de Login:** Finalizada y funcional.
- **Escena Principal:** Estructura inicial creada con 'AnchorPane', incluyendo un saludo personalizado ('Label') y una tabla de datos ('TableView') configurada con columnas para Título, Autor y Género.

### 4. Conexión y Pruebas
- **Prueba de Flujo Completo:** Verificada con éxito. El sistema comunica la interfaz con MySQL, valida el acceso, transfiere el nombre de usuario y gestiona el cierre de sesión correctamente.

---

### Notas de Ejecución
Para ejecutar el proyecto correctamente en IntelliJ/Eclipse:
1. Configurar las **VM Options** con la ruta local al SDK de JavaFX:
   '--module-path "TU_RUTA_AL_SDK\lib" --add-modules javafx.controls,javafx.fxml'
2. Asegurar que el JAR del conector MySQL esté añadido en las librerías del proyecto ('Project Structure > Libraries').