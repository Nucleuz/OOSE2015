package example;
import java.io.Console;

import java.security.cert.CertPathChecker;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import com.sun.java.swing.plaf.windows.WindowsOptionPaneUI;

public class BreakoutGame extends BasicGame
{
	// Declaring two variables for width and Height of the game window
	private static int windowWidth = 650;
	private static int windowHeight = 480;
	
	// Declaring objects of the player and ball class
	private Player player;
	private Ball ball;
	
	// Declaring and initializing arrays of obstacle objects.
	private Obstacle[] obstacles = new Obstacle[91];
	private Obstacle[] movingObstacles = new Obstacle[2];
	
	// Speed for the moving obstacles
	private float brickSpeed = 1.5f;

	// Declaring images for graphics
	private Image brickBrick;
	private Image brickYellow;
	private Image brickGreen;
	private Image brickSteel;
	
	// Constructor
	public BreakoutGame(String gamename)
	{
		super(gamename);
		
	}

	// This method runs before the game starts.
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		// Initializing player and ball
		player = new Player(windowWidth-100/2,windowHeight-40,96,25);
		ball = new Ball(windowWidth/2, windowHeight-150, 10, 10);
		
		// Initializing all static obstacles and setting the width and height
		for(int i = 0; i<obstacles.length; i++){
			obstacles[i] = new Obstacle(0,25,50, 17);
			
		}
		
		// Initializing all moving obstacles and setting the width and height
		for(int i = 0; i < movingObstacles.length; i++){
			movingObstacles[i] = new Obstacle(50, 50, 50, 17);
		}
		
		// Initializing images
		brickBrick = new Image("images/brick_brick.png");
		brickYellow = new Image("images/yellow_brick.png");
		brickGreen = new Image("images/green_brick.png");
		brickSteel = new Image("images/steel_brick.png");
		
		// Declaring and initializing tempX and tempY for where the first brick will spawn
		int tempX = 0;
		int tempY = 25;
		
		// Sets the position of all obstacles
		for(int j=0; j < obstacles.length; j++){
			obstacles[j].x = tempX;
			obstacles[j].y = tempY;
			tempX += 50;
			if(tempX >= 650){
				tempX = 0;
				tempY += 17;
			}
		}
		
