package vallegrade.edu.pe;

import vallegrade.edu.pe.view.EstudianteView;

public class AppLauncher {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new EstudianteView().setVisible(true);
        });
    }
}
