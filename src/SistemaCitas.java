import java.util.ArrayList;

public class SistemaCitas implements IAlmacenable {
    private ArrayList<Doctor> listaDoctores;
    private ArrayList<Paciente> listaPacientes;
    private ArrayList<Cita> listaCitas;

    public SistemaCitas() {
        listaDoctores = new ArrayList<>();
        listaPacientes = new ArrayList<>();
        listaCitas = new ArrayList<>();
    }

    public void agregarDoctor(Doctor doctor) {
        listaDoctores.add(doctor);
    }

    public void agregarPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
    }

    public void agregarCita(Cita cita) {
        listaCitas.add(cita);
    }

    public void eliminarDoctor(String idDoctor) {

    }

    public void eliminarPaciente(String idPaciente) {

    }

    public void eliminarCita(String idCita) {

    }

    public Doctor buscarDoctor(String idDoctor) {
        for (Doctor doctor : listaDoctores) {
            if (doctor.getIdentificador().equals(idDoctor)) {
                return doctor;
            }
        }
        return null;
    }

    public Paciente buscarPaciente(String idPaciente) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getIdentificador().equals(idPaciente)) {
                return paciente;
            }
        }
        return null;
    }

    public Cita buscarCita(String idCita) {
        for (Cita cita : listaCitas) {
            if (cita.getIdentificador().equals(idCita)) {
                return cita;
            }
        }
        return null;
    }

    public void modificarCita(String idCita, Cita nuevaCita) {
        for (int i = 0; i < listaCitas.size(); i++) {
            if (listaCitas.get(i).getIdentificador().equals(idCita)) {
                listaCitas.set(i, nuevaCita);
                break;
            }
        }
    }

    @Override
    public void guardarDatos() {

    }

    @Override
    public void cargarDatos() {

    }
}