public class Administrador extends Persona {
    private String usuario;
    private String contrasena;

    public Administrador(String nombre, String apellido, String usuario, String contrasena) {
        super(nombre, apellido);
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getContrasena() {
        return this.contrasena;
    }
}