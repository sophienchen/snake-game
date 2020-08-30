=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: sophiech
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the three core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections
  
  I used LinkedLists to model the snake, apples, and bombs. I chose to use LinkedLists because of 
  its ordering feature, it had methods (e.g. peekfirst()) that were very helpful in getting/setting 
  location of each object. 
  
  I designed the LinkedList to contain Points, with each point representing one snake node. 
  Since a point already has an x and y coordinate, this structure made it easier to track and 
  change the position of snake parts. The first element of the list represents the snake's head. 
  The last element represents the snake's tail. I added/removed/changed elements from the linked 
  list in almost all of my methods, including move(), grow(), intersects()...etc. I also looped 
  through the linked list of apples and bombs to check if any of them intersected with the snake 
  head. If that happens for the apple, an element from the linked list is removed to show the 
  apple "disappearing" after being eaten.


  2. Inheritance/Subtyping
  
  I used Java's subtyping feature by creating four new classes: snake, BoardObj, apple, and bomb.
  The Snake class extends GameObj. The Apple and Bomb classes both extend the abstract BoardObj 
  class. The BoardObj class then extends GameObj. I chose to do this because I wanted the snake, 
  apple, and the bomb to all take on methods that were already defined in the GameObj class, 
  including all the getters, setters. At the same time, the three classes needed methods that 
  are implemented differently. For example, snake implements a move method that updates the linked 
  list of points according to its direction. The snake also implements a new grow method to get 
  longer whenever it eats an apple. The apple and the bomb must be able to spawn at random positions
  , and detect whenever it intersects with the snake head. All of these differentiating 
  characteristics were reasons for this design of inheritance.
  

  3. JUnit testing
  
  I used JUnit testing to test the methods that were important to the game's function, such as the
  snake's grow and move method, the hasHitWall method, and the intersect method. I tested
  the getters and setters that I used frequently for retrieving and updating information, 
  to make sure that the methods return/change the values correctly. I also tested the encapsulation
  of the game, ensuring that simply moving the snake will not change its length.
  

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game — Game is the class containing the run() method, which executes the program and displays
  		 the different components that we see on the screen. The method displays the JFrames and
  		 JPanels, which contains the GameCourt, the "Instructions" button, nd the "Reset" button 
  		 that allows the player to play again. The Main() method starts and runs the game by 
  		 initializing the GUI elements.
  
  GameCourt — GameCourt contains all the main game logic for how different elements interact with
  			  each other. It contains a snake and an apple, which are updated before the screen
  			  gets redrawn. The KeyListener translates inputs (key presses) into changes in the
  			  snake's direction and velocity. This class contains the timer, which triggers action 
  			  based on an interval. The tick method defines the actions that should take place in 
  			  each time-step based on the state of the snake (if the snake eats an apple, if the
  			  snake hits the wall or itself), while repainting to update the display. The reset 
  			  method resets the game back to its initial state. The GameCourt class also displays 
  			  the instructions image whenever the user presses the "instructions" button.
  
  GameObj — GameObj is the base abstract class that contain methods for common behaviors across
  			different objects in the game. It defines coordinate, size, and velocity parameters
  			for the object (px, py, width, height, vx, vy), and the maximum bounds for the object
  			to be positioned (maxX, maxY). The GameObj constructor takes in parameters mentioned
  			above, instantiates a LinkedList of Points, and adds the first object to the list.
  			It contains many helpful getters, setters, and other basic methods such as 
  			Clip, and Move. 
  			
  Snake —  Snake is a subclass of GameObj. A snake is a GameObj represented by a LinkedList of
  		   Points. Each point represent a snake node. The first point in the list represents the 
  		   snake head, and the last point represents the snake tail. The Snake class overrides many 
  		   methods from GameObj, and defines it differently to fit the snake's characteristics. 
  		   For example, the move method manipulates the linked list elements based on the snake's 
  		   direction. The Snake class contains many new methods, including grow (adds a node to the 
  		   snake), hasHitSnake (checks if the snake has hit itself), getHead, getTail, getLength, 
  		   setScore()...etc. The class also defines integer values that store the snake's direction.
  		   
  BoardObj — BoardObj is an abstract subclass of GameObj. It inherits all the methods defined in
 			 GameObj, while implementing new methods including spawn (generating new board objects
 			 at random positions on the court), Intersects (check if the board object has
 			 intersected with another object), and remove (remove an element from the board object
 			 linked list).

  Apple — Apple is a subclass of BoardObj. The Apple class reads in the apple image, and overrides
 		  the draw method from BoardObj to draw the object. The class overrides all methods defined
 		  in BoardObj. One important difference here is that when the apple intersects an object,
 		  that specific apple in the linked list is removed. The intersect method is called under 
 		  tick in GameCourt, whenever the snake intersects ("eats") an
 		  apple.
 		  
  Bomb — Bomb is another subclass of BoardObj. It overrides all methods from BoardObj, with some
  		 changes in implementation. The difference between the Apple and the Bomb is that more than 
  		 one bomb can be on the court at the same time. If there are less than 8 bombs on the court,
  		 then a random number of bombs (between 0-2) will be generated at random locations whenever
  		 the snake eats an apple.The bomb also does not get removed when the snake head intersects 
  		 it.
 		  

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  I think the biggest challenge was ensuring a smooth interaction between my classes. Initially,
  the apple and the bomb were not behaving in the way I expected it to, so I had to look through 
  almost all of my classes to locate the bug and make sure that methods 
  (especially ones from other classes) are being called correctly. 
  
  Additionally, since I decided to reuse and adapt a lot of the "Mushroom of Doom" source code 
  provided to us for this game, it took some time for me to fully understand the function and 
  purpose of the source code. There were several bugs caused by the fact that I forgot to change 
  some parts of the source code or I changed it incorrectly. The implementation of the grow and 
  move methods for the snake was also difficult. Since the snake is constantly moving at different 
  directions, it was tricky to decide where to add the new head or tail.
 
 
- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I think the functionality is well separated by the different classes. There are some values, such
  as the snake's direction, that are maintained private in its class. However, if given the chance, 
  I would refactor to keep more values private for encapsulation, when possible. 


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  I referred to the Google Snake game for conceptualizing the movement and growth of the snake.
  (https://www.google.com/search?q=play+snake)
  
  I also referred to Javadocs for LinkedList and BufferedImage.
  https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
  https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/image/BufferedImage.html
  
  I used Canva to make my instructions window.
  
