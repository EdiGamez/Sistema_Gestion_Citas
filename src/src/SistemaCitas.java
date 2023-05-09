import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class SistemaCitas implements AlmacenamientoDatos {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Cita> citas;

    private List<Administrador> administradores = new ArrayList<>();

    // Archivos para guardar datos
    private static final String PACIENTES_CSV = "db/pacientes.csv";
    private static final String MEDICOS_CSV = "db/medicos.csv";
    private static final String CITAS_CSV = "db/citas.csv";

    public SistemaCitas() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        citas = new ArrayList<>();
        try {
            cargarDatos();
        } catch (Exception e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
        }
    }
    public void agregarPacienteYGuardar(String nombre, String apellido) {
        Paciente paciente = new Paciente(nombre, apellido);
        pacientes.add(paciente);
        try {
            guardarPacientesEnCSV(paciente);
        } catch (IOException e) {
            System.err.println("Error al guardar el paciente en el archivo .csv: " + e.getMessage());
        }
    }
    public void agregarMedicoYGuardar(String nombre, String apellido, String especialidad) {
        Medico medico = new Medico(nombre, apellido,especialidad);
        medicos.add(medico);
        try {
            guardarMedicosEnCSV(medico);
        } catch (IOException e) {
            System.err.println("Error al guardar el paciente en el archivo .csv: " + e.getMessage());
        }
    }
    public void agregarCita(Paciente paciente, Medico medico, LocalDateTime fechaHora) {
        Cita cita = new Cita(paciente, medico, fechaHora);
        citas.add(cita);
        try {
            guardarCitasEnCSV(cita);
        } catch (IOException e) {
            System.err.println("Error al guardar citas en archivo: " + e.getMessage());
        }
    }
    public Paciente buscarPacientePorNombre(String nombre, String apellido) {
        return pacientes.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre) && p.getApellido().equalsIgnoreCase(apellido))
                .findFirst()
                .orElse(null);
    }
    public Medico buscarMedicoPorNombre(String nombre, String apellido) {
        return medicos.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombre) && m.getApellido().equalsIgnoreCase(apellido))
                .findFirst()
                .orElse(null);
    }
    public Paciente buscarPacientePorId(UUID id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(id)) {
                return paciente;
            }
        }
        return null;
    }
    public Medico buscarMedicoPorId(UUID id) {
        for (Medico medico : medicos) {
            if (medico.getId().equals(id)) {
                return medico;
            }
        }
        return null;
    }
    public List<Cita> obtenerCitasDePaciente(Paciente paciente) {
        List<Cita> citasPaciente = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getPaciente().equals(paciente)) {
                citasPaciente.add(cita);
            }
        }
        return citasPaciente;
    }
    public List<Cita> obtenerCitasDeMedico(Medico medico) {
        List<Cita> citasMedico = new ArrayList<>();

        for (Cita cita : citas) {
            if (cita.getMedico().equals(medico)) {
                citasMedico.add(cita);
            }
        }
        return citasMedico;
    }
    public Administrador validarAdministrador(String usuario, String contrasena) {
        for (Administrador admin : administradores) {
            if (admin.getUsuario().equals(usuario) && admin.getContrasena().equals(contrasena)) {
                return admin;
            }
        }
        return null;
    }
    public Paciente buscarPacientesPorNombre(String nombre, String apellido) {
        List<Paciente> pacientesEncontrados = new ArrayList<>();

        for (Paciente paciente : pacientes) {
            if (paciente.getNombre().equalsIgnoreCase(nombre) &&
                    paciente.getApellido().equalsIgnoreCase(apellido)) {
                pacientesEncontrados.add(paciente);
            }
        }

        return pacientesEncontrados.get(0);
    }
    public Medico buscarMedicosPorNombre(String nombre, String apellido) {

        List<Medico> medicosEncontrados = new ArrayList<>();

        for (Medico medico : medicos) {
            if (medico.getNombre().equalsIgnoreCase(nombre) &&
                    medico.getApellido().equalsIgnoreCase(apellido)) {
                medicosEncontrados.add(medico);
            }
        }

        return medicosEncontrados.get(0);
    }
    public List<Cita> buscarCitasPorNombres(String nombrePaciente, String apellidoPaciente, String nombreDoctor, String apellidoDoctor) {
        List<Cita> citasEncontradas = new ArrayList<>();

        for (Cita cita : citas) {
            Paciente paciente = cita.getPaciente();
            Medico medico = cita.getMedico();

            if (paciente.getNombre().equalsIgnoreCase(nombrePaciente) &&
                    paciente.getApellido().equalsIgnoreCase(apellidoPaciente) &&
                    medico.getNombre().equalsIgnoreCase(nombreDoctor) &&
                    medico.getApellido().equalsIgnoreCase(apellidoDoctor)) {
                citasEncontradas.add(cita);
            }
        }

        return citasEncontradas;
    }
    @Override
    public void cargarDatos() {
        try {
            cargarPacientes();
            cargarMedicos();
            cargarCitas();
            cargarAdministradores();
        } catch (IOException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
        }
    }
    @Override
    public void guardarDatos() {
        try {
            for (Paciente paciente : pacientes) {
                guardarPacientesEnCSV(paciente);
            }
            for (Medico medico : medicos) {
                guardarMedicosEnCSV(medico);
            }
            for (Cita cita : citas){
                guardarCitasEnCSV(cita);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar datos en archivos .csv: " + e.getMessage());
        }
    }
    // Métodos para cargar datos desde archivos CSV
    private void cargarPacientes() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PACIENTES_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    UUID pacienteId = UUID.fromString(data[0]);
                    pacientes.add(new Paciente(pacienteId, data[1], data[2]));
                } catch (IllegalArgumentException e) {
                    System.err.println("UUID no válido en el archivo pacientes.csv: " + data[0]);
                }
            }
        }
    }
    private void cargarMedicos() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(MEDICOS_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    UUID medicoId = UUID.fromString(data[0]);
                    medicos.add(new Medico(medicoId, data[1], data[2],data[3]));
                } catch (IllegalArgumentException e) {
                    System.err.println("UUID no válido en el archivo medicos.csv: " + data[0]);
                }
            }
        }
    }
    private void cargarCitas() throws IOException {
        if (Files.exists(Paths.get(CITAS_CSV))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(CITAS_CSV))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    UUID citaID = UUID.fromString(data[0]);
                    UUID pacienteID = UUID.fromString(data[1]);
                    UUID medicoID = UUID.fromString(data[2]);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime fechaHora = LocalDateTime.parse(data[3], formatter);
                    Cita.EstatusCita estatus = Cita.EstatusCita.valueOf(data[4]);

                    Paciente paciente = buscarPacientePorId(pacienteID);
                    Medico medico = buscarMedicoPorId(medicoID);
                    if (paciente != null && medico != null) {
                        citas.add(new Cita(citaID, paciente, medico, fechaHora, estatus));
                    }
                }
            }
        }
    }
    public void guardarPacientesEnCSV(Paciente paciente) throws IOException {
        FileWriter writer = new FileWriter(PACIENTES_CSV, true); // Cambiar a true para agregar pacientes en lugar de sobrescribir
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(paciente.toCSV());
        pw.flush();
        pw.close();
    }
    private void guardarMedicosEnCSV(Medico medico) throws IOException {
        FileWriter writer = new FileWriter(MEDICOS_CSV, true); // Cambiar a true para agregar pacientes en lugar de sobrescribir
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(medico.toCSV());
        pw.flush();
        pw.close();
    }
    public void guardarCitasEnCSV(Cita cita) throws IOException {
        FileWriter writer = new FileWriter(CITAS_CSV, true); // Cambiar a true para agregar pacientes en lugar de sobrescribir
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(cita.toCSV());
        pw.flush();
        pw.close();
    }
    public void cargarAdministradores() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("db/administradores.csv"))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                UUID id = UUID.fromString(data[0]);
                String nombre = data[1];
                String apellido = data[2];
                String usuario = data[3];
                String contrasena = data[4];
                administradores.add(new Administrador(nombre, apellido, usuario, contrasena));
            }
        }
    }
}