/*
 * Dependencies
 */
package oop_term_project;
import java.awt.Color;
/**
 * MovingObjectFatory class for Final Project.
  * @author Brett Jones
 */
public class MovingObjectFactory {
    /**
     * setPath() links to the shared data.
     * @param sharedData is the shared data.
     */
    public void setPath(FireworkSharedData sharedData) {
        this.sharedData = sharedData;
    }
    /**
     * newEnemy() creates a new enemy and adds it to the shared data list.
     * @param x is the initial x-coordinate.
     * @param y is the initial y-coordinate.
     * @param t is the type.
     */
    public void newEnemy(int x, int y, int t) {
        Enemy n = new Enemy(x, y, t);
        if(t == Enemy.TYPE1)
            n.image = ImageHandler.imageEnemy1;
        else if(t == Enemy.TYPE2)
            n.image = ImageHandler.imageEnemy2;
        n.setPath(sharedData);
        n.setTarget((float)x, sharedData.canvasHeight+50);
        sharedData.addShip(n);
    }
    /**
     * newFirework() creates a new firework and adds it to the shared data list.
     * @param x is the initial x-coordinate.
     * @param y is the initial y-coordinate.
     * @param color is the color.
     * @param my is the target y-coordinate.
     * @param mr is the explosion radius.
     * @param e is the elongation.
     * @param s is the size.
     * @param o is the owner, true if enemy and false if player.
     * @param d is the damage.
     */
    public void newFirework(float x, float y, Color color,
            int my, int mr, int e, int s, boolean o, int d) {
        Firework f = new Firework((int)x, (int)y, color, e);
        f.setSpeed(s);
        f.setFireWorkMaxRadius(mr);
        f.setTarget(x, my);
        f.setOwner(o);
        f.setDamage(d);
        sharedData.add(f);
    }
    /**
     * newPlayer() creates the player ship.
     * @param x is the initial x-coordinate.
     * @param y is the initial y-coordinate.
     */
    public void newPlayer(int x, int y) {
        Player p = new Player(x, y);
        p.image = ImageHandler.imagePlayer;
        p.setPath(sharedData);
        //p.setTarget(x, y);
        sharedData.addShip(p);
    }
    /**
     * Variables
     */
    private FireworkSharedData sharedData;
}
