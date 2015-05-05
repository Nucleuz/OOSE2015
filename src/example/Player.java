package example;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Player{

	public int x, y;
	public int width, height;
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
}
