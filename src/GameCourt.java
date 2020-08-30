/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;



import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Snake snake; // the Black Square, keyboard control
    private Apple apple; // the Apple
    private Bomb bomb;
    private boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."

    // Game constants
    public static final int COURT_WIDTH = 500;
    public static final int COURT_HEIGHT = 500;
    public static final int SNAKE_VELOCITY = 1;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;
    
    public static final String instructions_img = "files/instructions.png";
    private static BufferedImage img;
    public boolean pressedInstructions = false;

    public GameCourt(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        
        try {
            if (img == null) {
                img = ImageIO.read(new File(instructions_img));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the snake to move whenever an arrow key is pressed, by
        // changing the snake's velocity accordingly. (The tick method below actually moves the
        // snake.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {                	
                    snake.setVx(-SNAKE_VELOCITY);
                    snake.setVy(0);
                    snake.setDirection(1);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.setVx(SNAKE_VELOCITY);
                    snake.setVy(0);
                    snake.setDirection(2);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {                    
                    snake.setVx(0);
                    snake.setVy(SNAKE_VELOCITY);
                    snake.setDirection(3);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	snake.setVx(0);
                    snake.setVy(-SNAKE_VELOCITY);
                    snake.setDirection(4);
                }
            }
            
        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	
    	//reset game objects
        snake = new Snake(COURT_WIDTH, COURT_HEIGHT);
        apple = new Apple(COURT_WIDTH, COURT_HEIGHT);
        bomb = new Bomb(COURT_WIDTH, COURT_HEIGHT);
        
        //reset playing status
        playing = true;
        
        //reset score
        snake.setScore(0);
        
        //reset status label
        status.setText("Score: " + Integer.toString(snake.getScore()));

        //reset instructions button status
        pressedInstructions = false;
        
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    
    public void instructions() {
    	pressedInstructions = true;
    	requestFocusInWindow();
    	repaint();
    	
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
            // advance the snake in its current direction.
            snake.move();
            
            // check to see if snake caught an apple
            if (apple.intersects(snake)) {
            	// if so, spawn a new apple, grow snake, then add score
            	apple.spawn();
            	bomb.spawn();
            	snake.grow();
            	snake.addScore();
            	status.setText("Score: " + Integer.toString(snake.getScore()));
            }
            
            // check for the game end conditions: snake has hit the wall or hit itself
            if (bomb.intersects(snake) || snake.hasHitWall() || snake.hasHitSnake()) {
            	System.out.println(bomb.intersects(snake));
            	// if so, set playing status to false, then display game over status
            	playing = false;
            	status.setText("Game Over! Your score was: " + Integer.toString(snake.getScore()));
            }

            // update the display
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pressedInstructions) {
        	g.drawImage(img, 0, 0, 500, 500, null);
        } 
        else {
        snake.draw(g);
        apple.draw(g);
        bomb.draw(g);
    }
}
       

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}