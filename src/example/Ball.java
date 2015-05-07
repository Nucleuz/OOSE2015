package example;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ball {
	
//	Random rx = new Random();
//	Random ry = new Random();
//	public int x = 650;
//	public int y = 480;
//
//	int rX = rx.nextInt(x-y)+x;
//	int rY = ry.nextInt(y-x)+y;
	
	public float x,y;

	
	public int width, height;
	
	public float dx = 2f;
	public float dy = 2f;
	
	public int widthSize = 10;
	public int heightSize = 10;
	public int radius = (widthSize + heightSize) / 2;
	
	public int ballsLeft = 0;
	
	private Image ballImg;
	
	public Ball(int x, int y, int width, int height) throws SlickException{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		ballImg = new Image("images/ball1.png");
	}
	
	public void render(Graphics g) throws SlickException{
		ballImg.draw(x-5,y-5);
	}
}
