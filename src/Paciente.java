public class Paciente extends Persona {
    public Paciente(String identificador, String nombreCompleto) {
        super(identificador, nombreCompleto);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "identificador='" + getIdentificador() + '\'' +
                ", nombreCompleto='" + getNombreCompleto() + '\'' +
                '}';
    }
}