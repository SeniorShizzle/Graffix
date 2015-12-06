import java.awt.*;
import java.util.Arrays;

public class Polygon3D {

    public double x[];
    public double y[];
    public double z[];

    private double initialX[];
    private double initialY[];
    private double initialZ[];

    private final int numberOfPoints;

    private int currentPointIndex = 0;

    public Polygon3D(double x[], double y[], double z[], int numberOfPoints){
        this.numberOfPoints = numberOfPoints;

        if (x.length < numberOfPoints || y.length < numberOfPoints || z.length < numberOfPoints){
            throw new Error("Improper array lengths. Less than number of points.");
        }

        this.x = x;
        this.y = y;
        this.z = z;

        this.initialX = x;
        this.initialY = y;
        this.initialZ = z;


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
        if (currentPointIndex >= numberOfPoints){

            return;
        }

        this.x[currentPointIndex] = x;
        this.y[currentPointIndex] = y;
        this.z[currentPointIndex] = z;

        this.initialX[currentPointIndex] = x;
        this.initialY[currentPointIndex] = y;
        this.initialZ[currentPointIndex] = z;

        currentPointIndex++;
    }

    @Override
    public String toString(){
        return "\n\tx:" + Arrays.toString(x) + "\n\ty:" + Arrays.toString(y) + "\n\tz:" + Arrays.toString(z) + "\n\n";
    }

    public int getNumberOfPoints(){
        return this.numberOfPoints;
    }


    @Deprecated
    public void rotateX(double theta){
        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {

            y[i] = y[i] * cos_t - z[i] * sin_t;
            z[i] = z[i] * cos_t + y[i] * sin_t;
        }

    }

    @Deprecated
    public void rotateY(double theta){
        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {

            x[i] = x[i] * cos_t - z[i] * sin_t;
            z[i] = z[i] * cos_t + x[i] * sin_t;
        }

    }

    @Deprecated
    public void rotateZ(double theta){
        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {
            x[i] = x[i] * cos_t - y[i] * sin_t;
            y[i] = y[i] * cos_t + x[i] * sin_t;
        }
    }


    public void rotateXTo(double theta){
        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {

            y[i] = y[i] * cos_t - z[i] * sin_t;
            z[i] = z[i] * cos_t + y[i] * sin_t;
        }
    }

    public void rotateYTo(double theta){
        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {

            x[i] = x[i] * cos_t - z[i] * sin_t;
            z[i] = z[i] * cos_t + x[i] * sin_t;
        }
    }

    public void rotateZTo(double theta){
        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {
            x[i] = x[i] * cos_t - y[i] * sin_t;
            y[i] = y[i] * cos_t + x[i] * sin_t;
        }
    }


}
