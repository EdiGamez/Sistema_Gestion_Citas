
import java.awt.Component;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class InterfazSistemaCitas extends JFrame {
    private SistemaCitas sistemaCitas;
    private Administrador administrador;

    public InterfazSistemaCitas(SistemaCitas sistemaCitas, Administrador administrador) {
        this.sistemaCitas = sistemaCitas;
        this.administrador = administrador;
        this.setTitle("Sistema de Gestión de Citas");
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 400, 100);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu pacientesMenu = new JMenu("Pacientes");
        JMenu doctoresMenu = new JMenu("Doctores");
        JMenu citasMenu = new JMenu("Citas");
        menuBar.add(pacientesMenu);
        menuBar.add(doctoresMenu);
        menuBar.add(citasMenu);
        String var10002 = administrador.getNombre();
        JLabel adminLabel = new JLabel("Bienvenido, " + var10002 + " " + administrador.getApellido());
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(adminLabel);
        JMenuItem agregarPaciente = new JMenuItem("Agregar paciente");
        JMenuItem buscarPaciente = new JMenuItem("Buscar paciente");
        JMenuItem agregarDoctor = new JMenuItem("Agregar doctor");
        JMenuItem buscarDoctor = new JMenuItem("Buscar doctor");
        JMenuItem agregarCita = new JMenuItem("Agregar cita");
        JMenuItem buscarCita = new JMenuItem("Buscar cita");
        new JMenuItem("Agregar administrador");
        pacientesMenu.add(agregarPaciente);
        pacientesMenu.add(buscarPaciente);
        doctoresMenu.add(agregarDoctor);
        doctoresMenu.add(buscarDoctor);
        citasMenu.add(agregarCita);
        citasMenu.add(buscarCita);
        agregarPaciente.addActionListener((e) -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del paciente:");
            if (nombre != null && apellido != null && !nombre.trim().isEmpty() && !apellido.trim().isEmpty()) {
                sistemaCitas.agregarPacienteYGuardar(nombre.trim(), apellido.trim());
                JOptionPane.showMessageDialog((Component)null, "Paciente agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog((Component)null, "No se pudo agregar el paciente. Por favor, complete todos los campos.");
            }

        });
        buscarPaciente.addActionListener((e) -> {
            String nombrePaciente = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
            String apellidoPaciente = JOptionPane.showInputDialog("Ingrese el apellido del paciente:");
            Paciente pacienteEncontrado = sistemaCitas.buscarPacientesPorNombre(nombrePaciente, apellidoPaciente);
            if (pacienteEncontrado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró un paciente con ese nombre y apellido.", "Búsqueda de paciente", 1);
            } else {
                DetallesPaciente detallesPaciente = new DetallesPaciente(pacienteEncontrado, sistemaCitas);
                detallesPaciente.setVisible(true);
            }

        });
        agregarDoctor.addActionListener((e) -> {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del médico:");
            String especialidad = JOptionPane.showInputDialog("Ingrese la especialidad del médico:");
            if (nombre != null && apellido != null && especialidad != null && !nombre.trim().isEmpty() && !apellido.trim().isEmpty() && !especialidad.trim().isEmpty()) {
                sistemaCitas.agregarMedicoYGuardar(nombre.trim(), apellido.trim(), especialidad.trim());
                JOptionPane.showMessageDialog((Component)null, "Médico agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog((Component)null, "No se pudo agregar el médico. Por favor, complete todos los campos.");
            }

        });
        buscarDoctor.addActionListener((e) -> {
            String nombreMedico = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
            String apellidoMedico = JOptionPane.showInputDialog("Ingrese el apellido del médico:");
            Medico medicosEncontrados = sistemaCitas.buscarMedicosPorNombre(nombreMedico, apellidoMedico);
            if (medicosEncontrados == null) {
                JOptionPane.showMessageDialog(this, "No se encontraron médicos con ese nombre y apellido.", "Búsqueda de médico", 1);
            } else {
                DetallesMedico detallesPaciente = new DetallesMedico(medicosEncontrados, sistemaCitas);
                detallesPaciente.setVisible(true);
            }

        });
        agregarCita.addActionListener((e) -> {
            String nombrePaciente = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
            String apellidoPaciente = JOptionPane.showInputDialog("Ingrese el apellido del paciente:");
            String nombreMedico = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
            String apellidoMedico = JOptionPane.showInputDialog("Ingrese el apellido del médico:");
            String asuntoCita = JOptionPane.showInputDialog("Ingrese el asunto de la cita:");
            JComboBox<Integer> comboDia = new JComboBox();

            for(int i = 1; i <= 30; ++i) {
                comboDia.addItem(i);
            }

            JComboBox<Integer> comboMes = new JComboBox();

            for(int ix = 1; ix <= 12; ++ix) {
                comboMes.addItem(ix);
            }

            JComboBox<Integer> comboAnio = new JComboBox();

            for(int ixx = 2023; ixx <= 2025; ++ixx) {
                comboAnio.addItem(ixx);
            }

            JSpinner hora = new JSpinner(new SpinnerDateModel());
            hora.setEditor(new JSpinner.DateEditor(hora, "HH:mm"));
            JPanel panelFechaHora = new JPanel();
            panelFechaHora.add(comboDia);
            panelFechaHora.add(comboMes);
            panelFechaHora.add(comboAnio);
            panelFechaHora.add(hora);
            int opcion = JOptionPane.showConfirmDialog((Component)null, panelFechaHora, "Seleccione fecha y hora", 2);
            if (opcion == 0) {
                int diaSeleccionado = (Integer)comboDia.getSelectedItem();
                int mesSeleccionado = (Integer)comboMes.getSelectedItem();
                int anioSeleccionado = (Integer)comboAnio.getSelectedItem();
                Date horaSeleccionada = (Date)hora.getValue();
                LocalDateTime fechaHora = LocalDateTime.of(anioSeleccionado, mesSeleccionado, diaSeleccionado, horaSeleccionada.getHours(), horaSeleccionada.getMinutes());
                if (nombrePaciente != null && apellidoPaciente != null && !nombrePaciente.trim().isEmpty() && !apellidoPaciente.trim().isEmpty() && nombreMedico != null && apellidoMedico != null && !nombreMedico.trim().isEmpty() && !apellidoMedico.trim().isEmpty()) {
                    Paciente paciente = sistemaCitas.buscarPacientePorNombre(nombrePaciente.trim(), apellidoPaciente.trim());
                    Medico medico = sistemaCitas.buscarMedicoPorNombre(nombreMedico.trim(), apellidoMedico.trim());
                    if (paciente != null && medico != null) {
                        if (!sistemaCitas.citaExistente(medico, fechaHora)) {
                            Cita cita = new Cita(paciente, medico, fechaHora,asuntoCita);
                            sistemaCitas.agregarCita(paciente, medico, fechaHora,asuntoCita);

                            JOptionPane.showMessageDialog((Component) null, "Cita creada exitosamente.");
                        } else {
                            JOptionPane.showMessageDialog((Component) null, "El médico ya tiene una cita programada en esa fecha y hora.");
                        }
                    } else {
                        JOptionPane.showMessageDialog((Component) null, "No se pudo crear la cita. Por favor, verifique los datos del paciente y médico.");
                    }
                } else {
                    JOptionPane.showMessageDialog((Component) null, "No se pudo crear la cita. Por favor, complete todos los campos.");
                }} else if (opcion == 2) {
                JOptionPane.showMessageDialog((Component) null, "Creación de cita cancelada.");
            }
        });
        buscarCita.addActionListener((e) -> {
            String nombrePaciente = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
            String apellidoPaciente = JOptionPane.showInputDialog("Ingrese el apellido del paciente:");
            String nombreDoctor = JOptionPane.showInputDialog("Ingrese el nombre del doctor:");
            String apellidoDoctor = JOptionPane.showInputDialog("Ingrese el apellido del doctor:");
            List<Cita> citasEncontradas = sistemaCitas.buscarCitasPorNombres(nombrePaciente, apellidoPaciente, nombreDoctor, apellidoDoctor);
            if (citasEncontradas != null) {
                StringBuilder resultado = new StringBuilder("Cita encontradas:\n");
                Iterator var8 = citasEncontradas.iterator();

                while(var8.hasNext()) {
                    Cita c = (Cita)var8.next();
                    resultado.append(c.toString());
                    resultado.append("\n--------------------------------\n");
                }

                JOptionPane.showMessageDialog((Component)null, resultado.toString());
            } else {
                JOptionPane.showMessageDialog((Component)null, "No se encontraron citas con ese paciente y doctor.");
            }

        });
    }
}
