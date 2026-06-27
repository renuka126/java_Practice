import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PolygonFill extends JPanel {
    private String fillType;
    private Color fillColor = Color.CYAN;

    private int[] xPoints = {100, 200, 250, 200, 150, 120};
    private int[] yPoints = {200, 100, 200, 250, 220, 250};
    private int n = xPoints.length;

    public PolygonFill(String fillType) {
        this.fillType = fillType;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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
            List<Integer> intersections = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int x1 = xPoints[i],           y1 = yPoints[i];
                int x2 = xPoints[(i + 1) % n], y2 = yPoints[(i + 1) % n];

                if (y1 == y2) continue; // skip horizontal edges

                if (y >= Math.min(y1, y2) && y < Math.max(y1, y2)) {
                    int x = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
                    intersections.add(x);
                }
            }

            Collections.sort(intersections);

            for (int i = 0; i + 1 < intersections.size(); i += 2) {
                g.drawLine(intersections.get(i), y, intersections.get(i + 1), y);
            }
        }
    }

    // ------------------- Flood Fill --------------------
    private void floodFillDemo(Graphics g) {
        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, n);
        g.setColor(Color.BLACK);
        g.drawString("Flood Fill", 10, 20);
    }

    // ------------------- Seed Fill --------------------
    private void seedFillDemo(Graphics g) {
        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, n);
        g.setColor(Color.BLACK);
        g.drawString("Seed Fill", 10, 20);
    }

    // ------------------- Main --------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== Polygon Fill Algorithms ====");
        System.out.println("1. Scan-line Fill");
        System.out.println("2. Flood Fill");
        System.out.println("3. Seed Fill");
        System.out.print("Enter choice (1-3): ");

        String option;
        try {
            int choice = Integer.parseInt(sc.nextLine().trim());
            switch (choice) {
                case 1: option = "scan";  break;
                case 2: option = "flood"; break;
                case 3: option = "seed";  break;
                default:
                    System.out.println("Invalid choice! Defaulting to Scan-line.");
                    option = "scan";
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Defaulting to Scan-line.");
            option = "scan";
        }

        sc.close();

        JFrame frame = new JFrame("Polygon Fill - " + option.toUpperCase());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); // centers window on screen
        frame.add(new PolygonFill(option));
        frame.setVisible(true);
    }
}