package modelo;

public class Libro {
    private int id;
    private String titulo;
    private String rutaPortada;
    private int idAutor;
    private int idGenero;
    private String nombreAutor;
    private String nombreGenero;
    private int puntuacion; // Nuevo

    public Libro() {}

    public Libro(String titulo, String rutaPortada, int idAutor, int idGenero) {
        this.titulo = titulo;
        this.rutaPortada = rutaPortada;
        this.idAutor = idAutor;
        this.idGenero = idGenero;
    }

    // Constructor para la Tabla (incluyendo puntuación)
    public Libro(String titulo, String nombreAutor, String nombreGenero, int puntuacion) {
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
        this.nombreGenero = nombreGenero;
        this.puntuacion = puntuacion;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getRutaPortada() { return rutaPortada; }
    public int getIdAutor() { return idAutor; }
    public int getIdGenero() { return idGenero; }
    public String getNombreAutor() { return nombreAutor; }
    public String getNombreGenero() { return nombreGenero; }
    public int getPuntuacion() { return puntuacion; } // Nuevo
}