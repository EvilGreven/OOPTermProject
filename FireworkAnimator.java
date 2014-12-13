
/*
 * Dependencies
 */
package oop_term_project;
/**
 * FireworkAnimator class for Final Project.
 * This class animates objects.
 * @author Brett Jones, derived from Dr. Sung's version of the class.
 */
public class FireworkAnimator implements Runnable {
    /**
     * The constructor links the animator to the shared data and renderer
     * @param sharedData is the shared data.
     * @param renderer is the renderer.
     */
    public FireworkAnimator(FireworkSharedData sharedData,
            FireworkRenderer renderer) {
        this.sharedData = sharedData;
        this.renderer = renderer;
        delay = new MyTimer();
    }
    /**
     * run() is the main game loop.
     */
    @Override
    public void run() {
        // runs forever
      for (;;) {
          if(delay.isDone()) {
            delay.set(30);
            // update location & shape of each firework
            for (int i = 0; i < sharedData.size(); i++) {
                Firework f = sharedData.get(i);
                f.update();

                if (f.getState() == Firework.STATE_DONE) {
                    sharedData.remove(i);
                    i--; // adjust index in the ArrayList
                }
            }
            // update location & shape of each ship
            for (int i = 0; i < sharedData.sizeShip(); i++) {
                Ship s = sharedData.getShip(i);
                s.update();
                int d = sharedData.cHandler.checkCollision(s);
                if(d > 0)
                    s.hit(d);
                
                if (s.getState() == Ship.STATE_DONE) {
                    if(s.isEnemy) {
                        sharedData.removeShip(i);
                        sharedData.sHandler.addPoints(s.getValue());
                        i--; // adjust index in the ArrayList
                    }
                    else //end this, game is over
                        return;
                }
            }
            renderer.repaint(); // redraw the canvas
        }
      }
    }
    /**
     * Variables
     */
    private MyTimer delay;
    private FireworkSharedData sharedData;
    FireworkRenderer renderer;
}
