import java.awt.*;
import java.awt.event.*;

public class CircleDrawingAWT extends Frame implements ActionListener {
    int algo = 1, style = 1; 

    public CircleDrawingAWT() {
        setTitle("Circle Drawing AWT");
        setSize(500, 500);
        setLayout(new FlowLayout());

        String[] algos = { "Midpoint", "Bresenham", "DDA" };
        String[] styles = { "Solid", "Dotted", "Dashed" };

        Choice algoChoice = new Choice();
        Choice styleChoice = new Choice();
        for (String a : algos)
            algoChoice.add(a);
        for (String s : styles)
            styleChoice.add(s);

        Button drawBtn = new Button("Draw Circle");

        algoChoice.addItemListener(e -> algo = algoChoice.getSelectedIndex() + 1);
        styleChoice.addItemListener(e -> style = styleChoice.getSelectedIndex() + 1);
        drawBtn.addActionListener(this);

        add(new Label("Algorithm:"));
        add(algoChoice);
        add(new Label("Style:"));
        add(styleChoice);
        add(drawBtn);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        int xc = 250, yc = 250, r = 100;
        switch (algo) {
            case 1:
                drawMidpoint(g, xc, yc, r);
                break;
            case 2:
                drawBresenham(g, xc, yc, r);
                break;
            case 3:
                drawDDA(g, xc, yc, r);
                break;
        }
    }

    
    void plot(Graphics g, int x, int y, int count) {
        if (style == 2 && count % 4 != 0)
            return; 
        if (style == 3 && (count / 5) % 2 == 1)
            return; 
        g.drawLine(x, y, x, y);
    }
   void drawSymmetricPoints(Graphics g, int xc, int yc, int x, int y, int count) {
        plot(g, xc + x, yc + y, count);
        plot(g, xc - x, yc + y, count);
        plot(g, xc + x, yc - y, count);
        plot(g, xc - x, yc - y, count);
        plot(g, xc + y, yc + x, count);
        plot(g, xc - y, yc + x, count);
        plot(g, xc + y, yc - x, count);
        plot(g, xc - y, yc - x, count);
    }

  
    void drawMidpoint(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r;
        int p = 1 - r;
        int count = 0;

        while (x <= y) {
            drawSymmetricPoints(g, xc, yc, x, y, count++);
            if (p < 0) {
                p = p + 2 * x + 3;
            } else {
                p = p + 2 * (x - y) + 5;
                y--;
            }
            x++;
        }
    }

  
    void drawBresenham(Graphics g, int xc, int yc, int r) {
        int x = 0, y = r, d = 3 - 2 * r, count = 0;
        while (x <= y) {
            drawSymmetricPoints(g, xc, yc, x, y, count++);
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    void drawDDA(Graphics g, int xc, int yc, int r) {
        double eps = 1.0 / r; 
        double x = r;
        double y = 0;
        int steps = (int)(2 * Math.PI * r / eps);
        int count = 0;

        for (int i = 0; i < steps; i++) {
            plot(g, (int)Math.round(xc + x), (int)Math.round(yc + y), count++);
            double xNew = x + eps * y;
            double yNew = y - eps * xNew;
            x = xNew;
            y = yNew;
        }
    }

    public static void main(String[] args) {
        new CircleDrawingAWT();
    }
}