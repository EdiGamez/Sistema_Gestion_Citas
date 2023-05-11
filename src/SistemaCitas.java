import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class SistemaCitas implements AlmacenamientoDatos {
    private List<Paciente> pacientes = new ArrayList();
    private List<Medico> medicos = new ArrayList();
    private List<Cita> citas = new ArrayList();
    private List<Administrador> administradores = new ArrayList();

    public SistemaCitas() {
        try {
            this.cargarDatos();
        } catch (Exception var2) {
            System.err.println("Error al cargar datos: " + var2.getMessage());
        }

    }

    public void agregarPacienteYGuardar(String nombre, String apellido) {
        Paciente paciente = new Paciente(nombre, apellido);
        this.pacientes.add(paciente);

        try {
            this.guardarPacientesEnCSV(paciente);
        } catch (IOException var5) {
            System.err.println("Error al guardar el paciente en el archivo .csv: " + var5.getMessage());
        }

    }

    public void agregarMedicoYGuardar(String nombre, String apellido, String especialidad) {
        Medico medico = new Medico(nombre, apellido, especialidad);
        this.medicos.add(medico);

        try {
            this.guardarMedicosEnCSV(medico);
        } catch (IOException var6) {
            System.err.println("Error al guardar el paciente en el archivo .csv: " + var6.getMessage());
        }

    }

    public void agregarCita(Paciente paciente, Medico medico, LocalDateTime fechaHora,String asunto) {
        // Verificar si ya existe una cita con el mismo médico en la misma fecha y hora
        if (citaExistente(medico, fechaHora)) {
            System.out.println("El médico ya tiene una cita programada en la misma fecha y hora.");
            return;
        }
        try {
            this.guardarCitasEnCSV(new Cita(paciente,medico,fechaHora,asunto));
            this.citas.add(new Cita(paciente,medico,fechaHora,asunto));
        } catch (IOException var6) {
            System.err.println("Error al guardar citas en archivo: " + var6.getMessage());
        }
    }
        public boolean citaExistente(Medico medico, LocalDateTime fechaHora) {
            return this.citas.stream().anyMatch(cita -> cita.getMedico().equals(medico) && cita.getFechaHora().equals(fechaHora));
        }

    public Paciente buscarPacientePorNombre(String nombre, String apellido) {
        return (Paciente)this.pacientes.stream().filter((p) -> {
            return p.getNombre().equalsIgnoreCase(nombre) && p.getApellido().equalsIgnoreCase(apellido);
        }).findFirst().orElse((null));
    }

    public Medico buscarMedicoPorNombre(String nombre, String apellido) {
        return (Medico)this.medicos.stream().filter((m) -> {
            return m.getNombre().equalsIgnoreCase(nombre) && m.getApellido().equalsIgnoreCase(apellido);
        }).findFirst().orElse((null));
    }

    public Paciente buscarPacientePorId(UUID id) {
        Iterator var2 = this.pacientes.iterator();

        Paciente paciente;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            paciente = (Paciente)var2.next();
        } while(!paciente.getId().equals(id));

        return paciente;
    }

    public Medico buscarMedicoPorId(UUID id) {
        Iterator var2 = this.medicos.iterator();

        Medico medico;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            medico = (Medico)var2.next();
        } while(!medico.getId().equals(id));

        return medico;
    }

    public List<Cita> obtenerCitasDePaciente(Paciente paciente) {
        List<Cita> citasPaciente = new ArrayList();
        Iterator var3 = this.citas.iterator();

        while(var3.hasNext()) {
            Cita cita = (Cita)var3.next();
            if (cita.getPaciente().equals(paciente)) {
                citasPaciente.add(cita);
            }
        }

        return citasPaciente;
    }

    public List<Cita> obtenerCitasDeMedico(Medico medico) {
        List<Cita> citasMedico = new ArrayList();
        Iterator var3 = this.citas.iterator();

        while(var3.hasNext()) {
            Cita cita = (Cita)var3.next();
            if (cita.getMedico().equals(medico)) {
                citasMedico.add(cita);
            }
        }

        return citasMedico;
    }

    public Administrador validarAdministrador(String usuario, String contrasena) {
        Iterator var3 = this.administradores.iterator();

        Administrador admin;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            admin = (Administrador)var3.next();
        } while(!admin.getUsuario().equals(usuario) || !admin.getContrasena().equals(contrasena));

        return admin;
    }

    public Paciente buscarPacientesPorNombre(String nombre, String apellido) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNombre().equalsIgnoreCase(nombre) && paciente.getApellido().equalsIgnoreCase(apellido)) {
                return paciente;
            }
        }
        return null; // Devuelve null si no se encuentra ningún paciente con ese nombre y apellido
    }

    public Medico buscarMedicosPorNombre(String nombre, String apellido) {
        List<Medico> medicosEncontrados = new ArrayList();
        Iterator var4 = this.medicos.iterator();

        while(var4.hasNext()) {
            Medico medico = (Medico)var4.next();
            if (medico.getNombre().equalsIgnoreCase(nombre) && medico.getApellido().equalsIgnoreCase(apellido)) {
                medicosEncontrados.add(medico);
            }
        }

        return (Medico)medicosEncontrados.get(0);
    }

    public List<Cita> buscarCitasPorNombres(String nombrePaciente, String apellidoPaciente, String nombreDoctor, String apellidoDoctor) {
        List<Cita> citasEncontradas = new ArrayList();
        Iterator var6 = this.citas.iterator();

        while(var6.hasNext()) {
            Cita cita = (Cita)var6.next();
            Paciente paciente = cita.getPaciente();
            Medico medico = cita.getMedico();
            if (paciente.getNombre().equalsIgnoreCase(nombrePaciente) && paciente.getApellido().equalsIgnoreCase(apellidoPaciente) && medico.getNombre().equalsIgnoreCase(nombreDoctor) && medico.getApellido().equalsIgnoreCase(apellidoDoctor)) {
                citasEncontradas.add(cita);
            }
        }

        return citasEncontradas;
    }

    public void cargarDatos() {
        try {
            this.cargarPacientes();
            this.cargarMedicos();
            this.cargarCitas();
            this.cargarAdministradores();
        } catch (IOException var2) {
            System.err.println("Error al cargar datos: " + var2.getMessage());
        }

    }

    public void guardarDatos() {
        try {
            Iterator var1 = this.pacientes.iterator();

            while(var1.hasNext()) {
                Paciente paciente = (Paciente)var1.next();
                this.guardarPacientesEnCSV(paciente);
            }

            var1 = this.medicos.iterator();

            while(var1.hasNext()) {
                Medico medico = (Medico)var1.next();
                this.guardarMedicosEnCSV(medico);
            }

            var1 = this.citas.iterator();

            while(var1.hasNext()) {
                Cita cita = (Cita)var1.next();
                this.guardarCitasEnCSV(cita);
            }
        } catch (IOException var3) {
            System.err.println("Error al guardar datos en archivos .csv: " + var3.getMessage());
        }

    }


    private void cargarPacientes() throws IOException {
        File dbDirectory = new File("db");
        if (!dbDirectory.exists()) {
            dbDirectory.mkdir();
        }

        File file = new File("db/pacientes.csv");

        if (!file.exists()) {
            // Copia el archivo pacientes.csv del .jar al archivo externo
            copyFileFromJar("db/pacientes.csv", file);
        }

        BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));String line;
        try {
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                try {
                    UUID pacienteId = UUID.fromString(data[0]);
                    this.pacientes.add(new Paciente(pacienteId, data[1], data[2]));
                } catch (IllegalArgumentException var6) {
                    System.err.println("UUID no válido en el archivo pacientes.csv: " + data[0]);
                }
            }
        } catch (Throwable var7) {
            try {
                reader.close();
            } catch (Throwable var5) {
                var7.addSuppressed(var5);
            }

            throw var7;
        }

        reader.close();
    }

    private void cargarMedicos() throws IOException {
        File medicosFile = new File("db/medicos.csv");
        if (!medicosFile.exists()) {
            medicosFile.getParentFile().mkdirs();
            Files.copy(SistemaCitas.class.getResourceAsStream("/db/medicos.csv"), medicosFile.toPath());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(medicosFile, StandardCharsets.UTF_8))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                try {
                    UUID medicoId = UUID.fromString(data[0]);
                    this.medicos.add(new Medico(medicoId, data[1], data[2], data[3]));
                } catch (IllegalArgumentException var6) {
                    System.err.println("UUID no válido en el archivo medicos.csv: " + data[0]);
                }
            }
        }
    }

    private void cargarCitas() throws IOException {
        File citasFile = new File("db/citas.csv");
        if (!citasFile.exists()) {
            citasFile.getParentFile().mkdirs();
            Files.copy(SistemaCitas.class.getResourceAsStream("/db/citas.csv"), citasFile.toPath());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(citasFile, StandardCharsets.UTF_8))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                UUID citaID = UUID.fromString(data[0]);
                UUID pacienteID = UUID.fromString(data[1]);
                UUID medicoID = UUID.fromString(data[2]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime fechaHora = LocalDateTime.parse(data[3], formatter);
                String asunto = data[4];
                Paciente paciente = this.buscarPacientePorId(pacienteID);
                Medico medico = this.buscarMedicoPorId(medicoID);
                if (paciente != null && medico != null) {
                    this.citas.add(new Cita(citaID, paciente, medico, fechaHora, asunto));
                }
            }
        }
    }

    public void guardarPacientesEnCSV(Paciente paciente) throws IOException {
        FileWriter writer = new FileWriter("db/pacientes.csv", true);
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter pw = new PrintWriter(bw);
        pw.println(paciente.toCSV());
        pw.flush();
        pw.close();
    }

    private void guardarMedicosEnCSV(Medico medico) throws IOException {
        FileWriter writer = new FileWriter("db/medicos.csv", true);
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter pw = new PrintWriter(bw);
        pw.println(medico.toCSV());
        pw.flush();
        pw.close();
    }

    public void guardarCitasEnCSV(Cita cita) throws IOException {
        FileWriter writer = new FileWriter("db/citas.csv", true);
        BufferedWriter bw = new BufferedWriter(writer);
        PrintWriter pw = new PrintWriter(bw);
        pw.println(cita.toCSV());
        pw.flush();
        pw.close();
    }


    public void cargarAdministradores() throws IOException {
        ClassLoader classLoader = SistemaCitas.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("db/administradores.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        try {
            reader.readLine();
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                UUID id = UUID.fromString(data[0]);
                String nombre = data[1];
                String apellido = data[2];
                String usuario = data[3];
                String contrasena = data[4];
                this.administradores.add(new Administrador(nombre, apellido, usuario, contrasena));
            }
        } catch (Throwable var10) {
            try {
                reader.close();
            } catch (Throwable var9) {
                var10.addSuppressed(var9);
            }

            throw var10;
        }

        reader.close();
    }

    private void copyFileFromJar(String sourcePath, File destination) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(sourcePath);
        Files.copy(inputStream, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}

