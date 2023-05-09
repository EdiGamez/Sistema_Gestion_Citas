import java.util.UUID;

class Medico extends Persona {
    private String especialidad;

    public Medico(UUID id, String nombre, String apellido, String especialidad) {
        super(id, nombre, apellido);
        this.especialidad = especialidad;
    }

    public Medico(String nombre, String apellido, String especialidad) {
        super(nombre, apellido);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public String toCSV() {
        String var10000 = String.valueOf(this.getId());
        return var10000 + "," + this.getNombre() + "," + this.getApellido() + "," + this.getEspecialidad();
    }

    public String toString() {
        String var10000 = this.getNombre();
        return ", Nombre: '" + var10000 + " " + this.getApellido() + ", Especialidad'" + this.especialidad + "ID: " + String.valueOf(this.getId());
    }
}
