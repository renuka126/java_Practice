import java.awt.*;
import javax.swing.*;

public class BresenhamCircle extends JPanel {

    private int xc = 250, yc = 250;
    private int r = 100;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = r;
        int d = 3 - (2 * r);

        while (x <= y) {
            drawCirclePoints(g, xc, yc, x, y);

            if (d < 0) {
                d = d + (4 * x) + 6;
            } else {
                y--;
                d = d + 4 * (x - y) + 10;
            }
            x++;
        }
    }

    void drawCirclePoints(Graphics g, int xc, int yc, int x, int y) {
        g.drawLine(xc + x, yc + y, xc + x, yc + y);
        g.drawLine(xc - x, yc + y, xc - x, yc + y);
        g.drawLine(xc + x, yc - y, xc + x, yc - y);
        g.drawLine(xc - x, yc - y, xc - x, yc - y);
        g.drawLine(xc + y, yc + x, xc + y, yc + x);
        g.drawLine(xc - y, yc + x, xc - y, yc + x);
        g.drawLine(xc + y, yc - x, xc + y, yc - x);
        g.drawLine(xc - y, yc - x, xc - y, yc - x);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Bresenham Circle Drawing Algorithm");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                BresenhamCircle circle = new BresenhamCircle();
                circle.setPreferredSize(new java.awt.Dimension(500, 500));
                frame.add(circle);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}