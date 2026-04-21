package modelo;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String password;
    private String correoElectronico;


    public Usuario() {}

    public Usuario(String nombreUsuario, String correoElectronico, String password) {
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.password = password;
    }

    public Usuario(int id, String nombreUsuario, String password, String correoElectronico) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.correoElectronico = correoElectronico;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
}