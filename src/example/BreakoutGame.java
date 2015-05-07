package example;
import java.io.Console;
import java.security.cert.CertPathChecker;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.util.vector.Vector;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;
import com.sun.java.swing.plaf.windows.WindowsOptionPaneUI;

public class BreakoutGame extends BasicGame
{
	// Declaring two variables for width and Height of the game window
	private static int windowWidth = 650;
	private static int windowHeight = 480;
	
	// Declaring two variables for the position of the player.
	private int x,y;
	
	// Declaring a player of the Player class.
	private Player player;
	private Ball ball;
	private Obstacle[] obstacles = new Obstacle[66];
	private Obstacle[] movingObstacles = new Obstacle[2];
	private float brickSpeed = 2f;
	private static boolean isInMenu = true;
	private static boolean isDead = false;
	private Menu menu;
	private Dead dead;
	//private Sound sound;
	

	// Declaring images for graphics
	//private Image playerImg;
	
	private Image brickBrick;
	private Image brickYellow;
	private Image brickGreen;
	private Image brickSteel;
	//private Image Menu;
	
	private boolean checkCollision;
	
	public BreakoutGame(String gamename)
	{
		super(gamename);
		
	}

	// This method runs before the game starts.
	@Override
	public void init(GameContainer gc) throws SlickException {
		x = (windowWidth-100)/2;
		y = windowHeight-40;
				
		
		// Initializing player, ball, menu and dead
		player = new Player(x,y,96,25);
		ball = new Ball(windowWidth/2, windowHeight-150, 10, 10);
		menu = new Menu(0,0);
		dead = new Dead(0,0);
		//sound = new Sound("sounds/Dong_1.wav");
		
		// Initializing all static obstacles and setting the width and height
		for(int i = 0; i<obstacles.length; i++){
			obstacles[i] = new Obstacle(50,50,50, 17);
			
		}
		
		// Initializing all moving obstacles and setting the width and height
		for(int i = 0; i < movingObstacles.length; i++){
			movingObstacles[i] = new Obstacle(50, 50, 50, 17);
		}
		
		// Initializing images
		//playerImg = new Image("images/player.png");
		//Menu = new Image("images/Menu.png");
		brickBrick = new Image("images/brick_brick.png");
		brickYellow = new Image("images/yellow_brick.png");
		brickGreen = new Image("images/green_brick.png");
		brickSteel = new Image("images/steel_brick.png");
		
		checkCollision = false;
		
		// Declaring and initializing tempX and tempY for where the first brick will spawn
		//if(isDead == false || isInMenu == true){
		int tempX = 50;
		int tempY = 50;
		for(int j=0; j < obstacles.length; j++){
			obstacles[j].setX(tempX);
			obstacles[j].setY(tempY);
			tempX += 50;
			if(tempX >= 600){
				tempX = 50;
				tempY += 17;
			}
		}
		
		for(int j = 0; j < movingObstacles.length; j++){
			if (j < 1 )
				movingObstacles[j].setX(windowWidth-100);
				movingObstacles[j].setY(200);
			if ( j > 0)
				movingObstacles[j].setX(50);
				movingObstacles[j].setY(200);
		}
	}
	
	// This method runs every frame (just like Unity's Update method)
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		
		//GameContainer gc1;
		Input input1 = gc.getInput();
		if(input1.isKeyPressed(Input.KEY_SPACE)){
			isInMenu = false;
			ball.ballsLeft = 3;
			//sound.playAt(1, 0, 0);
		}


		//Checks if the game is in Menu, if it isn't, then run all the code below.
		if(isInMenu == false){
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
			
			//If the player has 0 balls left, show "dead image", and wait for input, and give the player 3 new balls.
			if(ball.ballsLeft == 0){
				isInMenu = true;
				isDead = true;
				ball.ballsLeft = 3;
			}
		}
		
		// Moves the left brick
		movingObstacles[0].setX(movingObstacles[0].getX() + brickSpeed);
		if(movingObstacles[0].getX() <= 0 || movingObstacles[0].getX() >= windowWidth-50)
			brickSpeed *= -1;
		
		// Moves the right brick
		movingObstacles[1].setX(movingObstacles[1].getX() - brickSpeed);
		if(movingObstacles[1].getX() <= 0 || movingObstacles[1].getX() >= windowWidth-50)
			brickSpeed *= -1;

		for(int j = 0; j < obstacles.length; j++){
			if(ball.y+5 < obstacles[j].getY() || ball.x+5 < obstacles[j].getX() || ball.x-5 > obstacles[j].getX()+obstacles[j].getWidth() || ball.y-5 > obstacles[j].getY()+obstacles[j].getHeight()){
				checkCollision = false;
			} else{
				checkCollision = true;
				if(ball.y > obstacles[j].getY()+obstacles[j].getHeight()){
					System.out.println("Hit from below");
					ball.dy *= -1;
				}
				
				if(ball.y < obstacles[j].getY()){
					System.out.println("Hit from above");
					ball.dy *= -1;
				}
				
				if(ball.x < obstacles[j].getX()){
					System.out.println("Hit on the left");
					ball.dx *= -1;
				}
				
				if(ball.x > obstacles[j].getX()+obstacles[j].getWidth()){
					System.out.println("Hit on the right");
					ball.dx *= -1;
				}
				obstacles[j].setX(9999);
				obstacles[j].setY(9999);
			}
		}
		
