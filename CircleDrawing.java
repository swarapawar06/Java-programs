import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class CircleDrawing extends JPanel {
    private int choiceAlgo = 1; 
    private int style = 1;     
    private int xc = 200, yc = 200, r = 100; 
    private void plot(Graphics g, int x, int y, int count) {
        if (style == 1) { 
            g.fillRect(x, y, 1, 1);
        } else if (style == 2) { 
            if (count % 5 == 0) g.fillRect(x, y, 1, 1);
        } else if (style == 3) {
            if ((count / 10) % 2 == 0) g.fillRect(x, y, 1, 1);
        }
    }

    
    private void drawDDACircle(Graphics g) {
        int steps = 360;
        int count = 0;
        for (int i = 0; i <= steps; i++) {
            double angle = Math.toRadians(i);
            int x = (int) (xc + r * Math.cos(angle));
            int y = (int) (yc + r * Math.sin(angle));
            plot(g, x, y, count++);
        }
    }

    
    private void drawBresenhamCircle(Graphics g) {
        int x = 0, y = r;
        int d = 3 - 2 * r;
        int count = 0;
        while (x <= y) {
            plotCirclePoints(g, xc, yc, x, y, count++);
            if (d < 0)
                d = d + 4 * x + 6;
            else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    
    private void drawMidpointCircle(Graphics g) {
        int x = 0, y = r;
        int p = 1 - r;
        int count = 0;
        while (x <= y) {
            plotCirclePoints(g, xc, yc, x, y, count++);
            x++;
            if (p < 0)
                p += 2 * x + 1;
            else {
                y--;
                p += 2 * (x - y) + 1;
            }
        }
    }

    
    private void plotCirclePoints(Graphics g, int xc, int yc, int x, int y, int count) {
        plot(g, xc + x, yc + y, count);
        plot(g, xc - x, yc + y, count);
        plot(g, xc + x, yc - y, count);
        plot(g, xc - x, yc - y, count);
        plot(g, xc + y, yc + x, count);
        plot(g, xc - y, yc + x, count);
        plot(g, xc + y, yc - x, count);
        plot(g, xc - y, yc - x, count);
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (choiceAlgo == 1)
            drawDDACircle(g);
        else if (choiceAlgo == 2)
            drawBresenhamCircle(g);
        else if (choiceAlgo == 3)
            drawMidpointCircle(g);
    }

    
    public static void main(String[] args) {
        Scanner 'sc' = new Scanner(System.in);
        CircleDrawing panel = new CircleDrawing();

        System.out.println("Menu Driven Circle Drawing:");
        System.out.println("1. DDA Circle");
        System.out.println("2. Bresenham Circle");
        System.out.println("3. Midpoint Circle");
        System.out.print("Enter your choice: ");
        panel.choiceAlgo = sc.nextInt();

        System.out.println("\nSelect Style:");
        System.out.println("1. Solid");
        System.out.println("2. Dotted");
        System.out.println("3. Dashed");
        System.out.print("Enter your choice: ");
        panel.style = sc.nextInt();

        JFrame frame = new JFrame("Circle Drawing Algorithms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(panel);
        frame.setVisible(true);
    }
}