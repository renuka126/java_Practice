
import java.util.*;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PolygonFill extends JPanel {
    private String fillType;   // "scan", "flood", "seed"
    private Color fillColor = Color.CYAN;

    // Polygon vertices (concave polygon example)
    private int[] xPoints = {100, 200, 250, 200, 150, 120};
    private int[] yPoints = {200, 100, 200, 250, 220, 250};
    private int n = xPoints.length;

    public PolygonFill(String fillType) {
        this.fillType = fillType;
        setBackground(Color.WHITE);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw polygon outline
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, n);

        switch (fillType.toLowerCase()) {
            case "scan":
                scanLineFill(g);
                break;
            case "flood":
                floodFillDemo(g);
                break;
            case "seed":
                seedFillDemo(g);
                break;
            default:
                g.drawString("Invalid Fill Option!", 20, 20);
        }
    }

    // ------------------- Scan Line Fill --------------------
    private void scanLineFill(Graphics g) {
        g.setColor(fillColor);

        int ymin = Arrays.stream(yPoints).min().getAsInt();
        int ymax = Arrays.stream(yPoints).max().getAsInt();

        for (int y = ymin; y <= ymax; y++) {
            java.util.List<Integer> intersections = new ArrayList<>();

            // Find intersections with polygon edges
            for (int i = 0; i < n; i++) {
                int x1 = xPoints[i], y1 = yPoints[i];
                int x2 = xPoints[(i + 1) % n], y2 = yPoints[(i + 1) % n];

                if (y1 == y2) continue; // horizontal edges

                if ((y >= Math.min(y1, y2)) && (y < Math.max(y1, y2))) {
                    int x = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
                    intersections.add(x);
                }
            }

            Collections.sort(intersections);

            // Fill between pairs
            for (int i = 0; i < intersections.size(); i += 2) {
                if (i + 1 < intersections.size()) {
                    int xStart = intersections.get(i);
                    int xEnd = intersections.get(i + 1);
                    g.drawLine(xStart, y, xEnd, y);
                }
            }
        }
    }

    // ------------------- Flood Fill (demo using recursion) -------------------
    private void floodFillDemo(Graphics g) {
        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, n); // simplified demo
    }

    // ------------------- Seed Fill (demo with fillPolygon) -------------------
    private void seedFillDemo(Graphics g) {
        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, n); // simplified demo
    }

    // ------------------- Main Program -------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("---- Polygon Fill Algorithms ----");
        System.out.println("1. Scan-line Fill");
        System.out.println("2. Flood Fill");
        System.out.println("3. Seed Fill");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        String option;
        switch (choice) {
            case 1: option = "scan"; break;
            case 2: option = "flood"; break;
            case 3: option = "seed"; break;
            default: option = "invalid";
        }

        JFrame frame = new JFrame("Polygon Fill - " + option.toUpperCase());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new PolygonFill(option));
        frame.setVisible(true);
    }
}
