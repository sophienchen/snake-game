import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {
	public Snake snake = new Snake(500, 500);
	public Apple apple = new Apple(500, 500);
	public Bomb bomb = new Bomb(500, 500);

    @Test
    public void testGetDirection() {
    	snake.setDirection(1);
        assertEquals(snake.getDirection(), 1);
    }
    
    @Test
    public void testHead() {
    	Point head = snake.gameObjs.peekFirst();
        assertEquals(head, snake.getHead());
    }
    
    @Test
    public void testGetLength() {
        assertEquals(snake.getLength(), 1);
    }
    
    @Test
    public void testSetters() {
    	snake.getHead().setLocation(300, 300);
    	assertEquals(snake.getHead().getX(), 300);
    	assertEquals(snake.getHead().getY(), 300);
    }
    @Test
    public void testGrowSnake() {
    	snake.grow();
        assertEquals(snake.getLength(), 2);
    }
    
    @Test
    public void testMoveSnake() {
    	snake.setDirection(1);
    	snake.move();
        assertEquals(10, snake.getHead().getX());
        assertEquals(20, snake.getHead().getY());
    }
    
    @Test
    public void testMoveReturnToInitialPos() {
    	snake.setDirection(1);
    	snake.move();
    	snake.setDirection(2);
    	snake.move();
        assertEquals(20, snake.getHead().getX());
        assertEquals(20, snake.getHead().getY());
    }
    
    @Test
    public void testMoveChangeDirection() {
    	snake.setDirection(1);
    	snake.move();
    	snake.setDirection(3);
    	snake.move();
        assertEquals(10, snake.getHead().getX());
        assertEquals(30, snake.getHead().getY());
    }
    
    @Test
    public void testMoveMaintainsLength() {
    	snake.move();
    	assertEquals(1, snake.getLength());
    }
    
    @Test
    public void testHasHitWall() {
    	snake.getHead().setLocation(500, 500);
    	assertTrue(snake.hasHitWall());
    }
    
    @Test
    public void testIntersectsApple() {
    	Point p = new Point (50, 50);
    	apple.appleObjs.add(p);
    	snake.getHead().setLocation(50, 50);
    	assertTrue(apple.intersects(snake));
    }
    
    @Test
    public void testIntersectsBomb() {
    	Point p = new Point (50, 50);
    	bomb.bombObjs.add(p);
    	snake.getHead().setLocation(50, 50);
    	assertTrue(bomb.intersects(snake));
    }
    
    @Test
    public void testSetScore() {
    	snake.setScore(2);
    	assertEquals(2, snake.getScore());
    }
    
    @Test
    public void testRemoveApple() {
    	apple.remove(0);
    	assertEquals(0, apple.appleObjs.size());
    }

}
