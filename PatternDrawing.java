import java.awt.*;
import javax.swing.*;

public class PatternDrawing extends JPanel {

    // ------------ Line styles ----------------
    private void plot(Graphics g, int x, int y, String style, int count) {
        if (style.equalsIgnoreCase("solid")) {
            g.fillRect(x, y, 1, 1);
        } else if (style.equalsIgnoreCase("dotted")) {
            if (count % 5 == 0) g.fillRect(x, y, 1, 1);
        } else if (style.equalsIgnoreCase("dashed")) {
            if ((count / 10) % 2 == 0) g.fillRect(x, y, 1, 1);
        } else if (style.equalsIgnoreCase("thick")) {
            g.fillRect(x, y, 2, 2);
        }
    }

    // ------------ DDA Line Algorithm ------------
    private void drawDDALine(Graphics g, int x1, int y1, int x2, int y2, String style) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = dx / (float) steps;
        float yInc = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            plot(g, Math.round(x), Math.round(y), style, i);
            x += xInc;
            y += yInc;
        }
    }

    // ------------ Bresenham’s Line Algorithm ------------
    private void drawBresenhamLine(Graphics g, int x1, int y1, int x2, int y2, String style) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        int count = 0;
        while (true) {
            plot(g, x1, y1, style, count++);
            if (x1 == x2 && y1 == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    // ------------ Shapes ----------------
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Outer Rectangle (DDA, Dotted)
        drawDDALine(g, 100, 100, 500, 100, "dotted");  // top
        drawDDALine(g, 500, 400, 500, 100, "dotted"); // right
        drawDDALine(g, 100, 400, 500, 400, "dotted"); // bottom
        drawDDALine(g, 100, 100, 100, 400, "dotted"); // left

        // Inner Rectangle (DDA, Thick)
        drawDDALine(g, 200, 175, 400, 175, "thick");  // top
        drawDDALine(g, 400, 175, 400, 325, "thick");  // right
        drawDDALine(g, 400, 325, 200, 325, "thick");  // bottom
        drawDDALine(g, 200, 325, 200, 175, "thick");  // left

        // Diamond (Bresenham, Solid + Dashed)
        // Solid top and bottom
        drawBresenhamLine(g, 100, 250, 300, 400, "solid"); // top right
        drawBresenhamLine(g, 300, 400, 500, 250, "solid"); // bottom left

        // Dashed other two
        drawBresenhamLine(g, 500, 250, 300, 100, "dashed"); // top left
        drawBresenhamLine(g, 300, 100, 100, 250, "dashed"); // bottom right
    }

    // ------------ Main ------------
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pattern Drawing - DDA & Bresenham");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(new PatternDrawing());
        frame.setVisible(true);
    }
}