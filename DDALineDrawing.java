import javax.swing.*;
import java.awt.*;
public class DDALineDrawing extends JPanel{
    public void drawLineDDA(Graphics g,int x1,int y1,int x2,int y2){
        int dx=x2-x1;
        int dy=y2-y1;
        int steps = Math.max(Math.abs(dx),Math.abs(dy));
        float xInc=dx/(float)steps;
        float yInc=dy/(float)steps;
        float x = x1;
        float y = y1;
        for (int i = 0;i <= steps;i++){
            g.fillRect(Math.round(x),Math.round(y),1,1);
            x += xInc;
            y += yInc;
            
        }
    }
 

protected void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(Color.RED);
    drawLineDDA(g,150,150,200,100);
    g.setColor(Color.BLUE);
    drawLineDDA(g,100,150,300,300);
        g.setColor(Color.BLACK);
        drawLineDDA(g,200,50,100,150);
        
} 

public static void main(String[]args){
    JFrame frame = new JFrame("DDA drawing algorithm");
       DDALineDrawing panel = new DDALineDrawing();
    frame.add(panel);
    frame.setSize(400,800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
