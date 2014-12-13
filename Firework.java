
/*
 * Dependencies
 */
package oop_term_project;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import javax.vecmath.Vector2f;
/**
 * Firework class for Final Project.
 * This a modified firework class.
 * @author Brett Jones, derived from Dr. Sung's version of the class.
 */
public class Firework extends Ellipse2D.Float {
    /**
     * This constructor accepts three variables for initialization.
     * @param x is the initial x-coordinate.
     * @param y is the initial y-coordinate.
     * @param color is the color.
     */
    public Firework(int x, int y, Color color) {
       setFrameFromCenter(x, y, x+FIREWORK_SIZE, y+FIREWORK_SIZE);
       this.color = color;
     }
    /**
     * This constructor accepts four variables for initialization.
     * @param x is the initial x-coordinate.
     * @param y is the initial y-coordinate.
     * @param color is the color.
     * @param e is the elongation.
     */
    public Firework(int x, int y, Color color, int e) {
        elongation = e;
       setFrameFromCenter(x, y, x+FIREWORK_SIZE, y+FIREWORK_SIZE+elongation);
       this.color = color;
     }
    /**
     * This constructor accepts two variables for initialization.
     * @param x is the initial x-coordinate.
     * @param y is the initial y-coordinate.
     */
     public Firework(int x, int y) {
       this(x, y, Color.WHITE);
     }
     /**
      * setTarget() sets the target destination.
      * @param x is the target x-coordinate.
      * @param y is the target y-coordinate.
      */
     public void setTarget(float x, float y) {
       target = new Point2D.Float(x, y);
     }
     /**
      * updateLocation() determines current location and moves towards target.
      */
     // Vector arithmetic
     // A: current position
     // B: target position
     // d: distance to travel along the line from A to B
     //     A_moved = A + |B - A| * d where |  | represents 'norm'
     public void updateLocation() {
       Vector2f currentLoc = new Vector2f((float)getCenterX(),
               (float)getCenterY());
       Vector2f update = new Vector2f(target.x, target.y);
       update.sub(currentLoc); // B - A
       update.normalize(); // |B - A|
       update.scale(speed); // |B - A| x dist
       currentLoc.add(update); // A + |B - A| x d

       setFrameFromCenter(currentLoc.x, currentLoc.y,
               currentLoc.x+FIREWORK_SIZE, currentLoc.y+FIREWORK_SIZE+elongation);
     }
     /**
      * updateSize() updates the size of the explosion.
      * @param d is the increment.
      */
     public void updateSize(int d) {
       double cx = getCenterX();
       double cy = getCenterY();
       fireWorkSize += d*FIREWORK_SIZE;
       setFrameFromCenter(cx, cy, cx+fireWorkSize, cy+fireWorkSize);
     }
     /**
      * getSize() gets the current size.
      * @return returns the size.
      */
     public int getSize() {
         return fireWorkSize;
     }
     /**
      * update() checks the state and updates accordingly.
      */
     public void update() {
       updateState();
       if (state == STATE_TRAVELING) updateLocation();
       else if (state == STATE_EXPLODING) updateSize(1);
       else if (state == STATE_DECAYING) updateSize(-1);
     }
     /**
      * updateState() determines and updates the firework's state.
      */
     public void updateState() {
	if (state == STATE_TRAVELING ) {
	  double distance = target.distance(getCenterX(), getCenterY());
	  boolean targetReached = distance <= speed ? true : false;
	  if (targetReached) state = STATE_EXPLODING;
	} else if (state == STATE_EXPLODING) {
          if (fireWorkSize >= fireWorkMaxRadius) state = STATE_DECAYING;
	}
        else if(state == STATE_DECAYING) {
            if(fireWorkSize <= 0) state = STATE_DONE;
        }
     }
     /**
      * getColor() gets the color.
      * @return returns the color.
      */
     public Color getColor() {
       return color;
     }
     /**
      * getState() gets the state.
      * @return returns the state.
      */
     public int getState() {
       return state;
     }
     /**
      * setFireWorkMaxRadius() sets the maximum explosion size.
      * @param size is the max size.
      */
     public void setFireWorkMaxRadius(int size) {
       fireWorkMaxRadius = size;
     }
     /**
      * setSpeed() sets the firework speed.
      * @param speed is the speed.
      */
     public void setSpeed(int speed) {
         this.speed = speed;
     }
     /**
      * destroy() triggers the firework to explode.
      */
     public void destroy() {
         if(state != STATE_EXPLODING && state != STATE_DECAYING)
             state = STATE_EXPLODING;
     }
     /**
      * setOwner() sets the owner (true is enemy, false is player)
      * @param o is true or false.
      */
     public void setOwner(boolean o) {
         isEnemy = o;
     }
     /**
      * getOwner() returns the owner (true is enemy, false is player).
      * @return returns true or false.
      */
     public boolean getOwner() {
         return isEnemy;
     }
     /**
      * setDamage() sets the firework damage.
      * @param d is the damage value.
      */
     public void setDamage(int d) {
         damage = d;
     }
     /**
      * getDamage() gets the firework damage.
      * @return d is the damage value.
      */
     public int getDamage() { 
         return damage;
     }
     /**
      * Variables
      */
     private Color color;
     private Point2D.Float target;
     private float unitX, unitY; // unit x y increment
     private int state = STATE_TRAVELING;
     private int fireWorkSize = FIREWORK_SIZE;
     private int fireWorkMaxRadius;
     private int elongation;
     private int speed;
     private boolean isEnemy = true;
     private int damage;
     /**
      * Enumerations
      */
     public static final int STATE_TRAVELING = 0;
     public static final int STATE_EXPLODING = 1;
     public static final int STATE_DECAYING = 2;
     public static final int STATE_DONE = 3;
     private static final int FIREWORK_SIZE = 2; // frame size from center
}
