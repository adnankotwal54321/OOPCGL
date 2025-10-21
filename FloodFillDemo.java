import java.awt.*;
import java.awt.event.*;

public class FloodFillDemo extends Frame implements MouseListener {
    int width = 400, height = 400;
    int[][] pixels = new int[width][height]; // 0=empty, 1=filled (boundary), 2=filled (color)

    public FloodFillDemo() {
        setTitle("Flood Fill Demo");
        setSize(width, height);
        addMouseListener(this);

        // Create a simple rectangle boundary
        for (int x = 100; x <= 300; x++) {
            pixels[x][100] = 1;
            pixels[x][300] = 1;
        }
        for (int y = 100; y <= 300; y++) {
            pixels[100][y] = 1;
            pixels[300][y] = 1;
        }

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixels[x][y] == 1) {
                    g.setColor(Color.BLACK); // boundary
                    g.drawLine(x, y, x, y);
                } else if (pixels[x][y] == 2) {
                    g.setColor(Color.RED); // filled
                    g.drawLine(x, y, x, y);
                }
            }
        }
    }

    // Flood fill algorithm (4-connected)
    public void floodFill(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return;
        if (pixels[x][y] != 0)
            return;

        pixels[x][y] = 2; // fill color

        floodFill(x + 1, y);
        floodFill(x - 1, y);
        floodFill(x, y + 1);
        floodFill(x, y - 1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        floodFill(x, y);
        repaint();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new FloodFillDemo();
    }
}