public class Doctor extends Persona {
    private String especialidad;

    public Doctor(String identificador, String nombreCompleto, String especialidad) {
        super(identificador, nombreCompleto);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "especialidad='" + especialidad + '\'' +
                ", identificador='" + getIdentificador() + '\'' +
                ", nombreCompleto='" + getNombreCompleto() + '\'' +
                '}';
    }
}