/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;


/**
 * Apple objects can be eaten by the snake. 
 * Each time the snake eats an apple, the score increments by 1.
 * The apple will initially spawn at a random position within the court.
 */

public class Apple extends BoardObj {
	
    public static final int SIZE = 20;
    public static final int INIT_POS_X = (int)(Math.random() * 460);
    public static final int INIT_POS_Y = (int)(Math.random() * 460);
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;
    public static final String img_file = "files/apple.png";
    public static final int max_apples = 5;
    
    private static BufferedImage img;
    
    public LinkedList<Point> appleObjs;
   

    public Apple(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
        
        try {
            if (img == null) {
                img = ImageIO.read(new File(img_file));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        
        appleObjs = new LinkedList<Point>();
        appleObjs.add(new Point(INIT_POS_X, INIT_POS_Y));
        
    }
    @Override
    public void spawn() {
    	if (appleObjs.size() <= max_apples) {
    		int randx = (int)(Math.random() * maxX);
    		int randy = (int)(Math.random() * maxY);
    		appleObjs.add(new Point(randx, randy));
    	}    	
	}
    
    @Override
	public boolean intersects(GameObj that) {    			    	
    		for (Point p : appleObjs){
	    		Point head = that.gameObjs.peekFirst();
	    		int i = appleObjs.indexOf(p);
	    		if (p.x + width >= head.x
	    	            && p.y + height >= head.y
	    	            && head.x + that.width >= p.x 
	    	            && head.y + that.height >= p.y) {		    			
	    			
	    			remove(i);
	    			return true;
	    		}     		
	    	}  		
	    	return false;  
	    }
    
    public void remove(int i) {
    	appleObjs.remove(i);
    }
    
    @Override
    public void draw(Graphics g) {
    	for(int i = 0; i < appleObjs.size(); i++) {
			Point p = appleObjs.get(i);
			g.drawImage(img, p.x, p.y, this.getWidth(), this.getHeight(), null);
		}
    		    
    }
}