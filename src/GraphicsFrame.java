
import javax.swing.*;
import java.awt.*;

public class GraphicsFrame extends JPanel {

    public GraphicsFrame(){
        System.out.println("Potato");
        this.setBackground(new Color(155, 226, 255));
    }


    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        // ALWAYS draw to the g2d component

        g2d.setBackground(new Color(195, 223, 255));

//        int[] xPoints = new int[]{0, Graphics.WINDOW_WIDTH / 2 - 50, Graphics.WINDOW_WIDTH / 2 + 50, Graphics.WINDOW_WIDTH};
//        int[] yPoints = new int[]{Graphics.WINDOW_HEIGHT, Graphics.WINDOW_HEIGHT / 2, Graphics.WINDOW_HEIGHT / 2, Graphics.WINDOW_HEIGHT};
//        g2d.setColor(new Color(184, 212, 244));
//        g2d.fillPolygon(xPoints, yPoints, 4);



        g2d.setColor(new Color(255, 127, 22));

        g2d.drawLine(0,0,400,400);

    }
}
