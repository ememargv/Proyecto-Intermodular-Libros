package modelo;

public class Libro {
    private int id;
    private String titulo;
    private String rutaPortada;
    private int idAutor;
    private int idGenero;

    // Constructor vacío
    public Libro() {}

    // Constructor con datos (sin ID, porque es autoincremental)
    public Libro(String titulo, String rutaPortada, int idAutor, int idGenero) {
        this.titulo = titulo;
        this.rutaPortada = rutaPortada;
        this.idAutor = idAutor;
        this.idGenero = idGenero;
    }

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public String getRutaPortada() { return rutaPortada; }
    public int getIdAutor() { return idAutor; }
    public int getIdGenero() { return idGenero; }

    // ... puedes añadir el resto de getters/setters si los necesitas
}