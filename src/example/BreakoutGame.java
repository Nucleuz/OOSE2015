package example;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class BreakoutGame extends BasicGame
{
	// Declaring two variables for width and Height of the game window
	private static int windowWidth = 640;
	private static int windowHeight = 480;
	
	// Declaring two variables for the position of the player.
	private float x,y;
	
	// Declaring a variable of type Image to hold an image of the Player.
	private Image playerImg;
	
	
	public BreakoutGame(String gamename)
	{
		super(gamename);
	}

	// This method runs before the game starts.
	@Override
	public void init(GameContainer gc) throws SlickException {
		playerImg = new Image("images/player.png");
		x = (windowWidth-100)/2;
		y = windowHeight-40;
	}

	// This method runs every frame (just like Unity's Update method)
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_LEFT)){
			x -= 0.4 * i;
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT)){
			x += 0.4 * i;
		}
		
	}

	// This is used to render things, like drawing on the game window.
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Hello World!", 250, 200);
		g.drawRect(40,40,50,50);
		playerImg.draw(x,y);
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