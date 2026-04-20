# Proyecto Intermodular - Gestión de Biblioteca
1º Desarrollo de Aplicaciones Multiplataforma (DAM)

## Introducción
Esta aplicación permite gestionar una colección personal de libros, permitiendo al usuario registrar sus lecturas, puntuarlas, permitir hacer un seguimiento de lecturas (leído/no leído/leyendo), visualizar las portadas de forma organizada, y realizar una valoración personal.

## Tecnologías
- **Lenguaje:** Java (JDK 17+)
- **Interfaz:** JavaFX 21+ + Scene Builder
- **Base de datos:** MySQL (XAMPP)/JDBC
- **Driver de conexión:** MySQL Connector/J
- **Gestión de versiones:** Git / GitHub

## Estructura del repositorio
- **/src:** Código fuente organizado bajo el patrón **MVC** (Modelo-Vista-Controlador).
  - **/conexion:** Lógica de enlace con MySQL ('ConexionBD').
  - **/controlador:** Lógica de negocio y gestión de eventos ('LoginController').
  - **/modelo:** Clases de objeto (Libro, Usuario, Autor).
  - **/dao:** Clases de acceso a datos (LibroDAO, AutorDAO, etc.).
  - **/vista:** Archivos FXML para la interfaz gráfica ('LoginView.fxml').
- **/sql:** Script de creación de la base de datos 'biblioteca_dam'.
- **/docs:** Documentación técnica y diagramas.
- **'/docs/xml':** Definición y validación de datos en formato XML.

## Análisis del Modelo de Datos
La aplicación utiliza un diseño relacional normalizado para garantizar la integridad de los datos.
- **Seguridad:** Implementación de 'PreparedStatement' para prevenir ataques de Inyección SQL en el acceso.
- **Integridad:** Restricciones 'NOT NULL' en credenciales y uso de claves foráneas para la consistencia del catálogo.

## Estado de la implementación (Hito: Login Funcional)

### 1. Base de Datos
- Estructura completada en MySQL con la tabla 'usuarios' operativa.
- Sistema de autenticación verificado mediante consultas directas.
- Simplificación para que un libro pertenezca a un solo género al igual que un libro solo pueda estar escrito por un único autor.
- Tabla intermedia por N-M y una tabla extra 'Estado' independiente, para permitir la escalabilidad en futuras actualizaciones sin modificar el código. (Normalización).
- Implementación de las pertinentes claves foraneas para evitar duplicidad.
- Añadido UNIQUE y NOT NULL para evitar campos vacíos indeseados en sitios clave y duplicidad de correos o usuarios.


### 2. Capa de Control y Lógica
- **Controlador de Login:** Implementado siguiendo el principio de separación de intereses. Se ha evitado el uso de etiquetas de evento en el FXML, vinculando los controles mediante el método '@FXML initialize()' y el uso de lambdas ('setOnAction') en Java.
- **Gestión de Alertas:** Sistema de validación de campos mediante ventanas emergentes (`Alert`) para mejorar la experiencia de usuario.
- **Clase PruebaConexión:** He eliminado esa clase para mayor facilidad y limpieza del código.

### 3. Interfaz Gráfica (JavaFX)
- **Escena de Login:** Diseño finalizado en Scene Builder y correctamente vinculado a la clase controladora.
- **Configuración del Entorno:** Integración de librerías externas y configuración de **VM Options** para el despliegue del SDK de JavaFX.
- **Conectividad JDBC:** Resolución de dependencias del conector MySQL para asegurar la persistencia.

### 4. Conexión y Pruebas
- **Prueba de Autenticación:** Verificada con éxito. El sistema comunica la interfaz con la base de datos, permitiendo el acceso solo con credenciales válidas registradas en el servidor local.

---

### Notas de Ejecución
Para ejecutar el proyecto correctamente en IntelliJ/Eclipse:
1. Configurar las **VM Options** con la ruta local al SDK de JavaFX:
   '--module-path "TU_RUTA_AL_SDK\lib" --add-modules javafx.controls,javafx.fxml'
2. Asegurar que el JAR del conector MySQL esté añadido en las librerías del proyecto ('Project Structure > Libraries').