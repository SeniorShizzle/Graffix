import java.awt.*;
import java.util.Arrays;

public class Polygon3D {

    public double x[];
    public double y[];
    public double z[];

    private final int numberOfPoints;

    private int currentPointIndex = 0;

    private boolean completed = false;

    public Polygon3D(double x[], double y[], double z[], int numberOfPoints){
        this.numberOfPoints = numberOfPoints;

        if (x.length < numberOfPoints || y.length < numberOfPoints || z.length < numberOfPoints){
            throw new Error("Improper array lengths. Less than number of points.");
        }

        this.x = x;
        this.y = y;
        this.z = z;

        completed = true;
    }

    public Polygon3D(int numberOfPoints){
        this.numberOfPoints = numberOfPoints;

        this.x = new double[numberOfPoints];
        this.y = new double[numberOfPoints];
        this.z = new double[numberOfPoints];
    }

    /**
     * Adds a point to the Polygon3D. If the polygon is full (contains the set number of points) then nothing happens.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    public void addPoint(double x, double y, double z){
        if (currentPointIndex >= numberOfPoints) return;

        this.x[currentPointIndex] = x;
        this.y[currentPointIndex] = y;
        this.z[currentPointIndex] = z;

        currentPointIndex++;
    }

    @Override
    public String toString(){
        return "\n\tx:" + Arrays.toString(x) + "\n\ty:" + Arrays.toString(y) + "\n\tz:" + Arrays.toString(z) + "\n\n";
    }

    public int getNumberOfPoints(){
        return this.numberOfPoints;
    }


}
