package example;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player{

	private int x, y;
	private int width, height;
	private Image playerImg;

	
	public Player(int x, int y, int width, int height) throws SlickException {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		playerImg = new Image("images/player1.png");
	}
	
	public void render(Graphics g) throws SlickException{
		playerImg.draw(x,y);
	}

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
