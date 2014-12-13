/*
 * Dependencies
 */
package oop_term_project;
/**
 * Enemy class for Final Project.
  * @author Brett Jones
 */
public class Enemy extends Ship {
    /**
     * Constructor initializes the enemy.
     * @param x is the initial x-coordinate.
     * @param y is the initial y-coordinate.
     * @param t is the type.
     */
    public Enemy(int x, int y, int t) {
        cannon = new Weapon(1000, Weapon.TYPE_CANNON);
        alternate = new Weapon(6000, Weapon.TYPE_MISSILE);
        unitX = (float)x;
        unitY = (float)y;
        type = t;
        this.setTarget(unitX, unitY);
        isEnemy = true;
        if(type == TYPE1)
            firingCannon = true;
        else if(type == TYPE2)
            firingAlternate = true;
        value = 1000 * t;
        lives = 1;
        speed = 1;
    }
    /**
     * update() updates the enemy, and may ask for more enemies if offscreen.
     */
    @Override
    public void update() {
        if(firingCannon)
            fireCannon();
        if(firingAlternate)
            fireAlternate();
       updateState();
       if(unitY > sharedData.canvasHeight + 22) {
           //System.out.println("DESTROYING ENEMY");
             state = STATE_DONE;
             sharedData.oFactory.newEnemy((sharedData.canvasWidth / 2) 
                        + (sharedData.canvasWidth / 4),0, type);
             if((int)(Math.random()*10) > 4)
                  sharedData.oFactory.newEnemy(sharedData.canvasWidth / 4,0,
                       Enemy.TYPE2);
       }
       if (state == STATE_TRAVELING) updateLocation();
       else if (state == STATE_EXPLODING) updateSize();
     }
    /**
     * hit() adds to score, triggers player level up, and may add more enemies.
     * @param damage 
     */
    @Override
        public void hit(int damage) {
        health -= damage;
        if(health <= 0) {
            state = STATE_DONE;
            sharedData.oFactory.newEnemy(22 +
                (int)(Math.random()*(sharedData.canvasWidth-45)), 0, type);
            if(type == TYPE2) {
                Ship s = sharedData.getShip(0);
                s.levelUp();
            }
            if((int)(Math.random()*10) > 8) {
                sharedData.oFactory.newEnemy(22 +
                (int)(Math.random()*(sharedData.canvasWidth-45)), 0, type);
                Ship s = sharedData.getShip(0);
                s.levelUp();
            }
            explode();
        }
    }
    /**
     * Variables
     */
    protected int type;
    /**
     * Enumerations
     */
    protected static int TYPE1 = 1;
    protected static int TYPE2 = 2;
}
