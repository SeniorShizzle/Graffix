
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GraphicsFrame extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

    /** TRUE if the model should be drawn as a wireframe, false to draw as filled panels */
    private boolean shouldDrawWireframe = true;

    /** The ArrayList of PolyDrawable items to be drawn in each render loop */
    private ArrayList<PolyDrawable> drawables = new ArrayList<>();


    /** The initial mouse X value intercepted during clickdown */
    private double initialMouseX = 0;

    /** The initial mouse Y value intercepted during clickdown */
    private double initialMouseY = 0;

    private double maxBound = Double.MIN_VALUE;
    private double minBound = Double.MAX_VALUE;





    public GraphicsFrame(){
        this.setBackground(new Color(155, 226, 255));
        this.setFocusable(true);

        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        // ALWAYS draw to the g2d component

        g2d.setBackground(new Color(195, 223, 255));

        g2d.setColor(new Color(255, 127, 22));
        for (PolyDrawable drawable : drawables){

            // Draw each drawable
            drawable.setShouldDrawWireFrame(shouldDrawWireframe);
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
     * @param e the KeyEvent passed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the KeyEvent passed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        // For now, we're drawing to our shared graphics context
        GraphicsContext context = GraphicsContext.getSharedInstance();

        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                context.setZValue(context.getZValue() - 0.2);
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                context.setZValue(context.getZValue() + 0.2);
                break;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                context.setzTheta(context.getzTheta() + 0.01);
                for (PolyDrawable drawable : drawables) drawable.setThetaY(drawable.getThetaY() + 0.1);
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                context.setzTheta(context.getzTheta() - 0.01);
                for (PolyDrawable drawable : drawables) drawable.setThetaY(drawable.getThetaY() - 0.1);
                break;

            //// The following map to the W,A,S,D keys in QWERTY and Dvorak layouts (<,A,O,E)

            case KeyEvent.VK_W :
            case KeyEvent.VK_LESS:
                // Rotate X axis positive
                for (PolyDrawable drawable : drawables) drawable.setThetaX(drawable.getThetaX() + 0.1);

                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_O:
                // Rotate X axis negative
                for (PolyDrawable drawable : drawables) drawable.setThetaX(drawable.getThetaX() - 0.1);
                break;

            case KeyEvent.VK_A:
                // Rotate anticlockwise
                for (PolyDrawable drawable : drawables) drawable.setThetaZ(drawable.getThetaZ() + 0.1);
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_E:
                // Rotate clockwise
                for (PolyDrawable drawable : drawables) drawable.setThetaZ(drawable.getThetaZ() - 0.1);
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
     * @param e the KeyEvent passed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setShouldDrawWireframe(boolean shouldDrawWireframe){
        this.shouldDrawWireframe = shouldDrawWireframe;
    }

    public boolean shouldDrawWireframe(){
        return this.shouldDrawWireframe;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        this.initialMouseX = e.getX();
        this.initialMouseY = e.getY();
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        double deltaX = initialMouseX - e.getX();
        double deltaY = initialMouseY - e.getY();

        double thetaY = (deltaX / (Graphics.WINDOW_WIDTH * 2)) * 6.28; // dragging three quarters of the window = 2pi
        double thetaX = (deltaY / (Graphics.WINDOW_HEIGHT * 1.5)) * 6.28;

        for (PolyDrawable drawable : drawables){
            // Look at deltaX for thetaY because rotation around vertical Y axis is horizontal mouse movement
            drawable.setThetaX(thetaX);
            drawable.setThetaY(thetaY);
        }

        repaint();
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }


    public double getMaxBound() {
        return maxBound;
    }

    public void setMaxBound(double maxBound) {
        this.maxBound = maxBound;

        GraphicsContext.getSharedInstance().setMaximumRange(maxBound - minBound);
    }

    public double getMinBound() {
        return minBound;
    }

    public void setMinBound(double minBound) {
        this.minBound = minBound;

        GraphicsContext.getSharedInstance().setMaximumRange(maxBound - minBound);
    }
}
