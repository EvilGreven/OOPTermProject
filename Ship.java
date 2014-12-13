/*
 * Dependencies
 */
package oop_term_project;
import java.awt.Color;
/**
 * Ship class for Final Project.  Parent of Enemy and Player.
  * @author Brett Jones
 */
public class Ship extends MovingObject {
    /**
     * The constructor method does setup for the ship.
     */
    public Ship () {
        cannon = new Weapon(100, Weapon.TYPE_CANNON);
        alternate = new Weapon(600, Weapon.TYPE_MISSILE);
        aDelay = new MyTimer();
        cDelay = new MyTimer();
        isEnemy = true;
        size = 22;
        maxHealth = 250;
        health = maxHealth;
        value = 0;
        lives = 1;
        speed = 3;
    }
    /**
     * setPath() specifies the link to the shared data.
     * @param sharedData 
     */
    public void setPath(FireworkSharedData sharedData) {
        this.sharedData = sharedData;
    }
    /**
     * hit() damages the ship by an amount and destroys the ship if health is 0.
     * If the ship has multiple lives, this resets weapons and removes one life.
     * @param damage is the amount of damage done.
     */
    public void hit(int damage) {
        health -= damage;
        if(health <= 0) {
            if(lives > 0) {
                health = maxHealth;
                lives--;
                cannon.reset();
                alternate.reset();
            }
            else
                state = STATE_DONE;
            explode();
        }
    }
    /**
     * explode() creates an explosion where the ship was destroyed.
     */
    public void explode() {
        sharedData.oFactory.newFirework(unitX, unitY, Color.ORANGE,
                (int)unitY, 22, 1, 1, isEnemy, 0);
    }
    /**
     * levelUp() increases the level of the ship's weaponry.
     */
    public void levelUp() {
        cannon.level();
        alternate.level();
    }
    /**
     * getX() is used to get the x-coordinate of the ship.
     * @return returns the ship's x-coordinate as a float.
     */
    public float getX() {
        return unitX;
    }
    /**
     * getIntX() is used to get the x-coordinate of the ship.
     * @return returns the ship's x-coordinate as an integer.
     */
    public int getIntX() {
        return (int)unitX;
    }
    /**
     * getY() is used to get the y-coordinate of the ship.
     * @return returns the ship's y-coordinate as a float.
     */
    public float getY() {
        return unitY;
    }
    /**
     * getIntY() is used to get the y-coordinate of the ship.
     * @return returns the ship's y-coordinate as an integer.
     */
    public int getIntY() {
        return (int)unitY;
    }
    /**
     * fireCannon() attempts to fire the, but it may be on delay.
     */
    public void fireCannon()
    {
        firingCannon = true;
        if(cDelay.isDone())
            cDelay.set(cannon.fire(sharedData, isEnemy, unitX, unitY));
    }
    /**
     * fireAlternate() attempts to fire the alternate, but it may be on delay.
     */
    public void fireAlternate()
    {
        firingAlternate = true;
        if(aDelay.isDone())
            aDelay.set(alternate.fire(sharedData, isEnemy, unitX, unitY));
    }
    /**
     * stopCannon() is used to stop firing the cannon.
     */
    public void stopCannon() {
        firingCannon = false;
    }
    /**
     * stopAlternate() is used to stop firing the alternate.
     */
    public void stopAlternate() {
        firingAlternate = false;
    }
    /**
     * update() checks to see if the ship is firing and handles movement.
     */
    @Override
    public void update() {
        if(firingCannon)
            fireCannon();
        if(firingAlternate)
            fireAlternate();
       updateState();
       if (state == STATE_TRAVELING) updateLocation();
       else if (state == STATE_EXPLODING) updateSize();
     }
    /**
     * getSize() is used to get the size of the ship.
     * @return returns the size of the ship.
     */
    public int getSize() {
        return size;
    }
    /**
     * getValue() is used to get the point value of the ship.
     * @return returns the point value of the ship.
     */
    public int getValue() {
        return value;
    }
    /**
     * getLives() is used to get the lives of the ship.
     * @return returns the lives of the ship.
     */
    public int getLives() {
        return lives;
    }
    /**
     * Variables
     */
    public int image;
    public boolean isEnemy;
    protected int health;
    protected int maxHealth;
    protected int size;
    protected int value;
    protected int lives;
    protected boolean firingCannon;
    protected boolean firingAlternate;
    protected MyTimer cDelay;
    protected MyTimer aDelay;
    protected Weapon cannon;
    protected Weapon alternate;
    protected FireworkSharedData sharedData;
    /**
     * Enumerations
     */
    protected static final int FIRE_UP = -1;
    protected static final int FIRE_DOWN = 1;
}
