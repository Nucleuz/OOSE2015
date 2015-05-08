package example;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Menu {

	// Declaring a few variables
	public int height,width;
	private Image menuImage;
	
	// Constructor
	public Menu(int width, int height) throws SlickException{
		this.width = width;
		this.height = height;
		menuImage = new Image("images/Menu.png");
	}
	
	// render method for drawing the menu image
	public void render(Graphics g) throws SlickException{
		menuImage.draw();
	}
	
	
}
