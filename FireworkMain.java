/*
 * Dependencies
 */
package oop_term_project;
import javax.swing.JFrame;
/**
 * FireworkMain class for Final Project.
 * This is the main class.
 * @author Dr. Sung.
 */
public class FireworkMain {
    /**
     * The main program class.
     * @param args accepts arguments.
     */
    public static void main(String[] args) {
        FireworkSharedData sharedData = new FireworkSharedData();
        FireworkRenderer renderer = new FireworkRenderer(sharedData);
        renderer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        renderer.setVisible(true);

        FireworkAnimator animator = new FireworkAnimator(sharedData, renderer);
        Thread t = new Thread(animator);
        t.start();
    }
}
