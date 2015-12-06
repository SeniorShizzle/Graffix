
import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class GraphicsFrame extends JPanel implements KeyListener {

    /** The ArrayList of PolyDrawable items to be drawn in each render loop */
    private ArrayList<PolyDrawable> drawables = new ArrayList<>();

    public GraphicsFrame(){
        this.setBackground(new Color(155, 226, 255));
        this.setFocusable(true);

        this.addKeyListener(this);
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
        for (PolyDrawable drawable : drawables){
            // Draw each drawable
            drawable.drawTo(g2d);
        }


    }


    public void addPolyDrawable(PolyDrawable drawable){
        this.drawables.add(drawable);
    }

    public void clearAllDrawables(){
        this.drawables = new ArrayList<>();
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

        // For now, we're drawing to our shared graphics context
        GraphicsContext context = GraphicsContext.getSharedInstance();

        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                context.setZValue(context.getZValue() + 0.1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                context.setZValue(context.getZValue() - 0.1);
                break;
            default:
                break;
        }

        repaint();

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
