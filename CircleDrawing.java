import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class CircleDrawing extends JPanel {
    private  int radius ;
    private int centerX, centerY;
    private String algorithm;
    private String style;

    public CircleDrawing(int radius, int centerX, int centerY, String algorithm, String style) {
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        this.algorithm = algorithm;
        this.style = style;
        // ensure the panel has a reasonable size
        this.setPreferredSize(new Dimension(600, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (algorithm.toLowerCase()) {
            case "dda":
                drawDDACircle(g, centerX, centerY, radius);
                break;
            case "bresenham":
                drawBresenhamCircle(g, centerX, centerY, radius);
                break;
            case "midpoint":
                drawMidpointCircle(g, centerX, centerY, radius);
                break;
            default:
                g.drawString("Invalid Algorithm!", 20, 20);
        }
    }

    // Utility to apply style
    private void plot(Graphics g, int x, int y, int count) {
        if (style.equalsIgnoreCase("solid")) {
            g.fillRect(x, y, 1, 1);
        } else if (style.equalsIgnoreCase("dotted")) {
            if (count % 5 == 0) g.fillRect(x, y, 1, 1);
        } else if (style.equalsIgnoreCase("dashed")) {
            if ((count / 10) % 2 == 0) g.fillRect(x, y, 1, 1);
        }
    }

    // DDA Circle
    private void drawDDACircle(Graphics g, int xc, int yc, int r) {
        int count = 0;
        double step = 1.0 / r;   // small angle increment
        for (double theta = 0; theta <= 2 * Math.PI; theta += step) {
            int x = (int) Math.round(r * Math.cos(theta));
            int y = (int) Math.round(r * Math.sin(theta));
            count++;
            plot(g, xc + x, yc + y, count);
        }
    }

    // Bresenham’s Circle
    private void drawBresenhamCircle(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r, d = 3 - 2 * r, count = 0;
        while (x <= y) {
            count++;
            drawCirclePoints(g, xc, yc, x, y, count);
            if (d < 0) d += 4 * x + 6;
            else {
                d += 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    // Midpoint Circle
    private void drawMidpointCircle(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r;
        int d = 1 - r;
        int count = 0;
        while (x <= y) {
            count++;
            drawCirclePoints(g, xc, yc, x, y, count);
            if (d < 0) d += 2 * x + 3;
            else {
                d += 2 * (x - y) + 5;
                y--;
            }
            x++;
        }
    }

    // Symmetry points
    private void drawCirclePoints(Graphics g, int xc, int yc, int x, int y, int count) {
        plot(g, xc + x, yc + y, count);
        plot(g, xc - x, yc + y, count);
        plot(g, xc + x, yc - y, count);
        plot(g, xc - x, yc - y, count);
        plot(g, xc + y, yc + x, count);
        plot(g, xc - y, yc + x, count);
        plot(g, xc + y, yc - x, count);
        plot(g, xc - y, yc - x, count);
    }

    // ---- Main Program with Menu ----
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Circle Drawing Algorithms -----");
        System.out.println("1. DDA");
        System.out.println("2. Bresenham");
        System.out.println("3. Midpoint");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        System.out.print("Enter radius of circle: ");
        int r = sc.nextInt();

        System.out.println("Choose style: solid / dotted / dashed");
        String style = sc.next();

        String algorithmm;
        switch (choice) {
            case 1:
                algorithmm = "dda";
                break;
            case 2:
                algorithmm = "bresenham";
                break;
            case 3:
                algorithmm = "midpoint";
                break;
            default:
                algorithmm = "invalid";
        }

        // close scanner - inputs already read
        sc.close();

        // Create GUI on the Event Dispatch Thread
        final String algorithmFinal = algorithmm;
        final String styleFinal = style;
        final int radiusFinal = r;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Circle Drawing - " + algorithmFinal.toUpperCase());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new CircleDrawing(radiusFinal, 300, 300, algorithmFinal, styleFinal));
                frame.pack();
                frame.setLocationRelativeTo(null); // center on screen
                frame.setVisible(true);
            }
        });
    }
}