import java.awt.*;
import javax.swing.*;

public class DDALineDrawing extends JPanel {

    public void drawLineDDA(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = dx / (float) steps;
        float yInc = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            g.fillRect(Math.round(x), Math.round(y), 1, 1); // small pixel
            x += xInc;
            y += yInc;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Example lines
        g.setColor(Color.RED);
        drawLineDDA(g, 50, 50, 200, 100);

        g.setColor(Color.BLUE);
        drawLineDDA(g, 150, 150, 200, 200);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DDA Line Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new DDALineDrawing());
        frame.setVisible(true);
    }
}
