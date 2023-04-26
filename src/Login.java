import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {
    private JTextField identificadorField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Administrador administrador;

    public Login(Administrador administrador) {
        this.administrador = administrador;
        setTitle("Iniciar sesión");
        setSize(300, 200);
        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(3, 2));
        add(panelCentral, BorderLayout.CENTER);

        panelCentral.add(new JLabel("Identificador:"));
        identificadorField = new JTextField();
        panelCentral.add(identificadorField);

        panelCentral.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        panelCentral.add(passwordField);

        loginButton = new JButton("Iniciar sesión");
        panelCentral.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificador = identificadorField.getText();
                String password = new String(passwordField.getPassword());

                if (identificador.equals(administrador.getIdentificador()) && administrador.validarAcceso(password)) {
                        JOptionPane.showMessageDialog(Login.this, "Acceso exitoso", "Iniciar sesión", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Credenciales incorrectas", "Iniciar sesión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}