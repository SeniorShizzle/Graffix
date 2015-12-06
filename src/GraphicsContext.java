public class GraphicsContext {

    private double zValue = 5;

    private double xTheta = 0;
    private double yTheta = 0;
    private double zTheta = 0;

    private double scale  = 1;

    private static final GraphicsContext sharedInstance = new GraphicsContext();

    public static GraphicsContext getSharedInstance(){
        return sharedInstance;
    }

    public int[] transformedX(Polygon3D polygon) {
        int wWidth = Graphics.WINDOW_WIDTH;

        // Plot each X,Y,Z coordinate onto the "film plane"
        int numberOfPoints = polygon.getNumberOfPoints();
        int x[] = new int[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            x[i] = (int) (((polygon.x[i] * 300) / (polygon.z[i] + zValue) + wWidth / 2));
        }
        //Manually adds the last point to the array of points; arrays must be declared with one extra space
        //x[numberOfPoints] = (int)  ((polygon.x[0] * 100) + wWidth / 2);

        return x;
    }

    public int[] transformedY(Polygon3D polygon) {
        int wHeight = Graphics.WINDOW_HEIGHT;

        // Plot each X,Y,Z coordinate onto the "film plane"
        int numberOfPoints = polygon.getNumberOfPoints();
        int y[] = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            y[i] = (int) -((polygon.y[i] * 300) / (polygon.z[i] + zValue) + wHeight / 2) + wHeight;
        }
        //Manually adds the last point to the array of points; arrays must be declared with one extra space
        //x[numberOfPoints] = (int)  ((polygon.x[0] * 100) + wWidth / 2);

        return y;
    }

    public void setZValue(double zValue){
        this.zValue = zValue;
    }

    public double getZValue(){
        return this.zValue;
    }


    public double getzValue() {
        return zValue;
    }

    public void setzValue(double zValue) {
        this.zValue = zValue;
    }

    public double getxTheta() {
        return xTheta;
    }

    public void setxTheta(double xTheta) {
        this.xTheta = xTheta;
    }

    public double getyTheta() {
        return yTheta;
    }

    public void setyTheta(double yTheta) {
        this.yTheta = yTheta;
    }

    public double getzTheta() {
        return zTheta;
    }

    public void setzTheta(double zTheta) {

        this.zTheta = zTheta;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
