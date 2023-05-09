import java.util.UUID;

abstract class Persona {
    private UUID id;
    private String nombre;
    private String apellido;

    public Persona(UUID id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona(String nombre, String apellido) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public UUID getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }
}

