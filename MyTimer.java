/*
 * Dependencies
 */
package oop_term_project;
/**
 * MyTimer class for Final Project, which handles timing.
 * @author Brett Jones
 */
public class MyTimer {
    /**
     * Constructor resets the member variables.
     */
    public MyTimer() {
	reset();
    }
    /**
     * set() sets the target time.
     * @param m is the millisecond target time.
     */
    public void set(int m) {
        reset();
        start();
        targetTime = m;
    }
    /**
     * start() starts the timer.
     */
    public void start() {
	//System.gc();
        startTime = System.currentTimeMillis();
    }
    /**
     * end() ends the timer.
     */
    public void end() {
	endTime = System.currentTimeMillis();
    }
    /**
     * isDone() checks the time to see if it meets or exceeds the target time.
     * @return returns true or false.
     */
    public boolean isDone() {
        end();
        if(targetTime <= duration())
            return true;
        return false;
    }
    /**
     * duration() returns the duration between the start time and end time.
     * @return 
     */
    public long duration() {
	return (endTime - startTime);
    }
    /**
     * reset() resets the timer.
     */
    public final void reset() {
        startTime = 0;
        endTime = 0;
    }
    /**
     * Variables
     */
    private long startTime;
    private long endTime;
    private long targetTime;
}
