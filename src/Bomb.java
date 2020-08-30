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

public class Bomb extends BoardObj {
	
	 public static final int SIZE = 20;
	    public static final int INIT_POS_X = (int)(Math.random() * 460);
	    public static final int INIT_POS_Y = (int)(Math.random() * 460);
	    public static final int INIT_VEL_X = 0;
	    public static final int INIT_VEL_Y = 0;
	    public static final String img_file_bomb = "files/bomb.png";
	    public static final int max_bombs = 8;
	    
	    private static BufferedImage img;
	    
	    public LinkedList<Point> bombObjs;

	    public Bomb(int courtWidth, int courtHeight) {
	        super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE, courtWidth, courtHeight);
	        
	        try {
	            if (img == null) {
	                img = ImageIO.read(new File(img_file_bomb));
	            }
	        } catch (IOException e) {
	            System.out.println("Internal Error:" + e.getMessage());
	        }
	        
	        bombObjs = new LinkedList<Point>();
	        bombObjs.add(new Point(INIT_POS_X, INIT_POS_Y));
     	        
	    }
	    
	    // generate the bomb at a random position on the game court
	    
	    @Override
	    public void spawn() {
	    
	    	if (bombObjs.size() <= max_bombs) {
	    		
	    		int numtimes = (int)(Math.random()*2);
	    		
	    		 for (int i =0; i < numtimes; i++) {
	 	    		int randx = (int)(Math.random() * 460);
	 	    		int randy = (int)(Math.random() * 460);
	 	    		bombObjs.add(new Point(randx, randy));
	    		 }
	    		
	    	}    	
	    }
	    
	    @Override
		public boolean intersects(GameObj that) {    			    	
		    	for (Point p : bombObjs) {
		    		Point head = that.gameObjs.peekFirst();
		    		if (p.x + width >= head.x
		    	            && p.y + height >= head.y
		    	            && head.x + that.width >= p.x 
		    	            && head.y + that.height >= p.y) {		    			
		    			return true;
		    		}     		
		    	}  		
		    	return false;  
		    }

	  
	    @Override
	    public void draw(Graphics g) {
	    	for(int i = 0; i < bombObjs.size(); i++) {
				Point p = bombObjs.get(i);
				g.drawImage(img, p.x, p.y, this.getWidth(), this.getHeight(), null);
			}
	    		    
	    }
 }
	
