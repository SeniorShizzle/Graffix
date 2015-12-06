import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Graphics extends JFrame {

    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_WIDTH = 800;

    GraphicsFrame mainGraphicsFrame = new GraphicsFrame();


    public static void main(String args[]){
        new Graphics().run();
    }


    public Graphics(){
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));


        mainGraphicsFrame.setBounds(this.getBounds());

        this.add(mainGraphicsFrame);

        JButton resetButton = new JButton("Choose File");
        resetButton.addActionListener(ActionListener -> chooseFile());

        mainGraphicsFrame.add(resetButton);
        resetButton.setBounds(WINDOW_HEIGHT - 70, WINDOW_HEIGHT - 50, 55, 35);

        JButton renderStyleButton = new JButton("Switch Rendering Style");
        renderStyleButton.addActionListener(ActionListener -> {
            // Toggle the wireframe on and off
            mainGraphicsFrame.setShouldDrawWireframe(!mainGraphicsFrame.shouldDrawWireframe());
            mainGraphicsFrame.repaint();
            mainGraphicsFrame.grabFocus();
        });

        mainGraphicsFrame.add(renderStyleButton);


        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public void chooseFile(){

        JFileChooser fileChooser = new JFileChooser("./data");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XZY files", "xyz", "txt");
        fileChooser.setFileFilter(filter);

        switch (fileChooser.showOpenDialog(this)) {
            case JFileChooser.APPROVE_OPTION:
                PolyDrawable drawable;
                try {
                    drawable = createPolyDrawableFromFile(fileChooser.getSelectedFile());
                    mainGraphicsFrame.clearAllDrawables();
                    mainGraphicsFrame.addPolyDrawable(drawable);

                } catch (Exception e) {
                    System.out.println("Error creating shape:");
                    System.out.println(e.getMessage());
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            default:
                break;
        }

        mainGraphicsFrame.grabFocus();
        mainGraphicsFrame.repaint();
    }


    public void run(){

        chooseFile();

    }


    public PolyDrawable createPolyDrawableFromFile(File input) throws Exception {

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
        PolyDrawable drawable = new PolyDrawable(numberOfFaces);

        // Coalesce all of the points
        for (int i = 0; i < numberOfFaces; i++){
            int numberOfVertices = sc.nextInt();
            Polygon3D face = new Polygon3D(numberOfVertices);

            // Add the x and y points stored in the indexes
            for (int j = 0; j < numberOfVertices; j++) {
                int index = sc.nextInt();
                face.addPoint(x[index], y[index], z[index]);
            }

            drawable.addFace(face);
        }

        return drawable;
    }


}
