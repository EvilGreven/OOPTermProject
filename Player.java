/*
 * Dependencies
 */
package oop_term_project;
/**
 * Player class for Final Project.
  * @author Brett Jones
 */
public class Player extends Ship {
    /**
     * The constructor initializes all member variables.
     * @param x is the initial x-coordinate.
     * @param y is the initial y-coordinate.
     */
    public Player(int x, int y) {
        unitX = (float)x;
        unitY = (float)y;
        this.setTarget(unitX, unitY);
        isEnemy = false;
        speed = 7;
        maxHealth = 1000;
        health = maxHealth;
        lives = 3;
    }
    /**
     * fireCannon() is the player-specific method to fire the cannon.
     */
    @Override
    public void fireCannon()
    {
        firingCannon = true;
        if(cDelay.isDone()) {
            cDelay.set(cannon.fire(sharedData, isEnemy, unitX-11, unitY+2)
                    + cannon.fire(sharedData, isEnemy, unitX-7, unitY)
                    + cannon.fire(sharedData, isEnemy, unitX+7, unitY)
                    + cannon.fire(sharedData, isEnemy, unitX+11, unitY+2));
            sharedData.sHandler.shot(4);
        }
    }
    /**
     * fireAlternate() is the player-specific method to fire the alternate.
     */
    @Override
    public void fireAlternate()
    {
        firingAlternate = true;
        if(aDelay.isDone()) {
            aDelay.set(alternate.fire(sharedData, isEnemy, unitX, unitY));
            sharedData.sHandler.shot(1);
        }
    }
}
