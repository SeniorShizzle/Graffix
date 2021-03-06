import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;
import java.util.Arrays;

public class PolyDrawable {

    private Polygon3D[] faces;
    private int faceIndex = 0;

    private final int numberOfFaces;

    private boolean wireFrame = true;

    private double thetaX = 0;
    private double thetaY = 0;
    private double thetaZ = 0;


    public PolyDrawable(int numberOfFaces) {
        this.numberOfFaces = numberOfFaces;

        this.faces = new Polygon3D[numberOfFaces];
    }

    public void addFace(Polygon3D face){
        if (faceIndex >= numberOfFaces) return;

        faces[faceIndex] = face;

        faceIndex++;
    }

    public void drawTo(Graphics2D g2d){

        GraphicsContext context = GraphicsContext.getSharedInstance();

        for (Polygon3D face: faces){
            // Plot each X,Y,Z coordinate onto the "film plane"

            //g2d.setColor(randColor());
            int b = (int)(context.relativeValuePosition(face.z[0]) * 255);
            b = b < 255 && b > 0 ? b : 128;
            g2d.setColor(new Color(b, 128, 256 - b));
            if (wireFrame) g2d.drawPolygon(context.transformedX(face), context.transformedY(face), face.getNumberOfPoints());
            else g2d.fillPolygon(context.transformedX(face), context.transformedY(face), face.getNumberOfPoints());

        }



    }

    private Color randColor(){
        int r = (int)(Math.random() * 256);
        int g = (int)(Math.random() * 256);
        int b = (int)(Math.random() * 256);

        return new Color(r, g, b);
    }

    @Override
    public String toString(){
        return "PolyDrawable with " + numberOfFaces + " faces:\n\t" + Arrays.toString(faces);
    }

    //// ACCESSOR methods

    public int getNumberOfPoints(){
        return this.numberOfFaces;
    }

    public Polygon3D[] getFaces(){
        return this.faces;
    }

    public void setShouldDrawWireFrame(boolean shouldDrawWireFrame){
        this.wireFrame = shouldDrawWireFrame;
    }

    public double getThetaZ() {
        return thetaZ;
    }

    public void setThetaZ(double thetaZ) {
        for (Polygon3D face : faces){
            //face.rotateZ(this.thetaZ - thetaZ);
            face.rotateZTo(thetaZ);
        }

        this.thetaZ = thetaZ;
    }

    public double getThetaX() {
        return thetaX;
    }

    public void setThetaX(double thetaX) {
        for (Polygon3D face : faces) {
            //face.rotateX(this.thetaX - thetaX);
            face.rotateXTo(thetaX);
        }

        this.thetaX = thetaX;
    }

    public double getThetaY() {
        return thetaY;
    }

    public void setThetaY(double thetaY) {
        for (Polygon3D face : faces) {
            //face.rotateY(this.thetaY - thetaY);
            face.rotateYTo(thetaY);
        }

        this.thetaY = thetaY;
    }
}
