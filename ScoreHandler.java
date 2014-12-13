/*
 * Dependencies
 */
package oop_term_project;
/**
 * ScoreHandler class for Final Project, which handles all scoring.
  * @author Brett Jones
 */
public class ScoreHandler {
    /**
     * The constructor() initializes the member variables.
     */
    public ScoreHandler() {
        score = 0;
        shots = 0;
        hits = 0;
    }
    /**
     * addPoints() is used to increase the score.
     * @param p is the number of points to add.
     */
    public void addPoints(int p) {
        score += p;
    }
    /**
     * getScore() is used to obtain the score.
     * @return returns the score.
     */
    public long getScore() {
        return score;
    }
    /**
     * shot() is used to add to the shots tally.
     * @param i is the number of shots to add.
     */
    public void shot(int i) {
        shots+=i;
    }
    /**
     * hit() is used to add to the hits tally.
     */
    public void hit() {
        hits++;
    }
    /**
     * calculate() increases the score by a weighted hit ratio.
     */
    public void calculate() {
        score = score + (int)((double) score * 0.25
                * ((double)hits / (double)shots));
    }
    /**
     * Variables
     */
    private long score;
    private long shots;
    private long hits;
}
