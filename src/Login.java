import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private SistemaCitas sistemaCitas;

    public Login(final SistemaCitas sistemaCitas) {
        this.sistemaCitas = sistemaCitas;
        this.setTitle("Login");
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager)null);
        JLabel lblUsername = new JLabel("Usuario:");
        lblUsername.setBounds(110, 87, 61, 16);
        this.contentPane.add(lblUsername);
        this.textFieldUsername = new JTextField();
        this.textFieldUsername.setBounds(183, 82, 130, 26);
        this.contentPane.add(this.textFieldUsername);
        this.textFieldUsername.setColumns(10);
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(110, 125, 77, 16);
        this.contentPane.add(lblPassword);
        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(183, 120, 130, 26);
        this.contentPane.add(this.passwordField);
        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setBounds(183, 158, 130, 29);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = Login.this.textFieldUsername.getText();
                String password = String.valueOf(Login.this.passwordField.getPassword());
                Administrador admin = sistemaCitas.validarAdministrador(username, password);
                if (admin != null) {
                    InterfazSistemaCitas menuPrincipal = new InterfazSistemaCitas(sistemaCitas, admin);
                    menuPrincipal.setVisible(true);
                    Login.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Usuario o contraseña incorrectos.", "Error", 0);
                }

            }
        });
        this.contentPane.add(loginButton);
    }
}
