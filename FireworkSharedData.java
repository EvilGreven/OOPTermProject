
/*
 * Dependencies
 */
package oop_term_project;
import java.util.ArrayList;
/**
 * FireworkSharedData class for Final Project.
 * This class shares data between the animator, renderer, and other classes.
 * @author Brett Jones, derived from Dr. Sung's version of the class.
 */
public class FireworkSharedData {
    /**
     * The constructor initializes member variables.
     */
    public FireworkSharedData() {
        fireworks = new ArrayList<Firework>();
        ships = new ArrayList<Ship>();
        iHandler = new ImageHandler();
        oFactory = new MovingObjectFactory();
        cHandler = new CollisionHandler();
        sHandler = new ScoreHandler();
    }
    /**
     * add() adds a firework to the list.
     * @param f is the firework to add.
     */
    public synchronized void add(Firework f) {
        fireworks.add(f);
    }
    /**
     * remove() removes a firework from the list
     * @param i is the desired firework to remove.
     */
    public synchronized void remove(int i) {
        fireworks.remove(i);
    }
    /**
     * get() returns the desired firework.
     * @param i is the element of the array for the desired firework.
     * @return returns the desired firework.
     */
    public synchronized Firework get(int i) {
        return fireworks.get(i);
    }
    /**
     * size() returns the size of the firework array.
     * @return returns the size.
     */
    public int size() {
        return fireworks.size();
    }
    //ships
    /**
     * addShip() adds a Ship to the list.
     * @param s is the Ship to add.
     */
    public synchronized void addShip(Ship s) {
        ships.add(s);
    }
    /**
     * removeShip() removes a Ship from the list
     * @param i is the desired Ship to remove.
     */
    public synchronized void removeShip(int i) {
        ships.remove(i);
    }
    /**
     * getShip() returns the desired Ship.
     * @param i is the element of the array for the desired Ship.
     * @return returns the desired Ship.
     */
    public synchronized Ship getShip(int i) {
        return ships.get(i);
    }
    /**
     * sizeShip() returns the size of the Ship array.
     * @return returns the size.
     */
    public int sizeShip() {
        return ships.size();
    }
    /**
     * Variables
     */
    private ArrayList<Firework> fireworks;
    private ArrayList<Ship> ships;
    public ImageHandler iHandler;
    public MovingObjectFactory oFactory;
    public CollisionHandler cHandler;
    public ScoreHandler sHandler;
    public int canvasWidth;
    public int canvasHeight;
}
