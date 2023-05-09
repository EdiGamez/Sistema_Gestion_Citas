import java.util.UUID;

class Paciente extends Persona {
    public Paciente(UUID id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }

    public Paciente(String nombre, String apellido) {
        super(nombre, apellido);
    }

    public String toString() {
        String var10000 = String.valueOf(this.getId());
        return "ID:" + var10000 + "Nombre " + this.getNombre() + " " + this.getApellido() + "ID: " + String.valueOf(this.getId());
    }

    public String toCSV() {
        String var10000 = String.valueOf(this.getId());
        return var10000 + "," + this.getNombre() + "," + this.getApellido();
    }
}
