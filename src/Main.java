public class Main {
    public static void main(String[] args) {
        SistemaCitas sistemaCitas = new SistemaCitas();
        Administrador administrador = new Administrador("admin", "Administrador", "contraseña123");
        InterfazUsuario interfazUsuario = new InterfazUsuario(sistemaCitas,administrador);
        interfazUsuario.mostrarMenu();
    }
}