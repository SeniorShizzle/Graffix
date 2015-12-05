import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Graphics extends JFrame {

    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_WIDTH = 800;


    public static void main(String args[]){
        new Graphics().run();
    }


    public Graphics(){
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));


        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }




    public void run(){

        GraphicsFrame graphicsFrame = new GraphicsFrame();

        graphicsFrame.setBounds(this.getBounds());

        this.add(graphicsFrame);

        JFileChooser fileChooser = new JFileChooser("./data");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XZY files", "xyz", "txt");
        fileChooser.addChoosableFileFilter(filter);

        switch (fileChooser.showOpenDialog(this)){
            case JFileChooser.APPROVE_OPTION:
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            default:
                break;
        }
    }


    public Drawable createDrawableFromFile(File input) throws Exception {

        Scanner sc = new Scanner(input);
        int numberOfPoints = sc.nextInt();

        double[] x = new double[numberOfPoints];
        double[] y = new double[numberOfPoints];
        double[] z = new double[numberOfPoints];

        // Scan the points into the arrays
        for (int i = 0; i < numberOfPoints; i++) {
            x[i] = sc.nextDouble();
            y[i] = sc.nextDouble();
            z[i] = sc.nextDouble();
        }

        int numberOfFaces = sc.nextInt();
        Polygon3D[] faces = new Polygon3D[numberOfFaces];

        // Coalesce all of the points
        for (int i = 0; i < numberOfFaces; i++){
            int numberOfVerticies = sc.nextInt();


        }


        return null;
    }


}
