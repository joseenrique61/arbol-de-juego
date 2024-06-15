import javax.swing.*;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel panel1;
    private JLabel lbRonda;
    private JLabel lbPuntuacion1;
    private JLabel lbPuntuacion2;
    private JButton btnColaborar;
    private JButton btnNoColaborar;
    private JLabel lbJugador2;
    private JPanel panelGrafo;
    private Arbol arbol;

    private int ronda = 1;

    private final int cantidadRondas = 7;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        Ventana ventana = new Ventana();
        frame.setContentPane(ventana.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ventana.arbol = new Arbol(ventana.cantidadRondas);
    }

    public Ventana() {
        btnColaborar.addActionListener(e -> {
            arbol.anadirEleccion(true);
            if (++ronda == cantidadRondas) {
                btnColaborar.setEnabled(false);
                btnNoColaborar.setEnabled(false);
            }
        });

        btnNoColaborar.addActionListener(e -> {
            arbol.anadirEleccion(false);
            if (++ronda == cantidadRondas) {
                btnColaborar.setEnabled(false);
                btnNoColaborar.setEnabled(false);
            }
        });
    }
}
