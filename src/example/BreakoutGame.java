package example;
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

public class BreakoutGame extends BasicGame
{
	// Declaring two variables for width and Height of the game window
	private static int windowWidth = 650;
	private static int windowHeight = 480;
	
	// Declaring two variables for the position of the player.
	private float x,y;
	
	// Declaring a player of the Player class.
	private Player player;
	private Ball ball;
	private Obstacle[] obstacles = new Obstacle[66];
	private Obstacle[] movingObstacles = new Obstacle[2];

	// Declaring images for graphics
	private Image playerImg;
	
	private Image brickBrick;
	private Image brickYellow;
	private Image brickGreen;
	private Image brickSteel;
	
	
	public BreakoutGame(String gamename)
	{
		super(gamename);
	}

	// This method runs before the game starts.
	@Override
	public void init(GameContainer gc) throws SlickException {
		x = (windowWidth-100)/2;
		y = windowHeight-40;
		
		// Initializing player and ball
		player = new Player(x,y,100,25);
		ball = new Ball(new Vector2f(100,100), new Vector2f(500,100));
		
		// Initializing all obstacles and set the width and height
		for(int i = 0; i<obstacles.length; i++){
			obstacles[i] = new Obstacle(50, 17);
			
		}
		
		// Initializing images
		playerImg = new Image("images/player.png");
		
		brickBrick = new Image("images/brick_brick.png");
		brickYellow = new Image("images/yellow_brick.png");
		brickGreen = new Image("images/green_brick.png");
		brickSteel = new Image("images/steel_brick.png");

	}

	// This method runs every frame (just like Unity's Update method)
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		player.position(x, y);
		ball.update(i);
		int tempX = 50;
		int tempY = 50;
		for(int j=0; j < obstacles.length; j++){
			obstacles[j].position(tempX, tempY);
			tempX += 50;
			if(tempX >= 600){
				tempX = 50;
				tempY += 17;
			}
		}
		
		Input input = gc.getInput();
		
		// Checks if the left arrow key is pressed down and makes sure that we cannot move out of the left side of the window.
		if(input.isKeyDown(Input.KEY_LEFT) && x >= 0){
			x -= 0.4 * i;
		}
		
		// Checks if the right arrow key is pressed down and makes sure that we cannot move out of the right side of the window.
		if(input.isKeyDown(Input.KEY_RIGHT) && x <= windowWidth-100){
			x += 0.4 * i;
		}
	}

	// This is used to render things, like drawing on the game window.
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		// Level indicator on top right corner
		g.setColor(Color.white);
		g.drawString("Level 1", windowWidth-85, 10);

		// Set background color
		g.setBackground(Color.lightGray);
		
		// Draw the player image in the player's position
		playerImg.draw(player.x1,player.y1);
		
		// Set color of ball and fill it with fillOval
		g.setColor(Color.red);
		g.fillOval(ball.pos.getX()-10,ball.pos.getY()-20,20,20);
		
		// Go through the position of all the obstacles and draw the appropriate image in its position.
		for(int i = 0; i < obstacles.length; i++){
			if(obstacles[i].y > 30 && obstacles[i].y < 70){
				brickBrick.draw(obstacles[i].x,obstacles[i].y);
			}
			
			if(obstacles[i].y > 70 && obstacles[i].y < 110){
				brickGreen.draw(obstacles[i].x,obstacles[i].y);
			}
			
			if(obstacles[i].y > 110 && obstacles[i].y < 150){
				brickYellow.draw(obstacles[i].x,obstacles[i].y);
			}
			
			if(obstacles[i].y > 150){
				brickSteel.draw(obstacles[i].x,obstacles[i].y);
			}
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
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(BreakoutGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}