		for(int k = 0; k < movingObstacles.length; k++){
			if(ball.y+5 < movingObstacles[k].getY() || ball.x+5 < movingObstacles[k].getX() || ball.x-5 > movingObstacles[k].getX()+movingObstacles[k].getWidth() || ball.y-5 > movingObstacles[k].getY()+movingObstacles[k].getHeight()){
				checkCollision = false;
			} else{
				if(ball.y > movingObstacles[k].getY()+movingObstacles[k].getHeight()){
					System.out.println("Hit from below");
					ball.dy *= -1;
				}
				
				if(ball.y < movingObstacles[k].getY()){
					System.out.println("Hit from above");
					ball.dy *= -1;
				}
				
				if(ball.x < movingObstacles[k].getX()){
					System.out.println("Hit on the left");
					ball.dx *= -1;
				}
				
				if(ball.x > movingObstacles[k].getX()+movingObstacles[k].getWidth()){
					System.out.println("Hit on the right");
					ball.dx *= -1;
				}
			}
		}
		
//		if(ball.y < obstacles[j].y || ball.x < obstacles[j].x || ball.x > obstacles[j].x+obstacles[j].width || ball.y > obstacles[j].y+obstacles[j].height){
//			ball.dy *= -1;
//		}
		
//		if(ball.y1 < player.y1 || ball.x1 < player.x1 || ball.y2 > player.y2 || ball.x2 > player.x2){
//			checkCollision = false;
//		} else{
//			System.out.println("Collision! !!! ");
//			checkCollision = true;
//			ball.dy *= -1;
//		}
		
		if(ball.y+5 < player.getY() || ball.x+5 < player.getX() || ball.x-5 > player.getX()+player.getWidth() || ball.y-5 > player.getY()+player.getHeight()){
		checkCollision = false;
	} else{
		if(ball.y > player.getY()+player.getHeight()){
			System.out.println("Hit from below");
			ball.dy *= -1;
		}
		
		if(ball.y < player.getY()){
			System.out.println("Hit from above");
			ball.dy *= -1;
		}
		
		if(ball.x < player.getX()){
			System.out.println("Hit on the left");
			ball.dx *= -1;
		}
		
		if(ball.x > player.getX()+player.getWidth()){
			System.out.println("Hit on the right");
			ball.dx *= -1;
		}
	}

		Input input = gc.getInput();
//		ball.x = input.getMouseX();
//		ball.y = input.getMouseY();
		
		// Checks if the left arrow key is pressed down and makes sure that we cannot move out of the left side of the window.
//		if(input.isKeyDown(Input.KEY_LEFT) && player.x >= 0){
//			player.x -= 1 * i;
//		}
		player.setX(input.getMouseX() - player.getWidth()/2);
		// Checks if the right arrow key is pressed down and makes sure that we cannot move out of the right side of the window.
//		if(input.isKeyDown(Input.KEY_RIGHT) && player.x <= windowWidth-100){
//			player.x += 1 * i;
//		}
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
			init(gc);
		}
		
		//If you press space, render all of the objects
		if(isInMenu == false || isDead == true){
			isDead = false;
			

		// Level indicator on top right corner
		g.setColor(Color.white);
		g.drawString("Level 1", windowWidth-85, 10);

		// Set background color
		g.setBackground(Color.lightGray);
		
		// Draws the balls left
		g.drawString("Balls left: " + ball.ballsLeft, windowWidth/2-60, 10);
		
		// Draw the player image in the player's position
		//playerImg.draw(player.x,player.y);
		player.render(g);
		ball.render(g);
		
//		Input input1 = gc.getInput();
//		if(input1.isKeyPressed(Input.KEY_SPACE)){
//			for(int i = 0; i < obstacles.length; i++){
//				if(obstacles[i].getY() > 30 && obstacles[i].getY() < 70){
//					brickYellow.draw(obstacles[i].getX(),obstacles[i].getY());
//				}
//				
//				if(obstacles[i].getY() > 70 && obstacles[i].getY() < 110){
//					brickGreen.draw(obstacles[i].getX(),obstacles[i].getY());
//				}
//				
//				if(obstacles[i].getY() > 110 && obstacles[i].getY() < 150){
//					brickBrick.draw(obstacles[i].getX(),obstacles[i].getY());
//				}
//
//			}
//			
//			for(int i = 0; i < movingObstacles.length; i++){
//					brickSteel.draw(movingObstacles[i].getX(),movingObstacles[i].getY());
//					//g.fillRect(movingObstacles[i].x, movingObstacles[i].y, movingObstacles[i].width, movingObstacles[i].height);
//				}
//		}
		
		// Go through the position of all the obstacles and draw the appropriate image in its position.
		//if(isDead == true){
		for(int i = 0; i < obstacles.length; i++){
			if(obstacles[i].getY() > 30 && obstacles[i].getY() < 70){
				brickYellow.draw(obstacles[i].getX(),obstacles[i].getY());
			}
			
			if(obstacles[i].getY() > 70 && obstacles[i].getY() < 110){
				brickGreen.draw(obstacles[i].getX(),obstacles[i].getY());
			}
			
			if(obstacles[i].getY() > 110 && obstacles[i].getY() < 150){
				brickBrick.draw(obstacles[i].getX(),obstacles[i].getY());
			}

		}
		
		for(int i = 0; i < movingObstacles.length; i++){
				brickSteel.draw(movingObstacles[i].getX(),movingObstacles[i].getY());
				//g.fillRect(movingObstacles[i].x, movingObstacles[i].y, movingObstacles[i].width, movingObstacles[i].height);
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
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(BreakoutGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}