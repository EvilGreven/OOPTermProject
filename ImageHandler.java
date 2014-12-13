/*
 * Dependencies
 */
package oop_term_project;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.ArrayList;
/**
 * ImageHandler class for Final Project.
  * @author Brett Jones
 */
public class ImageHandler {
    /**
     * The constructor initializes member variables.
     */
    public ImageHandler() {
        images = new ArrayList<Image>();
    }
    /**
     * loadImage() loads an image from a file.
     * @param fileName is the path to the file.
     * @return returns the image.
     */
    private Image loadImage(String fileName) {
        Image image = null;
        try {
            image = ImageIO.read(new File(fileName));
        } catch (Exception ioe) {
            System.out.println("Error: Cannot open image:" + fileName);
        }
        return image;
    }
    /**
     * addImage() adds an image to the list.
     * @param fileName is the path to the file.
     * @param i is the desired element for the image.
     */
    public void addImage(String fileName, int i) {
        images.add(i, loadImage(fileName));
    }
    /**
     * getImage() gets the desired image from the list.
     * @param i is the element for the desired image.
     * @return returns the desired image.
     */
    public Image getImage(int i) {
        return images.get(i);
    }
    /**
     * Variables
     */
    private ArrayList<Image> images;
    /**
     * Enumerations
     */
    public static int imagePlayer = 0;
    public static int imageEnemy1 = 1;
    public static int imageEnemy2 = 2;
}
