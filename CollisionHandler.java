/*
 * Dependencies
 */
package oop_term_project;
import java.awt.geom.Point2D;
/**
 * CollisionHandler class for Final Project.
  * @author Brett Jones
 */
public class CollisionHandler {
    /**
     * setPath() links to the shared data.
     * @param sharedData is the shared data.
     */
    public void setPath(FireworkSharedData sharedData) {
        this.sharedData = sharedData;
    }
    /**
     * checkCollision() determines if a ship is hit by a firework projectile.
     * @param s is the Ship to examine.
     * @return returns the total damage dealt by exploding projectiles.
     */
    public int checkCollision(Ship s) {
        int damage = 0;
        Point2D.Float t = new Point2D.Float(s.getX(), s.getY());
        for(int j = 0; j < sharedData.size(); j++) {
             Firework f = sharedData.get(j);
            if(s.isEnemy != f.getOwner()) {
                double d = t.distance(f.getCenterX(), f.getCenterY());
                if((int)d <= f.getSize() + s.getSize()) {
                    f.destroy();
                    damage += f.getDamage();
                    sharedData.sHandler.hit();
                }
            }
        }
        return damage;
    }
    /**
     * Variables
     */
    private FireworkSharedData sharedData;
}
