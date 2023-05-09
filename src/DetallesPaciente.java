
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class DetallesPaciente extends JDialog {
    private Paciente paciente;
    private SistemaCitas sistemaCitas;

    public DetallesPaciente(Paciente paciente, SistemaCitas sistemaCitas) {
        this.paciente = paciente;
        this.sistemaCitas = sistemaCitas;
        this.setTitle("Detalles del paciente");
        this.setSize(400, 300);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo((Component)null);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = 17;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(new JLabel("ID:"), constraints);
        constraints.gridx = 1;
        this.add(new JLabel(paciente.getId().toString()), constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(new JLabel("Nombre:"), constraints);
        constraints.gridx = 1;
        this.add(new JLabel(paciente.getNombre()), constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(new JLabel("Apellido:"), constraints);
        constraints.gridx = 1;
        this.add(new JLabel(paciente.getApellido()), constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        this.add(new JLabel("Citas:"), constraints);
        List<Cita> citasPaciente = sistemaCitas.obtenerCitasDePaciente(paciente);
        JList<Cita> listaCitas = new JList((Cita[])citasPaciente.toArray(new Cita[0]));
        JScrollPane scrollPane = new JScrollPane(listaCitas);
        constraints.gridy = 4;
        constraints.fill = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        this.add(scrollPane, constraints);
    }
}
