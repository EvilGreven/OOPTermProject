/*
 * Dependencies
 */
package oop_term_project;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * FireworkSharedData class for Final Project.
 * This class shares data between the animator, renderer, and other classes.
 * @author Brett Jones, derived from Dr. Sung's version of the class.
 */
public class FireworkRenderer extends JFrame {
    /**
     * The constructor takes link to the shared data and initializes variables.
     * @param sharedData is the link to the shared data.
     */
    public FireworkRenderer(FireworkSharedData sharedData) {

        this.sharedData = sharedData;
        sharedData.oFactory.setPath(sharedData);
        sharedData.cHandler.setPath(sharedData);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        sharedData.canvasHeight = WINDOW_HEIGHT;
        sharedData.canvasWidth = WINDOW_WIDTH;
        setTitle("Demo 2 - Brett Jones");
        setLocation(100, 100);

        panel = new DrawPanel();
        panel.addMouseListener(new MouseEventListner());
        panel.addMouseMotionListener(new MouseMotionEventListner());
        panel.setBackground(Color.black);
        Container contentPane = getContentPane();
        contentPane.add(panel, "Center");
        
        String imagePath = System.getProperty("user.dir");
        // separator: Windows '\', Linux '/'
        String separator = System.getProperty("file.separator");

        // put images in 'images' folder, which is on the top level of
        // the NetBeans project folder.
        // Using "Files" tab of the NetBeans explorer window, right click on
        // the project folder name, and create a folder named "image"
        // You cannot see "images" folder in 'Project' tab, though
        sharedData.iHandler.addImage(imagePath + separator + "images"
                    + separator + "player.png", ImageHandler.imagePlayer);
        sharedData.iHandler.addImage(imagePath + separator + "images"
                    + separator + "enemy1.png", ImageHandler.imageEnemy1);
        sharedData.iHandler.addImage(imagePath + separator + "images"
                    + separator + "enemy2.png", ImageHandler.imageEnemy2);
        sharedData.oFactory.newPlayer(LAUNCH_XLOC, LAUNCH_YLOC);
    }
    /**
     * MouseEventListener interface.
     */
    private class MouseEventListner extends MouseAdapter {
        /**
         * mousePressed() override to capture user input.
         * @param e is the event.
         */
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            if(gameState == GAME_STARTING)
            {
                sharedData.oFactory.newEnemy(WINDOW_WIDTH / 2, 0, Enemy.TYPE1);
                gameState = GAME_STARTED;
            }
            Ship s = sharedData.getShip(0);
            if(e.getButton() == MouseEvent.BUTTON1)
                s.fireCannon();
            else if(e.getButton() == MouseEvent.BUTTON3)
                s.fireAlternate();

        }
        /**
         * mouseReleased() override to capture user input.
         * @param e is the event.
         */
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            Ship s = sharedData.getShip(0);
            if(e.getButton() == MouseEvent.BUTTON1)
                s.stopCannon();
            if(e.getButton() == MouseEvent.BUTTON3)
                s.stopAlternate();
        }
    }
    /**
     * MouseMotionEventListener interface.
     */
    private class MouseMotionEventListner extends MouseMotionAdapter {
        /**
         * mouseMoved() override to capture user input.
         * @param e is the event.
         */
        @Override
        public void mouseMoved(java.awt.event.MouseEvent e) {
            Ship s = sharedData.getShip(0);
            if(s.getState() == Ship.STATE_DONE) {
                try {
                    endGame();
                }
                catch(IOException ex) {
                }
            }

            if(gameState == GAME_STARTING) {
             s.setX(e.getX());
             s.setY(e.getY());

           }
           else {
             s.setTarget(e.getX(),e.getY());

           }
        }
        /**
         * mouseDragged() override to capture user input.
         * @param e is the event.
         */
        @Override
        public void mouseDragged(java.awt.event.MouseEvent e) {
           Ship s = sharedData.getShip(0);
           if(s.getState() == Ship.STATE_DONE) {
                try {
                    endGame();
                }
                catch(IOException ex) {
                }
            }

           if(gameState == GAME_STARTING) {
             s.setX(e.getX());
             s.setY(e.getY());
           }
           else {
             s.setTarget(e.getX(),e.getY());

           }
        }
    }
    /**
     * DrawPanel class handles painting the window.
     */
    private class DrawPanel extends JPanel {
        /**
         * paintComponent() override paints the window.
         * @param g is the graphics.
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            sharedData.canvasWidth = getWidth();
            sharedData.canvasHeight = getHeight(); // size known after rendering

            //render fireworks
            for (int i = 0; i < sharedData.size(); i++) {
                Firework f = sharedData.get(i);
                g2.setColor(f.getColor());
                g2.fill(f);
            }
            //render ships
            for (int i = 0; i < sharedData.sizeShip(); i++) {
                Ship s = sharedData.getShip(i);
                g2.drawImage(sharedData.iHandler.getImage(s.image),
                        s.getIntX()-22, s.getIntY()-22, panel);
            }
            //render score
            String score = "Lives: " + sharedData.getShip(0).getLives() + "   ";
            score += Long.toString(sharedData.sHandler.getScore());
            g2.setColor(Color.white);
            g2.drawString(score, sharedData.canvasWidth / 2 - 45, 15);
        }
    }
    /**
     * repaint() handles repainting.
     */
    @Override
    public void repaint() {
        panel.repaint();
    }
    /**
     * endGame() ends the game and handles scoring I/O.
     * @throws IOException an IO error.
     */
    private void endGame() throws IOException {
       gameState = GAME_OVER;
       sharedData.sHandler.calculate();
       long score = sharedData.sHandler.getScore();
       String s = (String)JOptionPane.showInputDialog(panel,
               "Score with Accuracy bonus: " + score
               + "\nPlease enter your initials:",
                "GAME OVER", JOptionPane.WARNING_MESSAGE, null, null, "???");
         
       BufferedReader inputStream = null;
       ArrayList<String> strings = new ArrayList<String>();
       String h;
       try {
            inputStream = 
                new BufferedReader(new FileReader("highscores.txt"));
            while ((h = inputStream.readLine()) != null) {
                strings.add(h);
            }
            h = String.format("%1$10s%2$10s", score, s);
            strings.add(h);
            Comparator comparator = Collections.reverseOrder();
            Collections.sort(strings,comparator);
       } finally {
            if (inputStream != null) {
                inputStream.close();
            }
       }
       PrintWriter outputStream = null;
       String d = "";
        try {
            outputStream = 
                new PrintWriter(new FileWriter("highscores.txt"));
            String l;
            for(int i = 0; i < strings.size() && i < 10; i++) {
                l = strings.get(i);
                outputStream.println(l);
                if(h == null ? l == null : h.equals(l))
                    d += ("[" + l + "]\n");
                else
                    d += (l + "\n");
            }
            
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        JOptionPane.showMessageDialog(panel, d, "HIGH SCORES",
            JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

    /**
     * Variables
     */
    private int LAUNCH_XLOC = WINDOW_WIDTH / 2;
    private int LAUNCH_YLOC = WINDOW_HEIGHT - 70;
    private int gameState = GAME_STARTING;
    JPanel panel;
    private FireworkSharedData sharedData;
    /**
     * Enumerations
     */
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static int GAME_STARTING = 0;
    private static int GAME_OVER = 1;
    private static int GAME_STARTED = 2;

}
