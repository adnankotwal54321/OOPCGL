import java.awt.*;
import javax.swing.*;

public class DrawPixelExample extends JPanel {

   
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        g.setColor(Color.BLUE);

        
        g.fillRect(50, 50, 1, 1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw Single Pixel Example");
        DrawPixelExample panel = new DrawPixelExample();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.add(panel);
        frame.setVisible(true);
    }
}