package example;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player{

	// Declaring a few variables
	private int x, y, width, height;
	private Image playerImg;

	// Constructer
	public Player(int x, int y, int width, int height) throws SlickException {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		playerImg = new Image("images/player1.png");
	}
	
	// render method for drawing the image of the player.
	public void render(Graphics g) throws SlickException{
		playerImg.draw(x,y);
	}

	// Getters and setters methods for the player.
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
}
