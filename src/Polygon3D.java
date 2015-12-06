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

        this.initialX = new double[numberOfPoints];
        this.initialY = new double[numberOfPoints];
        this.initialZ = new double[numberOfPoints];
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
        System.out.println("Using deprecated method. Invoke rotateXTo(double) instead");

        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {

            y[i] = y[i] * cos_t - z[i] * sin_t;
            z[i] = z[i] * cos_t + y[i] * sin_t;
        }

    }

    @Deprecated
    public void rotateY(double theta){
        System.out.println("Using deprecated method. Invoke rotateYTo(double) instead");

        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {

            x[i] = x[i] * cos_t - z[i] * sin_t;
            z[i] = z[i] * cos_t + x[i] * sin_t;
        }

    }

    @Deprecated
    public void rotateZ(double theta){
        System.out.println("Using deprecated method. Invoke rotateZTo(double) instead");

        double sin_t = Math.sin(theta);
        double cos_t = Math.cos(theta);

        for (int i = 0; i < numberOfPoints; i++) {
            x[i] = x[i] * cos_t - y[i] * sin_t;
            y[i] = y[i] * cos_t + x[i] * sin_t;
        }
    }


    /**
     * Rotates the Polygon3D to the specified theta value, around the X axis (parallel to screen's X)
     * @param theta the angle to rotate to in radians
     */
    public void rotateXTo(double theta){

        for (int i = 0; i < numberOfPoints; i++) {
            double radius = Math.sqrt(initialZ[i] * initialZ[i] + initialY[i] * initialY[i]);
            double tangent = Math.atan2(initialY[i], initialZ[i]);

            y[i] = radius * Math.sin(theta + tangent);
            z[i] = radius * Math.cos(theta + tangent);
        }
    }

    /**
     * Rotates the Polygon3D to the specified theta value, around the Y axis (parallel to screen's Y)
     * @param theta the angle to rotate to in radians
     */
    public void rotateYTo(double theta){

        for (int i = 0; i < numberOfPoints; i++) {
            double radius = Math.sqrt(initialX[i] * initialX[i] + initialZ[i] * initialZ[i]);
            double tangent = Math.atan2(initialX[i], initialZ[i]);

            x[i] = radius * Math.sin(theta + tangent);
            z[i] = radius * Math.cos(theta + tangent);
        }
    }

    /**
     * Rotates the Polygon3D to the specified theta value, around the Z axis (orthogonal to screen)
     * @param theta the angle to rotate to in radians
     */
    public void rotateZTo(double theta){

        for (int i = 0; i < numberOfPoints; i++) {
            double radius = Math.sqrt(initialX[i] * initialX[i] + initialY[i] * initialY[i]);
            double tangent = Math.atan2(initialY[i], initialX[i]);

            x[i] = radius * Math.cos(theta + tangent);
            y[i] = radius * Math.sin(theta + tangent);
        }
    }


}
