import javax.swing.*;
import java.awt.*;

public class Lienzo extends JPanel {
    private final boolean[] arbol;
    private final int niveles;

    private int nivel = 1;

    public Lienzo(int niveles) {
        this.niveles = niveles;
        arbol = new boolean[(int)Math.pow(2, niveles) - 1];
        arbol[0] = true;
    }

    public void anadirEleccion(boolean eleccion) {
        int indice = 0;
        int vistos = 0;
        for (int i = 0; i < arbol.length; i++) {
            if (arbol[i]) {
                vistos++;
            }

            if (vistos == nivel) {
                indice = i;
                break;
            }
        }

        int nuevoIndice = indice * 2 + 1;
        if (!eleccion) {
            nuevoIndice++;
        }

        arbol[nuevoIndice] = true;

        nivel++;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.gray);
        int margen = 20;

        for (int i = 0; i < arbol.length; i++) {
            int nivelActual = (int)Math.floor(Math.log(i + 1) / Math.log(2)) + 1;
            Rectangle rectangle = getDimensionDeNodo(i, margen, nivelActual);

            if (nivelActual != niveles) {
                int hijo1 = i * 2 + 1;
                int hijo2 = i * 2 + 2;
                Rectangle rectangleHijo1 = getDimensionDeNodo(hijo1, margen, nivelActual + 1);
                Rectangle rectangleHijo2 = getDimensionDeNodo(hijo2, margen, nivelActual + 1);

                Color colorActual = g.getColor();
                g.setColor(Color.gray);

                g.drawLine(rectangleHijo1.x + rectangleHijo1.width / 2, rectangleHijo1.y + rectangleHijo1.height / 2, rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);
                g.drawLine(rectangleHijo2.x + rectangleHijo2.width / 2, rectangleHijo2.y + rectangleHijo2.height / 2, rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);
                g.setColor(colorActual);

                if (arbol[i]){
                    int siguiente = getSiguiente(i);
                    if (siguiente != -1) {
                        Color colorActual1 = g.getColor();
                        g.setColor(Color.green);
                        Rectangle siguienteRectangle = getDimensionDeNodo(siguiente, margen, nivelActual + 1);
                        g.drawLine(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2, siguienteRectangle.x + siguienteRectangle.width / 2, siguienteRectangle.y + siguienteRectangle.width / 2);

                        g.setColor(colorActual1);
                    }
                }
            }

            if (arbol[i]) {
                Color colorActual = g.getColor();
                g.setColor(Color.green);
                g.fillOval(rectangle.x - 2, rectangle.y - 2, rectangle.width + 4, rectangle.height + 4);
                g.setColor(colorActual);
            }

            g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            g.setColor(i % 2 == 0 ? Color.blue : Color.red);
        }
    }

    private int getSiguiente(int i) {
        for (int j = i + 1; j < arbol.length; j++) {
            if (arbol[j]) {
                return j;
            }
        }
        return -1;
    }

    private Rectangle getDimensionDeNodo(int i, int margen, int nivelActual) {
        int base = (int)Math.pow(2, nivelActual - 1) - 1;
        return new Rectangle((this.getWidth() - margen) * ((i - base + 1) * 2 - 1) / (int)Math.pow(2,  nivelActual), nivelActual * margen, 15, 15);
    }
}
