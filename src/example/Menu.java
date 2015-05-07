package example;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Menu {

	public int height,width;
	private Image menuImage;
	
	public Menu(int width, int height) throws SlickException{
		this.width = width;
		this.height = height;
		menuImage = new Image("images/Menu.png");
	}
	
	public void render(Graphics g) throws SlickException{
		menuImage.draw();
	}
	
	
}
