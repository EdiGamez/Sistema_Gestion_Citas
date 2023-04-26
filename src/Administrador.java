public class Administrador extends Persona {
    private String contraseña;

    public Administrador(String identificador, String nombreCompleto, String contraseña) {
        super(identificador, nombreCompleto);
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean validarAcceso(String contraseña) {
        return this.contraseña.equals(contraseña);
    }
    @Override
    public String toString() {
        return "Administrador{" +
                "contraseña='" + contraseña + '\'' +
                ", identificador='" + getIdentificador() + '\'' +
                ", nombreCompleto='" + getNombreCompleto() + '\'' +
                '}';
    }
}
