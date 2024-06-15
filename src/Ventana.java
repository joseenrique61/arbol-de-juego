import javax.swing.*;
import java.util.Random;

public class Ventana {
    private JPanel panel1;
    private JLabel lbRonda;
    private JLabel lbPuntuacion1;
    private JLabel lbPuntuacion2;
    private JButton btnColaborar;
    private JButton btnNoColaborar;
    private JLabel lbJugador2;
    private Arbol arbol;

    private int ronda = 1;
    private final int cantidadRondas = 5;
    private int puntos1 = 0;
    private int puntos2 = 0;

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
        btnColaborar.addActionListener(e -> tomarEleccion(true));

        btnNoColaborar.addActionListener(e -> tomarEleccion(false));
    }

    private void tomarEleccion(boolean eleccion) {
        arbol.anadirEleccion(eleccion);

        boolean eleccion2 = getEleccion2();
        if (eleccion2) {
            lbJugador2.setText("El Jugador 2 ha decidido colaborar.");
        }
        else {
            lbJugador2.setText("El Jugador 2 ha decidido no colaborar.");
        }

        if (eleccion && eleccion2) {
            puntos1 += 3;
            puntos2 += 3;
        }
        else if (eleccion && !eleccion2) {
            puntos2 += 5;
        }
        else if (!eleccion && eleccion2) {
            puntos1 += 5;
        }
        else {
            puntos1 += 1;
            puntos2 += 1;
        }

        lbPuntuacion1.setText("Puntuación Jugador 1: " + puntos1);
        lbPuntuacion2.setText("Puntuación Jugador 2: " + puntos2);

        actualizarRonda();
    }

    private boolean getEleccion2() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private void actualizarRonda() {
        ronda++;
        lbRonda.setText("Ronda: " + ronda);
        if (ronda == cantidadRondas) {
            btnColaborar.setEnabled(false);
            btnNoColaborar.setEnabled(false);

            if (puntos1 > puntos2) {
                JOptionPane.showMessageDialog(null, "El Jugador 1 ha ganado con " + (puntos1 - puntos2) + " puntos extra.");
            }
            else if (puntos1 < puntos2) {
                JOptionPane.showMessageDialog(null, "El Jugador 2 ha ganado con " + (puntos2 - puntos1) + " puntos extra.");
            }
            else {
                JOptionPane.showMessageDialog(null, "Ha habido un empate.");
            }
        }
    }
}
