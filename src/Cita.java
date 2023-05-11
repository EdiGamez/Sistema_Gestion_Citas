import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

class Cita {
    private UUID id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String asunto;

    public Cita(UUID id, Paciente paciente, Medico medico, LocalDateTime fechaHora, String asunto) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.asunto = asunto;
    }

    // Constructor sin UUID
    public Cita(Paciente paciente, Medico medico, LocalDateTime fechaHora, String asunto) {
        this(UUID.randomUUID(), paciente, medico, fechaHora, asunto);
    }

    public UUID getId() {
        return this.id;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public String toCSV() {
        String var10000 = String.valueOf(this.id);
        return var10000 + "," + String.valueOf(this.paciente.getId()) + "," + String.valueOf(this.medico.getId()) + "," + this.fechaHora.format(this.formatter) + "," +asunto;
    }

    public String toString() {
        String var10000 = this.paciente.getNombre();
        return " Paciente: " + var10000 + " " + this.paciente.getApellido() + "\n Medico: " + this.medico.getNombre() + " " + this.medico.getApellido() + ",\n Fecha y Hora: " + this.fechaHora.format(this.formatter) + ",\n Motivo: " +asunto + "\n ID: " + String.valueOf(this.getId());
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

}
