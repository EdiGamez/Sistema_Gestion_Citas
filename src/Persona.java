public abstract class Persona {
    private String identificador;
    private String nombreCompleto;

    public Persona(String identificador, String nombreCompleto) {
        this.identificador = identificador;
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "identificador='" + identificador + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                '}';
    }
}