		// Sets the position of all moving obstacles
		for(int j = 0; j < movingObstacles.length; j++){
			if (j < 1 )
				movingObstacles[j].x = windowWidth-100;
				movingObstacles[j].y = 200;
			if ( j > 0)
				movingObstacles[j].x = 50;
				movingObstacles[j].y = 200;
		}
	}

	// This method runs every frame (just like Unity's Update method)
	@Override
	public void update(GameContainer gc, int i) throws SlickException {

		// Moves the ball in the x and y axis
		ball.x += ball.dx;
		ball.y += ball.dy;
		
		// Changes the vertical direction of the ball
		if(ball.y < 0){
			ball.dy *= -1;
		}
		
		// Changes the horizontal direction of the ball
		if(ball.x < 0 || ball.x > windowWidth - ball.radius){
			ball.dx *= -1;
		}
		
		// Makes sure that we decrease the "Balls left: " each time the player misses the ball
		if(ball.y > windowHeight + 100){
			ball.ballsLeft--;
			ball.x = windowWidth/2;
			ball.y = windowHeight/2;
		}
		
		// Moves the left brick
		movingObstacles[0].x += brickSpeed;
		if(movingObstacles[0].x <= 0 || movingObstacles[0].x >= windowWidth-50)
			brickSpeed *= -1;
		
		// Moves the right brick
		movingObstacles[1].x -= brickSpeed;
		if(movingObstacles[1].x <= 0 || movingObstacles[1].x >= windowWidth-50)
			brickSpeed *= -1;

		// Checks if there is a collision between the ball and obstacles
		// It basically starts by checking if the ball is NOT hitting an obstacle, but if it does, then the "else" runs.
		for(int j = 0; j < obstacles.length; j++){
			if(ball.y+5 < obstacles[j].y || ball.x+5 < obstacles[j].x || ball.x-5 > obstacles[j].x+obstacles[j].width || ball.y-5 > obstacles[j].y+obstacles[j].height){
			
			} else{
				// Checks if the ball hits the bottom of the obstacle
				if(ball.y > obstacles[j].y+obstacles[j].height){
					System.out.println("Hit from below");
					ball.dy *= -1;
				}
				
				// Checks if the ball hits the top of the obstacle
				if(ball.y < obstacles[j].y){
					System.out.println("Hit from above");
					ball.dy *= -1;
				}
				
				// Checks if the ball hits the left of the obstacle
				if(ball.x < obstacles[j].x){
					System.out.println("Hit on the left");
					ball.dx *= -1;
				}
				
				// Checks if the ball hits the right of the obstacle
				if(ball.x > obstacles[j].x+obstacles[j].width){
					System.out.println("Hit on the right");
					ball.dx *= -1;
				}
				
				// Moves the bricks out of the window - Easy way of making it look like they are destroyed
				obstacles[j].x = 9999;
				obstacles[j].y = 9999;
			}
		}
	
		// Check if there is a collision between the ball and the moving obstacles
		// It basically starts by checking if the ball is NOT hitting a moving obstacle, but if it does, then the "else" runs.
		for(int k = 0; k < movingObstacles.length; k++){
			if(ball.y+5 < movingObstacles[k].y || ball.x+5 < movingObstacles[k].x || ball.x-5 > movingObstacles[k].x+movingObstacles[k].width || ball.y-5 > movingObstacles[k].y+movingObstacles[k].height){

			} else{
				// Checks if the ball hits the bottom of the obstacle
				if(ball.y > movingObstacles[k].y+movingObstacles[k].height){
					System.out.println("Hit from below");
					ball.dy *= -1;
				}
				
				// Checks if the ball hits the top of the obstacle
				if(ball.y < movingObstacles[k].y){
					System.out.println("Hit from above");
					ball.dy *= -1;
				}
				
				// Checks if the ball hits the left of the obstacle
				if(ball.x <= movingObstacles[k].x){
					System.out.println("Hit on the left");
					ball.dx *= -1;
				}
				
				// Checks if the ball hits the right of the obstacle
				if(ball.x >= movingObstacles[k].x+movingObstacles[k].width+1){
					System.out.println("Hit on the right");
					ball.dx *= -1;
				}		
			}
		}
	
		// Checks if there is a collision between the ball and the player
		// It basically starts by checking if the ball is NOT hitting the player, but if it does, then the "else" runs.
		if(ball.y+5 < player.getY() || ball.x+5 < player.getX() || ball.x-5 > player.getX()+player.getWidth() || ball.y-5 > player.getY()+player.getHeight()){

		} else{
			// Checks if the ball hits the bottom of the player
			if(ball.y > player.getY()+player.getHeight()){
				System.out.println("Hit from below");
				ball.dy *= -1;
			}
			
			// Checks if the ball hits the top of the player
			if(ball.y < player.getY()){
				System.out.println("Hit from above");
				ball.dy *= -1;
			}
			
			// Checks if the ball hits the left of the player
			if(ball.x <= player.getX()){
				System.out.println("Hit on the left");
				ball.dx *= -1;
			}
			
			// Checks if the ball hits the right of the player
			if(ball.x > player.getX()+player.getWidth()){
				System.out.println("Hit on the right");
				ball.dx *= -1;
			}	
		}
		
		// Used for handling inputs, such as keyboard, mouse and controller inputs.
		Input input = gc.getInput();
		
		// Makes sure that the player can control the paddle with the mouse.
		player.setX(input.getMouseX() - player.getWidth()/2);
	}

	// This is used to render things, like drawing on the game window.
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		// Level indicator on top right corner
		g.setColor(Color.white);
		g.drawString("Level 1", windowWidth-85, 3);

		// Set background color
		g.setBackground(Color.lightGray);
		
		// Draws the balls left
		g.drawString("Balls left: " + ball.ballsLeft, 0+10, 3);
		
		// Call the render method in the player class.
		player.render(g);
		
		// Call the render method in the ball class.
		ball.render(g);		
		
		// Go through the position of all the obstacles and draw the appropriate image in its position.
		for(int i = 0; i < obstacles.length; i++){
			if(obstacles[i].y >= 0 && obstacles[i].y < 70){
				brickYellow.draw(obstacles[i].x,obstacles[i].y);
			}
			
			if(obstacles[i].y > 50 && obstacles[i].y < 110){
				brickGreen.draw(obstacles[i].x,obstacles[i].y);
			}
			
			if(obstacles[i].y > 84 && obstacles[i].y < 150){
				brickBrick.draw(obstacles[i].x,obstacles[i].y);
			}
		}
		
		// Go through the position of all the moving obstacles and draw the appropriate image in its position.
		for(int i = 0; i < movingObstacles.length; i++){
				brickSteel.draw(movingObstacles[i].x,movingObstacles[i].y);
		}
	}
	

	// This is the first method that runs when you start the program.
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new BreakoutGame("Breakout by Hans & Mikkel"));
			appgc.setDisplayMode(windowWidth, windowHeight, false);
			appgc.setTargetFrameRate(120);
			appgc.setShowFPS(false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(BreakoutGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}