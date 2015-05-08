package example;

import java.util.Random;
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
	private float brickSpeed = 2f;
	
	
	private static boolean isInMenu = true;
	private static boolean isDead = false;
	private Menu menu;
	private Dead dead;
	//private Sound sound;
	
	
	private Random rnd;
	private int randomX;

	// Declaring images for graphics
	private Image brickBrick;
	private Image brickYellow;
	private Image brickGreen;
	private Image brickSteel;
	//private Image Menu;
	
	// Constructor
	public BreakoutGame(String gamename)
	{
		super(gamename);
		
	}

	// This method runs before the game starts.
	@Override
	public void init(GameContainer gc) throws SlickException {
		// Initializing player, ball, menu and dead
		player = new Player((windowWidth-100)/2,windowHeight-40,96,25);
		ball = new Ball(randomX, windowHeight/2, 10, 10);
		menu = new Menu(0,0);
		dead = new Dead(0,0);
		//sound = new Sound("sounds/Dong_1.wav");
		
		// Initializing all static obstacles and setting the width and height
		for(int i = 0; i<obstacles.length; i++){
			obstacles[i] = new Obstacle(0,25,50, 17);
		}
		
		// Initializing all moving obstacles and setting the width and height
		for(int i = 0; i < movingObstacles.length; i++){
			movingObstacles[i] = new Obstacle(50, 50, 50, 17);
		}
		
		// Initializing images
		//Menu = new Image("images/Menu.png");
		brickBrick = new Image("images/brick_brick.png");
		brickYellow = new Image("images/yellow_brick.png");
		brickGreen = new Image("images/green_brick.png");
		brickSteel = new Image("images/steel_brick.png");
		
		spawnObstacles();
	}
	
	public void spawnObstacles(){

		if(isDead == false || isInMenu == true){
		// Declaring and initializing tempX and tempY for where the first brick will spawn
		int tempX = 0;
		int tempY = 25;
		
		// Sets the position of all obstacles
		for(int j=0; j < obstacles.length; j++){
			obstacles[j].setX(tempX);
			obstacles[j].setY(tempY);
			tempX += 50;
			if(tempX >= 650){
				tempX = 0;
				tempY += 17;
			}
		}
		
		// Sets the position of all moving obstacles
		for(int j = 0; j < movingObstacles.length; j++){
			if (j < 1 )
				movingObstacles[j].setX(windowWidth-100);
				movingObstacles[j].setY(200);
			if ( j > 0)
				movingObstacles[j].setX(50);
				movingObstacles[j].setY(200);
		}
	}
	}
	
	// This method runs every frame (just like Unity's Update method)
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		rnd = new Random();
		randomX = 125+rnd.nextInt((525-125)+1);
		Input input1 = gc.getInput();
		if(input1.isKeyPressed(Input.KEY_SPACE)){
			isInMenu = false;
			isDead = false;
			ball.ballsLeft = 3;
			//sound.playAt(1, 0, 0);
		}


		//Checks if the game is in Menu, if it isn't, then run all the code below.
		if(isInMenu == false){
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
				ball.x = randomX;
				ball.y = windowHeight/2;
				
				
			}
			
			// Moves the left moving obstacle
			movingObstacles[0].setX(movingObstacles[0].getX() + brickSpeed);
			if(movingObstacles[0].getX() <= 0 || movingObstacles[0].getX() >= windowWidth-50)
				brickSpeed *= -1;
			
			// Moves the right moving obstacle
			movingObstacles[1].setX(movingObstacles[1].getX() - brickSpeed);
			if(movingObstacles[1].getX() <= 0 || movingObstacles[1].getX() >= windowWidth-50)
				brickSpeed *= -1;
	
			// Checks if there is a collision between the ball and obstacles
			// It basically starts by checking if the ball is NOT hitting an obstacle, but if it does, then the "else" runs.
			for(int j = 0; j < obstacles.length; j++){
				if(ball.y+5 < obstacles[j].getY() || ball.x+5 < obstacles[j].getX() || ball.x-5 > obstacles[j].getX()+obstacles[j].getWidth() || ball.y-5 > obstacles[j].getY()+obstacles[j].getHeight()){
					
				} else{
					// Checks if the ball hits the bottom of the obstacle
					if(ball.y > obstacles[j].getY()+obstacles[j].getHeight()){
						System.out.println("Hit from below");
						ball.dy *= -1;
					}
					
					// Checks if the ball hits the top of the obstacle
					if(ball.y < obstacles[j].getY()){
						System.out.println("Hit from above");
						ball.dy *= -1;
					}
					
					// Checks if the ball hits the left of the obstacle
					if(ball.x < obstacles[j].getX()){
						System.out.println("Hit on the left");
						ball.dx *= -1;
					}
					
					// Checks if the ball hits the right of the obstacle
					if(ball.x > obstacles[j].getX()+obstacles[j].getWidth()){
						System.out.println("Hit on the right");
						ball.dx *= -1;
					}
					
					// Moves the bricks out of the window - Easy way of making it look like they are destroyed
					obstacles[j].setX(9999);
					obstacles[j].setY(9999);
				}
				//If the player has 0 balls left, show "dead image", and wait for input, and give the player 3 new balls.
				if(ball.ballsLeft == 0){
					isInMenu = true;
					isDead = true;
					ball.ballsLeft = 3;
				}
			}
			// Check if there is a collision between the ball and the moving obstacles
			// It basically starts by checking if the ball is NOT hitting a moving obstacle, but if it does, then the "else" runs.
			for(int k = 0; k < movingObstacles.length; k++){
				if(ball.y+5 < movingObstacles[k].getY() || ball.x+5 < movingObstacles[k].getX() || ball.x-5 > movingObstacles[k].getX()+movingObstacles[k].getWidth() || ball.y-5 > movingObstacles[k].getY()+movingObstacles[k].getHeight()){
					
				} else{
					// Checks if the ball hits the bottom of the obstacle
					if(ball.y > movingObstacles[k].getY()+movingObstacles[k].getHeight()){
						System.out.println("Hit from below");
						ball.dy *= -1;
					}
					
					// Checks if the ball hits the top of the obstacle
					if(ball.y < movingObstacles[k].getY()){
						System.out.println("Hit from above");
						ball.dy *= -1;
					}
					
					// Checks if the ball hits the left of the obstacle
					if(ball.x < movingObstacles[k].getX()){
						System.out.println("Hit on the left");
						ball.dx *= -1;
					}
					
					// Checks if the ball hits the right of the obstacle
					if(ball.x > movingObstacles[k].getX()+movingObstacles[k].getWidth()){
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
				if(ball.x < player.getX()){
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
	}

	// This is used to render things, like drawing on the game window.
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//Shows the menu
		if(isInMenu == true){
			menu.render(g);
		}
		
		//If you have 0 balls left, show dead
		if(isDead == true){
			dead.render(g);
			spawnObstacles();
		}
		
		//If you press space, render all of the objects
		if(isInMenu == false && isDead == false){
			

		// Level indicator on top right corner
		g.setColor(Color.white);
		g.drawString("Level 1", windowWidth-85, 3);

		// Set background color
		g.setBackground(Color.lightGray);
		
		// Draws the balls left
		g.drawString("Balls left: " + ball.ballsLeft, 10, 3);
		
		// Call the render method in the player class.
		player.render(g);
				
		// Call the render method in the ball class.
		ball.render(g);	
		
		// Go through the position of all the obstacles and draw the appropriate image in its position.
		for(int i = 0; i < obstacles.length; i++){
			if(obstacles[i].getY() >= 0 && obstacles[i].getY() < 70){
				brickYellow.draw(obstacles[i].getX(),obstacles[i].getY());
			}
			
			if(obstacles[i].getY() > 50 && obstacles[i].getY() < 110){
				brickGreen.draw(obstacles[i].getX(),obstacles[i].getY());
			}
			
			if(obstacles[i].getY() > 84 && obstacles[i].getY() < 150){
				brickBrick.draw(obstacles[i].getX(),obstacles[i].getY());
			}

		}
		
		// Go through the position of all the moving obstacles and draw the appropriate image in its position.
		for(int i = 0; i < movingObstacles.length; i++){
				brickSteel.draw(movingObstacles[i].getX(),movingObstacles[i].getY());
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