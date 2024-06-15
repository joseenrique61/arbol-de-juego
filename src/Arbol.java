import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.awt.*;

public class Arbol {
    private final Lienzo lienzo;

    public Arbol(int niveles) {
        lienzo = new Lienzo(niveles);

        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(this.lienzo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        lienzo.setBackground(new Color(43, 45, 48));
        frame.setVisible(true);
    }

    public void anadirEleccion(boolean eleccion) {
        lienzo.anadirEleccion(eleccion);
    }
}
