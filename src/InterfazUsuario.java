import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class InterfazUsuario {
    private SistemaCitas sistemaCitas;
    private JFrame frame;
    private Login login;
    public InterfazUsuario(SistemaCitas sistemaCitas,Administrador administrador) {
        this.sistemaCitas = sistemaCitas;
        frame = new JFrame("Sistema de Citas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(7, 1));

        JButton crearDoctorBtn = new JButton("Crear Doctor");
        panelCentral.add(crearDoctorBtn);

        JButton crearPacienteBtn = new JButton("Crear Paciente");
        panelCentral.add(crearPacienteBtn);

        JButton crearCitaBtn = new JButton("Crear Cita");
        panelCentral.add(crearCitaBtn);

        JButton eliminarDoctorBtn = new JButton("Eliminar Doctor");
        panelCentral.add(eliminarDoctorBtn);

        JButton eliminarPacienteBtn = new JButton("Eliminar Paciente");
        panelCentral.add(eliminarPacienteBtn);

        JButton eliminarCitaBtn = new JButton("Eliminar Cita");
        panelCentral.add(eliminarCitaBtn);

        JButton salirBtn = new JButton("Salir");
        panelCentral.add(salirBtn);

        frame.add(panelCentral, BorderLayout.CENTER);

        login = new Login(administrador);
        login.setModal(true);
        login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }

public void mostrarMenu() {
        login.setVisible(true);
    frame.setVisible(true);
        }

public void crearDoctor() {

        }

public void crearPaciente() {

        }

public void crearCita() {

        }

public void eliminarDoctor() {

        }

public void eliminarPaciente() {

        }

public void eliminarCita() {

        }

public boolean iniciarSesion(String contraseña) {

        return false;
        }
}