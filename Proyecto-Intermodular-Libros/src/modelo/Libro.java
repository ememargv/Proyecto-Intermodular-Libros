package modelo;

public class Libro {
    private int id;
    private String titulo;
    private String rutaPortada;
    private int idAutor;
    private int idGenero;

    public Libro() {}

    public Libro(int id, String titulo, String rutaPortada, int idAutor, int idGenero) {
        this.id = id;
        this.titulo = titulo;
        this.rutaPortada = rutaPortada;
        this.idAutor = idAutor;
        this.idGenero = idGenero;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getRutaPortada() { return rutaPortada; }
    public void setRutaPortada(String rutaPortada) { this.rutaPortada = rutaPortada; }
    public int getIdAutor() { return idAutor; }
    public void setIdAutor(int idAutor) { this.idAutor = idAutor; }
    public int getIdGenero() { return idGenero; }
    public void setIdGenero(int idGenero) { this.idGenero = idGenero; }
}