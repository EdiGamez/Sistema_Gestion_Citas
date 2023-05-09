import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

class Cita {
    private UUID id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    DateTimeFormatter formatter;
    private EstatusCita estatus;

    public Cita(UUID id, Paciente paciente, Medico medico, LocalDateTime fechaHora, EstatusCita estatus) {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estatus = estatus;
    }

    public Cita(Paciente paciente, Medico medico, LocalDateTime fechaHora) {
        this(UUID.randomUUID(), paciente, medico, fechaHora, Cita.EstatusCita.PENDIENTE);
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
        return var10000 + "," + String.valueOf(this.paciente.getId()) + "," + String.valueOf(this.medico.getId()) + "," + this.fechaHora.format(this.formatter) + "," + String.valueOf(this.estatus);
    }

    public String toString() {
        String var10000 = this.paciente.getNombre();
        return "Paciente: " + var10000 + " " + this.paciente.getApellido() + "\nMedico: " + this.medico.getNombre() + " " + this.medico.getApellido() + ",\nFecha y Hora: " + this.fechaHora.format(this.formatter) + ",\nEstatus: " + String.valueOf(this.estatus) + "\nID: " + String.valueOf(this.getId());
    }

    public static enum EstatusCita {
        PENDIENTE,
        REALIZADA;

        private EstatusCita() {
        }
    }
}
