import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;
import java.util.Arrays;

public class PolyDrawable {

    private Polygon3D[] faces;
    private int faceIndex = 0;

    private final int numberOfFaces;


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

            g2d.setColor(randColor());
            g2d.drawPolygon(context.transformedX(face), context.transformedY(face), face.getNumberOfPoints());
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

}
