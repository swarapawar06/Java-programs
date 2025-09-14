import javax.swing.*;
import java.awt.*;

public class DDACircle extends JPanel {
    private int xc,yc,r;

    public DDACircle (int xc, int yc, int r) {
        this.xc = xc;
        this.yc = yc;
        this.r = r;
    }

@ Override
protected void paintComponent (Graphics g) {
    super.paintComponent (g);

    double esp = 1.0/r;
    double x = r;
    double y = 0;
    double eps = 1/r;

    int steps = (int)(2*Math.PI*r/eps);

    for (int i = 0; i< steps; i ++) {
        g.fillRect ((int)Math.round(xc+x),(int)Math.round(yc+y),10,10);

        double xNew = x+eps*x;
        double yNew = y-eps*xNew;

        x = xNew;
        y = yNew;
    }
    
}

public static void main (String[] args) {
    JFrame frame = new JFrame ("Increment DDA Circle (NO Symmetry)");

    DDACircle circle = new DDACircle (200,200,100);
    frame.add (circle);
    frame.setSize(400,400);
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
}
}