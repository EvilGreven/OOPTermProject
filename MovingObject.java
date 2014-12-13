/*
 * Dependencies
 */
package oop_term_project;
import java.awt.geom.Point2D;
import javax.vecmath.Vector2f;
/**
 * MovingObject class for Final Project.
  * @author Brett Jones
 */
public class MovingObject {
    /**
     * setTarget() sets the target for the object.
     * @param x is the target x-coordinate.
     * @param y is the target y-coordinate.
     */
    public void setTarget(float x, float y) {
        state = STATE_TRAVELING;
        target = new Point2D.Float(x, y);
    }
    /**
     * setRadius() sets the explosion radius.
     * @param r is the radius.
     */
    public void setRadius(int r) {
        radius = r;
    }
    /**
     * setSpeed() sets the speed.
     * @param s is the speed.
     */
    public void setSpeed(int s) {
        speed = s;
    }
    /**
     * setX() sets the x-coordinate.
     * @param x is the x-coordinate.
     */
    public void setX(int x) {
        unitX = (float)x;
    }
    /**
     * setY() sets the y-coordinate.
     * @param y is the y-coordinate.
     */
    public void setY(int y) {
        unitY = (float)y;
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
        if(target == null) {
            target = new Point2D.Float(unitX, unitY);
        }
        Vector2f currentLoc = new Vector2f(unitX, unitY);
        Vector2f update = new Vector2f(target.x, target.y);
         //System.out.println("x: " + target.x + ", y: " + target.y);
         //System.out.println("currA: " + currentLoc.x + ", " + currentLoc.y);
         update.sub(currentLoc); // B - A
        update.normalize(); // |B - A|
        update.scale(speed); // |B - A| x dist
        currentLoc.add(update); // A + |B - A| x d
        //System.out.println("currB: " + currentLoc.x + ", " + currentLoc.y);
	double distance = target.distance(unitX, unitY);
        if(distance > (float)speed) {
            unitX = currentLoc.x;
            unitY = currentLoc.y;
        }
    }
    /**
     * updateSize() updates the explosion size.
     */
     public void updateSize() {
       double x = target.getX();
       double y = target.getY();
     }
     /**
      * update() determines state and updates accordingly.
      */
     public void update() {
       updateState();
       if (state == STATE_TRAVELING) updateLocation();
       else if (state == STATE_EXPLODING) updateSize();
     }
     /**
      * updateState() determines the state.
      */
     public void updateState() {
	if (state == STATE_TRAVELING ) {
            double distance = 0.0;
	  if(target != null)
               distance = target.distance(unitX, unitY);
	  boolean targetReached = distance <= 3.0 ? true : false;
	//if (targetReached) state = STATE_EXPLODING;
	//} else if (state == STATE_EXPLODING) {
        //    state = STATE_DONE;
	}
     }
     /**
      * getState() returns the state.
      * @return returns the state.
      */
     public int getState() {
       return state;
     }
     /**
      * Variables
      */
     protected Point2D.Float target;
     protected float unitX, unitY; // unit x y increment
     protected int radius;
     protected int speed = 3;
     /**
      * Enumerations
      */
     public static final int STATE_TRAVELING = 0;
     public static final int STATE_EXPLODING = 1;
     public static final int STATE_DONE = 2;
     protected int state = STATE_TRAVELING;
}
