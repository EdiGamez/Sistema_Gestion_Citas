import java.time.LocalDateTime;

public class Cita {
    private String identificador;
    private LocalDateTime fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String identificador, LocalDateTime fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.identificador = identificador;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    @Override
    public String toString() {
        return "Cita{" +
                "identificador='" + identificador + '\'' +
                ", fechaHora=" + fechaHora +
                ", motivo='" + motivo + '\'' +
                ", doctor=" + doctor +
                ", paciente=" + paciente +
                '}';
    }
}