package modelo;

public class Libro {
    private int id;
    private String titulo;
    private String rutaPortada;
    private int idAutor;
    private int idGenero;
    private String nombreAutor;
    private String nombreGenero;

    public Libro() {}

    public Libro(String titulo, String rutaPortada, int idAutor, int idGenero) {
        this.titulo = titulo;
        this.rutaPortada = rutaPortada;
        this.idAutor = idAutor;
        this.idGenero = idGenero;
    }

    // Constructor para la Tabla(mostrar datos)
    public Libro(String titulo, String nombreAutor, String nombreGenero) {
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
        this.nombreGenero = nombreGenero;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getRutaPortada() { return rutaPortada; }
    public int getIdAutor() { return idAutor; }
    public int getIdGenero() { return idGenero; }
    public String getNombreAutor() { return nombreAutor; } // <--- Importante
    public String getNombreGenero() { return nombreGenero; } // <--- Importante
}