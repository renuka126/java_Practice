import java.awt.*;
import javax.swing.*;

public class CohenSutherlandClip extends JPanel {
    // Region codes
    final int INSIDE = 0; // 0000
    final int LEFT   = 1; // 0001
    final int RIGHT  = 2; // 0010
    final int BOTTOM = 4; // 0100
    final int TOP    = 8; // 1000

    // Clipping window boundaries
    final int x_min = 100;
    final int y_min = 100;
    final int x_max = 300;
    final int y_max = 300;

    // Line endpoints
    int x1 = 50, y1 = 250, x2 = 350, y2 = 150;

    // Compute region code for a point (x, y)
    private int computeCode(double x, double y) {
        int code = INSIDE;

        if (x < x_min)      code |= LEFT;
        else if (x > x_max) code |= RIGHT;
        if (y < y_min)      code |= BOTTOM;
        else if (y > y_max) code |= TOP;

        return code;
    }

    // Cohen-Sutherland clipping algorithm
    private void cohenSutherlandClip(Graphics g) {
        double x1d = x1, y1d = y1, x2d = x2, y2d = y2;

        int code1 = computeCode(x1d, y1d);
        int code2 = computeCode(x2d, y2d);

        boolean accept = false;

        while (true) {
            if ((code1 | code2) == 0) {
                // Both points inside
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                // Both points outside in same region
                break;
            } else {
                // Line needs clipping
                double x = 0, y = 0;
                int code_out = (code1 != 0) ? code1 : code2;

                if ((code_out & TOP) != 0) {
                    x = x1d + (x2d - x1d) * (y_max - y1d) / (y2d - y1d);
                    y = y_max;
                } else if ((code_out & BOTTOM) != 0) {
                    x = x1d + (x2d - x1d) * (y_min - y1d) / (y2d - y1d);
                    y = y_min;
                } else if ((code_out & RIGHT) != 0) {
                    y = y1d + (y2d - y1d) * (x_max - x1d) / (x2d - x1d);
                    x = x_max;
                } else if ((code_out & LEFT) != 0) {
                    y = y1d + (y2d - y1d) * (x_min - x1d) / (x2d - x1d);
                    x = x_min;
                }

                if (code_out == code1) {
                    x1d = x;
                    y1d = y;
                    code1 = computeCode(x1d, y1d);
                } else {
                    x2d = x;
                    y2d = y;
                    code2 = computeCode(x2d, y2d);
                }
            }
        }

        // Draw results
        g.setColor(Color.BLACK);
        g.drawRect(x_min, y_min, x_max - x_min, y_max - y_min);
        g.setColor(Color.RED);
        g.drawLine(x1, y1, x2, y2); // Original line

        if (accept) {
            g.setColor(Color.GREEN);
            g.drawLine((int)x1d, (int)y1d, (int)x2d, (int)y2d); // Clipped line
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        cohenSutherlandClip(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cohen–Sutherland Line Clipping Algorithm");
        CohenSutherlandClip panel = new CohenSutherlandClip();
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}