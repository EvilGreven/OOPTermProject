/*
 * Dependencies
 */
package oop_term_project;
import java.awt.Color;
/**
 * Weapon class for Final Project.  This class creates fireworks.
  * @author Brett Jones
 */
public class Weapon {
    /**
     * The Weapon() constructor.
     * @param rof is the rate of fire for the weapon.
     * @param t is the type of weapon.
     */
    public Weapon(int rof, int t) {
        rateOfFire = rof;
        type = t;
        level = 0;
        if(type == TYPE_CANNON) {
            radius = 3;
            color = new Color((float)1.0, (float)0.25, (float)0.25);
            elongation = 1;
            speed = 7;
            damage = 20;
        }
        else if(type == TYPE_MISSILE) {
            radius = 25;
            color = new Color((float)0.25, (float)0.25, (float)1.0);
            elongation = 7;
            speed = 5;
            damage = 100;
        }
    }
    /**
     * The fire() method is responsible for creating firework projectiles.
     * @param sharedData is a link to the shared data.
     * @param enemy is a boolean value to determine who owns the projectile.
     * @param x is the x-coordinate point of origin for the projectile.
     * @param y is the y-coordinate point of origin for the projectile.
     * @return returns the rate of fire to the ship for for delay purposes.
     */
    public int fire(FireworkSharedData sharedData,
            boolean enemy, float x, float y) {
        if(enemy) { //enemy firing
            sharedData.oFactory.newFirework(x, y, color,
                    sharedData.canvasHeight+50, radius, elongation,
                    speed, enemy, damage);
        }
        else { //player firing
            sharedData.oFactory.newFirework(x, y, color, -50, radius,
                    elongation, speed, enemy, damage);
        }
        return rateOfFire;
    }
    /**
     * reset() is used to reset the weapon level.
     */
    public void reset() {
        if(type == TYPE_CANNON) {
            radius = 3;
            color = new Color((float)1.0, (float)0.25, (float)0.25);
            elongation = 1;
            speed = 7;
            damage = 20;
        }
        else if(type == TYPE_MISSILE) {
            radius = 50;
            color = new Color((float)0.25, (float)0.25, (float)1.0);
            elongation = 7;
            speed = 5;
            damage = 100;
        }
    }
    /**
     * level() is used to increase the weapon level.
     */
    public void level() {
        if(level < 10) {
            level++;
            color.brighter();
            damage *= 1.1;
            speed *= 1.1;
            radius *= 1.1;
        }
    }
    /**
     * Variables
     */
    protected int damage;
    protected int level;
    protected int rateOfFire;
    protected int radius;
    protected int elongation;
    protected int speed;
    protected int type;
    protected Color color;
    /**
     * Enumerations
     */
    public static final int TYPE_CANNON = 0;
    public static final int TYPE_MISSILE = 1;
}
