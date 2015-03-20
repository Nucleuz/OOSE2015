package example;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

public class BreakoutGame extends BasicGame
{
	// Declaring two variables for width and Height of the game window
	private static int windowWidth = 640;
	private static int windowHeight = 480;
	
	// Declaring two variables for the position of the player.
	private float x,y;
	
	// Declaring a player of the Player class.
	private Player player;
	
	public BreakoutGame(String gamename)
	{
		super(gamename);
	}

	// This method runs before the game starts.
	@Override
	public void init(GameContainer gc) throws SlickException {
		x = (windowWidth-100)/2;
		y = windowHeight-40;
		player = new Player(x,y,100,25);

	}

	// This method runs every frame (just like Unity's Update method)
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		player.position(x, y);

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
		g.drawString("Hello World!", 250, 200);
		player.render(gc,g);
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