package example;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Dead {

	// Declaring a few variables
	public int height,width;
	private Image deadImage;
	
	// Constructor
	public Dead(int width, int height) throws SlickException{
		this.width = width;
		this.height = height;
		deadImage = new Image("images/Dead.png");
	}
	
	// render method for drawing the dead image
	public void render(Graphics g) throws SlickException{
		deadImage.draw();
	}
	
}
