import java.awt.EventQueue;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        final SistemaCitas sistemaCitas = new SistemaCitas();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login(sistemaCitas);
                    frame.setVisible(true);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }
            }
        });
    }
}
