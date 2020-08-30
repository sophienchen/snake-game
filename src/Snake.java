/**
 * CIS 120 Game HW

 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * Snake object: the body parts of the snake are represented by a linked list of points.
 * Each point stores the x and y coordinates of the body part.
 */
public class Snake extends GameObj {
		
	// initial parameters
    public static final int SIZE = 10;
    public static final int INIT_POS_X = 20;
    public static final int INIT_POS_Y = 20;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static int score;
    private int direction = 0;
    
    // integer values representing the snake's direction
    public static final int NO_DIRECTION = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    /**
    * Construct snake by using the superclass constructor called with the correct parameters.
    */
    public Snake(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
    }
    
    public int getDirection() {
    	return this.direction;
    }
    
    public void setDirection(int direction) {
    	this.direction = direction;
    }
    
    @Override
    public void move() {
    	
    	Point head = gameObjs.peekFirst();
    	Point newHead = new Point();
    	
    	//move snake by adding a new head and removing its old tail
    	//position of new head depends on the snake's moving direction
    	if (this.direction == 1) {
    		newHead.setLocation(head.x - 10, head.y);
    	} else if (this.direction == 2) {
    		newHead.setLocation(head.x + 10, head.y);
    	} else if (this.direction == 3) {
    		newHead.setLocation(head.x, head.y + 10);
    	} else if (this.direction == 4) {
    		newHead.setLocation(head.x, head.y - 10);
    	}
    	
    	gameObjs.addFirst(newHead);
    	gameObjs.removeLast();
 	
    }
    
    public void grow () {
    	//grow snake by adding a new point to the end of the list
    	Point tail = getTail();
    	Point newTail = new Point();
    	if (this.direction == 1) {
    		newTail.setLocation(tail.x + 10, tail.y);
    	} else if (this.direction == 2) {
    		newTail.setLocation(tail.x - 10, tail.y);
    	} else if (this.direction == 3) {
    		newTail.setLocation(tail.x, tail.y - 10);
    	} else if (this.direction == 4) {
    		newTail.setLocation(tail.x, tail.y + 10);
    	}
    	gameObjs.addLast(newTail);
    }
    
    @Override
    public boolean hasHitWall() {
    	//retrieve coordinates of snake head
    	Point p = getHead();
    	return ((p.x + this.vx < 0)||(p.x + this.vx > this.maxX)||(p.y + this.vy < 0)
    	    	||(p.y + this.vy > this.maxY));
    }
    
    public boolean hasHitSnake() {

    	//loop through the list to check if the snake has hit itself
    	for (int i=1; i < gameObjs.size(); i++) {
        	Point p = getHead();
        	Point o = gameObjs.get(i);
        	
        	// if the coordinates of the head is the same as any other snake part
        	// then the snake has hit itself
        	if ((o.x == p.x) && (o.y == p.y)) {
        		return true;
        	}        	
    	} // if not, then the snake has not hit itself
    	return false;
    }
    
    public Point getHead() {
    	return gameObjs.peekFirst();
    }
    
    public Point getTail() {
    	return gameObjs.peekLast();
    }
    
    public int getLength() {
    	return gameObjs.size();
    }
    
    public int getScore() {
    	return score;
    }
    
    public void setScore(int x) {
    	score = x;
    }
    
    public void addScore() {
    	score += 1;
    }
    
    public boolean contains(Point p) {
    	return (gameObjs.contains(p));
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        
    	// draw snake body
    	for (Point p : gameObjs) {
    		// draw each part with rectangle
    		g.fillRect(p.x, p.y, 10, 10);
    	}
    	
    }
}
