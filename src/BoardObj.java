import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;



public abstract class BoardObj extends GameObj {

	public int px; 
    public int py;
    public int width;
    public int height;
    public int vx;
    public int vy;
    public int maxX;
    public int maxY;
    public static int size;
    public LinkedList<Point> boardObjs;

       
    public BoardObj (int vx, int vy, int px, int py, int width, int height, int courtWidth,
            int courtHeight) {
    	super(vx, vy, px, py, width, height, courtWidth, courtHeight);
        this.vx = vx;
        this.vy = vy;
        this.px = px;
        this.py = py;
        this.width  = width;
        this.height = height;
        this.maxX = courtWidth - width;
        this.maxY = courtHeight - height;
        
        boardObjs = new LinkedList<Point>();
        boardObjs.add(new Point(px, py));
        
    }
    	
	public void spawn() {
		int randx = (int)(Math.random() * maxX);
		int randy = (int)(Math.random() * maxY);
		this.setPx(randx);
		this.setPy(randy);
		clip();
	}

	
	@Override
	public boolean intersects(GameObj that) {    	
    	
    	for (int i=0; i < boardObjs.size(); i++) {
    		Point p = boardObjs.get(i);
    		if (p.x + width >= that.px
    	            && p.y + height >= that.py
    	            && that.px + that.width >= p.x 
    	            && that.py + that.height >= p.y) {
  
    			return true;
    		}     		
    	}  		
    	return false;  
    }
	
	public void remove(int i) {
		boardObjs.remove(i);
	}
	
	@Override
	public void clip() {
        this.px = Math.min(Math.max(this.px, 0), maxX);
        this.py = Math.min(Math.max(this.py, 0), maxY);
    }
	
	public abstract void draw(Graphics g);
}